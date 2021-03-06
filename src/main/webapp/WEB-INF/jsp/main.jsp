<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>后台Demo系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/images/Logo_40.jpg" type="image/x-icon">
    <!-- layui.css -->
    <link href="/layui/css/layui.css" rel="stylesheet" />
    <!-- font-awesome.css -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!-- animate.css -->
    <link href="/css/animate.min.css" rel="stylesheet" />
    <!-- 本页样式 -->
    <link href="/css/main.css" rel="stylesheet" />

</head>
<body>
<div class="layui-layout layui-layout-admin">
    <!--顶部-->
    <div class="layui-header">
        <div class="ht-console">
            <div class="ht-user">
                <a class="ht-user-name" href="/main"><span class="sys-title">后台Demo系统</span></a>
            </div>
        </div>
        <div class="ht-console">
            <!-- 天气信息 -->
            <div class="weather" style="padding: 5px 5px;height: 60px;box-sizing: border-box;margin-top: 10px;">
                <div id="tp-weather-widget"></div>
                <script>(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))</script>
                <script>tpwidget("init", {
                    "flavor": "slim",
                    "location": "WX4FBXXFKE4F",
                    "geolocation": "enabled",
                    "language": "zh-chs",
                    "unit": "c",
                    "theme": "chameleon",
                    "container": "tp-weather-widget",
                    "bubble": "disabled",
                    "alarmType": "badge",
                    "color": "#FFFFFF",
                    "uid": "U9EC08A15F",
                    "hash": "039da28f5581f4bcb5c799fb4cdfb673"
                });
                tpwidget("show");</script>
            </div>
        </div>
        <ul class="ht-nav layui-nav" lay-filter="nav">
            <li class="ht-nav-item">
                <a href="javascript:;" id="individuation"><i class="fa fa-tasks fa-fw" style="padding-right:5px;"></i>个性化</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="/images/Logo_40.jpg" class="layui-circle" width="35" height="35">
                    <cite style="color: #FFF">${user.nickname}</cite>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" data-id="10" data-url="${ctx}/user/toUser?id=${user.id}"><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite style="color: #0C0C0C">个人资料</cite></a></dd>
                    <dd><a href="javascript:;" data-id="11" data-url="page/user/changePwd.html"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite style="color: #0C0C0C">修改密码</cite></a></dd>
                </dl>
            </li>
            <li class="ht-nav-item">
                <a href="${ctx}/logout"><i class="fa fa-power-off fa-fw"></i>注销</a>
            </li>
        </ul>
    </div>
    <!--侧边导航-->
    <div class="layui-side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="nav">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:;" data-id="0"><i class="fa fa-home"></i>首页</a>
                </li>
                <chen:hasAnyPermission permissions="workspace/toDoList,workspace/toDoneList,workspace/toStartUpList">
                    <li class="layui-nav-item">
                        <a href="javascript:;"><i class="fa fa-tasks"></i>工作台</a>
                        <dl class="layui-nav-child">
                            <shiro:hasPermission name="workspace/toDoList">
                                <dd><a href="javascript:;" data-url="toDoList.html" data-id="1">待办列表</a></dd>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="workspace/toDoneList">
                                <dd><a href="javascript:;" data-url="datalist.html" data-id="2">已审列表</a></dd>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="workspace/toStartUpList">
                                <dd><a href="javascript:;" data-url="datalist.html" data-id="3">发起列表</a></dd>
                            </shiro:hasPermission>
                        </dl>
                    </li>
                </chen:hasAnyPermission>
                <shiro:hasPermission name="workspace/request">
                    <li class="layui-nav-item">
                        <a href="javascript:;" data-id="4" data-url="${ctx}/workspace/request"><i class="fa fa-file-text"></i>新建申请</a>
                    </li>
                </shiro:hasPermission>
                <chen:hasAnyPermission permissions="activiti/model,activiti/modelDef,activiti/modelDesign">
                    <li class="layui-nav-item">
                        <a href="javascript:;"><i class="fa fa-cog"></i>流程设置</a>
                        <dl class="layui-nav-child">
                            <shiro:hasPermission name="activiti/model">
                                <dd><a href="javascript:;" data-url="${ctx}/activiti/model/toModelList" data-id="5">模型管理</a></dd>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="activiti/modelDef">
                                <dd><a href="javascript:;" data-url="${ctx}/activiti/processDef/toProcessDefList" data-id="6">流程定义</a></dd>
                            </shiro:hasPermission>
                        </dl>
                    </li>
                </chen:hasAnyPermission>
                <chen:hasAnyPermission permissions="user/toUserList,resources/toResourcesTree,role/toRoleList">
                    <li class="layui-nav-item">
                        <a href="javascript:;"><i class="fa fa-wrench"></i>系统工具</a>
                        <dl class="layui-nav-child">
                            <shiro:hasPermission name="user/toUserList">
                                <dd><a href="javascript:;" data-url="${ctx}/user/toUserList" data-id="8">用户管理</a></dd>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="resources/toResourcesTree">
                                <dd><a href="javascript:;" data-url="${ctx}/resources/toResourcesTree" data-id="9">资源管理</a></dd>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="role/toRoleList">
                                <dd><a href="javascript:;" data-url="${ctx}/role/toRoleList" data-id="10">角色管理</a></dd>
                            </shiro:hasPermission>
                        </dl>
                    </li>
                </chen:hasAnyPermission>
                <chen:hasAnyPermission permissions="other/time,other/toTimeLineList">
                    <li class="layui-nav-item">
                        <a href="javascript:;"><i class="fa fa-info-circle"></i>其他信息</a>
                        <shiro:hasPermission name="other/time">
                            <dl class="layui-nav-child">
                                <dd><a href="javascript:;" data-url="${ctx}/other/toTimeLine" data-id="11">事件时间线</a></dd>
                            </dl>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="other/toTimeLineList">
                            <dl class="layui-nav-child">
                                <dd><a href="javascript:;" data-url="${ctx}/other/toTimeLineList" data-id="12">时间线管理</a></dd>
                            </dl>
                        </shiro:hasPermission>
                    </li>
                </chen:hasAnyPermission>
            </ul>
        </div>
    </div>
    <!--收起导航-->
    <div class="layui-side-hide layui-bg-cyan">
        <i class="fa fa-long-arrow-left fa-fw"></i>收起导航
    </div>
    <!--主体内容-->
    <div class="layui-body">
        <div style="margin:0;position:absolute;top:4px;bottom:0px;width:100%;" class="layui-tab layui-tab-brief" lay-filter="tab" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li lay-id="0" class="layui-this">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <p style="padding: 10px 15px; margin-bottom: 20px; margin-top: 10px; border:1px solid #ddd;display:inline-block;">
                        上次登陆
                        <span style="padding-left:1em;">IP：192.168.1.101</span>
                        <span style="padding-left:1em;">地点：四川成都</span>
                        <span style="padding-left:1em;">时间：2017-3-26 14：12</span>
                    </p>
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend>统计信息</legend>
                        <div class="layui-field-box">
                            <div style="display: inline-block; width: 100%;">
                                <div class="ht-box layui-bg-blue">
                                    <p>123</p>
                                    <p>用户总数</p>
                                </div>
                                <div class="ht-box layui-bg-red">
                                    <p>32</p>
                                    <p>今日注册</p>
                                </div>
                                <div class="ht-box layui-bg-green">
                                    <p>55</p>
                                    <p>今日登陆</p>
                                </div>
                                <div class="ht-box layui-bg-orange">
                                    <p>123</p>
                                    <p>文章总数</p>
                                </div>
                                <div class="ht-box layui-bg-cyan">
                                    <p>321</p>
                                    <p>资源总数</p>
                                </div>
                                <div class="ht-box layui-bg-black">
                                    <p>231</p>
                                    <p>笔记总数</p>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
    <!--底部信息-->
    <div class="layui-footer">
        <p style="line-height:44px;text-align:center;">后台Demo系统 - Design By Cz丶冷颜</p>
    </div>
    <!--个性化设置-->
    <div class="individuation animated flipOutY layui-hide">
        <ul>
            <li><i class="fa fa-cog" style="padding-right:5px"></i>个性化</li>
        </ul>
        <div class="explain">
            <small>从这里进行系统设置和主题预览</small>
        </div>
        <div class="setting-title">设置</div>
        <div class="setting-item layui-form">
            <span>侧边导航</span>
            <input type="checkbox" lay-skin="switch" lay-filter="sidenav" lay-text="ON|OFF" checked>
        </div>
        <div class="setting-title">主题</div>
        <div class="setting-item skin skin-default" data-skin="skin-default">
            <span>低调优雅</span>
        </div>
        <div class="setting-item skin skin-deepblue" data-skin="skin-deepblue">
            <span>蓝色梦幻</span>
        </div>
        <div class="setting-item skin skin-pink" data-skin="skin-pink">
            <span>姹紫嫣红</span>
        </div>
        <div class="setting-item skin skin-green" data-skin="skin-green">
            <span>一碧千里</span>
        </div>
    </div>
</div>
<!-- layui.js -->
<script src="/layui/layui.all.js"></script>
<!-- layui规范化用法 -->
<script type="text/javascript">
    layui.config({
        base: '/js/'
    }).use('main');
</script>
</body>
</html>