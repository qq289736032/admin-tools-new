<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>魔王降临</title>
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
		<li class="active"><a href="${ctx}/log/devil/devilSbefallList">魔王降临</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/log/devil/devilSbefallList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
		<label><spring:message code='str3'/> <spring:message code='str4'/></label>
		<input name="createDateStart" id="startDate" maxlength="50"  value="${paramMap.startDate}" class="input-small required" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		<label><spring:message code='str55'/></label>
		<input id="serverIds" name="serverIds" value="${paramMap.serverIds}" readonly/>
        <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();" />
	</form>
	<tags:message content="${message}"/>

	<div class="container-fluid">
			<div class="panel panel-primary">
				<div class="panel-heading">魔王降临</div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th></th><th>1日</th><th>2日</th><th>3日</th><th>4日</th></tr>
					<tr>
						<td>服务器活跃人数</td><td>${activeOne[0].num}</td><td>${activeTwo[0].num}</td><td>${activeThree[0].num}</td><td>${activeFour[0].num}</td>
					</tr>
					<tr>
						<td>活动参与人数</td><td>${participationOne[0].cou}</td><td>${participationTwo[0].cou}</td><td>${participationThree[0].cou}</td><td>${participationFour[0].cou}</td>
					</tr>
					<tr>
						<td>玩家死亡人数</td><td>${deathOne[0].cou }</td><td>${deathTwo[0].cou }</td><td>${deathThree[0].cou }</td><td>${deathFour[0].cou }</td>
					</tr>
					<tr>
						<td>BOSS击杀</td><td>${boosKillOne[0].cou}</td><td>${boosKillTwo[0].cou}</td><td>${boosKillThree[0].cou}</td><td>${boosKillFour[0].cou}</td>
					</tr>
				</table>
				</div>
			</div>

		<div class="panel panel-primary">
				<div class="panel-heading">BOSS击杀信息</div>
				<div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
				<table id="dailyTable" class="table table-striped table-bordered table-condensed">
					<tr><th>名字</th><th>战力</th><th>充值金额</th> </tr>
					<c:forEach items="${boosKillMsg}" var="item">
						<tr>
							<td>${item.role_name}</td>
							<td>${item.power}</td>
							<td>${item.rmb}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
		</div>
	</div>
</body>
</html>
