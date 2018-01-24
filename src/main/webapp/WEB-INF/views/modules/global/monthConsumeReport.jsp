<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str343'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/rechargeConsume/monthConsumeReport"><spring:message code='str343'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/rechargeConsume/monthConsumeReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		
		<label><spring:message code='str56'/> <spring:message code='str4'/></label>
		<!-- <spring:message code='str344'/> -->
		<select id="pids" name="pids" multiple="multiple">
			<c:forEach items="${fns:getGamePlatformList()}" var="item">
				<c:choose>
					<c:when test="${fn:length(selectedPids)==0}">
						<option value="${item.pid}" >${item.name}</option>
					</c:when>
					<c:otherwise>
					<option value="${item.pid}" 
						<c:forEach items="${selectedPids}" var="pid">
							<c:if test="${pid==item.pid}">
								selected="selected"
							</c:if>
						</c:forEach>
					>${item.name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>

	<div class="container-fluid">
<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str363'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable1" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str234'/></th><th><spring:message code='str201'/></th><th><spring:message code='str217'/></th><th><spring:message code='str200'/></th><th><spring:message code='str348'/></th><th><spring:message code='str349'/></th><th><spring:message code='str262'/></th><th><spring:message code='str254'/></th><th><spring:message code='str263'/></th><th><spring:message code='str264'/>ARPU</th><th><spring:message code='str364'/>ARPU</th><th><spring:message code='str265'/></th><th><spring:message code='str266'/></th></tr>
					<c:forEach items="${monthList}" var="item">
						<tr>
							<td>${item.log_month}</td>
							<td>${item.kaifuNum}</td>
							<td>${item.income}</td>
							<td>${item.dru}</td>
							<td>${item.dau}</td>
							<td>${item.ou}</td>
							<td>${item.num}</td>
							<td>${item.times}</td>
							<td>${item.amount}</td>
							<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.arpu}" pattern="0.00"/></td>
							<td><fmt:formatNumber value="${item.active_arpu}" pattern="0.00"/></td>
							<td>${item.first_num}</td>
							<td>${item.first_amount}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str350'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;ovoverflow:scrolld="dailyTable2" class="table table-striped table-bordered table-condensed">
					<table id="dailyTable1" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str351'/></th><th><spring:message code='str352'/></th><th><spring:message code='str353'/></th><th><spring:message code='str354'/></th><th><spring:message code='str355'/></th><th><spring:message code='str356'/>ARPU</th><th><spring:message code='str357'/></th><th><spring:message code='str358'/></th><th><spring:message code='str359'/></th><th><spring:message code='str365'/></th><th><spring:message code='str366'/>ARPU</th></tr>
					<c:forEach items="${monthList}" var="item">
						<tr>
							<td>${item.log_month}</td>
							<td>${item.amount}</td>
							
							<td>${item.month_new_dau}</td>
							<td>${item.month_new_num}</td>
							<td>${item.month_new_amount}</td>
							<td <c:if test="${item.month_new_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.month_new_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.month_new_arpu}" pattern="0.00"/></td>
							
							<td>${item.month_old_dau}</td>
							<td>${item.month_old_num}</td>
							<td>${item.month_old_amount}</td>
							<td <c:if test="${item.month_old_pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.month_old_pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.month_old_arpu}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str367'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th><spring:message code='str198'/></th><th><spring:message code='str56'/></th><th><spring:message code='str223'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/></th><th><spring:message code='str205'/></th><th>APRU</th><th><spring:message code='str220'/>APRU</th></tr>
					<c:forEach items="${monthPlatFormList}" var="item">
						<tr>
							<td>${item.log_month}</td>
							<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
							<td>${item.dau}</td>
							<td>${item.num}</td>
							<td>${item.amount}</td>
							<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate * 100}" pattern="0.00"/>%</td>
							<td><fmt:formatNumber value="${item.arpu}" pattern="0.00"/></td>
							<td><fmt:formatNumber value="${item.active_arpu}" pattern="0.00"/></td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	
	function selectMonth() {  
	       WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false });  
	    }  
</script>

</body>
</html>
