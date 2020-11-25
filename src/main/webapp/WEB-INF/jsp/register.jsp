<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function (){

            if($("#getMsg").text().length>0){
                alert($("#getMsg").text());
            }
            if($("#getTrue").text().length>0){
                if(confirm("注册以成功，现在去登录吗")){
                    location.href="index.jsp";
                }else{
                    location.href="/toregister";
                }
            }
            $("#card").blur(function (){
                var card=$("#card").val();
                $.ajax({
                    url:"/getCard",
                    data:{"card":card},
                    success:function (result){
                        if(result!=1){
                            alert("该身份证已注册");
                        }
                    }
                })
            })

            $("#search").click(function (){
                var card=$("#card").val();
                var name=$("#name").val();
                var pwd1=$("#pwd1").val();
                var pwd2=$("#pwd2").val();
                if(card.length==0){
                    alert("身份证号不能为空");
                    return false;
                }else if(name.length==0){
                    alert("名称不能为空");
                    return false;
                }else if(pwd1.length==0){
                    alert("密码不能为空");
                    return false;
                }else if(pwd2.length==0){
                    alert("确认密码不能为空");
                    return false;
                }else {
                    if(pwd1!=pwd2){
                        alert("两次密码不一致");
                        return false
                    }else{
                        return  true;
                    }
                }
            })
        })
    </script>
</head>
<body>
<h1>账号注册</h1>
<form action="/login" method="post">
    <p>身份证号:<input type="text" name="cardid" id="card"></p>
    <p>用户名:<input type="text" name="name" id="name"></p>
    <p>密码:<input type="password" name="password" id="pwd1"></p>
    <p>确认密码:<input type="password" id="pwd2"></p>
    <p><input type="submit" value="注册" id="search"><input type="reset" value="重置" id="back"></p>
</form>
<span style="display:none;" id="getMsg">${msg}</span>
<span style="display:none;" id="getTrue">${getTrue}</span>
</body>
</html>
