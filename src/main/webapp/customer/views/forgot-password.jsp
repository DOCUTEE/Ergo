<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>
<h2>Forgot Password</h2>

<form action="${pageContext.request.contextPath}/customer/forgot-password" method="POST">
    <label for="email">Enter your email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <button type="submit">Request OTP</button>
</form>

<p><font color="red">${error}</font></p>

<a href="${pageContext.request.contextPath}/customer/login">Back to Login</a>
</body>
</html>
