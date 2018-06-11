

<!DOCTYPE html>
<html lang="zh">
[#include "common/head.ftl"/]
<body style="min-height: auto!important;">

<div class="htmleaf-container" id="loginBody" v-cloak>
    <div class="signin">
        <div class="signin-head"><img src="${baseResourcePath}/img/login/login.gif" alt="" class="img-circle"></div>
        <form class="form-signin" role="form" id="loginForm" onsubmit="return false;">
            <input type="text" name="username" id="username" class="form-control t-show" placeholder="[@spring.message code="login.username" /]" autofocus/>
            <input type="password" name="password" id="password" class="form-control t-show" placeholder="[@spring.message code="login.password" /]"/>
            <button class="btn btn-lg btn-warning btn-block ladda-button" data-style="zoom-out"  type="submit" id="submit">[@spring.message code="login.login" /]</button>
            <label class="checkbox">
                [#--<input type="checkbox" value="remember-me"> 记住我--]
            </label>
        </form>
        <div class="form-signin">[@spring.message code="login.copy.right" /]</div>
    </div>
</div>
</body>
</html>
<script>
    $(function () {
        var oriData = {
            rw: {},
            showAlert: false,
            alertType: ''
        }
        /*Vue.component('alert-msg', {
            template: '<div class="alert" v-bind:class="alertType" v-show="showAlert" id="alertMsg">\n' +
            '        <strong class=\'alertType\'>\n' +
            '            <template v-if="rw.resultType==\'SUCCESS\'">操作成功！</template>\n' +
            '            <template v-else-if="rw.resultType==\'WARN\'">操作失败，原因：</template>\n' +
            '            <template v-else="rw.resultType==\'ERROR\'">警告</template>\n' +
            '        </strong>\n' +
            '        <span class="alertMsg">{{rw.msg}}</span>\n' +
            '        <a href="javascript:void(0)" class="close" @click="closeMsg()">\n' +
            '            &times;\n' +
            '        </a>\n' +
            '    </div>',
            data: function () {
                return oriData;
            },
            methods: {
                closeMsg: function () {
                    oriData.showAlert=false;
                }
            }
        })*/

        var login = new Vue({
            el: '#loginBody',
            data: oriData
        });
        //数据+dom更新完毕后回调事件
        login.$nextTick(function () {
            $("#loginForm").validate({
                //debug: true, //调试模式取消submit的默认提交功能
                //errorClass: "label.error", //默认为错误的样式类为：error
                focusInvalid: false, //当为false时，验证无效时，没有焦点响应
                onkeyup: false,
                submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form
                    //按钮loding,需要绑定样式ladda-button，以及属性：data-style="zoom-out"
                    var submitButton=Ladda.create($("#submit").get(0));
                    //按钮设置加载中
                    submitButton.start();
                    if(!ajaxSubmitFlag){
                        toastr.info('[@spring.message code="server.busy" /]');
                        return;
                    }
                    $.post('${basePath}/login',
                            $("#loginForm").serialize(),
                            function (data, status) {
                                oriData.rw = data;
                                if (data.resultType == 'SUCCESS') {
                                    window.location.href = '${basePath}/index'
                                }
                                //按钮加载取消
                                submitButton.stop();
                            });
                    //form.submit();   //提交表单
                },
                rules: {
                    username: {
                        required: true
                    },
                    password: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: '[@spring.message code="form.validate.required" /]'
                    },
                    password: {
                        required: '[@spring.message code="form.validate.required" /]'
                    }
                }
            });
        });

    })
</script>
