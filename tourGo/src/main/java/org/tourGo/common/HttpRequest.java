package org.tourGo.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


/**
 * API 요청을 위한 HTTP 소켓 통신 기능 구현
 * 
 * @author LEE YONGGYO
 */
public class HttpRequest<T> {

	private String method; // 요청 메서드 
	private String url; // 요청 URL 
	private T requestData;
	private String responseData;
	private Map<String, String> headers;
	private boolean json = true; // 요청 타입이 JSON 인지 여부(기본값 true)
	private boolean error;
	
	public HttpRequest<T> request() {
		method = (method == null ||  method.isBlank()) ? "GET" : method.toUpperCase(); // 요청 메서드
		String contentType = json?"application/json; charset=utf-8" : "application/x-www-form-urlencoded; charset=utf-8";
		
		if (url == null || url.isBlank()) {
			throw new HttpClientErrorException(null);
		}
		
		HttpURLConnection conn = null;
		InputStream in = null;
		try {
			URL reqUrl = new URL(url);
			ignoreSsl();
			conn = (HttpURLConnection)reqUrl.openConnection();
			conn.setDoOutput(true); 
			conn.setDoInput(true);
			conn.setRequestMethod(method);
			
			/** 추가 요청 헤더가 있는 경우 추가 S */
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			/** 추가 요청 헤더가 있는 경우 추가 E */
			
			/** POST 일때만 요청 BODY 출력 처리 S */
			if (method.equals("POST")) {
				String requestParams = null;
				if (requestData != null) {
					if (json) {
						ObjectMapper om = new ObjectMapper();
						om.registerModule(new JavaTimeModule());
						requestParams = om.writeValueAsString(requestData);
					} else {
						if (requestData instanceof Map) {
							boolean isFirst = true;
							Map<String, String> maps = (Map<String, String>) requestData;
							StringBuffer sb  = new StringBuffer(100);
							for (Map.Entry<String, String> entry : maps.entrySet()) {
								String key = entry.getKey();
								String value = entry.getValue();
								if (!isFirst) {
									sb.append("&");
								}
								sb.append(key);
								sb.append("=");
								sb.append(URLEncoder.encode(value, "UTF-8"));
							} // endfor 
							if (!sb.isEmpty()) requestParams = sb.toString();
						} // endif 
					}
				}
				
				conn.setRequestProperty("Content-Type", contentType);
				if (requestParams  != null) {
					conn.setRequestProperty("Content-Length", String.valueOf(requestParams.length()));
					OutputStream os =  conn.getOutputStream();
					os.write(requestParams.getBytes());
				}
			}
			/** POST 일때만 요청 BODY 출력 처리 E */
			
			in = conn.getInputStream();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				in = conn.getErrorStream();
			}
			
			setError(true);
		}
		/** 서버 응답 처리 S */
		if (in != null) {
			StringBuffer sb = new StringBuffer(1000);
			try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
				String line = null;
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			responseData = sb.toString();
			if (isError()) {
				System.out.println(responseData);
			}
		}
		/** 서버 응답 처리 E */
		
		return this;
	}
	
	/**
	 * 처리 결과 문자열로 반환 
	 * 
	 */
	public String toString() {
		return responseData;
	}
	
	/** 
	 * JSON으로 변환
	 * 
	 * @param <R>
	 * @param cls
	 * @return
	 */
	public <R> R toJson(Class<R> cls) {
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		R data = null;
		try {
			data = om.readValue(responseData, cls);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public String getMethod() {
		return method;
	}
	
	public HttpRequest<T> setMethod(String method) {
		this.method = method;
		
		return this;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}

	public HttpRequest<T> setHeaders(Map<String, String> headers) {
		this.headers = headers;
		
		return this;
	}
	
	public boolean isJson() {
		return json;
	}

	public HttpRequest<T> setJson(boolean json) {
		this.json = json;
		
		return this;
	}

	public String getUrl() {
		return url;
	}
	
	public HttpRequest<T> setUrl(String url) {
		this.url = url;
		
		return this;
	}
	
	public  T getRequestData() {
		return requestData;
	}
	
	public HttpRequest<T> setRequestData( T requestData) {
		this.requestData = requestData;
		
		return this;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	public static void ignoreSsl() throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

	
	private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
 
    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
 
        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }
 
        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }
 
        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
 
        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }
}
