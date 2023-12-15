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
<title>Inventory</title>
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
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	/* cursor: pointer; */
	position: sticky;
}

a {
	text-decoration: none;
	color: #2196F3;
	font-weight: bold;
	cursor: pointer;
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
		<img src="../images/go-back-icon.png" title="Back To Character"
			class='back-image' onclick="goBack()" alt="Back Icon">
		<h2>${inventory[0].character.firstName}
			${inventory[0].character.lastName}'s Inventory</h2>
		<h3>Total: ${total} / 144</h3>
		<input type="text" id="myInput" oninput="filterTable()"
			placeholder="Filter By Content">
		<table id="myTable">
			<thead>
				<tr>
					<th onclick="sortTable(0)">Inventory Slot</th>
					<th onclick="sortTable(1)">Item Name</th>
					<th onclick="sortTable(2)">Quantity</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="inventory" items="${inventory}">
					<tr>
						<td>No.${inventory.inventorySlotId}</td>
						<td><a
							title="
Item Name: ${inventory.item.itemName}
MaxStackSize: ${inventory.item.maxStackSize}
VendorPrice: ${inventory.item.vendorPrice}
						">${inventory.item.itemName}</a></td>
						<td>${inventory.quantity}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="../script.js">
		/* 	function filterTable() {
				var input, filter, table, tr, td, i, txtValue;
				input = document.getElementById("myInput");
				filter = input.value.toUpperCase();
				table = document.getElementById("myTable");
				tr = table.getElementsByTagName("tr");

				for (i = 0; i < tr.length; i++) {
					
					var td =  tr[i].getElementsByTagName("td");				
					for (j = 0; j < td.length; j++) 
					{
						tr[i].style.display = "none";
						if (td[j])
						{
							txtValue = td[j].textContent ||  td[j].innerText;
							if(txtValue.toUpperCase().indexOf(filter) > -1)
							{
								tr[i].style.display = "";
								break;
							}
						}
						
					}
				}
			} */
		/* 
		 function sortTable(columnIndex) {
		 var table, rows, switching, i, x, y, shouldSwitch;
		 table = document.getElementById("myTable");
		 switching = true;

		 while (switching) {
		 switching = false;
		 rows = table.rows;

		 for (i = 1; i < rows.length - 1; i++) {
		 shouldSwitch = false;
		 x = rows[i].getElementsByTagName("td")[columnIndex];
		 y = rows[i + 1].getElementsByTagName("td")[columnIndex];

		 if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
		 shouldSwitch = true;
		 break;
		 }
		 }

		 if (shouldSwitch) {
		 rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
		 switching = true;
		 }
		 }
		 flg =!flg;
		 } */
	</script>
</body>
</html>

