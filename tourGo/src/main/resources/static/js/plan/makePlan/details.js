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
					
					let html =`<div draggable="true" class="test"> `;
			}
				
				
		
		}//if끝
		
	
	});//xhr끝
	
	
	
	
	
	
	
	}//search()끝
}//tourGo끝