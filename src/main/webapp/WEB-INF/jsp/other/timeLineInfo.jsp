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
        <form class="layui-form" name="form" id="timeLineForm">
            <input type="hidden" id="id" value="${timeLine.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block" style="width: 50%">
                    <input type="text" name="title" id="title" value="${timeLine.title}" lay-verType="tips"  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">时间</label>
                <div class="layui-input-block" style="width: 50%">
                    <input type="text" name="time" id="time" value="<fmt:formatDate value="${timeLine.time}" pattern="yyyy-MM-dd" />" readonly lay-verType="tips" lay-verify="required|date" placeholder="请选择时间" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea id="content" name="content" class="layui-textarea" style="display: none;">${timeLine.contentStr}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="timeLineForm">立即提交</button>
                    <a href="${ctx}/other/toTimeLineList" class="layui-btn layui-btn-primary">返回列表</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element','form','layer','jquery','util','table','layedit','laydate'], function(){
        var element = layui.element,form = layui.form,layer = layui.layer,$ = layui.$,util = layui.util,
            table = layui.table,layedit = layui.layedit,laydate = layui.laydate;
        laydate.render({
            elem: '#time'
        });

        var edit = layedit.build('content', {
            tool: [
                'strong' //加粗
                ,'italic' //斜体
                ,'underline' //下划线
                ,'del' //删除线
                ,'|' //分割线
                ,'left' //左对齐
                ,'center' //居中对齐
                ,'right' //右对齐
                ,'link' //超链接
                ,'unlink' //清除链接
                ,'face' //表情
            ]
        });

        //监听提交
        form.on('submit(timeLineForm)', function(data){
            layedit.sync(edit);//同步内容
            var loading = layer.load();
            $.ajax({
                url:'${ctx}/other/timeLineSave',
                type:"POST",
                data:{"id":$("#id").val(),"title":$("#title").val(),"time":$("#time").val(),"content":layedit.getContent(edit)},
                dataType:"json",
                success:function(result){
                    if(result.retCode == 1){
                        layer.close(loading);
                        layer.msg("保存成功");
                        location.href="/other/toTimeLineList";
                    }else{
                        layer.close(loading);
                        layer.msg(result.errMsg);
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>