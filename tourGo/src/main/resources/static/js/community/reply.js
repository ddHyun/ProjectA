
window.addEventListener("DOMContentLoaded", function(){	
	
	const idEl = document.getElementById("id");
	if(!idEl){
		const registerReply = document.getElementById("registerReply");
		registerReply.disabled = true;
	}	
	
	/**댓글 삭제 이벤트 처리  S*/
	const deleteReplyEl = document.getElementById("deleteReply");
	if(deleteReplyEl){
		const replyNo = document.getElementById("replyNo").value;
		deleteReplyEl.addEventListener("click", function() {	
 			if(!confirm("삭제하시겠습니까?")){
			return;
		}
		const xhr = new XMLHttpRequest();
		const url = "/reply/remove?replyNo="+replyNo;
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
		});
	} 
	/**댓글 삭제 이벤트 처리  E*/
	
	/**댓글달기 처리 S */
	const reReplyEl = document.getElementById("reReply");
	if(reReplyEl){
		reReplyEl.addEventListener("click", function(){
			
		});
	/**댓글달기 처리 E */
	}
});