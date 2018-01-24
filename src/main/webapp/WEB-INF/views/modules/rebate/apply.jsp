<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>申请返利</title>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/apply.js?timestamp=1117"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/initSelect.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/rebateBase.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootbox.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrapExport.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/tableExport.js"></script>

<script type="text/javascript">
	var fileName = "返利特殊配置";
	var param = getUrlVars();
	$(function() {
		$(".tipDiv").tooltip('show');
		addEmailFormValidate();
		var url = BASE +"/rebate/self/selfHelpData?roleId="+param['id'];
		$("#selfTable").bootstrapTable('refresh',{
			url:url
		});
		loadKeepDayData();
		initGoodsSpecTable(BASE + "/rebate/self/goodsData?pid="+param['pid']+"&roleId="+param['id']);
	});
</script>
<style>
.hfont {
	font-weight: bold;
}
</style>
</head>
<body>

	<div style="margin: 20px">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/rebate/self/apply">申请返利</a></li>

		</ul>
		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">返利说明</div>
			</div>
		</div>

		<table id="selfTable" data-unique-id="id" data-toggle="table"
			data-url="" data-cache="false" 
			data-pagination = "true" data-page-list="[30, 50, 80]" data-page-size="30"
			data-id-field="id" data-response-handler="dealselfData">
			<thead>
				<tr>

					<th data-field="pid" editable="true" data-sortable="true">平台</th>
					<th data-field="serverName" data-sortable="true">服务器</th>
					<th data-field="roleName" data-sortable="true"
						data-align="center">角色名</th>
					<th data-field="userId" data-sortable="true"
						data-align="center">平台账号</th>
					<th data-field="roleId" data-sortable="true"
						data-align="center">角色ID</th>
					<th data-field="allowGold" data-sortable="true" data-align="center">可返虚拟元宝数</th>
					<th data-field="usedGold" data-sortable="true"
						data-align="center">已返虚拟元宝数</th>
				</tr>
			</thead>
		</table>
		<hr style="border: 1px dashed #000; height: 1px">
		<hr style="border: 1px dashed #000; height: 1px">


		<div class="container-fluid">
			<div class="row-fluid">
				
				<div class="span6">
					<table id="ratioTable" data-unique-id="id" data-toggle="table"
						data-url="${ctx}/rebate/config/rebateRatioData" data-cache="false"
						data-id-field="id" data-response-handler="dealRatioData">
						<thead>
							<tr>
								<th data-field="dayAmount" editable="true" data-sortable="true">单日累充金额(元)</th>
								<th data-field="rebateRatio" data-sortable="true">返还虚拟元宝比例(%)</th>
							</tr>
						</thead>
					</table>
				</div>
				
				<div class="span6">
					<p>返利说明:</p>
					<p>1、单日充值超过指定金额的可以参与返还，返还计算方式为：单日充值金额*对应的比例</p>
					<p>2、多日充值金额不累计，但单日返还的元宝可以进行多日累计，最高可累计至<font color="red" id="day">0</font>日</p>
					<p>3、<font color="red">本系统只保留<font id="day1">0</font>日的返还元宝，超出日期未返还的将失效，请及时返利操作</font></p>
				</div>
			</div>
		</div>

		<p>最近N日单日充值情况</p>
		<table id="chargeTable">
		</table>
		
		
		
		<div class="row-fluid">
			<div class="span12">
				<div class="form-actions">自助返利</div>
			</div>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<form class="form-horizontal" role="form" id="emailForm">

						<div class="control-group">
							<label class="control-label" for="inputEmail">邮件内容：</label>
							<div class="controls">
								<font color="blue">温馨提示：此内容为玩家查收邮件中收到的内容。其中标题限制字数为14字，邮件限制字数为100字。</font>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputEmail">邮件标题：</label>
							<div class="controls">
								<input id="emailTitle" value="充值返利道具" name="emailTitle" type="text" /> 
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputEmail">邮件内容：</label>
							<div class="controls">
								<textarea rows="4" cols="10"  id="emailText" name="emailText">亲爱的玩家您好，附件为您的充值返利道具，请查收，如有问题可直接与游戏客服联系，感谢您对《战神争霸》的支持，祝您游戏愉快！</textarea>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
		<hr style="border: 1px dashed #000; height: 1px">
		<hr style="border: 1px dashed #000; height: 1px">
		
		<font color="blue" style="margin-left: 0px">温馨提示：本页面返利物品不定时更新。</font>
		<font color="red" style="text-align: right">共有 <font id="allGold">0</font> 虚拟元宝，本次兑换将消耗   <font id="spendGold">0</font> 虚拟元宝</font>
		<br/>
		<br/>
		<table id="goodsSpecTable">
		</table>
		<br/>
		<div class="row-fluid">
			<div class="span12">
				<button type="button" class="btn btn-primary"
							onclick="sendGoods(this)" data-loading-text="正在提交">
							<span class="glyphicon glyphicon-floppy-save"></span> 提交
				</button>
				
				<button type="button" class="btn btn-primary"
							onclick="window.location.href='${ctx}/rebate/self/selfHelp'">
							<span class="glyphicon glyphicon-floppy-save"></span> 返回
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function initGoodsSpecTable(url) {
			$('#goodsSpecTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				showToggle : false,
				showColumns : false,
				showRefresh : false,
				responseHandler : "dealGoodsData",

				columns : [ {
					field : 'goodsName',
					title : '物品名称',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsId',
					title : '物品ID',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsDesc',
					title : '物品描述',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsPrice',
					title : '物品单价(虚拟元宝)',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsProp1',
					title : '物品属性',
					sortable : "true",
					align : "center"
				}, {
					field : 'exchangeLimit1',
					title : '兑换限制',
					sortable : "true",
					align : "center"
				}, {
					field : 'topNum',
					title : '上限数量',
					sortable : "true",
					align : "center"
				}, {
					field : 'usedNum',
					title : '已兑换数量',
					sortable : "true",
					align : "center"
				} , {
					field : 'currentNum',
					title : '本次兑换数量',
					sortable : "true",
					align : "center"
				} 
				, {
					field : 'sumGold',
					title : '总价(虚拟元宝)',
					sortable : "true",
					align : "center"
				} ]

			});
		}
		
		function initChargeDetailTable(url) {
			$('#chargeTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				showToggle : false,
				showColumns : false,
				showRefresh : false,
				responseHandler : "dealDetailData",

				columns : [ {
					field : 'statisticDate1',
					title : '日期',
					sortable : "true",
					align : "center"
				}, {
					field : 'sumMoney',
					title : '单日充值金额',
					sortable : "true",
					align : "center"
				}, {
					field : 'rechargeTimes',
					title : '充值次数',
					sortable : "true",
					align : "center"
				}, {
					field : 'rebateRatio',
					title : '当日返还虚拟元宝比例',
					sortable : "true",
					align : "center"
				}, {
					field : 'rebateGold',
					title : '当日返还虚拟元宝数',
					sortable : "true",
					align : "center"
				}]

			});
		}
	</script>
</body>
</html>
