<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" href="css/fontawesome/css/all.css" />
    <link rel="stylesheet" href="css/default.css" />
    <style>* {
        box-sizing: border-box;
    }

    /* Main Body Styling */
    body {
        height: 100vh;
        background-image: url("./images/main-background.png");
        background-size: cover;
        background-position: center center;
        background-repeat: no-repeat;
        font-family: Arial, Helvetica, sans-serif;
    }

    #sample-text-card {
        margin: 0px 100px;
        color: whitesmoke;
    }

    #sample-text-card h1 {
        font-size: 5vw;
    }

    #sample-text-card {
        font-size: 1vw;
    }

    .button-wrapper a {
        width: 15vw;
        color: white;
        padding: 1vw;
    }

    .register {
        margin-right: 1vw;
        background-color: rgb(50, 50, 50);
    }

    .login {
        background-color: rgb(50, 50, 50);
    }

    .card .form-control {
        margin: 10px 0px;
    }

    .card button {
        background-color: rgb(50, 50, 50);
        color: white;
    }
    </style>
    <title>Error</title>
</head>
<body class="d-flex align-items-center justify-content-center">
<div class="card col-4 alert alert-danger border border-danger text-danger">
    <h3 class="card-title">
        <i class="fa fa-window-close w-100">
            Errors:
            <hr />
        </i>
    </h3>
    <div class="card-body">
        <p class="card-text">
            <c:if test="${requestScope.PageTitle != null}">
        <div class="alert alert-danger text-center border border-danger">
            <b>${requestScope.PageTitle}</b>
        </div>
        </c:if>
        </p>
        <hr />
        <a href="login" class="btn btn-sm btn-danger"
        ><i class="fa fa-arrow-alt-circle-left me-1"></i>Back</a
        >
    </div>
</div>
</body>
</html>
