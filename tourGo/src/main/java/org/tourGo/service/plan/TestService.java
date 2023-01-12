package org.tourGo.service.plan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;
import org.tourGo.models.plan.tourList.TourList;

@Service
public class TestService {
	private String serviceKey = "ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D";
	
	
	public List<TourList> process(String keyword) {
		try {
		
		String urlstr = "https://apis.data.go.kr/B551011/KorService/searchKeyword"
		+ "?serviceKey=ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D"
		+ "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=tourGo"
		+ "&_type=json&listYN=Y&arrange=C&keyword="+keyword;
		
		URL url = new URL(urlstr);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
