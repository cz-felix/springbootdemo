<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body class="layui-layout-body" style="overflow: auto;">
    <ul class="layui-timeline">
        <c:forEach var="timeLine" items="${timeLineList}" varStatus="status">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title"><fmt:formatDate value="${timeLine.time}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;&nbsp;${timeLine.title}</h3>
                    ${timeLine.contentStr}
                </div>
            </li>
        </c:forEach>
    </ul>
<script src="/layui/layui.js"></script>
<script>
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;

        //…
    });
</script>
</body>
</html>