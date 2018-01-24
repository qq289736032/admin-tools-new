<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str196'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/dashboard/baseReport"><spring:message code='str196'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dashboard/baseReport" method="post" class="breadcrumb form-search">
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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str197'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
					<table id="dailyTable" class="table table-striped table-bordered table-condensed">
						<tr><th><spring:message code='str198'/></th><th><spring:message code='str1675'/></th><th><spring:message code='str199'/></th><th><spring:message code='str200'/></th><th><spring:message code='str201'/></th><th><spring:message code='str202'/></th><th><spring:message code='str203'/></th><th><spring:message code='str204'/></th><th><spring:message code='str205'/>(%)</th><th>ARPU</th></tr>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_day}</td>
								<td>${item.dru}</td>
								<td>${item.druc}</td>
								<td>${item.dau}</td>
								<!-- 收入(元) --><td><fmt:formatNumber value="${item.income/100}" pattern="#0.00" /></td>
								<td>${item.dpa}</td>
								<td><fmt:formatNumber value="${item.acu}" pattern="#0.00" /></td>
								<td>${item.pccu}</td>
								<td <c:if test="${item.pay_rate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.pay_rate *100}"  pattern="#0.00" />%</td>
								<td><fmt:formatNumber value="${item.arpu/100}" pattern="#0.00" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str201'/></div>
				<div style="height: 300px;line-height: 300px">
				<div class="highchart-income" style="height: 300px"></div>
				<table class="highchart table"
					   data-graph-container=".highchart-income" data-graph-type="column" data-graph-xaxis-reversed="1"
					   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>' data-graph-yaxis-2-title-text = '<spring:message code='str207'/>'
					   data-graph-zoom-type="xy" data-graph-yaxis-2-opposite="1" style="display: none">
					<caption><spring:message code='str201'/></caption>
					<thead>
					<tr>
						<th><spring:message code='str198'/></th>
						<th data-graph-type="column"><spring:message code='str208'/></th>
						<th data-graph-type="line"><spring:message code='str209'/></th>
						<th data-graph-type="line" data-graph-yaxis="1"><spring:message code='str210'/></th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_day}</td>
								<td><fmt:formatNumber value="${item.income/100}" pattern="#0.00" /></td>
								<td>${item.dpa}</td>
								<td><fmt:formatNumber value="${item.nn_pay_value}" pattern="#0.00" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>

		</div>

		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str211'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
					<table id="overallTable" class="table table-striped table-bordered table-condensed">
						<tr><th><spring:message code='str198'/></th><th><spring:message code='str212'/></th><th><spring:message code='str213'/></th><th><spring:message code='str214'/></th><th><spring:message code='str215'/></th></tr>
						<c:forEach items="${hisTotal}" var="item">
							<tr>
								<td>${item.log_day}</td>
								<td>${item.total_server_count}</td>
								<td><fmt:formatNumber value="${item.total_earning/100}" pattern="#0.00" /></td>
								<td>${item.total_registration}</td>
								<td>${item.total_recharge_user_count}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str216'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
					<table id="totalData" class="table table-striped table-bordered table-condensed">
						<tr>
							<th>
							<spring:message code='str198'/></th>
							<th><spring:message code='str201'/></th>
							<th><spring:message code='str217'/></th>
							<th><spring:message code='str218'/></th>
							<th><spring:message code='str219'/></th>
							<th>ACCU</th>
							<th>PCCU</th>
							<th><spring:message code='str202'/></th>
							<th><spring:message code='str205'/>(%)</th>
							<th>ARPU</th>
							<th><spring:message code='str220'/>ARPU</th>
						</tr>
						<c:forEach items="${currentMonthTotal }" var="item" varStatus="statu">
						    <tr>
                                <td>${item.log_month}</td>
						        <td><fmt:formatNumber value="${item.income/100}" pattern="#0.00" /></td>
								<td>${item.dru}</td>
								<td>${item.mau}</td>
								<td>${item.month_remainer}</td>
								<td><fmt:formatNumber value="${item.acu}" pattern="#0.00" /></td>
								<td>${item.pccu}</td>
								<td>${item.mpu}</td>
								<td>
									<c:if test="${item.mau >0 }"><fmt:formatNumber value="${item.mpu/item.mau *100}"  pattern="#0.00" /></c:if>
									<c:if test="${item.mau ==0 }">0</c:if>% 
								</td>
								<td><c:if test="${item.mpu >0 }"><fmt:formatNumber value="${(item.income/item.mpu)/100}"  pattern="#0.00" /></c:if>
									<c:if test="${item.mpu ==0 }">0</c:if>
								</td>
								<td>
									<c:if test="${item.mau >0 }"><fmt:formatNumber value="${(item.income/item.mau)/100}"  pattern="#0.00" /></c:if>
									<c:if test="${item.mau ==0 }">0</c:if>
								</td>
						   </tr>
						</c:forEach>
						<c:forEach items="${monthlyTotal}" var="item" varStatus="statu">
							 <tr>
								<td>${item.log_month}</td>
								<td><fmt:formatNumber value="${item.income}" pattern="#0.00" /></td>
								<td>${item.ru}</td>
								<td>${item.au}</td>
								<td>${item.month_remainer}</td>
								<td><fmt:formatNumber value="${item.acu}" pattern="#0.00" /></td>
								<td>${item.pccu}</td>
								<td>${item.pa}</td>
							    <td <c:if test="${item.mRate > 1 }">style="color: red;font-weight:bold" </c:if>><fmt:formatNumber value="${item.mRate *100}"  pattern="#0.00" />%</td>
								<td><fmt:formatNumber value="${item.mArpu/100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${item.active_arpu/100}" pattern="#0.00" /></td>
							</tr> 
						</c:forEach>
					</table>
				</div>
			</div>

		</div>


		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str221'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-newactive" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-newactive" data-graph-type="spline" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>'  style="display: none">
						<caption><spring:message code='str221'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th><spring:message code='str222'/></th>
							<th><spring:message code='str223'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_day}</td>
								<td>${item.dru}</td>
								<td>${item.dau}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str224'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-accupccu" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-accupccu" data-graph-type="spline" data-graph-xaxis-reversed="1"
						   data-graph-yaxis-1-title-text = '<spring:message code='str206'/>'  style="display: none">
						<caption><spring:message code='str224'/></caption>
						<thead>
						<tr>
							<th><spring:message code='str198'/></th>
							<th>ACCU</th>
							<th>PCCU</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.log_day}</td>
								<td>${item.acu}</td>
							    <td>${item.pccu}</td> 
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str225'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-income-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-income-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str225'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str226'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${mapList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : 
									<c:if test="${totalMap.income >0 }"><fmt:formatNumber type="percent" value="${item.income/totalMap.income}"/></c:if>
					    			<c:if test="${totalMap.income ==0 }">0</c:if>">
					    			<c:if test="${totalMap.income >0 }"><fmt:formatNumber type="percent" value="${item.income/totalMap.income}"/></c:if>
					    			<c:if test="${totalMap.income ==0 }">0</c:if>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str227'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-active-pie" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-active-pie" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str227'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str226'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${mapList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : 
									<c:if test="${totalMap.au >0 }"><fmt:formatNumber type="percent" value="${item.au/totalMap.au}"/></c:if>
					    			<c:if test="${totalMap.au ==0 }">0</c:if>">
					    			<c:if test="${totalMap.au >0 }"><fmt:formatNumber type="percent" value="${item.au/totalMap.au}"/></c:if>
					    			<c:if test="${totalMap.au ==0 }">0</c:if>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>

		<div class="row-fluid">
			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str228'/></div>
				<div style="height: 300px;line-height: 300px">
					<div class="highchart-new" style="height: 300px"></div>
					<table class="highchart table"
						   data-graph-container=".highchart-new" data-graph-type="pie" data-graph-pie-show-in-legend="1" data-graph-datalabels-enabled="1" style="display: none">
						<caption><spring:message code='str228'/></caption>
						<thead>
						<tr>
							<th data-graph-type="pie"><spring:message code='str56'/></th>
							<th data-graph-type="pie"><spring:message code='str226'/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${mapList}" var="item">
							<tr>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td data-graph-name="${fns:getGamePlatformNameById(item.pid,item.pid)} : 
									<c:if test="${totalMap.ru >0 }"><fmt:formatNumber type="percent" value="${item.ru/totalMap.ru}"/></c:if>
					    			<c:if test="${totalMap.ru ==0 }">0</c:if>">
					    			<c:if test="${totalMap.ru >0 }"><fmt:formatNumber type="percent" value="${item.ru/totalMap.ru}"/></c:if>
					    			<c:if test="${totalMap.ru ==0 }">0</c:if>
					    		</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="span6 panel panel-primary">
				<div class="panel-heading"><spring:message code='str229'/></div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
					<table id="platformTable" class="table table-striped table-bordered table-condensed">
						<tr>
							<th><spring:message code='str187'/></th>
							<th><spring:message code='str230'/></th>
							<th><spring:message code='str231'/></th>
							<th><spring:message code='str226'/></th>
							<th><spring:message code='str232'/></th>
							<th><spring:message code='str226'/></th>
							<th><spring:message code='str233'/></th>
							<th><spring:message code='str226'/></th>
						</tr>
						<c:forEach items="${mapList}" var="item">
							<tr>
								<td>${paramMap.createDateStart} ~ ${paramMap.createDateEnd}</td>
								<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
								<td><fmt:formatNumber value="${item.income/100}" pattern="#0.00" /></td>
								<td>
									<c:if test="${totalMap.income >0 }">
									       <c:if test="${item.income/totalMap.income > 1  }"><span style="color: red;font-weight:bold"><fmt:formatNumber type="percent" value="${item.income/totalMap.income}"/></span></c:if>
									       <c:if test="${item.income/totalMap.income <= 1  }"><fmt:formatNumber type="percent" value="${item.income/totalMap.income}"/></c:if>
					    			</c:if>
					    			<c:if test="${totalMap.income ==0 }">0</c:if>
								</td>
								<td>${item.au}</td>
								<td>
									<c:if test="${totalMap.au >0 }">
									       <c:if test="${item.au/totalMap.au > 1  }"><span style="color: red;font-weight:bold"><fmt:formatNumber type="percent" value="${item.au/totalMap.au}"/></span></c:if>
									       <c:if test="${item.au/totalMap.au <= 1  }"><fmt:formatNumber type="percent" value="${item.au/totalMap.au}"/></c:if>
									</c:if>
					    			<c:if test="${totalMap.au ==0 }">0</c:if>
					    		</td>
								<td>${item.ru}</td>
								<td>
									<c:if test="${totalMap.ru >0 }">
									   <span style="<c:if test="${item.ru/totalMap.ru >1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber type="percent" value="${item.ru/totalMap.ru}"/></span> 
									</c:if>
					    			<c:if test="${totalMap.ru ==0 }">0</c:if>
				    			</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</div>
	</div>



	<%--<table id="contentTable" class="table table-striped table-bordered table-condensed">--%>
		<%--<tr><th><spring:message code='str198'/></th><th><spring:message code='str234'/></th><th><spring:message code='str201'/></th><th><spring:message code='str199'/></th><th><spring:message code='str200'/></th><th><spring:message code='str235'/></th><th>ACCU</th><th>PCCU</th><th>DT<spring:message code='str236'/>(<spring:message code='str237'/>)</th><th><spring:message code='str238'/></th>--%>
			<%--<th><spring:message code='str205'/></th><th>ARPU</th><th><spring:message code='str220'/>ARPU</th><th><spring:message code='str239'/></th><th><spring:message code='str240'/></th><th><spring:message code='str241'/></th><th><spring:message code='str242'/></th><th><spring:message code='str244'/><spring:message code='str205'/></th><th><spring:message code='str244'/>ARPU</th>--%>
			<%--<th><spring:message code='str245'/></th><spring:message code='str246'/><th><spring:message code='str247'/></th><th><spring:message code='str248'/>ARPU</th><th><spring:message code='str245'/></th><th><spring:message code='str249'/></th><th><spring:message code='str250'/></th><th><spring:message code='str251'/>ARPU</th></tr>--%>
		<%--<c:forEach items="${page.list}" var="item">--%>
			<%--<tr>--%>

			<%--</tr>--%>
		<%--</c:forEach>--%>
	<%--</table>--%>
	<%--<div class="pagination">${page}</div>--%>

<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
	});
</script>

</body>
</html>
