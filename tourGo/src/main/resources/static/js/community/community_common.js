//커뮤니티 각 게시핀에 접근시 상단바에 active클래스 추가하기
window.addEventListener("DOMContentLoaded", function(){
	console.log('커뮤니티공통js시작');
	const boardEl = document.getElementById("board");
	if (boardEl) {
		const board = boardEl.value;
		document.getElementById(board).className += ' active';
	}
});