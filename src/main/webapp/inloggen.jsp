<!DOCTYPE html>
<html>
<head>
<title>KAREL Bootverhuur</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/style.css" />
</head>
<body>
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
					<li class="selected"><a href="login.html">Login</a></li>
					<li><a href="registreer.html">Registreer</a></li>
				</ul>
			</div>
		</div>
		<div id="site_content">
			<div class="sidebar">
				<img src="style/logo.jpg" width="300" height="200">
			</div>
<!-- Main Content -->
		<div id="content">
			<%
			Object usr = session.getAttribute("username");
			String username;
			if(usr != null) {
				username = "Log in";
			%>
			<%-- <p>Hallo, <%=username%></p> --%>
			<%
			} else {
				username = "Inloggen is niet gelukt!";
			}%>
			
			<h1><%=username %></h1>
			<form action="login/account/inloggen" method="post">
				<div class="form_settings">
					<p>
						<label for="username" ><span>gebruiksnaam</span></label>
						<input id="username" class="contact" type="text"
							name="username" value="" />
					</p>
					<p>
						<label for="password"><span>wachtwoord</span></label><input id="password" class="contact" type="password"
							name="password" value="" />
					</p>

					<p style="padding-top: 15px">
						<span>&nbsp;</span><input class="submit" type="submit"
							name="contact_submitted" value="Login"/>
					</p>
				</div>
			</form>
		</div>
	</div>
	<div id="footer">
		<p>
			<a href="index.html">Home</a> | <a href="login.html">Login</a> | <a
				href="registreer.html">Registreer</a>
		</p>
		<p>
			Copyright &copy; Karel & Dana | <a
				href="https://www.ishetalvrijdag.nl/">Benieuwd of het al vrijdag
				is?</a>
	</div>
	</div>
</body>
</html>
