$(function(){
    /**
     * jq validate 中文配置
     */
    $.extend($.validator.messages, {
        required: "必填",
        remote: "请修改该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入拥有合法后缀名的字符串",
        maxlength: $.validator.format("不能输入超过 {0}位 的字符串"),
        minlength: $.validator.format("不能输入少于 {0}位 字符串 "),
        rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
        range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: $.validator.format("不能超过 {0} ！"),
        min: $.validator.format("不能小于 {0} ！")
    });
    //jq validate 错误放置自定义
    $.validator.setDefaults({
        //修改默认错误提示为tooltip提示
        errorPlacement: function(error, element) {
            if($(element).hasClass("t-show")){
                if(error.html() != null && error.html().length > 0) {
                    $(element).data("trigger", "manual");
                    $(element).attr("title", error.html()).tooltip('fixTitle').tooltip('show');
                }
            }
        },
        //验证成功隐藏tooltip提示
        success: function(error, element) {
            if($(element).hasClass("t-show")){
                $(element).tooltip('hide');
            }
        }
    })
})