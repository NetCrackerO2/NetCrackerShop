			</div>
			<footer>
			 </footer>
		</div>
		<c:if test="${not empty clientInfo.errorMessage}">
			<div class="error"><c:out value="${clientInfo.errorMessage}"/></div>
			<c:set target="${clientInfo}" property="errorMessage" value="" />
		</c:if>
	</body>
</html>
