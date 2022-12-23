
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
	
	/**댓글쓰기 양식 버튼이벤트 S */
	const reReplyEl = document.getElementById("reReply");
	if(reReplyEl){
		reReplyEl.addEventListener("click", function(e){
			console.log(e.target);
			const divEl = document.createElement("div");
			divEl.className = "rpl";
			let text = "<form method='post' th:action='@{/reply/register}' name='replyForm' target='ifrmProcess' th:object='${replyRequest}'>";
			text += "<input th:if='${user}' type='hidden' name='id' id='id' th:value='${user}'>";
			text += "<input type='hidden' name='no' th:value='${no}'>";
			text += "<div>";
			text += "<textarea rows='2'>";
			text += "</textarea>";
			text += "<div th:each='err : ${#fields.errors('replyContent')}' th:text='${err}'>";
			text += "</div>";
			text += "<button type='submit' th:text='댓글등록'>";
			text += "</button>";
			text += "</div>";
			text += "</form>";
			divEl.innerHTML = text;

		
			const replyNo = document.getElementById("replyNo").value;	
			console.log(replyNo);
			document.getElementById("comment_"+replyNo).appendChild(divEl);
		});
	}
	/**댓글쓰기 양식 버튼이벤트 E */
});