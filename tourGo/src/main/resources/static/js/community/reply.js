
window.addEventListener("DOMContentLoaded", function(){	
	
	const idEl = document.getElementById("id");
	if(!idEl){
		const registerReply = document.getElementById("registerReply");
		registerReply.disabled = true;
	}	
	
	/**댓글 삭제 이벤트 처리  S*/
	/*
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
	*/
	/**댓글 삭제 이벤트 처리  E*/
	
});

	/**댓글쓰기 양식 버튼이벤트 S */
	function writeReply(replyNo){

		const textareaEl = document.createElement("textarea");
		textareaEl.setAttribute("name", "replyContent");
		textareaEl.setAttribute("th:field", "*{replyContent}");
		
		const button0El = document.createElement("button");
		button0El.setAttribute("type", "button");
		button0El.innerText = "취소하기";
		
		/**취소하기 버튼 이벤트 S */
		button0El.addEventListener("click", function(){
			parent.removeChild(textareaEl);
			parent.removeChild(button0El);
			parent.removeChild(button1El);
		});
		/**취소하기 버튼 이벤트 E */
		
		const button1El = document.createElement("button");
		button1El.setAttribute("type", "submit");
		button1El.innerText = "댓글등록";
	
		const parent = document.getElementById("reRpl"+replyNo);

		parent.appendChild(textareaEl);
		parent.appendChild(button0El);
		parent.appendChild(button1El);
	}	
	/**댓글쓰기 양식 버튼이벤트 E */
	
	/**댓글삭제 버튼이벤트 S */
	function deleteReply(replyNo){
		if(!confirm("정말 삭제하시겠습니까?")){
			return;
		}
	}
	/**댓글삭제 버튼이벤트 E */
	
