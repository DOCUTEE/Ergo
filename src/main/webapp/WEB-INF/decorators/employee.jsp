<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
  <meta charset="utf-8" />
  <meta
          name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
  />

  <title>Ergo</title>

  <meta name="description" content="" />

  <!-- Favicon -->
  <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/employee/assets/img/favicon/favicon.ico" />
  <!-- Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
          href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet"
  />

  <!-- Icons. Uncomment required icon fonts -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/assets/vendor/fonts/boxicons.css" />

  <!-- Core CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/assets/vendor/css/core.css" class="template-customizer-core-css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/assets/css/demo.css" />

  <!-- Vendors CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/assets/vendor/libs/apex-charts/apex-charts.css" />

  <!-- Page CSS -->

  <!-- Helpers -->
  <script src="${pageContext.request.contextPath}/employee/assets/vendor/js/helpers.js"></script>

  <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
  <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
  <script src="${pageContext.request.contextPath}/employee/assets/js/config.js"></script>
</head>
<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
  <div class="layout-container">
<%@include file="/employee/common/menu.jsp"%>
  <!-- Layout container -->
  <div class="layout-page">
    <!-- Content wrapper -->
    <div class="content-wrapper">

      <sitemesh:write property="body"/>
    </div>
    <!-- Content wrapper -->
  </div>
    <!-- / Layout page -->
  </div>
  <!-- Overlay -->
  <div class="layout-overlay layout-menu-toggle"></div>
</div>
<!-- build:js assets/vendor/js/core.js -->
<script src="${pageContext.request.contextPath}/employee/assets/vendor/libs/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/employee/assets/vendor/libs/popper/popper.js"></script>
<script src="${pageContext.request.contextPath}/employee/assets/vendor/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/employee/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="${pageContext.request.contextPath}/employee/assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->
<script src="${pageContext.request.contextPath}/employee/assets/vendor/libs/apex-charts/apexcharts.js"></script>

<!-- Main JS -->
<script src="${pageContext.request.contextPath}/employee/assets/js/main.js"></script>

<!-- Page JS -->
<script src="${pageContext.request.contextPath}/employee/assets/js/dashboards-analytics.js"></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
