<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>
<c:set var="title" value="Авторизация" scope="page"/>
<link rel="stylesheet" type="text/css" href="/css/login.css">

<%@include file="template_start.jsp" %>
</nav>

<!-- Главный Экран- -->
<section id="content">
    <div class="container">
        <div class="row">
            <hr>
            <div class="col-md-6 col-md-offset-3">
                    <form name="login-form" class="login-form" method="POST" action="/auth.jsp">
                        <div class="headerLogin">
                            <h1>Авторизация</h1>
                        </div>
                        <div class="contentLogin">
                            <input name="clientName" type="text" class="input username" placeholder="Имя"/>
                        </div>

                        <div class="footerAuth footerLogin">
                            <input type="submit" name="login" value="ВОЙТИ" class="btn-primary button"/>
                        </div>
                        <c:if test="${isError==true}">
                            <div>
                                <p style="color:red;" align="center"><c:out value="${errorMessage}"/></p>
                            </div>
                        </c:if>
                    </form>
            </div>
        </div>
    </div>
</section>

<%@include file="template_end.jsp" %>
