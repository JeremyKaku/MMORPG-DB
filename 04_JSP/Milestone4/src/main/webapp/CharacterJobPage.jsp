<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Locale"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Character Attributes</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
}

.container {
	max-width: 800px;
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

.back-image {
	cursor: pointer;
	width: 50px;
	height: 50px;
	left: 10px;
	top: 10px;
}

.back-image:hover::after {
	display: block;
	position: absolute;
	background-color: #000;
	color: #fff;
	padding: 5px;
	border-radius: 5px;
	left: 20px;
	top: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<img src="../images/go-back-icon.png" title="Back To Player Dashboard"
			class='back-image' onclick="goBack()" alt="Back Icon">
		<h2>${characterJobs[0].character.firstName}
			${characterJobs[0].character.lastName}'s Job</h2>
		<input type="text" id="myInput" oninput="filterTable()"
			placeholder="Filter By Content">

		<table id="myTable">
			<thead>
				<tr>
					<th>Job Name</th>
					<th>Level</th>
					<th>EXP</th>
					<th>Current Job</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="characterJob" items="${dataList}">
					<tr>
						<td>${characterJob.jobName}</td>
						<td>${characterJob.jobLevel}</td>
						<td>${characterJob.experiencePoint}</td>
						<td>${characterJob.currentJob}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="../script.js"></script>
</body>
</html>

