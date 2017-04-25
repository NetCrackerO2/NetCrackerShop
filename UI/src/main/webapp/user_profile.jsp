<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="title" value="Профиль" scope="page"/>
<c:set var="objStack" value="${[title,'user_profile.jsp']}" scope="page"/>
<c:set var="pathStack" value="${[objStack]}" scope="page"/>

<%@include file="template_start.jsp" %>
<!-- Главный Экран- -->
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <h2 class="text3">Личные данные</h2>
        <form action="clientInfoServlet.jsp" class="saveUserInfoForm">
            <table class="lk table">
                <tr>
                    <td><label>ID</label></td>
                    <td><input type="text" class="textfield" name="idUser" value="${clientInfo.id}" readonly>
                    </td>
                </tr>
                <tr>
                    <td><label>Имя</label></td>
                    <td><input class="textfield" type="text" name="nameUser" value="${clientInfo.name}"></td>
                </tr>
                <tr>
                    <td><label>Адрес</label></td>
                    <td><input class="textfield" type="text" name="addressUser" value="${clientInfo.address}"/>
                    </td>

                </tr>
                <tr>
                    <td></td>
                    <td><input class="btn btn-primary" type="submit" name="saveChangeInfo" value="Сохранить"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</section>
<%@include file="template_end.jsp" %>