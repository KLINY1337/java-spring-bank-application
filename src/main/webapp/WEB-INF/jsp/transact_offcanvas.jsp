<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div
        class="offcanvas offcanvas-start"
        tabindex="-1"
        id="offcanvasExample"
        aria-labelledby="offcanvasExampleLabel"
>
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasExampleLabel">Transact</h5>
        <button
                type="button"
                class="btn-close"
                data-bs-dismiss="offcanvas"
                aria-label="Close"
        ></button>
    </div>
    <div class="offcanvas-body">
        <small class="card-text"
        >Choose an option below to perform a transactions</small
        >
        <!--transaction type -->
        <select
                name="transact-type"
                class="form-control my-3"
                id="transact-type"
        >
            <option value="">-- Select transaction type --</option>
            <option value="payment">Payment</option>
            <option value="transfer">Transfer</option>
            <option value="deposit">Deposit</option>
            <option value="withdraw">Withdraw</option>
        </select>

        <c:import url="payment_form.jsp"/>
        <c:import url="transfer_form.jsp"/>
        <c:import url="deposit_form.jsp"/>
        <c:import url="withdraw_form.jsp"/>







    </div>
</div>