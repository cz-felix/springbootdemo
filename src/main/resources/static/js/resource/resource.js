layui.config({
    base: "/js/resource/"
}).use(["tabletree", "laypage", "form"], function() {
    var o = layui.jquery,
        l = o(".table-list"),
        b = o(".handle-btn"),
        s = o(".content-search"),
        tb = "#tabletree",
        tp = "#table-pages";
    o(window).scroll(function() {
        var fh = (s.length ? s.height() : 0) + (b.length ? b.height() : 0);
        fh && o(window).scrollTop() > fh ? b.addClass("listTopFixed") : b.removeClass("listTopFixed")
    });
    l.on("click", "tbody tr", function() {
        o(this).toggleClass("tableTrSelect").siblings().removeClass("tableTrSelect")
    });
    if (o(tb).length) {
        layui.tabletree({
            elem: tb
        })
    }
});