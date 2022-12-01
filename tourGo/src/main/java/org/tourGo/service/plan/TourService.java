package org.tourGo.service.plan;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;
import org.tourGo.models.plan.tourList.TourList;


@Service
public class TourService {
	
	private String serviceKey = "ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D";
	
	
	public List<TourList> process(String keyword) {
		List<TourList> items = null;	
		
		InputStream in = null;
		HttpURLConnection conn = null;
		
		try {
		String apiURL = getURL(keyword);
		URL url = new URL(apiURL);
		}catch(Exception e) {
			
		}
		
	return null;
	}
	
	private String getURL(String keyword) {
		StringBuffer sb = new StringBuffer("https://apis.data.go.kr/B551011/KorService/searchKeyword?");
		sb.append("serviceKey=");
		sb.append(serviceKey);
		sb.append("&MobileOS=ETC&MobileApp=tourGo");
		try {
		sb.append(URLEncoder.encode("관광", "UTF-8"));
		} catch (Exception e) {}
	
		sb.append("&_type=json");
		sb.append("&keyword=");
		sb.append(keyword);
		
		return sb.toString();
	}
	
	
}
