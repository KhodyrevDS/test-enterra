<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:url var="rootURL" value="/"/>
<h1>Роли</h1>
<table class="table">
    <tr>
        <th>#</th>
        <th>Название</th>
    </tr>
    <tbody class="table-striped">
    <c:forEach items="${roles}" var="role">
        <tr>
            <td>${role.id}</td>
            <td><c:out value="${role.humanName}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>