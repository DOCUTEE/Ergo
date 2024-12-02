<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<section id="wsus__product_page" class="wsus__vendor_details_page">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-xl-10 col-lg-11">
					<div class="row">
						<div class="col-xl-12 d-none d-md-block mt-4 mt-lg-0">
							<div class="wsus__product_topbar">
								<div class="wsus__product_topbar_left">
									<div class="nav nav-pills" id="v-pills-tab" role="tablist"
										aria-orientation="vertical">
										<button class="nav-link active" id="v-pills-home-tab"
											data-bs-toggle="pill" data-bs-target="#v-pills-home"
											type="button" role="tab" aria-controls="v-pills-home"
											aria-selected="true">
											<i class="fas fa-th"></i>
										</button>
									</div>
									
								</div>
								<div class="wsus__topbar_select">
									<select class="select_2" name="pageSize" id="pageSizeSelect"
										onchange="updatePageSize()">
										<option value="12" ${pageSize == 12 ? 'selected' : ''}>Show
											12</option>
										<option value="15" ${pageSize == 15 ? 'selected' : ''}>Show
											15</option>
									</select>

									<script>
										function updatePageSize() {
											const pageSize = document
													.getElementById('pageSizeSelect').value; // Lấy giá trị mới
											const url = new URL(
													window.location.href);
											url.searchParams.set('page', 1); // Đặt về trang đầu tiên
											url.searchParams.set('size',
													pageSize); // Cập nhật số lượng sản phẩm
											window.location.href = url
													.toString(); // Cập nhật URL
										}
									</script>

								</div>
							</div>
						</div>

						<div class="tab-content" id="v-pills-tabContent">
						
							<!-- Nội dung Tab 1 -->
							<div class="tab-pane fade show active" id="v-pills-home"
								role="tabpanel" aria-labelledby="v-pills-home-tab">
								<div class="row">
									<c:forEach var="product" items="${products}">
									
										<div class="col-xl-4 col-sm-6">
											<div class="wsus__product_item">
												<!-- Link dẫn đến trang chi tiết sản phẩm -->
												<a class="wsus__pro_link" href="products/detail?id=${product.productId}"> <!-- Hình ảnh chính của sản phẩm -->
												<img src="${product.productImages[0].productImage}"
												alt="${product.name}" class="img-fluid w-100 img_1" /> <!-- Hình ảnh phụ nếu có -->
												</a>
												<div class="wsus__product_details">
													<!-- Tên sản phẩm -->
													<a class="wsus__pro_name"
														href="products/detail?id=${product.productId}">${product.name}</a>
													<!-- Giá sản phẩm -->
													<p class="wsus__price">
														<c:if
															test="${not empty product.productTypes && product.productTypes[0] != null}">
        ${product.productTypes[0].price}
    </c:if>
														<c:if
															test="${empty product.productTypes || product.productTypes[0] == null}">
        Price Not Available
    </c:if>
													</p>
													<!-- Nút thêm vào giỏ hàng -->
													<a class="add_cart" href="#">add to cart</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-12">
			<section id="pagination">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
							<a class="page-link"
							href="?page=${currentPage - 1}&size=${pageSize}"
							aria-label="Previous"> <i class="fas fa-chevron-left"></i>
						</a>
						</li>

						<!-- Hiển thị danh sách các số trang -->
						<c:forEach var="pageNum" items="${pageNumbers}">
							<li class="page-item ${pageNum == currentPage ? 'active' : ''}">
								<a class="page-link" href="?page=${pageNum}&size=${pageSize}">${pageNum}</a>
							</li>
						</c:forEach>

						<li
							class="page-item ${currentPage == productPage.totalPages ? 'disabled' : ''}">
							<a class="page-link"
							href="?page=${currentPage + 1}&size=${pageSize}"
							aria-label="Next"> <i class="fas fa-chevron-right"></i>
						</a>
						</li>
					</ul>
					<div class="text-center mt-3">
						<span>Page ${currentPage} of ${productPage.totalPages}</span>
					</div>
				</nav>
			</section>
		</div>

		</div>
		</div>
	</section>
	<!--============================
       VENDORS DETAILA END
    ==============================-->
