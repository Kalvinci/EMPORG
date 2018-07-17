<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>employee_Info</title>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />

<link type="text/css" rel="stylesheet" href="css/customs.css" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<script type="text/javascript">
var pageno = 1;
$(function(){
	data  = {
			'request' : 'load',
			'limit' : 2,
			'offset' : 0
	};
	
	$.ajax({
		type : 'post',
		url : 'DataInserter',
		data : data,
		dataType : 'json',
		encode : true,
		success : function(obj) {
			console.log("data loaded in servlet " + obj[1].department.id);
			if(obj.length === 1){
				$("#two").hide();
			}
			for(var i = 0; i < obj.length; i++){
				var id = (i == 0)?"one":"two";
				$("#"+id+" input[name = 'firstName']").val(obj[i].firstName);
				$("#"+id+" #delemp input[name = 'empid']").val(obj[i].id);
				$("#"+id+" input[name = 'emp_id']").val(obj[i].id);
				$("#"+id+" input[name = 'lastName']").val(obj[i].lastName);
				$("#"+id+" select#gender").val(obj[i].gender);
				$("#"+id+" input[name = 'email']").val(obj[i].email_Id);
				$("#"+id+" input[name = 'street']").val(obj[i].address.street);
				$("#"+id+" input[name = 'state']").val(obj[i].address.state);
				$("#"+id+" input[name = 'pin']").val(obj[i].address.pin);
				$("#company"+(i+1)).val(obj[i].company.id);
				$("#country"+(i+1)).val(obj[i].country.id);
				$("#department"+(i+1)).val(obj[i].department.id);
			}
		},
		error : function() {
			window.alert("error");
		}
	});
	$("#" + pageno).addClass("active");
});

