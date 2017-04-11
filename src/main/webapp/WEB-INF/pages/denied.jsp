<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	
		<h3 id="banner">Unauthorized Access</h3>
	
		<hr />
	
		<c:if test="${not empty error}">
			<div style="color:red">
				Either user name or password is incorrect.
			</div>
		</c:if>
	
		<p class="message">Access denied.</p>
		<a href="login">Back to login page</a> 
	</body>
</html>