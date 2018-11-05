<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<html>
<head>
<link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
<fmt:setBundle basename="ApplicationResources" />
<title> <fmt:message key="title.crudrp"/> </title>
</head>
<body>
        <h1><fmt:message key="label.crudrp"/></h1>
        <c:url var="url" scope="page" value="/nocturne/addeditrp">
        		<c:param name="name" value=""/>
                <c:param name="description" value=""/>
                <c:param name="duration" value=""/>
                <c:param name="insert" value="true"/>
        </c:url>
        <a href="${url}"><fmt:message key="label.crudrp.add"/></a>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.crudrp.name"/></th>
                <th><fmt:message key="label.crudrp.description"/></th>
                <th><fmt:message key="label.crudrp.duration"/></th>
                <th><fmt:message key="label.crudrp.edit"/> <fmt:message key="label.crudrp.delete"/></th>
            </tr>
            <c:forEach var="crudrp" items="${rps}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${e:forHtml(crudrp.name)}</td>
                    <td class="nowrap">${e:forHtml(crudrp.description)}</td>
                    <td class="nowrap">${e:forHtml(crudrp.typicalDuration)}</td>
                    <td class="nowrap">
                        <c:url var="updurl" scope="page" value="/nocturne/addeditrp">
                            <c:param name="name" value="${e:forHtmlAttribute(crudrp.name)}"/>
                            <c:param name="description" value="${e:forHtmlAttribute(crudrp.description)}"/>
                            <c:param name="typicalDuration" value="${e:forHtmlAttribute(crudrp.typicalDuration)}"/>
                             <c:param name="insert" value="false"/>
                        </c:url>
                        <a href="${updurl}"><fmt:message key="label.crudrp.edit"/></a>
                        &nbsp;&nbsp;&nbsp;
                        <c:url var="delurl" scope="page" value="/nocturne/deleterp">
                            <c:param name="name" value="${e:forHtmlAttribute(crudrp.name)}"/>
                        </c:url>
                        <a href="${delurl}"><fmt:message key="label.crudrp.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>