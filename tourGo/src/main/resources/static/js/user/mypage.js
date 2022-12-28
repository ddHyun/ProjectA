/**
* 범용 양식 Validator
 */
const formValidator = {
	/* 임시 Ajax  */
	 async handleEvent(e) {		
		const requiredEls = document.getElementsByClassName('required');
		const intro = document.getElementById('intro');
		const gid = document.getElementById('gid');
		const data = {};
		
		for(const el of requiredEls) {
			const type = el.type;

			// console.log(el);
			switch (type) {
				case "checkbox": // 체크박스 
					data[el.name] = el.checked;
					break;
				case "radio" : // 라디오박스는 여러개 중에서 한개 선택이니깐 분리 
					data[el.name] = el.checked.val();
					break;
				default :  // 나머지는 값의 유무로 체크 처리
					data[el.name] = el.value;
			}
		}
		data[intro.name] = intro.value;
		data[gid.name] = gid.value;
		// console.log(data);
		
		try {
			const result = await this.getAjaxData( '/user/api/updateMypage', data);
			alert("회원 정보가 수정되었습니다.");
			location.href = "/main_view";
		} catch (err) {
			// console.error(err);
			
			/* 에러메시지 초기화 */
			for(const el of requiredEls) {
				const name = el.name;
				const type = el.type;
				if(type != "hidden") {
					if(!document.getElementById("err_"+name).classList.contains("err_close")) {
						document.getElementById("err_"+name).innerText = "";
						document.getElementById("err_"+name).classList.add("err_close");
					}
				}
			}
			
			for (const el of requiredEls) {
				let name = el.name;
				if(err.hasOwnProperty(el.name)) {
					if(!el.name.includes('check')) {
						document.getElementById("err_"+name).innerText = err[name];
						document.getElementById("err_"+name).classList.remove("err_close");
					}
				}
			}
		}
	}, 
	 getAjaxData(url, data) {
			return new Promise((resolve, reject) => {
				const xhr = new XMLHttpRequest();
				xhr.open('PUT', url);
				xhr.responseType='json';
				xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
				xhr.send(JSON.stringify(data));
				xhr.onload = () => {
					const data = xhr.response;
					if(data.success === false) {
						reject(data.data);
					} else {
						resolve(data.data);
					}
				}
			});
	}
};

/** 파일 업로드 콜백 처리 S */
// 파일업로드가 성공하면 업로드된 정보를 콜백 함수의 매개변수로 넘겨주는데 이 값을 가지고 에디터에 넣어준다.
function fileUploadCallback(files) {
	if (files.length == 0) {
		return;
	}	
	
	let uploadURL = "/uploads";
	const pathname = location.pathname;
	if (pathname.split("/").length >= 4) { // /가 3개 이상 포함되어 있다면 context path가 있는 경우로 판단
		uploadURL = `../${uploadURL}`;
	} 
	
	const profile = document.getElementById("profile");
	
	for (const file of files) {
		const id = file.id;
		
		const url = `${uploadURL}/${id % 10}/${id}`;
		
		profile.src = url;
	}	
}
/** 파일 업로드 콜백 처리 E */

// 마이페이지 submit 버튼
window.addEventListener("DOMContentLoaded", function() {
	//signForm.addEventListener("submit", formValidator);
	
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
			
			console.log(files);
			
			fileUpload.process(gid, files, true);
			this.value = "";
		});		
	}		
	/** 파일 선택 이벤트 처리 E */
	
	const submitBtn = document.getElementById("btn_update");
	if (submitBtn) {
		submitBtn.addEventListener("click", formValidator);
	}
});
