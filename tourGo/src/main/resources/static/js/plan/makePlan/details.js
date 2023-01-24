const plannerNo = document.getElementById(`plannerNo`).value;

const tourGo = {
	
	search(){
		const keyword = document.getElementById(`keyword`).value;
		if(!keyword){
			alert("키워드를 입력해주세요");
			return;
		}
		
			const url = `/tourList?keyword=${keyword}`;
			const xhr = new XMLHttpRequest();
		
		xhr.open("GET", url);
		xhr.addEventListener("readystatechange", function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				const items = JSON.parse(xhr.responseText);
					const parentEl = document.querySelector(".api_list");
						const domParser = new DOMParser();
				parentEl.innerHTML ="";	
				
			for (const item of items) {
					const label = document.createElement("label");
					label.dataset.xpos = item.mapx;
					label.dataset.ypos = item.mapy;
					
					let html =`<div class="test" id="tourItem" > `;
					html += `<input type="checkbox" id="clickItem" value="${item}">`;
					html+=`<span>${item.title}</span>`;
					html=+`</div>`;
					label.innerHTML=html;
					parentEl.appendChild(label);
					
					const tourItem = document.getElementById(`tourItem`);
					const clickItem = document.getElementById(`clickItem`);
					tourItem.addEventListener("click",function(){
							const xpos = this.dataset.xpos;
						const ypos = this.dataset.ypos;
						if (kakao && map) {
							const moveLatLon = new kakao.maps.LatLng(ypos, xpos);
							map.panTo(moveLatLon);  
							const marker = new kakao.maps.Marker({
								map:map,
							    position: moveLatLon
							});
							marker.setMap(map);
						}
					});//touritem click이벤트 종료
					clickItem.addEventListener("click",function(){
						
						const xpos = this.dataset.xpos;
						const ypos = this.dataset.ypos;
						if (kakao && map) {
							const moveLatLon = new kakao.maps.LatLng(ypos, xpos);
							map.panTo(moveLatLon);  
							const marker = new kakao.maps.Marker({
								map:map,
							    position: moveLatLon
							});
							marker.setMap(map);
							
						
							
						};
							const newUrl = `/testxml?plannerNo=${plannerNo}`;
							const newXhr = new XMLHttpRequest();
							var formdata = new FormData(frm);
							
							
							formdata.append(`title`,item.title);
							formdata.append(`address`,item.address);
							consoloe.log(formdata);
							newXhr.open("POST",newUrl,true);
							newXhr.send(formdata);
												
						
					});//click이벤트종료
					
					
					
			}
				
				
		
		}//if끝
		
	
	});//xhr끝
	xhr.send();
	
	
	
	
	
	
	}//search()끝
}//tourGo끝


const planner = {
	/**관광지 선택시마다 db에 값 저장, 날짜 변경시에 ajax로 변경사항(stime,etime) db에 업데이트, 마지막에 save누를때도 동일하게 업데이트 */
	loadSelection(){
		const day = $("input[name='day']:checked").val();
		console.log(day);
		var detailsNo = document.getElementsByName("detailsNo");
		var arrayNo = [];
		detailsNo.forEach(function(i){
			arrayNo.push(i.value);
			
			console.log(arrayNo);
		});
		
		var detailsStime = document.getElementsByName("stime");
		var arrayStime = [];
		
		detailsStime.forEach(function(i){
			arrayStime.push(i.value);
			console.log(arrayStime)
		});
		
		var detailsEtime = document.getElementsByName("etime");
		var arrayEtime = [];
		
		detailsEtime.forEach(function(i){
			arrayEtime.push(i.value);
			console.log(arrayEtime)
		});
		var detailsItem = {
			"detailsNo" : arrayNo,
			"stime" : arrayStime,
			"etime" : arrayEtime,
			"plannerNo" : plannerNo,
			"day" : day
		};
		
		// const formData = new FormData(document.frm);
		var formData = new FormData();
		
		formData.append("day", day);
		formData.append("plannerNo",plannerNo);
		formData.append("detailsNo",arrayNo);
		formData.append("stime",arrayStime);
		formData.append("etime",arrayEtime);	
		const xhr = new XMLHttpRequest();
		
		xhr.open("POST", '/select');
		xhr.send(formData);
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				$("#selected_items").replaceWith(xhr.responseText);
			}
		};
		
		xhr.onerror = function(err) {
			console.error(err);
		};
		
		// plannerNo=19&day=3&detailsNo=2&stime=&etime=&detailsNo=1&stime=&etime=
		//const formData = new FormData(document.frm);
		 
	/*	$.ajax({
			url : `/select`,
			data : encodeURIComponent(frm),
			//dataType : 'json',
			type : "POST",
			cache : false,	
			})//ajax끝
		.done(function(fragment){
			$("#selected_items").replaceWith(fragment);
			/*const domParser = new DOMParser();
			const dom = domParser.parseFromString(fragment, "text/html");
			const html = dom.getElementById("selected_items").innerHTML;
			document.getElementById("selected_items").innerHTML = html;
			console.log(html);
				
			console.log(fragment);
		})//done끝
		.fail(function(jqXHR) {
                console.log("에러");
            });//fail끝
            */
            
	}//함수끝
};
let map;

window.addEventListener("DOMContentLoaded", function() {
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
});















