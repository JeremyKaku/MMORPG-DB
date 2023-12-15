function goBack() {
	window.history.back();
}

function redirect(id,url) {
	var form = document.createElement("form");
	form.method = "post";
	form.action = url;

	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "characterID";
	input.value = id;

	form.appendChild(input);

	document.body.appendChild(form);
	form.submit();
}

function redirectToPageInventory(id) {
	var form = document.createElement("form");
	form.method = "post";
	form.action = "/FinalFinal/character/inventory";

	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "characterID";
	input.value = id;

	form.appendChild(input);

	document.body.appendChild(form);
	form.submit();
}

function redirectToPageCharacterGear(id) {
	var form = document.createElement("form");
	form.method = "post";
	form.action = "/FinalFinal/character/gear";

	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "characterID";
	input.value = id;

	form.appendChild(input);

	document.body.appendChild(form);
	form.submit();
}


function redirectToPageCharacterAttribute(id) {
	var form = document.createElement("form");
	form.method = "post";
	form.action = "/FinalFinal/character/characterAttributes";

	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "characterID";
	input.value = id;

	form.appendChild(input);

	document.body.appendChild(form);
	form.submit();
}

function filterTable() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");

	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function filterTable() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");

	for (i = 0; i < tr.length; i++) {

		var td = tr[i].getElementsByTagName("td");
		for (j = 0; j < td.length; j++) {
			tr[i].style.display = "none";
			if (td[j]) {
				txtValue = td[j].textContent || td[j].innerText;
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
					break;
				}
			}

		}
	}
}
/*window.showWeaponInfo = function() {
	var weaponInfo = '<%= weaponInfo %>';

	alert(weaponInfo);
}
*/