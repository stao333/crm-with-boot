<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Example CRM Login</title>
</head>
<body>
	<h3 id="banner">Login to Example CRM</h3>  
	<form action="login" method="post">

			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='username' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password'></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit">&nbsp;<input name="reset" type="reset"></td>
				</tr>
			</table>

<%-- 		<div>
			<div>
				<input type="text" name="username"
					placeholder="User Name" />
			</div>
			<div>
				<input type="password" name="password"
					placeholder="Password" />
			</div>
			<div>
				<input type="submit" value="Sign In" />
			</div>
			<c:if test="${param.error ne null}">
				<div>Invalid username and password.</div>
			</c:if>
			<c:if test="${param.logout ne null}">
				<div>You have been logged out.</div>
			</c:if>
		</div>
 --%>		
 			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

</body>
</html>