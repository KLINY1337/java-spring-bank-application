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
    <title>Register</title>
</head>
<body class="d-flex align-items-center justify-content-center">
<div class="card registration-form-card col-6 bg-transparent border-0">
    <div class="card-body">
        <h1 class="form-header card-title mb-3">
            <i class="fa fa-edit"></i> Register
        </h1>

        <form:form action="/register" class="registation-form" modelAttribute="registerUser">
            <div class="row">
                <div class="form-group col">
                    <form:input
                            type="text"
                            path="first_name"
                            class="form-control form-control-lg"
                            placeholder="Enter first name"
                    />
                    <form:errors path="first_name" class="text-white bg-danger" />
                </div>
                <div class="form-group col">
                    <form:input
                            type="text"
                            path="last_name"
                            class="form-control form-control-lg"
                            placeholder="Enter last name"
                    />
                    <form:errors path="last_name" class="text-white bg-danger" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col">
                    <form:input
                            type="email"
                            path="email"
                            class="form-control form-control-lg"
                            placeholder="Enter email"
                    />
                    <form:errors path="email" class="text-white bg-danger" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col">
                    <form:input
                            type="password"
                            path="password"
                            class="form-control form-control-lg"
                            placeholder="Enter password"
                    />
                    <form:errors path="password" class="text-white bg-danger" />
                </div>
                <div class="form-group col">
                    <input
                            type="password"
                            name="confirm_password"
                            class="form-control form-control-lg"
                            placeholder="Repeat password"
                    />
                    <small class="text-white bg-danger">${confirm_pass}</small>
                </div>
            </div>
            <div class="form-group col">
                <button class="btn btn-lg">Register</button>
            </div>
        </form:form>

        <p class="card-text text-white my-2">
            Already have an account
            <span class="ms-2 text-warning"
            ><a href="login" class="btn btn-sm text-warning"
            >Sign in</a
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
