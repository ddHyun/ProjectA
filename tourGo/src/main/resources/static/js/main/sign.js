/**
* 범용 양식 Validator
 */
const formValidator = {
	target : null,
	handleEvent(e) {
		try {
			const requiredEls = document.getElementsByClassName("required");
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
							const inputType = el.nodeName == 'SELECT' ? "선택":"입력";
							const msg = `${title}은(는) 필수 ${inputType}항목입니다.`;
							throw new Error(msg);
						}
				}
			}
		} catch (err) {
			e.preventDefault();
			alert(err.message);
			if (this.target) target.focus();
		}
	}
};

window.addEventListener("DOMContentLoaded", function() {
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
	
	frmSign.addEventListener("submit", formValidator);
});