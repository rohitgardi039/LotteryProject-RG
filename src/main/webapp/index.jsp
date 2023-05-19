<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lottery App</title>
</head>
<body>
    <h1>Welcome to the Lottery App!</h1>
    
    <form action="lottery" method="post">
        <label for="numbers">Enter lottery numbers (comma-separated):</label><br>
        <input type="text" id="numbers" name="numbers" required><br>
        <button type="submit">Save Numbers</button>
    </form>
    
    <h2>Check Lottery Number</h2>
    
    <form action="lottery" method="get">
        <label for="number">Enter lottery number:</label><br>
        <input type="text" id="number" name="number" required><br>
        <button type="submit">Check</button>
    </form>
</body>