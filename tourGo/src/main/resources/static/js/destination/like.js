window.addEventListener("DOMContentLoaded", function(){
	
	const uidEl = document.getElementById("uid");
	if(uidEl) {
		document.getElementsByClassName("toggleLiked")[0].className = "fa-solid fa-heart toggleLiked on";
	}
	
	const toggleLikedEls = document.getElementsByClassName("toggleLiked");
	for(const el of toggleLikedEls){
		el.addEventListener("click",function(){
			try{
	
				const classList = this.classList;
				if (classList.contains("login_required")) {
					throw new Error("로그인 후 이용가능합니다.");	
										
				}
				
				const destinationviewEl = document.getElementById("destinationNo");
				if(!destinationviewEl) {
					throw new Error("잘못된 접근입니다.");
				}
				
				const destinationNo = destinationviewEl.value;	
				const uidEl = document.getElementById("uid");
				const uid = uidEl != null ? uidEl.value : "";
				const liked = classList.contains("on") ? false : true;
				const url = `/destinationlike?destinationNo=${destinationNo}&uid=${uid} `;
				
				console.log(url);
			
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
			
	/*
				if(el.className=="fa-regular fa-heart"){
					el.className = "fa-solid fa-heart";

					
				}
				else{
					el.className = "fa-regular fa-heart";
				}*/
				} catch(err) {
					alert(err);
				}
				
});
}

});