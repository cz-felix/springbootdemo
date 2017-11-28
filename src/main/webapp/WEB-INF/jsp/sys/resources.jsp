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
            <input type="hidden" name="id" value="${resources.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-block" style="width: 30%">
                    <input type="text" name="name" value="${resources.name}" <c:if test="${isEdit eq 'Y'}">readonly</c:if> lay-verType="tips"  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">URL</label>
                <div class="layui-input-block"style="width: 30%">
                    <input type="text" name="resUrl" value="${resources.resUrl}" <c:if test="${isEdit eq 'Y'}">readonly</c:if> lay-verType="tips"  lay-verify="required" placeholder="请输入URL" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-inline">
                    <select name="type" lay-verify="required" <c:if test="${isEdit eq 'Y'}">disabled</c:if>>
                        <option value="">请选择资源类型</option>
                        <option value="1" <c:if test="${resources.type eq '1'}">selected</c:if>>菜单</option>
                        <option value="2" <c:if test="${resources.type eq '2'}">selected</c:if>>按钮</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-inline">
                    <input type="text" name="sort" value="${resources.sort}" <c:if test="${isEdit eq 'Y'}">disabled</c:if> lay-verType="tips"  lay-verify="required|number" placeholder="请输入排序数" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请输入排序的数字，按从小到大排序</div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <c:if test="${isEdit ne 'Y'}">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    </c:if>
                    <a href="${ctx}/resources/toResourcesList" class="layui-btn layui-btn-primary">返回列表</a>
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