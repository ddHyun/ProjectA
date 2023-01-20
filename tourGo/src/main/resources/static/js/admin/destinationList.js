function callBack(areaCode) {
	const url = "/admin/destination/destinationCallBack/" + areaCode;
	const xhr = new XMLHttpRequest();
	
	xhr.open('get', url);
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
}