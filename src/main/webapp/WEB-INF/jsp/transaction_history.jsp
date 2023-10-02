<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" href="../../css/fontawesome/css/all.css" />
    <link rel="stylesheet" href="../../css/main.css" />
    <script src="../../js/bootstrap.bundle.js"></script>
    <title>Dashboard</title>
</head>
<body>

<c:import url="header.jsp"/>

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

<div class="container">
    <div class="card payment-history-card mb-2">
        <div class="card-body">
            <h1 class="card-title">
                <i class="fas fa-building text-black"></i> Payment history
            </h1>
            <hr />
            <div class="card-text">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Sender name</th>
                        <th scope="col">Receiver email</th>
                        <th scope="col">Receiver account number</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Reference</th>
                        <th scope="col">Time</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:if test="${payments != null}">
                            <c:forEach items="${payments}" var="p">
                                <tr>
                                    <th scope="row">${p.payment_id}</th>
                                    <td>${p.user_from_name}</td>
                                    <td>${p.user_to_email}</td>
                                    <td>${p.user_to_account_number}</td>
                                    <td>${p.amount}</td>
                                    <td>${p.reference}</td>
                                    <td>${p.created_at}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="card payment-history-card mb-2">
        <div class="card-body">
            <h1 class="card-title">
                <i class="fas fa-piggy-bank text-black"></i> Deposit history
            </h1>
            <hr />
            <div class="card-text">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Account name</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${deposits != null}">
                        <c:forEach items="${deposits}" var="d">
                            <tr>
                                <th scope="row">${d.deposit_id}</th>
                                <td>${d.account_name}</td>
                                <td>${d.amount}</td>
                                <td>${d.created_at}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="card payment-history-card mb-2">
        <div class="card-body">
            <h1 class="card-title">
                <i class="fas fa-check text-black"></i> Transfer history
            </h1>
            <hr />
            <div class="card-text">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Sender account</th>
                        <th scope="col">Receiver account</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${transfers != null}">
                        <c:forEach items="${transfers}" var="t">
                            <tr>
                                <th scope="row">${t.transfer_id}</th>
                                <td>${t.account_from_name}</td>
                                <td>${t.account_to_name}</td>
                                <td>${t.amount}</td>
                                <td>${t.created_at}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="card payment-history-card mb-2">
        <div class="card-body">
            <h1 class="card-title">
                <i class="fas fa-download text-black"></i> Withdraw history
            </h1>
            <hr />
            <div class="card-text">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Account name</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${withdraws != null}">
                        <c:forEach items="${withdraws}" var="w">
                            <tr>
                                <th scope="row">${w.withdraw_id}</th>
                                <td>${w.account_name}</td>
                                <td>${w.amount}</td>
                                <td>${w.created_at}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
