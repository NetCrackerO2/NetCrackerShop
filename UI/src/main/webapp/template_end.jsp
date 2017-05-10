<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->


<!-- Include all compiled plugins (below), or include individual files as needed -->

<footer class="navbar-fixed-bottom">
    <div class="container">
        <div class="row">

        </div>
    </div>
</footer>
<c:if test="${not empty clientInfo.errorMessage}">
    <div class="error"><span><c:out value="${clientInfo.errorMessage}"/></span></div>
    <c:set target="${clientInfo}" property="errorMessage" value="" />
</c:if>
</body>
</html>