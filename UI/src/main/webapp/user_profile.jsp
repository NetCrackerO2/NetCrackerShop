<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="title" value="Личный кабинет" scope="page"/>
<c:set var="objStack" value="${[title,'user_profile.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>

<%@include file="reqauth.jsp" %>

<%@include file="template_start.jsp" %>
<c:set var="crumbs">
    <a href="index.jsp">Главная</a>,
    <a href="user_profile.jsp">Личный кабинет</a>
</c:set>
<jsp:include page="menu.jsp">
    <jsp:param name="crumbs" value="${crumbs}"/>
    <jsp:param name="title" value="${title}"/>
</jsp:include>
</nav>

<!-- Главный Экран- -->
<div class="container">
    <div class="row">
        <hr>
        <div class="col-md-7 col-md-offset-3">
            <form action="clientInfo.jsp" class="saveUserInfoForm">
                <table class="lk table">
                    <tr>
                        <td><label>Имя</label></td>
                        <td><input class="textfield" type="text" name="clientName"
                                   value="${clientBean.getClientInfo().name}"></td>
                    </tr>
                    <tr>
                        <td><label>Адрес</label></td>
                        <td><input class="textfield" type="text" name="clientDefaultAddress"
                                   value="${clientBean.getClientInfo().address}"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input class="btn btn-primary" type="submit" name="editClientInfo" value="Сохранить"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="col-md-2">
            <ul class="list-group submenu">
                <li class="list-group-item"><a href="orders.jsp">История заказов</a></li>
            </ul>
        </div>
    </div>
</div>
</div>
</section>
<%@include file="template_end.jsp" %>