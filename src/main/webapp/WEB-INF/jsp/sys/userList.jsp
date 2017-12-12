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
            用户名：
            <div class="layui-inline">
                <input class="layui-input" name="username" id="username" autocomplete="off">
            </div>
            昵称：
            <div class="layui-inline">
                <input class="layui-input" name="nickname" id="nickname" autocomplete="off">
            </div>
            状态：
            <div class="layui-inline">
                <select name="status" id="status" class="layui-select">
                    <option value="">请选择用户状态</option>
                    <option value="1">有效</option>
                    <option value="0">禁止登录</option>
                </select>
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
            <a href="${ctx}/user/toUserList" class="layui-btn layui-btn-warm">刷新</a>
            <button class="layui-btn layui-btn-danger" style="float:right" data-type="batchDel">批量删除</button>
            <a href="${ctx}/user/toUser" style="float:right" class="layui-btn layui-btn-normal">新增用户</a>
        </div>
        <table class="layui-table" id="userList" lay-filter="userList"></table>
        <script type="text/html" id="barTool">
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            <a class="layui-btn layui-btn-xs" lay-event="password">修改密码</a>
            <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="role">分配角色</a>
        </script>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
            laypage = layui.laypage,table = layui.table,selrole = layui.selRoleList;

        var active = {
            reload: function(){
                table.reload('userList', {
                    where: {
                        username: $('#username').val(),
                        nickname: $('#nickname').val(),
                        status:$("#status").val()
                    }
                });
            },
            batchDel:function () {
                var checkStatus = table.checkStatus('userList');
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
                        url:'${ctx}/user/batchDel',
                        type:"GET",
                        data:{"ids":ids},
                        dataType:"json",
                        success:function(result){
                            if(result.retCode == 1){
                                layer.close(index);
                                layer.msg("删除成功");
                                setTimeout(function(){ window.location.href="${ctx}/user/toUserList" }, 1000);
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
                elem: '#userList',
                url:'${ctx}/user/getUserList',

                cols: [[
                    {fixed: 'left',width:'5%',type:'checkbox'}
                    ,{fixed: 'left',width:'5%',type:'numbers',title:'序号'}
                    ,{field:'username',width:'15%',sort:true,title:'用户名'}
                    ,{field:'nickname',width:'15%',sort:true,title:'昵称'}
                    ,{field:'statusName',width:'10%', sort:true,title:'状态'}
                    ,{field:'lastLoginTime',width:'20%',sort:true,title:'最后登录时间'}
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
        table.on('tool(userList)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                window.location.href="${ctx}/user/toUser?id="+data.id+"&isEdit=Y";
            } else if(obj.event === 'del'){
                layer.confirm('确定删除？', function(index){
                    $.ajax({
                        url:'${ctx}/user/delete',
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
                window.location.href="${ctx}/user/toUser?id="+data.id;
            }else if(obj.event === 'password'){
                if("1" == data.id){
                    layer.msg("系统内置管理员不可修改密码！");
                    return false;
                }else{
                    layer.open({
                        type: 2,
                        title:"修改密码",
                        skin:"layui-layer-molv",
                        area:['400px', '250px'],
                        anim:2,
                        content: '${ctx}/user/toUpdatePassword?userId='+data.id
                    });
                }
            }else if(obj.event === 'role'){
                layer.open({
                    type: 2,
                    title:"角色列表",
                    skin:"layui-layer-molv",
                    area:['600px', '400px'],
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
                            idArr.push(item.id);
                        });
                        var ids = idArr.join();
                        $.post("${ctx}/user/saveUserRoles", { userId: data.id, roleId: ids }, function(data){
                            if(data.retCode == 1){
                                layer.closeAll();
                                layer.msg("操作成功");
                            }else{
                                layer.msg(data.errMsg);
                            }
                        });
                    },
                    content: '${ctx}/role/toSelectRoles?userId='+data.id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                });
            }
        });
    });
</script>
</body>
</html>