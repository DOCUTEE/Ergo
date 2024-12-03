<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Warning</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure about deleting all items?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button id="remo" type="button" class="btn btn-primary removeAllProductBtn" data-bs-dismiss="modal">Sure</button>
            </div>
        </div>
    </div>
</div>
<div class="container d-flex justify-content-end gap-3"><a type="button" href="<%= request.getContextPath() %>/order/?${cartId}" class="checkoutBtn btn btn-warning">Check out</a><button type="button"
                                                                                                                                                                                             class="subProductBtn btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Delete all</button></div>
<div class="container d-flex justify-content-center">
    <c:choose>
        <c:when test="${cartItems != null && cartItems.size() > 0}">
            <table id="table-cart" class="table table-striped">
                <thead>
                <tr class="table-primary">
                    <th class="text-center">Name</th>
                    <th class="text-center">Preview</th>
                    <th class="text-center">Quantity</th>
                    <th class="text-center">Unit price</th>
                    <th class="text-center">Total price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cartItem" items="${cartItems}">
                    <tr class="cart-item  ${(cartItem.getProductType().getQuantity() <= 0) ? 'opacity-50 cart-item--out-stock' : ''}" cartItemId="${cartItem.getProductType().getProduct().getProductId()}">
                        <td class="align-content-center text-center"><div
                                class="h-100 d-flex justify-content-center align-items-center gap-1"><input class="form-check-input mt-0" type="checkbox" value=""><label>${cartItem.getProductType().getProduct().getName()}</label></div></td>
                        <td class="align-content-center text-center"><img width="200" height="200" class="img-thumbnail rounded" src="${cartItem.getProductType().getProduct().getProductImages().get(0).getProductImage()}" alt="preview"/></td>
                        <td class="align-content-center text-center">
                            <button type="button" class="subProductBtn btn btn-primary bold">-</button>
                                <input class="w-25 text-center quantity-input" value="${cartItem.getQuantity()}">
                            <button type="button" class="addProductBtn btn btn-primary bold">+</button>
                        </td>
                        <td class="align-content-center text-center"><fmt:formatNumber value="${cartItem.getProductType().getPrice()}" type="currency" currencySymbol="₫"/></td>
                        <td class="align-content-center text-center"><fmt:formatNumber value="${cartItem.getProductType().getPrice() * cartItem.getQuantity()}" type="currency" currencySymbol="₫"/> <button type="button" class="btn btn-danger end-0 removeProductBtn">X</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="cart-empty p-5">
                <p>${message}</p>
                <p class="text-center"><a href="<%= request.getContextPath() %>/shop" class="btn-back-to-shop">Shopping now!</a></p>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
