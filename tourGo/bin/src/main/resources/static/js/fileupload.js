/**
* 파일 업로드 처리
*
*/
const fileUpload = {
	/**
	* 파일 업로드 처리
	*
	* @params {Mixed} gid : 그룹아이디
	* @params {FileList} files : 업로드할 파일 목록
	* @params {boolean} isImage : true인 경우 이미지만 업로드되도록 통제
	*/
	process(gid, files, isImage) {
		try {
			/** 유효성 검사 S */
			if (!gid) {
				throw new Error("잘못된 접근입니다.");
			}
			
			if (!files || files.length == 0) {
				throw new Error("업로드할 파일을 선택하세요.");
			} 
			
			if (isImage) { // 이미지인 경우는 이미지가 아닌 파일이 섞여 있는지 체크
				for (const file of files) {
					if (file.type.indexOf("image") == -1) {
						throw new Error("이미지 형식만 업로드하세요.");
					}
				}
			}
			
			/** 유효성 검사 E */
			
			/** 파일 업로드 양식 생성 S */
			const formData = new FormData();
			formData.append("gid", gid);
			for (const file of files) {
				formData.append("file", file);
			}
			/** 파일 업로드 양식 생성 E */
			
			/** 전송 처리 S */
			const xhr = new XMLHttpRequest(); // 0 ~ 4
			xhr.open("POST", "../../file/upload"); 
			xhr.addEventListener("readystatechange", function() {
				if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) { // 업로드 후 데이터 
					const result = JSON.parse(xhr.responseText);	
					if (result.success) {
						const fileInfos = result.data;
						if (typeof fileUploadCallback == 'function') {
							fileUploadCallback(fileInfos);
						}
 					} else {
						alert(result.message);
					}
				}
			});
			xhr.send(formData);
			/** 전송 처리 E */
			
		} catch (err) {
			alert(err.message);
		}
	}	
};