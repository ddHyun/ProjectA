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

			console.log(el);
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
		// console.log(data);
		
		try {
			const result = await this.getAjaxData( '/user/api/signUp', data);
			console.log(result); // 회원 가입 후 확인하고 처리할 것이 더 있다면 여기에서 확인
			alert("회원가입이 완료되었습니다.");
			// 아마 ajax로 다 처리되는 듯 하지만 우선 여기서 submit 하고 전화 드릴꼐요~!
			signform.submit();
		} catch (err) {
			// console.error(err);
			
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
				xhr.open('POST', url);
				xhr.responseType='json';
				xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
				xhr.send(JSON.stringify(data));
				xhr.onload = () => {
					const data = xhr.response;
					if(data.status === 400) {
						reject(data.data);
					} else {
						resolve(data.data);
					}
				}
			});
	}
};

// 회원가입 submit 버튼
window.addEventListener("DOMContentLoaded", function() {
	//signForm.addEventListener("submit", formValidator);
	
	const checkAll = document.getElementById("checkAll");
	const checkBoxes = document.querySelectorAll(".input_check input")
	const agreements = {
		checkbox1 : false,
		checkbox2 : false
	}
	
	checkBoxes.forEach((item) => item.addEventListener('input', toggleCheckbox));

	// 각 체크박스 클릭 이벤트 함수
	function toggleCheckbox(e) {
		const { checked, id } = e.target;
		agreements[id] = checked;
		this.parentNode.classList.toggle('active');
		// console.log(checkBoxes);
		checkAllStatus();
	}
	
	// 체크박스 이벤트로 인한 전체 동의 체크 여부
	function checkAllStatus() {
		const { check1, check2 } = agreements;
		if(check1 && check2) {
			checkAll.checked = true;
		} else {
			checkAll.checked = false;
		}
	}
	
	// 이용약관 전체 동의 체크박스 이벤트
	checkAll.addEventListener('click', (e) => {
		const { checked } = e.target;
		if(checked) {
			checkBoxes.forEach((item) => {
				item.checked = true;
				agreements[item.id] = true;
			});
		} else {
			checkBoxes.forEach((item) => {
				item.checked = false;
				agreements[item.id] = false;
			});
		}
	});
	
	const submitBtn = document.getElementById("btn_signUp");
	if (submitBtn) {
		submitBtn.addEventListener("click", formValidator);
	}
});
