<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="rootURL" value="/"/>
<h1>Редактирование бага</h1>
<form:form cssClass="form-horizontal" role="form" commandName="bug">
    <form:hidden path="id"/>
    <div class="form-group <form:errors path="title">has-error</form:errors>">
        <form:label path="title" cssClass="col-sm-2 control-label">Название*</form:label>
        <div class="col-sm-5">
            <form:input path="title" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="title" cssClass="error"/>
        </div>
    </div>

    <div class="form-group <form:errors path="text">has-error</form:errors>">
        <form:label path="text" cssClass="col-sm-2 control-label">Текст*</form:label>
        <div class="col-sm-5">
            <form:textarea path="text" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="text" cssClass="error"/>
        </div>
    </div>

    <div class="form-group <form:errors path="assignee">has-error</form:errors>">
        <form:label path="assignee" cssClass="col-sm-2 control-label">Исполнитель*</form:label>
        <div class="col-sm-5">
            <form:select path="assignee" cssClass="form-control">
                <form:option value="${null}"/>
                <form:options items="${users}" itemValue="id" itemLabel="fullName"/>
            </form:select>
        </div>
        <div class="col-sm-5">
            <form:errors path="assignee" cssClass="error"/>
        </div>
    </div>

    <div class="form-group <form:errors path="reporter">has-error</form:errors>">
        <form:label path="reporter" cssClass="col-sm-2 control-label">Сообщил*</form:label>
        <div class="col-sm-5">
            <form:select path="reporter" cssClass="form-control">
                <form:option value="${null}"/>
                <form:options items="${users}" itemValue="id" itemLabel="fullName"/>
            </form:select>
        </div>
        <div class="col-sm-5">
            <form:errors path="reporter" cssClass="error"/>
        </div>
    </div>

    <div class="form-group <form:errors path="tags">has-error</form:errors>">
        <form:label path="tags" cssClass="col-sm-2 control-label">Теги</form:label>
        <div class="col-sm-5">
            <form:input path="tags" cssClass="form-control"/>
        </div>
        <div class="col-sm-5">
            <form:errors path="tags" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Сохранить</button>
        </div>
    </div>
</form:form>