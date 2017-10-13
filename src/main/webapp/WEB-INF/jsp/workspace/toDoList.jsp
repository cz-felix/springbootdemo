<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body class="layui-layout-body">
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">

            <table class="layui-table" lay-data="{width:1108,url:'${ctx}/workspace/getToDoList', page:true , id:'idTest'}" lay-filter="demo">
                <thead>
                <tr>
                    <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
                    <th lay-data="{field:'username', width:100}">用户名</th>
                    <th lay-data="{field:'sex', width:80, sort: true}">性别</th>
                    <th lay-data="{field:'city', width:100}">城市</th>
                    <th lay-data="{field:'sign', width:177}">签名</th>
                    <th lay-data="{field:'experience', width:85, sort: true}">积分</th>

                    <th lay-data="{field:'classify', width:80}">职业</th>
                    <th lay-data="{field:'wealth', width:135, sort: true}">财富</th>
                    <th lay-data="{field:'score', width:80, sort: true, fixed: 'right'}">评分</th>
                    <th lay-data="{fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
                <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
            </script>
        </div>
    </div>
    <script src="/layui/layui.js"></script>
    <script type="text/javascript">
        layui.config({
            dir: '/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
            ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
            ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
            ,base: '/layui/lay/modules/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
        });
        //JavaScript代码区域
        layui.use(['element','form','layer','jquery','carousel','util','laypage','table'], function(){
            var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,carousel = layui.carousel,util = layui.util,
                laypage = layui.laypage,table = layui.table;

            //监听左侧导航点击
            element.on('nav(left-nav)', function (elem) {
                debugger;
                var url = $(this).children('a').attr('data-url');
                var id = $(this).children('a').attr('data-id');
                var title = $(this).children('a').text();
                if (id == "0") {
                    element.tabChange('tab', 0);
                    return;
                }
                if (url == undefined) return;

                var tabTitleDiv = $('.layui-tab[lay-filter=\'tab\']').children('.layui-tab-title');
                var exist = tabTitleDiv.find('li[lay-id=' + id + ']');
                if (exist.length > 0) {
                    //切换到指定索引的卡片
                    element.tabChange('tab', id);
                } else {
                    var loading = layer.load();
                    $.ajax({
                        type: 'post',
                        url: url,
                        success: function (data) {
                            debugger;
                            layer.close(loading);
                            element.tabAdd('tab', { title: title, content: data, id: id });
                            //切换到指定索引的卡片
                            element.tabChange('tab', id);
                        },
                        error: function (e) {
                            var message = e.responseText;
                            layer.close(loading);
                            layer.msg(message, { icon: 2 });
                        }
                    });
                }
            });
        });
    </script>
</body>
</html>