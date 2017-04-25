<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="title" value="Профиль" scope="page"/>
<c:set var="objStack" value="${[title,'user_profile.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>

<%@include file="reqauth.jsp" %>

<%@include file="template_start.jsp" %>
<!-- Главный Экран- -->
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <h2 class="text3">Личные данные</h2>
        <form action="clientsServlet.jsp" class="saveUserInfoForm">
            <table class="lk table">
                <tr>
                    <td><label>ID</label></td>
                    <td><input type="text" class="textfield" name="clientId"
                                       value="${clientBean.getClientInfo().id}" readonly>
                    </td>
                </tr>
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
                    <td><input class="btn btn-primary" type="submit" name="editClient" value="Сохранить"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</section>
<%@include file="template_end.jsp" %>