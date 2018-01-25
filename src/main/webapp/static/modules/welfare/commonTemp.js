/**
 * 处理通用福利模板数据
 * @param res 表格数据
 * @returns
 */
function dealCommonTempData(res){
	$.each(res,function(index,value){
		res[index]['operate'] = "<a title='预览' href=\"javascript:editCommonTemp('"+value['id']+"')\">"+'修改</a>';
		res[index]['rResourceRatio1'] = res[index]['rResourceRatio']+"%";
		res[index]['singleChargeRatio1'] = res[index]['singleChargeRatio']+"%";
	})
	return res;
}

/**
 * 处理通用平台福利数据
 * @param res 表格数据
 * @returns
 */
function dealCommonPlatData(res){
	$.each(res,function(index,value){
		var operateType = value['status'] == 0 ? "暂停" : "启动";
		res[index]['operate'] = "<a title='' href=\"javascript:updatePlatStatus('"+value['id']+"')\">"+operateType+'</a>';
		res[index]['rResourceRatio1'] = res[index]['rResourceRatio']+"%";
		res[index]['singleChargeRatio1'] = res[index]['singleChargeRatio']+"%";
		res[index]['updateDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(res[index]['updateDate']));
		res[index]['status1'] = res[index]['status'] == 0 ? "正常" : "暂停";
	})
	return res;
}

/**
 * 处理通用平台福利数据
 * @param res 表格数据
 * @returns
 */
function dealSettingLogData(res){
	$.each(res.rows,function(index,value){
		res.rows[index]['rResourceRatio1'] = value['rResourceRatio']+"%";
		res.rows[index]['singleChargeRatio1'] = value['singleChargeRatio']+"%";
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
		switch(value['editType']){
		case 0:res.rows[index]['editType1'] ="新增";break;
		case 1:res.rows[index]['editType1'] ="修改";break;
		case 2:res.rows[index]['editType1'] ="删除";break;
		case 3:res.rows[index]['editType1'] ="启动";break;
		case 4:res.rows[index]['editType1'] ="停止";break;
		default:res.rows[index]['editType1'] ="unknown";break;
		}
		res.rows[index]['status1'] = value['status'] == 0 ? "正常" : "暂停";
	})
	return res;
}

/**
 * 弹出通用福利模板编辑modal
 * @param id 主键
 */
function editCommonTemp(id){
	//模态框显示
	$("#editCommonTemp").modal("show");
	var row = $('#commonTempTable').bootstrapTable('getRowByUniqueId', id);
	$("#commonTempId").val(id);
	//$("#platNature").val(row['platNature']);
	$("#platNatureSel3").select2("val",row['platNature']);
	$("#newServiceGold").val(row['newServiceGold']);
	$("#rResourceAmount").val(row['rResourceAmount']);
	$("#rResourceRatio").val(row['rResourceRatio']);
	$("#singleChargeRatio").val(row['singleChargeRatio']);
	$("#topCharge").val(row['topCharge']);
	$("#topGoldDay").val(row['topGoldDay']);
	$("#topHoldGold").val(row['topHoldGold']);
	$("#topInternalNumber").val(row['topInternalNumber']);
	$("#addTimeLimit").val(row['addTimeLimit']);
}

/**
 * 保存编辑通用福利模板
 * @param obj
 */
function saveCommonTemp(obj){
	$('#editForm').validate();
	if(!$('#editForm').valid()){
	    return;
	}
	var data = {
			id : $("#commonTempId").val(),
			platNature : $("#platNatureSel3").select2('val'),
			newServiceGold : $("#newServiceGold").val(),
			RResourceAmount : $("#rResourceAmount").val(),
			RResourceRatio : $("#rResourceRatio").val(),
			singleChargeRatio : $("#singleChargeRatio").val(),
			topCharge : $("#topCharge").val(),
			topGoldDay : $("#topGoldDay").val(),
			topHoldGold : $("#topHoldGold").val(),
			topInternalNumber : $("#topInternalNumber").val(),
			addTimeLimit : $("#addTimeLimit").val(),
			timestamp:$.now()
	}
	bootbox.confirm("确认修改吗？",function(result){
		if(result){
			$(obj).button('loading');
			$.ajax({
		        url: BASE+"/welfare/common/saveTempData",
		        type: "get",
		        data: data,
		        dataType: "json",
		        success: function (result) {
		        	$(obj).button('reset');
		        	if(result.success){
		        		var row = $('#commonTempTable').bootstrapTable('getRowByUniqueId', data.id);
		                
		                row['platNature'] =data.platNature;
		                row['newServiceGold'] = data.newServiceGold;
		                row['rResourceAmount'] =  data.RResourceAmount;
		                row['rResourceRatio'] = data.RResourceRatio;
		                row['singleChargeRatio'] =data.singleChargeRatio;
		                row['rResourceRatio1'] = data.RResourceRatio+"%";
		                row['singleChargeRatio1'] =data.singleChargeRatio+"%";
		                row['topCharge'] = data.topCharge;
		                row['topGoldDay'] =  data.topGoldDay;
		                row['topHoldGold'] = data.topHoldGold;
		                row['topInternalNumber'] =  data.topInternalNumber;
		                row['addTimeLimit'] = data.addTimeLimit;
		        		
		        		$('#commonTempTable').bootstrapTable('updateByUniqueId', {  
		        			    id: data.id,  
		        			    row:row
		        		});
		        		//隐藏模态框
		        		$("#editCommonTemp").modal("hide");
		        	}else{
		        		bootbox.alert("error");
		        	}
		        },
			    error:function(XMLHttpRequest, textStatus, errorThrown){
			    	$(obj).button('reset');
			    	bootbox.alert("error");
			    }
		    });
		}
	})
	
}


function updatePlatNature(){
	var data = {
			pId : $("#platSel2").select2('val'),
			platNature : $("#platNatureSel1").select2('val'),
			timestamp : $.now()
	};
	bootbox.confirm("确认修改吗？",function(result){
		if(result){
			$("#updateNature").button('loading');
			$.ajax({
		        url: BASE+"/welfare/common/updatePlatNature",
		        type: "get",
		        data: data,
		        dataType: "json",
		        success: function (result) {
		        	$("#updateNature").button('reset');
		        	if(result.success){
		        		bootbox.alert("修改成功");
		        		$("#commonPlatTable").bootstrapTable('refresh');
		        	}else{
		        		bootbox.alert("error");
		        	}
		        },
			    error:function(XMLHttpRequest, textStatus, errorThrown){
			    	$("#updateNature").button('reset');
			    	bootbox.alert("network error");
			    }
		    });
		}
	})
	
}

function updatePlatStatus(id){
	var row = $('#commonPlatTable').bootstrapTable('getRowByUniqueId', id);
	var data = {
			id : id,
			status : row['status'] == 0 ?1:0,
			timestamp : $.now(),
			platNature : row['platNature'],
			newServiceGold : row['newServiceGold'],
			RResourceAmount : row['rResourceAmount'],
			RResourceRatio : row['rResourceRatio'],
			singleChargeRatio : row['singleChargeRatio'],
			topCharge : row['topCharge'],
			topGoldDay : row['topGoldDay'],
			topHoldGold : row['topHoldGold'],
			topInternalNumber : row['topInternalNumber'],
			addTimeLimit : row['addTimeLimit'],
			goldPoolCategory : row['goldPoolCategory'],
			platName : row['platName'],
			pid : row['pid']
	};
	
	$.ajax({
        url: BASE+"/welfare/common/updatePlatStatus",
        type: "get",
        data: data,
        dataType: "json",
        success: function (result) {
        	if(result.success){
        		row['status1'] = data.status == 0 ? "正常" : "暂停";
        		row ['status'] = data.status;
        		var operateType = data.status == 0 ? "暂停" : "启动";
        		row['operate'] = "<a title='' href=\"javascript:updatePlatStatus('"+id+"')\">"+operateType+'</a>';
        		$('#commonPlatTable').bootstrapTable('updateByUniqueId', {  
    			    id: id,  
    			    row:row
        		});
        	}else{
        		alert("error");
        	}
        },
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	alert("network error");
	    }
    });
}

