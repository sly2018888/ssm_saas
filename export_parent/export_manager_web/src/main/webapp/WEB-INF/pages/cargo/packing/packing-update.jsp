<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
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
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            装箱管理
            <small>新增装箱单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">装箱管理</a></li>
            <li class="active">新增装箱单</li>
        </ol>
    </section>
    <!-- 正文区域 -->
    <section class="content">
        <form action="/cargo/packing/edit.do" method="post">
            <div class="panel panel-default">
                <input type="text" id="packingListId" name="packingListId" value="${packingListC.packingListId}" style="display: none"></input>
                <div class="row data-type" style="margin: 0px">

                    <div class="col-md-2 title">卖方</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="卖方" name="seller" value="${packingListC.seller}"/>
                    </div>

                    <div class="col-md-2 title">买方</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="买方" name="buyer" value="${packingListC.buyer}"/>
                    </div>

                    <div class="col-md-2 title">发票号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="发票号" name="invoiceNo" value="${packingListC.invoiceNo}"/>
                    </div>

                    <div class="col-md-2 title">发票日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="发票日期"  name="invoiceDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${packingListC.invoiceDate}" pattern="yyyy-MM-dd"/>" id="invoiceDate">
                        </div>
                    </div>

                    <div class="col-md-2 title">唛头</div>
                    <div class="col-md-4 data">
                        <input type="text" name="marks" class="form-control" placeholder="唛头" value="${packingListC.marks}"/>
                    </div>

                    <input type="text" id="state" name="state" value="${packingListC.state}" style="display: none"></input>


                    <div class="col-md-2 title">备注</div>
                    <div class="col-md-4 data">
                        <input type="text" name="descriptions" class="form-control" placeholder="备注" value="${packingListC.descriptions}"/>
                    </div>

                </div>
            </div>

            <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">已报运报运单列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">
                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                                <th class="sorting">报运号</th>
                                <th class="sorting">货物/附件</th>
                                <th class="sorting">信用证号</th>
                                <th class="sorting">收货地址</th>
                                <th class="sorting">装运港</th>
                                <th class="sorting">目的港</th>
                                <th class="sorting">运输方式</th>
                                <th class="sorting">价格条件</th>
                                <th class="sorting">状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="o" varStatus="status">
                                <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                    <td>
                                        <c:if test="${fn:contains(packingListC.exportIds,o.id)}">
                                        <input type="checkbox" name="id" value="${o.id}" checked="checked"/>
                                        </c:if>
                                        <c:if test="${!fn:contains(packingListC.exportIds,o.id)}">
                                            <input type="checkbox" name="id" value="${o.id}"/>
                                        </c:if>
                                    </td>
                                    <td>${o.id}</td>
                                    <td align="center">
                                            ${o.proNum}/${o.extNum}
                                    </td>
                                    <td>${o.lcno}</td>
                                    <td>${o.consignee}</td>
                                    <td>${o.shipmentPort}</td>
                                    <td>${o.destinationPort}</td>
                                    <td>${o.transportMode}</td>
                                    <td>${o.priceCondition}</td>
                                    <td><font color="red">已报运</font></td>
                                    <td>
                                        <a href="${ctx }/cargo/export/toView.do?id=${o.id}">[查看]</a>
                                        <a href="/cargo/export/exportPdf.do?id=${o.id}">[下载]</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--数据列表/-->
                        <!--工具栏/-->

                        <%--<div class="box-footer">--%>
                            <%--<jsp:include page="../../common/page.jsp">--%>
                                <%--<jsp:param value="cargo/packing/list.do" name="pageUrl"/>--%>
                            <%--</jsp:include>--%>
                        <%--</div>--%>
                    </div>
                    <!-- 数据表格 /-->
                </div>
                <!-- /.box-body -->

            </div>

            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="submit"  class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
        </form>
        <!--工具栏/-->
    </section>
</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#invoiceDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>

</html>