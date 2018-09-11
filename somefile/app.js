


$(function () {

        //导航宽度判断
        var $nav_w = $(".nav").width();
        var $nav_li_len = $(".nav").find("li").length;
        if ($nav_li_len > 6) {
            $(".nav li").width(($nav_w - $nav_li_len+1) / $nav_li_len);
        } else {
            $(".nav li").width(140);
        }
});
$('.nav ul').find('li:eq(0)').addClass('active');
$('.nav ul li').on('click',function () {
    $(this).addClass('active').siblings("li").removeClass('active');
})


$('.tab-title a').eq(0).addClass("active");
$('.leftBottomModule').find('ul:eq(0)').css('display','block');
function tab_switch(cname, delay){
    //默认延迟为300ms
    var delay = delay ? delay : 0;
    //控制切换
    $('.' + cname).on('click', '.tab-title>li>a', function () {
        var tabindex = $(this).parent().index();

        var that = $(this);
        var change_tabs = setTimeout(function () {

            that.addClass("active").parent().siblings('li').children('a').removeClass("active");
            that.parents(".leftTopModule").siblings('.leftBottomModule').find("ul").eq(tabindex).show().siblings("ul").hide();
        }, delay);
        $(this).mouseout(function () {
            clearTimeout(change_tabs)
        })
    })
}


tab_switch("tab1", 0);
tab_switch("tab2", 0);
tab_switch("tab3", 0);

//footer
function setFooter() {
    var bodyHH = document.documentElement.clientHeight;
    var docHH = $('.header').height()+$('.nav').height()+$('.body-main').height()+$('.footer').height();
    if(bodyHH > docHH){
        $('.footer').css("position","fixed");
        $('.footer').css("bottom","0px")
    }else {
        $('.footer').css("position","relative");
    }
}
setFooter()
window.onresize = function(){
    setFooter()
}

// 侧边菜单
$('.sidebar-nav-sub-title').on('click', function () {
    $('.sidebar-nav-sub').slideUp(80)
        .end()
        .find('.sidebar-nav-sub-ico').removeClass('sidebar-nav-sub-ico-rotate');
    var nextSub = $(this).next('.sidebar-nav-sub');
    if (nextSub.is(':hidden')) {
        nextSub.slideToggle(80)
            .end()
            .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
    }
});

//计算leftside高度
var headerHight = $(".header").height();
var leftHeight = $(document).height() - headerHight;
$(".left-sidebar").height(leftHeight);

//计算menu高度
var windowHeight = $(window).height();
$(".left-sidebar").height(windowHeight - headerHight );

//计算地区码表的高 row-left
var containerFluidHeight = $(".container-fluid").height();
var rowContentHeight = windowHeight - headerHight - containerFluidHeight-80;
$(".row-left").height(rowContentHeight);
var areaCodeHeadHeight = $(".areaCode-head").height();
$(".areaCode-body").height(rowContentHeight-areaCodeHeadHeight-22);

// 地区码表展开
//$(".areaCode-body .sidebar-nav").find("li:eq(0)").addClass("active").next("li").css("margin-top","10px");
$('.areaCode-body > .sidebar-nav > .sidebar-nav-link > .areaCode-plus').on('click', function () {
    $('.areaCode-ul').slideUp(80);
    $('.iconfont.areaCode-plus').removeClass("am-icon-minus-square-o").addClass("am-icon-plus-square-o");
    var nextSub = $(this).siblings('.areaCode-ul');
    if(nextSub.is(':hidden')){
        nextSub.slideToggle(80);
        $(this).removeClass("am-icon-plus-square-o").addClass("am-icon-minus-square-o");
    }else{
        $(this).removeClass("am-icon-minus-square-o").addClass("am-icon-plus-square-o");
    }
});
$(".sidebar-nav-sub > .sidebar-nav-link > a").on('click',function () {
    $(".sidebar-nav-sub > .sidebar-nav-link > a").removeClass("active");
    $(this).addClass("active");
});


$(function () {
    //table foot 单元格合并
    $(".tfoot-outer").each(function () {
        var th_length = $(this).parents("table").find("thead > tr > td").length;
        $(this).attr("colspan", th_length);
    });
    //计算leftside高度
    var headerHight = $(".header").height();
    var leftHeight = $(document).height() - headerHight;
    $(".left-sidebar").height(leftHeight);

    //计算menu高度
    var windowHeight = $(window).height();
    $(".left-sidebar").height(windowHeight - headerHight );
    /*$("#leftMenuBox").sticky({
        top: 70,
        theme:"light-thick"
    });*/


    //右下浮动
    var blockSidebar = "<div class='block-sidebar'><a href='javascript:void(0)' class='gotop'><i class='iconfont icon-top'></i></a></div>";
    $(blockSidebar).appendTo("body");
    $(window).scroll(function (e) {
        var scrollTop = $(window).scrollTop();
        if (scrollTop > 200) {
            $(".block-sidebar").fadeIn();
        } else {
            $(".block-sidebar").fadeOut();
        }
    });
    $(document).on('click', ".gotop",function () {
        $("body,html").animate({"scrollTop": 0})
    });

    //table横向滚动条
    if($(".table-body").length > 0){
        $(".table-body").mCustomScrollbar({
            axis: "x",
            autoExpandScrollbar: true,
            advanced: {autoExpandHorizontalScroll: true}
        });
    }

    //角色选择
    $(".info-wrap-role input").on('click', function () {
        var icheck = '<i class="checked"></i>';
        if ($(this).is(':checked')) {
            //加入勾选样式
            $(".info-wrap-role label").removeClass("active-label").find("i.checked").remove();
            $(this).prev('label').addClass("active-label").prepend(icheck);

            //点击非会员显示地区
            if (!$('#sccin_member').is(':checked')) {
                $('.area-hide').slideDown();
            } else {
                $('.area-hide').slideUp();
            }

        } else {
            return false;
        }
    });


    //步骤
    $(".step-bar.active").prevUntil().addClass("complate");
    var stepItem = $(".step-wrap .step-bar");
    var stepsNumber = stepItem.length;
    stepItem.width(100 / stepsNumber + "%");
    stepItem.each(function (e) {
        var index=$(this).index();
        $(this).find(".step-point").text(index + 1);
    })

});


