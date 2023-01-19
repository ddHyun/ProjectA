package org.tourGo.service.plan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;




import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tourGo.common.JsonResult;
import org.tourGo.models.plan.tourList.TourList;

@Service
public class TestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 
	  https://apis.data.go.kr/B551011/KorService/searchKeyword
	  ?serviceKey=ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D
	  &numOfRows=10
	  &pageNo=1
	  &MobileOS=ETC
	  &MobileApp=tourGo
	  &_type=json
	  &listYN=Y
	  &arrange=C
	  &keyword=
	 * */
	 private final String BASE_URL = "https://apis.data.go.kr/B551011/KorService/searchKeyword";
	 private final String serviceKey = "?ServiceKey=ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D";
	 private final String numOfRows = "&numOfRows=10";
	 private final String pageNo= "&pageNo=1";
	 private final String defaultQueryParam = "&MobileOS=ETC&MobileApp=tourGo&_type=json";
	 private final String listYN = "&listYN=Y";
	 private final String arrange = "&arrange=C";
	 private String _keyword = "&keyword=";	 

	 
	public JsonResult<?> fetch(String keyword) throws Exception{
		keyword = URLEncoder.encode(keyword, "UTF-8");
		String url = BASE_URL+serviceKey+numOfRows+pageNo+defaultQueryParam+listYN+arrange+_keyword+keyword;
		System.out.println(url);

//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//		restTemplate.
//		ResponseEntity<Map> resultMap = restTemplate.exchange(url,HttpMethod.GET,entity, Map.class);
//		System.out.println(resultMap.getBody());
//		RestTemplate restTemplate = new RestTemplate();
//		 HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
//		 ResponseEntity<Map> resultMap = restTemplate.exchange(url,HttpMethod.GET,entity,Map.class);
		
		  HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		    // 모든 인증서를 신뢰하도록 설정한다
		    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (X509Certificate[] chain, String authType) -> true).build();
		    httpClientBuilder.setSSLContext(sslContext);

		    // Https 인증 요청시 호스트네임 유효성 검사를 진행하지 않게 한다.
		    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
		            .register("http", PlainConnectionSocketFactory.getSocketFactory())
		            .register("https", sslSocketFactory).build();

		    PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		    httpClientBuilder.setConnectionManager(connMgr);

		    // RestTemplate 와 HttpClient 연결
		    HttpClient httpClient = httpClientBuilder.build();
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		    requestFactory.setHttpClient(httpClient);
		
		
		
		  RestTemplate restTemplate = new RestTemplate(requestFactory);
		  HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<?> entity = new HttpEntity<>(headers);
	        ResponseEntity<Map> resultMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
	        System.out.println(resultMap.getBody());
		
	        
	        
	        
	        
	        
	        
		return new JsonResult<>(true, "성공", resultMap);
		
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
