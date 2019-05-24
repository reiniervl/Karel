function BeschikbareTochten(tochttype, verhuurder) {
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

	this.getTestList = function() {
		return "{ \"success\": true,"
			+ "\"tochten\": ["
			+ "{ \"id\": 1, \"prijs\": 12.95, \"start\": \"2014-01-01T23:28:56.782Z\", \"eind\": \"2014-01-01T23:28:56.782Z\"},"
			+ "{ \"id\": 2, \"prijs\": 11.95, \"start\": \"2014-01-01T23:28:56.782Z\", \"eind\": \"2014-01-01T23:28:56.782Z\"},"
			+ "{ \"id\": 3, \"prijs\": 14.95, \"start\": \"2014-01-01T23:28:56.782Z\", \"eind\": \"2014-01-01T23:28:56.782Z\"},"
			+ "{ \"id\": 4, \"prijs\": 13.95, \"start\": \"2014-01-01T23:28:56.782Z\", \"eind\": \"2014-01-01T23:28:56.782Z\"}"
			+ "]}";
	};
}

function Tochtenbak(tochttype, verhuurder) {
	this.bt = new BeschikbareTochten(tochttype, verhuurder);
	this.tochten = [];
	this.vul = function() {
		var res = JSON.parse(this.bt.getTestList());
		var ts = document.getElementById("tochtSelect")
		if(res.success === true) {
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