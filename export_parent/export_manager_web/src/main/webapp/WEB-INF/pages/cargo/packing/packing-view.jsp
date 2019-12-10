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
    <!-- 页面meta /-->
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>出口报运单</small>
        </h1>
        <ol class="breadcrumb"></ol>
    </section>

    <!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">装箱单详情</div>

            <div class="row data-type" style="margin: 0px">
                <div class="col-md-2 title">装箱号</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${packingListC.packingListId}
                </div>

                <div class="col-md-2 title">卖方</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${packingListC.seller}
                </div>

                <div class="col-md-2 title">买方</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${packingListC.buyer}
                </div>

                <div class="col-md-2 title">发票号</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${packingListC.invoiceNo}
                </div>

                <div class="col-md-2 title">发票日期</div>
                <div class="col-md-4 data" style="line-height:34px">
                        <fmt:formatDate value="${packingListC.invoiceDate}" pattern="yyyy-MM-dd"/>
                </div>

                <div class="col-md-2 title">唛头</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${packingListC.marks}
                </div>

                <div class="col-md-2 title">描述</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${packingListC.descriptions}
                </div>
            </div>
        </div>

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">该装箱单下报运单列表</h3>
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
                        <c:forEach items="${list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td>${status.count}</td>
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
                </div>
                <!-- 数据表格 /-->
            </div>
            <!-- /.box-body -->


        </div>
    </section>


</div>
<!-- 内容区域 /-->
</body>

</html>