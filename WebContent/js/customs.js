 $(function(){
	 	$("#header").load("header.html"); 
	 	$("#footer").load("footer.html");
	    $('select').formSelect();
	    $('.tabs').tabs();
	    console.log('tabs and select initialized');
	  });
 
$("#empNewSubmit").submit(function(){
	
	var pin = document.getElementById('pin').value;
	 if(Number.isNaN(Number.parseInt(pin))|| pin.length !== 6){
		 alert("pin not valid");
		 console.log(typeof Number(pin));
		 return false;
	 }
	 return true;
	
});


$(function(){
	//$("input").prop("disabled", true);
	//$("#target select").prop("disabled", "disabled");
	$(".close-card").hide();
	$(".card-submit").hide();
});

var x = true;
$("button#manage").click(function() {
	if(x){
		//$("input").prop("disabled", false);
		//$("#target select").removeAttr = "disabled";
		$(".close-card").show();
		$(".card-submit").show();
		$(this).text("Save Changes");
		x = false;
	}
	else{
		//$("input").prop("disabled", true);
		//$("#target select").prop("disabled", "disabled");
		console.log('this workz');
		$(".close-card").hide();
		$(".card-submit").hide();
		$(this).text("Manage");
		x = true;
		window.reload(true);
	}
});



$("#one #target #empShowSubmit").click(function(event) {
	console.log("inside ajax paradigm id ");
	event.preventDefault();
	var formdata = {
		'firstName' : $("#one #target input[name = 'firstName']").val(),
		'lastName' : $("#one #target input[name = 'lastName']").val(),
		'gender' : $("#one #target select#gender").val(),
		'email' : $("#one #target input[name = 'email']").val(),
		'street' : $("#one #target input[name = 'street']").val(),
		'state' : $("#one #target input[name = 'state']").val(),
		'country' : $("#one #target select#country1").val(),
		'pin' : $("#one #target input[name = 'pin']").val(),
		'request' : $("#one #target input[name = 'request']").val(),
		'emp_id' : $("#one #target input[name = 'emp_id']").val(),
		'department' : $("#one #target select#department1").val(),
		'company' : $("#one #target select#company1").val()
	};
	console.log("comp " + $("#one #target select#company1").val());
	var request = $.ajax({
		type : 'post',
		url : 'DataInserter',
		data : formdata,
		dataType : 'text',
		encode : true,
		success : function(data) {
			window.alert("update status "
					+ data);
		},
		error : function() {
			window.alert("error");
		}
	});
});

$("#two #target #empShowSubmit").click(function(event) {
var id = "";
console.log("inside ajax paradigm id ");
event.preventDefault();
var formdata = {
	'firstName' : $("#two #target input[name = 'firstName']").val(),
	'lastName' : $("#two #target input[name = 'lastName']").val(),
	'gender' : $("#two #target select#gender").val(),
	'email' : $("#two #target input[name = 'email']").val(),
	'street' : $("#two #target input[name = 'street']").val(),
	'state' : $("#two #target input[name = 'state']").val(),
	'country' : $("#two #target select#country2").val(),
	'pin' : $("#two #target input[name = 'pin']").val(),
	'request' : $("#two #target input[name = 'request']").val(),
	'emp_id' : $("#two #target input[name = 'emp_id']").val(),
	'department' : $("#two #target select#department2").val(),
	'company' : $("#two #target select#company2").val()
};
console.log("comp " + $("#two #target select#company2").val());
var request = $.ajax({
	type : 'post',
	url : 'DataInserter',
	data : formdata,
	dataType : 'text',
	encode : true,
	success : function(data) {
		window.alert("update status "
				+ data);
	},
	error : function() {
		window.alert("error");
	}
});
});




