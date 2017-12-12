<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../jsp/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="application/msword; charset=UTF-8">
    <title>Activiti Demo系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <link rel="stylesheet" href="/css/resourceTree.css"/>
</head>
<body>
    <div class="table-list">
        <a href="${ctx}/resources/toResources" style="float:right" class="layui-btn layui-btn-normal">新增资源</a>
        <table class="layui-table" id="tabletree">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <thead>
                <tr>
                    <th>序号</th>
                    <th>名称</th>
                    <th>URL</th>
                    <th>类型</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="resource" items="${resourcesList}" varStatus="status">
                    <tr data-tb-pid="${resource.parentId}" data-tb-id="${resource.id}">
                        <td>${status.count}</td>
                        <td clas="name">${resource.name}</td>
                        <td>${resource.resUrl}</td>
                        <td>${resource.typeName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<script src="/layui/layui.js"></script>
<script type="text/javascript" src="/js/resource/resource.js"></script>
</body>
</html>