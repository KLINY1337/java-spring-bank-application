<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<header class="main-page-header mb-3 bg-white">
    <div class="container d-flex align-items-center">
        <div class="company-name">Черномурофф</div>
        <nav class="navigation">
            <li><a href="/app/dashboard">Главная</a></li>
            <li><a href="/app/dashboard/history">История транзакций</a></li>
        </nav>

        <div class="display-name ms-auto">
            <i class="fa fa-circle text-success me-2 text-success"></i>
            <span>${user.first_name} ${user.last_name}</span>
        </div>
        <a href="/logout" class="btn btn-sm text-black ms-2"
        ><i class="fa fa-sign-out-alt"></i>Выйти</a
        >
    </div>
</header>