$("ul.pagination li.page").click(function(){
	$("#two").show();
	pageno = $(this).attr("id")-1;
	console.log("clicked " + pageno);
	data = {
			
			'request' : 'paginateright',
			'currentpage' : pageno,
			'limit' : 2
	};
	$.ajax({
		type : 'post',
		url : 'DataInserter',
		data : data,
		dataType : 'json',
		encode : true,
		success : function(obj) {
			//console.log(obj.objects[0].id + " " + obj.objects[1].id);
			pageno = obj.pageno;
			$("li").removeClass("active");
			$("#" + pageno).addClass("active");
			var innerobj = obj.objects;
			if(innerobj.length === 1){
				$("#two").hide();
			}
			for(var i = 0; i < innerobj.length; i++){
				var id = (i === 0)?"one":"two";
				$("#"+id+" input[name = 'firstName']").val(innerobj[i].firstName);
				$("#"+id+" input[name = 'emp_id']").val(innerobj[i].id);
				$("#"+id+" #delemp input[name = 'empid']").val(innerobj[i].id);
				$("#"+id+" input[name = 'lastName']").val(innerobj[i].lastName);
				$("#"+id+" select#gender option#default").val(innerobj[i].gender);
				$("#"+id+" input[name = 'email']").val(innerobj[i].email_Id);
				$("#"+id+" input[name = 'street']").val(innerobj[i].address.street);
				$("#"+id+" input[name = 'state']").val(innerobj[i].address.state);
				$("#"+id+" input[name = 'pin']").val(innerobj[i].address.pin);
				$("#company"+(i+1)).val(innerobj[i].company.id);
				$("#country"+(i+1)).val(innerobj[i].country.id);
				$("#department"+(i+1)).val(innerobj[i].department.id);
			}
		},
		error : function() {
			window.alert("error ");
		}

	});
});

$("#rightScroll").click(function() {
	$("#two").show();
	console.log("hey right");
	rdata = {
		'request' : 'paginateright',
		'currentpage' : pageno,
		'limit' : 2
	};

	$.ajax({
		type : 'post',
		url : 'DataInserter',
		data : rdata,
		dataType : 'json',
		encode : true,
		success : function(obj) {
		//	console.log(obj.objects[0].id + " " + obj.objects[1].id);
			pageno = obj.pageno;
			$("li").removeClass("active");
			$("#" + pageno).addClass("active");
			var innerobj = obj.objects;
			if(innerobj.length === 1){
				$("#two").hide();
			}
			for(var i = 0; i < innerobj.length; i++){
				var id = (i === 0)?"one":"two";
				$("#"+id+" input[name = 'firstName']").val(innerobj[i].firstName);
				$("#"+id+" input[name = 'emp_id']").val(innerobj[i].id);
				$("#"+id+" #delemp input[name = 'empid']").val(innerobj[i].id);
				$("#"+id+" input[name = 'lastName']").val(innerobj[i].lastName);
				$("#"+id+" select#gender option#default").val(innerobj[i].gender);
				$("#"+id+" input[name = 'email']").val(innerobj[i].email_Id);
				$("#"+id+" input[name = 'street']").val(innerobj[i].address.street);
				$("#"+id+" input[name = 'state']").val(innerobj[i].address.state);
				$("#"+id+" input[name = 'pin']").val(innerobj[i].address.pin);
				$("#company"+(i+1)).val(innerobj[i].company.id);
				$("#country"+(i+1)).val(innerobj[i].country.id);
				$("#department"+(i+1)).val(innerobj[i].department.id);
			}
		},
		error : function() {
			window.alert("error ");
		}

	});

});

$("#leftScroll").click(function() {
	$("#two").show();
	console.log("hey left");
	ldata = {
		'request' : 'paginateleft',
		'currentpage' : pageno,
		'limit' : 2
	};

	$.ajax({
		type : 'post',
		url : 'DataInserter',
		data : ldata,
		dataType : 'json',
		encode : true,
		success : function(obj) {
			pageno = obj.pageno;
			$("li").removeClass("active");
			$("#" + pageno).addClass("active");
			var innerobj = obj.objects;
			if(innerobj.length === 1){
				$("#two").hide();
			}
	//		console.log(obj.objects[0].id + " " + obj.objects[1].id);
			for(var i = 0; i < innerobj.length; i++){
				var id = (i === 0)?"one":"two";
				$("#"+id+" input[name = 'firstName']").val(innerobj[i].firstName);
				$("#"+id+" input[name = 'emp_id']").val(innerobj[i].id);
				$("#"+id+" input[name = 'lastName']").val(innerobj[i].lastName);
				$("#"+id+" #delemp input[name = 'empid']").val(innerobj[i].id);
				$("#"+id+" select#gender option#default").val(innerobj[i].gender);
				$("#"+id+" input[name = 'email']").val(innerobj[i].email_Id);
				$("#"+id+" input[name = 'street']").val(innerobj[i].address.street);
				$("#"+id+" input[name = 'state']").val(innerobj[i].address.state);
				$("#"+id+" input[name = 'pin']").val(innerobj[i].address.pin);
				$("#company"+(i+1)).val(innerobj[i].company.id);
				$("#country"+(i+1)).val(innerobj[i].country.id);
				$("#department"+(i+1)).val(innerobj[i].department.id);
			}
		},
		error : function() {
			window.alert("error");
		}

	});
});
