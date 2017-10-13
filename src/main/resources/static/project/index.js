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
        var url = $(this).children('a').attr('data-url');
        var id = $(this).children('a').attr('data-id');
        var title = $(this).children('a').text();
        if (title == "首页") {
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