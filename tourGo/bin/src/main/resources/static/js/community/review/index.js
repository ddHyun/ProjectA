window.addEventListener("DOMContentLoaded", function(){

	/**정렬기준 선택 이벤트 처리 S*/
	const orderEl = document.getElementById("order");
	
	orderEl.addEventListener("change", function() {
		frmSearch.submit();
	});
	/**정렬기준 선택 이벤트 처리 E*/
});