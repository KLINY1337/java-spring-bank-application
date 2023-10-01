<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--payment card-->
<div class="card payment-card">
    <div class="card-body">
        <div class="form-group mb-2">
            <label for="">Enter Account holder / Beneficiary</label>
            <input
                    type="text"
                    name="beneficiary"
                    placeholder="Enter account holder / beneficiary name"
                    class="form-control"
            />
        </div>
        <div class="form-group mb-2">
            <label for="">Enter Account holder / Beneficiary number</label>
            <input
                    type="text"
                    name="account_number"
                    placeholder="Enter account holder / beneficiary account №"
                    class="form-control"
            />
        </div>
        <div class="form-group">
            <label for="">Select account</label>
            <!-- select account option-->
            <select name="account_id" class="form-control">
                <option value="">-- Select account --</option>
            </select>
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
    </div>
</div>