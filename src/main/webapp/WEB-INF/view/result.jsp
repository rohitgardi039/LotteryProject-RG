<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<html>
<head>
    <title>Lottery App - Result</title>
</head>
<body>
    <h1>Lottery Result:</h1>
    
    <% boolean isWinner = (boolean) request.getAttribute("isWinner"); %>
    <% if (isWinner) { %>
        <p>Congratulations! You are a winner!</p>
    <% } else { %>
        <p>Sorry! You are not a winner.</p>
    <% } %>
    
    <a href="index.jsp">Go Back</a>
</body>
</html>