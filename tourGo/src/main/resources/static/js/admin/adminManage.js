/* userManage(회원 관리) S */
window.addEventListener("DOMContentLoaded", function() {
	const selectBox = document.getElementById("selectBox");
	const searchType = document.getElementById("searchType");
	
	if(selectBox) {
		if(searchType.value.length == 0 || searchType.value == 0) {
			searchType.value = selectBox.options[selectBox.selectedIndex].value;
		}
		
		selectBox.addEventListener("change", () => {
			searchType.value = selectBox.options[selectBox.selectedIndex].value;
		});
	}
	
});
/* userManage(회원 관리) S */

/* userManageView(회원 상세보기) S */
function changeModal1() {
	userForm.submit();
}
/* userManageView(회원 상세보기) E */

/* userActiveManage (사용자 활동 변경) S */
function changeModal2(id, name, deleteYn) {
	let item = '';
	const modalBody = document.getElementById("modalBody");
	const btn_change = document.getElementById('btn_change');
	
	if(deleteYn === 'N') {
		item += '<form method="post" id="changeForm" name="changeForm">' + 
				'<h5 class="text-danger">주의! 활동 변경은 신중히 해주세요.</h5><br/>'
				+ '<p><span class="font-weight-bold">['+name+']</span> 님을 다시 활동 상태로 바꾸시겠습니까?</p>'
				+ '<input type="hidden" name="userNo" id="userNo" value='+ id +'></form>';
		btn_change.disabled = false;
	} else {
		item += '<h5 class="text-danger">삭제된 사용자는 활성화 할 수 없습니다.</h5><br/>';
		btn_change.disabled = true;
	}
	modalBody.innerHTML = item;
}

function changeActive() {
	const id = document.getElementById("userNo").value;
	const xhr = new XMLHttpRequest();
		xhr.open('Get', '/admin/user/activeTypeChange/'+id);
		xhr.responseType='json';
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
		xhr.send();
		xhr.onload = () => {
			const data = xhr.response;
			if(data.success == true) {
				alert(data.message);
				location.reload();
			} else {
				alert("실패했습니다. 다시 해주세요.");
			}
		}
};
/* userActiveManage (사용자 활동 변경) E */

/* adminTypeManage(관리자 등급 변경) S */
function changeModal3(id, name, adminType) {
	let item = '<form method="post" id="changeForm" name="changeForm">' + 
				'<h5 class="text-danger">주의! 등급 변경은 신중히 해주세요.</h5><br/>';
	if(adminType === 'USER') {
		item += '<p><span class="font-weight-bold">['+name+']</span> 님을 관리자 등급으로 올리시겠습니까?</p>'
				+ '<input type="hidden" name="userNo" id="userNo" value='+ id +'>';
	}
	else {
		item += '<p><span class="font-weight-bold">['+name+']</span> 님을 사용자 등급으로 내리시겠습니까?</p>'
				+ '<input type="hidden" name="userNo" id="userNo" value='+ id +'>';
	}
	
	item += '</form>'
	
	const modalBody = document.getElementById("modalBody");
	// console.log(item);
	modalBody.innerHTML = item;
};

function changeAdmin() {
	const id = document.getElementById("userNo").value;
	const xhr = new XMLHttpRequest();
		xhr.open('Get', '/admin/user/adminTypeChange/'+id);
		xhr.responseType='json';
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
		xhr.send();
		xhr.onload = () => {
			const data = xhr.response;
			if(data.success == true) {
				alert(data.message);
				location.reload();
			} else {
				alert("실패했습니다. 다시 해주세요.");
			}
		}
};
/* adminTypeManage(관리자 등급 변경) E */

function modalClose() {
	// console.log("모달 창 내부 제거");
	// $("#modalTable > form").remove();
	const modalBody = document.getElementById("modalBody");
	
	modalBody.innerHTML = "";
};