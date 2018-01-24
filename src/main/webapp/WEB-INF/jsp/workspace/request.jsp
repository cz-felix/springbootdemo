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
        <form class="layui-form" name="form" action="${ctx}/resources/save">
            <div class="layui-form-item">
                <label class="layui-form-label">申请名称</label>
                <div class="layui-input-block" style="width: 30%">
                    <input type="text" name="name" lay-verType="tips"  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">申请编号</label>
                <div class="layui-input-block"style="width: 30%">
                    <input type="text" name="resUrl"   lay-verType="tips"  lay-verify="required" placeholder="请输入编号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">流程类型</label>
                <div class="layui-input-inline">
                    <select name="type" lay-verify="required">
                        <option value="">请选择审核流程</option>
                        <option value="1" <c:if test="${resources.type eq '1'}">selected</c:if>>菜单</option>
                        <option value="2" <c:if test="${resources.type eq '2'}">selected</c:if>>按钮</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">申请编号</label>
                <div class="layui-input-block"style="width: 30%">
                    <input type="text" name="resUrl"   lay-verType="tips"  lay-verify="required" placeholder="请输入编号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
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