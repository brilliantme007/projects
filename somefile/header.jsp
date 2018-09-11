<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%-- start header --%>
<script type="text/javascript">
	var MyInterval_checkMoreBack;	
	jQuery(function(){
		checkMore();
	});
	MyInterval_checkMoreBack=setInterval("checkMore()",10000);
	function checkMore(){
		var unique=jQuery("#checkMoreUnique").val();
		if(unique!=null&&unique!=undefined&&unique!=""){	
			$.post("checkMore.action",function(data){
				if(data.success!=null){
					
					if(data.success!='-1' && data.success!=unique){
						layer.msg("当前账号已在其他地方登录,即将退出登录!", {
							icon : 0,
							time : 5000
						}, function() {
							
							clearInterval(MyInterval_checkMoreBack);
							window.location.href = "${ctx}/logout";
						});
					}
				}
			});	
		}
		
	}
</script>
<div id="header">
    <div id="logo"><input type="hidden" id="checkMoreUnique" value="${login_KeyParam}"/></div>
    <i class="gull"><img src="${ctx}/resources/images/header_gull.png" width="73" height="29"  alt=""/></i>
	<div id="lv2nav">
    	<ul>
        	<li data-tt='h6' data-ll="navhd6_l1"><a href="#" class="nav_go_supervise">使用介绍</a></li>
        	<li data-tt='h7' data-ll="navhd7_l1"><a href="#" class="nav_go_statistical">统计分析</a></li>
        	<li data-tt='h4' data-ll="navhd5_l1,navhd5_l2"><a href="#" class="nav_go_property">资讯管理</a></li>
        	<li data-tt='h3' data-ll="navhd1_l3,navhd1_l4,navhd2_l1,navhd2_l3,navhd4_l1"><a href="#" class="nav_go_procurement">考试测评</a></li>
        	<li data-tt='h2' data-ll="navhd1_l5,navhd1_l6,navhd1_l7,navhd2_l2"><a href="#" class="nav_go_cons">网络课程</a></li>
            <shiro:hasAnyRoles name="admin,padmin,uadmin">
        	    <li data-tt='h1' data-ll="navhd1_l1,navhd1_l2,navhd1_l8,navhd1_l9" class="current"><a href="#" class="nav_go_index">用户管理</a></li>
        	</shiro:hasAnyRoles>
        </ul>
    </div>
</div>
<%-- end header --%>

<%-- start topbar --%>
<div class="topbar">
	<div class="current_position"> 当前位置：系统首页</div>
    <div class="userid">
    	<em>欢迎您：<shiro:principal property="name"/></em>
        <a href="${ctx}/logout">安全退出</a> | <a href="index.html">门户首页</a>
    </div>
</div>
<%-- end topbar --%>