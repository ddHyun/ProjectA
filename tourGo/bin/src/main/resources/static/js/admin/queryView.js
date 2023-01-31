
function createReplyForm() {
	const replyForm = document.getElementById("replyForm");
	const queryLabel = document.getElementById("queryLabel");
	const createReply = document.getElementById("createReply");
	if(replyForm) {
		replyForm.classList.remove("d-none");
		queryLabel.innerText = "답변 등록";
		createReply.setAttribute("onclick", "submitReply()");
	}
}

function updateReplyForm() {
	const queryReplyDiv = document.getElementById("queryReplyDiv");
	const replyForm = document.getElementById("replyForm");
	const queryLabel = document.getElementById("queryLabel");
	const updateReply = document.getElementById("updateReply");
	if(replyForm) {
		replyForm.classList.remove("d-none");
		queryReplyDiv.classList.add("d-none");
		queryLabel.innerText = "답변 수정";
		updateReply.setAttribute("onclick", "submitReply()");
	}
}

function submitReply() {
	replyForm.submit();
}