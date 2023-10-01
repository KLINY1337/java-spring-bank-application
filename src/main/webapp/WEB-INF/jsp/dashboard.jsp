<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" href="../css/fontawesome/css/all.css" />
    <link rel="stylesheet" href="../css/main.css" />
    <script src="../js/bootstrap.bundle.js"></script>
    <title>Dashboard</title>
</head>
<body>

    <c:import url="header.jsp"/>


    <c:import url="transact_offcanvas.jsp"/>
    <c:import url="add_account_offcanvas.jsp"/>

    <c:if test="${success != null}">
    <div class="alert alert-success text-center border border-success">
        <b>${success}</b>
    </div>
    </c:if>
    <c:if test="${error != null}">
    <div class="alert alert-danger text-center border border-danger">
        <b>${error}</b>
    </div>
    </c:if>

    <c:choose>
        <c:when test="${fn:length(userAccounts) > 0}">
            <c:import url="accounts_display.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="no_accounts_display.jsp"/>
        </c:otherwise>
    </c:choose>

    <c:import url="footer.jsp"/>
