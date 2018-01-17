<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>Activiti Demo系统</title>
    <link rel="shortcut icon" href="/images/Logo_40.jpg" type="image/x-icon">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <style>
        .layui-carousel div div img{width: 100%;height: 100%;background-size: 100% 100%;}
    </style>
</head>
<body >
<div class="layui-carousel" id="login-carousel">
    <div carousel-item>
        <div><img src="/images/background1.jpg"></div>
        <div><img src="/images/background2.jpg"></div>
        <div><img src="/images/background3.jpg"></div>
    </div>
</div>
<%--<blockquote class="layui-elem-quote layui-quote-nm bottom-blockquote">
    <p style="line-height:44px;text-align:center;">Activiti Demo系统 -- Design By Cz丶冷颜</p>
</blockquote>--%>
<script src="/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['jquery','carousel', 'element','form','util','layer'], function(){
        var form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,layer = layui.layer;
        carousel.render({
            elem: '#login-carousel'
            ,full: true //全屏轮播
            ,arrow: 'none' //不显示悬停显示箭头
            ,anim:  'fade'//渐隐渐现
            ,indicator: 'none'//不显示指示器位置*/
        });

        //页面一打开就执行弹层
        layer.ready(function(){
            layer.open({
                id:'loginInfo',
                type: 2,
                title:false,
                skin: 'layui-layer-nobg',
                closeBtn:0,
                area:['400px', '320px'],
                anim:2,
                content: ['/loginInfo.html', 'no']
            });
        });
    });
</script>
</body>
</html>