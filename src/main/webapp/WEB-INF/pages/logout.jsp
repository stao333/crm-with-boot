<!DOCTYPE html>
<html>
<body>
	
<% session.invalidate(); %>
You are now logged out. <br/>

<a href="${pageContext.request.contextPath}/login">Go back</a>

</body>
</html>