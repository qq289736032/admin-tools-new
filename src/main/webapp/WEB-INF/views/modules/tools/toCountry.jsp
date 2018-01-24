<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1178'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript">
		function deleteCrossAreaList(url) {
		    var oldUrl = $("form:first").attr("action");
		    $("form:first").attr("action", url);
		    $("form:first").submit();
		    $("form:first").attr("action", oldUrl);
		}
	</script>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
		td.ui-dialog-body {
            height: auto;
     }
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/tools/crossArea/crossAreaManage"><spring:message code='str1179'/></a></li>
		<li class="active"><a><spring:message code='str1181'/></a></li>
	</ul>
	<div style="display: none" id="countryContent"  >
	</div>
	<tags:message content="${message}"/>
	<input id='countryIds' name='countryIds' value=''  type="hidden" readonly='readonly'>
	<input id="btnAddCountry" class="btn btn-primary" type="button" value="<spring:message code='str1521'/>" onclick="showContent('${ctx}/tools/crossArea/openCountry?crossId=${crossId }')"/><br>
	    <table class="table table-striped table-bordered table-condensed" id="countryListTable">
				<tr><th><spring:message code='str1181'/>ID</th><th><spring:message code='str1237'/></th><th><spring:message code='str1238'/></th><th><spring:message code='str101'/></th><th><spring:message code='str149'/></th></tr>
		        <c:forEach items="${countryList}" var="country" varStatus="status">
		     		 	 <c:if test="${country.crossId == crossId}">  
				               <tr  id="${country.id }">
					                 <td>${country.id }</td>
					                 <td>${country.name }</td>
					                 <td>${country.kingName }</td>
					                 <td><fmt:formatDate value="${country.addTime }" pattern="yyyy-MM-dd HH:"/></td>
					                 <td><a onclick="removeCountry('${ctx}/tools/crossArea/removeCountry?countryId=${country.id }' ,'${country.id }');"><spring:message code='str22'/></a></td> 
                            </tr>
					       </c:if>    
			  </c:forEach>
		  </table>
<script>

	function showContent(url) {
	    $.ajax({
	        type : "POST",
	        url: url,
	        dataType: 'text',
	        //	data:params,
	        before:function(){
	            loading("<spring:message code='str1189'/>...")
	        },
	        success:function(data){
	            var d = top.dialog({
	                title: '<spring:message code='str1190'/>',
	                width:'100%',
	                height:'97%',
	                content: data,
	                ok: function(){
	                top.countryCallback();
	                },
	                cancel: function(){}
	            });

	            d.showModal();
	        }
	    })
	}
	
	
</script>
</body>
</html>
