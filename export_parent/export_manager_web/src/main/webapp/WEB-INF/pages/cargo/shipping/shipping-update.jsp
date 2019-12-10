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
            委托管理
            <small>修改委托单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">委托管理</a></li>
            <li class="active">修改委托单</li>
        </ol>
    </section>
    <!-- 正文区域 -->
    ${shipping.shippingOrderId}
    <section class="content">
        <section class="content">
            <form id="updatePackingForm" action="/cargo/shipping/edit.do?id=${packingListC.packingListId}" method="post">
                <input type="hidden" name="shippingOrderId" value="${packingListC.packingListId}" />
                <div class="panel panel-default">
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-2 title">发货人</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="发货人" id="shipper" name="shipper"
                                   value="${shipping.shipper}"/>
                        </div>

                        <div class="col-md-2 title">收货人</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="收货人" id="consignee" name="consignee"
                                   value="${shipping.consignee}"/>
                        </div>

                        <div class="col-md-2 title">信用证号</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="信用证号" id="lcNo" name="lcNo"
                                   value="${shipping.lcNo}"/>
                        </div>

                        <div class="col-md-2 title">装货港</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="装货港" id="portOfLoading"
                                   name="portOfLoading"
                                   value="${shipping.portOfLoading}"/>
                        </div>
                        <div class="col-md-2 title">转运港</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="转运港" id="portOfTrans" name="portOfTrans"
                                   value="${shipping.portOfTrans}"/>
                        </div>
                        <div class="col-md-2 title">卸货港</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="卸货港" id="portOfDischarge"
                                   name="portOfDischarge"
                                   value="${shipping.portOfDischarge}"/>
                        </div>

                        <div class="col-md-2 title">是否分批</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="是否分批" id="isBatch" name="isBatch"
                                   value="${shipping.isBatch}"/>
                        </div>

                        <div class="col-md-2 title">是否转船</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="是否转船" id="isTrans" name="isTrans"
                                   value="${shipping.isTrans}"/>
                        </div>

                        <div class="col-md-2 title">份数</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="份数" id="copyNum" name="copyNum"
                                   value="${shipping.copyNum}"/>
                        </div>

                        <div class="col-md-2 title">扼要说明</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="扼要说明" id="remark" name="remark"
                                   value="${shipping.remark}"/>
                        </div>

                        <div class="col-md-2 title">运输要求</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="运输要求" id="specialCondition"
                                   name="specialCondition"
                                   value="${shipping.specialCondition}"/>
                        </div>

                        <div class="col-md-2 title">运费说明</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="运费说明" id="freight"
                                   name="freight"
                                   value="${shipping.freight}"/>
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
                                <th class="sorting">装箱单号</th>
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
                                    <a href="${ctx }/cargo/export/toView.do?id=${packingListC.packingListId}">[查看]</a>
                                    <a href="/cargo/export/exportPdf.do?id=${packingListC.packingListId}">[下载]</a>
                                </td>
                            </tr>

                            <%--</c:forEach>--%>
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
    </section>
</div>
<!-- 内容区域 /-->
</body>
<script src="../../../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../../../css/style.css">
<script>
    $('#invoiceDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>

</html>