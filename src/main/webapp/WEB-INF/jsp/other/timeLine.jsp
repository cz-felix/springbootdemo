<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body class="layui-layout-body" style="overflow: auto;">
<ul class="layui-timeline">
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">2017-10-14</h3>
            使用JSO,实现redis+cookie单点登录
            <p>添加redis缓存机制，添加用户表等，拦截器，实现单点登录</p>
            <ul>
                <li>创建用户信息表</li>
                <li>添加redis缓存</li>
                <li>添加登录拦截器，实现单点登录</li>
            </ul>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">2017-10-12</h3>
            使用JSO,实现redis+cookie单点登录
            <p>添加redis缓存机制，添加用户表等，拦截器，实现单点登录</p>
            <ul>
                <li>创建用户信息表</li>
                <li>添加redis缓存</li>
                <li>添加登录拦截器，实现单点登录</li>
            </ul>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">2017-09-08</h3>
            集成mybatis
            <p>在不使用xml文件的基础上配置数据源与连接池</p>
            <ul>
                <li>使用Configuration配置数据源</li>
                <li>删除dpcp连接池，使用druid连接池</li>
            </ul>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis"></i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">2017-09-07</h3>
            <p>
                系统开始日
                <br>最初只是想搭建个SpringBoot框架玩玩，然后...慢慢的加加加....
                <ul>
                    <li>搭建了SpringBoot框架， 并将代码上传到git上。</li>
                    <li>学习使用.yml配置文件</li>
                </ul>
            </p>
        </div>
    </li>
</ul>
<script src="/layui/layui.js"></script>
</body>
</html>