<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body class="layui-layout-body" style="overflow: auto;">
<div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
        <form class="layui-form" name="form" action="${ctx}/user/save">
            <input type="hidden" name="id" value="${user.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block" style="width: 30%">
                    <input type="text" name="username" value="${user.username}" <c:if test="${isEdit eq 'Y'}">readonly</c:if> lay-verType="tips"  lay-verify="required|username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block"style="width: 30%">
                    <input type="text" name="nickname" value="${user.nickname}" <c:if test="${isEdit eq 'Y'}">readonly</c:if> lay-verType="tips"  lay-verify="required|pass" placeholder="请输入昵称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block"style="width: 30%">
                    <input type="password" name="password" value="${user.password}" <c:if test="${not empty user.id}">readonly</c:if> lay-verType="tips"  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select name="status" lay-verify="required" <c:if test="${isEdit eq 'Y'}">disabled</c:if>>
                        <option value="1" <c:if test="${user.status eq '1'}">selected</c:if>>有效</option>
                        <option value="0" <c:if test="${user.status eq '0'}">selected</c:if>>禁止登录</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <c:if test="${isEdit ne 'Y'}">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    </c:if>
                    <a href="${ctx}/user/toUserList" class="layui-btn layui-btn-primary">返回列表</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
            laypage = layui.laypage,table = layui.table;
    });
</script>
</body>
</html>