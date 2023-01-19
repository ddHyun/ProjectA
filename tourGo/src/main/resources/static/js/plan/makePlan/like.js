window.onload= function(){
	const ex = document.getElementsByClassName(`fa-regular fa-heart`);
	const str1 = 'fa-regular fa-heart';
	console.log(ex)

	for(const el of ex){
		el.addEventListener("click",function(){
	
				if(el.className=="fa-regular fa-heart"){
					el.className = "fa-solid fa-heart";

					
				}
				else{
					el.className = "fa-regular fa-heart";
				}
				
			
			
			
		});//이벤트종료
	}
}