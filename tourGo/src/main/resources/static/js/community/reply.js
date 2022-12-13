/**댓글 등록 S*/
function register(e){
		const idEl = document.getElementById("id");
	if(idEl){
		if(idEl.value==""){
			e.preventDefault();
			alert("로그인 후 이용 가능합니다.");
			location.href="user/login";
		}		
	}	
	const content = document.getElementById("content").value;
	if(content==""){
		e.preventDefault();
		alert("내용을 입력해 주세요.");
		return;
	}
	
	e.preventDefault();
	const formData = new FormData(replyForm);
	const xhr = new XMLHttpRequest();
	xhr.open("post",  "/reply/register");
	xhr.send(formData);
	xhr.addEventListener("readystatechange", function(){
		if(xhr.status==200&&xhr.readyState==XMLHttpRequest.DONE){
			const result = JSON.parse(xhr.responseText);
			if(result.success){
				location.reload();
				}
			}
		});
}
/**댓글 등록 E*/

/**댓글 삭제 S */
/*function remove(replyNo){
	if(!confirm("삭제하시겠습니까?")){
		return;
	}

	const xhr = new XMLHttpRequest();
	const url = '/reply/remove?replyNo=${replyNo}';
	xhr.open("GET", url);
	xhr.send();
	xhr.addEventListener("readystatechange", function(){
		if(xhr.status==200&&xhr.readyState==XMLHttpRequest.DONE){
			const result = JSON.parse(xhr.responseText);
			if(result.success){
				location.reload();
				}
			}
	}); 
}*/
/**댓글 삭제 E */

window.addEventListener("DOMContentLoaded", function(){	
		
	/**댓글 선택 이벤트 처리  S*/
	const replyForm = document.replyForm;	
	replyForm.addEventListener("submit", register)
	/**댓글 선택 이벤트 처리  E*/
	
	/**댓글 삭제 이벤트 처리  S*/
/*	const deleteReplyEl = document.getElementById("deleteReply");
	if(deleteReplyEl){
		const replyNo = document.getElementById("replyNo").value;
		deleteReplyEl.addEventListener("click", remove(replyNo))
	} */
	/**댓글 삭제 이벤트 처리  E*/
});