//커뮤니티 각 게시핀에 접근시 상단바에 active클래스 추가하기
window.addEventListener("DOMContentLoaded", function(){
	//해당 탬플릿 script에서 보낸 파라미터는 선언없이 사용 가능
	document.getElementById(board).className += ' active';
});