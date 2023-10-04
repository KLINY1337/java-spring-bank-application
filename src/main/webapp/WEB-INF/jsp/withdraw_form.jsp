<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!--withdraw card-->
<div class="card withdraw-card">
    <div class="card-body">
        <form class="withdraw-form" action="/transact/withdraw" method="post">
        <div class="form-group mb-2">
            <label for="">Сумма вывода</label>
            <input
                    type="text"
                    name="withdrawal_amount"
                    placeholder="Введите сумму для вывода"
                    class="form-control"
            />
        </div>
            <div class="form-group mb-2">
                <label for="">Счёт вывода</label>
                <!-- select account option-->
                <select name="account_id" class="form-control">
                    <option value="">-- Выберите счёт для вывода --</option>
                    <c:if test="${userAccounts != null}">
                        <c:forEach items="${userAccounts}" var="selectAccount">
                            <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        <div class="form-group mb-2">
            <button id="transact-btn" class="btn btn-md">Вывести</button>
        </div>
        </form>
    </div>
</div>