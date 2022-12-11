/*review main*/

window.addEventListener("DOMContentLoaded", function(){
	
		/**select 선택 이벤트 처리 S*/
		const orderEl = document.getElementById("order");
		
		if(orderEl){
			orderEl.addEventListener("change", function(){
				const order = orderEl.value;
				const keywordEl = document.getElementById("keyword");
				if(keywordEl){
					const keyword = keywordEl.value;
					const xhr = new XMLHttpRequest();
					const url = `/community/by_order?keyword=${keyword}&order=${order}`;
					xhr.open("get", url);
					xhr.addEventListener("readystatechange", function(){
						if(xhr.status==200&&xhr.readyState==XMLHttpRequest.DONE){
							const result = JSON.parse(xhr.responseText);
							if(result.success){
								console.log(result.data);
							}
						}
					});
					xhr.send();
				}else{
					const xhr = new XMLHttpRequest();
					const url = `/community/by_order?order=${order}`;
					xhr.open("get", url);
					xhr.addEventListener("readystatechange", function(){
						if(xhr.status==200&&xhr.readyState==XMLHttpRequest.DONE){
							const result = JSON.parse(xhr.responseText);
							if(result.success){
								console.log(result.data);
							}
						}
					});
					xhr.send();
				}
			});
		}
		/**select 선택 이벤트 처리 E*/
});