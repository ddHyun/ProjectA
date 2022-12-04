
window.addEventListener("DOMContentLoaded", function(){
	
	/**게시판 선택 활성화 표시 S */
	const boardEl = document.getElementById("board");
	if (boardEl) {
		const board = boardEl.value;
		document.getElementById(board).className += ' active';
	}
	/**게시판 선택 활성화 표시 E */

});