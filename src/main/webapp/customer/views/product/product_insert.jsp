<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert New Product</title>
</head>
<body>
    <h1>Insert New Product</h1>

    <!-- Thông báo thành công hoặc lỗi -->
    <c:if test="${not empty successMessage}">
        <div style="color: green;">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>
    <!-- Form thêm sản phẩm -->
    <form action="<%= request.getContextPath() %>/products/insert" method="post">
    <label for="name">Product Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="descript">Description:</label>
    <textarea id="descript" name="descript" required></textarea><br><br>

    <label for="categoryId">Category ID:</label>
    <input type="number" id="categoryId" name="categoryId" required><br><br>

    <!-- ProductType Fields -->
    <label for="color">Color:</label>
    <input type="text" id="color" name="color"><br><br>

    <label for="length">Length:</label>
    <input type="number" id="length" name="length" step="0.01"><br><br>

    <label for="width">Width:</label>
    <input type="number" id="width" name="width" step="0.01"><br><br>

    <label for="height">Height:</label>
    <input type="number" id="height" name="height" step="0.01"><br><br>

    <label for="weight">Weight:</label>
    <input type="number" id="weight" name="weight" step="0.01"><br><br>

    <label for="material">Material:</label>
    <input type="text" id="material" name="material"><br><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" step="0.01" required><br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br><br>

    <label for="imageUrl">Product Image URL:</label>
    <input type="text" id="imageUrl" name="imageUrl" required><br><br>

    <button type="submit">Add Product</button>
</form>

</body>
</html>
