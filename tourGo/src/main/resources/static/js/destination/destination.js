// radio 클릭 시 db 값 불러오기
window.addEventListener("DOMContentLoaded", function() {
	
	// const inputValue = document.querySelectorAll("input[name=destination]:checked");
    const destination = document.querySelectorAll("input[name=destination]");
   	
   	destination.forEach(radio => {
		radio.addEventListener("click", function() {
			value = radio.value;
			console.log(value);
			
			const url = '/api/travel/'+value;
		    const xhr = new XMLHttpRequest();
		    
		    xhr.open("GET", url);
		    xhr.responseType='json';
			xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
			xhr.send();
		    xhr.onload = () => {
				const data = xhr.response.data;
				console.log("데이터 : " + JSON.stringify(data));
			}
		});
	});	
});

// db 값 불러온거 뿌려주기
function appendHtml() {
	
}