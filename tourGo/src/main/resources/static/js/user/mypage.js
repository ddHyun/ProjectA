/**
* 범용 양식 Validator
 */
const formValidator = {
	/* 임시 Ajax  */
	 async handleEvent(e) {		
		const requiredEls = document.getElementsByClassName('required');
		const intro = document.getElementById('intro');
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
		console.log(data);
		
		try {
			const result = await this.getAjaxData( '/user/api/updateMypage', data);
			alert("회원 정보가 수정되었습니다.");
			location.href = "/index";
		} catch (err) {
			console.error(err);
			
			for (const el of requiredEls) {
				let name = el.name;
				if(err.hasOwnProperty(el.name)) {
					if(!el.name.includes('check')) {
						document.getElementById("err_"+name).innerText = err[name];
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
