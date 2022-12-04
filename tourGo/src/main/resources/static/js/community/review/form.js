
/** 파일 업로드 콜백 처리 S */
// 파일업로드가 성공하면 업로드된 정보를 콜백 함수의 매개변수로 넘겨주는데 이 값을 가지고 에디터에 넣어준다.
function fileUploadCallback(files) {
	if (files.length == 0) {
		return;
	}
	
	for (const file of files) {
		const id = file.id;
		const url = `../uploads/${id % 10}/${id}`;
		const img = `<img src='${url}'>`;
		CKEDITOR.instances.reviewContent.insertHtml(img);
	}	
}
/** 파일 업로드 콜백 처리 E */


window.addEventListener("DOMContentLoaded", function() {
	/** 에디터 로드 S */
	CKEDITOR.replace("reviewContent");
	CKEDITOR.config.height=350;	
	/** 에디터 로드 E */
	
	console.log("form.js 들어옴");
	
	/**취소 선택 이벤트 처리 S */
	const cancelEl = document.getElementById("cancel");
	if(cancelEl){
		cancelEl.addEventListener("click", function(){
			if(!confirm("작성중이던 글은 저장되지 않습니다. 취소하시겠습니까?")){
				return;
			}
			location.href="review_main";
		});
	}
	/**취소 선택 이벤트 처리 E */
	
	/** 파일 선택 이벤트 처리 S */
	const filesEl = document.getElementById("files");
	if (filesEl) {
		filesEl.addEventListener("change", function(e) {
			const gidEl = document.getElementById("gid");
			if (!gidEl) { // 그룹 ID는 필수 이므로 없는 경우는 파일 업로드 차단
				return;
			}
			/**파일에 gid 부여 S */
			const gid = gidEl.value;
			filesEl.dataset.gid=gid;
			/**파일에 gid 부여 E */
			
			const files = e.target.files;			
			
			fileUpload.process(gid, files, true);
			this.value = "";
		});		
	}		
	/** 파일 선택 이벤트 처리 E */
	
	/** 양식 submit 시 유효성 검사 S */
	regForm.addEventListener("submit", function(e) {
		try {
			if (regForm.region.value.trim() == "") {
				throw new Error("여행지를 선택하세요.");
			} 
			
			if (regForm.period.value.trim() == "") {
				throw new Error("기간을 선택하세요.");	
			}				
			
		} catch (err) {
			alert(err.message);
			e.preventDefault();
		}
	});
	/** 양식 submit 시 유효성 검사 E */	
	
});