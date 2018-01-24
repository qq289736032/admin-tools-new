<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str307'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/sevenDaysRemainer/sevenDaysRemainerReport"><spring:message code='str607'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/sevenDaysRemainer/sevenDaysRemainerReport" method="post" class="breadcrumb form-search">
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
	<div class="panel panel-primary">
	<div class="panel-heading"><spring:message code='str608'/>7<spring:message code='str609'/> </div>
	<table id="newRegisterRemained" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str416'/></th> <th><spring:message code='str417'/></th> <th><spring:message code='str418'/></th> <th><spring:message code='str419'/></th> <th>3<spring:message code='str420'/></th>
			<th>3<spring:message code='str597'/></th> <th>4<spring:message code='str420'/></th> <th>4<spring:message code='str597'/></th> <th>5<spring:message code='str420'/></th> <th>5<spring:message code='str597'/></th>
			<th>6<spring:message code='str420'/></th> <th>6<spring:message code='str597'/></th> <th>7<spring:message code='str420'/></th> <th>7<spring:message code='str597'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${sevenDaysRemainer}" var="item">
			<tr>
				<td>${item.log_day}</td>
				<td>${item.sum_dru}</td>
				<td>${item.sum_2}</td>
				<td><span style="<c:if test="${item.sum_2_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_2_dru * 100}" pattern="#0.00"/>%</span></td>
				<td>${item.sum_3}</td>
				<td><span style="<c:if test="${item.sum_3_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_3_dru * 100}" pattern="#0.00"/>%</span></td>
				<td>${item.sum_4}</td>
				<td><span style="<c:if test="${item.sum_4_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_4_dru * 100}" pattern="#0.00"/>%</span></td>
				<td>${item.sum_5}</td>
				<td><span style="<c:if test="${item.sum_5_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_5_dru * 100}" pattern="#0.00"/>%</span></td>
				<td>${item.sum_6}</td>
				<td><span style="<c:if test="${item.sum_6_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_6_dru * 100}" pattern="#0.00"/>%</span></td>
				<td>${item.sum_7}</td>
				<td><span style="<c:if test="${item.sum_7_dru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_7_dru * 100}" pattern="#0.00"/>%</span></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>

	<div class="panel panel-primary">
	<div class="panel-heading"><spring:message code='str610'/>7<spring:message code='str609'/></div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str416'/></th> <th><spring:message code='str611'/></th> <th><spring:message code='str612'/></th> <th>3<spring:message code='str609'/></th> <th>4<spring:message code='str609'/></th>
			<th>5<spring:message code='str609'/></th> <th>6<spring:message code='str609'/></th> <th>7<spring:message code='str609'/></th> <th><spring:message code='str613'/></th> <th><spring:message code='str612'/></th>
			<th>3<spring:message code='str609'/></th> <th>4<spring:message code='str609'/></th> <th>5<spring:message code='str609'/></th> <th>6<spring:message code='str609'/></th> <th>7<spring:message code='str609'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${sevenDaysRemainer}" var="item">
			<tr>
				<td>${item.log_day}</td>
				<td>${item.sum_dru}</td>
				<td><span style="<c:if test="${item.sum_2_dru_new   * 100 >100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_2_dru_new * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_3_dru_new   * 100>100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_3_dru_new * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_4_dru_new   * 100>100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_4_dru_new * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_5_dru_new   * 100>100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_5_dru_new * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_6_dru_new   * 100>100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_6_dru_new * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_7_dru_new   * 100>100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_7_dru_new * 100}" pattern="#0.00"/>%</span></td>
				<td>${item.sum_dru_old}</td>
				<td><span style="<c:if test="${item.sum_2_dru_old   * 100 >100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_2_dru_old * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_3_dru_old   * 100>100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_3_dru_old * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_4_dru_old   * 100>100  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_4_dru_old * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_5_dru_old  * 100>100   }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_5_dru_old * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_6_dru_old  * 100>100   }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_6_dru_old * 100}" pattern="#0.00"/>%</span></td>
				<td><span style="<c:if test="${item.sum_7_dru_old   * 100>100   }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${item.sum_7_dru_old * 100}" pattern="#0.00"/>%</span></td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>

	<div class="panel panel-primary">
			<div class="panel-heading"><spring:message code='str614'/> <spring:message code='str615'/></div>
		<div style="height: 300px;line-height: 300px">
			<div  class="highchart-daysActive" style="height: 300px"></div>
			<table class="highchart table"
				   data-graph-container=".highchart-daysActive" data-graph-type="line" data-graph-xaxis-reversed="1"
				   data-graph-yaxis-1-title-text = '<spring:message code='str462'/>' data-graph-zoom-type="xy" style="display: none">
				<caption><spring:message code='str614'/> <spring:message code='str615'/></caption>
				<thead>
				<tr>
					<th><spring:message code='str198'/></th>
					<th ><spring:message code='str616'/></th>
					<th ><spring:message code='str617'/></th>
					<th ><spring:message code='str618'/></th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${sevenDaysRemainer}" var="item">
						<tr>
							<td>${item.log_day}</td>
							<td>${item.sum_dru}</td>
							<td>${item.sum_dru_new}</td>
							<td>${item.sum_dru_old}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
		});
	</script>

</body>
</html>
