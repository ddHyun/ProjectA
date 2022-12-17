function newPage()  {
  window.location.href = 'https://www.naver.com'
};

let map;
const tourGo = {
	 
	 search(){
		const keyword = document.getElementById(`keyword`).value;
		if(!keyword){
			throw new Error("키워드를 입력해주세요");
			
		}

			const url = `tourList?keyword=${keyword}`;
				const xhr = new XMLHttpRequest();
		xhr.open("GET", url);
				xhr.addEventListener("readystatechange", function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				const items = JSON.parse(xhr.responseText);
				const parentEl = document.querySelector(".api_list");
				if (!parentEl) {
					return;
				}
				const selectedItemsEl = document.querySelector(".selected_items ul");
				const tpl = document.getElementById("tpl_selectedItems").innerHTML;
				const domParser = new DOMParser();
				parentEl.innerHTML ="";	
				for (const item of items) {
					
					const label = document.createElement("label");
					label.dataset.xpos = item.mapx;
					label.dataset.ypos = item.mapy;
					let html = `<input type="checkbox" id="tourItem" value="${item}">  `;

					if (item.firstimage) {
						html += `<div><img src='${item.firstimage}' ></div>`;
					}
					html += `
						<div>${item.title}</div>
		 					<div>${item.addr1} ${item.addr2}</div>
		    			<div >
		     	
					`;
						
					label.innerHTML = html;
					parentEl.appendChild(label);
					const checkBoxEl = document.getElementById(`tourItem`);
					label.addEventListener("click", function() {
						const targetId = "contentId_" + item.contentid;
						// 이미 선택 아이템이 있다면 추가 X
						if (document.getElementById(targetId)) {
							return;
						}
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
    					
    					if (!selectedItemsEl) {
							return;
						}
						
						let html = tpl;
						const address = item.addr1 + " " + item.addr2;
						
						html = html.replace(/<%=firstimage>/g,item.firstimage)
									.replace(/<%=title%>/g, item.title)
									.replace(/<%=address%>/g, address)
									.replace(/<%=id%>/g, item.contentid)
									;
						html += `
						<div>
		       
		           		 	<label for="check">시간</label>
		            		<input type="time" name="checkTime id="check">        
		      		  	</div>
		      		  	
		      		  	`;		
									
						const dom = domParser.parseFromString(html, "text/html");
						const liEl = dom.querySelector("li");		
						
						selectedItemsEl.appendChild(liEl);
					
						const removeEl = liEl.querySelector(".remove");
						removeEl.addEventListener("click", function() {
							if (!confirm('정말 선택해제 하시겠습니까?')) {
								return;
							}
							
							liEl.parentElement.removeChild(liEl);
						});          
					});
					
				}
				
			}
		});
	
	
	
		
		xhr.send(null);
	}
	
};


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
