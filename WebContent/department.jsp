<%@page import="java.sql.*"%>
<%@page import="java.lang.*"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>

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
<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/customs.js"></script>
<title>department_Info</title>
</head>
<body>
	<div id="header"></div>
	<main>
	<div class="container" style="margin-top:250px; margin-top:250px;">
		<form action="DataInserter" method="post" autocomplete="off">
			<div class="row">
				<div class="input-field col l4 offset-l4">
					<input type="text" id="name" name="name" class="validate" required> <label for="name">Department Name</label>
					<input type="hidden" name="request" id="request" value="department">
				</div>
			</div>
			<div class="center-align">
				<button type="submit" class="btn waves-effect waves-light">
					Submit <i class="material-icons right">send</i>
				</button>
			</div>
		</form>
	</div>
	</main>
	<div id="footer"></div>
</body>
</html>