</script>
</head>
<body>

	<div id="header"></div>
	<main> <!-- #ffff8d yellow accent-1 -->
		<div class="row">
			<div class="col s12">
				<ul class="tabs">
					<li class="tab col s6"><a href="#show_employees">Show Employees</a></li>
					<li class="tab col s6"><a href="#new_employee">Add new employee</a></li>
				</ul>
			</div>
		</div>
		<div id="show_employees" class="col s12" style="margin-top: 10px;">
			<div class="row center-align">
				<button id="manage" class="btn waves-effect waves-light">Manage</button>
			</div>
			<%
				DataInserter dataInserter = new DataInserter();
				DriverProg driverProg = dataInserter.getDriverProg();
				List<Employee> employees = driverProg.getEmployees(2, 0); 
				int size = driverProg.getEmployeeSize();
				int numberOfPages = size / 2 + (size % 2 == 0 ? 0 : 1);
				List<Department> departments = driverProg.getDepartments();
				List<Company> companies = driverProg.getCompanies();
				List<Country> countries = driverProg.getCountries();
			%>
			
		<div class="card" id = "one">
			<div class="card-content">
				<form id="delemp" action="DataInserter" method="post">
					<div class="row close-card">
						<input type="hidden" name="request" value="deleteEmployee">
						<input type="hidden" name="empid">
						<i></i>
						<button id="close" type="submit" class="right">
						<i class="close material-icons center black-text">close</i>
						</button>
					</div>
				</form>
			<!--  action = "DataInserter" -->
				<form id="target" method="post" autocomplete="on">
					<div class="row">
						<div class="input-field col l2 tooltip">
							<input type="text" name="firstName" id="firstName"
								class="validate" required>
							<span class="tooltiptext"></span> <label>First
								Name</label>
						</div>
						<div class="input-field col l2">
							<input type="text" name="lastName" id="lastName"
								class="validate" required>
							<label>Last Name</label>
						</div>
						<div class="input-field col l1">
							<select name="gender" id="gender" class="validate">
								<option value="MALE">MALE</option>
								<option value="FEMALE">FEMALE</option>
								<option value="UNSPECIFIED">UNSPECIFIED</option>
							</select> <label for="gender">Gender</label>
						</div>
						<div class="input-field col l3 tooltip">
							<input type="email" name="email" id="email" class="validate" required> <label>Email_Id</label>
						</div>
						<div class="input-field col l2">
							<input type="text" name="street" id="street" class="validate"
								required><label>Street</label>
						</div>
						<div class="input-field col l2 tooltip">
							<input type="text" name="state" id="state" class="validate"
								 required> <span
								class="tooltiptext"></span> <label>State</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l3">
							<select name="country1" id="country1" class="default" required>
								<%
									for (Country cont : countries) {
								%>

								<option value="<%=cont.getId()%>"><%=cont.getName()%></option>
								<%
									}
								%>
							</select> <label>Country</label>
						</div>
						<div class="input-field col l3 tooltip">
							<input type="text" name="pin" id="pin"
								class="validate" required>
							<span class="tooltiptext"></span> <label>PIN</label>
						</div>
						<div class="input-field col l3">
							<select name="company1" id="company1" class="validate">
								<%
									for (Company c : companies) {
								%>
								<option value="<%=c.getId()%>"><%=c.getCompany_name()%></option>
								<%
									}
								%>
							</select> <label>Company</label>
						</div>
						<div class="input-field col l3">
							<select name="department1" id="department1" class="validate">
								<%
									for (Department d : departments) {
								%>
								<option value="<%=d.getId()%>"><%=d.getName()%></option>
								<%
									}
								%>
							</select> <label>Department</label>
						</div>
					</div>
					<input type="hidden" name="request" value="changes"> <input
						type="hidden" name="emp_id">

					<div class="row card-submit">
						<div class="col l2">
							<button id="empShowSubmit" type="submit"
								class="btn waves-effect waves-light">
								Save <i class="material-icons right">send</i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<div class="card" id = "two">
			<div class="card-content">
				<form id="delemp" action="DataInserter" method="post">
					<div class="row close-card">
						<input type="hidden" name="request" value="deleteEmployee">
						<input type="hidden" name="empid">
						<i></i>
						<button id="close" type="submit" class="right">
						<i class="close material-icons center black-text">close</i>
						</button>
					</div>
				</form>
			<!--  action = "DataInserter" -->
				<form id="target" method="post" autocomplete="on">
					<div class="row">
						<div class="input-field col l2 tooltip">
							<input type="text" name="firstName" id="firstName"
								class="validate" required>
							<span class="tooltiptext"></span> <label>First
								Name</label>
						</div>
						<div class="input-field col l2 tooltip">
							<input type="text" name="lastName" id="lastName"
								class="validate" required>
							<span class="tooltiptext"></span> <label>Last
								Name</label>
						</div>
						<div class="input-field col l1">
							<select name="gender" id="gender" class="validate">
								<option value="MALE">MALE</option>
								<option value="FEMALE">FEMALE</option>
								<option value="UNSPECIFIED">UNSPECIFIED</option>
							</select> <label for="gender">Gender</label>
						</div>
						<div class="input-field col l3 tooltip">
							<input type="email" name="email" id="email" class="validate"
								required> <span
								class="tooltiptext"></span> <label>Email_Id</label>
						</div>
						<div class="input-field col l2 tooltip">
							<input type="text" name="street" id="street" class="validate"
								required> <span
								class="tooltiptext"></span> <label>Street</label>
						</div>
						<div class="input-field col l2 tooltip">
							<input type="text" name="state" id="state" class="validate"
								required> <span
								class="tooltiptext"></span> <label>State</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l3">
							<select name="country2" id="country2" class="validate" required>
								<%
									for (Country cont : countries) {
								%>

								<option value="<%=cont.getId()%>"><%=cont.getName()%></option>
								<%
									}
								%>
							</select> <label>Country</label>
						</div>
						<div class="input-field col l3 tooltip">
							<input type="text" name="pin" id="pin"
								class="validate" required>
							<span class="tooltiptext"></span> <label>PIN</label>
						</div>
						<div class="input-field col l3">
							<select name="company2" id="company2" class="validate">
								<%
									for (Company c : companies) {
								%>
								<option value="<%=c.getId()%>"><%=c.getCompany_name()%></option>
								<%
									}
								%>
							</select> <label>Company</label>
						</div>
						<div class="input-field col l3">
							<select name="department2" id="department2" class="validate">
								<%
									for (Department d : departments) {
								%>
								<option value="<%=d.getId()%>"><%=d.getName()%></option>
								<%
									}
								%>
							</select> <label>Department</label>
						</div>
					</div>
					<input type="hidden" name="request" value="changes"> <input
						type="hidden" name="emp_id">

					<div class="row card-submit">
						<div class="col l2">
							<button id="empShowSubmit" type="submit"
								class="btn waves-effect waves-light">
								Save <i class="material-icons right">send</i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
				
				<div class="container center">
					<ul class="pagination">
						<li id="leftScroll" class="waves-effect"><a href="#!"><i
								class="material-icons">chevron_left</i></a></li>
						<%
							for (int i = 1; i <= numberOfPages; i++) {
						%>
						<li id="<%=i%>" class = "page"><a href='#!'><%=i%></a></li>
						<%
							}
						%>
						<li id="rightScroll" class="waves-effect"><a href="#!"><i
								class="material-icons">chevron_right</i></a></li>
					</ul>
	
				
				</div>
			</div>


		<div id="new_employee" class="col s12" style="margin-top: 30px;">
			<div class="container custom_container">

				<form action="DataInserter" name="empForm" method="post"
					autocomplete="on">
					<div class="row">
						<div class="input-field col l4 offset-l2">
							<input type="text" name="firstName" id="firstName"
								class="validate" required> <label for="firstName">First
								Name</label>
						</div>
						<div class="input-field col l4">
							<input type="text" name="lastName" id="lastName" class="validate"
								required> <label for="lastName">Last Name</label>
						</div>
					</div>
					<div class="row">
						<div class="col l8 offset-l2">
							<label>Gender</label>
							<p>
								<label> <input name="gender" type="radio" value="male"
									checked /> <span>Male</span>
								</label>
							</p>
							<p>
								<label> <input name="gender" type="radio" value="female" />
									<span>Female</span>
								</label>
							</p>
							<p>
								<label> <input name="gender" type="radio"
									value="unspecified" /> <span>Unspecified</span>
								</label>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<input type="email" id="email" name="email" class="validate"
								required> <label for="email">Email_Id</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<label>Address</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<input type="text" id="street" name="street" class="validate"
								required> <label for="street">Street</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<input type="text" id="state" name="state" class="validate"
								required> <label for="state">State</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<select name="country" id="country" class="validate">
								<option value="" disabled selected>Select your Country</option>
								<%
									for (Country c : countries) {
								%>

								<option value="<%=c.getId()%>"><%=c.getName()%></option>
								<%
									}
								%>
							</select> <label for="country">Country</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<input type="text" id="pin" name="pin" class="validate" required>
							<label for="pin">PIN</label> <input type="hidden" name="request"
								id="request" value="employee">
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<select name="company" id="company" class="validate">
								<option value="" disabled selected>Select your Company</option>
								<%
									for (Company c : companies) {
								%>
								<option value="<%=c.getId()%>"><%=c.getCompany_name()%></option>
								<%
									}
								%>
							</select> <label for="company">Company</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col l8 offset-l2">
							<select name="department" id="department" class="validate"
								required>
								<option value="" disabled selected>Select your Department</option>
								<%
									for (Department d : departments) {
								%>
								<option value="<%=d.getId()%>"><%=d.getName()%></option>
								<%
									}
								%>
							</select> <label for="department">Department</label>
						</div>
					</div>
					<div class="center-align" style="margin-bottom : 30px;">
						<button type="submit" id="empNewSubmit" value="Submit"
							class="btn waves-effect waves-light">Submit <i class="material-icons right">send</i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	<div id="footer"></div>
	<!--JavaScript at end of body for optimized loading-->
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script type="text/javascript" src="js/customs.js"></script>

</body>
</html>
