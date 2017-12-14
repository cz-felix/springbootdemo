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
            <button class="layui-btn layui-btn-normal" style="float:right" data-type="addRole">新增角色</button>
        </div>
        <table class="layui-table" id="roleList" lay-filter="roleList"></table>
        <script type="text/html" id="barTool">
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            <a class="layui-btn layui-btn-xs" lay-event="allocation">分配权限</a>
        </script>
    </div>
</div>
<script src="/layui/layui.all.js"></script>
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
            },
            addRole:function(){
                layer.open({
                    type: 2,
                    title:"新增角色",
                    skin:"layui-layer-molv",
                    area:['400px', '250px'],
                    anim:2,
                    content: '${ctx}/role/toRole'
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
                    {field:'name',sort:true,edit: 'text',title:'名称'},
                    {field:'roleDesc',sort:true,edit: 'text',title:'角色描述'},
                    {align:'center', toolbar:'#barTool', fixed: 'right',title:'操作'}
                ]],
                page: true,
                limits:[5,10,30,60],
                even:true
            });
            active.reload();
        }).resize();

        //监听单元格编辑
        table.on('edit(roleList)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段

            var param = {};
            param['id'] = data.id;
            param[field] = value;
            if(value == ''){
                layer.msg("不可修改为空");
                active.reload();
                return false;
            }

            if("1" == data.id){
                layer.msg("内置管理员不可修改");
                active.reload();
                return false;
            }

            $.post("${ctx}/role/update", param, function(data){
                if(data.retCode == 1){
                    layer.msg("修改成功");
                }else{
                    layer.msg(data.errMsg);
                }
            });
        });

        $('.selTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //监听工具条
        table.on('tool(roleList)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                if("1" == data.id){
                    layer.msg("内置管理员不可删除");
                    return false;
                }

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
            }else if(obj.event === 'allocation'){
                if("1" == data.id){
                    layer.msg("内置管理员不可修改权限");
                    return false;
                }
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

        window.refreshList = function () {
            active.reload();
        }
    });
</script>
</body>
</html>