function beschikbare_tochten(tochttype, verhuurder) {
	this.url = "login/activiteiten/" + tochttype + "/" + verhuurder;
	this.getlist = function() {
		var request = new XMLHttpRequest();
		request.onload = function(e) {
			if(this.readyState === 4 && this.status === 200) {
				var data = JSON.parse(this.responseText);
				if(data.success === "true") {
					return data.tochten;
				} else {
					return data.success;
				}
			}
		};

		request.open("GET", this.url, true);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.send();
	};
}