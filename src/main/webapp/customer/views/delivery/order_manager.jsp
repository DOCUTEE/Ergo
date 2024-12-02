<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang Quản Lý Đơn Hàng</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="order-page">
		<!-- Tabs for different order statuses -->
		<div class="tabs">
			<a
				href="${pageContext.request.contextPath}/customer/managerorder?status=all"
				class="tab <c:if test="${empty param.status or param.status == 'all'}">active</c:if>">Tất
				cả</a> <a
				href="${pageContext.request.contextPath}/customer/managerorder?status=Đã đặt hàng"
				class="tab <c:if test="${param.status == 'Đã đặt hàng'}">active</c:if>">Đã
				đặt hàng</a> <a
				href="${pageContext.request.contextPath}/customer/managerorder?status=Đang vận chuyển"
				class="tab <c:if test="${param.status == 'Đang vận chuyển'}">active</c:if>">Vận
				chuyển</a> <a
				href="${pageContext.request.contextPath}/customer/managerorder?status=Chờ giao hàng"
				class="tab <c:if test="${param.status == 'Chờ giao hàng'}">active</c:if>">Chờ
				giao hàng</a> <a
				href="${pageContext.request.contextPath}/customer/managerorder?status=Hoàn thành"
				class="tab <c:if test="${param.status == 'Hoàn thành'}">active</c:if>">Hoàn
				thành</a> <a
				href="${pageContext.request.contextPath}/customer/managerorder?status=Đã hủy"
				class="tab <c:if test="${param.status == 'Đã hủy'}">active</c:if>">Đã
				hủy</a>
		</div>

		<%-- <!-- Search bar for filtering orders -->
		<form method="get"
			action="${pageContext.request.contextPath}/customer/managerorder">
			<div class="search-bar">
				<input type="text" id="searchInput" name="productName"
					placeholder="Tìm kiếm Tên Sản phẩm" value="${param.productName}">
			</div>
		</form>
 --%>

		<!-- List of orders -->
		<div class="order-list">
			<c:forEach var="order" items="${orders}">
				<div class="order-card">
					<!-- Order Header -->
					<div class="order-header">
						<p class="order-status">${order.status}</p>
						<div class="total-price">
							<span>Thành tiền:</span> <span class="price">${order.totalCost}₫</span>
						</div>
						<div class="order-date">
							<span>Ngày Đặt:</span>
							<fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy" />
						</div>
					</div>

					<div class="order-items">
						<!-- Hiển thị orderItems tìm kiếm nếu có -->
						<c:forEach var="item"
							items="${orderItems != null ? orderItems : order.orderItems}">
							<div class="order-item">
								<div class="product-info">
									<div class="col">
										<c:forEach var="image"
											items="${item.productType.product.productImages}">
											<div class="product-image">
												<img src="${image.productImage}"
													alt="Image of ${item.productType.product.name}" />
											</div>
										</c:forEach>
									</div>
									<div class="col product-details">
										<p>Tên sản phẩm: ${item.productType.product.name}</p>
										<p>Màu sắc: ${item.productType.color}</p>
										<p>Kích thước: ${item.productType.length} x
											${item.productType.width} x ${item.productType.height}</p>
										<p>Giá: ${item.productType.price}₫</p>
										<p>Số lượng: ${item.quantity}</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<!-- Order Footer -->
					<div class="order-footer">
						<button class="review-btn">Đánh Giá</button>
						<button class="buy-again-btn">Mua Lại</button>
					</div>
				</div>
			</c:forEach>
		</div>

		<script>
        document.addEventListener('DOMContentLoaded', function() {
            const searchInput = document.getElementById('searchInput');
            const orderCards = document.querySelectorAll('.order-card');
            searchInput.addEventListener('input', function() {
                const query = searchInput.value.toLowerCase();
                orderCards.forEach(card => {
                    const orderId = card.querySelector('.order-id') ? card.querySelector('.order-id').textContent.toLowerCase() : '';
                    const orderStatus = card.querySelector('.order-status') ? card.querySelector('.order-status').textContent.toLowerCase() : '';
                    const orderDate = card.querySelector('.order-date') ? card.querySelector('.order-date').textContent.toLowerCase() : '';
                    if (orderId.includes(query) || orderStatus.includes(query) || orderDate.includes(query)) {
                        card.style.display = 'block';
                    } else {
                        card.style.display = 'none';
                    }
                });
            });
        });
    </script>
</body>
</html>
