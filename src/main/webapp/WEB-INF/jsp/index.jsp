<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <p/>

        <form method="post" action="users">
            Select User: <select name="userId">
            <option value="100000" selected>User</option>
            <option value="100001">Admin</option>
            <option value="100002">User_1</option>
            <option value="100003">User_2</option>
            <option value="100004">User_3</option>
        </select>
            <button type="submit">Select</button>
        </form>
        <ul>
            <li><a href="users">Users</a></li>
            <%--<li><a href="meals"><spring:message code="meal.title"/></a></li>--%>
        </ul>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>