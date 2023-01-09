package org.tourGo.service.destination;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.common.HttpRequest;
import org.tourGo.models.destination.entity.DestinationDetail;
import org.tourGo.models.entity.user.User;
import org.tourGo.models.user.UserRepository;

@Service
public class DestinationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DestinationDetailRepository destinationDetailRepository;
	
	private String serviceKey = "ORm3mQovRB97uz6GfTJJNBR%2F2egRn2vglLUfbXCP%2B2pblHvBggwbP1wMwnl%2FRvFZfHqob4GRBHbQDNn6IZP%2FFg%3D%3D";
	
	public void responseList(int areaCode, String userId) throws IOException {
		
		User user = userRepository.findByUserId(userId).orElseThrow();
		
		// 서울, 인천, 대전, 대구, 광주, 부산, 울산, 세종특별자치시, 경기도, 강원도
		Map<Integer, String> areaMap = new HashMap<>();
		areaMap.put(1, "서울");
		areaMap.put(2, "인천");
		areaMap.put(3, "대전");
		areaMap.put(4, "대구");
		areaMap.put(5, "광주");
		areaMap.put(6, "부산");
		areaMap.put(7, "울산");
		areaMap.put(8, "세종");
		areaMap.put(31, "경기");
		areaMap.put(32, "강원");
		
		StringBuilder view = new StringBuilder();
		
		try {
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService/areaBasedList").append("?");
			urlBuilder.append("serviceKey=").append(serviceKey);
			urlBuilder.append("&").append("MobileOS=").append(URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&").append("MobileApp=").append(URLEncoder.encode("Tour", "UTF-8"));
			urlBuilder.append("&").append("listYN=").append(URLEncoder.encode("N", "UTF-8"));
			urlBuilder.append("&").append("_type=").append(URLEncoder.encode("json", "UTF-8"));
			urlBuilder.append("&").append("areaCode=").append(URLEncoder.encode(String.valueOf(areaCode), "UTF-8"));
			HttpRequest<Map<String, String>> request = new HttpRequest<>();
			Map<String, String> requestData = new HashMap<>();
			
			request.setUrl(urlBuilder.toString());
			
			StringBuilder sb = new StringBuilder(request.request().toString());
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = null;
		
			/** 1. 지역 1개에 존재하는 전체 관광 데이터 총량 */
			jsonObj = (JSONObject) parser.parse(sb.toString());
			
			Integer totalCnt = 0;
			
			JSONObject response = (JSONObject) jsonObj.get("response");
			JSONObject body = (JSONObject) response.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");
			for(int i = 0; i < item.size(); i++) {
				JSONObject object = (JSONObject) item.get(i);
				
				if(object.containsKey("totalCnt")) {
					totalCnt = Integer.parseInt(object.get("totalCnt").toString());
					// System.out.println(totalCnt);
				}
			}
			
			/** 2. 관광 데이터 갖고오기 */
			Integer count = (totalCnt % 10 == 0) ? totalCnt / 10 : (totalCnt / 10) + 1;
			// System.out.println(count);
			for(int i = 1; i <= count; i++) {
				urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService/areaBasedList").append("?");
				urlBuilder.append("serviceKey=").append(serviceKey);
				urlBuilder.append("&").append("MobileOS=").append(URLEncoder.encode("ETC", "UTF-8"));
				urlBuilder.append("&").append("MobileApp=").append(URLEncoder.encode("Tour", "UTF-8"));
				urlBuilder.append("&").append("numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
				urlBuilder.append("&").append("pageNo=").append(URLEncoder.encode(String.valueOf(i), "UTF-8"));
				urlBuilder.append("&").append("_type=").append(URLEncoder.encode("json", "UTF-8"));
				urlBuilder.append("&").append("areaCode=").append(URLEncoder.encode(String.valueOf(areaCode), "UTF-8"));
				request = new HttpRequest<>();
				
				request.setUrl(urlBuilder.toString());
				
				sb.setLength(0);
				sb.append(request.request().toString());
				
				jsonObj = (JSONObject) parser.parse(sb.toString());
				
				response = (JSONObject) jsonObj.get("response");
				body = (JSONObject) response.get("body");
				items = (JSONObject) body.get("items");
				item = (JSONArray) items.get("item");
				
				System.out.println(urlBuilder.toString());
				
				for(int j = 0; j < item.size(); j++) {
					JSONObject object = (JSONObject) item.get(j);
					
					DestinationDetail detail = DestinationDetail.builder()
															.tourDestination(areaMap.get(Integer.valueOf(object.get("areacode").toString())))
															.tourTitle(object.get("title").toString())
															.tourImg(object.get("firstimage").toString())
															.mapX(Double.valueOf(object.get("mapx").toString()).doubleValue())
															.mapY(Double.valueOf(object.get("mapy").toString()).doubleValue())
															.tourAddr(object.get("addr1").toString())
															.user(user)
															.build();
					
					destinationDetailRepository.save(detail);
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
