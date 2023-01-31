package org.tourGo.service.plan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tourGo.common.HttpRequest;
import org.tourGo.common.JsonResult;
import org.tourGo.models.plan.tourList.TourList;
import org.tourGo.models.plan.tourList.TourListDto;

@Service
public class TourService {
	
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
	//ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D
	//ORm3mQovRB97uz6GfTJJNBR/2egRn2vglLUfbXCP+2pblHvBggwbP1wMwnl/RvFZfHqob4GRBHbQDNn6IZP/Fg==
	 private final String BASE_URL = "https://apis.data.go.kr/B551011/KorService/searchKeyword";
	 private final String serviceKey = "ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D";
	 private final String numOfRows = "&numOfRows=10";
	 private final String pageNo= "&pageNo=1";
	 private final String defaultQueryParam = "&MobileOS=ETC&MobileApp=tourGo&_type=json";
	 private final String listYN = "&listYN=Y";
	 private final String arrange = "&arrange=C";
	 private String _keyword = "&keyword=";	 

	 public List<TourListDto> loadApi(String keyword) throws Exception{
		 
		 StringBuilder urlBuilder = new StringBuilder(BASE_URL);
		 urlBuilder.append("?serviceKey=").append(serviceKey);
		 urlBuilder.append("&").append("numOfRows=").append("10");
		 urlBuilder.append("&").append("pageNo=").append("1");
		 urlBuilder.append("&").append("MobileOS=").append("ETC");
		 urlBuilder.append("&").append("MobileApp=").append("tourGo");
		 urlBuilder.append("&").append("_type=").append("json");
		 urlBuilder.append("&").append("listYN=").append("Y");
		 urlBuilder.append("&").append("arrange=").append("C");
		 urlBuilder.append("&").append("keyword=").append(URLEncoder.encode(keyword,"UTF-8"));
		 
		 String makeUrl = urlBuilder.toString();
	
		
		 URI uri = new URI(makeUrl);
		 RestTemplate restTemplate = this.makeRestTemplate();
		  String jsonString = restTemplate.getForObject(uri,String.class);
		
			
		 JSONParser jsonParser = new JSONParser();
		 JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
		  // 가장 큰 JSON 객체 response 가져오기
		    JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
		    // 그 다음 body 부분 파싱
		    JSONObject jsonBody = (JSONObject) jsonResponse.get("body");

		    // 그 다음 위치 정보를 배열로 담은 items 파싱
		    JSONObject jsonItems = (JSONObject) jsonBody.get("items");

		    // items는 JSON임, 이제 그걸 또 배열로 가져온다
		    JSONArray jsonItemList = (JSONArray) jsonItems.get("item");
		    
		    List<TourListDto> result = new ArrayList<>();
		    
		    for (Object o : jsonItemList) {
		        JSONObject item = (JSONObject) o;
		        result.add(makeLocationDto(item));
		    }
		    System.out.println(result);
		 return result;
	 }
	 //restTemplate ssl인증 무시하는 메서드
	 private RestTemplate makeRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
			
	        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

	        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
	        		.loadTrustMaterial(null, acceptingTrustStrategy)
	        		.build();
	        
	        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
	        
	        CloseableHttpClient httpClient = HttpClients.custom()
	        		.setSSLSocketFactory(csf)
	        		.build();
	        
	        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	        requestFactory.setHttpClient(httpClient);
	        
	        requestFactory.setConnectTimeout(3 * 1000);    
	        
	        requestFactory.setReadTimeout(3 * 1000);
	        
	        return new RestTemplate(requestFactory);
	    }

	
	
	private TourListDto makeLocationDto(JSONObject item) {
		
		TourListDto dto = TourListDto.builder()
				.title((String) item.get("title"))
				.address((String)item.get("addr1"))
				.areacode((String)item.get("areacode"))
				.firstimage((String)item.get("firstimage"))
				.mapx((String)item.get("mapx"))
				.mapy((String)item.get("mapy"))
				.build();
		
		return dto;
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
