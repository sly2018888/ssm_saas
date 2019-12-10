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
            委托单管理
            <small>委托单</small>
        </h1>
        <ol class="breadcrumb"></ol>
    </section>

    <!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">委托单详情</div>

            <div class="row data-type" style="margin: 0px">
                <div class="col-md-2 title">委托单号</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.shippingOrderId}
                </div>

                <div class="col-md-2 title">发货人</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.shipper}
                </div>

                <div class="col-md-2 title">收货人</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.consignee}
                </div>

                <div class="col-md-2 title">信用证编号</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.lcNo}
                </div>

                <div class="col-md-2 title">装货港</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.portOfLoading}
                </div>

                <div class="col-md-2 title">转运港</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.portOfTrans}
                </div>

                <div class="col-md-2 title">卸货港</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.portOfDischarge}
                </div>

                <div class="col-md-2 title">是否分批</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.isBatch}
                </div>

                <div class="col-md-2 title">是否转船</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.isTrans}
                </div>

                <div class="col-md-2 title">份数</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.copyNum}
                </div>

                <div class="col-md-2 title">扼要说明</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.remark}
                </div>

                <div class="col-md-2 title">运输要求</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.specialCondition}
                </div>



                <div class="col-md-2 title">运费说明</div>
                <div class="col-md-4 data" style="line-height:34px">
                    ${shipping.freight}
                </div>
            </div>
        </div>

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">该委托单下装箱单列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                            <th class="sorting">装箱单号运号</th>
                            <th class="sorting">卖方</th>
                            <th class="sorting">买方</th>
                            <th class="sorting">发票号</th>
                            <th class="sorting">发票日期</th>
                            <th class="sorting">唛头</th>
                            <th class="sorting">备注</th>
                            <th class="sorting">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td>1</td>
                                <td>${packingListC.packingListId}</td>
                                <td align="center">
                                        ${packingListC.seller}
                                </td>
                                <td>${packingListC.buyer}</td>
                                <td>${packingListC.invoiceNo}</td>
                                <td>${packingListC.invoiceDate}</td>
                                <td>${packingListC.marks}</td>
                                <td>${packingListC.descriptions}</td>
                                <td><font color="red">已报运</font></td>
                                <td>
                                    <a href="${ctx }/cargo/packing/list.do?id=${o.id}">[查看]</a>
                                    <a href="/cargo/export/exportPdf.do?id=${o.id}">[下载]</a>
                                </td>
                            </tr>
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