/** 파일 업로드 콜백 처리 S */
// 파일업로드가 성공하면 업로드된 정보를 콜백 함수의 매개변수로 넘겨주는데 이 값을 가지고 에디터에 넣어준다.
function fileUploadCallback(files) {
	if (!files || files.length == 0) {
		return;
	}	
}
/** 파일 업로드 콜백 처리 E */

window.addEventListener("DOMContentLoaded", function() {
	/** 에디터 로드 S */
	CKEDITOR.replace("noticeContent");
	CKEDITOR.config.height=500;
	/** 에디터 로드 E */
	
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
			
			fileUpload.process(gid, files, false);
			this.value = "";
		});		
	}		
	/** 파일 선택 이벤트 처리 E */
});