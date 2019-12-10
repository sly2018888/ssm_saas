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
                        <input type="text" class="form-control" placeholder="标题" id="title" name="title" value="${feedbackC.title}"/>
                    </div>

                    <div class="col-md-2 title">内容</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="内容" id="content" name="content" value="${feedbackC.content}"/>
                    </div>

                    <div class="col-md-2 title">分类</div>
                    <div class="col-md-4 data">
                        <select class="form-control" id="classType" name="classType">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${feedbackC.classType=='1'}">selected="selected"</c:if> >管理</option>
                            <option value="2" <c:if test="${feedbackC.classType=='2'}">selected="selected"</c:if> >安全</option>
                            <option value="3" <c:if test="${feedbackC.classType=='3'}">selected="selected"</c:if> >建议</option>
                            <option value="4" <c:if test="${feedbackC.classType=='4'}">selected="selected"</c:if> >其他</option>
                        </select>
                    </div>

                    <c:if test="${myId == feedbackC.createBy}">
                    <div class="col-md-2 title">是否公开</div>
                    <div class="col-md-4 data">
                        <select class="form-control" id="isShare" name="isShare">
                            <option value="0" <c:if test="${feedbackC.isShare=='0'}">selected="selected"</c:if> >非公开</option>
                            <option value="1" <c:if test="${feedbackC.isShare=='1'}">selected="selected"</c:if> >公开</option>
                        </select>
                    </div>
                    </c:if>


                    <%--管理员--%>
                    <c:if test="${degree == 0 or degree == 1 }">
                    <div class="col-md-2 title">解决方式</div>
                    <div class="col-md-4 data">
                        <select id="resolution" name="resolution" class="form-control">
                            <option value="1" <c:if test="${feedbackC.resolution=='1'}">selected="selected"</c:if> >已修改</option>
                            <option value="2" <c:if test="${feedbackC.resolution=='2'}">selected="selected"</c:if> >无需修改</option>
                            <option value="3" <c:if test="${feedbackC.resolution=='3'}">selected="selected"</c:if> >重复问题</option>
                            <option value="4" <c:if test="${feedbackC.resolution=='4'}">selected="selected"</c:if> >描述不完整</option>
                            <option value="5" <c:if test="${feedbackC.resolution=='5'}">selected="selected"</c:if> >无法再现</option>
                            <option value="6" <c:if test="${feedbackC.resolution=='6'}">selected="selected"</c:if> >其他</option>
                        </select>
                    </div>

                    <div class="col-md-2 title">解决难度</div>
                    <div class="col-md-4 data">
                        <select id="difficulty" name="difficulty"  class="form-control">
                            <option value="1" <c:if test="${feedbackC.difficulty=='1'}">selected="selected"</c:if> >极难</option>
                            <option value="2" <c:if test="${feedbackC.difficulty=='2'}">selected="selected"</c:if> >比较难</option>
                            <option value="3" <c:if test="${feedbackC.difficulty=='3'}">selected="selected"</c:if> >有难度</option>
                            <option value="4" <c:if test="${feedbackC.difficulty=='4'}">selected="selected"</c:if> >一般</option>
                        </select>
                    </div>

                    <div class="col-md-2 title rowHeight2x">解决办法</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea class="form-control" rows="3" id="solveMethod" name="solveMethod">${feedbackC.solveMethod}</textarea>
                    </div>
                    </c:if>
                </div>
                </form>
            </div>

            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="button" class="btn bg-default" onclick="tijiao()">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
            <!--工具栏/-->
        </section>

    </div>
</body>
<script>
    function tijiao() {
        let title = $("#title").val();
        let content = $("#content").val();
        let solveMethod = $("#solveMethod").val();


        if(title == null || title ==""){
            $("#warning").html("请输入标题!");
            return;
        }

        if(content == null || content ==""){
            $("#warning").html = "请输入内容!";
            return;
        }


        if(solveMethod == null || solveMethod ==""){
            $("#warning").html = "请选择解决办法!";
            return;
        }
        $('#infoForm').submit();
    }
</script>

</html>