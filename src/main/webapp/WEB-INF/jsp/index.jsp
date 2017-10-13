<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all"/>
    <style>
        .sys-title {display: inline-block;line-height: 60px;color: #00F7DE;
            text-shadow: 0 0 8px #1E9FFF,0 0 8px #009688;
            font-size: 26px;
            letter-spacing: 6px;
            cursor: pointer;
        }
        .ht-box {
            display: inline-block;
            margin: 15px;
            padding: 15px 0;
            color: #fff;
            width: 12%;
        }
        .ht-box p:first-child {
            font-size: 40px;
        }
        .layui-tab-item {
            text-align: center;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header" style="text-align: center">
        <div class="layui-logo">
            <a href="${ctx}/index"><font color="#009688" size="4">首页</font></a>
        </div>
        <span class="sys-title">Activiti Demo系统</span>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"></img>
                    ${user.name}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/logout">退了</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree"  lay-filter="left-nav">
                <li class="layui-nav-item layui-this" ><a href="javascript:;" data-id="0"><i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe68e;</i> 首页</a></li>
                <li class="layui-nav-item ">
                    <a class="" href="javascript:;"><i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe756;</i> 工作台</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="${ctx}/workspace/toDoList" data-id="1">待办列表</a></dd>
                        <dd><a href="javascript:;">已办列表</a></dd>
                        <dd><a href="javascript:;">发起列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href=""><i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe6b2;</i> 新建申请</a></li>
                <li class="layui-nav-item ">
                    <a class="" href="javascript:;"><i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe614;</i> 流程设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">模型列表</a></dd>
                        <dd><a href="javascript:;">流程定义列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe631;</i> 系统工具</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">用户列表</a></dd>
                        <dd><a href="javascript:;">角色管理</a></dd>
                        <dd><a href="javascript:;">用户角色</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href=""><i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe60a;</i> 在线帮助</a></li>
                <li class="layui-nav-item"><a href=""><i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe636;</i> 事件时间点</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="margin:0;margin-top:4px;" class="layui-tab layui-tab-brief" lay-filter="tab" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li lay-id="0" class="layui-this">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <p style="padding: 10px 15px; margin-bottom: 20px; margin-top: 10px; border:1px solid #ddd;display:inline-block;">
                        上次登陆
                        <span style="padding-left:1em;">IP：113.81.148.44</span>
                        <span style="padding-left:1em;">地点：未知</span>
                        <span style="padding-left:1em;">时间：2017/10/12 23:09:39</span>
                    </p>
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend>统计信息</legend>
                        <div class="layui-field-box">
                            <div style="display: inline-block; width: 100%;">
                                <div class="ht-box layui-bg-blue">
                                    <p id="totalCount">0</p>
                                    <p>用户总数</p>
                                </div>
                                <div class="ht-box layui-bg-red">
                                    <p id="todayRegist">0</p>
                                    <p>今日注册</p>
                                </div>
                                <div class="ht-box layui-bg-green">
                                    <p id="todayLogin">0</p>
                                    <p>今日登陆</p>
                                </div>
                                <div class="ht-box layui-bg-orange">
                                    <p id="articleCount">0</p>
                                    <p>文章总数</p>
                                </div>
                                <div class="ht-box layui-bg-cyan">
                                    <p id="resourceCount">0</p>
                                    <p>资源总数</p>
                                </div>
                                <div class="ht-box layui-bg-black">
                                    <p id="noteCount">0</p>
                                    <p>笔记总数</p>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-footer">
        <p style="line-height:44px;text-align:center;">Activiti Demo系统 - Design By Cz丶冷颜</p>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        dir: '/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
        ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
        ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
        ,base: '/layui/lay/modules/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
    });
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
            laypage = layui.laypage,table = layui.table;

        //监听左侧导航点击
        element.on('nav(left-nav)', function (elem) {
            var url = $(this).children('a').attr('data-url');
            var id = $(this).children('a').attr('data-id');
            var title = $(this).children('a').text();
            if (id == "0") {
                element.tabChange('tab', 0);
                return;
            }
            if (url == undefined) return;

            var tabTitleDiv = $('.layui-tab[lay-filter=\'tab\']').children('.layui-tab-title');
            var exist = tabTitleDiv.find('li[lay-id=' + id + ']');
            if (exist.length > 0) {
                //切换到指定索引的卡片
                element.tabChange('tab', id);
            } else {
                var loading = layer.load();
                $.ajax({
                    type: 'post',
                    url: url,
                    success: function (data) {
                        layer.close(loading);
                        element.tabAdd('tab', { title: title, content: data, id: id });
                        //切换到指定索引的卡片
                        element.tabChange('tab', id);
                    },
                    error: function (e) {
                        var message = e.responseText;
                        layer.close(loading);
                        layer.msg(message, { icon: 2 });
                    }
                });
            }
        });
    });
</script>
</body>
</html>