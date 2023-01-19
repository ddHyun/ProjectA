
window.addEventListener("DOMContentLoaded", function(){	
	
	/**미로그인시 등록버튼 비활성화 처리 S */
	const idEl = document.getElementById("id");
	if(!idEl){
		const registerReply = document.getElementById("registerReply");
		registerReply.disabled = true;
	}
	/**미로그인시 등록버튼 비활성화 처리 E */

});

	/**댓글쓰기 양식 버튼이벤트 S */
	function writeReply(replyNo){	
		document.getElementById("dn"+replyNo).style.display="none";
		const textareaEl = document.createElement("textarea");
		textareaEl.setAttribute("class", "reRplArea");
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
			document.getElementById("dn"+replyNo).style.display="block";
		});
		/**취소하기 버튼 이벤트 E */
		
		const button1El = document.createElement("button");
		button1El.setAttribute("type", "submit");
		button1El.innerText = "답글등록";
	
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
		document.getElementById("ifrmProcess").src="/reply/remove?replyNo="+replyNo;
	}
	/**댓글삭제 버튼이벤트 E */
	
