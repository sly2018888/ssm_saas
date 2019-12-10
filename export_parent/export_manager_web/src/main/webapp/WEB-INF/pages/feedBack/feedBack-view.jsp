<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="../base.jsp"%>
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
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="/system/feedBackC/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        用户反馈
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">反馈信息</div>

            <div class="row data-type" style="margin: 0px">
                <div class="col-md-2 title">提出人</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.inputBy}
                </div>

                <div class="col-md-2 title">提出时间</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.inputTime}
                    <%--<fmt:formatDate value=" ${feedbackC.inputTime}" pattern="yyyy-MM-dd"/>--%>
                </div>

                <div class="col-md-2 title">标题</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.title}
                </div>

                <div class="col-md-2 title">内容</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.content}
                </div>

                <div class="col-md-2 title">分类</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.classType}
                </div>

                <div class="col-md-2 title">联系电话</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.tel}
                </div>

                <div class="col-md-2 title">解决人</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.answerBy}
                </div>

                <div class="col-md-2 title">解决时间</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.answerTime}
                   <%--<fmt:formatDate value=" ${feedbackC.answerTime}" pattern="yyyy-MM-dd"/>--%>
                </div>

                <div class="col-md-2 title">解决方式</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.resolution}
                </div>
                <div class="col-md-2 title">解决难度</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.difficulty}
                </div>
                <div class="col-md-2 title">解决办法</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${feedbackC.solveMethod}
                </div>

            </div>


        </div>

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->
    </section>


</div>
</body>
</html>