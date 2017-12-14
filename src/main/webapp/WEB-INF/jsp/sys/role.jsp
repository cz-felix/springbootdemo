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
        <form class="layui-form" name="form" id="roleForm">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-block" style="width: 50%">
                    <input type="text" name="name" lay-verType="tips"  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block"style="width: 50%">
                    <input type="text" name="roleDesc" lay-verType="tips"  lay-verify="required" placeholder="请输入描述" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="save">保存</button>
                    <button class="layui-btn layui-btn-primary" id="cancel" lay-filter="canel">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="/layui/layui.all.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
            laypage = layui.laypage,table = layui.table;

        form.on('submit(save)', function(data){
            $.post("${ctx}/role/add", data.field, function(data){
                if(data.retCode == 1){
                    parent.layer.closeAll();
                    parent.refreshList();
                    parent.layer.msg("操作成功");
                }else{
                    parent.layer.msg(data.errMsg);
                }
            });
            return false;
        });
        $('#cancel').click(function(){
            parent.layer.closeAll();
        });
    });
</script>
</body>
</html>