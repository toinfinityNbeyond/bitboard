<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-18
  Time: 오후 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Page</h1>

<h4>${pageMaker}</h4>

<ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>${dto}</li>
    </c:forEach>
</ul>

<hr/>

<style>
    .pageList {
        list-style: none;
        display: flex;
        flex-direction: row;
    }
    .pageList li {
        margin-left: 0.3em;
        background-color: green;
        font-family: "Roboto Light";
        border: 1px solid greenyellow;
    }
</style>
<ul class="pageList">
    <c:if test="${pageMaker.prev}">
        <li><a href="/board/list?page=${pageMaker.start - 1}&size=${pageMaker.size}">PREV</li>
    </c:if>

        <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
        <li><a href="/board/list?page=${page}&size=${pageMaker.size}">${page}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next}">
        <li><a href="/board/list?page=${pageMaker.end + 1}&size=${pageMaker.size}">NEXT</a></li>
    </c:if>

</ul>

</body>
</html>
