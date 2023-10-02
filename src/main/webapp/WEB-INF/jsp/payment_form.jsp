<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--payment card-->
<div class="card payment-card">
    <div class="card-body">
        <form class="payment-form" method="post" action="/transact/payment">
        <div class="form-group mb-2">
            <label for="">Select FROM account</label>
            <!-- select account option-->
            <select name="account_from_id" class="form-control">
                <option value="">-- Select account --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div class="form-group mb-2">
            <label for="">Enter user TO email</label>
            <input
                    type="text"
                    name="account_to_email"
                    placeholder="Enter account holder / beneficiary account №"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Enter account TO number</label>
            <!-- select account option-->
            <input
                    type="text"
                    name="account_to_number"
                    placeholder="Enter account holder / beneficiary account №"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Reference</label>
            <input
                    type="text"
                    name="reference"
                    placeholder="Enter Reference"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Enter payment amount</label>
            <input
                    type="text"
                    name="payment_amount"
                    placeholder="Enter payment amount"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <button id="transact-btn" class="btn btn-md">Pay</button>
        </div>
        </form>
    </div>
</div>