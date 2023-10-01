<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--No accounts-->
<div class="container">
    <div class="card no-accaunts-card">
        <div class="card-body">
            <h1 class="card-title">
                <i class="fas fa-ban text-danger"></i> No registered accounts
            </h1>
            <hr />
            <div class="card-text">
                You currently do not have any registered accounts. <br />
                Please click below to register / add new account
            </div>
            <!--Add new acc button-->
            <br />
            <button
                    class="btn bg-black btn-lg text-white"
                    type="button"
                    data-bs-toggle="offcanvas"
                    data-bs-target="#offcanvasRight"
                    aria-controls="offcanvasRight"
            >
                <i class="fa fa-credit-card"></i> Add new account
            </button>
        </div>
    </div>
</div>