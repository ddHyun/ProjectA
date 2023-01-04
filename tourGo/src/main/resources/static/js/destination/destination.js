function showDiv(event) {
            const radioId = event.target.id;
            

            
            let query = 'label[for="' + radioId + '"]'
            let text = document.querySelector(query).innerText;
			
			console.log(radioId);
			console.log(query);
			
            document.getElementById('allBox').innerText = text;

        }
        
        /*
                    const inputValue = document.getElementById('radioId').value;
            const url = 'travel?destination=${inputValue}';
            const xhr = new XMLHttpRequest();
            
            xhr.open("GET", url);
            xhr.addEventListener("readystatechange", function() {
				if(xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
					document.getElementById("allBox").innerHTML=xhr.response;
				};
			});
			
			xhr.send(null);
        */
        