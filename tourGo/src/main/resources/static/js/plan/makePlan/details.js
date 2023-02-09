let map;
const tourGo = {
	//관광api 호출 함수
	search() {
		const keyword = document.getElementById(`keyword`).value;
		if (!keyword) {
			alert("키워드를 입력해주세요");
			return;
		}

		let url = `/tourList?keyword=${keyword}`;

		let xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		//키워드를 비동기적으로 컨트롤러에 전송
		xhr.addEventListener("readystatechange", function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				try{
				const items = JSON.parse(xhr.responseText);
	
				const result = items.data;
				const parentEl = document.querySelector(".api_list");
				const domParser = new DOMParser();
				parentEl.innerHTML = "";

				createItem(result, parentEl);
				}catch(err){
					console.log(err);
					alert("타임 오버!! 다시 검색해주세요.");
					
				}

			}//if끝


		});//xhr끝
	
		xhr.send();






	}//search()끝
}//tourGo끝

function selectedItem(){
	const ex = document.getElementById("select").value;

	
	/*const moveLatLon = new kakao.maps.LatLng(ypos, xpos);
				map.panTo(moveLatLon);
				
				const marker = new kakao.maps.Marker({
					map: map,
					position: moveLatLon
				});
				marker.setMap(map);
				// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
				var iwContent = `<div style="padding:5px;">${infoTitle}</div>
				<img src="${infoImg}" onerror="this.onerror=null; this.src='../../../../images/noimg.jpg';"></img>`
								, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
   				 iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    content : iwContent,
    removable : iwRemoveable
});

// 마커에 클릭이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'click', function() {
      // 마커 위에 인포윈도우를 표시합니다
      infowindow.open(map, marker);  
});*/
}//selectedItem함수종료


//관광지 삭제함수
function deleteDetails(){
	
var detailsNo = document.getElementsByName("detailsNo");
	detailsNo.forEach(i=> i.addEventListener("click",function(){
		if(confirm('정말 이 관광지를 삭제하시겠습니까?')){
		
		let xhr = new XMLHttpRequest();
		var details = i.value;
		
		xhr.open("GET", `/delteDetails?detailsNo=${details}`);
		//클릭한 관광지에 db PK를 비동기적으로 전송함(람다식)
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				$("#selected_items").replaceWith(xhr.responseText);
			}
		
		};
		xhr.onerror = function(err) {
				alert("다시 시도해주시기 바랍니다.");
					location.reload();
			};
	
	xhr.send();
	}//confirm끝
	})//forEach끝
	
)}//function끝

//관광공사 api호출후 데이터 뿌려주는 함수
function createItem(result, parentEl) {
	if (!result || !parentEl) {
		return;
	}

	const domParser = new DOMParser();
	for (const item of result) {
	
		const label = document.createElement("label");
		label.dataset.xpos = Number(item.mapx);
		label.dataset.ypos = Number(item.mapy);
		label.dataset.title=item.title;
		label.dataset.firstimage = item.firstimage;
		
 
		
		let html = '<div class="test" id="tourItem" >';


		html += `<img src="${item.firstimage}" onerror="this.onerror=null; this.src='../../../../images/noimg.jpg';"></img>`;
		html += `
								<div>${item.title}</div>
								<div> 주소 : ${item.address}</div>`;
		if(!item.tel){
			item.tel="전화번호가 존재하지 않습니다";
		}
		html+=						`<div>Tel : ${item.tel}</div>`;						
		html+=						`<button type="button" id="clickItem">선택</button>
								</div>`;

		const dom = domParser.parseFromString(html, "text/html");

		const clickItem = dom.getElementById(`clickItem`);
		clickItem.dataset.xpos = Number(item.mapx);
		clickItem.dataset.ypos = Number(item.mapy);
		clickItem.dataset.title = item.title;
		clickItem.dataset.address = item.address;
		clickItem.dataset.firstimage = item.firstimage;
		clickItem.dataset.tel = item.tel;
		const tourItem = dom.getElementById(`tourItem`);


		label.appendChild(tourItem);

		parentEl.appendChild(label);
		parentEl.appendChild(clickItem);

		label.addEventListener("click", function() {
			const xpos = this.dataset.xpos;
			const ypos = this.dataset.ypos;
			const infoTitle = this.dataset.title;
			const infoImg = this.dataset.firstimage;
			if (xpos == 0 && ypos == 0) {
				alert("이 관광지는 좌표가 존재하지 않습니다");
				return;
			};

			if (kakao && map) {
				const moveLatLon = new kakao.maps.LatLng(ypos, xpos);
				map.panTo(moveLatLon);
				
				const marker = new kakao.maps.Marker({
					map: map,
					position: moveLatLon
				});
				marker.setMap(map);
				// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
				var iwContent = `<div style="padding:5px;">${infoTitle}</div>
				<img class="imgview" src="${infoImg}" onerror="this.onerror=null; this.src='../../../../images/noimg.jpg';"></img>`
								, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
   				 iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    content : iwContent,
    removable : iwRemoveable
});

// 마커에 클릭이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'click', function() {
      // 마커 위에 인포윈도우를 표시합니다
      infowindow.open(map, marker);  
});

			}
			/*moveMap(xpos,ypos);
			console.log(moveMap);
			console.log(xpos,ypos);*/
		});//touritem click이벤트 종료



		clickItem.addEventListener("click", function() {
			var mapx = this.dataset.xpos;
			var mapy = this.dataset.ypos;
			var title = this.dataset.title;
			var address = this.dataset.address;
			var firstimage = this.dataset.firstimage;
			var tel = this.dataset.tel;
		
			var day = $("input[name='day']:checked").val();
			var plannerNo = document.getElementById(`plannerNo`).value;
		
		
			var formdata = new FormData();

			formdata.append("plannerNo", plannerNo);
			
			formdata.append("title", title);
			formdata.append("address", address);
			formdata.append("mapx", mapx);
			formdata.append("mapy", mapy);
			formdata.append("firstimage", firstimage);
			formdata.append("day", day);
			formdata.append("tel",tel);
			let url = `/saveDetails`;
			let xhr = new XMLHttpRequest();
			xhr.open("POST", url, true);
			xhr.send(formdata);
			xhr.onreadystatechange = function() {
				if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
					$("#selected_items").replaceWith(xhr.responseText);
				}
			};
			xhr.onerror = function(err) {
			alert("다시 시도해주시기 바랍니다.");
				window.location.reload();
			};
			

		});//click이벤트종료


	}


}

