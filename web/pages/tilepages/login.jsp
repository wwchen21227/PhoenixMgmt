<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<html>
<head>
<fmt:setBundle basename="ApplicationResources" />
<c:set var="t" value="true" />
<title><fmt:message key="title.login" /></title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/nocturne/login"
		method=post>
		<h2>
			<fmt:message key="title.login" />
		</h2>
		<table >
			<tr>
				<td><fmt:message key="fieldLabel.username" /></td>
				<td><input type="text" name="id" value="${e:forHtmlAttribute(param['name'])}"
					size=15 maxlength=20></td>
			</tr>
			<tr>
				<td><fmt:message key="fieldLabel.password" /></td>
				<td><input type="password" name="password"
					value="${e:forHtmlAttribute(param['name'])}" size=15 maxlength=20></td>
			</tr>
			<tr >
				<td colspan="2" align="center"><input type="submit" value="Submit"> &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>
</body>