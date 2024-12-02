<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <!-- Nhúng jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Nhúng Bootstrap nếu cần -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Order History</h1>
    <!-- Hiển thị Toast -->
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
        <div id="toastSuccess" class="toast bg-success text-white" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-body">
                Review submitted successfully!
            </div>
        </div>
        <div id="toastError" class="toast bg-danger text-black" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-body">
                Failed to submit review. Please try again.
            </div>
        </div>
    </div>
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Status</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty orderHistory}">
            <c:forEach var="order" items="${orderHistory}">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.status}</td>
                    <td>${order.totalCost}</td>
                    <td>
                        <c:if test="${not empty order.orderItems}">
<%--                            <button class="btn btn-dark btn-sm detail-btn"--%>
<%--                                    data-bs-toggle="modal"--%>
<%--                                    data-bs-target="#detailModal${order.orderId}"--%>
<%--                                    data-id="${order.orderId}"--%>
<%--                            >--%>
<%--                                Detail--%>
<%--                            </button>--%>
                            <a href="${pageContext.request.contextPath}/order-items?orderId=${order.orderId}" class="btn btn-dark btn-sm detail-btn">
                                Detail
                            </a>
                        </c:if>
                        <c:if test="${empty order.orderItems}">
                            <span>No items available</span>
                        </c:if>


                    </td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <!-- Pagination -->
<%--    <nav>--%>
<%--        <ul class="pagination">--%>
<%--            <c:forEach var="i" begin="1" end="${totalPages}">--%>
<%--                <li class="page-item ${i == currentPage ? 'active' : ''}">--%>
<%--                    <a class="page-link" href="${pageContext.request.contextPath}/admin/customer?page=${i}">${i}</a>--%>
<%--                </li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </nav>--%>

    <!-- Modal Review -->
    <div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="reviewModalLabel">Review Product</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/review/add" method="post">
                        <input type="hidden" id="reviewOrderId" name="orderId">
                        <input type="hidden" id="reviewProductId" name="productId">
                        <div class="mb-3">
                            <label for="content" class="form-label">Review Content</label>
                            <textarea class="form-control" id="content" name="content" rows="3" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="rating" class="form-label">Rating</label>
                            <select class="form-control" id="rating" name="rating" required>
                                <option value="5">5 - Excellent</option>
                                <option value="4">4 - Good</option>
                                <option value="3">3 - Average</option>
                                <option value="2">2 - Poor</option>
                                <option value="1">1 - Very Poor</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit Review</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="detailModalLabel">Order Detail</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered">
                        <thead class="table-dark">
                        <tr>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody id="orderItemDetails">
                        <c:if test="${not empty orderItems}">
                            <c:forEach var="item" items="${orderItems}">
                                <tr>
                                    <td>
                                        <div class="d-flex align-items-center" >
                                            <c:choose>
                                                    <c:when test="${not empty item.productType.product.productImages[0].productImage}">
                                                        <img src="${item.productType.product.productImages[0].productImage}"
                                                             alt=""
                                                             class="img-thumbnail me-2"
                                                             style="width: 100px; height: 100px; object-fit: cover;">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="https://via.placeholder.com/100"
                                                             alt=""
                                                             class="img-thumbnail me-2"
                                                             style="width: 100px; height: 100px; object-fit: cover;">
                                                    </c:otherwise>
                                            </c:choose>
                                            <span>${item.productType.product.name}</span>
                                        </div>
                                    </td>
                                    <td>${item.quantity}</td>
                                    <td>${item.price}</td>
                                    <td>
                                        <c:if test="${not empty orderHistory}">
                                            <c:forEach var="order" items="${orderHistory}">
                                                <c:choose>
                                                    <c:when test="${currentOrderId == order.orderId and order.status == 'Delivered'}">
                                                        <!-- Nút Review chỉ hiển thị nếu currentOrderId khớp với orderId -->
                                                        <button class="btn btn-warning btn-sm review-btn"
                                                                data-bs-toggle="modal"
                                                                data-bs-target="#reviewModal"
                                                                data-order-id="${order.orderId}"
                                                                data-order-date="${order.orderDate}"
                                                                data-order-status="${order.status}"
                                                                data-product-id="${item.productType.product.productId}">
                                                            Review
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <!-- Disable Review button -->
                                                        <button class="btn btn-secondary btn-sm" disabled>Review</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${ empty order.orderItems}">
                            <h3>No item</h3>
                        </c:if>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const urlParams = new URLSearchParams(window.location.search);
            const reviewStatus = urlParams.get('reviewStatus');

            // Hiển thị toast thông báo
            if (reviewStatus === 'success') {
                const toast = new bootstrap.Toast(document.getElementById('toastSuccess'));
                toast.show();
            } else if (reviewStatus === 'error') {
                const toast = new bootstrap.Toast(document.getElementById('toastError'));
                toast.show();
            }

            // Gán giá trị vào modal khi nhấn Review
            const reviewModal = document.getElementById('reviewModal');
            reviewModal.addEventListener('show.bs.modal', function (event) {
                const button = event.relatedTarget;
                document.getElementById('reviewOrderId').value = button.getAttribute('data-order-id');
                document.getElementById('reviewProductId').value = button.getAttribute('data-product-id');
            });
            // Gán giá trị vào modal khi nhấn Review
            const detailModal = document.querySelectorAll('.detail-btn');
            detailModal.addEventListener('show.bs.modal', function (event) {
                const button = event.relatedTarget; // Lấy nút được nhấn


            });
        });
        document.addEventListener('DOMContentLoaded', function () {
            const currentOrderId = "${currentOrderId}";
            if (currentOrderId) {
                const detailModal = new bootstrap.Modal(document.getElementById('detailModal'));
                detailModal.show();
            }
        });
    </script>
</div>
</body>
</html>