//table 宽度拖动
function widthDrag() {
    var tTD; //用来存储当前更改宽度的Table Cell,避免快速移动鼠标的问题
    var table = document.getElementById("widthDragable");
    for (j = 0; j < table.rows[0].cells.length; j++) {
        table.rows[0].cells[j].onmousedown = function () {
//记录单元格
            tTD = this;
            if (event.offsetX > tTD.offsetWidth - 10) {
                tTD.mouseDown = true;
                tTD.oldX = event.x;
                tTD.oldWidth = tTD.offsetWidth;
            }
//记录Table宽度
//table = tTD; while (table.tagName != ‘TABLE') table = table.parentElement;
//tTD.tableWidth = table.offsetWidth;
        };
        table.rows[0].cells[j].onmouseup = function () {
//结束宽度调整
            if (tTD == undefined) tTD = this;
            tTD.mouseDown = false;
            tTD.style.cursor = 'default';
        };
        table.rows[0].cells[j].onmousemove = function (element) {

//更改鼠标样式
            if (event.offsetX > this.offsetWidth - 10)
                this.style.cursor = 'col-resize';
            else
                this.style.cursor = 'default';
//取出暂存的Table Cell
            if (tTD == undefined) tTD = this;
//调整宽度
            if (tTD.mouseDown != null && tTD.mouseDown == true) {
                tTD.style.cursor = 'default';
                if (tTD.oldWidth + (event.x - tTD.oldX) > 0)
                    tTD.width = tTD.oldWidth + (event.x - tTD.oldX);
//调整列宽
                tTD.style.width = tTD.width;
                tTD.style.cursor = 'col-resize';
//调整该列中的每个Cell

                table = tTD;
                while (table.tagName != 'TABLE') table = table.parentElement;
                for (j = 0; j < table.rows.length; j++) {
                    table.rows[j].cells[tTD.cellIndex].width = tTD.width;
                }
//调整整个表
//table.width = tTD.tableWidth + (tTD.offsetWidth – tTD.oldWidth);
//table.style.width = table.width;
            }
        };
    }
}
$(function () {
    if($("#widthDragable").length>0){
        widthDrag();
    }

});



/*var tabHeight=function () {
    var $win_w = $(window).width();
    var $tabs = $(".page-tabs-controler-wrap a");
        if($win_w<1351){
            $tabs.each(function () {
                $(this).css({
                    height:"50px",
                    width:"10%"
                });
            });
        }else {
            $tabs.each(function () {
                $(this).css({
                    height:"30px",
                    width:"auto"
                });
            });
        }
    };
$(window).resize(function () {
    tabHeight()
})*/


//滚动插件
;(function($, window, document,undefined) {
    //定义Beautifier的构造函数
    var Beautifier = function(ele, opt) {
        this.$element = ele,
        this.defaults = {
            'interval': 3000, //间隔
            'height': 24, //高度
            "controler":false
        },
        this.options = $.extend({}, this.defaults, opt);
    };
    //定义Beautifier的方法
    Beautifier.prototype = {
        beautify: function() {
            var $ele = this.$element,
                $opt = this.options;
            $ele.height($opt.height+"px");


            //执行滚动
            var funcSlidUp = function () {
                var $ul = $ele.find("ul"),
                    $li = $ul.find("li");
                $ul.css({
                    "position":"relative"
                });
                console.log("up");
                $ul.animate({
                    "top":"-"+$opt.height+"px"
                },function () {
                    //滚动完毕则把第一个li添加到最后面，并且把ui的top设置0
                    $li.eq(0).appendTo($ul);
                    $ul.css({"top":0})
                });
            };
            var funcSlidDown = function () {
                var $ul = $ele.find("ul"),
                    $li = $ul.find("li");
                $ul.css({
                    "position":"relative"
                });
                console.log("down");
                $li.eq(-1).prependTo($ul);
                $ul.css({"top":-$opt.height+"px"});
                $ul.animate({
                    "top":0
                });
            };

            var interVal = setInterval(funcSlidUp,$opt.interval);
            $ele.on("mouseenter",function (e) {
                clearInterval(interVal);
            }).on("mouseleave",function (e) {
                interVal = setInterval(funcSlidUp,$opt.interval);
            });

            if($opt.controler){
                $ele.append("<div class='controler'><a href='javascript:void(0)' class='up am-icon-angle-up'></a><a href='javascript:void(0)' class='down am-icon-angle-down'></a></div>");
                var $up=$ele.find(".up"),
                    $down=$ele.find(".down");
                $up.on('click',function (e) {
                    funcSlidUp()
                });
                $down.on('click',function (e) {
                    funcSlidDown()
                })
            }
        }
    };
    //在插件中使用Beautifier对象
    $.fn.linkSlider = function(options) {
        //创建Beautifier的实体
        var beautifier = new Beautifier(this, options);
        //调用其方法
        return beautifier.beautify();
    }


})(jQuery, window, document);










