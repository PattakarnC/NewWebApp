<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login Webapp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<style>
    body {
        background-image: url('https://wallpaperaccess.com/full/1943290.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        background-attachment: fixed;
    }
</style>
<div class="container-fluid">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="/">SSC - Login Webapp</a>
        <a class="btn btn-light btn-sm pull-right" type="button" href="/logout">
            <i class="fa fa-sign-out"></i> &nbsp; Logout</a>
    </nav>
    <c:if test="${not empty message}">
        <c:choose>
            <c:when test="${hasError}">
                <div class="alert alert-danger" role="alert">
                    <a><i class="fa fa-times-circle" style="color: red"></i> ${message} </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-success" role="alert" md-6>
                    <a><i class="fa fa-check-circle-o" style="color: green"></i> ${message} </a>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>

    <section class="h-100">
        <div class="container h-100">
            <div class="row justify-content-sm-center h-100">
                <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9 my-auto">
                    <div class="card shadow-lg">
                        <div class="card-body p-5">
                            <h1 class="fs-4 card-title fw-bold mb-4">Create New User</h1>
                            <p>
                                ${error}
                            </p>
                            <form action="/user/create" method="POST" autocomplete="off">
                                <div class="mb-3">
                                    <label class="mb-2 text-muted">Username</label>
                                    <input type="text" name="username" class="form-control" value="${username}">
                                </div>
                                <div class="mb-3">
                                    <label class="mb-2 text-muted">Display Name</label>
                                    <input type="text" name="displayName" class="form-control" value="${displayName}">
                                </div>
                                <div class="mb-3">
                                    <label class="mb-2 text-muted">Password</label>
                                    <input type="password" name="password" class="form-control" value="${password}">
                                </div>
                                <div class="mb-3">
                                    <label class="mb-2 text-muted">Confirm Password</label>
                                    <input type="password" name="cpassword" class="form-control" value="${cpassword}">
                                </div>

                                <div class="d-flex align-items-center">
                                    <button type="submit" class="btn btn-success ms-auto">
                                        Create
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

</body>
</html>