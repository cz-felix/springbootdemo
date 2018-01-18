<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <style>
        .layui-form-checkbox span {
            height: 7%;
        }
        .layui-form-checkbox[lay-skin=primary] i {
            top:2px;
        }
    </style>
</head>
<body style="overflow: auto;">
    <form class="layui-form" action="">
        <!--xtree容器 begin-->
        <div id="layui-xtree-demo1" <%--style="width:400px;height:400px; border:1px solid black; margin:20px;overflow:auto;float:left;"--%>></div>
        <!--xtree容器 end-->
    </form>
    <script src="/layui/layui.all.js"></script>
    <script src="/layui-xtree/layui-xtree.js"></script>
    <script type="text/javascript">

        layui.use('form', function () {
            var form = layui.form;

            //创建tree
            var xtree = new layuiXtree({
                elem: 'layui-xtree-demo1'           //放xtree的容器，id，不要带#号（必填）
                , form: form                       //layui form对象 （必填）
                , data: '${ctx}/resources/resourcesWithSelected?roleId=${roleId}'    //服务端地址（必填）
                , isopen: true                     //初次加载时全部展开，默认true
                , color: "#000"                    //图标颜色
                , icon: {                          //图标样式 （必填，不填会出点问题）
                    open: "&#xe7a0;"               //节点打开的图标
                    , close: "&#xe622;"            //节点关闭的图标
                    , end: "&#xe621;"              //末尾节点的图标
                }
            });

            window.getCheckData = function () {
                var oCks = xtree.GetChecked(); //获取末级且选中的checkbox原dom对象，返回的类型:Array
                return oCks;
            }
        });
    </script>
</body>
</html>