<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Player Dashboard</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
}

.container {
	max-width: 700px;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #4caf50;
	color: #fff;
}

a {
	text-decoration: none;
	color: #2196F3;
	font-weight: bold;
}

a:hover {
	color: #0b7dda;
}

.tabs-container {
	display: flex;
	justify-content: flex-end;
	padding: 10px;
}

.tab {
	margin-right: 20px;
	color: red;
	text-decoration: none;
	font-weight: bold;
	font-size: 16px;
}

.tab:hover {
	color: green;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1;
}

.button-container button {
	padding: 11px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-weight: bold;
	font-size: 12px;
	transition: background-color 0.3s;
}

.button-container button:hover {
	filter: brightness(85%);
}

.button-container .expand-button {
	background-color: #2196F3;
	color: white;
}

.button-container .delete-button {
	background-color: #FF0000;
	color: white;
}
</style>
</head>
<body>
	<script>
	    	function confirmDelete(characterID, characterName) {
		        var confirmation = confirm("Are you sure you want to delete " + characterName + "?");
		        if (confirmation) {
		            	document.getElementById("deleteForm_" + characterID).submit();
		        }
	    	}
	    </script>
	<div class="container">
		<div class="tabs-container">
			<div class="dropdown">
				<a href="#" class="tab">About Game</a>
				<div class="dropdown-content">
					<a href="<c:url value='/AllItems'/>">Items</a> <a
						href="<c:url value='/AllGears'/>">Equipment</a> <a
						href="<c:url value='/AllWeapons'/>">Weapons</a> <a
						href="<c:url value='/Attributes'/>">Attributes</a> <a
						href="<c:url value='/Jobs'/>">Jobs</a> <a
						href="<c:url value='/AllCurrency'/>">Currency</a>
				</div>
			</div>
			<a href="<c:url value='aboutme'/>" class="tab">About Me</a> <a
				href="<c:url value='/login'/>" class="tab">Logout</a>
		</div>
		<h1>Hi ${loggedInPlayer.playerName}!</h1>
		<h2>Welcome to your dashboard.</h2>

		<h3>Characters</h3>

		<%-- Display success message if available --%>
		<%
		String successMessage = (String) session.getAttribute("successMessage");
		%>
		<%
		if (successMessage != null) {
		%>
		<div class="success-message">${successMessage}</div>
		<%
		session.removeAttribute("successMessage");
		%>
		<%
		}
		%>


		<table>
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Explore</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="character" items="${characters}">
					<tr>
						<td>${character.firstName}</td>
						<td>${character.lastName}</td>
						<td class="button-container">
							<form action="character" method="post">
								<input type="hidden" name="characterID"
									value="${character.characterID}">
								<button type="submit" class="expand-button">Profile</button>
							</form>
						</td>

						<td class="button-container">
							<form id="deleteForm_${character.characterID}"
								action="deleteCharacter" method="post">
								<input type="hidden" name="characterID"
									value="${character.characterID}">
								<button type="button" class="delete-button"
									onclick="confirmDelete(${character.characterID}, '${character.firstName} ${character.lastName}')">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
