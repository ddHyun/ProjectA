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

/**좋아요 토글 처리 S */
function toggleLiked(){
	const user = document.getElementById("user");
	if(user.value==''){
		alert("로그인 후 이용 가능합니다");
		return;
	}
	
	const likedEl = document.getElementById("liked");
	let liked = false;
	if(likedEl.className=="liked"){
		likedEl.innerHTML = '<i class="fa-solid fa-heart" onclick="toggleLiked();"></i>';
		likedEl.className += " on";		
		liked = true;
	}else{
		likedEl.innerHTML = '<i class="fa-regular fa-heart" onclick="toggleLiked();"></i>';
		likedEl.className = "liked";		
	}

}	

/**좋아요 토글 처리 E */

window.addEventListener("DOMContentLoaded", function(){
	
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