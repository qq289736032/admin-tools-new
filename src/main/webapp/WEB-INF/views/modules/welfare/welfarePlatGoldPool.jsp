<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>福利奖金池(平台)</title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	var passageway = "平台通道";
	var goldPoolCategory = "平台奖金池";
	$(function() {
		
		$(".tipDiv").tooltip('show');
		
		//加载平台下拉框
		initPlatSelect2("#platSel2","#serverSelect2");
		initTable(BASE+"/welfare/num/numData?pid="
				+$("#platSel2").select2('val')
				+"&serverId="+$("#serverSelect2").select2('val'));
	
		loadResource();
		$("#platSel2").on("change",function(e){
			initServerSelect2("#serverSelect2",e.val);
		})
	});
</script>
<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/welfareNum.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/initSelect.js?timestamp=1111"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/welfare/goldPool.js?timestamp=1111"></script>
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
			<li class="active"><a href="${ctx}/welfare/goldPool/platGoldPool">福利奖金池</a></li>
			
		</ul>


		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6">
					<form class="form-horizontal">
						<div class="control-group">

							<!-- Select Basic -->
							<label class="control-label">平台</label>
							<div class="controls">
								<select id="platSel2">
								</select>
							</div>
						</div>
						<div class="control-group">

							<!-- Select Basic -->
							<label class="control-label">服务器</label>
							<div class="controls">
								<select id="serverSelect2">
								</select>
							</div>

						</div>
						
						<div class="control-group">
							<div class="controls">
								<button type="button" class="btn btn-primary " id="add" data-toggle="modal" onclick="search();loadResource();">
		            				
		          				 			 查询
		       					</button>
								
							</div>
						</div>
						
						
						<div class="control-group">

							<!-- Select Basic -->
							<label class="control-label">元宝数量</label>
							
							<div class="controls">
								<input id="goldNum" type="text" />
							</div>
						</div>
						
						
						<div class="control-group">
							<div class="controls">
								<button type="button" data-loading-text="正在发放" class="btn btn-primary " id="grant" data-toggle="modal" onclick="grantGold()">
		            				
		          				 			 发放
		       					</button>
								
							</div>
						</div>
						
					
						<p style="color: red">温馨提示：内部福利账号不允许进行任何交易或买卖（包括但不限于账号间交易、市场交易、公会交易等），一经发现，将立即做封号处理，且所有交易金额均计入对账。</p>
						
						
						
					</form>
				</div>
				
				<div class="span6">
			<table class="table-bordered table-condensed">
				<tbody>
					<tr>
					
						
						
						<td>新服资源</td>
						<td id="newServiceGold"></td>
						<td>大R金额</td>
						
						<td id="RResourceAmount"></td>
					</tr>
					<tr>
						<td>本服今日充值</td>
						<td id="chargeSumOfDay"></td>
						<td>最高充值限制(rmb)</td>
						<td id="topCharge"></td>
						
					</tr>
					<tr>
						<td>本服总充值</td>
						<td id="chargeSum"></td>
						<td>单角色每日最高额度</td>
						<td id="topGoldDay"></td>
						
					</tr>
					<tr>
						<td>大R单日累充</td>
						<td id="drSum"></td>
						<td>单角色持有最高额度</td>
						
						<td id="topHoldGold"></td>
						
						
					</tr>
					<tr>
						<td>后续资源</td>
						
						<td id="singleSum"></td>
						<td>单区内部号数量上限</td>
						
						<td id="topInternalNumber"></td>
						
						
						
					</tr>
					
					
					<tr>
						
						<td>本服资源总额</td>
						<td id="goldSum"></td>
						<td>内部号添加时间限制</td>
						
						<td id="addTimeLimit"></td>
						
					</tr>
					<tr>
						<td>本服已发资源</td>
						<td id="issuedResource"></td>
						<td><font color="red">剩余资源</font></td>
						
						<td id="surplus"></td>
						
						
					</tr>
				</tbody>
			</table>
			</div>
			
			</div>
		</div>
		
		<div class="row-fluid">
		<div class="span12">
			<div class="form-actions">
             	内部角色元宝发放
            </div>
		</div>
	</div>
	
	
		<table id="welfareNumTable" >
			
		</table>

		<p  style="color: red">说明：</p>
		
			<ul  style="color: red">
				<li>1.添加福利号以后，在本页面先选择平台、服务器，点击“查询”，可显示对应区服所有的内部号。在下方的查询列表左侧勾选需要发放的角色，上方输入元宝数量，点击“发放”</li>
				<li>2.右上角的数据定义：
					<ul>
						<li>2.1  新服资源：新服起始可直接发放的元宝数量（固定金额）</li>
						<li>2.2  大R资源：单日0点-24点以内，单角色累计充值超过一定额度（例如1000RMB），累计充值的金额数量按照一定比例（例如70%）投入到奖金池中。（单日刷新1次）</li>
						<li>2.3  后续资源：单服总充值的一定比例（例如30%）投入到奖金池中（实时刷新）</li>
						<li>2.4  本服资源总额=新服资源+大R单笔资源+后续资源</li>
						<li>2.5  单角色每日限额：每个角色单日可以从奖金池获取的最高元宝数量为多少</li>
						<li>2.6  充值限制：该角色实际充值额度低于限制金额，可以发放奖金，若高于限制金额，则无法发放奖金</li>
						<li>2.7  单角色持有上限：该角色已剩余元宝高于限制金额，则无法发放奖金</li>
					</ul>
				</li>
				<li>3.剩余资源，已发资源，为实时刷新，若发放金额，低于剩余资源也无法发放成功</li>
				<li>4.正式服开服前建议不要使用奖金池内资源，否则可能导致正式服时无剩余资源可用</li>
			</ul>
		
	</div>
	<script type="text/javascript">
	function initTable(url){
		$('#welfareNumTable').bootstrapTable('destroy').bootstrapTable({
			url:url,
			uniqueId:"id",
			toggle:"table",
			cache:"false",
			sidePagination:"server",
			pagination:"true",
			buttonsAlign:"left" ,
			//singleSelect:"false",
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
			responseHandler:"dealNumSeniorData",
		
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
    						title: '未登天数',
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
    						title: '战斗力',
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
