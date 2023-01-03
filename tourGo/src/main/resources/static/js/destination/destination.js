function showDiv(event) {
            let radioId = event.target.id;
            
            
            
            let query = 'label[for="' + radioId + '"]'
            let text = document.querySelector(query).innerText;
			
			console.log(radioId);
			console.log(query);
			
            document.getElementById('allBox').innerText = text;
        }
        