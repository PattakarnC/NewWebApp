<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>SSC Webapp Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="text-center my-5">
                    <img src="https://lh3.googleusercontent.com/z1YQISVHpT8uy8GG1HKiRyWBApWd3R-m3As63DN7BN2b0u69GEDcG44sCy1DkP0JvQFEby9cyPU4DHVylh8hLeEEXFYTl0ronidaiQB6513JV3UmPOSz6WbCABMp49TMjGkTtw2C5A=s240-p-k" alt="logo" width="150">
                </div>
                <div class="card shadow-lg">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4">Login</h1>
                        <div style="color:red" class="mb-3">${error}</div>
                        <form action="/login" method="POST">
                            <div class="mb-3">
                                <label class="mb-2 text-muted">Username</label>
                                <input type="text" name="username" class="form-control" value="" required autofocus>
                            </div>

                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted">Password</label>
                                </div>
                                <input type="password" class="form-control" name="password" required>
                            </div>

                            <div class="d-flex align-items-center">
                                <button type="submit" class="btn btn-primary ms-auto">
                                    Login
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/login.js"></script>
</body>
</html>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<title>Home Page</title>--%>
<%--<body>--%>
<%--<p>--%>
<%--    ${error}--%>
<%--</p>--%>
<%--<p>--%>
<%--<form action="/login" method="post">--%>
<%--    <input type="text" placeholder="Enter Username" name="username" required><br>--%>
<%--    <input type="password" placeholder="Enter Password" name="password" required><br>--%>
<%--    <button type="submit">Login</button>--%>
<%--</form>--%>
<%--</p>--%>
<%--</body>--%>
<%--</html>--%>