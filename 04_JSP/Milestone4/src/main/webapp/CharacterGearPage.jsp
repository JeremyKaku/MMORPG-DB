<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CharacterGear</title>
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
	cursor: pointer;
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
		<img src="../images/go-back-icon.png" title="Back To Character"
			class='back-image' onclick="goBack()" alt="Back Icon">
		<h2>${gear[0].character.firstName}
			${gear[0].character.lastName}'s Gear Set</h2>
		<h3>Total: ${total} / 12</h3>
		<input type="text" id="myInput" oninput="filterTable()"
			placeholder="Filter By Content">

		<table id="myTable">
			<thead>
				<tr>
					<th>Gear Slot</th>
					<th>Gear Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="gear" items="${gear}">
					<tr>
						<td>${gear.gearSlot.gearSlotName}</td>
						<td><a
							title="
Gear Name: ${gear.gear.itemName}
MaxStackSize: ${gear.gear.maxStackSize}
VendorPrice: ${gear.gear.vendorPrice}
ItemLevel: ${gear.gear.itemLevel}
RequiredLevel: ${gear.gear.requiredLevel}
DefenseRating: ${gear.gear.defenseRating}
MagicDefenseRating: ${gear.gear.magicDefenseRating}
">${gear.gear.itemName}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="../script.js"></script>
</body>
</html>

