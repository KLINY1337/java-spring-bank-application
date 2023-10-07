<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!--transfer card-->
<div class="card transfer-card">
    <div class="card-body">
        <form class="transfer-form" method="post" action="/transact/transfer">
        <div class="form-group mb-2">
            <label for="">Счёт для перевода</label>
            <!-- select account option-->
            <select name="account_from_id" class="form-control">
                <option value="">-- Выберите счёт --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div class="form-group mb-2">
            <label for="">Электронная почта получателя</label>
            <input
                    type="text"
                    name="account_to_email"
                    placeholder="Введите почту получателя"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Номер счёта получателя</label>
            <!-- select account option-->
            <input
                    type="text"
                    name="account_to_number"
                    placeholder="Введите номер счёт получателя"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Комментарий</label>
            <input
                    type="text"
                    name="reference"
                    placeholder="Введите комментарий к платежу"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Сумма перевода</label>
            <input
                    type="text"
                    name="payment_amount"
                    placeholder="Введите сумму перевода"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <button id="transact-btn" class="btn btn-md">Перевод</button>
        </div>
        </form>
    </div>
</div>