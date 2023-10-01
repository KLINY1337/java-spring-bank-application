<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" href="css/fontawesome/css/all.css" />
    <link rel="stylesheet" href="css/default.css" />
    <title>Login</title>
</head>
<body class="d-flex align-items-center justify-content-center">
<div class="card login-form-card col-4 bg-transparent border-0">
    <div class="card-body">
        <h1 class="form-header card-title mb-3">
            <i class="fa fa-user-circle"></i> Login
        </h1>

        <c:if test="${requestScope.success != null}">
            <div class="alert alert-success text-center border border-success">
                <b>${requestScope.success}</b>
            </div>
        </c:if>
        <c:if test="${requestScope.error != null}">
            <div class="alert alert-danger text-center border border-danger">
                <b>${requestScope.error}</b>
            </div>
        </c:if>
        <c:if test="${logged_out != null}">
            <div class="alert alert-info text-center border border-info">
                <b>${logged_out}</b>
            </div>
        </c:if>

        <form action="/login" method="post" class="login-form">
            <div class="row">
                <div class="form-group col">
                    <input
                            type="email"
                            name="email"
                            class="form-control form-control-lg"
                            placeholder="Enter email"
                    />
                </div>
            </div>
            <div class="row">
                <div class="form-group col">
                    <input
                            type="password"
                            name="password"
                            class="form-control form-control-lg"
                            placeholder="Enter password"
                    />
                </div>
                <div class="form-group col">
                    <input
                            type="hidden"
                            name="_token"
                            value="${token}"
                    />
                </div>
            </div>
            <div class="form-group col">
                <button class="btn btn-lg">Login</button>
            </div>
        </form>

        <p class="card-text text-white my-2">
            Don't have an account
            <span class="ms-2 text-warning"
            ><a href="register" class="btn btn-sm text-warning"
            >Sign up</a
            ></span
            >
        </p>

        <small class="text-warning">
            <i class="fa fa-arrow-alt-circle-left"
            ><a href="/" class="btn btn-sm text-warning">Back</a></i
            >
        </small>
    </div>
</div>
</body>
</html>
