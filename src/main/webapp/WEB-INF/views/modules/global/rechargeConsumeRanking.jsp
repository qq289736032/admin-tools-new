<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str559'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
        .SortDescCss{background-image:url(${ctxStatic}/images/Asc.gif);background-repeat:no-repeat;background-position:right center;}
        .SortAscCss{background-image:url(${ctxStatic}/images/Asc.gif);background-repeat:no-repeat;background-position:right center;}
    </style>
    <script src="${ctxStatic}/common/TableSorterV2.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/rechargeConsume/rechargeConsumeRanking"><spring:message code='str559'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/rechargeConsume/rechargeConsumeRanking" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20" value="${paramMap.createDateStart}" id="startDatePicker" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
		-&nbsp;<input type="text" name="createDateEnd"   class="input_search" size="10" readonly="readonly" value="${paramMap.createDateEnd}"   id="endDatePicker" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
		<label><spring:message code='str55'/></label>
	    <input id="serverIds" name="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
		<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
		<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
		<br>
		<input type="hidden" id="hidden1" name="hidden1" value="${paramMap.jeValue}">
		<input type="radio" id="jeValue1" name="jeValue" value="0">0+
		<input type="radio" id="jeValue2" name="jeValue" value="500" checked="checked">500+
		<input type="radio" id="jeValue3" name="jeValue" value="1000">1000+
		<input type="radio" id="jeValue4" name="jeValue" value="2000">2000+
		<input type="radio" id="jeValue5" name="jeValue" value="5000">5000+
		<input type="radio" id="jeValue6" name="jeValue" value="10000">10000+
		<input type="radio" id="jeValue7" name="jeValue" value="20000">20000+
		<input type="radio" id="jeValue8" name="jeValue" value="50000">50000+
		<input type="radio" id="jeValue9" name="jeValue" value="100000">100000+
	</form>
	<tags:message content="${message}"/>
	
	<div class="container-fluid">
	<div class="row-fluid">
	<div class="span6 panel panel-primary">
		<div class="panel-heading"><spring:message code='str560'/></div>
		<table id="contentTable1" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str187'/></th><th><spring:message code='str56'/></th><th><spring:message code='str14'/></th><th><spring:message code='str561'/></th><th><spring:message code='str238'/></th><th><spring:message code='str253'/></th><th>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${tableList1}" var="item">
				<tr>
					<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.area_id}</td>
					<td>${fns:getGameServer(item.area_id).name}</td>
					<td>${item.pa}</td>
					<td>${item.income}</td>
					<td><fmt:formatNumber value="${item.arpu}" pattern="#0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="span6 panel panel-primary">
		<div class="panel-heading"><spring:message code='str562'/></div>
		<table id="contentTable2" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str187'/></th><th><spring:message code='str56'/></th><th><spring:message code='str238'/></th><th><spring:message code='str253'/></th><th>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${tableList2}" var="item">
				<tr>
					<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.pa}</td>
					<td>${item.income}</td>
					<td><fmt:formatNumber value="${item.arpu}" pattern="#0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
	</div>
	
	<div class="container-fluid">
	<div class="row-fluid">
	<div class="span6 panel panel-primary">
		<div class="panel-heading"><spring:message code='str563'/></div>
		<table id="contentTable3" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str187'/></th><th><spring:message code='str56'/></th><th><spring:message code='str14'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/>(<spring:message code='str93'/>)</th><th>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${tableList3}" var="item">
				<tr>
					<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.area_id}</td>
					<td>${item.num}</td>
					<td>${item.s_amount}</td>
					<td><fmt:formatNumber value="${item.arpu}" pattern="#0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="span6 panel panel-primary">
		<div class="panel-heading"><spring:message code='str564'/></div>
		<table id="contentTable4" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str187'/></th><th><spring:message code='str56'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/>(<spring:message code='str93'/>)</th><th>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${tableList4}" var="item">
				<tr>
					<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.num}</td>
					<td>${item.s_amount}</td>
					<td><fmt:formatNumber value="${item.arpu}" pattern="#0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
	</div>
	
	<div class="container-fluid">
	<div class="row-fluid">
	<div class="span6 panel panel-primary">
		<div class="panel-heading"><spring:message code='str563'/>(<spring:message code='str565'/>)</div>
		<table id="contentTable5" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str187'/></th><th><spring:message code='str56'/></th><th><spring:message code='str14'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/>(<spring:message code='str565'/>)</th><th>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${tableList5}" var="item">
				<tr>
					<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.area_id}</td>
					<td>${item.num}</td>
					<td>${item.s_amount}</td>
					<td><fmt:formatNumber value="${item.arpu}" pattern="#0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="span6 panel panel-primary">
		<div class="panel-heading"><spring:message code='str564'/>(<spring:message code='str565'/>)</div>
		<table id="contentTable6" class="datatable table table-striped table-bordered table-condensed" style="overflow: scroll">
			<thead>
			<tr><th><spring:message code='str187'/></th><th><spring:message code='str56'/></th><th><spring:message code='str191'/></th><th><spring:message code='str254'/>(<spring:message code='str565'/>)</th><th>ARPU</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${tableList6}" var="item">
				<tr>
					<td>${fn:substring(paramMap.createDateStart,"5","10")}~${fn:substring(paramMap.createDateEnd,"5","10")}</td>
					<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
					<td>${item.num}</td>
					<td>${item.s_amount}</td>
					<td><fmt:formatNumber value="${item.arpu}" pattern="#0.00"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
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
		new TableSorter("contentTable1");
		new TableSorter("contentTable2");
		new TableSorter("contentTable3");
		new TableSorter("contentTable4");
		new TableSorter("contentTable5");
		new TableSorter("contentTable6");
		var radioVal = $("#hidden1").val();
		if (radioVal==500) {
			$("input[id=jeValue][value=500]").attr("checked",true);
		} else if(radioVal==1000){
			$("input[id=jeValue][value=1000]").attr("checked",true);
		} else if(radioVal==2000){
			$("input[id=jeValue][value=2000]").attr("checked",true);
		} else if(radioVal==5000){
			$("input[id=jeValue][value=5000]").attr("checked",true);
		} else if(radioVal==10000){
			$("input[id=jeValue][value=10000]").attr("checked",true);
		}  else if(radioVal==20000){
			$("input[id=jeValue][value=20000]").attr("checked",true);
		} else if(radioVal==50000){
			$("input[id=jeValue][value=50000]").attr("checked",true);
		} else if(radioVal==100000){
			$("input[id=jeValue][value=100000]").attr("checked",true);
		} else if(radioVal==0){
			$("input[id=jeValue][value=0]").attr("checked",true);
		}
	
	});
</script>

</body>
</html>
