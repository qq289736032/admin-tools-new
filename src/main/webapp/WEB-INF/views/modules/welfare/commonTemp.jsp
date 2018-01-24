<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1217' /></title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	var fileName = "平台配置";
	$(function() {
		$(".tipDiv").tooltip('show');
		//加载平台下拉框
		initAllPlat("#platSel1",true);
		initAllPlat("#platSel2",true);
		initAllPlat("#platSel3",true);
        // 增加表单校验
		addplatTempFormValidate();
        addEditFormValidate();
        
        
	});
</script>
<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/commonTemp.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/initSelect.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/welfareBase.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/jquery/jquery.validate.js?timestamp=1112"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootbox.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrapExport.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/tableExport.js"></script>
<style>
.hfont {
	font-weight: bold;
}
</style>
</head>
<body>

	<div style="margin: 20px">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/welfare/common/temp">福利配置</a></li>
			
		</ul>
		<span class="hfont">通用福利模板</span>
		<table id="commonTempTable" 
			data-unique-id="id"
			data-toggle="table"
			data-url="${ctx}/welfare/common/tempData" 
			data-cache="false"
			data-id-field="id" 
			data-response-handler="dealCommonTempData">
			<thead>
				<tr>

					<th data-field="platNature" editable="true" 
						data-sortable="true">公会性质</th>
					<th data-field="newServiceGold" data-sortable="true"
						>新服资源</th>
					<th data-field="rResourceAmount" data-sortable="true"
						data-align="center" >大R资源金<br/>额(RMB)</th>
					<th data-field="rResourceRatio1" data-sortable="true"
						data-align="center" >大R资源比<br/>例(%)</th>
					<th data-field="singleChargeRatio1" data-sortable="true"
						data-align="center" >单服总充值<br/>后续比例(%)</th>
					<th data-field="topCharge" data-sortable="true" data-align="center"
						>内部号最高<br/>充值限制(RMB)</th>
					<th data-field="topGoldDay" data-sortable="true"
						data-align="center" >单角色每日<br/>最高限额(元宝)</th>
					<th data-field="topHoldGold" data-sortable="true"
						data-align="center" >单角色最高<br/>持有额度(元宝)</th>
					<th data-field="topInternalNumber" data-sortable="true"
						data-align="center" >单区内部号<br/>数量上限(个)</th>
					<th data-field="addTimeLimit" data-sortable="true"
						data-align="center" >内部号添加<br/>时间限制(天)</th>
					<th data-field="operate" data-sortable="true" data-align="center"
						>操作</th>
				</tr>
			</thead>
		</table>
		<hr style="border: 1px dashed #000; height: 1px">
		<hr style="border: 1px dashed #000; height: 1px">

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6">
					<form class="form-horizontal" role="form" id="platTempForm">
						<div class="control-group">

							<!-- Select Basic -->
							<label class="control-label">平台</label>
							<div class="controls">
								<select id="platSel1">
								</select>
							</div>

						</div>
						<div class="control-group">

							<!-- Select Basic -->
							<label class="control-label">奖金池类型</label>
							<div class="controls">
								<select id="goldPoolSelect1">
									<option value="">全部</option>
			   						<option value="独代奖金池">独代奖金池</option>
			   						<option value="平台奖金池">平台奖金池</option>
								</select>
							</div>

						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmail">* 新服资源(元宝)</label>
							<div class="controls">
								<input id="editNewService" name="editNewService" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 大R资源金额(rmb)</label>
							<div class="controls">
								<input id="editRRmb" name="editRRmb" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 大R资源比例(%)</label>
							<div class="controls">
								<input id="editRRatio" name="editRRatio" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 单服总充值后续比例(%)</label>
							<div class="controls">
								<input id="editChargeRatio" name="editChargeRatio" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 内部号最高充值限制(rmb)</label>
							<div class="controls">
								<input id="editTopCharge" name="editTopCharge" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 单角色每日最高限额(元宝)</label>
							<div class="controls">
								<input id="editTopGold" name="editTopGold" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 单角色持有最高额度(元宝)</label>
							<div class="controls">
								<input id="editHoldGold" name="editHoldGold" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 单区内部号数量限制上限(个)</label>
							<div class="controls">
								<input id="editTopNum" name="editTopNum" type="text" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 内部号添加时间限制(天)</label>
							<div class="controls">
								<input id="editTopLimit" name="editTopLimit" type="text" />
								<p style="color: red">说明：开服X天后无法添加福利号</p>
							</div>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<button type="button" class="btn btn-primary " data-loading-text="正在保存"  id="updatePData" data-toggle="modal" onclick="updatePlatData()">
		            				<span class="glyphicon glyphicon glyphicon-search"></span>
		          				 			 确定
		       					</button>
								
							</div>
						</div>
						<p style="color: red">温馨提示: 如若在下方勾选平台, 则新配置只对下方勾选的平台生效</p>
					</form>
				</div>
				<div class="span6">
					<form class="form-horizontal">
						<fieldset>
							<div id="legend" class="">
								<legend class="">平台性质调整</legend>
							</div>
							<div class="control-group">

								<!-- Select Basic -->
								<label class="control-label">* 平台</label>
								<div class="controls">
									<select id="platSel2">
									</select>
								</div>

							</div>

							<div class="control-group">

								<!-- Select Basic -->
								<label class="control-label">* 平台性质</label>
								<div class="controls">
									<select id="platNatureSel1">
										<option value="网盟">网盟</option>
			   							<option value="公会">公会</option>
									</select>
								</div>

							</div>

							<div class="control-group">


								<!-- Button -->
								<div class="controls">
								<button type="button" class="btn btn-primary " data-loading-text="正在保存"  id="updateNature" data-toggle="modal" onclick="updatePlatNature()">
		            				<span class="glyphicon glyphicon glyphicon-search"></span>
		          				 			 确定
		       					</button>
								</div>
							</div>

						</fieldset>
					</form>
				</div>
			</div>
		</div>
		
		<hr style="border: 1px dashed #000; height: 1px">
		<hr style="border: 1px dashed #000; height: 1px">
		<div id="toolbar">
		<div class="row-fluid">
			<div class="span12">
		<div style="margin-left:20px;">
			
			<div class="form-group">
			 <label for="name" >平台:</label>
			   	<select  id="platSel3">
			   	</select>
			   
			
			
			   <label for="name" style="margin-left: 20px" >状态:</label>
			   	<select  id="statusSelect">
			   		<option value="">全部</option>
			   		<option value="0">正常</option>
			   		<option value="1">暂停</option>
			   	</select>
			   	
			   	  <label for="name" style="margin-left: 20px" >奖金池类型:</label>
			   	<select id="goldPoolSelect2">
			   		<option value="">全部</option>
			   		<option value="独代奖金池">独代奖金池</option>
			   		<option value="平台奖金池">平台奖金池</option>
				</select>
			   	
			   <label for="name" style="margin-left: 20px" >平台性质:</label>
			   	<select  id="platNatureSel2">
			   		<option value="">全部</option>
			   		<option value="网盟">网盟</option>
			   		<option value="公会">公会</option>
			   	</select>
			  
			   
			    <div class="btn-group" style="margin-left: 20px" >
			        <button type="button" class="btn btn-primary " data-toggle="modal" onclick="search()">
		            <i class="icon-search"></i>
		            查 询
		       		</button>
		       		<button type="button" class="btn btn-primary " id="dc">
						<i class="icon-download-alt"></i> 导出
					</button>
		    	</div>
			</div>
		</div>
	</div>
	</div>
	</div>
		<table id="commonPlatTable" 
			data-unique-id="id" 
			data-toggle="table"
			data-url="${ctx}/welfare/common/platData" 
			data-cache="false"
			data-id-field="id"  
			data-click-to-select="true"
			data-buttons-align="left" 
			data-toolbar="#toolbar"
            data-toolbar-align="right" 
			data-show-toggle="true" 
			data-show-columns="true" 
			data-show-refresh="true"
			data-show-Export="true"
            data-show-export="true"
            data-export-data-type="all"
			data-response-handler="dealCommonPlatData">
			<thead>
				<tr>
					<th data-checkbox="true" >id</th>
					<th data-field="platName" editable="true" 
						data-sortable="true">平台</th>
					<th data-field="platNature" editable="true" 
						data-sortable="true">平台性质</th>
						<th data-field="goldPoolCategory" data-sortable="true">奖金池类别</th>
					<th data-field="newServiceGold" data-sortable="true"
						>新服资源</th>
					<th data-field="rResourceAmount" data-sortable="true"
						data-align="center" >大R资源金<br/>额(RMB)</th>
					<th data-field="rResourceRatio1" data-sortable="true"
						data-align="center" >大R资源比<br/>例(%)</th>
					<th data-field="singleChargeRatio1" data-sortable="true"
						data-align="center" >单服总充值<br/>后续比例(%)</th>
					<th data-field="topCharge" data-sortable="true" data-align="center">内部号最高<br/>充值限制(RMB)</th>
					<th data-field="topGoldDay" data-sortable="true"
						data-align="center" >单角色每日<br/>最高限额(元宝)</th>
					<th data-field="topHoldGold" data-sortable="true"
						data-align="center" >单角色最高<br/>持有额度(元宝)</th>
					<th data-field="topInternalNumber" data-sortable="true"
						data-align="center" >单区内部号<br/>数量上限(个)</th>
					<th data-field="addTimeLimit" data-sortable="true"
						data-align="center" >内部号添加<br/>时间限制(天)</th>
					<th data-field="updateBy" data-sortable="true"
						data-align="center" >最后操作人</th>
					<th data-field="updateDate1" data-sortable="true"
						data-align="center" >最后修改<br/>时间</th>
					<th data-field="status1" data-sortable="true"
						data-align="center" >状态</th>
					<th data-field="operate" data-sortable="true" data-align="center"
						>操作</th>
				</tr>
			</thead>
		</table>

		<!-- Modal newPageOut -->
		<div class="modal fade" id="editCommonTemp" tabindex="-1"
			role="dialog" aria-labelledby="editLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="newPageOut">修改通用福利模板</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="editForm">
							<input type="hidden" id="commonTempId" />
							<div class="form-group">
								<label for="platNature" class="control-label">公会性质</label> 
								<select  id="platNatureSel3">
									<option value="网盟">网盟</option>
			   						<option value="公会">公会</option>
			   					</select>
							</div>
							<div class="form-group">
								<label for="newServiceGold" >新服资源</label> <input
									class="form-control" name="newServiceGold" id="newServiceGold"></input>
							</div>
							<div class="form-group">
								<label for="rResourceAmount">大R资源金额(RMB)</label> <input
									class="form-control" name="rResourceAmount" id="rResourceAmount"></input>
							</div>
							<div class="form-group">
								<label for="rResourceRatio">大R资源比例(%)</label> <input
									class="form-control" name="rResourceRatio" id="rResourceRatio"></input>
							</div>
							<div class="form-group">
								<label for="singleChargeRatio">单服总充值后续比例(%)</label> <input
									class="form-control" name="singleChargeRatio" id="singleChargeRatio"></input>
							</div>
							<div class="form-group">
								<label for="topCharge">内部号最高充值限制(RMB)</label> <input
									class="form-control" name="topCharge" id="topCharge"></input>
							</div>
							<div class="form-group">
								<label for="topGoldDay">单角色每日最高限额(元宝)</label> <input
									class="form-control" name="topGoldDay" id="topGoldDay"></input>
							</div>
							<div class="form-group">
								<label for="topHoldGold">单角色最高持有额度(元宝)</label> <input
									class="form-control" name="topHoldGold" id="topHoldGold"></input>
							</div>
							<div class="form-group">
								<label for="topInternalNumber">单区内部号数量上限(个)</label> <input
									class="form-control" name="topInternalNumber" id="topInternalNumber"></input>
							</div>
							<div class="form-group">
								<label for="addTimeLimit">内部号添加时间限制(天)</label> <input
									class="form-control" name="addTimeLimit" id="addTimeLimit"></input>
							</div>


						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-warning" data-dismiss="modal">
							<span class="glyphicon glyphicon-off"></span> 关闭
						</button>
						<button type="button" class="btn btn-primary"
						data-loading-text="正在保存"
							onclick="saveCommonTemp(this)">
							<span class="glyphicon glyphicon-floppy-save"></span> 保存
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
