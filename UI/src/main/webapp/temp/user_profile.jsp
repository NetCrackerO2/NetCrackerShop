<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<%@include file="reqauth.jsp" %>
<%@include file="template_start.jsp" %>
<aside>
    <div id="categoryName" class="text1"><c:out value="${title}"/></div>
    <div class="text3">Что-то будет...</div>
</aside>
<div class="main">
    <h2 class="text3">Личные данные</h2>
    <form action="clientInfoServlet.jsp" class="saveUserInfoForm">
        <table class="lk">
            <tr>
                <td> Ваш id</td>
                <td><input type="text" class="textfield" name="idUser" value="${clientInfo.id}" readonly></td>
            </tr>
            <tr>
                <td><label>Имя</label></td>
                <td><input class="textfield" type="text" name="nameUser" value="${clientInfo.name}"></td>
            </tr>
            <tr>
                <td><label>Адрес</label></td>
                <td><input class="textfield" type="text" name="addressUser" value="${clientInfo.address}"/></td>

            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="saveChangeInfo" value="Сохранить"/>
                </td>
            </tr>
        </table>
    </form>
    <%@include file="template_end.jsp" %>