<script>
//Thêm script này vào cuối file hoặc trong một file JS riêng
document.addEventListener('DOMContentLoaded', function() {
    const priceSlider = document.getElementById('priceSlider');
    const minPriceInput = document.getElementById('minPrice');
    const maxPriceInput = document.getElementById('maxPrice');
    
    // Khởi tạo giá trị
    let minPrice = 0;
    let maxPrice = 1000; // Giá trị tối đa mặc định
    
    // Tạo hai thumb cho slider
    const createThumb = (position) => {
        const thumb = document.createElement('div');
        thumb.className = 'price-slider-thumb';
        thumb.style.left = position + '%';
        priceSlider.appendChild(thumb);
        return thumb;
    };
    
    const leftThumb = createThumb(0);
    const rightThumb = createThumb(100);
    
    // Tạo range track
    const range = document.createElement('div');
    range.className = 'price-slider-range';
    priceSlider.appendChild(range);
    
    // Cập nhật giá trị và vị trí của slider
    const updateSlider = (left, right) => {
        range.style.left = left + '%';
        range.style.width = (right - left) + '%';
        leftThumb.style.left = left + '%';
        rightThumb.style.left = right + '%';
        
        // Cập nhật input fields
        minPriceInput.value = Math.round((maxPrice * left) / 100);
        maxPriceInput.value = Math.round((maxPrice * right) / 100);
    };
    
    // Xử lý kéo thumb
    let isDragging = null;
    let startX = 0;
    let startLeft = 0;
    
    const onMouseDown = (e, thumb) => {
        isDragging = thumb;
        startX = e.clientX;
        startLeft = parseFloat(thumb.style.left);
        document.addEventListener('mousemove', onMouseMove);
        document.addEventListener('mouseup', onMouseUp);
    };
    
    const onMouseMove = (e) => {
        if (!isDragging) return;
        
        const deltaX = e.clientX - startX;
        const deltaPercent = (deltaX / priceSlider.offsetWidth) * 100;
        let newLeft = startLeft + deltaPercent;
        
        // Giới hạn phạm vi di chuyển
        if (isDragging === leftThumb) {
            newLeft = Math.max(0, Math.min(parseFloat(rightThumb.style.left) - 10, newLeft));
            updateSlider(newLeft, parseFloat(rightThumb.style.left));
        } else {
            newLeft = Math.max(parseFloat(leftThumb.style.left) + 10, Math.min(100, newLeft));
            updateSlider(parseFloat(leftThumb.style.left), newLeft);
        }
    };
    
    const onMouseUp = () => {
        isDragging = null;
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
    };
    
    // Xử lý input thay đổi
    minPriceInput.addEventListener('change', () => {
        const value = Math.max(0, Math.min(parseInt(maxPriceInput.value) - 10, parseInt(minPriceInput.value)));
        const percent = (value / maxPrice) * 100;
        updateSlider(percent, parseFloat(rightThumb.style.left));
    });
    
    maxPriceInput.addEventListener('change', () => {
        const value = Math.max(parseInt(minPriceInput.value) + 10, Math.min(maxPrice, parseInt(maxPriceInput.value)));
        const percent = (value / maxPrice) * 100;
        updateSlider(parseFloat(leftThumb.style.left), percent);
    });
    
    // Thêm event listeners cho thumbs
    leftThumb.addEventListener('mousedown', (e) => onMouseDown(e, leftThumb));
    rightThumb.addEventListener('mousedown', (e) => onMouseDown(e, rightThumb));
    
    // Khởi tạo slider
    updateSlider(0, 100);
});
</script>
</body>
