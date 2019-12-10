<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<header class="main-header ">
    <a href="all-admin-index.html" class="logo">
        <span class="logo-mini"><img src="../img/logo.png"></span>
        <span class="logo-lg">
                    <img src="../img/export.png">
                    <i> SaaS外贸进出口平台</i>
                </span>
    </a>


    <script>

        window.onload = function () {
            if(${sessionScope.get("degree") == 0 || sessionScope.get("degree") ==1}){
                guanli();
            }else {
                myself();
            }
        }


        function guanli() {
            let url = "${pageContext.request.contextPath}/system/feedBackC/listNew.do";
            let  date = "";
            $.post(url,date,function (result) {
                let a = "";
                for(let i = 0; i <result.length;i++){
                    a+=" <li >\n" +
                        "                                    <a  onclick='manageInfo(\""+result[i].feedbackId+"\")'>\n" +
                        "                                        <i class=\"fa fa-user text-red\"></i> "+result[i].inputBy+":"+result[i].title+"\n" +
                        "                                    </a>\n" +
                        "                                </li>";
                    $("#newInfoMenu").html(a);
                }
                $("#newInfoNum").text(result.length);
                $("#newInfoNumLi").text("你有"+(result.length)+"个新消息");
            })
        }

        function manageInfo(obj) {
            let url="${pageContext.request.contextPath}/system/feedBackC/findById.do";
            let data = {"id":obj};
            $.post(url,data,function (feedbackC) {
                    $("#feedbackId").val(feedbackC.feedbackId);
                    $("#title").val(feedbackC.title);
                    $("#content").val(feedbackC.content);
                    $("#classType").val(feedbackC.classType)
                    $("#resolution").val(feedbackC.resolution);
                    $("#difficulty").val(feedbackC.difficulty);
                    $("#solveMethod").val(feedbackC.solveMethod);

            });
            document.getElementById('myModal12').click();
        }

        function myself() {
            let url = "${pageContext.request.contextPath}/system/feedBackC/comback.do";
            let  date = "";
            $.post(url,date,function (result) {

                let a = "";
                for(let i = 0; i <result.length;i++){
                    let b = result[i].resolution;
                    if(b == 1){
                         b= "已修改";
                    }else  if(b == 2){
                        b="无需修改";
                    }else  if(b == 3){
                        b="重复问题";
                    }else  if(b == 4){
                        b="描述不完整";
                    }else  if(b == 5){
                        b="无法再现";
                    }else  if(b == 6){
                        b="其他";
                    };
                    a+=" <li >\n" +
                        "                                    <a  onclick='info(\""+result[i].feedbackId+"\")'>\n" +
                        "                                        <i  class=\"fa fa-user text-red\"></i> "+result[i].title+" : "+result[i].solveMethod+"("+b+")\n" +
                        "                                    </a>\n" +
                        "                                </li>";
                    $("#newInfoMenu").html(a);
                }
                $("#newInfoNum").text(result.length);
                $("#newInfoNumLi").text("你有"+(result.length)+"个新消息");
            })
        }

        function info(obj){
            let url="${pageContext.request.contextPath}/system/feedBackC/findById.do";
            let data = {"id":obj};
            $.post(url,data,function (feedbackC) {
                $("#inputBy1").text(feedbackC.inputBy);
                $("#title1").text(feedbackC.title);
                $("#content1").text(feedbackC.content);
                if(feedbackC.classType == 1){
                    $("#classType1").text("管理");
                }else if(feedbackC.classType == 2){
                    $("#classType1").text("安全");
                }else if(feedbackC.classType == 3){
                    $("#classType1").text("建议");
                }else if(feedbackC.classType == 4){
                    $("#classType1").text("其他");
                }
                 $("#tel1").text(feedbackC.tel);
                $("#answerBy1").text(feedbackC.answerBy);
                if(feedbackC.resolution == 1){
                    $("#resolution1").text("已修改");
                }else if(feedbackC.resolution == 2){
                    $("#resolution1").text("无需修改");
                }else if(feedbackC.resolution == 3){
                    $("#resolution1").text("重复问题");
                }else if(feedbackC.resolution == 4){
                    $("#resolution1").text("描述不完整");
                }else if(feedbackC.resolution == 5){
                    $("#resolution1").text("无法再现");
                }else if(feedbackC.resolution == 6){
                    $("#resolution1").text("其他");
                }
                if(feedbackC.difficulty == 1){
                    $("#difficulty1").text("极难");
                }else    if(feedbackC.difficulty == 2){
                    $("#difficulty1").text("比较难");
                }else    if(feedbackC.difficulty == 3){
                    $("#difficulty1").text("有难度");
                }else   if(feedbackC.difficulty == 4){
                    $("#difficulty1").text("一般");
                }
                $("#solveMethod1").text(feedbackC.solveMethod);
            });
            document.getElementById('myModal11').click();
        }


    </script>
    <nav class="navbar navbar-static-top" id="nav1">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown messages-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-envelope-o"></i>
                        <span class="label label-success">10</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有10个邮件</li>
                        <li>
                            <ul class="menu">
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            系统消息
                                            <small><i class="fa fa-clock-o"></i> 5 分钟前</small>
                                        </h4>
                                        <p>欢迎登录系统?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            团队消息
                                            <small><i class="fa fa-clock-o"></i> 2 小时前</small>
                                        </h4>
                                        <p>你有新的任务了</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            Developers
                                            <small><i class="fa fa-clock-o"></i> Today</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            Sales Department
                                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            Reviewers
                                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">See All Messages</a></li>
                    </ul>
                </li>
                <!-- Notifications: style can be found in dropdown.less -->
                <li class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning" id="newInfoNum"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header" id="newInfoNumLi"></li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu" id="newInfoMenu">
                                <%--<li>
                                    <a href="#">
                                        <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-warning text-yellow"></i> Very long description here that may not
                                        fit into the page and may cause design problems
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-users text-red"></i> 5 new members joined
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                    </a>
                                </li>--%>

                                <%--<li >--%>
                                <%--<a href="#">--%>
                                <%--<i class="fa fa-user text-red"></i> ${o.inputBy}:${o.title}--%>
                                <%--</a>--%>
                                <%--</li>--%>
                            </ul>
                        </li>
                        <li class="footer"><a href="/system/feedBackC/list.do">View all</a></li>
                    </ul>
                </li>
                <!-- Tasks: style can be found in dropdown.less -->
                <li class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span class="label label-danger">9</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有9个新任务</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Design some buttons
                                            <small class="pull-right">20%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Create a nice theme
                                            <small class="pull-right">40%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">40% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Some task I need to do
                                            <small class="pull-right">60%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">60% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Make beautiful transitions
                                            <small class="pull-right">80%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">80% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                            </ul>
                        </li>
                        <li class="footer">
                            <a href="#">View all tasks</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" onchange="dianji()" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="../img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs"> ${sessionScope.loginUser.userName}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">

                            <p>
                                ${sessionScope.loginUser.userName}
                            </p>
                        </li>
                        <!-- Menu Body
                <li class="user-body">
                    <div class="row">
                        <div class="col-xs-4 text-center">
                            <a href="#">Followers</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Sales</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Friends</a>
                        </div>
                    </div>
                </li>-->
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">修改密码</a>
                            </div>
                            <div class="pull-right">
                                <a href="/logout.do" class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <script>

                    function dianji(){
                        $(".dropdown-menu").show();
                    }
                </script>

            </ul>
        </div>
    </nav>
