<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!--deposit card-->
<div class="card deposit-card">
    <div class="card-body">
        <form class="deposit-form" action="/transact/deposit" method="post">
        <div class="form-group mb-2">
            <label for="">Сумма депозита</label>
            <input
                    type="text"
                    name="deposit_amount"
                    placeholder="Введите сумму депозита"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Счёт для пополнения</label>
            <!-- select account option-->
            <select name="account_id" class="form-control">
                <option value="">-- Выберите счёт для пополнения --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div class="form-group mb-2">
            <button id="transact-btn" class="btn btn-md">Внести средства на счёт</button>
        </div>
        </form>
    </div>
</div>