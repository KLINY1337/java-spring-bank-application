<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--transfer card-->
<div class="card transfer-card">
    <div class="card-body">
        <div class="form-group">
            <label for="">Select account</label>
            <!-- select account option-->
            <select name="account_id" class="form-control">
                <option value="">-- Select account --</option>
            </select>
        </div>

        <div class="form-group">
            <label for="">Select account</label>
            <!-- select account option-->
            <select name="account_id" class="form-control">
                <option value="">-- Select account --</option>
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
    </div>
</div>