<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>About Me</title>
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
	function changeColor(fieldName) {
		var inputField = document.getElementById(fieldName);
		inputField.style.backgroundColor = 'yellow'; 
	}
</script>
</head>
<body>
	<div class="container">
		<h2>About Me - Update Player Details</h2>
		<form action="<c:url value='/aboutme'/>" method="post">
			<label for="playerName">Player Name:</label> <input type="text"
				id="playerName" name="playerName"
				value="${loggedInPlayer.playerName}" oninput="changeColor(this.id)">

			<label for="email">Email:</label> <input type="text" id="email"
				name="email" value="${loggedInPlayer.email}"
				oninput="changeColor(this.id)"> <label for="password">Password:</label>
			<input type="password" id="playerPassword" name="playerPassword"
				value="${loggedInPlayer.playerPassword}"
				oninput="changeColor(this.id)">

			<button type="submit">Update Profile</button>
		</form>
		        <br><br>
        <a href="<c:url value='/playerdashboard'/>"><i class="fa fa-arrow-left"></i>&nbsp;&nbsp;Go Back to Main Page</a>
		<br><br>
		<c:choose>
			<c:when test="${updateSuccess eq 'true'}">
				<p class="success-message">Values successfully updated!</p>
			</c:when>
			<c:when test="${updateSuccess eq 'false'}">
				<p class="error-message">Error when updating values. Try again.</p>
			</c:when>
		</c:choose>
	</div>
</body>
</html>
