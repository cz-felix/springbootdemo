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
        <form class="layui-form" name="form" id="modelInfoForm"  <%--action=""--%>>
            <div class="layui-form-item">
                <label class="layui-form-label">模型名称</label>
                <div class="layui-input-block" style="width: 60%">
                    <input type="text" name="modelName" lay-verType="tips"  lay-verify="required" placeholder="请输入模型名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">模型标识</label>
                <div class="layui-input-block"style="width: 60%">
                    <input type="text" name="modelKey" lay-verType="tips"  lay-verify="required" placeholder="请输入模型标识" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">模型描述</label>
                <div class="layui-input-block" style="width: 60%">
                    <textarea name="modelDesc" placeholder="请输入模型描述" class="layui-textarea"></textarea>
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
            $.post("${ctx}/activiti/model/saveModelInfo", data.field, function(data){
                layer.load();
                if(data.retCode == 1){
                    parent.layer.closeAll();
                    window.parent.parent.showModelDesign(data.data);
                    parent.location.reload();
                }else{
                    parent.layer.closeAll();
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