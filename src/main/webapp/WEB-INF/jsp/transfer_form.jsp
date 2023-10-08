<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!--transaction card-->
<div class="card transaction-card">
    <div class="card-body">
        <form class="transaction-form" action="/transact/transaction" method="post">
        <div class="form-group mb-2">
            <label for="">Счёт отправления</label>
            <!-- select account option-->
            <select name="account_id_from" class="form-control">
                <option value="">-- Выберите счёт отправления --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div class="form-group mb-2">
            <label for="">Счёт получения</label>
            <!-- select account option-->
            <select name="account_id_to" class="form-control">
                <option value="">-- Выберите счёт получения --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>

        <div class="form-group mb-2">
            <label for="">Сумма перевода</label>
            <input
                    type="text"
                    name="transfer_amount"
                    placeholder="Введите сумму перевода"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <button id="transact-btn" class="btn btn-md">Перевести</button>
        </div>
        </form>
    </div>
</div>