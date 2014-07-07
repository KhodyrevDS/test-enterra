<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="rootURL" value="/"/>
<h1>Редактирование пользователя</h1>
<form:form cssClass="form-horizontal" role="form" commandName="user">
    <form:hidden path="id"/>
    <div class="form-group <form:errors path="lastName">has-error</form:errors>">
        <form:label path="lastName" cssClass="col-sm-2 control-label">Фамилия*</form:label>
        <div class="col-sm-5">
            <form:input path="lastName" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="lastName" cssClass="error"/>
        </div>
    </div>

    <div class="form-group <form:errors path="firstName">has-error</form:errors>">
        <form:label path="firstName" cssClass="col-sm-2 control-label">Имя*</form:label>
        <div class="col-sm-5">
            <form:input path="firstName" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="firstName" cssClass="error"/>
        </div>
    </div>

    <div class="form-group <form:errors path="email">has-error</form:errors>">
        <form:label path="email" cssClass="col-sm-2 control-label">Email*</form:label>
        <div class="col-sm-5">
            <form:input path="email" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="email" cssClass="error"/>
        </div>
    </div>

    <div class="form-group <form:errors path="password">has-error</form:errors>">
        <form:label path="email" cssClass="col-sm-2 control-label">Password<c:if test="${user.id == null}">*</c:if></form:label>
        <div class="col-sm-5">
            <input type="text" id="password" name="password" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="password" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="roles" cssClass="col-sm-2 control-label">Роли*</form:label>
        <div class="col-sm-5">
            <form:checkboxes path="roles" items="${roles}" itemLabel="humanName" itemValue="id" delimiter="<br/>"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="roles" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Сохранить</button>
        </div>
    </div>
</form:form>