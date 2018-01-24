<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str423'/></title>
	<meta name="decorator" content="default"/>

	<script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
	<style type="text/css">
		.progress {
			margin-bottom: 0px;
			background-color: #9da1a1;
		}
		.progress-striped .bar {
			background-color: #149bdf;
			background-image: -webkit-gradient(linear, 0 100%, 100% 0, color-stop(0.25, rgba(255, 255, 255, 0.15)), color-stop(0.25, transparent), color-stop(0.5, transparent), color-stop(0.5, rgba(255, 255, 255, 0.15)), color-stop(0.75, rgba(255, 255, 255, 0.15)), color-stop(0.75, transparent), to(transparent));
			background-image: -webkit-linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
			background-image: -moz-linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
			background-image: -o-linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
			background-image: linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent);
			-webkit-background-size: 40px 40px;
			-moz-background-size: 40px 40px;
			-o-background-size: 40px 40px;
			background-size: 40px 40px;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/global/dashboard/monthSalesReport"><spring:message code='str423'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/global/dashboard/monthSalesReport" method="post" class="breadcrumb form-search">
		<label><spring:message code='str185'/></label>
	      <input name="month" style="width: 216px" readonly="readonly" value="${paramMap.month}" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	 <table id="monthly-sales" class="datatable table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><c:if test="${empty revenue.month }"><spring:message code='str369'/></c:if><c:if test="${not empty revenue.month }">${month }</c:if></th>
		    <th><spring:message code='str217'/></th><th><spring:message code='str235'/></th><th><spring:message code='str387'/></th><th><spring:message code='str205'/>(%)</th><th>ARPU</th><th><spring:message code='str201'/></th>
		</thead>
	 	<tbody>
			<tr>
				<td><spring:message code='str424'/></td><!-- 当月指标 -->
				<td id="cur_newUser"><c:if test="${not empty revenue.newUser}">${revenue.newUser}</c:if><c:if test="${empty revenue.newUser }">0</c:if></td>
				<td id="cur_oldUser"><c:if test="${not empty revenue.oldUser}">${revenue.oldUser}</c:if><c:if test="${empty revenue.oldUser}">0</c:if></td>
				<td id="cur_active"><c:if test="${not empty revenue.active}">${revenue.active}</c:if><c:if test="${empty revenue.active}">0</c:if></td>
				<td id="cur_payrate"><c:if test="${not empty revenue.payrate}"><fmt:formatNumber value="${revenue.payrate*100}"  pattern="#0.00" />%</c:if><c:if test="${empty revenue.payrate }">0%</c:if></td>
				<td id="cur_arpu"><c:if test="${not empty revenue.arpu}"><fmt:formatNumber value="${revenue.arpu/100}"  pattern="#0.00" /></c:if><c:if test="${empty revenue.arpu }">0</c:if></td>
				<td id="cur_income"><c:if test="${not empty revenue.income}"><fmt:formatNumber value="${revenue.income/100}"  pattern="#0.00" /></c:if><c:if test=" ${empty revenue.income}">0</c:if> </td>
			</tr>
		  <tr>
		      <td><spring:message code='str425'/></td><!-- 实际数据 -->
			  <td id="newUser">${real.newUser}</td>
			  <td id="oldUser">${real.oldUser}</td>
			  <td id="active">${real.active}</td>
			  <td id="payrate">
                    <c:if test="${not empty real.payrate && real.payrate > 0 }">
			            <span style="<c:if test="${real.payrate > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${real.payrate*100}"  pattern="#0.00" />%</span>
			       </c:if>		
			  	    <c:if test="${ real.payrate <= 0 }">0%</c:if>
			  </td>
			  <td id="arpu"><c:if test="${not empty real.arpu && real.arpu > 0 }"><fmt:formatNumber value="${real.arpu/100}"  pattern="#0.00" /></c:if>
			      <c:if test="${ real.arpu <= 0 }">0</c:if>
			  </td>
			  <td id="income"><fmt:formatNumber value="${real.income/100}"  pattern="#0.00" /></td>
		  </tr>
		    <tr>
		      <td><spring:message code='str426'/></td><!-- 时间进度 -->
			  <td id="newUser_process"></td>
			  <td id="oldUser_process"></td>
			  <td id="active_process"></td>
			  <td id="payrate_process"></td>
			  <td id="arpu_process"></td>
			  <td id="income_process"></td>
		  </tr>
		  <tr>
		      <td><spring:message code='str427'/></td><!-- 指标偏差 -->
			  <td><c:if test="${real.newUser-revenue.newUser >0 }">+ ${real.newUser-revenue.newUser}</c:if><c:if test="${real.newUser-revenue.newUser <=0 }"> ${real.newUser-revenue.newUser}</c:if>
			      
			  </td>
			  <td><c:if test="${real.oldUser-revenue.oldUser >0 }">+ ${real.oldUser-revenue.oldUser}</c:if><c:if test="${real.oldUser-revenue.oldUser <=0 }"> ${real.oldUser-revenue.oldUser}</c:if>
			   
			  </td>
				<td><c:if test="${real.active-revenue.active >0 }">
				+ ${real.active-revenue.active}
				</c:if>
					<c:if test="${real.active-revenue.active <=0 }">
					 ${real.active-revenue.active}
					 </c:if>

				</td>
				<td>
				<c:if test="${real.payrate-revenue.payrate >0 }">+<span style="<c:if test="${real.payrate-revenue > 1  }">color: red;font-weight:bold</c:if>"><fmt:formatNumber value="${(real.payrate-revenue.payrate)*100}"  pattern="#0.00" />%</span> </c:if>
			  	<c:if test="${real.payrate-revenue.payrate <=0 }"> <fmt:formatNumber value="${(real.payrate-revenue.payrate)*100}"  pattern="#0.00" />%</c:if>
			     
			  </td>
			  <td><c:if test="${real.arpu-revenue.arpu >0 }">+ <fmt:formatNumber value="${(real.arpu-revenue.arpu)/100}"  pattern="#0.00" /></c:if>
			  <c:if test="${real.arpu-revenue.arpu <=0 }"> <fmt:formatNumber value="${(real.arpu-revenue.arpu)/100}"  pattern="#0.00" /></c:if>
			     
			  </td>
			  <td><c:if test="${real.income-revenue.income >0 }">+ <fmt:formatNumber value="${(real.income-revenue.income)/100}"  pattern="#0.00" /></c:if><c:if test="${real.income-revenue.income <=0 }"> ${real.income-revenue.income}</c:if>
			     
			   </td>
		  </tr>
		  <tr>
		      <td><spring:message code='str428'/></td>
			  <td><c:if test="${surplus >0 && revenue.newUser-real.newUser >0 }"><fmt:formatNumber value=" ${(revenue.newUser-real.newUser)/surplus }"></fmt:formatNumber></c:if>
			      <c:if test="${surplus == 0 && revenue.newUser-real.newUser >0}">${revenue.newUser-real.newUser }</c:if>
			      <c:if test="${surplus == 0 && revenue.newUser-real.newUser <=0 }">0</c:if>
			  </td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td><c:if test="${surplus >0 && revenue.income-real.income >0 }"><fmt:formatNumber value=" ${(revenue.income-real.income)/surplus }"></fmt:formatNumber></c:if>
			      <c:if test="${surplus == 0 && revenue.income-real.income >0}"><fmt:formatNumber value=" ${(revenue.income-real.income)/100}" pattern="#0.00"></fmt:formatNumber></c:if>
			      <c:if test="${surplus == 0 && revenue.income-real.income <=0 }">0</c:if>
			  </td>
		  </tr>
		</tbody>
	</table>

	<div class="row-fluid">
			<div class="span12 panel panel-primary">
				<div class="panel-heading"><spring:message code='str429'/></div>
				<div class="" style="width:100%;height:500px;line-height:500px;overflow:scroll;">
	<table id="monthly-report" class="datatable table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th><spring:message code='str198'/></th>
			<th><spring:message code='str430'/></th>
			<th><spring:message code='str431'/></th>
			<th><spring:message code='str432'/></th>
			<th><spring:message code='str433'/>1<spring:message code='str201'/></th>
			<th><spring:message code='str433'/>2<spring:message code='str201'/></th>
			<th><spring:message code='str433'/>3<spring:message code='str201'/></th>
			<th><spring:message code='str434'/></th>
			<th><spring:message code='str435'/></th><th><spring:message code='str432'/></th>
			<th><spring:message code='str433'/>1<spring:message code='str217'/></th>
			<th><spring:message code='str433'/>2<spring:message code='str217'/></th>
			<th><spring:message code='str433'/>3<spring:message code='str217'/></th>
		</tr>
	   </thead>
		<tbody>
		<c:forEach items="${realDayList}" var="item">
			<tr>
				<td>${item.day}</td>
				<td><fmt:formatNumber value="${redayincome/100}"  pattern="#0.00" /></td>
				<td><fmt:formatNumber value="${item.dayIncome/100}"  pattern="#0.00" /></td>
				<td><c:if test="${item.dayIncome-redayincome >0}"> +<fmt:formatNumber value="${(item.dayIncome-redayincome)/100}"  pattern="#0.00" /></c:if>
				    <c:if test="${item.dayIncome-redayincome <=0}"> <fmt:formatNumber value="${(item.dayIncome-redayincome)/100}"  pattern="#0.00" /></c:if>
				   
				</td>
				<td><fmt:formatNumber value="${item.pid1Income/100}" pattern="#0.00" /></td>
				<td><fmt:formatNumber value="${item.pid2Income/100}" pattern="#0.00" /></td>
				<td><fmt:formatNumber value="${item.pid3Income/100}" pattern="#0.00" /></td>
				<td><fmt:formatNumber value="${redayuser}" pattern="##0" /></td>
				<td>${item.newUser}</td>
				<td>
					<c:if test="${item.newUser-redayuser > 0}">+<fmt:formatNumber value="${item.newUser-redayuser}"  pattern="##0" /></c:if>
				    <c:if test="${item.newUser-redayuser <=0}"> <fmt:formatNumber value="${item.newUser-redayuser}"  pattern="##0" /></c:if>
				</td>
				<td>${item.pid1newUser }</td>
				<td>${item.pid2newUser }</td>
				<td>${item.pid3newUser }</td>
			</tr>
		</c:forEach>
	 </tbody>
	</table>
	</div>
 </div>
 </div>
<script type="text/javascript">
	$(document).ready(function(){
		var newUser_p = ($("#newUser").text()/$("#cur_newUser").text() * 100).toFixed(2);
		var oldUser_p = ($("#oldUser").text()/$("#cur_oldUser").text() * 100).toFixed(2);
		var active_p = ($("#active").text()/$("#cur_active").text() * 100).toFixed(2);
		var payrate_p = ((${real.payrate/revenue.payrate})*100).toFixed(2);
		var arpu_p = ($("#arpu").text()/$("#cur_arpu").text() * 100).toFixed(2);
		var income_p = ($("#income").text()/$("#cur_income").text() * 100).toFixed(2);
		if(newUser_p > 100){
		  var newUserP= "<span style='color: red;font-weight:bold'>"+newUser_p +"</span>";
			$("#newUser_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+100+"%;\">"+newUserP+"%</div>\n</div>");
		}else{
			$("#newUser_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+newUser_p+"%;\">"+newUser_p+"%</div>\n</div>");
		}
		if(oldUser_p > 100){
			var oldUserP= "<span style='color: red;font-weight:bold'>"+oldUser_p +"</span>";
			$("#oldUser_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+100+"%;\">"+oldUserP+"%</div>\n</div>");
		}else{
			$("#oldUser_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+oldUser_p+"%;\">"+oldUser_p+"%</div>\n</div>");
		}

		if(active_p > 100){
			var activeP= "<span style='color: red;font-weight:bold'>"+active_p +"</span>";
			$("#active_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+100+"%;\">"+activeP+"%</div>\n</div>");
		}else{
			$("#active_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+active_p+"%;\">"+active_p+"%</div>\n</div>");
		}
		if(payrate_p > 100){
			var payrateP= "<span style='color: red;font-weight:bold'>"+payrate_p +"</span>";
			$("#payrate_process").html("<div class=\"progress progress-success progress-striped\">\n<div class=\"bar\" style=\"width: "+100+"%;\">"+payrateP+"%</div>\n</div>");
		}else{
			$("#payrate_process").html("<div class=\"progress progress-success progress-striped\">\n<div class=\"bar\" style=\"width: "+payrate_p+"%;\">"+payrate_p+"%</div>\n</div>");
		}

		if(arpu_p > 100){
		    var arpuP= "<span style='color: red;font-weight:bold'>"+arpu_p +"</span>";
			$("#arpu_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+100+"%;\">"+arpuP+"%</div>\n</div>");
		}else{
			$("#arpu_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+arpu_p+"%;\">"+arpu_p+"%</div>\n</div>");
		}

		if(income_p > 100){
			var incomeP= "<span style='color: red;font-weight:bold'>"+income_p +"</span>";
			$("#income_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+100+"%;color: red;font-weight:bold\">"+incomeP+"%</div>\n</div>");
		}else{
			$("#income_process").html("<div class=\"progress progress-success progress-striped active\">\n<div class=\"bar\" style=\"width: "+income_p+"%;\">"+income_p+"%</div>\n</div>");
	})
</script>
</body>
</html>
