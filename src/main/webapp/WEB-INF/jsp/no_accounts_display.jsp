<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!--No accounts-->
<div class="container">
    <div class="card no-accaunts-card">
        <div class="card-body">
            <h1 class="card-title">
                <i class="fas fa-ban text-danger"></i> Нет открытых счетов
            </h1>
            <hr />
            <div class="card-text">
                На данный момент у вас не открыто ни одного счёта.<br />
                Нажмите ниже, чтобы открыть счёт для расходов, накоплений или вашего бизнеса
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
                <i class="fa fa-credit-card"></i> Открыть счёт
            </button>
        </div>
    </div>
</div>