</header>
<!-- 页面头部 /-->
<body>

<!-- 按钮触发模态框 管理员 -->
<button class="btn btn-primary btn-lg" id="myModal12" data-toggle="modal" data-target="#myModal" style="display: none;">开始演示模态框</button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">用户反馈</h4>
            </div>
            <div class="modal-body">

                <form id="infoForm" action="${ctx}/system/feedBackC/edit.do" method="post">
                    <div class="row data-type" style="margin: 0px">
                        <input type="text" class="form-control" id="feedbackId"  name="feedbackId" value="${feedbackC.feedbackId}" style="display: none;"/>

                        <div class="col-md-2 title">标题</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="标题" id="title" name="title" value="${feedbackC.title}" readonly="readonly"/>
                        </div>

                        <div class="col-md-2 title">内容</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="内容" id="content" name="content" value="${feedbackC.content}" readonly="readonly"/>
                        </div>

                        <div class="col-md-2 title">分类</div>
                        <div class="col-md-4 data">
                            <select class="form-control" id="classType" name="classType" disabled="disabled">
                                <option value="">请选择</option>
                                <option value="1" >管理</option>
                                <option value="2" >安全</option>
                                <option value="3" >建议</option>
                                <option value="4" >其他</option>
                            </select>
                        </div>

                            <div class="col-md-2 title">解决方式</div>
                            <div class="col-md-4 data">
                                <select id="resolution"  name="resolution" class="form-control">
                                    <option value="1"  >已修改</option>
                                    <option value="2"  >无需修改</option>
                                    <option value="3"  >重复问题</option>
                                    <option value="4"  >描述不完整</option>
                                    <option value="5"  >无法再现</option>
                                    <option value="6"  >其他</option>
                                </select>
                            </div>

                            <div class="col-md-2 title">解决难度</div>
                            <div class="col-md-4 data">
                                <select id="difficulty" name="difficulty"  class="form-control">
                                    <option value="1"  >极难</option>
                                    <option value="2"  >比较难</option>
                                    <option value="3"  >有难度</option>
                                    <option value="4"  >一般</option>
                                </select>
                            </div>

                            <div class="col-md-2 title rowHeight2x">解决办法</div>
                            <div class="col-md-4 data rowHeight2x">
                                <textarea class="form-control" rows="3" id="solveMethod" name="solveMethod">${feedbackC.solveMethod}</textarea>
                            </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" onclick="$('#infoForm').submit()" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 按钮触发模态框  普通用户-->
