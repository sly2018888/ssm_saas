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
<script>

</script>


<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            发票管理
            <small>新增发票</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">发票管理</a></li>
            <li class="active">新增发票</li>
        </ol>
    </section>
    <!-- 正文区域 -->
    <section class="content">
        <form id="addInvoiceForm" action="/cargo/invoice/edit.do" method="post">
            <div class="panel panel-default">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">提单号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="提单号" id="blNo" name="blNo" value="${invoice.blNo}"/>
                    </div>

                    <div class="col-md-2 title">贸易条款</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="贸易条款" id="tradeTerms" name="tradeTerms" value="${invoice.tradeTerms}"/>
                    </div>
                </div>
            </div>

            <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">已报运委托单列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">
                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <td><input type="checkbox" name="selid" id="selid" onclick="checkAll('id',this)"></td>
                                <th class="sorting">委托号</th>
                                <th class="sorting">空运/海运</th>
                                <th class="sorting">货主</th>
                                <th class="sorting">提单抬头</th>
                                <th class="sorting">正本通知人</th>
                                <th class="sorting">信用证</th>
                                <th class="sorting">转运港</th>
                                <th class="sorting">转船港</th>
                                <th class="sorting">卸货港</th>
                                <th class="sorting">装期</th>
                                <th class="sorting">效期</th>
                                <th class="sorting">运输要求</th>
                                <th class="sorting">运费说明</th>
                                <th class="sorting">复核人</th>
                                <th class="sorting">状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${page.list}" var="o" varStatus="status">
                                    <c:if test="${o.shippingState == 1}">
                                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                        <td><input type="checkbox" name="id" id="id" value="${o.shippingOrderId}"/></td>
                                        <td>${o.shippingOrderId}</td>
                                        <td align="center">
                                            <c:if test="${o.orderType eq 'SEA'}">
                                                海运
                                            </c:if>
                                            <c:if test="${o.orderType eq 'AIR'}">
                                                空运
                                            </c:if>
                                        </td>
                                        <td>${o.shipper}</td>
                                        <td>${o.consignee}</td>
                                        <td>${o.notifyParty}</td>
                                        <td>${o.lcNo}</td>
                                        <td>${o.portOfLoading}</td>
                                        <td>${o.portOfTrans}</td>
                                        <td>${o.portOfDischarge}</td>
                                        <td><fmt:formatDate value="${o.loadingDate}" pattern="yyyy-MM-dd"/>
                                        </td>
                                        <td><fmt:formatDate value="${o.limitDate}" pattern="yyyy-MM-dd"/></td>
                                        <td>${o.specialCondition}</td>
                                        <td>${o.freight}</td>
                                        <td>${o.checkBy}</td>
                                        <td><c:if test="${o.shippingState==0}">草稿</c:if>
                                            <c:if test="${o.shippingState==1}"><font color="green">已上报</font></c:if></td>
                                        <td>
                                            <a href="${ctx }/cargo/shippingOrder/toView.do?id=${o.shippingOrderId}">[查看]</a>
                                            <a href="/cargo/shippingOrder/exportPdf.do?id=${o.shippingOrderId}">[下载]</a>
                                        </td>
                                    </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>

                        <%--<div class="box-footer">--%>
                            <%--<jsp:include page="../../common/page.jsp">--%>
                                <%--<jsp:param value="cargo/packing/list.do" name="pageUrl"/>--%>
                            <%--</jsp:include>--%>
                        <%--</div>--%>
                        <!--数据列表/-->
                        <!--工具栏/-->
                    </div>
                    <!-- 数据表格 /-->
                </div>
                <!-- /.box-body -->

            </div>

            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="submit" id="save"   class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span type="text" name="warning" id="warning" style="color: red;font-size: 15px"></span>
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

    function tijiao() {

        var id = getCheckId();
        let id = $("checkbox").val();
        let blNo = $("#blNo").val();
        let tradeTerms = $("#tradeTerms").val();

        // if(id == null || id ==""){
        //     $("#warning").html("请至少选择一个报运单项");
        //     return;
        // }
        if(blNo == null || blNo ==""){
            $("#warning").html("请输入提单号!");
            return;
        }
        if(tradeTerms == null || tradeTerms ==""){
            $("#warning").html ("请输入贸易条款!");
            return;
        }
        if(id) {
            $("#addInvoiceForm").submit();
        }else{
            $("#warning").html ("请勾选待处理的记录，且每次只能勾选一个!");
            return;
        }
    }





</script>
</html>