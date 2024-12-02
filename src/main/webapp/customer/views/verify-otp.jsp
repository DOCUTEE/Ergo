<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verify OTP</title>
</head>
<body>
<h2>Verify OTP</h2>

<form action="${pageContext.request.contextPath}/customer/verify-otp" method="POST">
    <label for="otp">Enter OTP:</label>
    <input type="text" id="otp" name="otp" required><br><br>

    <button type="submit">Verify OTP</button>
</form>

<p><font color="red">${error}</font></p>

<a href="${pageContext.request.contextPath}/customer/forgot-password">Back to Forgot Password</a>
</body>
</html>
