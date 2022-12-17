
window.addEventListener("DOMContentLoaded", function(){		
	
	/**댓글 삭제 이벤트 처리  S*/
	const deleteReplyEl = document.getElementById("deleteReply");
	console.log(deleteReplyEl);
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
});