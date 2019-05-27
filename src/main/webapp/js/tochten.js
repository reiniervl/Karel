function BeschikbareTochten(tochttype, verhuurder) {
	this.url = "login/activiteiten/" + tochttype + "/" + verhuurder;
	this.getlist = function(callback) {
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if(this.readyState === 4 && this.status === 200) {
				var data = JSON.parse(this.responseText);
				callback(data);
			} else if (this.status === 500) {
				callback({success: false, reason: "Internal server error"})
			} else if (this.status === 404) {
				callback({success: false, reason: "file not found"})
			}
		};

		request.open("GET", this.url, true);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.send();
	};
}

function Tochtenbak(tochttype, verhuurder) {
	this.bt = new BeschikbareTochten(tochttype, verhuurder);
	this.tochten = [];
	this.vul = function() {
		this.bt.getlist(this.update);
	}
	this.update = function(res) {
		var ts = document.getElementById("tochtSelect")
		if(res.success === true && res.tochten.length > 0) {
			ts.remove(null);
			this.tochten = res.tochten;
			for(i = 0; i < res.tochten.length; i++) {
				var o = document.createElement("option");
				o.value = res.tochten[i].id;
				o.text = "tocht " + o.value;
				ts.add(o);
			}
		} else {
			ts.remove(null);
			var o = document.createElement("option");
			o.value = "";
			o.text = "Geen tochten gevonden";
			ts.add(o);
		}

	}
}