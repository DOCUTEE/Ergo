<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<section id="wsus__product_page" class="wsus__vendor_details_page">
		<div class="container">
			<div class="row">

				<div class="col-xl-3 col-lg-4">
					<div class="wsus__sidebar_filter">
						<p>filter</p>
						<span class="wsus__filter_icon"> <i class="far fa-minus"
							id="minus"></i> <i class="far fa-plus" id="plus"></i>
						</span>
					</div>
					<div class="wsus__product_sidebar" id="sticky_sidebar">
						<div class="accordion" id="accordionExample">
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingOne">
									<button class="accordion-button" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseOne"
										aria-expanded="true" aria-controls="collapseOne">All
										Categories</button>
								</h2>
								<div id="collapseOne" class="accordion-collapse collapse show"
									aria-labelledby="headingOne" data-bs-parent="#accordionExample">
									<div class="accordion-body">
										<ul>
											<c:forEach var="category" items="${categories}">
												<!-- Link đến trang search với tham số categoryName -->
												<li><a
													href="${pageContext.request.contextPath}/products/search?categoryName=${category.categoryName}&page=${sessionScope.currentPage}&keyword=${sessionScope.keyword}&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&pageSize=${sessionScope.pageSize}"
													onclick="console.log('Navigating to:', this.href)"
													<c:if test="${category.categoryName == sessionScope.categoryName}">style="color:#08c"</c:if>>
														${category.categoryName} </a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>

							<c:if test="${not empty sessionScope.selectedColors}">
								<c:set var="colorsAsString"
									value="${fn:join(sessionScope.selectedColors, ',')}"
									scope="session" />
							</c:if>

							<c:if test="${not empty sessionScope.selectedMaterials}">
								<c:set var="materialsAsString"
									value="${fn:join(sessionScope.selectedMaterials, ',')}"
									scope="session" />
							</c:if>

							<c:if test="${not empty sessionScope.selectedHeights}">
								<c:set var="heightsAsString"
									value="${fn:join(sessionScope.selectedHeights, ',')}"
									scope="session" />
							</c:if>

							<c:if test="${not empty sessionScope.selectedLengths}">
								<c:set var="lengthsAsString"
									value="${fn:join(sessionScope.selectedLengths, ',')}"
									scope="session" />
							</c:if>

							<form action="${pageContext.request.contextPath}/products/search"
								method="get">

								<div class="accordion-item">
									<h2 class="accordion-header" id="headingTwo">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse" data-bs-target="#collapseTwo"
											aria-expanded="true" aria-controls="collapseTwo">Price</button>
									</h2>
									<div id="collapseTwo" class="accordion-collapse collapse show"
										aria-labelledby="headingTwo"
										data-bs-parent="#accordionExample">
										<div class="accordion-body">
											<!-- Form lọc theo khoảng giá -->
											<div class="price_range_filter">
												<div class="price_range_inputs">
													<div class="range_input_group">
														<input type="number" class="price_input" id="minPrice"
															name="minPrice" placeholder="Min"
															value="${sessionScope.minPrice != null ? sessionScope.minPrice : ''}">
													</div>
													<div class="range_separator">-</div>
													<div class="range_input_group">
														<input type="number" class="price_input" id="maxPrice"
															name="maxPrice" placeholder="Max"
															value="${sessionScope.maxPrice != null ? sessionScope.maxPrice : ''}">
													</div>
												</div>
												<div class="price_slider_container">
													<!-- Slider sẽ được render ở đây -->
													<div class="price_slider" id="priceSlider"></div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="accordion-item">
									<h2 class="accordion-header" id="headingColor">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse" data-bs-target="#collapseColor"
											aria-expanded="true" aria-controls="collapseColor">Color</button>
									</h2>
									<div id="collapseColor"
										class="accordion-collapse collapse show"
										aria-labelledby="headingColor"
										data-bs-parent="#accordionExample">
										<div class="accordion-body">
											<c:forEach var="color" items="${colors}">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														name="color" value="${color}" id="color-${color}"
														<c:if test="${fn:contains(colorsAsString, color)}">checked</c:if>>
													<label class="form-check-label" for="color-${color}">${color}</label>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>

								<div class="accordion-item">
									<h2 class="accordion-header" id="headingMaterial">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse" data-bs-target="#collapseMaterial"
											aria-expanded="true" aria-controls="collapseMaterial">Material</button>
									</h2>
									<div id="collapseMaterial"
										class="accordion-collapse collapse show"
										aria-labelledby="headingMaterial"
										data-bs-parent="#accordionExample">
										<div class="accordion-body">
											<c:forEach var="material" items="${materials}">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														name="material" value="${material}"
														id="material-${material}"
														<c:if test="${fn:contains(materialsAsString, material)}">checked</c:if>>
													<label class="form-check-label" for="material-${material}">${material}</label>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>

								<div class="accordion-item">
									<h2 class="accordion-header" id="headingHeight">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse" data-bs-target="#collapseHeight"
											aria-expanded="true" aria-controls="collapseHeight">Height</button>
									</h2>
									<div id="collapseHeight"
										class="accordion-collapse collapse show"
										aria-labelledby="headingHeight"
										data-bs-parent="#accordionExample">
										<div class="accordion-body">
											<c:forEach var="height" items="${heights}">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														name="height" value="${height}" id="height-${height}"
														<c:if test="${fn:contains(heightsAsString, height)}">checked</c:if>>
													<label class="form-check-label" for="height-${height}">${height}</label>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								<input type="hidden" name="categoryName"
									value="${sessionScope.categoryName}" /> 
									<input type="hidden"
									name="minPrice" value="${sessionScope.minPrice}" /> <input
									type="hidden" name="maxPrice" value="${sessionScope.maxPrice}" />
								<input type="hidden" name="pageSize"
									value="${sessionScope.pageSize}" /> <input type="hidden"
									name="keyword" value="${sessionScope.keyword}" /> <input
									type="hidden" name="pageSize" value="${sessionScope.pageSize}" />
								<input type="hidden" name="page"
									value="${sessionScope.currentPage}" />
								<div class="accordion-item">
									<h2 class="accordion-header" id="headingLength">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse" data-bs-target="#collapseLength"
											aria-expanded="true" aria-controls="collapseLength">Length</button>
									</h2>
									<div id="collapseLength"
										class="accordion-collapse collapse show"
										aria-labelledby="headingLength"
										data-bs-parent="#accordionExample">
										<div class="accordion-body">
											<c:forEach var="length" items="${lengths}">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														name="length" value="${length}" id="length-${length}"
														<c:if test="${fn:contains(lengthsAsString, length)}">checked</c:if>>
													<label class="form-check-label" for="length-${length}">${length}</label>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>

								<!-- Nút Apply duy nhất -->
								<button type="submit" class="btn btn-primary mt-3">Apply</button>
							</form>

						</div>
					</div>
				</div>
				<div class="col-xl-9 col-lg-8">
					<div class="row">
						<div class="col-xl-12 d-none d-md-block mt-4 mt-lg-0">
							<div class="wsus__product_topbar">
								<div class="wsus__product_topbar_left">
									<div class="nav nav-pills" id="v-pills-tab" role="tablist"
										aria-orientation="vertical"></div>
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
                            const pageSize = document.getElementById('pageSizeSelect').value; 
                            const url = new URL(window.location.href);
                            url.searchParams.set('page', 1);
                            url.searchParams.set('size', pageSize); 
                            window.location.href = url.toString(); 
                        }
                    </script>
								</div>
							</div>
						</div>

						<div class="tab-content" id="v-pills-tabContent"></div>
						<div class="tab-pane fade show active" id="v-pills-home"
							role="tabpanel" aria-labelledby="v-pills-home-tab">
							<div class="row">
								<!-- Hiển thị các sản phẩm tìm kiếm -->
								<c:forEach var="product" items="${products}">
									<div class="col-xl-4 col-sm-6">
										<div class="wsus__product_item">
											<!-- Link dẫn đến trang chi tiết sản phẩm -->
											<a class="wsus__pro_link"
												href="detail?id=${product.productId}"> <!-- Hình ảnh chính của sản phẩm -->
												<img src="${product.productImages[0].productImage}"
												alt="${product.name}" class="img-fluid w-100 img_1" />
											</a>
											<div class="wsus__product_details">
												<!-- Tên sản phẩm -->
												<a class="wsus__pro_name"
													href="detail?id=${product.productId}">${product.name}</a>
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
												<a class="add_cart" href="#">Add to Cart</a>
											</div>
										</div>
									</div>
								</c:forEach>
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
									href="?page=${currentPage - 1}&size=${pageSize}&categoryName=${sessionScope.categoryName}&keyword=${sessionScope.keyword}&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}"
									aria-label="Previous"> <i class="fas fa-chevron-left"></i>
								</a>
								</li>

								<!-- Hiển thị danh sách các số trang -->
								<c:forEach var="pageNum" items="${pageNumbers}">
									<li class="page-item ${pageNum == currentPage ? 'active' : ''}">
										<a class="page-link"
										href="?page=${pageNum}&size=${pageSize}&categoryName=${sessionScope.categoryName}&keyword=${sessionScope.keyword}&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}">${pageNum}</a>
									</li>
								</c:forEach>

								<li
									class="page-item ${currentPage == productPage.totalPages ? 'disabled' : ''}">
									<a class="page-link"
									href="?page=${currentPage + 1}&size=${pageSize}&categoryName=${sessionScope.categoryName}&keyword=${sessionScope.keyword}&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}"
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
document.addEventListener('DOMContentLoaded', function() {
    const priceSlider = document.getElementById('priceSlider');
    const minPriceInput = document.getElementById('minPrice');
    const maxPriceInput = document.getElementById('maxPrice');
    
    let minPrice = 0;
    let maxPrice = 10000000; 
    
    const createThumb = (position) => {
        const thumb = document.createElement('div');
        thumb.className = 'price-slider-thumb';
        thumb.style.left = position + '%';
        priceSlider.appendChild(thumb);
        return thumb;
    };
    
    const leftThumb = createThumb(0);
    const rightThumb = createThumb(100);
    
    const range = document.createElement('div');
    range.className = 'price-slider-range';
    priceSlider.appendChild(range);
    
    const updateSlider = (left, right) => {
        range.style.left = left + '%';
        range.style.width = (right - left) + '%';
        leftThumb.style.left = left + '%';
        rightThumb.style.left = right + '%';
        
        minPriceInput.value = Math.round((maxPrice * left) / 100);
        maxPriceInput.value = Math.round((maxPrice * right) / 100);
    };
    
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
        
        if (isDragging === leftThumb) {
            newLeft = Math.max(0, Math.min(parseFloat(rightThumb.style.left) - 10, newLeft));
            updateSlider(newLeft, parseFloat(rightThumb.style.left));
        } else {
            newLeft = Math.max(parseFloat(leftThumb.style.left) + 10, Math.min(100000000, newLeft));
            updateSlider(parseFloat(leftThumb.style.left), newLeft);
        }
    };
    
    const onMouseUp = () => {
        isDragging = null;
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
    };
    
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
    
    leftThumb.addEventListener('mousedown', (e) => onMouseDown(e, leftThumb));
    rightThumb.addEventListener('mousedown', (e) => onMouseDown(e, rightThumb));
    

});
</script>
</body>