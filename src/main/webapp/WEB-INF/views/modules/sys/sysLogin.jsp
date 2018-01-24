<%@page import="org.springframework.format.Printer"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} <spring:message code='str1236'/></title>
	<meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/common/typica-login.css">
	<style type="text/css">
		.control-group{border-bottom:0px;}
	</style>
    <script src="${ctxStatic}/common/backstretch.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$.backstretch([
 		      "${ctxStatic}/images/bg1.jpg",
 		      <%--"${ctxStatic}/images/bg2.jpg",--%>
 		      <%--"${ctxStatic}/images/bg3.jpg"--%>
 		  	], {duration: 0, fade: 0000});
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "<spring:message code='str1617'/>."},password: {required: "<spring:message code='str1618'/>."},
					validateCode: {remote: "<spring:message code='str1620'/>.", required: "<spring:message code='str1621'/>."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// <spring:message code='str1256'/>
		if(self.frameElement && self.frameElement.tagName=="IFRAME"){
			parent.location.reload();
		}
	</script>
</head>
<body>
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <%--<a class="brand" href="${ctx}"><img src="${ctxStatic}/images/logo.png" alt="Cabal Admin" style="height:40px;"></a>--%>
        </div>
      </div>
    </div>

    <div class="container">
		<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4><spring:message code='str1625'/></h4><p><spring:message code='str1626'/> <a href="http://browsehappy.com" target="_blank"><spring:message code='str1627'/></a> <spring:message code='str1260'/>IE<spring:message code='str1629'/> Chrome<spring:message code='str1039'/>Firefox<spring:message code='str1039'/>Safari <spring:message code='str1630'/></p></div><![endif]-->
		<%String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);%>
		<div id="messageBox" class="alert alert-error <%=error==null?"hide":""%>"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">
            <%--   <%=error==null?"":"com.mokylin.cabal.modules.sys.security.CaptchaException".equals(error)?"<spring:message code='str1622'/>, <spring:message code='str1623'/>.":"<spring:message code='str1624'/>, <spring:message code='str1623'/>." %>--%>	
            <%  if(error==null ||"com.mokylin.cabal.modules.sys.security.CaptchaException".equals(error)){     %>
				 <spring:message code='str1622'/>, <spring:message code='str1623'/>
			    <%  }else{%>
			             <spring:message code='str1624'/>, <spring:message code='str1623'/>
			     <%  }%> 
			   
			</label>
			
		</div>
        <div id="login-wraper">
            <form id="loginForm"  class="form login-form" action="${ctx}/login" method="post">
                <legend><span style="color:#08c"><spring:message code='str991'/></span></legend>
                <div class="body">
					<div class="control-group">
						<div class="controls">
							<input type="text" id="username" name="username" class="required" value="${username}" placeholder="<spring:message code='str1427'/>">
						</div>
					</div>
					
					<div class="control-group">
						<div class="controls">
							<input type="password" id="password" name="password" class="required" placeholder="<spring:message code='str1546'/>"/>
						</div>
					</div>
					<c:if test="${isValidateCodeLogin}"><div class="validateCode">
						<label for="password"><spring:message code='str1619'/></label>
						<tags:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
					</div></c:if>
                </div>
                <div class="footer">
                    <label class="checkbox inline">
                        <input type="checkbox" id="rememberMe" name="rememberMe"> <span style="color:#08c;"><spring:message code='str997'/></span>
                    </label>
                    <input class="btn btn-primary" type="submit" value="<spring:message code='str995'/> <spring:message code='str996'/>"/>
                </div>
				<%--<div id="themeSwitch" class="dropdown pull-right">--%>
					<%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
					  <%--<c:if  test="${not empty cookie.theme.value}"><spring:message code="${fns:getDictKeys(cookie.theme.value,'theme',cookie.theme.value)}"/></c:if>--%>
					  <%--<c:if  test="${empty cookie.theme.value}"><spring:message code="dict12"/></c:if>--%>
					  <%--<b class="caret"></b></a>--%>
					<%--<ul class="dropdown-menu">--%>
					  <%--<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href"><spring:message code="${dict.internationalKey}"/></a></li></c:forEach>--%>
					<%--</ul>--%>
					<%--<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->--%>
				<%--</div>--%>
            </form>
        </div>
    </div>
    <footer class="white navbar-fixed-bottom">
		<%--Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="https://github.com/cabal" target="_blank">Cabal</a> ${fns:getConfig('version')}--%>
    </footer>
  </body>
</html>
