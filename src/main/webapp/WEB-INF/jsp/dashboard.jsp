<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
    <link rel="stylesheet" href="css/fontawesome/css/all.css" />
    <link rel="stylesheet" href="css/main.css" />
    <script src="js/bootstrap.bundle.js"></script>
    <title>Dashboard</title>
</head>
<body>
<header class="main-page-header mb-3 bg-white">
    <div class="container d-flex align-items-center">
        <div class="company-name">Easy Bank</div>
        <nav class="navigation">
            <li><a href="">Dashboard</a></li>
            <li><a href="">Payment History</a></li>
            <li><a href="">Transaction History</a></li>
        </nav>

        <div class="display-name ms-auto">
            <i class="fa fa-circle text-success me-2 text-success"></i>Welcome:
            <span>John Doe</span>
        </div>
        <a href="" class="btn btn-sm text-black ms-2"
        ><i class="fa fa-sign-out-alt"></i>Log out</a
        >
    </div>
</header>

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

        <!--deposit card-->
        <div class="card deposit-card">
            <div class="card-body">
                <div class="form-group mb-2">
                    <label for="">Enter deposit amount</label>
                    <input
                            type="text"
                            name="deposit_amount"
                            placeholder="Enter deposit amount"
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
                    <button id="transact-btn" class="btn btn-md">Deposit</button>
                </div>
            </div>
        </div>

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
    </div>
</div>

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
    <h2 class="ms-auto">0.00</h2>
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
</body>
<script src="js/main.js"></script>
</html>
