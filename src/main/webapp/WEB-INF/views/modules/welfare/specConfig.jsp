<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>特殊配置</title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	$(function() {
		$(".tipDiv").tooltip('show');
		addSingleServerFormValidate();
		//单账号平台下拉框
		initPlatSelect1("#platSel2","#serverSelect2");
		$("#platSel2").on("change",function(e){
			initServerSelect1("#serverSelect2",e.val);
		})
		//单服特殊配置下拉框
		initPlatSelect0("#platSel3","#serverSelect3");
		$("#platSel3").on("change",function(e){
			initServerSelect0("#serverSelect3",e.val);
		})
		
		initPlatSelect2("#platSel1","#serverSelect1");
		$("#platSel1").on("change",function(e){
			initServerSelect2("#serverSelect1",e.val);
		})
		
		initSingleAccountTable(BASE+"/welfare/num/numData?pid="+$("#platSel2").select2('val'));
		initSingleServerTable(BASE+"/welfare/specConfig/serverConfigData?pid="+$("#platSel3").select2('val'));
		
		//隐藏全选框
		$("#singleAccountConfigTable input[name='btSelectAll']").attr("type","hidden");
	});
</script>
<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/singleConfig.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/initSelect.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/welfareBase.js?timestamp=1112"></script>
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
			<li class="active"><a href="${ctx}/welfare/specConfig/specConfig">特殊配置</a></li>
			
		</ul>
<!-- ######################################单账号特殊配置 #############################################-->
	<div class="row-fluid">
		<div class="span12">
			<div class="form-actions">
             	 单账号特殊配置
            </div>
		</div>
	</div>
		<div class="row-fluid">
		<div class="span12">
		
		<div class="control-group">
		<div class="form-actions" style="background-color: #FFB6C1;margin:0 auto;">
             <label class="control-label" for="inputEmail">* 充值上限(rmb)</label>	  
             <input type="text" id="topCharge" style="width: 60px;vertical-align:middle; margin-bottom:2px; *margin-bottom:2px;"/>
             	 <button   type="button" class="btn" id="u" data-loading-text="正在保存"  onclick="updateTopCharge('update')">修改</button>
		       		<label class="checkbox">
      					<input style="vertical-align:middle; margin-bottom:2px; *margin-bottom:2px;"  id="topCheck" type="checkbox"> 本次修改后，此角色充值上限不再被“福利配置”及“单服特殊配置”所修改
    				</label>
            </div>
							
						</div>
		
			
		</div>
	</div>
			<div id="toolbar">
		<div class="row-fluid">
			<div class="span12">
		<div style="margin-left:20px;">
			
			<div class="form-group">
			 <label for="name" >平台:</label>
			   	<select  id="platSel2">
			   	</select>
			   
			
			
			   <label for="name" style="margin-left: 20px" >服务器:</label>
			   	<select  id="serverSelect2">
			   	</select>
			   	
			    <div class="btn-group" style="margin-left: 20px" >
			        <button type="button" class="btn btn-primary " data-toggle="modal" onclick="search()">
		            <i class="icon-search"></i>
		            查 询
		       		</button>
		       		<button type="button" class="btn btn-danger" onclick="updateTopCharge('del')">
		       		
						<i class="icon-trash"></i> 删除
					</button>
		    	</div>
			</div>
		</div>
	</div>
	</div>
	</div>
		<table id="singleAccountConfigTable" >
			
		</table>
