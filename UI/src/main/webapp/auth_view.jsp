<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<c:set var="title" value="Авторизация" scope="page"  />

<%@include file="template_start.jsp"%>
<div class="main">

<form name="login-form" class="login-form" method="POST" action="/auth.jsp">
    <div class="headerLogin">
        <h1>Авторизация</h1>
    </div>
    <div class="contentLogin">
        <input name="login" type="text" class="input username" placeholder="Логин" value="Логин" onfocus="this.value=''"/>
    </div>
    <div class="footerLogin">
        <input type="submit" name="submit" value="ВОЙТИ" class="button" />
    </div>
</form>
<%@include file="template_end.jsp"%>
