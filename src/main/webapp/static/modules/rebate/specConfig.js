function dealGoodsData(res){
	$.each(res, function(index, value) {
		var id = value['id'];
		res[index]['edit'] = "<a  href=\"javascript:beforeUpdateGoods('" + id
				+ "')\">修改</a>" + "&nbsp&nbsp<a  href=\"javascript:delGoods('"
				+ id + "')\">删除</a>";
		res[index]['goodsProp1'] = value['goodsProp']==0?"绑定":"不绑定";
		switch(value['exchangeLimit']){
		case 0:res[index]['exchangeLimit1'] ="无";break;
		case 1:res[index]['exchangeLimit1'] ="单角色每月上限";break;
		case 2:res[index]['exchangeLimit1'] ="单角色累积上限";break;
		default:res[index]['exchangeLimit1'] ="unknown";break;
		}
		
	});
	return res;
}

function addGoods(){
	$("#goodsId").removeAttr("disabled");
	$("#goodsDesc").removeAttr("disabled");
	$('#goodsForm').validate();
	var flag = $('#goodsForm').valid();
	$("#goodsId").attr("disabled","disabled");
	$("#goodsDesc").attr("disabled","disabled");
	if(!flag){
	    return;
	}
	var pids = $("#platMultiSel").select2('val');
	if(pids==""){
		bootbox.alert("请选择平台");
		return;
	}
	if($("#exchangeLimitSel").select2('val')!=0&&$("#topNum").val()==""){
		bootbox.alert("请输入上限数量");
		return;
	}
	$(obj).button('loading');
	var data = {
			pids:pids.join(","),
			goodsName:$("#addGoodsName").val(),
			goodsId:$("#goodsId").val(),
			goodsDesc : $("#goodsDesc").val(),
			goodsPrice : $("#goodsPrice").val(),
			goodsProp:$("#goodsPropSel").select2('val'),
			exchangeLimit:$("#exchangeLimitSel").select2('val'),
			topNum : $("#topNum").val(),
			timestamp : $.now()
	}
	
	$.ajax({
		url : BASE + "/rebate/config/insertGoodsSpec",
		type : "get",
		data : data,
		dataType : "json",
		success : function(result) {
			$(obj).button('reset');
			if (result.success) {
				bootbox.alert("保存成功");
				$('#goodsSpecTable').bootstrapTable('refresh');
			} else {
				bootbox.alert("保存失败");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$(obj).button('reset');
			bootbox.alert("network error");
		}
	});
}

function beforeUpdateGoods(id) {
	var row = $('#goodsSpecTable').bootstrapTable('getRowByUniqueId', id);
	$("#editId").val(id);
	$("#editPid").val(row['pid']);
	$("#editPlatName").val(row['platName']);
	$("#editGoodsId").val(row['goodsId']);
	$("#editGoodsName").val(row['goodsName']);
	$("#editGoodsDesc").val(row['goodsDesc']);
	
	$("#editGoodsPrice").val(row['goodsPrice']);
	$("#editGoodsProp").select2("val",row['goodsProp']);
	$("#editExchangeLimit").select2("val",row['exchangeLimit']);
	if(row['exchangeLimit']==0){
		$("#topNumDiv").hide();
		$("#topNum1").val("");
	}else{
		$("#topNumDiv").show();
		$("#topNum1").val(row['topNum']);
	}
	$("#editGoodsModal").modal("show");
}

function updateGoods(obj) {
	if($("#editExchangeLimit").select2('val')!=0&&$("#topNum1").val()==""){
		bootbox.alert("请输入上限数量");
		return;
	}
	var data = {
		id:$("#editId").val(),
		goodsId:$("#editGoodsId").val(),
		goodsName:$("#editGoodsName").val(),
		goodsDesc:$("#editGoodsDesc").val(),
		goodsPrice : $("#editGoodsPrice").val(),
		pid:$("#editPid").val(),
		platName:$("#editPlatName").val(),
		goodsProp : $("#editGoodsProp").select2('val'),
		exchangeLimit : $("#editExchangeLimit").select2('val'),
		topNum : $("#topNum1").val(),
		timestamp : $.now()
	}
	bootbox.confirm("确认修改吗？", function(result) {
		if (result) {
			$.ajax({
				url : BASE + "/rebate/config/updateGoodsSpec",
				type : "get",
				data : data,
				dataType : "json",
				success : function(result) {
					if (result.success) {
						$("#editGoodsModal").modal("hide");
						bootbox.alert("保存成功");
						$('#goodsSpecTable').bootstrapTable('refresh');
					} else {
						bootbox.alert("保存失败");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					bootbox.alert("network error");
				}
			});
		}
	});
}

function delGoods(id) {
	var ids = [];
	ids.push(id);
	var data = {
		ids : ids.join(","),
		timestamp : $.now()
	}
	doDelGoods(data);
}

function batchDel() {
	var selecteds = $('#goodsSpecTable').bootstrapTable('getSelections');
	if (selecteds.length <= 0) {
		bootbox.alert("请选择要删除的配置");
		return;
	}
	var ids = new Array();
	for (var i = 0; i < selecteds.length; i++) {
		ids[i] = selecteds[i].id;
	}

	var data = {
		ids : ids.join(","),
		timestamp : $.now()
	}
	doDelGoods(data);
}

function doDelGoods(data) {
	bootbox.confirm("确认删除吗？", function(result) {
		if (result) {
			$.ajax({
				url : BASE + "/rebate/config/delGoodsSpec",
				type : "get",
				data : data,
				dataType : "json",
				success : function(result) {
					if (result.success) {
						bootbox.alert("删除成功");
						$('#goodsSpecTable').bootstrapTable('refresh');
					} else {
						bootbox.alert("删除失败");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					bootbox.alert("network error");
				}
			});
		}
	})
}

function search(){
	var url = BASE+"/rebate/config/goodsSpecData?" +
	"pid="+$("#platSel2").select2('val') +
	"&goodsProp="+$("#goodsPropSel2").select2('val');
	
	if($("#searchGoodsName").val()){
		url = url+"&goodsName="+$("#searchGoodsName").val();
	}
	if($("#searchGoodsId").val()){
		url = url + "&goodsId="+$("#searchGoodsId").val();
	}
	
	$('#goodsSpecTable').bootstrapTable("refresh",{
		"url":url
	});
}

function initGoodsName(){
	$('#goodsName').select2({
	    placeholder          : "请输入",
	    minimumInputLength   : 1,
	    multiple             : false,
	    separator            : "^",                             // 分隔符
	    maximumSelectionSize : 1,                               // 限制数量
	    
	    createSearchChoice   : function(term, data) {// 创建搜索结果（使用户可以输入匹配值以外的其它值）
	    	
	    	console.log("search"+term);
	        return { id: term, text: term };
	    },
	    formatSelection : function (item) {
	    			$("#goodsId").val(item.id);
	    			$("#addGoodsName").val(item.name);
	    			var dd=item.goodsDesc.replace(/<\/?.+?>/g,""); 
	    			$("#goodsDesc").val(dd);
	    			return item.name; 
	    		},  // 选择结果中的显示
	    formatResult    : function (item) {console.log("Result"+item); return item.name; },  // 搜索列表中的显示
	    ajax : {
	        url      : BASE + "/rebate/config/searchByGoodsName",              // 异步请求地址
	        dataType : "json",                  // 数据类型
	        data     : function (term, page) {  // 请求参数（GET）
	            return { goodsName: term };
	        },
	        results      : function (data, page) { return data; },  // 构造返回结果
	        escapeMarkup : function (m) { return m; }               // 字符转义处理
	    }
	});
}

function addGoodsFormValidate(){
    $("#goodsForm").validate({
        rules: {
        	goodsId: {
                required: true
                
            },goodsDesc: {
                required: true
                
            },goodsPrice: {
                required: true,
                digits: true,
                min: 0
            }
        },
        messages: {
        	goodsId: {
        		required:"不能为空"
        		
        	},goodsDesc: {
        		required:"不能为空"
        	},goodsPrice: {
        		required:"不能为空",
        		digits:"请输入数字",
        		min:"请输入大于0的数字"
        	}
        }
    });
}