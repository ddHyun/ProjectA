/**
* 범용 양식 Validator
 */
const formValidator = {
	target : null,
	handleEvent(e) {
		try {
			const requiredEls = document.getElementsByClassName("required");
			const errorEls = document.getElementsByClassName("err");
			
			// 에러 호출 메시지 초기화
			for(const err of errorEls) {
				err.innerText = '';
			}
			
			// 빈 값 체크
			for (const el of requiredEls) {
				const type = el.type;
	
				const title = el.title;
				this.target = el; 
				switch (type) {
					case "checkbox": // 체크박스 
						if (!el.checked) {
							throw new Error(title + "은(는) 필수 체크항목 입니다.");
						}
						break;
					case "radio" : // 라디오박스는 여러개 중에서 한개 선택이니깐 분리 
						const elName = el.name;
						const els = document.querySelectorAll(`input[name='${elName}']:checked`);
						if (els.length == 0) { // 체크된 항목이 하나도 없다면
							throw new Error(title + "을(를) 선택하세요.");
						}
						break;
					default :  // 나머지는 값의 유무로 체크 처리
						const v = el.value;
						if (!v || (typeof v == 'string' && v.trim() == "")) {
							const name = el.name;
							const inputType = el.nodeName == 'SELECT' ? "선택":"입력";
							const msg = `${title}은(는) 필수 ${inputType}항목입니다.`;
							document.getElementById("err_"+name).innerText = msg;
							throw new Error(msg);
						}
				}
			}
			
			let object = {};
			console.log(signForm);
			signForm.forEach((value, key) => object[key] = value);
			console.log("test : "+object);
			// const json = JSON.stringify(object);
			// console.log(json);
			
			e.preventDefault();
			
			// const xhr = new XMLHttpRequest();

			// xhr.open('POST', '', true);
			// xhr.responseType='json';
			// xhr.setRequestHeader('Content-Type', 'application/json');
			// xhr.send(JSON.stringify(reqJson));
			
		} catch (err) {
			e.preventDefault();
			// alert(err.message);
			if (this.target) this.target.focus();
		}
	}
};

// 회원가입 submit 버튼
window.addEventListener("DOMContentLoaded", function() {
	signForm.addEventListener("submit", formValidator);
});
