<!DOCTYPE html>
<html>
<head>
	<title>KAREL Bootverhuur</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="website description" />
	<meta name="keywords" content="website keywords, website keywords" />
	<link rel="stylesheet" type="text/css" href="style/style.css" />
</head>
<body>
	<div id="main">
		<div id="header">
			<div id="logo">
				<h1><a href="index.html">Boot<span class="logo_colour">Verhuur</span></a></h1>
				<h2>Huur een BOOT. STUGA. FIETS</h2>
			</div>
			<div id="menubar">
				<ul id="menu">
					<li><a href="index.html">Home</a></li>
					<li><a href="inloggen.jsp">Login</a></li>
					<li class="selected"><a href="registreren.jsp">Registreer</a></li>
				</ul>
			</div>
		</div>

		<div id="site_content">
			<div class="sidebar">
				<img src="style/logo.jpg" width="300" height="200">
			</div>
			<div id="content">
			<%
			Object usr = session.getAttribute("username");
			if(usr != null) {
				String username = (String) usr;
			%>
				<h1>Hallo, <%=username%></h1>
				<p>Het ziet er naar uit dat je al bent ingelogd. Ga naar <a href="reserveren.jsp">reserveren</a> om een tochtje je maken</p>
				<p>Ben je niet <em><%=username%></em>, klik dan <a href="login/account/uitloggen">hier</a> om uit te loggen.</p>
			<%
			} else {
			%>
				<h1>Registreer hier</h1>
				<form action="login/registreren" method="post">
					<div class="form_settings">
						<p><span>gebruiksnaam</span><input class="contact" type="text" name="your_name" value="" /></p>
						<p><span>wachtwoord</span><input class="contact" type="password" name="password" value="" /></p>
						<p><span>Email Address</span><input class="contact" type="email" name="your_email" value="" /></p>
						<p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="contact_submitted"
								value="submit" /></p>
					</div>
				</form>
			<%
			}
			%>
			</div>
		</div>
		<div id="footer">
			<p><a href="index.html">Home</a> | <a href="inloggen.jsp">Login</a> | <a href="registreren.jsp">Registreer</a></p>
			<p>Copyright &copy; Karel & Dana | <a href="https://www.ishetalvrijdag.nl/">Benieuwd of het al vrijdag is?</a>
		</div>
	</div>
</body>

</html>