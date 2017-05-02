<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
<script src="js/func.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
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