<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 7px 0 10px;}
		#header {margin:0 0 10px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, <spring:message code='str1237'/>;font-size:26px;padding-left:43px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;}
        .btn_private {
            background: linear-gradient(to bottom, #ff782c 0%, #f86325 100%) repeat scroll 0 0 rgba(0, 0, 0, 0);
            color: #fff;
            font: bold 12px/32px "microsoft yahei";
            text-align: center;
            border-radius:3px;
            margin: 4px;
            cursor: pointer;
			padding: 2px 2px;
        }
		.btn_blue {
			background: linear-gradient(to bottom, #1f5c94 0%, #1f5c94 100%) repeat scroll 0 0 rgba(0, 0, 0, 0);
			color: #fff;
			font: bold 12px/32px "microsoft yahei";
			text-align: center;
			border-radius: 4px;
			margin: 4px;
			cursor: pointer;
			padding: 2px 2px;
		}
		.select2-container{
			width: 100px;
		}
		#s2id_pid {
			width: 100px;
		}
		.select2-container .select2-choice {
			display: block;
			height: 28px;
			padding: 0 0 0 8px;
			overflow: hidden;
			position: relative;
			border: 0px solid #FAFAFA;
			white-space: nowrap;
			/* line-height: 26px; */
			text-decoration: none;
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
			-webkit-background-clip: padding-box;
			-moz-background-clip: padding;
			background-clip: padding-box;
			-webkit-touch-callout: none;
			-webkit-user-select: none;
			-khtml-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
			background-color: #fff;
			background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #1F5C94), color-stop(0.5, rgb(31, 92, 148)));
			color: #fff;
		}
		.select2-container .select2-choice div {
			display: inline-block;
			width: 18px;
			height: 100%;
			position: absolute;
			right: 0;
			top: 0;
			/* border-left: 1px solid #aaa; */
			-webkit-border-radius: 0 4px 4px 0;
			background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #1F5C94), color-stop(0.6, #1F5C94));
		}
		.select2-container .select2-choice div b {
			background: url("select2.png") no-repeat scroll 0 1px #1f5c94;
			display: block;
			height: 100%;
			width: 100%;
			background-color: #1f5c94;
		}
		.font-style{
			font-family: impact, sans-serif;
			color: #FFFFFF;
			font-size: 30px;
			
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#menu a.menu").click(function(){
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
			});

			$("#s2id_pid").width("100px");

			$("#s2id_pid .select2-choice span").text($("#defaultPid").val());
			$("#s2id_pid .select2-choice").attr("style","background:#1F5C94");

		});
        function selectServer(){
            top.$.jBox.open("iframe:${ctx}/tools/gameArea/selectSingleGameServer", "<spring:message code='str1238'/>(<spring:message code='str1239'/>)",810,$(top.document).height()-240,{
                buttons:{"<spring:message code='str173'/>":"ok", "<spring:message code='str876'/>":true}, bottomText:"<spring:message code='str1240'/>",submit:function(v, h, f){
                    var selectedGameServerId = h.find("iframe")[0].contentWindow.selectedGameServerId;
                    var selectedGameServerName = h.find("iframe")[0].contentWindow.selectedGameServerName;
					var selectedGamePlatformName = h.find("iframe")[0].contentWindow.selectedGamePlatformName;

                    if (v=="ok"){
                        //loading('<spring:message code='str1105'/>...');
                        //$("#changeGameServerForm").attr('action','${ctx}/tools/gameArea/changeGameServer?gameServerId='+selectedGameServerId+"&gameSeverName="+selectedGameServerName).submit();
                        _remoteCall("${ctx}/tools/gameArea/changeGameServer",{gameServerId:selectedGameServerId,gameServerName:selectedGameServerName,pid:selectedGamePlatformName},function(result){
                                if(result.success){
									top.$.jBox.tip('<spring:message code='str1241'/>', 'success');
                                    $("#currentGameServer").html(result.data)
                                }
                        });

                        return true;
                    }
                }, loaded:function(h){
                    $(".jbox-content", top.document).css("overflow-y","hidden");
                }
            });
        }

		function setCurrentPlatform(){
			var pid = $("#s2id_pid .select2-choice span").text();
			_remoteCall("${ctx}/tools/gameArea/changePlatform",{pid : pid},function(result){
				if(result.success){
					var pid = $("#s2id_pid .select2-choice span").text();
					var areaId = result.data;
					console.log("<spring:message code='str1242'/>" + pid, +" <spring:message code='str768'/>:" + areaId);
					$("#currentGameServer").text(areaId)
				}
			});
		}


		function selectSingleGameServer(){
			var defaultPid = $("#s2id_pid .select2-choice span").text();
			if(defaultPid == null) {
				tips("<spring:message code='str1243'/>!");
				return;
			}

			$.ajax({
				type : "POST",
				url: "${ctx}/tools/gameArea/selectSingleGameArea",
//				data: {pid:defaultPid},
				dataType: 'text',
				before:function(){
					loading("<spring:message code='str1244'/>...")
				},
				success:function(data){

					var d = top.dialog({
						title: '<spring:message code='str1631'/>-(<spring:message code='str1632'/>)',
						lock:true,
						width:'100%',
						height:'100%',
						content: data,
						ok: function(){
							top.myCallback();
						},
						cancel: function(){}
					});

					d.showModal();
				}
			})
		}

	</script>