const planner = {
	/**날짜를 변경할때마다 변경된사항 db에 업데이트 */
	loadSelection() {
		const day = $("input[name='day']:checked").val();
		var plannerNo = document.getElementById(`plannerNo`).value;
		
	
		var detailsNo = document.getElementsByName("detailsNo");
		var arrayNo = [];
		detailsNo.forEach(function(i) {
			arrayNo.push(i.value);

		
		});

		var detailsStime = document.getElementsByName("stime");
		var arrayStime = [];

		detailsStime.forEach(function(i) {
			arrayStime.push(i.value);
			
		});

		var detailsEtime = document.getElementsByName("etime");
		var arrayEtime = [];

		detailsEtime.forEach(function(i) {
			arrayEtime.push(i.value);
			
		});
	

		// const formData = new FormData(document.frm);
		var formData = new FormData();

		formData.append("day", day);
		formData.append("plannerNo", plannerNo);
		formData.append("detailsNo", arrayNo);
		formData.append("stime", arrayStime);
		formData.append("etime", arrayEtime); 
		console.log(arrayStime);
		console.log(arrayEtime);
		console.log(formData);
		//formData.append("detailsItem",detailsItem);
		let xhr = new XMLHttpRequest();

		xhr.open("POST", '/select');
		xhr.send(formData);
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				$("#selected_items").replaceWith(xhr.responseText);
			}
		
		
		};

		xhr.onerror = function(err) {
			console.error(err);
			location.reload();
		};

		

	}//함수끝
};




window.addEventListener("DOMContentLoaded", function() {
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
map = new kakao.maps.Map(mapContainer, mapOption); 
});//카카오 지도끝

function moveMap(xpos,ypos){
	var markerPosition  = new kakao.maps.LatLng(xpos,ypos); 

// 이미지 지도에 표시할 마커입니다
// 이미지 지도에 표시할 마커는 Object 형태입니다
var marker = {
    position: markerPosition
};

var mapContainer  = document.getElementById('map'), // 이미지 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(xpos,ypos), // 이미지 지도의 중심좌표
        level: 5, // 이미지 지도의 확대 레벨
        marker: marker // 이미지 지도에 표시할 마커 
    };    

// 이미지 지도를 생성합니다
	 map = new kakao.maps.StaticMap(mapContainer, mapOption);

}

/*
window.addEventListener("DOMContentLoaded", function() {
	// 이미지 지도에서 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

// 이미지 지도에 표시할 마커입니다
// 이미지 지도에 표시할 마커는 Object 형태입니다
var marker = {
    position: markerPosition
};

var mapContainer  = document.getElementById('map'), // 이미지 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 이미지 지도의 중심좌표
        level: 3, // 이미지 지도의 확대 레벨
        marker: marker // 이미지 지도에 표시할 마커 
    };    

// 이미지 지도를 생성합니다
	 map = new kakao.maps.StaticMap(mapContainer, mapOption);


});//카카오 지도끝*/












