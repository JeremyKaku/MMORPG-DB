<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Gears</title>
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
    </style>
</head>
<body>
    <div class="container">
        <h2>All Currencies</h2>
        <table>
        <thead>
            <tr>
                <th>Currency Name</th>
                <th>Maximum Amount</th>
                <th>Weekly Cap</th>
                <th>Availability</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${currencies}" var="currency">
                <tr>
                    <td><c:out value="${currency.currencyName}" /></td>
                    <td><c:out value="${currency.maximumAmount}" /></td>
                    <td><c:out value="${currency.weeklyCap}" /></td>
                    <td><c:out value="${currency.availability}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html>
