<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="container d-flex">
    <button
            class="btn bg-white btn-lg"
            type="button"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasRight"
            aria-controls="offcanvasRight"
    >
        <i class="fa fa-credit-card"></i> Открыть счёт
    </button>
    <button
            id="transact-btn"
            class="btn bg-white ms-auto btn-lg"
            type="button"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasExample"
            aria-controls="offcanvasExample"
    >
        <i class="fa fa-wallet"></i> Переводы
    </button>
</div>

<!--Total acc balance-->
<div class="container d-flex text-white py-3">
    <h2 class="me-auto">Общий баланс: </h2>
    <h2 class="ms-auto"><c:if test="${requestScope.totalBalance != null}">
        <c:out value="${totalBalance}"/>
    </c:if></h2>
</div>
<!--Accordion-->
<div class="container">
    <c:if test="${requestScope.userAccounts != null}">
        <c:forEach items="${requestScope.userAccounts}" var="account">
            <div class="accordion" id="accordionPanelsStayOpenExample">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="panelsStayOpen-heading${account.account_number}">
                        <button
                                class="accordion-button collapsed"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target="#panelsStayOpen-collapse${account.account_number}"
                                aria-expanded="false"
                                aria-controls="panelsStayOpen-collapse${account.account_number}"
                        >
                            ${account.account_name}
                        </button>
                    </h2>
                    <div
                            id="panelsStayOpen-collapse${account.account_number}"
                            class="accordion-collapse collapse"
                            aria-labelledby="panelsStayOpen-heading${account.account_number}"
                    >
                        <div class="accordion-body">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex ">Имя <span class="ms-auto"> <b>${account.account_name}</b></span></li>
                                <li class="list-group-item d-flex">Номер <span class="ms-auto"> <b>${account.account_number}</b></span></li>
                                <li class="list-group-item d-flex">Тип <span class="ms-auto"> <b>${account.account_type}</b></span></li>
                                <li class="list-group-item d-flex">Баланс <span class="ms-auto"> <b>${account.balance}</b></span></li>
                                <li class="list-group-item d-flex">Создан <span class="ms-auto"> <b>${account.created_at}</b></span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>





