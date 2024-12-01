<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Product Management</h1>


    <style>
        .btn-group { display: flex; gap: 5px; }
    </style>
    <!-- Product List Table -->
    <table class="table table-bordered table-hover mt-3">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Images</th>
            <th>Description</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Using c:forEach to iterate through the product list -->
        <c:forEach var="product" items="${listProduct}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.name}</td>
                <td>
                    <c:forEach var="image" items="${product.productImages}">
                        <img src="../product-images/${image.productImage}" alt="Product Image" width="50" height="50"/>
                    </c:forEach>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${product.descript != null}">
                            ${product.descript}
                        </c:when>
                        <c:otherwise>
                            No description available
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><c:choose>
                        <c:when test="${product.category != null}">
                            ${product.category.categoryName}
                        </c:when>
                        <c:otherwise>
                            No category available
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>

                    <div class="btn-group" role="group">
                        <!-- Nút Delete -->
                        <c:choose>
                            <c:when test="${product.isDelete()}">
                                <button class="btn btn-danger btn-sm btn-disabled" disabled>Delete</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteProductModal${product.productId}">Delete</button>
                            </c:otherwise>
                        </c:choose>
                        <!-- Nút Edit -->
                        <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#editProductModal${product.productId}">Edit</button>
                    </div>
                    <!-- Delete Confirmation Modal -->
                    <div class="modal fade" id="deleteProductModal${product.productId}" tabindex="-1" aria-labelledby="deleteProductModalLabel${product.productId}" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteProductModalLabel${product.productId}">Confirm Delete</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete this product?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <form action="product/deleteproduct" method="post">
                                        <input type="hidden" name="productId" value="${product.productId}">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Delete Confirmation Modal -->
                    <!-- Form to edit the product -->

                    <!-- Edit Product Modal -->
                    <div class="modal fade" id="editProductModal${product.productId}" tabindex="-1" aria-labelledby="editProductModalLabel${product.productId}" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editProductModalLabel${product.productId}">Edit Product</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form id="editproductform" action="product/editproduct" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="productId" value="${product.productId}">
                                        <div class="mb-3">
                                            <label class="form-label">Product Name</label>
                                            <input type="text" class="form-control" name="productName" value="${product.name}" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Description</label>
                                            <textarea class="form-control" name="description">${product.descript}</textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Category</label>
                                            <select class="form-select" name="category" required>
                                                <c:forEach var="category" items="${categories}">
                                                    <option value="${category.categoryId}" <c:if test="${product.category.categoryId == category.categoryId}">selected</c:if>>
                                                            ${category.categoryName}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <input type="hidden" name="removeImages" id="removeImages" value="">
                                        <div class="mb-3">
                                            <label for="newImages" class="form-label">Add New Images</label>
                                            <input type="file" class="form-control" id="newImages" name="newImages" multiple>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                    </form>
                                    <div class="mb-3">
                                        <label for="existingImages" class="form-label">Existing Images</label>
                                        <div id="existingImages">
                                            <c:forEach var="image" items="${product.productImages}">
                                                <form action="product/deleteimage" method="post" id="${image.productImage}">
                                                    <img src="../product-images/${image.productImage}" alt="Product Image" width="50" height="50">
                                                    <input name="productImageId" type="hidden" value="${image.productImageId}">
                                                    <input name="productImageName" type="hidden" value="${image.productImage}">
                                                    <input name="productId" type="hidden" value="${product.productId}">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Edit Product Modal -->

                </td>
            </tr>


        </c:forEach>
        <!-- Display message if the product list is empty -->
        <c:if test="${empty listProduct}">
            <tr>
                <td colspan="6" class="text-center">No products available!</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <!-- Pagination Links -->
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <c:if test='${currentPage > 1}'>
                <li class="active">
                    <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                </li>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item <c:if test='${currentPage == i}'>active</c:if>'">
                    <a class="page-link" href="?page=${i}">${i}</a>
                </li>
            </c:forEach>
            <c:if test='${currentPage < totalPages}'>
                <li class="active">
                    <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>


    <div class="container mt-3">
        <div class="row justify-content-center">
            <!-- Back to home page -->
            <div class="col-auto">
                <a href="home" class="btn btn-primary">Back to Home</a>
            </div>
            <!-- Button to open the modal -->
            <div class="col-auto">
                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addProductModal">Add New Product</button>
            </div>
        </div>
    </div>
</div>

<!-- Add Product Modal -->
<div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">Add New Product</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Form to add a new product -->
                <form action="product/addproduct" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label class="form-label">Product Name</label>
                        <input type="text" class="form-control"  name="productName" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <textarea class="form-control" name="description"></textarea>
                    </div>
                    <div class="mb-3">
                        <label  class="form-label">Category</label>
                        <select class="form-select" name="category" required>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryId}">
                                        ${category.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="images" class="form-label">Product Images</label>
                        <input type="file" class="form-control" id="images" name="images" multiple>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </form>
            </div>
        </div>
    </div>
</div>



<!-- Bootstrap JS and Popper.js (required for modal functionality) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
