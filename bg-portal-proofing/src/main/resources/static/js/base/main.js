//全局ajax提交标记
var ajaxSubmitFlag=true;
$(function(){

    /** 提示框自定义**/
    toastr.options = {
        //顶部中间
        positionClass: "toast-top-center",
        //超时消失毫秒
        timeOut: "2000"
    };
    //全局信息提示
    $(document).ajaxComplete(function(event, xhr, options) {
        if(xhr.responseJSON!=null){
            var rw=xhr.responseJSON;
            if (rw.resultType == 'SUCCESS') {
                toastr.success(rw.msg);
            } else if (rw.resultType == 'WARN') {
                toastr.warning(rw.msg);
            } else if (rw.resultType == 'ERROR') {
                toastr.error(rw.msg);
            }
        }
        ajaxSubmitFlag=true;
    });
    //当 AJAX 请求即将发送时
    $(document).ajaxSend(function(event, xhr, options) {
        ajaxSubmitFlag=false;
    });
});