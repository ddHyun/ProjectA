
function modalOpen(id, name, adminType) {
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

function modalClose() {
	// console.log("모달 창 내부 제거");
	// $("#modalTable > form").remove();
	const modalBody = document.getElementById("modalBody");
	modalBody.innerHTML = "";
};

function changeAdmin() {
	const id = document.getElementById("userNo").value;
	const xhr = new XMLHttpRequest();
		xhr.open('Get', '/admin/user/userModalView/'+id);
		xhr.responseType='json';
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
		xhr.send();
		xhr.onload = () => {
			const data = xhr.response;
			console.log(data);
			if(data.success == true) {
				alert(data.message);
				location.reload();
			} else {
				alert("실패했습니다. 다시 해주세요.");
			}
		}
};