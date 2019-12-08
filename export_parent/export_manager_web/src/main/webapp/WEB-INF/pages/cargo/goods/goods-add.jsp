<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            产品管理
            <small>产品表单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">产品管理</a></li>
            <li class="active">产品表单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">产品信息</div>
            <form id="editForm" action="${ctx}/cargo/goods/edit.do" method="post">
                <input type="hidden" name="id" value="${goods.id}">
                <input type="hidden" name="factoryName" id="factoryName" value="${goods.factoryName}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">货号*(保存后不可修改)</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="货号" id="productNo" name="productNo" value="${goods.productNo}">
                    </div>

                    <div class="col-md-2 title">厂家简称*(保存后不可修改 )</div>
                    <div class="col-md-4 data">
                        <select class="form-control"  name="factoryId" id="factoryInfo" onchange="document.getElementById('factoryName').value=this.options[this.selectedIndex].text">
                            <option value="" selected="">请选择</option>
                            <c:forEach items="${factoryList}" var="factory">
                                <option value="${factory.id}">${factory.factoryName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-2 title">单价*</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="单价" id="price" name="price" value="${goods.price}">
                    </div>

                    <div class="col-md-2 title">产品长度</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="产品长度" name="sizeLenght" value="${goods.sizeLenght}">
                    </div>

                    <div class="col-md-2 title">产品宽度</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="产品宽度" name="sizeWidth" value="${goods.sizeWidth}">
                    </div>

                    <div class="col-md-2 title">产品高度</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="产品高度" name="sizeHeight" value="${goods.sizeHeight}">
                    </div>

                    <div class="col-md-2 title">颜色</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="颜色" name="color" value="${goods.color}">
                    </div>

                    <div class="col-md-2 title">包装</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="包装" name="packing" value="${goods.packing}">
                    </div>

                    <div class="col-md-2 title">包装单位</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="包装单位" name="packingUnit" value="${goods.packingUnit}">
                    </div>

                    <div class="col-md-2 title">集装箱类别20</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="集装箱类别20" name="type20" value="${goods.type20}">
                    </div>

                    <div class="col-md-2 title">集装箱类别40</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="集装箱类别40" name="type40" value="${goods.type40}">
                    </div>

                    <div class="col-md-2 title">集装箱类别40HC</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="集装箱类别40HC" name="type40hc" value="${goods.type40hc}">
                    </div>

                    <div class="col-md-2 title">数量*</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="数量" id="qty" name="qty" value="${goods.qty}">
                    </div>

                    <div class="col-md-2 title">体积*</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="体积" id="cbm" name="cbm" value="${goods.cbm}">
                    </div>

                    <div class="col-md-2 title">大箱尺寸长</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="大箱尺寸长" name="mpsizeLenght" value="${goods.mpsizeLenght}">
                    </div>

                    <div class="col-md-2 title">大箱尺寸宽</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="大箱尺寸宽" name="mpsizeWidth" value="${goods.mpsizeWidth}">
                    </div>

                    <div class="col-md-2 title">大箱尺寸高</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="大箱尺寸高" name="mpsizeHeight" value="${goods.mpsizeHeight}">
                    </div>

                    <div class="col-md-2 title ">备注</div>
                    <div class="col-md-4 data">
                        <textarea class="form-control" rows="3" name="remark">${goods.remark}</textarea>
                    </div>
                </div>
          </form>
        </div>
        <!--订单信息/-->
        <script>
            function yanzheng() {
                let factory = $("#factoryInfo option:selected").text();
                let productNo = $("#productNo").val();
                let price = $.trim($("#price").val());
                let qty = $.trim($("#qty").val());
                let cbm = $.trim($("#cbm").val());

                if(factory=="请选择"){
                    $("#errors").text('请选择厂家简称')
                }else if(productNo==''||productNo==null){
                    $("#errors").text('货号不能为空')
                }else if(price==''||price==null){
                    $("#errors").text('单价不能为空')
                }else if(qty==''||qty==null){
                    $("#errors").text('数量不能为空')
                }else if(cbm==''||cbm==null){
                    $("#errors").text('体积不能为空')
                }else{
                    document.getElementById("editForm").submit()
                }
            }
        </script>


        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='yanzheng()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <div id="errors" style="font-size:20px ; color: red"  align="center"></div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
</html>