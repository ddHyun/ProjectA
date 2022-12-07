/**댓글 */

window.addEventListener("DOMContentLoaded", function(){	
		
	/**댓글 선택 이벤트 처리  S*/
	const replyBtnEl = document.getElementById("replyBtn");
	if(replyBtnEl){
		replyBtnEl.addEventListener("submit", function(){
			console.log("누름");
		});
	}
	/**댓글 선택 이벤트 처리  E*/
});