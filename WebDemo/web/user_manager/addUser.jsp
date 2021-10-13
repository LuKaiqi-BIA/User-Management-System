<%--
  Created by IntelliJ IDEA.
  User: kevinlu
  Date: 28/9/21
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        KE.show({id:"ecp",width:"800px",height:"300px"});
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">用户管理</a></li>
        <li><a href="#">添加用户</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="../userManager.do" method="post">
        <input type="hidden" name="flag" value="addUser"/>
        <ul class="forminfo">
            <li>
                <label>用户名</label>
                <input name="username" type="text" class="dfinput" /></li>
            <li>
            <li>
                <label>用户密码</label>
                <input name="userpwd" type="text" class="dfinput" /><i></i></li>
            <li>
                <label>性别</label>
                <input name="usersex" type="radio" value="1" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="usersex" type="radio" value="0" />女

            </li>
            <li>
                <label>联系方式</label>
                <input name="phonenumber" type="text" class="dfinput" />
            </li>
            <li>
                <label>QQ号</label>
                <input name="qqnumber" type="text" class="dfinput" />
            </li>
            <li>
                <label>&nbsp;</label>
                <input type="submit" class="btn" value="确认保存" />
            </li>
        </ul>
    </form>


</div>

</body>
</html>
