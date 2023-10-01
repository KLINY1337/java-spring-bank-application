<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--withdraw card-->
<div class="card withdraw-card">
    <div class="card-body">
        <div class="form-group mb-2">
            <label for="">Enter withdrawal amount</label>
            <input
                    type="text"
                    name="withdrawal_amount"
                    placeholder="Enter withdraw amount"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Select account</label>
            <!-- select account option-->
            <select name="account_id" class="form-control">
                <option value="">-- Select account --</option>
            </select>
        </div>
        <div class="form-group mb-2">
            <button id="transact-btn" class="btn btn-md">Withdraw</button>
        </div>
    </div>
</div>