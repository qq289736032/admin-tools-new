<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str274'/></title>
	<meta name="decorator" content="default"/>

	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/dashboard/dailyComprehensiveReport"><spring:message code='str274'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dashboard/dailyComprehensiveReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
			   <input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<div style="overflow: scroll">
	<table id="contentTable" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
		<thead>
		<tr>
		<th><spring:message code='str198'/></th>
		<th><spring:message code='str234'/></th>
<!-- 收入(元) --><th><spring:message code='str201'/></th>
		<th><spring:message code='str199'/></th>
		<th><spring:message code='str200'/></th>
		<th><spring:message code='str235'/></th>
		<th>ACCU</th><th>PCCU</th><th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th>
		<th><spring:message code='str238'/></th>
		<th><spring:message code='str275'/></th>
		<th><spring:message code='str205'/></th>
<!-- ARPU --><th>ARPU</th>
<!-- 活跃ARPU --><th><spring:message code='str220'/>ARPU</th>
		<th><spring:message code='str239'/></th><th>
		<spring:message code='str240'/></th><th><spring:message code='str276'/></th>
		<th flex="true"><spring:message code='str241'/></th>
		<th flex="true"><spring:message code='str242'/></th>
		<th flex="true"><spring:message code='str244'/><spring:message code='str275'/></th>
		<th flex="true"><spring:message code='str244'/><spring:message code='str205'/></th>
		<th><spring:message code='str244'/>ARPU</th>
		<th><spring:message code='str245'/></th>
		<th><spring:message code='str246'/></th>
		<th><spring:message code='str278'/></th>
		<th><spring:message code='str247'/></th>
		<th><spring:message code='str248'/>ARPU</th>
		<th><spring:message code='str279'/></th>
		<th><spring:message code='str249'/></th>
		<th><spring:message code='str280'/></th>
		<th><spring:message code='str250'/></th>
		<th><spring:message code='str251'/>ARPU</th></tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.log_day}</td>
				<td>${item.kaifuNum}</td>
		<!-- 收入(元) --><td><fmt:formatNumber value="${item.income/100}" pattern="#0.00"/></td>
				<td>${item.dru}</td>
				<td>${item.dau}</td>
				<td>${item.dau - item.dru}</td>
				<td><fmt:formatNumber value="${item.acu}" pattern="#0.00"/></td>
				<td>${item.pccu}</td>
				<td><fmt:formatNumber value="${item.dt/60000}" pattern="#0.00"/> </td>
				<td>${item.dpa}</td>
				<td>${item.dp_times }</td>
				<td>
				     <span style="<c:if test="${item.pay_rate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.pay_rate * 100}" pattern="#0.00"/>%</span> 
				</td>
				<td><fmt:formatNumber value="${item.arpu/100}" pattern="#0.00"/></td>
				<td><fmt:formatNumber value="${item.active_arpu/100}" pattern="#0.00"/></td>
				<td>${item.first_pay_user}</td>
		<!-- 首充金额(元) --><td><fmt:formatNumber value="${item.first_pay_value/100}" pattern="#0.00"/></td>
				<td>${item.first_pay_Num }</td>
				<td>${item.nn_pa}</td>
		<!-- 新新首充金额 --><td><fmt:formatNumber value="${item.nn_pay_value/100}" pattern="#0.00"/></td>
				<td>${item.nn_pay_times }</td>
				<td>
		<!-- 新新付费率 -->		<span style="<c:if test="${item.nn_payingrate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.nn_payingrate * 100}" pattern="#0.00"/>%</span> 
				</td>
		<!-- 新新ARPU --><td><fmt:formatNumber value="${item.nn_arpu}" pattern="#0.00"/></td>
				<td>${item.on_pa}</td>
		<!-- 老新充值金额 --><td><fmt:formatNumber value="${item.on_pay_value/100}" pattern="#0.00"/></td>
				<td>${item.on_pay_times }</td>
				<td>
					<span style="<c:if test="${item.on_payingrate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.on_payingrate * 100}" pattern="#0.00"/>%</span> 
				</td>
		<!-- 老新ARPU --><td><fmt:formatNumber value="${item.on_arpu/100}" pattern="#0.00"/></td>
				<td>${item.oo_pa}</td>
		<!-- 老老充值金额 --><td><fmt:formatNumber value="${item.oo_pay_value/100}" pattern="#0.00"/></td>
				<td>${item.oo_pay_times }</td>
				<td>	
					  <span style="<c:if test="${item.oldold_payingrate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.oldold_payingrate * 100}" pattern="#0.00"/>%</span> 
                 </td>
		<!-- 老老充值金额 --><td><fmt:formatNumber value="${item.oldold_arpu/100}" pattern="#0.00"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		<div class="pagination">${page}</div>
	</div>
	<br/>
	<div>
	<table id="weekReport" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
		<thead>
			<th><spring:message code='str281'/></th><th><spring:message code='str187'/></th><th><spring:message code='str282'/></th><th><spring:message code='str283'/></th><th><spring:message code='str284'/></th><th><spring:message code='str285'/></th><th><spring:message code='str286'/></th>
			<th><spring:message code='str287'/></th><th><spring:message code='str205'/></th><th>ARPU</th><th><spring:message code='str285'/>ARPU</th><th><spring:message code='str241'/></th><th><spring:message code='str242'/></th><th><spring:message code='str288'/></th>
			<th><spring:message code='str289'/>ARPU</th><th><spring:message code='str290'/></th><th><spring:message code='str291'/></th><th><spring:message code='str292'/></th><th><spring:message code='str293'/>ARPU</th>
		</thead>
		<tbody>
		<c:forEach items="${weekReport}" var="item">
			<tr>
				<td>${item.log_year}<spring:message code='str294'/>${fn:substring(item.log_week,"5","6")}<spring:message code='str281'/></td>
				<td>${fns:getDayRange(item.log_year, item.log_week)}</td>
				<td>${item.open_num}</td>
		<!-- 周收入 --><td><fmt:formatNumber value="${item.income/100}" pattern="#0.00"/></td>
				<td>${item.ru}</td>
				<td>${item.au}</td>
				<td>${item.ou}</td>
				<td>${item.pu}</td>
				<td><span style="<c:if test="${item.wRate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.wRate * 100}" pattern="#0.00"/>%</span> </td>
				<td><fmt:formatNumber value="${item.wArpu/100}" pattern="#0.00"/></td>
		<!-- 周活跃ARPU --><td><fmt:formatNumber value="${item.wActiveArpu/100}" pattern="#0.00"/></td>
				<td>${item.npu}</td>
		<!-- 新新充值金额 --><td><fmt:formatNumber value="${item.nincome/100}" pattern="#0.00"/></td>
				<td><span style="<c:if test="${item.nRate > 1  }">color: red;font-weight:bold</c:if>"> <fmt:formatNumber value="${item.nRate * 100}" pattern="#0.00"/>%</span> </td>
				<td><fmt:formatNumber value="${item.nArpu/100}" pattern="#0.00"/></td>
				<td>${item.opu}</td>
		<!-- 周老充值金额 --><td><fmt:formatNumber value="${item.oincome/100}" pattern="#0.00"/></td>
				<td><span style="<c:if test="${item.oRate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.oRate * 100}" pattern="#0.00"/>%</span></td>
		<!-- 周老ARPU --><td><fmt:formatNumber value="${item.oArpu/100}" pattern="#0.00"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>


<script type="text/javascript">
//	$(document).ready(function(){
//		FixTable('contentTable',5,'100%','600px');
//	});
</script>

</body>
</html>
