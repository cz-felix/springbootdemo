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
            标题：
            <div class="layui-inline">
                <input class="layui-input" name="title" id="title" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
            <a href="${ctx}/other/toTimeLineList" class="layui-btn layui-btn-warm">刷新</a>
            <button class="layui-btn layui-btn-danger" style="float:right" data-type="batchDel">批量删除</button>
            <a href="${ctx}/other/toTimeLineInfo" style="float:right" class="layui-btn layui-btn-normal">新增时间线</a>
        </div>
        <table class="layui-table" id="timeLineList" lay-filter="timeLineList"></table>
        <script type="text/html" id="barTool">
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
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
                table.reload('timeLineList', {
                    where: {
                        title: $('#title').val()
                    }
                });
            },
            batchDel:function () {
                var checkStatus = table.checkStatus('timeLineList');
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
                        url:'${ctx}/other/batchDel',
                        type:"GET",
                        data:{"ids":ids},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                layer.close(index);
                                layer.msg("删除成功");
                                setTimeout(function(){ window.location.href="${ctx}/other/toTimeLineList" }, 1000);
                            }else{
                                layer.close(index);
                                layer.msg(result.errMsg);
                            }
                        }
                    })
                })
            }
        };

        //为了屏幕分辨率修改时重新渲染列表
        $(window).resize(function(){
            table.render({
                elem: '#timeLineList',
                url:'${ctx}/other/getPageTimeLineList',

                cols: [[
                    {fixed: 'left',width:'10%',type:'checkbox'}
                    ,{fixed: 'left',width:'10%',type:'numbers',title:'序号'}
                    ,{field:'title',width:'40%',sort:true,title:'标题'}
                    ,{field:'time',width:'20%',sort:true,title:'时间'}
                    ,{align:'center',fixed: 'right', toolbar:'#barTool',width:'20%',title:'操作'}
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
        table.on('tool(timeLineList)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('确定删除？', function(index){
                    $.ajax({
                        url:'${ctx}/other/delete',
                        type:"GET",
                        data:{"id":data.id},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                obj.del();
                                active.reload();
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
                window.location.href="${ctx}/other/toTimeLineInfo?id="+data.id;
            }
        });
    });
</script>
</body>
</html>