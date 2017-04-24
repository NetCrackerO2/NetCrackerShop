<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="env.jsp" %>

<c:set var="pathStack" value="${['Search']}" scope="page"/>
<c:set var="title" value="Search" scope="page"/>

<%@include file="template_start.jsp" %>
<!-- Главный Экран- -->
<section id="content">
    <div class="container">
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