</head>
<body>
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top navbar-inverse">
		  <%--<div class="navbar-inner navbar-black">--%>
			  <%--<img src="${ctxStatic}/images/lingyu-logo.jpg" width="246px" height="103px"/>--%>
		  <%--</div>--%>
	      <div class="navbar-inner" >
			  <div class="brand" style="margin-top: 10px;margin-left: 15px;margin-right: 50px"><font class="font-style">tenskygame</font></div>
			 <%--<div class="brand">${fns:getConfig('productName')}</div>--%>
	         <div class="nav-collapse">
	           <ul id="menu" class="nav">
				 <c:set var="firstMenu" value="true"/>
				 <c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
					<c:if test="${menu.parent.id eq '1' && menu.isShow eq '1'}">
						<li class="menu ${firstMenu ? ' active' : ''}"><a class="menu" href="${ctx}/sys/menu/tree?parentId=${menu.id}" target="menuFrame" ><spring:message code="${menu.internationalKey}"/></a></li>
						<c:if test="${firstMenu}">
							<c:set var="firstMenuId" value="${menu.id}"/>
						</c:if>
						<c:set var="firstMenu" value="false"/>
					</c:if>
				 </c:forEach>
				 <%--<shiro:hasPermission name="cms:site:select">--%>
					<%--<li class="dropdown">--%>
						<%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fnc:getSite(fnc:getCurrentSiteId()).name}<b class="caret"></b></a>--%>
						<%--<ul class="dropdown-menu">--%>
						<%--<c:forEach items="${fnc:getSiteList()}" var="site"><li><a href="${ctx}/cms/site/select?id=${site.id}&flag=1">${site.name}</a></li></c:forEach>--%>
						<%--</ul>--%>
					<%--</li>--%>
				 <%--</shiro:hasPermission>--%>
	           </ul>
	           <ul class="nav pull-right">

                   <%--<li id="lanSwitch" class="dropdown">--%>
                       <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="<spring:message code='language.change'/>"><spring:message code='language'/></a>--%>
                       <%--<ul class="dropdown-menu">--%>
                           <%--<li><a href="?locale=zh_CN"><spring:message code='str1547'/></a></li>--%>
                           <%--<li><a href="?locale=en_US">English</a></li>--%>
                       <%--</ul>--%>
                 <%--</li>--%>
						<input type="text" id="defaultPid" style="display: none" value="${defaultPlatformId}"/>
					   <li>
						   <div class="btn_blue" style="width:100px;">
							   <select id="pid" name="pid" onchange="setCurrentPlatform()">
								   <option value=""><spring:message code='str627'/></option>
								<c:forEach var="item" items="${fns:getGamePlatformList()}">
									<option id="${item.pid}" value="${item.pid}">${item.name}</option>
								</c:forEach>
							   </select>
						   </div>
					   </li>
                       <li>
						 <div class="btn_private" style="width:120px;background: #f86325" onclick="selectSingleGameServer()" id="currentGameServer">
							 <c:choose><c:when test="${not empty currentGameServerName}">${currentGameServerName}</c:when><c:otherwise><spring:message code='str1248'/></c:otherwise></c:choose>
						 </div>
                       </li>
                 <%--<li id="platformSwitch" class="dropdown">--%>
                     <%--<c:set var="firstPlatform" value="true"/>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<c:forEach items="${fns:getGamePlatformList()}" var="gamePlatform">--%>
                                <%--<li><a href="#" onclick="changeGamePlatform('${gamePlatform.name}')">${gamePlatform.name}</a></li>--%>
                                <%--<c:if test="${firstPlatform}">--%>
                                    <%--<c:set var="firstPlatformName" value="${gamePlatform.name}"/>--%>
                                    <%--<c:set var="firstPlatformId" value="${gamePlatform.id}"/>--%>
                                <%--</c:if>--%>
                                <%--<c:set var="firstPlatform" value="false"/>--%>
                            <%--</c:forEach>--%>
                       <%--</ul>--%>
                     <%--<a id="current_game_platform" class="dropdown-toggle" data-toggle="dropdown" href="#" title="<spring:message code='platform.switch'/>"><spring:message code='platform'/>:${firstPlatformName}</a>--%>
                 <%--</li>--%>

			  	 <li id="themeSwitch" class="dropdown">
			       	<%--<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="<spring:message code='theme.switch'/>"><i class="icon-th-large"></i></a>--%>
				    <%--<ul class="dropdown-menu">--%>
				      <%--<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href"><spring:message code="${dict.internationalKey}"/></a></li></c:forEach>--%>
				    <%--</ul>--%>
				    <!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
			     </li>
			  	 <li class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="<spring:message code='str1249'/>"><spring:message code="Hello"/> , <shiro:principal property="loginName"/></a>
				    <ul class="dropdown-menu">
				      <li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; <spring:message code="person.info"/></a></li>
				      <li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  <spring:message code="modify.passwod"/></a></li>
				    </ul>
			  	 </li>
			  	 <li><a href="${ctx}/logout" title="<spring:message code="logout"/>">退出</a></li>
			  	 <li>&nbsp;</li>
	           </ul>
	         </div><!--/.nav-collapse -->
	      </div>
	    </div>
        <tags:message content="${message}"/>
        <form id="changeGameServerForm" action="" method="post" class="hide"></form>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left">
					<iframe id="menuFrame" name="menuFrame" src="${ctx}/sys/menu/tree?parentId=${firstMenuId}" style="overflow:visible;"
						scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;"
						scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
		    <div id="footer" class="row-fluid">
	            <%--Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - Powered By <a href="https://github.com/mokylin/cabal" target="_blank">Cabal</a> ${fns:getConfig('version')}--%>
                <%--<div class="pull-right">--%>
                    <%--<a href="?locale=zh_CN"><spring:message code='str1547'/></a> |--%>
                    <%--<a href="?locale=en_US">English</a>--%>
                    <%--<a href="?locale=ko_KR">Korea</a>--%>
                <%--</div>--%>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var leftWidth = "180"; // <spring:message code='str1250'/>
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs=getWindowSize().toString().split(",");
			$("#menuFrame, #mainFrame, #openClose").height((strs[0]<minHeight?minHeight:strs[0])-$("#header").height()-$("#footer").height()-32);
			$("#openClose").height($("#openClose").height()-5);
			if(strs[1]<minWidth){
				$("#main").css("width",minWidth-10);
				$("html,body").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
			}else{
				$("#main").css("width","auto");
				$("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
			}
			$("#right").width($("#content").width()-$("#left").width()-$("#openClose").width()-5);
		}

        function changeGamePlatform(name){
            $("#current_game_platform").html('<spring:message code='platform'/>:'+name);
        }
        
        function showContents(url) {
            $.ajax({
                type : "POST",
                url: url,
                dataType: 'text',
                //	data:params,
                before:function(){
                    loading(str1189)
                },
                success:function(data){
                    var d = top.dialog({
                        title: str1649,
                        width:'100%',
                        height:'97%',
                        content: data,
                        ok: function(){
                             top.myCallback(); 
                        	 var a = top.frames["mainFrame"].document.getElementById("serverIds").value;
                        	$(window.parent.document).find("#serverIds").val(top.frames["mainFrame"].document.getElementById("serverIds").value);
                        },
                        cancel: function(){}
                    });
                    d.showModal();
                }
            })
        }
        
        function gameGetModules(){
        	var sec = document.getElementById('command'); 
        	var content='';
        	var modules=$("#commandss").val();
        	$.ajax({
				type : "POST",
				url: "${ctx}/tools/command/gameCommandModules",
				dataType: 'text',
				
				before:function(){
					loading("<spring:message code='str1244'/>...")
				},
				success:function(data){
					 $.each($.parseJSON(data), function(idx, obj) {
						if(modules==idx){
							for (var i = 0 ; i < obj.length ; i++ ){
								var id = jQuery.trim(obj[i]);
								content += "<option value='"+id+"'>"+id+"</option>";
							} 
						}
					});
					sec.innerHTML = content; 
				}
			})
             
        }
        
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>