<!-- ######################################单服特殊配置 #############################################-->
		<div class="row-fluid">
		<div class="span12">
			<div class="form-actions">
             	 单服特殊配置
            </div>
		</div>
	</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8">
					<form class="form-horizontal" id="singleServerForm">
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
							<label class="control-label">服务器</label>
							<div class="controls">
								<select id="serverSelect1">
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
							</div>
						</div>
						
						<div class="control-group">
							<label class="checkbox">
      							<input id="serverCheck" type="checkbox">
      							本次修改后，此服比例不再被“福利配置”所修改
    						</label>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<button type="button" class="btn btn-primary " data-loading-text="正在保存" id="updateCon" data-toggle="modal" onclick="updateConfig()">
		            				
		          				 			 确定
		       					</button>
								
							</div>
						</div>
					</form>
				</div>
			
			</div>
		</div>
		
		<hr style="border: 1px dashed #000; height: 1px">
		<hr style="border: 1px dashed #000; height: 1px">
		<div id="toolbar1">
		<div class="row-fluid">
			<div class="span12">
		<div style="margin-left:20px;">
			
			<div class="form-group">
			 <label for="name" >平台:</label>
			   	 <select  id="platSel3">
			   	</select> 
			   
			
			
			   <label for="name" style="margin-left: 20px" >服务器:</label>
			   	 <select  id="serverSelect3">
			   	</select> 
			   	
			   	 <label for="name" style="margin-left: 20px" >状态:</label>
			   	<select  id="status">
			   			<option value="">全部</option>
			   			<option value="0">生效</option>
			   			<option value="1">不生效</option>
			   	</select>
			  
			   
			    <div class="btn-group" style="margin-left: 20px" >
			        <button type="button" class="btn btn-primary " data-toggle="modal" onclick="searchServer()">
		            <i class="icon-search"></i>
		            查 询
		       		</button>
		       		<button type="button" class="btn btn-danger" onclick="deleteServerConfig()">
		       		
						<i class="icon-trash"></i> 删除
					</button>
		    	</div>
			</div>
		</div>
	</div>
	</div>
	</div>
		<table id="singleServerConfigTable" >
			
		</table>

		

	</div>
	<script type="text/javascript">
	function initSingleAccountTable(url){
		$('#singleAccountConfigTable').bootstrapTable('destroy').bootstrapTable({
			url:url,
			uniqueId:"id",
			toggle:"table",
			cache:"false",
			sidePagination:"server",
			pagination:"true",
			buttonsAlign:"left" ,
			toolbar:"#toolbar",
			toolbarAlign:"right" ,
			showToggle:"true" ,
			showColumns:"true" ,
			showRefresh:"true",
			pageList:"[30, 50, 80]" ,
			pageSize:"30",
			showExport:"true",
	        showExport:"true",
	        exportDataType:"all",
			responseHandler:"dealNumData",
		
            columns: [
                      	{
          	  				checkbox: true  
            			},
						{
    						field: 'platName',
    						title: '平台',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'serverName',
    						title: '服务器',
    						sortable:"true",
    						align:"center"
						},

						{
    						field: 'roleName',
    						title: '角色名',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'userId',
    						title: '平台账号',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'roleId',
    						title: '角色ID',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'usePeople',
    						title: '使用人',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'usePeoplePost',
    						title: '使用人职<br/>务',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'vipLevel',
    						title: 'VIP等级',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'sumYb',
    						title: '累计发放<br/>元宝',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'sumCharge',
    						title: '实际总充值<br/>(rmb)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topCharge',
    						title: '充值上限<br/>(rmb)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'numOfDays',
    						title: '连续未登<br/>天数',
    						sortable:"true",
    						align:"center"
						},
						
						{
    						field: 'level',
    						title: '等级',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'combat',
    						title: '当前战力',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'surplusYb',
    						title: '剩余元宝',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'createName',
    						title: '添加人',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'createDate1',
    						title: '设置时间',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'status1',
    						title: '状态',
    						sortable:"true",
    						align:"center"
						}
                  ]
            
        });
	}
	function initSingleServerTable(url){
		$('#singleServerConfigTable').bootstrapTable('destroy').bootstrapTable({
			url:url,
			uniqueId:"id",
			toggle:"table",
			cache:"false",
			sidePagination:"server",
			pagination:"true",
			buttonsAlign:"left" ,
			toolbar:"#toolbar1",
			toolbarAlign:"right" ,
			showToggle:"true" ,
			showColumns:"true" ,
			showRefresh:"true",
			pageList:"[30, 50, 80]" ,
			pageSize:"30",
			showExport:"true",
	        showExport:"true",
	        exportDataType:"all",
			responseHandler:"dealServerData",
		
            columns: [
                      	{
	  						checkbox: true  
						},
						{
    						field: 'platName',
    						title: '平台',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'serverName',
    						title: '服务器',
    						sortable:"true",
    						align:"center"
						},

						{
    						field: 'newServiceGold',
    						title: '新服资源<br/>(元宝)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'rResourceAmount',
    						title: '大R资源金额<br/>(rmb)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'rResourceRatio',
    						title: '大R资源<br/>比例',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'singleChargeRatio',
    						title: '单服总充值<br/>后续比例',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topCharge',
    						title: '内部号最高<br/>充值限制(rmb)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topGoldDay',
    						title: '单角色每日<br/>最高额度(元宝)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topHoldGold',
    						title: '单角色持有<br/>最高额度(元宝)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'topInternalNumber',
    						title: '单区内部号<br/>数量上限(个)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'addTimeLimit',
    						title: '内部号添加<br/>时间(天)',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'updateBy',
    						title: '最后操作人',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'isInfluence1',
    						title: '是否受批量<br/>修改影响',
    						sortable:"true",
    						align:"center"
						},
						
						{
    						field: 'updateDate1',
    						title: '最后修改<br/>时间',
    						sortable:"true",
    						align:"center"
						},
						{
    						field: 'status1',
    						title: '状态',
    						sortable:"true",
    						align:"center"
						}
						
                  ]
            
        });
	}
	</script>
</body>
</html>
