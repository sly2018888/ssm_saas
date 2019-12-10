<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                用户反馈
                <small>反馈</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>

        <section class="content">
            <div class="panel panel-default">
                <div class="panel-heading">反馈信息</div>

                <form id="infoForm" action="${ctx}/system/feedBackC/edit.do" method="post">
                    <div class="row data-type" style="margin: 0px">
                        <input type="text" class="form-control"  name="feedbackId" value="${feedbackC.feedbackId}" style="display: none;"/>

                        <div class="col-md-2 title">标题</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="标题" name="title" id="title" value="${feedbackC.title}"/>
                        </div>

                        <div class="col-md-2 title">分类</div>
                        <div class="col-md-4 data">
                            <select id="classType" name="classType" class="form-control" >
                                <option value="" selected="selected">请选择</option>
                                <option value="1" >管理</option>
                                <option value="2" >安全</option>
                                <option value="3" >建议</option>
                                <option value="4" >其他</option>
                            </select>
                        </div>

                        <div class="col-md-2 title">联系方式</div>
                        <div class="col-md-4 data">
                            <input type="text" id="tel" class="form-control" placeholder="联系方式" name="tel" value="${feedbackC.tel}"/>
                        </div>

                        <div class="col-md-2 title">是否公开</div>
                        <div class="col-md-4 data">
                            <select id="isShare" name="isShare" class="form-control">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${feedbackC.isShare=='0'}">selected="selected"</c:if> >非公开</option>
                                <option value="1" <c:if test="${feedbackC.isShare=='1'}">selected="selected"</c:if> >公开</option>

                            </select>
                        </div>

                        <div class="col-md-2 title">内容</div>
                        <div class="col-md-10 data">
                            <input type="text" id="content" class="form-control" placeholder="内容" name="content" value="${feedbackC.content}"/>
                        </div>

                    </div>
                </form>
            </div>

            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="button" class="btn bg-btn bg-maroon" onclick="tijiao()">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
            <!--工具栏/-->
        </section>

    </div>
</body>
<script>
    function tijiao() {
        let title = $("#title").val();
        let classType = $("#classType").val();
        let tel = $("#tel").val();
        let content = $("#content").val();
        let isShare = $("#isShare").val();


        if(title == null || title ==""){
            $("#warning").html("请输入标题!");
            return;
        }
        if(content == null || content ==""){
            $("#warning").html ("请输入内容!");
            return;
        }
        if(isShare == null || isShare ==""){
            $("#warning").html = "请选择是否公开!";
            return;
        }
        if(tel == null || tel ==""){
            $("#warning").html = "请输入电话!";
            return;
        }
        $('#infoForm').submit();
    }

</script>

</html>