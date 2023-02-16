// radio 클릭 시 db 값 불러오기
window.addEventListener("DOMContentLoaded", function() {

	// const inputValue = document.querySelectorAll("input[name=destination]:checked");
	const destination = document.querySelectorAll("input[name=destination]");

	destination.forEach(radio => {
		radio.addEventListener("click", function() {
		const keyword = radio.value;	
		
		const url = `/api/travel?keyword=${keyword}`;
		console.log(url);
		
		const xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				$("#allBox").replaceWith(xhr.responseText);
			}
		
		
		};

		xhr.onerror = function(err) {
			console.error(err);
			location.reload();
		};

		
		
		});
	});

});

// db 값 불러온거 뿌려주기
function appendHtml() {

}

// page 처리
