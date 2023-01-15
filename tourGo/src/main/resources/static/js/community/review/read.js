const btnFn = {
	/**게시글 수정하기 S */
	modify(reviewNo){
		try{
			if(!reviewNo){
				throw new Error("수정할 글이 존재하지 않습니다.");
			}
			location.href = `../review_register?reviewNo=${reviewNo}`;
		}catch(err){
			alert(err.message);
		}

	},
	/**게시글 수정하기 E */
	
	/**게시글 삭제하기 S */
	delete(reviewNo){
		
		if(!confirm("삭제하시겠습니까?")){
			return;
		}		
		
		const xhr = new XMLHttpRequest();
		let url = "../review_delete?reviewNo="+reviewNo;
		xhr.open("GET", url);
		xhr.addEventListener("readystatechange", function(){
			if(xhr.status==200&&xhr.readyState==XMLHttpRequest.DONE){
				const result = JSON.parse(xhr.responseText);
				if(result.success){
					alert("게시글을 삭제했습니다.");
					location.href="../review_main";
				}
			}
		});
		xhr.send();		
	}
	/**게시글 삭제하기 E */
}

window.addEventListener("DOMContentLoaded", function(){
	
	/**클릭한 좋아요 상태 처리 S */
	const uidEl = document.getElementById("uid");
	if(uidEl){
		document.getElementsByClassName("toggleLiked")[0].className = "fa-solid fa-heart toggleLiked on";
	}
	/**클릭한 좋아요 상태 처리 E */
	
	/** 좋아요 토클 처리 S */
	const toggleLikedEls = document.getElementsByClassName("toggleLiked");
	for (const el of toggleLikedEls) {
		el.addEventListener("click", function() {
			try {
				const classList = this.classList;
				if (classList.contains("login_required")) {
					throw new Error("로그인 후 이용가능합니다.");								
				}
			
				const reviewNoEl = document.getElementById("reviewNo");
				if (!reviewNoEl) {
					throw new Error("잘못된 접근입니다.");
				}
				const reviewNo = reviewNoEl.value;	
				const uidEl = document.getElementById("uid");
				const uid = uidEl != null ? uidEl.value : "";
				const liked = classList.contains("on") ? false : true;
				const url = `/liked?reviewNo=${reviewNo}&uid=${uid}`;
				
				const xhr = new XMLHttpRequest();
				xhr.open("GET", url);
				xhr.onreadystatechange = function() {
					if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
						const result = JSON.parse(xhr.responseText);
						if (!result.success) {
							alert(result.message);
							return;
						}
						
						if (liked) {
							el.className = "fa-solid fa-heart toggleLiked";
							classList.add("on");
						} else {
							el.className = "fa-regular fa-heart toggleLiked";
							classList.remove("on");
						}
						document.getElementById("totalLikes").innerText = result.data;
					}
				};
				xhr.send(null);
				
			} catch (err) {
				alert(err.message);
			}
		});
	}
	/** 좋아요 토클 처리 E */
	
	
	const reviewNoEl = document.getElementById("reviewNo");
	if(reviewNoEl){
		const reviewNo = reviewNoEl.value;

	
		/**수정 선택 이벤트 처리 S */
		const modifyBtnEl = document.getElementById("modifyBtn");
		if(modifyBtnEl){
			modifyBtnEl.addEventListener("click", function(){
				btnFn.modify(reviewNo);
			});
		}
		/**수정 선택 이벤트 처리 E */
		
		/**삭제 선택 이벤트 처리 S */
		const deleteBtnEl = document.getElementById("deleteBtn");
		if(deleteBtnEl){
			deleteBtnEl.addEventListener("click", function(){
				btnFn.delete(reviewNo);		
			})
		}
		/**삭제 선택 이벤트 처리 E */	
	} 
});