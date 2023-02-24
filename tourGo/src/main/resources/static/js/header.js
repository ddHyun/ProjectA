window.addEventListener("DOMContentLoaded", function() {
	const header = document.querySelector(".nav_all");
	console.log(header);
	const headerHeight = header.getBoundingClientRect().height;
	
	console.log(headerHeight);
	
	window.addEventListener("scroll", () => {
		if (window.scrollY > headerHeight) {
			console.log(scrollY);
			header.setAttribute("style", "background: white;");
		} else {
			header.setAttribute("style", "background: transparent;");
		}
	});
	
});