<button class="btn btn-primary btn-lg" id="myModal11" data-toggle="modal" data-target="#myModal1" >开始演示模态框</button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel1">我的反馈</h4>
            </div>
            <div class="modal-body">

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">提出人</div>
                    <div class="col-md-4 data" style="line-height:34px" id="inputBy1">
                        ${feedbackC.inputBy}
                    </div>

                    <div class="col-md-2 title">标题</div>
                    <div class="col-md-4 data" style="line-height:34px" id="title1">
                        ${feedbackC.title}
                    </div>

                    <div class="col-md-2 title">内容</div>
                    <div class="col-md-4 data" style="line-height:34px" id="content1">
                        ${feedbackC.content}
                    </div>

                    <div class="col-md-2 title">分类</div>
                    <div class="col-md-4 data" style="line-height:34px" id="classType1">
                        ${feedbackC.classType}
                    </div>

                    <div class="col-md-2 title">联系电话</div>
                    <div class="col-md-4 data" style="line-height:34px" id="tel1">
                        ${feedbackC.tel}
                    </div>

                    <div class="col-md-2 title">解决人</div>
                    <div class="col-md-4 data" style="line-height:34px" id="answerBy1">
                        ${feedbackC.answerBy}
                    </div>


                    <div class="col-md-2 title">解决方式</div>
                    <div class="col-md-4 data" style="line-height:34px" id="resolution1">
                        ${feedbackC.resolution}
                    </div>
                    <div class="col-md-2 title">解决难度</div>
                    <div class="col-md-4 data" style="line-height:34px" id="difficulty1">
                        ${feedbackC.difficulty}
                    </div>
                    <div class="col-md-2 title">解决办法</div>
                    <div class="col-md-4 data" style="line-height:34px" id="solveMethod1">
                        ${feedbackC.solveMethod}
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>


