<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="baseUrl" required="true" type="java.lang.String" description="начальный url" %>
<%@attribute name="pager" required="true" type="ru.kds.util.Pager" %>
<c:if test="${pager.totalPages > 1}">
<c:url var="firstUrl" value="${baseUrl}" />
<c:url var="lastUrl" value="${baseUrl}/${pager.totalPages}" />
<c:url var="prevUrl" value="${baseUrl}/${pager.page - 1}" />
<c:url var="nextUrl" value="${baseUrl}/${pager.page + 1}" />

    <ul class="pagination">
        <c:choose>
            <c:when test="${pager.page == 1}">
                <li class="disabled"><span>&lt;&lt;</span></li>
                <li class="disabled"><span>&lt;</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${1}" end="${pager.totalPages}">
            <c:url var="pageUrl" value="${baseUrl}/${i}" />
            <c:choose>
                <c:when test="${i == pager.page}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${pager.page == pager.totalPages}">
                <li class="disabled"><span>&gt;</span></li>
                <li class="disabled"><span>&gt;&gt;</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</c:if>