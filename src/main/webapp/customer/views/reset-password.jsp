<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password</title>
</head>
<body>
<h2>Reset Your Password</h2>

<form action="${pageContext.request.contextPath}/customer/reset-password" method="POST">
    <label for="password">New Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

    <button type="submit">Reset Password</button>
</form>

<p><font color="red">${error}</font></p>

<a href="${pageContext.request.contextPath}/customer/login">Back to Login</a>
</body>
</html>
