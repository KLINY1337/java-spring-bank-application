<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div
        class="offcanvas offcanvas-start"
        tabindex="-1"
        id="offcanvasExample"
        aria-labelledby="offcanvasExampleLabel"
>
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasExampleLabel">Переводы</h5>
        <button
                type="button"
                class="btn-close"
                data-bs-dismiss="offcanvas"
                aria-label="Close"
        ></button>
    </div>
    <div class="offcanvas-body">
        <small class="card-text"
        >Тип перевода</small
        >
        <!--transaction type -->
        <select
                name="transact-type"
                class="form-control my-3"
                id="transact-type"
        >
            <option value="">-- Выберите тип перевода --</option>
            <option value="transaction">Другому пользователю</option>
            <option value="transaction">Между своими счетами</option>
            <option value="deposit">Ввод средств</option>
            <option value="withdraw">Вывод средств</option>
        </select>

        <c:import url="payment_form.jsp"/>
        <c:import url="transfer_form.jsp"/>
        <c:import url="deposit_form.jsp"/>
        <c:import url="withdraw_form.jsp"/>







    </div>
</div>