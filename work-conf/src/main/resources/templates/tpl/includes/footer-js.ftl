<script src="${appHost}/modules/nprogress/nprogress.js?v=${ver!'1'}" ></script>
<script src="${appHost}/js/load.js?v=${ver!'1'}" ></script>
<!--[if lt IE 9]>
<script src="//cdn.bootcss.com/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
<script src="//cdn.bootcss.com/html5shiv/3.7.3/html5shiv.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<#include "/includes/jquery.ftl" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type="application/javascript" src="${appHost}/js/jquery.menu-aim.js" ></script>
<script type="application/javascript" >
    $(function () {
        //当点击跳转链接后，回到页面顶部位置
        $("#jdm-tbar-tab-top").on("click",function () {
            $('body,html').animate({scrollTop:0},2000);
            return false;
        });
    });
    $(function(){
        $('#logo').on({
            mouseover : function(){
                $('#logo .tip').show();
            } ,
            mouseout : function(){
                $('#logo .tip').hide();
            }
        }) ;
    });
</script>

<#include "/includes/baidu-zz.ftl" />
