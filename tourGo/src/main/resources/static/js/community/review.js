//여행후기 js
const fileUpload = {
	//register S
	register(files){
		try{
			if(files.length >=1){
				const formData = new FormData();
				
				for(const file of files){
					formData.append("file", file);
				}
				
				let url = "../community/review_register";
				const xhr = new XMLHttpRequest();
				xhr.open("POST", url);
				xhr.addEventListener("readystatechange", function(){
					if(xhr.status==200&&xhr.readyState==XMLHttpRequest.DONE){
						const result = JSON.parse(xhr.responseText);
						
					}
				});
			}			
		}catch(error){
			alert(error.message);
		}
	}
	//register E
};

window.addEventListener("DOMContentLoaded", function(){
	console.log('reviewjs시작');
	
	const filesEl = document.getElementById("files");
	if(filesEl){
		filesEl.addEventListener("change", function(e){
			const files = e.target.files;
			fileUpload.register(files);
		});
	}
});
