<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1164'/></title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	#main-nav {
margin-left: 1px;
}
#main-nav.nav-tabs.nav-stacked > li > a {
padding: 10px 8px;
font-size: 12px;
font-weight: 600;
color: #4A515B;
background: #E9E9E9;
background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FAFAFA), color-stop(100%,#E9E9E9));
background: -webkit-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
background: -o-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
background: -ms-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
background: linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9');
-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
border: 1px solid #D5D5D5;
border-radius: 4px;
}
#main-nav.nav-tabs.nav-stacked > li > a > span {
color: #4A515B;
}
#main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover {
color: #FFF;
background: #3C4049;
background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#4A515B), color-stop(100%,#3C4049));
background: -webkit-linear-gradient(top, #4A515B 0%,#3C4049 100%);
background: -o-linear-gradient(top, #4A515B 0%,#3C4049 100%);
background: -ms-linear-gradient(top, #4A515B 0%,#3C4049 100%);
background: linear-gradient(top, #4A515B 0%,#3C4049 100%);
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049');
-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
border-color: #2B2E33;
}
#main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
color: #FFF;
}
#main-nav.nav-tabs.nav-stacked > li {
margin-bottom: 4px;
}
/*定义二级菜单样式*/
.secondmenu a {
font-size: 10px;
color: #4A515B;
text-align: center;
}
.navbar-static-top {
background-color: #212121;
margin-bottom: 5px;
}
.navbar-brand {
background: url('') no-repeat 10px 8px;
display: inline-block;
vertical-align: middle;
padding-left: 50px;
color: #fff;
}

.secondmenu a {
	font-size: 14px;
	color: #4A515B;
	text-align: center;
}

/* .active1 {
	background-color: #eee;
	border-color: #2a5165;
} */
/* 
.active1 {
	background-color: #eee;
	border-color: #eee;
} */


	</style>
 	<script type="text/javascript"> 
		$(document).ready(function() {
			$(".nav-header").click(function(){
				var menuid = $(this).attr('menuid');
				if($('.menuid-'+menuid).hasClass('icon-chevron-right')){
					$('.menuid-'+menuid).removeClass('icon-chevron-right').addClass('icon-chevron-down');
				}else{
					$('.menuid-'+menuid).removeClass('icon-chevron-down').addClass('icon-chevron-right');
				}
				
				//点击某一个菜单 收起其他已经打开的菜单
				$.each($(".nav-header"),function(index,value){
					
					if($(value).attr('menuid')!=menuid&&!$(value).hasClass('collapsed')){
						$(value)[0].click();
					}
				})
			});
			$(".secondmenu li").click(function(){
				$(".secondmenu li").removeClass("active");
				$(this).addClass("active");
			});
			
			$(".secondmenu li:first a")[0].click();
			var parentMenuId = $(".secondmenu li:first a").attr('parentMenuId');
			$("#a"+parentMenuId)[0].click();
		});
	</script> 
</head>
<body>
<div class="container-fluid">
<div class="row">
<div class="col-md-2">
<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">

		<c:set var="menuList" value="${fns:getMenuList()}"/>
		<c:set var="firstMenu" value="true"/>
		<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
			<c:if test="${menu.parent.id eq (not empty param.parentId?param.parentId:'1')&&menu.isShow eq '1'}">
				<li>
					<a href="#${menu.id}" id="a${menu.id }" menuid="${menu.id}" class="nav-header collapsed" data-toggle="collapse">
						&nbsp;<spring:message code="${menu.internationalKey}"/>
						<span class="pull-right  icon-chevron-right menuid-${menu.id}" style="margin-top: 2px"></span>
					</a>
					<ul id="${menu.id}" class="nav nav-list collapse secondmenu" style="height: 0px;">
						<c:forEach items="${menuList}" var="menuChild">
							<c:if test="${menuChild.parent.id eq menu.id&&menuChild.isShow eq '1'}">
								<li>
									<a parentMenuId="${menuChild.parent.id}" href="${fn:indexOf(menuChild.href, '://') eq -1?ctx:''}${not empty menuChild.href?menuChild.href:'/404'}" target="${not empty menuChild.target?menuChild.target:'mainFrame'}" >
										<i class="icon-${not empty menuChild.icon?menuChild.icon:'circle-arrow-right'}"></i>
										&nbsp;<spring:message code="${menuChild.internationalKey}"/>
									</a>
								</li>
								<c:set var="firstMenu" value="false"/>
							</c:if>
						</c:forEach>
					
					</ul>
				</li>	
					
			</c:if>
		</c:forEach>

</ul>
</div>

</div>
</div>


<%-- 	<div class="accordion" id="menu">
		<c:set var="menuList" value="${fns:getMenuList()}"/>
		<c:set var="firstMenu" value="true"/>
		<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
			<c:if test="${menu.parent.id eq (not empty param.parentId?param.parentId:'1')&&menu.isShow eq '1'}">
				<div class="accordion-group">
		    		<div class="accordion-heading">
		    			<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapse${menu.id}" title="${menu.remarks}"><i class="icon-chevron-${firstMenu?'down':'right'}"></i>&nbsp;<spring:message code="${menu.internationalKey}"/> </a>
		    		</div>
		    		<div id="collapse${menu.id}" class="accordion-body collapse ${firstMenu?'in':''}">
						<div class="accordion-inner">
							<ul class="nav nav-list">
							<c:forEach items="${menuList}" var="menuChild">
								<c:if test="${menuChild.parent.id eq menu.id&&menuChild.isShow eq '1'}">
									<li>
										<a href="${fn:indexOf(menuChild.href, '://') eq -1?ctx:''}${not empty menuChild.href?menuChild.href:'/404'}" target="${not empty menuChild.target?menuChild.target:'mainFrame'}" >
											<i class="icon-${not empty menuChild.icon?menuChild.icon:'circle-arrow-right'}"></i>
											&nbsp;<spring:message code="${menuChild.internationalKey}"/>
										</a>
									</li>
									<c:set var="firstMenu" value="false"/>
								</c:if>
							</c:forEach>
							</ul>
					   </div>
		    		</div>
				</div>
			</c:if>
		</c:forEach>
	</div> --%>
</body>
</html>
