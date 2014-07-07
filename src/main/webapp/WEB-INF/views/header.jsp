<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:url var="rootURL" value="/"/>
<sec:authorize access="isAuthenticated()" var="isAuthenticated"/>
<header>

    <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${rootURL}">Enterra тест</a>
            </div>
            <div class="navbar-collapse collapse">
                <c:if test="${isAuthenticated}">
                <ul class="nav navbar-nav">
                    <li><a href="${rootURL}users">Пользователи</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${rootURL}roles">Роли</a></li>
                    </sec:authorize>
                    <li><a href="${rootURL}bugs">Баги</a></li>
                </ul>
                </c:if>
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="isAuthenticated()" var="isAuthenticated"/>
                    <c:choose>
                        <c:when test="${isAuthenticated}">
                            <li><a><sec:authentication property="principal.username"/></a></li>
                            <li><a href="${rootURL}j_spring_security_logout">Выход</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${rootURL}login">Вход</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </div>
</header>