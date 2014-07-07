<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<c:url var="rootURL" value="/"/>
<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <a href="${rootURL}users/add">Добавить</a>
</sec:authorize>
<h1>Пользователи</h1>
<table class="table">
    <tr>
        <th>#</th>
        <th>Имя</th>
        <th>Роли</th>
        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_MANAGER"><th>email</th></sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_ADMIN"><th></th></sec:authorize>
    </tr>
    <tbody class="table-striped">
    <c:forEach items="${users.data}" var="user">
        <tr>
            <td>${user.id}</td>
            <td><c:out value="${user.fullName}"/></td>
            <td>
                <c:forEach items="${user.roles}" var="role" varStatus="status">
                    <c:if test="${!status.first}">, </c:if>${role.humanName}
                </c:forEach>
            </td>
            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_MANAGER"><td>${user.email}</td></sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <th><a href="${rootURL}users/edit/${user.id}">Редактировать</a>
                    <sec:authentication property="principal.username" var="currentUsername"/>
                    <c:if test="${currentUsername != user.username}">
                        <a href="${rootURL}users/delete/${user.id}">Удалить</a>
                    </c:if>
                </th>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>

<util:pagination baseUrl="${rootURL}users" pager="${users}"/>