<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div
        class="offcanvas offcanvas-end"
        tabindex="-1"
        id="offcanvasRight"
        aria-labelledby="offcanvasRightLabel"
>
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasRightLabel">
            Открыть счёт
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
                <form action="/account/create_account" method="post" class="add-account-form">
                    <div class="form-group mb-3">
                        <label for="">Имя счёта</label>
                        <input
                                type="text"
                                name="account_name"
                                id=""
                                class="form-control"
                                placeholder="Введите имя счёта..."
                        />
                    </div>
                    <div class="form-group mb-3">
                        <label for="">Тип счёта</label>
                        <select name="account_type" id="" class="form-control">
                            <option value="">-- Выберите тип счёта --</option>
                            <option value="check">Платёжный</option>
                            <option value="savings">Накопительный</option>
                            <option value="business">Для бизнеса</option>
                        </select>
                    </div>

                    <div class="form-group mb-2">
                        <button id="transact-btn" class="btn btn-md">
                            Открыть счёт
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>