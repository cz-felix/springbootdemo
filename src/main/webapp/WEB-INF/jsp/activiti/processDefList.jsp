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
        <div class="layui-form selTable">
            流程定义名称：
            <div class="layui-inline">
                <input class="layui-input" name="modelKey" id="name" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
            <a href="${ctx}/activiti/processDef/toProcessDefList" class="layui-btn layui-btn-warm">刷新</a>
        </div>
        <table class="layui-table" id="processDefList" lay-filter="processDefList"></table>
    </div>
</div>
<script src="/layui/layui.all.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
            laypage = layui.laypage,table = layui.table,selrole = layui.selRoleList;

        var active = {
            reload: function(){
                table.reload('processDefList', {
                    where: {
                        modelName: $('#name').val()
                    }
                });
            }
        };

        //为了屏幕分辨率修改时重新渲染列表
        $(window).resize(function(){
            table.render({
                elem: '#processDefList',
                url:'${ctx}/activiti/processDef/getProcessDefList',
                cols: [[
                    {fixed: 'left',width:'10%',type:'numbers',title:'序号'}
                    ,{field:'name',width:'25%',sort:true,title:'流程定义名称'}
                    ,{field:'key',width:'25%',sort:true,title:'流程定义标识'}
                    ,{field:'version',width:'10%', sort:true,title:'版本'}
                    ,{field:'diagramResourceName', style:'color: #1E9FFF;cursor: pointer;', event:'openDiagram',width:'30%',title:'流程图文件'}
                ]],
                page: true,
                limits:[5,10,30,60],
                even:true
            });
            active.reload();
        }).resize();

        $('.selTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //监听工具条
        table.on('tool(processDefList)', function(obj){
            var data = obj.data;
            if(obj.event === 'openDiagram'){
                $.getJSON('${ctx}/activiti/processDef/getProcessDefDiagramJson?processDefId='+data.id, function(json){
                    layer.photos({
                        photos: json
                        ,anim: 4 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
                    });
                });
            }
        });
    });
</script>
</body>
</html>