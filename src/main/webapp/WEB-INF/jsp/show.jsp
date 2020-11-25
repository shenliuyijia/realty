<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/main.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#btn").on("click",function (){
                    if($("#selectid").val()=="name"){
                        $("#a").val($("#textid").val());
                    }
                    if($("#selectid").val()=="cardid"){
                        $("#b").val($("#textid").val());
                    }

            })
        })
    </script>
</head>
<body>
<h1>房产信息查询系统</h1>
<p>用户名：${user.name} &nbsp;&nbsp;&nbsp;<a href="/logout">退出</a> </p>
<p><a href="/list">房产信息查询</a> </p>
<form method="post" action="/list">
    <p>查询类型:<select id="selectid" name="select" >
                    <c:if test="${selectid==1||selectid==null}">
                        <option value="1" selected>用户名</option>
                        <option value="2" >身份证</option>
                    </c:if>
                    <c:if test="${selectid==2}">
                        <option value="1" >用户名</option>
                        <option value="2" selected>身份证</option>
                    </c:if>

              </select>
        <input type="text" id="textid" name="name" value="${getName}">
        <input type="submit" value="查找" id="btn">
    </p>
</form>
    <%--<input type="hidden" name="name" id="a"><input type="hidden" name="cardid" id="b">--%>
<table class="layui-table" border="1px">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>序号</th>
        <th>项目名称</th>
        <th>产权人</th>
        <th>身份证号</th>
        <th>房屋类型</th>
        <th>使用面积</th>
        <th>建造时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageInfo.list}" var="s" varStatus="status">
        <tr>
            <%--<td>${status.count}</td>--%>
            <td>${s.id}</td>
            <td>${s.projectname}</td>
            <td>${s.users.name}</td>
            <td>${s.cardid}</td>
            <td>${s.housetype}</td>
            <td>${s.area}</td>
            <td><fmt:formatDate value="${s.buildtime}" pattern="yyyy-MM-dd"/> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="/list?pageNum=${pageInfo.firstPage}&name=${getName}&select=${selectid}">首页</a>
<c:if test="${pageInfo.hasPreviousPage}"><a href="/list?pageNum=${pageInfo.prePage}&name=${getName}&select=${selectid}>上一页</a></c:if>
<c:forEach items="${pageInfo.navigatepageNums}" var="i">
    <%--<a href="/list?pageNum=+${s}">${s}</a>--%>
    <c:if test="${pageInfo.pageNum==i}">
       <a>【${i}】</a>
    </c:if>
    <c:if test="${pageInfo.pageNum!=i}">
        <a href="/list?pageNum=${i}&name=${getName}&select=${selectid}">${i}</a>
    </c:if>
</c:forEach>
<c:if test="${pageInfo.hasNextPage}"> <a href="/list?pageNum=${pageInfo.nextPage}&name=${getName}&select=${selectid}">下一页</a></c:if>
<a href="/list?pageNum=${pageInfo.lastPage}&name=${getName}&select=${selectid}">末页</a>
</body>
</html>
