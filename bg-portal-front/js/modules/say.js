layui.define(['layer'], function(exports){
    var layer = layui.layer;
    layer.msg('heiheihei!');
    exports('say', function(){
        layer.msg('xixixi!');
    }); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});