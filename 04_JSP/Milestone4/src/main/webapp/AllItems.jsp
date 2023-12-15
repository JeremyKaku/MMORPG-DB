<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Items</title>
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
        
        /* Style for the radio buttons container */
        .radio-toolbar {
            display: inline-block;
            overflow: hidden;
        }

        /* Style for each radio button */
        .radio-toolbar label {
            display: inline-block;
            background-color: #f2f2f2;
            color: #444;
            padding: 8px 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
            border: 1px solid #ccc;
            transition: background-color 0.3s ease;
        }

        /* Style for the checked radio buttons */
        .radio-toolbar input[type="radio"]:checked+label {
            background-color: lightgreen !important;
            color: white;
            border-color: lightgreen;
	        box-shadow: 0 0 5px rgba(0, 0, 255, 0.5);
            border: 2px solid green; /* Add a green border for the selected radio button */
            border-radius: 4px; /* Optional: Add rounded corners to the border */
        }

        /* Style for the radio buttons on hover */
        .radio-toolbar label:hover {
            background-color: lightgreen;
        }

        /* Hide the radio buttons */
        .radio-toolbar input[type="radio"] {
            display: none;
        }
        
        #sortBy {
        padding: 8px;
        border-radius: 4px;
        border: 1px solid #ccc;
        font-size: 16px;
        cursor: pointer;
	    }
	
	    /* Styling when dropdown is open */
	    #sortBy:focus {
	        outline: none;
	        border-color: lightgreen;
	        box-shadow: 0 0 5px rgba(0, 0, 255, 0.5);
	    }
	
	    /* Styling for dropdown options */
	    #sortBy option {
	        background-color: white;
	        color: lightgreen;
    </style>
</head>
<body>
    <div class="container">
        <h2>All Items</h2>
        <form id="categoryForm" class="radio-toolbar">
            <label for="All">All</label>
            <input type="radio" name="category" id="All" value="All">
            
            <label for="Weapons">Weapons</label>
            <input type="radio" name="category" id="Weapons" value="Weapons">
            
            <label for="Gears">Gears</label>
            <input type="radio" name="category" id="Gears" value="Gears">
            
            <label for="Consumables">Consumables</label>
            <input type="radio" name="category" id="Consumables" value="Consumables">
            
            <label for="Miscellaneous">Miscellaneous</label>
            <input type="radio" name="category" id="Miscellaneous" value="Miscellaneous">
        </form>
        <br>
        <script>
		    const radioButtons = document.querySelectorAll('input[type=radio][name=category]');
		    
		    radioButtons.forEach(button => {
		        button.addEventListener('change', function(event) {
		            // Retrieve the value of the clicked radio button
		            const selectedCategory = this.value;
		            console.log(`Selected category: ${selectedCategory}`);
		            
		            // Submit the form when a radio button is selected
		            document.getElementById('categoryForm').submit();
		        });
		    });
		</script>

        <br>
        <label for="sortBy">Sort by:</label>
        <select name="sortBy" id="sortBy" onchange="sortTable()">
            <option value="itemName">Item Name</option>
            <option value="maxStackSize">Max Stack Size</option>
            <option value="vendorPrice">Vendor Price</option>
        </select>
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Max Stack Size</th>
                    <th>Vendor Price</th>
                </tr>
            </thead>
            <tbody id="tableBody">
                <c:forEach var="item" items="${showitems}">
                    <tr>
                        <td>${item.itemName}</td>
                        <td>${item.maxStackSize}</td>
                        <td>${item.vendorPrice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        function sortTable() {
            const sortBy = document.getElementById('sortBy').value;
            const tbody = document.getElementById('tableBody');
            const rows = Array.from(tbody.rows);
            const arr = [];

            rows.forEach(row => {
                const item = {
                    itemName: row.cells[0].textContent.trim(),
                    maxStackSize: parseInt(row.cells[1].textContent.trim(), 10),
                    vendorPrice: parseFloat(row.cells[2].textContent.trim())
                };
                arr.push(item);
            });

            arr.sort((a, b) => {
                // Handle numeric sorting for stack size and vendor price
                if (sortBy === 'maxStackSize' || sortBy === 'vendorPrice') {
                    return a[sortBy] - b[sortBy];
                } else {
                    // Handle alphabetical sorting for item name
                    return a[sortBy].localeCompare(b[sortBy]);
                }
            });

            while (tbody.firstChild) {
                tbody.removeChild(tbody.firstChild);
            }

            arr.forEach(item => {
                const row = tbody.insertRow();
                const cell1 = row.insertCell(0);
                const cell2 = row.insertCell(1);
                const cell3 = row.insertCell(2);

                cell1.textContent = item.itemName;
                cell2.textContent = item.maxStackSize;
                cell3.textContent = item.vendorPrice;
            });
        }
        // On page load, sort by item name
        window.onload = function() {
            sortTable();
        };
    </script>
</body>
</html>

 