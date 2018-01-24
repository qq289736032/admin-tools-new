<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1217' /></title>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/modules/rebate/specConfig.js?timestamp=1112"></script>
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
	$(function() {
		$(".tipDiv").tooltip('show');
		addGoodsFormValidate();
		initPlat("#platMultiSel",false);
		initPlat("#platSel2",true);
		initGoodsName();
		initTable(BASE + "/rebate/config/goodsSpecData");
		$("#exchangeLimitSel").on("change",function(e){
			if(e.val!=0){
				$("#topNum").attr("type","text");
				$("#v").html("个");
			}else{
				$("#topNum").attr("type","hidden");
				$("#topNum").val("");
				$("#v").html("");
			}
		})
		
		$("#editExchangeLimit").on("change",function(e){
			if(e.val!=0){
				$("#topNumDiv").show();
			}else{
				$("#topNumDiv").hide();
				$("#topNum1").val("");
			}
		})
		//$(".sel2").select2();
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
			<li class="active"><a href="${ctx}/rebate/config/commonConfig">返利特殊配置</a></li>

		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8">
					<form class="form-horizontal" id="goodsForm">
						<div class="control-group">
						<label class="control-label" for="inputPassword">* 平台</label>
						<div class="controls">
						<select id="platMultiSel" multiple="multiple">
  							
						</select>
						</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 物品名称</label>
							<div class="controls">
							 <div id="goodsName"></div> 
							 <input id="addGoodsName" name="addGoodsName"  type="hidden" /> 
							 <p class="help-block">说明：输入物品名称，自动匹配物品ID及物品描述，若物品名称检测到多个物品id，则列出多条进行选择。</p>
								 <!-- <input id="goodsName" name="goodsName"  type="text" />  -->
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmail">* 物品ID</label>
							<div class="controls">
								<input id="goodsId" name="goodsId" disabled="disabled" type="text" />
								<!-- <button type="button" class="btn btn-danger" id="check"
									data-toggle="modal" onclick="checkGoodsName()">检查</button>
								<p class="help-block">说明：输入物品名称，点击检查，自动匹配物品ID及物品描述，若物品名称检测到多个角色id，则需要列出多条进行选择。</p> -->
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 物品描述</label>
							<div class="controls">
								<textarea id="goodsDesc" disabled="disabled" name="goodsDesc" rows="" cols=""></textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 物品单价</label>
							<div class="controls">
								<input id="goodsPrice" name="goodsPrice" type="text" />
								<font class="help-block">虚拟元宝</font>
							</div>
							
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 物品属性</label>
							<div class="controls">
								<select id="goodsPropSel" class="sel2">
									<option value="0">绑定</option>
									<option value="1">非绑定</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">* 兑换限制</label>
							<div class="controls">
								<select id="exchangeLimitSel" class="sel2">
									<option value="0">无</option>
									<option value="1">单角色每月上限</option>
									<option value="2">单角色累计上限</option>
								</select>
								<input id="topNum" name="topNum"  type="hidden" /><font id="v"></font>
							</div>
						</div>
						

						<div class="control-group">
							<div class="controls">
								<button type="button" class="btn btn-primary " id="add"
									data-toggle="modal" onclick="addGoods()"
									data-loading-text="正在添加">添加</button>

							</div>
						</div>
					</form>
				</div>

			</div>
		</div>

		<hr style="border: 1px dashed #000; height: 1px">
		<hr style="border: 1px dashed #000; height: 1px">
		<div id="toolbar">
			<div class="row-fluid">
				<div class="span12">
					<div style="margin-left: 20px;">
						<div class="form-group">
							<label for="name">平台:</label>
							<select id="platSel2"></select>
							<label for="name" style="margin-left: 20px">物品名称:</label> <input
								id="searchGoodsName" /> 
							<label for="name" style="margin-left: 20px">物品ID:</label> <input
								id="searchGoodsId" /> 
							<label for="name">物品属性:</label>
							<select id="goodsPropSel2" class="sel2">
									<option value="0">绑定</option>
									<option value="1">非绑定</option>
							</select>

							<div class="btn-group" style="margin-left: 20px">
								<button type="button" class="btn btn-primary "
									data-toggle="modal" onclick="search()">
									<i class="icon-search"></i> 查 询
								</button>
								<button type="button" class="btn btn-primary " id="dc">

									<i class="icon-download-alt"></i> 导出
								</button>
								<button type="button" class="btn btn-primary " onclick="batchDel()">

									<i class="icon-download-alt"></i> 批量删除
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<table id="goodsSpecTable">

		</table>

		<div class="modal fade" id="editGoodsModal" tabindex="-1"
			role="dialog" aria-labelledby="editLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="newPageOut1">修改</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="editGoodsForm">
							<input type="hidden" id="editId" />
							<input type="hidden" id="eidtPid" />
							<input type="hidden" id="editPlatName" />
							<input type="hidden" id="editGoodsId" />
							<input type="hidden" id="editGoodsName" />
							<input type="hidden" id="editGoodsDesc" />
						
							<div class="form-group">
								<label for="editGoodsPrice" >物品单价</label> <input
									class="form-control" name="editGoodsPrice" id="editGoodsPrice"></input>
							</div>
							<div class="form-group">
								<label for="editGoodsProp">物品属性</label> 
								<select id="editGoodsProp">
									<option value="0">绑定</option>
									<option value="1">非绑定</option>
								</select>
							</div>
							
							<div class="form-group">
								<label for="editGoodsProp">兑换限制</label> 
								<select id="editExchangeLimit">
									<option value="0">无</option>
									<option value="1">单角色每月上限</option>
									<option value="2">单角色累计上限</option>
								</select>
								
							</div>
							<div class="form-group" id="topNumDiv">
								<label for="topNum1">上限数量</label> 
								
								<input id="topNum1"  class="input-sm form-control" placeholder="数量" name="topNum1"  type="text" />
								
							</div>
							
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-warning" data-dismiss="modal">
							<span class="glyphicon glyphicon-off"></span> 关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick="updateGoods(this)">
							<span class="glyphicon glyphicon-floppy-save"></span> 保存
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		function initTable(url) {
			$('#goodsSpecTable').bootstrapTable('destroy').bootstrapTable({
				url : url,
				uniqueId : "id",
				toggle : "table",
				cache : "false",
				pagination : "true",
				buttonsAlign : "left",
				toolbar : "#toolbar",
				toolbarAlign : "right",
				showToggle : "true",
				showColumns : "true",
				showRefresh : "true",
				pageList : "[30, 50, 80]",
				pageSize : "30",
				showExport : "true",
				showExport : "true",
				exportDataType : "all",
				responseHandler : "dealGoodsData",

				columns : [ {
  	  				checkbox: true  
    			},{
					field : 'platName',
					title : '平台',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsName',
					title : '物品名称',
					sortable : "true",
					align : "center"
				}, {
					field : 'goodsId',
					title : '物品ID',
					sortable : "true",
					align : "center"
				},
				{
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
					field : 'edit',
					title : '操作',
					sortable : "true",
					align : "center"
				}
				]

			});
		}
	</script>
</body>
</html>
