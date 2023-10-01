<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container d-flex">
    <button
            class="btn bg-white btn-lg"
            type="button"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasRight"
            aria-controls="offcanvasRight"
    >
        <i class="fa fa-credit-card"></i> Add new account
    </button>
    <button
            id="transact-btn"
            class="btn bg-white ms-auto btn-lg"
            type="button"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasExample"
            aria-controls="offcanvasExample"
    >
        <i class="fa fa-wallet"></i> Transact
    </button>
</div>

<!--Total acc balance-->
<div class="container d-flex text-white py-3">
    <h2 class="me-auto">Total accounts balance:</h2>
    <h2 class="ms-auto"><c:if test="${requestScope.totalBalance != null}">
        <c:out value="${totalBalance}"/>
    </c:if></h2>
</div>
<!--Accordion-->
<div class="container">
    <div class="accordion" id="accordionPanelsStayOpenExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                <button
                        class="accordion-button collapsed"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#panelsStayOpen-collapseOne"
                        aria-expanded="false"
                        aria-controls="panelsStayOpen-collapseOne"
                >
                    Accordion Item #1
                </button>
            </h2>
            <div
                    id="panelsStayOpen-collapseOne"
                    class="accordion-collapse collapse"
                    aria-labelledby="panelsStayOpen-headingOne"
            >
                <div class="accordion-body">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    Vestibulum tempus.
                </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                <button
                        class="accordion-button collapsed"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#panelsStayOpen-collapseTwo"
                        aria-expanded="false"
                        aria-controls="panelsStayOpen-collapseTwo"
                >
                    Accordion Item #2
                </button>
            </h2>
            <div
                    id="panelsStayOpen-collapseTwo"
                    class="accordion-collapse collapse"
                    aria-labelledby="panelsStayOpen-headingTwo"
            >
                <div class="accordion-body">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    Vestibulum tempus.
                </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                <button
                        class="accordion-button collapsed"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#panelsStayOpen-collapseThree"
                        aria-expanded="false"
                        aria-controls="panelsStayOpen-collapseThree"
                >
                    Accordion Item #3
                </button>
            </h2>
            <div
                    id="panelsStayOpen-collapseThree"
                    class="accordion-collapse collapse"
                    aria-labelledby="panelsStayOpen-headingThree"
            >
                <div class="accordion-body">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    Vestibulum tempus.
                </div>
            </div>
        </div>
    </div>
</div>