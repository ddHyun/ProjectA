window.addEventListener("DOMContentLoaded", function(){

	/**정렬기준 선택 이벤트 처리 S*/
	const orderEl = document.getElementById("order");
	orderEl.addEventListener("change", function(){
		const order = orderEl.value;
		const search = document.getElementById("search").value;
		const keywordEl = document.getElementById("keyword");

		if(keywordEl){
			const keyword = keywordEl.value;
			if(keyword && search==""){
				location.href=`review_main?keyword=${keyword}&order=${order}`;
			}
			if(keyword&&search){
				location.href=`review_main?keyword=${search}&order=${order}`;
			}			
		}else{
			location.href=`review_main?keyword=${search}&order=${order}`;
		}
	});
	/**정렬기준 선택 이벤트 처리 E*/
});