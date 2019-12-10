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

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">反馈列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="/system/feedBackC/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">提出人</th>
                        <th class="sorting">提出时间</th>
                        <th class="sorting">标题</th>
                        <th class="sorting">内容</th>
                        <th class="sorting">分类</th>
                        <th class="sorting">联系电话</th>
                        <th class="sorting">解决办法</th>
                        <th class="sorting">解决难度</th>
                        <th class="sorting">解决方式</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="o"  varStatus="st">
                        <tr>
                            <c:if test="${o.isShare == 1 or o.createBy == myId}">
                            <td><input type="checkbox" name="id" value="${o.feedbackId }"/></td>
                            <td>${st.count }</td>
                            <td>${o.inputBy }</td>
                            <td><fmt:formatDate value="${o.inputTime}" pattern="yyyy-MM-dd"/></td>
                            <td>${o.title }</td>
                            <td>${o.content }</td>
                            <td>
                                <c:if test="${o.classType=='1'}">管理</c:if>
                                <c:if test="${o.classType=='2'}">安全</c:if>
                                <c:if test="${o.classType=='3'}">建议</c:if>
                                <c:if test="${o.classType=='4'}">其他</c:if>
                            </td>
                            <td>${o.tel }</td>
                            <td>${o.solveMethod}</td>
                            <td>
                                <c:if test="${o.difficulty=='1'}">极难 </c:if>
                                <c:if test="${o.difficulty=='2'}">比较难</c:if>
                                <c:if test="${o.difficulty=='3'}">有难度</c:if>
                                <c:if test="${o.difficulty=='4'}">一般 </c:if>
                            </td>
                            <td>
                                <c:if test="${o.resolution=='1'}">已修改   </c:if>
                                <c:if test="${o.resolution=='2'}">无需修改 </c:if>
                                <c:if test="${o.resolution=='3'}">重复问题 </c:if>
                                <c:if test="${o.resolution=='4'}">描述不完整 </c:if>
                                <c:if test="${o.resolution=='5'}">无法再现  </c:if>
                                <c:if test="${o.resolution=='6'}">其他    </c:if>
                            </td>
                            <td>
                                <c:if test="${o.state=='0'}">未处理   </c:if>
                                <c:if test="${o.state=='1'}">已处理 </c:if>
                            </td>
                            <td>
                                <a href="/system/feedBackC/toView.do?id=${o.feedbackId }">查看</a>

                                <c:if test="${(o.createBy == myId and o.state == 0) or degree==0 or degree == 1 }">
                                    <c:if test="${o.state == 0}">
                                <a href="/system/feedBackC/toUpdate.do?id=${o.feedbackId }">修改</a>
                                    </c:if>
                                </c:if>

                                <c:if test="${o.createBy == myId and o.state == 0}">
                                <a href="/system/feedBackC/delete.do?id=${o.feedbackId }">删除</a>
                                </c:if>
                            </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../common/page.jsp">
                <jsp:param value="${ctx}/system/feedBackC/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>