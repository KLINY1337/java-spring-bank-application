<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div
        class="offcanvas offcanvas-end"
        tabindex="-1"
        id="offcanvasRight"
        aria-labelledby="offcanvasRightLabel"
>
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasRightLabel">
            Create / add an account
        </h5>
        <button
                type="button"
                class="btn-close"
                data-bs-dismiss="offcanvas"
                aria-label="Close"
        ></button>
    </div>
    <div class="offcanvas-body">
        <div class="card">
            <div class="card-body">
                <form action="" class="add-account-form">
                    <div class="form-group mb-3">
                        <label for="">Enter account name</label>
                        <input
                                type="text"
                                name="account_name"
                                id=""
                                class="form-control"
                                placeholder="Enter account name..."
                        />
                    </div>
                    <div class="form-group mb-3">
                        <label for="">Select account type</label>
                        <select name="account_type" id="" class="form-control">
                            <option value="">-- Select account type --</option>
                            <option value="check">Check</option>
                            <option value="savings">Savings</option>
                            <option value="business">Business</option>
                        </select>
                    </div>

                    <div class="form-group mb-2">
                        <button id="transact-btn" class="btn btn-md">
                            Add account
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>