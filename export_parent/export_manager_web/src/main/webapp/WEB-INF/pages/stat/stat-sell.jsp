<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            统计分析
            <small>产品销售统计</small>
        </h1>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div id="main" style="width: 600px;height:600px;"></div>
        </div>
    </section>
</div>
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../../plugins/echarts/echarts.min.js"></script>
</body>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    $.get('/stat/sellCharts.do').done(function (data) {
        // 使用刚指定的配置项和数据显示图表。
        // data:[{"name":"JK040/JK1060338","value":3000},{"name":"JK041/JK1060339","value":3000},{"name":"JK1060338/JK338","value":2400}]
        let values = [];
        let titles = [];
        for (let i = 0; i < data.length; i++) {
            titles[i] = data[i].name;
            values[i] = data[i].value;
        }
        myChart.setOption(
            option = {
                title: {
                    left: 'center',
                    text: '产品销量排行',
                },
                xAxis: {
                    type: 'category',
                    data: titles,
                    axisLabel: {
                        interval:0,
                        rotate:60
                    }
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: values,
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            label: {
                                show: true, //开启显示
                                position: 'top', //在上方显示
                                textStyle: { //数值样式
                                    color: 'black',
                                    fontSize: 10
                                }
                            }
                        }
                    }
                }],
                grid: [{
                    left: '15%',//因旋转导致名字太长的类目造成遮蔽，可以配合这两个属性
                    bottom:'25%'// 分别表示：距离左边距和底部的距离，具体数值按实际情况调整
                }],
            }
        )
    });
</script>

</html>