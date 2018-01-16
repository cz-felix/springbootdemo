<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>Activiti Demo系统</title>
    <link rel="shortcut icon" href="/images/Logo_40.jpg" type="image/x-icon">

    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/supersized.css">
    <link rel="stylesheet" href="/assets/css/style.css">
</head>

<body>
<div class="page-container">
    <h1>登录</h1>
    <form action="" method="post">
        <input type="text" name="username" class="username" value="admin" placeholder="请输入用户名">
        <input type="password" name="password" class="password" value="admin" placeholder="请输入密码">
        <button type="submit">登录</button>
    </form>
</div>
<!-- Javascript -->
<script src="/assets/js/jquery-1.8.2.min.js"></script>
<script src="/assets/js/supersized.3.2.7.min.js"></script>
<script src="/assets/js/supersized-init.js"></script>
<script src="/assets/js/scripts.js"></script>

</body>
</html>
<%--
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>Activiti Demo系统</title>
    <link rel="shortcut icon" href="/images/Logo_40.jpg" type="image/x-icon">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <style>
        body{margin: 10px;}
        .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
        .form-item{padding-top: 10px;text-align: center;}
        .bottom-blockquote{position:absolute; width:76%; bottom:0; text-align:center;margin-left: 10%;border-color:#009688}
        .layui-elem-field legend {margin-left: 50%;}
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-carousel layui-main" id="login-carousel">
    <div carousel-item>
        <div><p class="layui-bg-green demo-carousel">在这里，你将以最直观的形式体验 layui！</p></div>
        <div><p class="layui-bg-red demo-carousel">在编辑器中可以执行 layui 相关的一切代码</p></div>
        <div><p class="layui-bg-blue demo-carousel">你也可以点击左侧导航针对性地试验我们提供的示例</p></div>
        <div><p class="layui-bg-orange demo-carousel">如果最左侧的导航的高度超出了你的屏幕</p></div>
        <div><p class="layui-bg-cyan demo-carousel">你可以将鼠标移入导航区域，然后滑动鼠标滚轮即可</p></div>
    </div>
</div>
<fieldset class="layui-elem-field layui-field-title" style="margin-left:14%;margin-top: 20px;text-align: center;width: 74%">
    <legend>登录</legend>
</fieldset>
<form class="layui-form" style="text-align: center" action="" method="POST">
    <div class="form-item">
        <div class="layui-input-inline">
            <label class="layui-form-label">用户名：</label>
        </div>
        <div class="layui-input-inline">
            <input type="text" name="username" lay-verType="tips" lay-verify="required" style="width: 250px"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="form-item">
        <div class="layui-input-inline">
            <label class="layui-form-label">密码：</label>
        </div>
        <div class="layui-input-inline">
            <input type="password" name="pwd" lay-verType="tips" lay-verify="required|password" style="width: 250px" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="loginForm">登录</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <a href="/toRegister" class="layui-btn layui-btn-warm">注册</a>
        </div>
    </div>
</form>
<blockquote class="layui-elem-quote layui-quote-nm bottom-blockquote">
    <p style="line-height:44px;text-align:center;">Activiti Demo系统 -- Design By Cz丶冷颜</p>
</blockquote>
<script src="/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['jquery','carousel', 'element','form','util'], function(){
        var form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util;
        carousel.render({
            elem: '#login-carousel'
            ,width: '80%' //设置容器宽度
            ,height:200  //设置容器高度
            ,arrow: 'hover' //悬停显示箭头
        });

        //监听提交
        form.on('submit(loginForm)', function(data){
            var loading = layer.load();
            $.ajax({
                url:'${ctx}/login',
                type:"POST",
                data:data.field,
                dataType:"json",
                success:function(result){
                    if(result.retCode == 1){
                        layer.close(loading);
                        layer.msg("登录成功");
                        window.location.href="${ctx}/main";
                    }else{
                        layer.close(loading);
                        layer.msg(result.errMsg);
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>--%>
