function dealNumData(res){
	$.each(res.rows,function(index,value){
		
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
		res.rows[index]['status1'] = value['status'] == 0 ? "正常" : "封停";
	})
	return res;
}

function dealServerData(res){
	$.each(res.rows,function(index,value){
		
		res.rows[index]['updateDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
		res.rows[index]['status1'] = value['status'] == 0 ? "生效" : "不生效";
		res.rows[index]['isInfluence1'] = value['isInfluence'] == 0 ?"是":"否";
	})
	return res;
}

function dealAccountLogData(res){
	$.each(res.rows,function(index,value){
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
	})
	return res;
}

function dealServerLogData(res){
	$.each(res.rows,function(index,value){
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
	})
	return res;
}

function search(){
	// var options =
	// $('#singleAccountConfigTable').bootstrapTable("getOptions");
	var url = BASE+"/welfare/num/numData?" +
			"pid="+$("#platSel2").select2('val') +
			"&serverId="+$("#serverSelect2").select2('val');
			/*
			 * "&limit="+options.pageSize+ "&offset=0";
			 */
	if($("#roleName2").val()){
		url = url+"&roleName="+$("#roleName2").val();
	}
	if($("#userId2").val()){
		url = url + "&userId="+$("#userId2").val();
	}
			
	$('#singleAccountConfigTable').bootstrapTable("refresh",{
		"url":url
	});
}

function searchServer(){
	// var options = $('#singleServerConfigTable').bootstrapTable("getOptions");
	var url = BASE+"/welfare/specConfig/serverConfigData?" +
			"pid="+$("#platSel3").select2('val') +
			"&serverId="+$("#serverSelect3").select2('val')+
			"&status="+$("#status").select2('val');
			/*
			 * "&limit="+options.pageSize+ "&offset=0";
			 */
			
	$('#singleServerConfigTable').bootstrapTable("refresh",{
		"url":url
	});
}
function updateTopCharge(type){
	var topCharge = $("#topCharge").val();
	if(type=="del"){
		topCharge = null;
	}
	var isInfluence = 0;
	if($("#topCheck").is(":checked")){
		isInfluence = 1;
	}
	
	var selecteds = $('#singleAccountConfigTable').bootstrapTable('getSelections');
	if(!selecteds.length>0){
		bootbox.alert("请选择要修改的配置");
		return;
	}
	var ids = new Array();
	for(var i = 0; i<selecteds.length;i++){
		ids[i] = selecteds[i].id;
	}
	var data = {
			ids : ids.join(","),
			isInfluence : isInfluence,
			topCharge : topCharge,
			timestamp : $.now()
	};
	bootbox.confirm("确认修改吗？",function(result){
		if(result){
		$("#u").button('loading');
	$.ajax({
        url: BASE+"/welfare/specConfig/updateTopCharge",
        type: "get",
        data: data,
        dataType: "json",
        success: function (result) {
        	$("#u").button('reset');
        	if(result){
        		bootbox.alert("修改成功");
        		$('#singleAccountConfigTable').bootstrapTable("refresh");
        	}else{
        		bootbox.alert("修改失败");
        	}
        },
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	$("#u").button('reset');
	    	bootbox.alert("network error");
	    }
    });
		}
	});
}

function updateConfig(){
	$('#singleServerForm').validate();
	if(!$('#singleServerForm').valid()){
	    return;
	}
	var isInfluence = 0;
	if($("#serverCheck").is(":checked")){
		isInfluence = 1;
	}
	var data = {
			pid : $("#platSel1").select2("val"),
			platName : $("#platSel1").select2("val"),
			serverId : $("#serverSelect1").select2("val"),
			newServiceGold : $("#editNewService").val(),
			RResourceAmount : $("#editRRmb").val(),
			RResourceRatio : $("#editRRatio").val(),
			singleChargeRatio : $("#editChargeRatio").val(),
			topCharge : $("#editTopCharge").val(),
			topGoldDay : $("#editTopGold").val(),
			topHoldGold : $("#editHoldGold").val(),
			topInternalNumber : $("#editTopNum").val(),
			addTimeLimit : $("#editTopLimit").val(),
			isInfluence : isInfluence,
			serverName:$("#serverSelect1").select2("data").text,
			timestamp : $.now()
	}
	$("#updateCon").button('loading');
	$.ajax({
        url: BASE+"/welfare/specConfig/insertSingleServerConfig",
        type: "get",
        data: data,
        dataType: "json",
        success: function (result) {
        	$("#updateCon").button('reset');
        	if(result){
        		bootbox.alert("保存成功");
        		$('#singleServerConfigTable').bootstrapTable("refresh");
        	}else{
        		bootbox.alert("保存失败");
        	}
        },
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	$("#updateCon").button('reset');
	    	bootbox.alert("network error");
	    }
    });
}

function deleteServerConfig(){
	
	
	var selecteds = $('#singleServerConfigTable').bootstrapTable('getSelections');
	if(!selecteds.length>0){
		bootbox.alert("请选择要删除的配置");
		return;
	}
	var ids = new Array();
	for(var i = 0; i<selecteds.length;i++){
		ids[i] = selecteds[i].id;
	}
	var data = {
			ids : ids.join(","),
			timestamp : $.now()
	};
	bootbox.confirm("确认删除吗？",function(result){
		if(result){
	$.ajax({
        url: BASE+"/welfare/specConfig/deleteServerConfig",
        type: "get",
        data: data,
        dataType: "json",
        success: function (result) {
        	if(result){
        		bootbox.alert("删除成功");
        		$('#singleServerConfigTable').bootstrapTable("refresh");
        	}else{
        		bootbox.alert("删除失败");
        	}
        },
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	bootbox.alert("network error");
	    }
    });
		}
	})
}

function addSingleServerFormValidate(){
    $("#singleServerForm").validate({
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

