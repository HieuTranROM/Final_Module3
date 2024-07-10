<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Trang chủ</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="/product">Product</a>
            </div>
        </div>
        <form class="d-flex" role="search" action="/product" method="get">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search">
            <input type="hidden" name="action" value="search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
</nav>
<form method="get" action="/product">
    <div class="mb-3">
        <label for="topCount" class="form-label">Xem top sản phẩm bán chạy</label>
        <select class="form-select" id="topCount" name="topCount">
            <option value="3">Top 3</option>
            <option value="5">Top 5</option>
            <option value="10">Top 10</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Xem</button>
</form>
<form method="get" action="/product">
    <div class="mb-3">
        <label for="startDate" class="form-label">Start Date</label>
        <input type="date" class="form-control" id="startDate" name="startDate" required>
    </div>
    <div class="mb-3">
        <label for="endDate" class="form-label">End Date</label>
        <input type="date" class="form-control" id="endDate" name="endDate" required>
    </div>
    <input type="hidden" name="action" value="viewByDate">
    <button type="submit" class="btn btn-primary">Xem</button>
</form>
<div class="container">
    <p style="color: green">${message}</p>

    <button class="btn btn-primary mt-4" onclick="window.location.href='/product?action=create'">Thêm</button>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>STT</th>
            <th>Name</th>
            <th>Price</th>
            <th>Discount</th>
            <th>Stock</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${product.productName}</td>
                <td>${product.price}</td>
                <td>${product.discount}</td>
                <td>${product.stock_quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${not empty productsByDate}">
        <h3>Danh sách sản phẩm được đặt hàng trong khoảng thời gian đã chọn</h3>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>STT</th>
                <th>Name</th>
                <th>Price</th>
                <th>Discount</th>
                <th>Stock</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${productsByDate}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${product.productName}</td>
                    <td>${product.price}</td>
                    <td>${product.discount}</td>
                    <td>${product.stock_quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
