package org.tourGo.service.plan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tourGo.common.JsonResult;
import org.tourGo.models.plan.tourList.TourList;

@Service
public class TestService {

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

	 
	public JsonResult<?> fetch(String keyword) throws UnsupportedEncodingException{
		String url = BASE_URL+serviceKey+numOfRows+pageNo+defaultQueryParam+listYN+arrange+_keyword+"keyword";
		RestTemplate restTemplate = new RestTemplate();
		 HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
		 ResponseEntity<Map> resultMap = restTemplate.exchange(url,HttpMethod.GET,entity,Map.class);
		 System.out.println(resultMap.getBody());
		
		
		return new JsonResult<>(true, "성공", resultMap);
		
	}
	
	
	
	
}
