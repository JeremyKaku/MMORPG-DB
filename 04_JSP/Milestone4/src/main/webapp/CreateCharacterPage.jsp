<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Create New Character</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
}

.container {
	max-width: 600px;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-bottom: 8px;
}

input {
	width: 100%;
	padding: 8px;
	margin-bottom: 16px;
	box-sizing: border-box;
}

button {
	background-color: #4caf50;
	color: #fff;
	padding: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

.success-message {
	color: green;
	font-weight: bold;
	margin-top: 10px; /* Adjust margin as needed */
}

.error-message {
	color: red;
	font-weight: bold;
	margin-top: 10px; /* Adjust margin as needed */
}

.changed {
	border: 2px solid #ffcc00; /* Yellow border for changed fields */
}

.confirm {
	wide: 50px;
	height: 40px;
	background-color: black;
	text-align: center;
	left: 50%;
}

a {
	text-decoration: none;
	color: #2196F3;
	font-weight: bold;
}

a:hover {
	color: #0b7dda;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
function validateForm() {
    var firstName = document.getElementById('firstName').value;
    var lastName = document.getElementById('lastName').value;

    if (firstName.trim() === "") {
        alert("Please enter a firstName.");
        return false; 
        
     if (lastName.trim() === "") {
         alert("Please enter a lastName.");
         return false; 
    }

    return true;
}
</script>
</head>
<body>
	<div class="container">
		<h2>Create New Character</h2>
		<form action="<c:url value='/create'/>" method="post"
			onsubmit="return validateForm()">
			<label for="firstName">First Name:</label> <input type="text"
				id="firstName" name="firstName" value=""> <label
				for="lastName">Last Name:</label> <input type="text" id="lastName"
				name="lastName" value=""> <label>Main Hand
				Weapon(Default):</label> <input type="text" id="27" name="weapon"
				style="background-color: #C6C6C9;" value="Tarnished Makai Bow"
				readonly>
			<button class="confirm" type="submit">Confirm</button>
		</form>
		<br> <br> <a href="<c:url value='/playerdashboard'/>"><i
			class="fa fa-arrow-left"></i>&nbsp;&nbsp;Go Back to Main Page</a> <br>
		<br>
		<c:choose>
			<c:when test="${createSuccess eq 'true'}">
				<c:redirect url="/playerdashboard" />

			</c:when>
			<c:when test="${createSuccess eq 'false'}">
				<p class="error-message">Error when creating. Please try again.</p>
			</c:when>
			<c:when test="${sameName eq 'true'}">
				<p class="error-message">There is already a character with the same first and last name. Please input again.</p>
			</c:when>
			<c:when test="${input eq 'false'}">
				<p class="error-message">First Name or Last Name can not be empty. Please input again.</p>
			</c:when>
			<c:when test="${session eq 'false'}">
				<p class="error-message">. Please input again.</p>
			</c:when>
		</c:choose>
	</div>
</body>
</html>
