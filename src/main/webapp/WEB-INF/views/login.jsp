<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:url var="rootURL" value="/"/>

<div class="login">
    <div class="row">
        <div class="col-md-4">
            <h3>Вход</h3>

            <c:if test="${not empty param.login_error}">
                <div class="error">
                    Неверный логин или пароль
                </div>
            </c:if>
            <form role="form" action="${rootURL}j_spring_security_check" method="POST">
                <div class="form-group">
                    <label for="j_username">Email</label>
                    <input id="j_username" type="email" name="j_username" class="form-control">
                </div>
                <div class="form-group">
                    <label for="j_password">Пароль</label>
                    <input type="password" class="form-control" id="j_password" name="j_password">
                </div>

                <button type="submit" class="btn btn-default">Войти</button>
            </form>
        </div>
    </div>
</div>
