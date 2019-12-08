<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../base.jsp" %>
<script src="../js/jquery-1.11.3.js"></script>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script >
    function formSubmit() {
        document.icform.action="http://note.java.itcast.cn/editVXUser.do";
        document.icform.submit();
    }

    function emailcheck(obj) {
        //1. 创建正则表达式
        var reg = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
        //2. 得到文本框中输入的值
        var value = obj.value;
        //3. 如果不匹配，在后面的span中显示错误信息，返回false
        if (reg.test(value)==false) {
            document.getElementById("emailInfo").innerHTML = "邮箱格式不正确";
            return false;
        }
        //4. 如果匹配，在后面的span中显示一个打勾图片，返回true
        else {
            document.getElementById("emailInfo").innerHTML = "<img src='img/success.jpg' width='15'/>";
            return true;
        }
    }
</script>
<script type="text/javascript" charset="UTF-8">
    function roleSelect(obj) {
        var value = obj.value;
        var url = "http://note.java.itcast.cn/selectRolesByCompanyId.do"
        var data = "companyId="+value;
        $.post(url,data,function (resp) {
            var json = eval("("+resp+")");
            $("#roles").empty();
            for ( var i = 0; i < json.length; i++) {
                $("#roles").append(
                    "<input name='roleIds' type='checkbox' value="+ json[i].id+">" + json[i].name + "</input>");
            }
        })

    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            新用户信息完善
            <small>信息完善</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">
        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">当前用户 [${user.userName}]</h3>
            </div>
            <div class="box-body">
                <form  name="icform" method="post" >
                    <input type="hidden" name="userid" value="${user.id}"/>
                    <input type="hidden" name="openid" value="${user.vxOpenid}"/>
                    <input type="hidden" name="nickname" value="${user.userName}"/>
                    <input type="hidden" name="creattime" value="${user.createTime}"/>
                    <input type="hidden" name="oldRoleIds" value="${userRoleStr}"/>
                    <%--提示用户修改邮箱密码--%>
                    <div class="form-group has-feedback">
                        <input onkeyup="emailcheck(this)" type="email" name="email" class="form-control" placeholder="Email">
                        <span id="emailInfo" style="color: red;"></span>
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" name="password" class="form-control" placeholder="密码">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
<%--                    选择公司--%>
                    <div class="col-md-4 data">
                        <select onchange="roleSelect(this)" id="companyId" name="companyid" style="width:150px">
                            <option value="">----请选择公司----</option>
                            <c:forEach items="${companyList}" var="company">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>
                        </select>
                    </div>
<%--                    角色分配--%>
                    <div class="textbox" id="centerTextbox">角色列表:[
                            <div id="roles"  style="text-align:left">
                                <c:set var="oldRoleIds" value=""/>
                                <c:forEach items="${roleList}" var="role" varStatus="vs">
                                     <span style="padding:3px;margin-right:30px;width: 160px;display: inline-block">
                                         <input type="checkbox" name="roleIds" value="${role.id}"/>
                                         ${role.name}
                                     </span>
                                </c:forEach>
                            </div>]
                    </div>
                </form>
            </div>
        </div>

        <div class="box-tools text-center">
            <button type="button" class="btn bg-maroon" onclick="formSubmit()">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
    </section>
</div>
</body>

</html>