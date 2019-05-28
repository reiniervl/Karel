<!DOCTYPE html>
<html>
<head>
<title>KAREL Bootverhuur</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<link rel="stylesheet" type="text/css" href="style/reserveer.css" />
</head>
<body>
	<%
			Object usr = session.getAttribute("username");
			if(usr == null) {
				response.sendRedirect(request.getContextPath() + "/inloggen.jsp");
			}
			String username = (String) usr;
	%>
	<div id="main">
		<div id="header">
			<div id="logo">
				<h1>
					<a href="index.html">Boot<span class="logo_colour">Verhuur</span></a>
				</h1>
				<h2>Huur een BOOT. STUGA. FIETS</h2>
			</div>
			<div id="menubar">
				<ul id="menu">
					<li><a href="index.html">Home</a></li>
					<li class="selected"><a href="inloggen.jsp">Reserveer</a></li>
				</ul>
			</div>
		</div>
		<div id="site_content">
			<div id="content">
				<h1>Hallo <%=username%> Selecteer een datum</h1>
				<p></p>
			<div id="content">
				<div id="agenda" style="background: #fff; max-width: 628px;">
					<form id="reserveerForm" method="POST" action="login/reserveren">
						<div>
							<label for="verhuurderSelect">Verhuurder</label>
							<select id="verhuurderSelect" name="verhuurder">
								<option value="karel">Karel</option>
							</select>
						</div>
						<div>
							<label for="soortSelect">Soort Tocht</label>
							<select id="soortSelect">
								<option value="meertocht">Meer Tocht</option>
								<option value="riviertocht">Rivier Tocht</option>
							</select>
						</div>
						<div>
							<label for="tochtSelect">Tocht</label>
							<select id="tochtSelect" name="tocht">
								<option value="">Tocht</option>
							</select>
						</div>
						<input class="submit" type="submit"
						name="contact_submitted" value="Reserveer" />
					</form>
				</div>
				<p></p>
			</div>
			<form action="rivier_meertocht.html" method="post">
				<div class="keuze_tocht">
					<p></p>
					<span>&nbsp;</span><input class="submit" type="submit"
						name="contact_submitted" value="Terug" />
				</div>
			</form>
		</div>
	</div>
	<div id="footer">
		<p>
			<a href="index.html">Home</a> | <a href="inloggen.jsp">Login</a> | <a
				href="registreer.html">Registreer</a>
		</p>
		<p>
			Copyright &copy; Karel & Dana | <a
				href="https://www.ishetalvrijdag.nl/">Benieuwd of het al vrijdag
				is?</a>
	</div>
	<!-- JavaScript -->
	<script src="js/tochten.js"></script>
	<script>
		var ts = document.getElementById("soortSelect");
		var tochtenbak = new Tochtenbak(ts.value, "karel");
		tochtenbak.vul();
		ts.addEventListener("onchange", function() {
			tochtenbak = new Tochtenbak(ts.value, "karel");
			tochtenbak.vul();
		});
	</script>
</body>
</html>