function updatePlatData(){
	$('#platTempForm').validate();
	if(!$('#platTempForm').valid()){
	    return;
	}
	var selecteds = $('#commonPlatTable').bootstrapTable('getSelections');
	var ids = new Array();
	for(var i = 0; i<selecteds.length;i++){
		ids[i] = selecteds[i].id;
	}
	var data = {
			ids : ids.join(","),
			timestamp : $.now(),
			newServiceGold : $("#editNewService").val(),
			RResourceAmount : $("#editRRmb").val(),
			RResourceRatio : $("#editRRatio").val(),
			singleChargeRatio : $("#editChargeRatio").val(),
			topCharge : $("#editTopCharge").val(),
			topGoldDay : $("#editTopGold").val(),
			topHoldGold : $("#editHoldGold").val(),
			topInternalNumber : $("#editTopNum").val(),
			addTimeLimit : $("#editTopLimit").val(),
			goldPoolCategory : $("#goldPoolSelect1").select2('val'),
			pid : $("#platSel1").select2('val')
	};
	bootbox.confirm("确认修改吗？",function(result){
		if(result){
			$("#updatePData").button('loading');
			$.ajax({
		        url: BASE+"/welfare/common/updatePlatData",
		        type: "get",
		        data: data,
		        dataType: "json",
		        success: function (result) {
		        	$("#updatePData").button('reset');
		        	if(result.success){
		        		bootbox.alert("修改成功");
		        		$('#commonPlatTable').bootstrapTable('refresh');
		        	}else{
		        		bootbox.alert("error");
		        	}
		        },
			    error:function(XMLHttpRequest, textStatus, errorThrown){
			    	$("#updatePData").button('reset');
			    	bootbox.alert("network error");
			    }
		    });
		}
	})
	
}

