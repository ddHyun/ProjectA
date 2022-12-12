window.addEventListener("DOMContentLoaded", function(){	
		
	/**댓글 선택 이벤트 처리  S*/
	replyForm.addEventListener("submit", function(e){
	
	if(replyForm.id.value==""){
		e.preventDefault();
		alert("로그인 후 이용 가능합니다.");
		location.href="user/login";
	}
	});
	/**댓글 선택 이벤트 처리  E*/
});