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
            模型名称：
            <div class="layui-inline">
                <input class="layui-input" name="name" id="name" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
            <a href="${ctx}/activiti/model/toModelList" class="layui-btn layui-btn-warm">刷新</a>
            <button class="layui-btn layui-btn-danger" style="float:right" data-type="batchDel">批量删除</button>
            <button data-type="modelDesign" style="float:right" class="layui-btn layui-btn-normal">新增模型</button>
        </div>
        <table class="layui-table" id="modelList" lay-filter="modelList"></table>
        <script type="text/html" id="barTool">
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn layui-btn-xs" lay-event="deploy">部署</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
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
                table.reload('modelList', {
                    where: {
                        modelName: $('#name').val()
                    }
                });
            },
            batchDel:function () {
                var checkStatus = table.checkStatus('modelList');
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
                    layer.load();
                    $.ajax({
                        url:'${ctx}/activiti/model/batchDel',
                        type:"GET",
                        data:{"ids":ids},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                layer.closeAll();
                                layer.msg("删除成功");
                                setTimeout(function(){ window.location.href="${ctx}/activiti/model/toModelList" }, 1000);
                            }else{
                                layer.closeAll();
                                layer.msg(result.errMsg);
                            }
                        }
                    })
                })
            },
            modelDesign:function () {
                layer.open({
                    type: 2,
                    title:"模型基本信息",
                    skin:"layui-layer-molv",
                    area:['400px', '350px'],
                    anim:2,
                    content: '${ctx}/activiti/model/toModelInfo'
                });
                //window.parent.showModelDesign('asdsadas');
            }
        };

        //为了屏幕分辨率修改时重新渲染列表
        $(window).resize(function(){
            table.render({
                elem: '#modelList',
                url:'${ctx}/activiti/model/getModelList',
                cols: [[
                    {fixed: 'left',width:'8%',type:'checkbox'}
                    ,{fixed: 'left',width:'8%',type:'numbers',title:'序号'}
                    ,{field:'name',width:'22%',sort:true,title:'模型名称'}
                    ,{field:'key',width:'20%',sort:true,title:'模型标识'}
                    ,{field:'version',width:'10%', sort:true,title:'模型版本'}
                    ,{align:'center',fixed: 'right', toolbar:'#barTool',width:'30%',title:'操作'}
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
        table.on('tool(modelList)', function(obj){
            var data = obj.data;
            if(obj.event === 'deploy'){
                layer.confirm('确定部署？', function(index){
                    layer.load();
                    $.ajax({
                        url:'${ctx}/activiti/model/deployModel',
                        type:"GET",
                        data:{"modelId":data.id},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                active.reload();
                                layer.closeAll();
                                layer.msg("部署成功");
                            }else{
                                layer.closeAll();
                                layer.msg(result.errMsg);
                            }
                        }
                    })
                });
            } else if(obj.event === 'del'){
                layer.confirm('确定删除？', function(index){
                    layer.load();
                    $.ajax({
                        url:'${ctx}/activiti/model/delete',
                        type:"GET",
                        data:{"id":data.id},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                obj.del();
                                active.reload();
                                layer.closeAll();
                                layer.msg("删除成功");
                            }else{
                                layer.closeAll();
                                layer.msg(result.errMsg);
                            }
                        }
                    })
                });
            } else if(obj.event === 'edit'){
                window.parent.showModelDesign(data.id);
            }
        });
    });
</script>
</body>
</html>