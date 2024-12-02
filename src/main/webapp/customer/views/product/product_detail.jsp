<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
</head>
<body>
	<section class="product-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product__details__pic">
						<div class="product__details__pic__item">
							<img class="product__details__pic__item--large"
								src="${product.productImages[0].productImage}" alt="">
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="product__details__text">
						<h3>${product.name}</h3>
						<div class="product__details__price">${product.productTypes[0].price}</div>
						<p>${product.descript != null ? product.descript : "Không có mô tả"}</p>
						<p>${product.category != null ? product.category.categoryName : 'Chưa có danh mục'}</p>
						<div class="product__details__quantity">
							<div class="quantity">
								<button class="qty-btn minus" onclick="decrementQuantity()">-</button>
								<input id="count" type="text"
									value="${product.productTypes[0].quantity}" readonly>
								<button class="qty-btn plus" onclick="incrementQuantity()">+</button>
							</div>
						</div>

						<script>
						    function incrementQuantity() {
						        const countInput = document.getElementById("count");
						        let value = parseInt(countInput.value, 10);
						        if (!isNaN(value)) {
						            countInput.value = value + 1; // Tăng số lượng
						        }
						    }
						
						    function decrementQuantity() {
						        const countInput = document.getElementById("count");
						        let value = parseInt(countInput.value, 10);
						        if (!isNaN(value) && value > 1) {
						            countInput.value = value - 1; // Giảm số lượng (không cho < 1)
						        }
						    }
						</script>

						<%-- <button class="primary-btn" onclick="submitForm(${product.productId})">THÊM VÀO GIỎ</button> --%>

						<a onclick="submitForm(${product.productId}, ${check})" href="#"
							class="primary-btn">THÊM VÀO GIỎ</a> <a href="#" id="heart-icon"
							class="heart-icon" onclick="updateFavorite(${product.productId})">
							<i id="heart-icon-element" class="fa-regular fa-heart"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="product__details__tab">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">
									Mô tả </a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-2" role="tab" aria-selected="false"> Thông tin </a>
							</li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-3" role="tab" aria-selected="false"> Đánh giá </a></li>
						</ul>

						<div class="tab-content">
							<div class="tab-pane active" id="tabs-1" role="tabpanel">
								<div class="product__details__tab__desc">
									<h6>MÔ TẢ SẢN PHẨM</h6>
									<p>${product.descript}</p>
								</div>
							</div>
							<div class="tab-pane" id="tabs-2" role="tabpanel">
								<div class="product__details__tab__desc">
									<h6>THÔNG TIN SẢN PHẨM</h6>
									<c:forEach var="type" items="${product.productTypes}">
										<div class="product-type">
											<div class="product-type-card-body">
												<p>
													<strong>Color:</strong> ${type.color}
												</p>
												<p>
													<strong>Material:</strong> ${type.material}
												</p>
												<p>
													<strong>Dimensions:</strong> ${type.length} x ${type.width}
													x ${type.height}
												</p>
												<p>
													<strong>Weight:</strong> ${type.weight}
												</p>
												<p>
													<strong>Price:</strong> ${type.price}
												</p>
												<p>
													<strong>Quantity:</strong> ${type.quantity}
												</p>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="tab-pane" id="tabs-3" role="tabpanel">
								<div class="product__details__tab__desc">
									<h6>ĐÁNH GIÁ SẢN PHẨM</h6>
									<div class="reviews">
										<c:if test="${not empty reviews}">
											<ul>
												<c:forEach var="review" items="${reviews}">
													<li><strong>${review.customer.name}</strong> - <span>${review.rating}
															stars</span>
														<p>${review.content}</p> <small>Reviewed on:
															${review.createAt}</small></li>
												</c:forEach>
											</ul>
										</c:if>
										<c:if test="${empty reviews}">
											<p>No reviews for this product yet.</p>
										</c:if>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Details Section End -->
	<!-- Related Product Section Begin -->
	<section class="related-product">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title related__product__title">
						<h2>Sản phẩm liên quan</h2>
					</div>
				</div>
			</div>

			<div class="row">
				<!-- Hiển thị sản phẩm liên quan -->
				<c:forEach var="product" items="${relatedProducts}">
					<div class="col-lg-3 col-md-4 col-sm-6">
						<div class="product__item">
							<div class="product__item__pic">

								<img src="${product.productImages[0].productImage}"
									alt="${product.name}">
								<ul class="product__item__pic__hover">
									<li><a href="#"
										onclick="updateFavorite(${product.productId})"> <i
											class="fa fa-heart"></i>
									</a></li>
									<li><a href="productdetail?id=${product.productId}"> <i
											class="fa fa-search"></i>
									</a></li>
									<li><a href="#"
										onclick="submitForm(${product.productId}, ${check})"> <i
											class="fa fa-shopping-cart"></i>
									</a></li>
								</ul>
							</div>
							<div class="product__item__text">
								<h6>
									<a href="productdetail?id=${product.productId}">${product.name}</a>
								</h6>
								<h5>${product.productTypes[0].price}</h5>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<!-- Phân trang -->
			<div class="row">
				<div class="flex-c-m flex-w w-full p-t-38">
					<!-- Nút phân trang đầu tiên -->
					<c:if test="${currentPage > 1}">
						<button onclick="changePage(1)"
							class="flex-c-m how-pagination1 trans-04 m-all-7">First</button>
					</c:if>

					<!-- Các nút phân trang giữa -->
					<c:forEach begin="1" end="${totalPages}" varStatus="loop">
						<button onclick="changePage('${loop.index}')"
							class="flex-c-m how-pagination1 trans-04 m-all-7 ${currentPage == loop.index ? 'active-pagination1' : ''}">
							${loop.index}</button>
					</c:forEach>

					<!-- Nút phân trang cuối cùng -->
					<c:if test="${currentPage < totalPages}">
						<button onclick="changePage(${totalPages})"
							class="flex-c-m how-pagination1 trans-04 m-all-7">Last</button>
					</c:if>
				</div>
			</div>
		</div>
	</section>

	<script>
		function changePage(pageNumber) {
			const urlParams = new URLSearchParams(window.location.search);
			urlParams.set('page', pageNumber);  // Cập nhật tham số 'page' trong URL
			window.location.search = urlParams.toString();  // Reload trang với số trang mới
		}
	</script>
</body>
</html>
