<footer class="navbar-fixed-bottom">
    <div class="container">
        <div class="row">

        </div>
    </div>
</footer>
<c:if test="${not empty clientInfo.errorMessage}">
    <div class="error"><span id="error_msg"><c:out value="${clientInfo.errorMessage}"/></span></div>
    <c:set target="${clientInfo}" property="errorMessage" value="" />
</c:if>
<c:if test="${empty clientInfo.errorMessage}">
    <div class="error" style = "display: none;"><span id="error_msg"></span></div>
</c:if>
</body>
</html>
