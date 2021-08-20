<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-20
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data"> <!--데이터를 어떻게 인코딩 할거인지. "multipart/form-data" 로 지정해줘야 파일이 올라감-->
    <input type="text" name="title" value="test.....">
    <input type="file" name="uploadFiles" multiple>   <!--multiple 과거에 파일을 첨부할때 하나만 가능했다.입력하는 창은 하나인데 한꺼번에 여러개의 파일을 올릴 수 있다. -->
    <button type="submit">SUBMIT</button>
</form>
</body>
</html>
