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
            名称：
            <div class="layui-inline">
                <input class="layui-input" name="name" id="name" autocomplete="off">
            </div>
            URL：
            <div class="layui-inline">
                <input class="layui-input" name="resUrl" id="resUrl" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
            <a href="${ctx}/resources/toResourcesList" class="layui-btn layui-btn-warm">刷新</a>
            <button class="layui-btn layui-btn-danger" style="float:right" data-type="batchDel">批量删除</button>
            <a href="${ctx}/resources/toResources" style="float:right" class="layui-btn layui-btn-normal">新增资源</a>
        </div>
        <table class="layui-table" id="resourcesList" lay-filter="resourcesList"></table>
        <script type="text/html" id="barTool">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
            laypage = layui.laypage,table = layui.table;

        var active = {
            reload: function(){
                table.reload('resourcesList', {
                    where: {
                        name: $('#name').val(),
                        resUrl: $('#resUrl').val()
                    }
                });
            },
            batchDel:function () {
                var checkStatus = table.checkStatus('resourcesList');
                var data = checkStatus.data;
                var ids = [];
                $.each(data, function(){
                    ids.push(this.id);
                });

                if(ids.length == 0){
                    layer.msg("未选中删除项！");
                    return false;
                }
                layer.confirm('确定删除？', function(index){
                    $.ajax({
                        url:'${ctx}/resources/batchDel',
                        type:"GET",
                        data:{"ids":ids},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                layer.close(index);
                                layer.msg("删除成功");
                                setTimeout(function(){ window.location.href="${ctx}/resources/toResourcesList" }, 1000);
                            }else{
                                layer.close(index);
                                layer.msg(result.errMsg);
                            }
                        }
                    })
                });
            }
        };

        //为了屏幕分辨率修改时重新渲染列表
        $(window).resize(function(){
            table.render({
                elem: '#resourcesList',
                url:'${ctx}/resources/getResourcesList',
                cellMinWidth: 100, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                cols: [[
                    {fixed: 'left',type:'checkbox'},
                    {fixed: 'left',type:'numbers',title:'序号'},
                    {field:'name',sort:true,title:'名称'},
                    {field:'resUrl',sort:true,title:'URL'},
                    {field:'typeName', sort:true,title:'类型'},
                    {align:'center', toolbar:'#barTool', fixed: 'right',title:'操作'}
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
        table.on('tool(resourcesList)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                window.location.href="${ctx}/resources/toResources?id="+data.id+"&isEdit=Y";
            } else if(obj.event === 'del'){
                layer.confirm('确定删除？', function(index){
                    $.ajax({
                        url:'${ctx}/resources/delete',
                        type:"GET",
                        data:{"id":data.id},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                obj.del();
                                layer.close(index);
                                layer.msg("删除成功");
                            }else{
                                layer.close(index);
                                layer.msg(result.errMsg);
                            }
                        }
                    })
                });
            } else if(obj.event === 'edit'){
                window.location.href="${ctx}/resources/toResources?id="+data.id;
            }
        });
    });
</script>
</body>
</html>