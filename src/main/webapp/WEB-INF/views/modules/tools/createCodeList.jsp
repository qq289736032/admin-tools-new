<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1265'/></title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript">
//       $(function(){
//           $('.tooltip-demo').tooltip({
//               selector: "a[data-toggle=tooltip]"
//           })
//       });

		function viewCreateCodeDetail(giftId,id){
			top.$.jBox.open("iframe:"+BASE+"/tools/gift/viewCreateCodeDetail?giftId="+giftId+"&id="+id, "<spring:message code='str1266'/>",1000,$(top.document).height()-240,{
				buttons:{"<spring:message code='str876'/>":true}, bottomText:"<spring:message code='str1266'/>", loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".jbox-content-loading", top.document).css("height","auto");
					$(".jbox-content", top.document).css("height","auto");
					$("#jbox-state-state0" ,top.document).children().eq(0).css("height","auto");
				}
			});
		}

    </script>
    <style type="text/css">
        table{table-layout: fixed;}
    </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/gift/createCodeList"><spring:message code='str1250'/></a></li>
		<li><a href="${ctx}/tools/gift/createCodeForm"><spring:message code='str1251'/></a></li>
	</ul>
    <form:form id="searchForm" action="${ctx}/tools/gift/createCodeList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label><spring:message code='str1267'/></label>
        <input type="text" name="name"  value="${paramMap.name }"/>
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
    </form:form>
	<tags:message content="${message}"/>
    <form id="tableForm" action="">
	<table id="contentTable" class="table table-striped table-bordered table-condensed tooltip-demo">
		<tr><th><spring:message code='str1252'/></th><th><spring:message code='str1254'/></th><th><spring:message code='str729'/></th><th><spring:message code='str56'/></th><th><spring:message code='str1268'/></th><th><spring:message code='str1269'/></th><th><spring:message code='str1270'/></th><th><spring:message code='str827'/></th><th><spring:message code='str149'/></th></tr>
		<c:forEach items="${page.list}" var="gift">
			<tr>
                <td>${gift.gift_name}</td>
                <td>${gift.count}</td>
                <td>${gift.remarks}</td>
				<td>${fns:getGamePlatformNameById(gift.pid,gift.pid)}</td>
                <td><fmt:formatDate value="${gift.effective_time}" type="both"/></td>
				<td><fmt:formatDate value="${gift.create_time}" type="both"/></td>
				<td><fmt:formatDate value="${gift.effective_end_time}" type="both"/></td>
				<td>${gift.create_name}</td>
				<td><c:if test="${ gift.del_flag == 1 }"><spring:message code='str1271'/></c:if>
					<c:if test="${ gift.del_flag == 0 }">
						<a href="${ctx}/tools/gift/deleteCreateCode?id=${gift.id}" onclick="return confirmx('<spring:message code='str1272'/>', this.href)"><spring:message code='str1273'/></a>
					</c:if>
				| <a href="${ctx}/tools/gift/exportCreateCode?giftId=${gift.gift_id}&id=${gift.id}"><spring:message code='str5'/></a>
				| <a href="javascript:" onclick="viewCreateCodeDetail('${gift.gift_id}','${gift.id}');"><spring:message code='str1274'/></a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
    </form>
    
</body>
</html>
