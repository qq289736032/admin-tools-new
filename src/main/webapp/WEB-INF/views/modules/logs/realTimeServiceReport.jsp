<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str961'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<script type="text/javascript">
	function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/log/realTimeService/realTimeServiceReport");
            $("#searchForm").submit();
            return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/log/realTimeService/realTimeServiceReport"><spring:message code='str961'/></a></li>
		<li><a href="${ctx}/log/realTimeService/realTime"><spring:message code='str962'/></a></li>
	    <li ><a href="${ctx}/log/realTimeService/realFenTime"><spring:message code='str963'/></a></li>
	    
	</ul>
	<form id="searchForm" action="${ctx}/log/realTimeService/realTimeServiceReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="startDate" id="startDate" maxlength="50"  value="${paramMap.startDate}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="<spring:message code='str5'/>" onclick="doExport('${ctx}/log/realTimeService/export')"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
		<th><spring:message code='str964'/></th> 
		<th><spring:message code='str56'/></th> 
		<th><spring:message code='str14'/></th> 
		<th><spring:message code='str217'/></th> 
		<th><spring:message code='str965'/></th>
			<th><spring:message code='str966'/></th> 
			<th><spring:message code='str238'/></th>
			<th><spring:message code='str275'/></th> 
			<th><spring:message code='str253'/></th> 
			<th><spring:message code='str205'/></th> 
			<th>ARPU</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${fns:parseYYYYMMDDHHMM(item.log_minute)}</td>
				<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
				<td>${fns:getGameServer(item.area_id).name}</td>
				<td>${item.ru}</td>
				<td>${item.au}</td>
				<td>${item.num}</td>
				<td>${item.pa}</td>
				<td>${item.pay_times}</td>
				<td><fmt:formatNumber value="${item.income/100}" pattern="#0.00"/></td>
				<td><span style="<c:if test="${item.pay_rate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.pay_rate * 100}" pattern="#0.00"/>%</span></td>
				<td><fmt:formatNumber value="${item.arpu/100}" pattern="#0.00"/></td>
			</tr>
		</c:forEach>
		</tbody>
		<%-- <tr>
		    <td><spring:message code='str193'/></td>
		      <td>${paramMap.pid}</td>
		      <td>${fns:getGameServer(paramMap.currentServerId).name}</td>
			<c:if test="${not  empty  newRegister }">
				<td>${fns:getGamePlatformNameById(newRegister.pid,newRegister.pid)}</td>
				<td>${fns:getGameServer(newRegister.server_id).name}</td>
				<td>${newRegister.num}</td>
			</c:if>
			<c:if test="${ empty  newRegister }">
				<td></td>
				<!-- <td></td>
				<td></td> -->
			</c:if>
			<c:if test="${not  empty  login }">
				<td>${login.num}</td>
			</c:if>
			<c:if test="${ empty  login }">
				<td></td>
			</c:if>

			<c:if test="${not  empty  online }">
				<td>${online.count}</td>
			</c:if>
			<c:if test="${ empty  online }">
				<td></td>
			</c:if>

			<c:if test="${not  empty  rechager }">
				<td>${rechager.ren}  </td>
				<td>${rechager.cishu}</td>
				<td>${rechager.amount}</td>
				<c:if test="${not  empty  login && login.num >0  }">
				<td><fmt:formatNumber value="${rechager.ren/login.num}"
						type="percent" /></td>
				</c:if>
				<c:if test="${  empty  login }">
				<td></td>
				</c:if>
					<c:if test="${  rechager.ren  >0  }">
				<td><fmt:formatNumber value="${rechager.amount/rechager.ren}"
						pattern="0.00" /></td></c:if>
											<c:if test="${  rechager.ren  == 0  }">
											<td></td>
						</c:if>
						
			</c:if>
			<c:if test="${ empty  rechager }">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>

		</tr> --%>
	</table>
	<div class="pagination">${page}</div>

	
	
	<script type="text/javascript">
		$(document).ready(function(){
// 			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
