<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <style>
        .layui-table-body .layui-table,.layui-table-header .layui-table{width: 100%;}
        .layui-table-body.layui-table-main .layui-table-cell,.layui-table-header .layui-table-cell{white-space: pre-wrap;word-wrap: break-word; word-break: break-all; height: auto;}
    </style>
</head>
<body class="layui-layout-body" style="overflow: auto;">
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="demoTable">
                搜索ID：
                <div class="layui-inline">
                    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
                </div>
                <button class="layui-btn" data-type="reload">搜索</button>
            </div>
            <table class="layui-table" lay-data="{url:'${ctx}/workspace/getToDoList', page:true ,limits:[10,30,60],limit: 10,id:'idTest'}" lay-filter="demo">
                <thead>
                <tr>
                    <th lay-data="{field:'id', width:'auto', sort: true,styles:'width:8%'}">ID</th>
                    <th lay-data="{field:'username', width:'auto',styles:'width:10%'}">用户名</th>
                    <th lay-data="{field:'sex', width:'auto',styles:'width:8%', sort: true}">性别</th>
                    <th lay-data="{field:'city', width:'auto',styles:'width:10%'}">城市</th>
                    <th lay-data="{field:'sign', width:'auto',styles:'width:10%'}">签名</th>
                    <th lay-data="{field:'experience', width:'auto',styles:'width:8%', sort: true}">积分</th>
                    <th lay-data="{field:'classify', width:'auto',styles:'width:10%'}">职业</th>
                    <th lay-data="{field:'wealth', width:'auto',styles:'width:10%', sort: true}">财富</th>
                    <th lay-data="{field:'score', width:'auto',styles:'width:8%', sort: true, fixed: 'right'}">评分</th>
                    <th lay-data="{fixed: 'right', width:'auto',styles:'width:18%', align:'center', toolbar: '#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-mini" lay-event="edit">审核</a>
                <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">流程图</a>
                <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
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
                    var demoReload = $('#demoReload');
                    table.reload('idTest', {
                        where: {
                            key: {
                                id: demoReload.val()
                            }
                        }
                    });
                }
            };

            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
    </script>
</body>
</html>