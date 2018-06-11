<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${baseResourcePath}/adminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${(user.username)!}</p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i>在线</a>
            </div>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input id="searchMenu" type="text" name="q" class="form-control" placeholder="菜单搜索..." >
                <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">HEADER</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="active">
                <a class="show-tab multitabs"  href="${basePath}/login">
            <i class="fa fa-link"></i> <span>当前点击菜单</span></a></li>
            <li>
                <a class="show-tab multitabs"  href="b.html">
            <i class="fa fa-link"></i> <span>Another Link</span></a></li>
            <li class="treeview">
                <a href="javascript:void(0);">
                    <i class="fa fa-link"></i>
                    <span>点菜</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a id="3" class="show-tab multitabs"  href="c.html">菜单1</a></li>
                    <li><a id="4" class="show-tab multitabs"  href="d.html">菜单2</a></li>
                </ul>
            </li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
<script type="text/javascript">
    $(function(){
        //菜单搜索
        $("#searchMenu").keypress(
            function(){
                if(window.event.keyCode == 13){
                    if (this.val!=null && this.val!=''){
                        $("#search-btn").click();
                    }
                }
            }
        );
    });
</script>