var tp = tp || {};

tp.ajaxLoader = function(url, isJson) {
	return new Promise(function(resolve, reject) {
		const xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		xhr.addEventListener("readystatechange", function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				let data = xhr.responseText;
				if (isJson) {
					data = JSON.parse(data);
				}
				resolve(data);	
			}
		});
		xhr.addEventListener("error", function() {
			reject(xhr.responseText);
		});
		xhr.send(null);
	});
};