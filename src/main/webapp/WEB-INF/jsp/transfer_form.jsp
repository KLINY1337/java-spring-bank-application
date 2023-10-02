<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--transfer card-->
<div class="card transfer-card">
    <div class="card-body">
        <form class="transfer-form" action="/transact/transfer" method="post">
        <div class="form-group mb-2">
            <label for="">Select account</label>
            <!-- select account option-->
            <select name="account_id_from" class="form-control">
                <option value="">-- Select FROM account --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div class="form-group mb-2">
            <label for="">Select account</label>
            <!-- select account option-->
            <select name="account_id_to" class="form-control">
                <option value="">-- Select TO account --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>

        <div class="form-group mb-2">
            <label for="">Enter transfer amount</label>
            <input
                    type="text"
                    name="transfer_amount"
                    placeholder="Enter transfer amount"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <button id="transact-btn" class="btn btn-md">Transfer</button>
        </div>
        </form>
    </div>
</div>