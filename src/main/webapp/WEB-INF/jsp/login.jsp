<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/20
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function (){
            var msg = $("#getMsg").text();
            $("#btn").click(function (){
                var cardid = $("#cardid").val();
                var password=$("#password").val();

                if(cardid.length==0){
                    alert("用户名不能为空");
                    return false;
                }else if(password.length==0){
                    alert("密码不能为空");
                    return false;
                }else{
                    if(cardid.length!=18){
                        alert("身份证号长度不对");
                        return false;
                    }else if(password.length<6){
                        alert("密码长度不对");
                        return  false
                    }else{
                        return true;
                    }
                }
            })

            if(msg.length>0){
                alert(msg);
            }
        })
    </script>
</head>
<body>
<form action="/login" method="post">
    <table>
        <tr>
            <th>房产信息查询系统</th>
        </tr>
    </table>
    <p>请输入身份证号：<input type="text" name="cardid" id="cardid"/></p>
    <p>请输入密码：<input type="text" name="password" id="password"/></p>
    <input type="submit" value="登录">&nbsp;&nbsp;&nbsp;<a href="/toregister">注册</a>
</form>
<span style="display: none" id="getMsg">${msg}</span>
</body>
</html>

