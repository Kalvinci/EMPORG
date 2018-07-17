<%@page import="testclasses.DriverProg"%>
<%@page import="java.sql.*"%>
<%@page import="java.lang.*"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException,testclasses.*, java.util.*"%>

<!Doctype html>
<html>
<head>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<link type="text/css" rel="stylesheet" href="css/customs.css">
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<title>company_Info</title>
</head>
<body>
	<div id="header"></div>
	<main>
	<div class="container" style = "margin-top : 30px; margin-bottom : 30px; ">
		<form action="DataInserter" method="post" autocomplete="on">
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<input type="text" id="name" name="name" class="validate" required> <label for="name">Name</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<input type="text" id="website" name="website" class="validate" required> <label for="website">Website</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<input type="text" id="count" name="count" class="validate" required> <label for="count">Number of Employees</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<label>Address</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<input type="text" id="street" name="street" class="validate" required> <label
						for="street">Street</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<input type="text" id="state" name="state" class="validate" required> <label
						for="state">State</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<select name="country" id="country" class="validate">
						<option value="" disabled selected>Select your Country</option>
						<%
							DriverProg driverProg = new DriverProg();
							List<Country> countries = driverProg.getCountries();
							for ( Country c : countries ){
						%>

						<option value="<%= c.getId() %>"><%=c.getName() %></option>
						<%
							}
						%>
					</select> <label for="country">Country</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col l8 offset-l2">
					<input type="text" id="pin" name="pin" class="validate" required> <label for="pin">PIN</label>
					<input type="hidden" name="request" id="request" value="company">
				</div>
			</div>
			<div class="center">
				<button type="submit" class="btn waves-effect waves-light">
					Submit <i class="material-icons right">send</i>
				</button>
			</div>
		</form>
	</div>
	</main>
	<div id="footer"></div>
	<!--JavaScript at end of body for optimized loading-->
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script type="text/javascript" src="js/customs.js"></script>
</body>
</html>