function search(){
	var pid = $("#platSel3").select2("val");
	var status = $("#statusSelect").select2('val');
	var goolPoolCategory = $("#goldPoolSelect2").select2('val');
	var platNature = $("#platNatureSel2").select2('val');
	var url = BASE+"/welfare/common/platData?pid="+pid
	+"&status="+status
	+"&goolPoolCategory="+goolPoolCategory
	+"&platNature="+platNature;
	$('#commonPlatTable').bootstrapTable('refresh', {  
	    "url":url
	});
}

function searchLog(){
	//获取table options
	var options = $('#commonSettingLogTable').bootstrapTable("getOptions");
	var pid = $("#platSel3").select2("val");
	var goolPoolCategory = $("#goldPoolSelect2").select2('val');
	var platNature = $("#platNatureSel2").select2('val');
	var startTime = $("#startDatePicker").val();
	var endTime = $("#endDatePicker").val();
	var url = BASE+"/welfare/common/settingLogData?pid="+pid
	+"&goolPoolCategory="+goolPoolCategory
	+"&platNature="+platNature
	+"&startTime="+startTime
	+"&endTime="+endTime;
	+"&limit="+options.pageSize
	+"&offset=0";
	$('#commonSettingLogTable').bootstrapTable('refresh', {  
	    "url":url
	});
	//initTable(url);
/*	$('#commonSettingLogTable').bootstrapTable('refresh', {  
	    "url":url
	});
	var options1 = $('#commonSettingLogTable').bootstrapTable("getOptions");
	console.log(options1);
	$('#commonSettingLogTable').bootstrapTable('refreshOptions', options1);*/

}

function addplatTempFormValidate(){
    $("#platTempForm").validate({
        rules: {
        	editNewService: {
                required: true,
                digits: true,
                min: 0
            },
            editRRmb: {
                required: true,
                digits: true,
                min: 0
            },
            editRRatio: {
                required: true,
                digits: true,
                min: 0
            },
            editChargeRatio: {
                required: true,
                digits: true,
                min: 0
            },
            editTopCharge: {
                required: true,
                digits: true,
                min: 0
            },
            editTopGold: {
                required: true,
                digits: true,
                min: 0
            },editHoldGold: {
                required: true,
                digits: true,
                min: 0
            },editTopNum: {
                required: true,
                digits: true,
                min: 0
            },editTopLimit: {
                required: true,
                digits: true,
                min: 0
            }
        },
        messages: {
        	editNewService: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editRRmb: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editRRatio: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editChargeRatio: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editTopCharge: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editTopGold: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editHoldGold: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editTopNum: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},editTopLimit: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	}
        }
    });
}

function addEditFormValidate(){
    $("#editForm").validate({
        rules: {
        	newServiceGold: {
                required: true,
                digits: true,
                min: 0
            },
            rResourceAmount: {
                required: true,
                digits: true,
                min: 0
            },
            rResourceRatio: {
                required: true,
                digits: true,
                min: 0
            },
            singleChargeRatio: {
                required: true,
                digits: true,
                min: 0
            },
            topCharge: {
                required: true,
                digits: true,
                min: 0
            },
            topGoldDay: {
                required: true,
                digits: true,
                min: 0
            },topHoldGold: {
                required: true,
                digits: true,
                min: 0
            },topInternalNumber: {
                required: true,
                digits: true,
                min: 0
            },addTimeLimit: {
                required: true,
                digits: true,
                min: 0
            }
        },
        messages: {
        	newServiceGold: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},rResourceAmount: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},rResourceRatio: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},singleChargeRatio: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},topCharge: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},topGoldDay: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},topHoldGold: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},topInternalNumber: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	},addTimeLimit: {
        		required:"不能为空",
        		digits:"请输入正确的数字",
        		min:"不能小于0"
        	}
        }
    });
}
