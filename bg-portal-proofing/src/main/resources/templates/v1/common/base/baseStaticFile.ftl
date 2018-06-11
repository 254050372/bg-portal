[#--公共静态文件和静态变量存放文件--]
[#import "/spring.ftl" as spring/]
[#include "baseAttribute.ftl"/]
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
[@jsImport isExternal=true path="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"][/@jsImport]
[@jsImport isExternal=true path="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"][/@jsImport]
<![endif]-->
<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
[@jsImport path="/adminLTE/bower_components/jquery/dist/jquery.min.js"][/@jsImport]
[#--<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/jquery.validate.min.js"></script>--]
[#--jquery 验证--]
[@jsImport path="/js/validate/jquery.validate.min.js"][/@jsImport]
[@jsImport path="/js/validate/validate_local.js"][/@jsImport]
[@jsImport path="/js/validate/jquery.metadata.js"][/@jsImport]

<!-- Bootstrap 3.3.7 -->
[@jsImport path="/adminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"][/@jsImport]
<!-- AdminLTE App -->
[@jsImport path="/adminLTE/dist/js/adminlte.min.js"][/@jsImport]
<!-- Google Font -->
[@cssImport isExternal=true path="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"][/@cssImport]
[@cssImport path="/adminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css"][/@cssImport]
[#--
[@cssImport path="/adminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css"][/@cssImport]
--]
<!-- Font Awesome -->
[@cssImport path="/adminLTE/bower_components/font-awesome/css/font-awesome.min.css"][/@cssImport]
<!-- Ionicons -->
[@cssImport path="/adminLTE/bower_components/Ionicons/css/ionicons.min.css"][/@cssImport]
<!-- Theme style -->
[@cssImport path="/adminLTE/dist/css/AdminLTE.min.css"][/@cssImport]
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
      page. However, you can choose any other skin. Make sure you
      apply the skin class to the body tag so the changes take effect. -->
[@cssImport path="/adminLTE/dist/css/skins/skin-blue.min.css"][/@cssImport]
[#--动态标签页https://github.com/edwinhuish/bootstrap-multitabs--]
[@cssImport path="/plugins/bootstrap-multitabs/css/style.css"][/@cssImport]
[@jsImport path="/plugins/bootstrap-multitabs/js/multitabs.js"][/@jsImport]

[#--<script src="https://cdn.bootcss.com/toastr.js/2.1.4/toastr.min.js"></script>--]
[@jsImport path="/js/base/toastr.min.js"][/@jsImport]
[#--<link href="https://cdn.bootcss.com/toastr.js/2.1.4/toastr.min.css" rel="stylesheet">--]
[@cssImport path="/css/base/toastr.min.css"][/@cssImport]
[#--<script src="https://cdn.bootcss.com/vue/2.5.13/vue.min.js"></script>--]
[@jsImport path="/js/base/vue.min.js"][/@jsImport]
[#--ladda按钮加载样式，api：http://www.htmleaf.com/jQuery/Buttons-Icons/201506232089.html--]
[@cssImport path="/css/base/ladda-themeless.min.css"][/@cssImport]
[#--登录界面样式--]
[@cssImport path="/css/module/login/signin.css"][/@cssImport]
[@jsImport path="/js/base/spin.min.js"][/@jsImport]
[@jsImport path="/js/base/ladda.min.js"][/@jsImport]
[#--自定义js--]
[@jsImport path="/js/base/main.js"][/@jsImport]


<style type="text/css">
    /*渲染完成vue组件前不展示元素{{}}，在vue渲染元素内标记 v-cloak即可*/
    [v-cloak] {
        display: none;
    }
</style>
