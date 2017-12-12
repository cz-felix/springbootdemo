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
            <button class="layui-btn" data-type="reload">搜索</button>
            <a href="${ctx}/role/toRoleList" class="layui-btn layui-btn-warm">刷新</a>
            <a href="${ctx}/role/toRole" style="float:right" class="layui-btn layui-btn-normal">新增角色</a>
        </div>
        <table class="layui-table" id="roleList" lay-filter="roleList"></table>
        <script type="text/html" id="barTool">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="allocation">分配权限</a>
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
                table.reload('roleList', {
                    where: {
                        name: $('#name').val()
                    }
                });
            }
        };

        //为了屏幕分辨率修改时重新渲染列表
        $(window).resize(function(){
            table.render({
                elem: '#roleList',
                url:'${ctx}/role/getRoleList',
                cellMinWidth: 100, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                cols: [[
                    {fixed:'left',type:'numbers',title:'序号'},
                    {field:'name',sort:true,title:'名称'},
                    {field:'roleDesc',sort:true,title:'角色描述'},
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
        table.on('tool(roleList)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                window.location.href="${ctx}/role/toRole?id="+data.id;
            } else if(obj.event === 'del'){
                layer.confirm('确定删除？', function(index){
                    $.ajax({
                        url:'${ctx}/role/delete',
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
                window.location.href="${ctx}/role/toRole?id="+data.id;
            }else if(obj.event === 'allocation'){
                layer.open({
                    type: 2,
                    title:"权限列表",
                    skin:"layui-layer-molv",
                    area:['350px', '400px'],
                    anim:2,
                    btn:['确认选择','取消'],
                    yes: function(index, layero){
                        var selData = $(layero).find("iframe")[0].contentWindow.getCheckData();
                        if (selData.length == 0) {
                            layer.msg("请至少选择一列");
                            return false;
                        }
                        var idArr = [];
                        $.each(selData,function(i,item){
                            idArr.push(item.value);
                        });
                        var ids = idArr.join();
                        $.post("${ctx}/role/saveRoleResources", { roleId: data.id, resourcesId: ids }, function(data){
                            if(data.retCode == 1){
                                layer.closeAll();
                                layer.msg("操作成功");
                            }else{
                                layer.msg(data.errMsg);
                            }
                        });
                    },
                    content: '${ctx}/resources/toSelResourcesTree?roleId='+data.id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                });
            }
        });
    });
</script>
</body>
</html>