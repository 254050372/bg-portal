<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="zh">
[#include "common/head.ftl"/]
<body class="hold-transition skin-blue sidebar-mini">
[#include "common/base/baseJSAttribute.ftl"/]
<div class="wrapper">
[#include "common/top.ftl"/]
[#include "common/left.ftl"/]
    <!-- Content Wrapper. Contains page content -->
    <div id="index_content_wrapper" class="content-wrapper">
     [#--主要内容区域--]
    </div>
    <!-- /.content-wrapper -->

[#include "common/bottom.ftl"/]
    <script type="text/javascript">
        //tab窗口
        $(function () {
            var initParam={
                link : '.multitabs',                        //触发multitabs的selector text，注意需要有".","#"等
                iframe : false,                             //iframe模式的总局设置。当值为false的时候，为智能模式，自动判断（内网用ajax，外网用iframe）。缺省为false。
                class : '',                                 //主框架的class
                init : [                                    //需要在初始加载的tab
                    {
                        type :'main',                           //标签页的类型，有 main | info，缺省为 info
                        title : '[@spring.message code="index.main.title" /]',  //标题（可选），没有则显示网址
                        //content: '',                        //html内容，如果设定此值，下面的URL设定则无效
                        url : '${basePath}/homePage'  //链接
                    }
                ],
                nav : {
                    backgroundColor : '#f5f5f5',            //默认nav-bar 背景颜色
                    class : '',                             //为nav添加class
                    draggable : true,                       //nav tab 可拖动选项
                    fixed : false,                          //固定标签头列表
                    layout : 'default',                     //有两种模式，'default', 'classic'(所有隐藏tab都在下拉菜单里) 和 'simple'
                    maxTabs : 5,                           //最多tab数量。（main和editor不计算在内) 当为1时，整个标签栏隐藏。main和editor分别只能有1个标签。
                    maxTabTitleLength : 25,                 //tab标题的最大长度
                    showCloseOnHover : true,                //当值为true，仅在鼠标悬浮时显示关闭按钮。false时一直显示
                    style : 'nav-pills',                     //可以为nav-tabs 或 nav-pills
                },
                content : {
                    ajax : {
                        class : '',                         //为ajax tab-pane 添加class
                        error : function (htmlCallBack) {
                            toastr.error('[@spring.message code="index.main.load.error" /]');
                            return htmlCallBack;
                        },
                        success : function (htmlCallBack) {
                            //modify html and return
                            return htmlCallBack;
                        }
                    },
                    iframe : {
                        class : ''                          //为iframe tab-pane 添加class
                    }
                },
                language : {                                //语言配置
                    nav : {
                        title : '选项卡',                                  //默认的标签页名称
                        dropdown : '<i class="fa fa-bars"></i>',        //标签栏的下拉菜单名称
                        //下拉菜单的显示激活页面
                        showActivedTab :'[@spring.message code="index.main.show.actived.tab"/]',
                        //下拉菜单的关闭所有页面
                        closeAllTabs :  '[@spring.message code="index.main.close.all.tabs"/]',
                        //下拉菜单的关闭其他页面
                        closeOtherTabs :'[@spring.message code="index.main.close.other.tabs"/]',
                    }
                }
            }
            //初始化选项卡
            $('#index_content_wrapper').multitabs(initParam);
        });
    </script>