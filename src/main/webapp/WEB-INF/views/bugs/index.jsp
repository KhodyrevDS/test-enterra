<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="rootURL" value="/"/>
<sec:authorize ifAnyGranted="ROLE_ADMIN" var="isAdmin"/>
<a href="${rootURL}bugs/add">Добавить</a>
<h1>Баги</h1>
<table class="table">
    <tr>
        <th>#</th>
        <th>Создан</th>
        <th>Название</th>
        <th>Исполнитель</th>
        <th>Сообщил</th>
        <th></th>
    </tr>
    <tbody class="table-striped">
    <c:forEach items="${bugs.data}" var="bug">
        <tr>
            <td>${bug.id}</td>
            <td><fmt:formatDate value="${bug.created}" pattern="dd.MM.yyyy HH:mm"/></td>
            <td><c:out value="${bug.title}"/></td>
            <td>
                <c:out value="${bug.assignee.fullName}"/>
            </td>
            <td>
                <c:out value="${bug.reporter.fullName}"/>
            </td>
            <th><a href="${rootURL}bugs/edit/${bug.id}">Редактировать</a>
                <sec:authentication property="principal.username" var="currentUsername"/>
                <c:if test="${currentUsername == bug.reporter.username || isAdmin}">
                    <a href="${rootURL}bugs/delete/${bug.id}">Удалить</a>
                </c:if>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<util:pagination baseUrl="${rootURL}bugs" pager="${bugs}"/>