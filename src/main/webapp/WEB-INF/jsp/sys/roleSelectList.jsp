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
        <input type="hidden" id="userId" value="${userId}">
        <table class="layui-table" id="selectRoleList" lay-filter="selectRoleList"></table>
    </div>
</div>
<script src="/layui/layui.all.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
            laypage = layui.laypage,table = layui.table;

        //为了屏幕分辨率修改时重新渲染列表
        $(window).resize(function(){
            table.render({
                elem: '#selectRoleList',
                url:'${ctx}/role/rolesWithSelected?userId=${userId}',
                cellMinWidth: 100, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                cols: [[
                    {fixed: 'left',type:'checkbox'},
                    {fixed: 'left',type:'numbers',title:'序号'},
                    {field:'name',sort:true,title:'名称'},
                    {field:'roleDesc',sort:true,title:'描述'}
                ]],
                even:true
            });

            window.getCheckData = function () {
                var checkStatus = table.checkStatus('selectRoleList');
                var data = checkStatus.data;
                return data;
            }
        }).resize();
    });
</script>
</body>
</html>