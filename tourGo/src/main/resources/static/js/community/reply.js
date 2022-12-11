/**댓글 처리 S*/
function registerPost(e){
	e.preventDefault();
	
	if(replyForm.user.value==""){
		alert("로그인 후 이용 가능합니다.");
		location.href="user/login";
	}
	if(replyForm.content.value==""){
		alert("내용을 입력해주세요");
		return;
	}
}
/**댓글 처리 E*/

window.addEventListener("DOMContentLoaded", function(){	
		
	/**댓글 선택 이벤트 처리  S*/
	replyForm.addEventListener("submit", registerPost);
	/**댓글 선택 이벤트 처리  E*/
});