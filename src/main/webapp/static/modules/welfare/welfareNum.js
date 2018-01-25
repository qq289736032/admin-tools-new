function dealNumData(res){
	var fontStop = "<font color='red'>封停</font>";
	$.each(res.rows,function(index,value){
		
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
		
		res.rows[index]['status1'] = value['status'] == 0 ? "正常" : fontStop;
		res.rows[index]['operate'] = 
			value['status'] == 1 ?"":
					"<a title='封禁' href=\"javascript:updateStatus('"+value['id']+"',false)\">"+'封禁</a>';
	})
	return res;
}

function dealNumSeniorData(res){
	var fontStop = "<font color='red'>封停</font>";
	$.each(res.rows,function(index,value){
		
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
		
		res.rows[index]['status1'] = value['status'] == 0 ? "正常" : fontStop;
		res.rows[index]['operate'] = 
			value['status'] == 1 ?"<a title='解封' href=\"javascript:updateStatus('"+value['id']+"',true)\">"+'解封</a>':
					"<a title='封禁' href=\"javascript:updateStatus('"+value['id']+"',true)\">"+'封禁</a>';
	})
	return res;
}

function dealNumLogData(res){
	var fontStop = "<font color='red'>封停</font>";
	$.each(res.rows,function(index,value){
		
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
		switch(value['editType']){
		case 0:res.rows[index]['editType1'] ="添加";break;
		case 1:res.rows[index]['editType1'] =fontStop;break;
		case 2:res.rows[index]['editType1'] ="解封";break;
		case 3:res.rows[index]['editType1'] ="删除";break;
		default:res.rows[index]['editType1'] ="unknown";break;
		}
			
	})
	return res;
}

/**
 * 检查角色名
 */
function checkRoleName(){
	var data = {
			pid:$("#platSel1").select2('val'),
			serverId:$("#serverSelect1").select2('val'),
			roleName:$("#roleName").val(),
			timestamp:$.now()
	}
	$("#check").button('loading');
	$.ajax({
        url: BASE+"/welfare/num/checkRoleName",
        type: "get",
        data: data,
        dataType: "json",
        success: function (result) {
        	$("#check").button('reset');
        	if(result){
        		$("#roleId").val(result.roleId);
        		$("#userId").val(result.userId);
        	}else{
        		bootbox.alert("error");
        	}
        },
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	$("#check").button('reset');
	    	bootbox.alert("network error");
	    }
    });
}

function addWelfareNum(){
	$("#roleId").removeAttr("disabled");
	$("#userId").removeAttr("disabled");
	$('#numForm').validate();
	var flag = $('#numForm').valid();
	$("#roleId").attr("disabled","disabled");
	$("#userId").attr("disabled","disabled");
	if(!flag){
	    return;
	}
	var data = {
			pid:$("#platSel1").select2('val'),
			serverId:$("#serverSelect1").select2('val'),
			serverName:$("#serverSelect1").select2('data').text,
			roleId:$("#roleId").val(),
			userId:$("#userId").val(),
			roleName:$("#roleName").val(),
			usePeople:$("#usePeople").val(),
			usePeoplePost:$("#usePeoplePost").val(),
			passageway:passageway,
			platName:$("#platSel1").select2('data').text
	}
	bootbox.confirm("添加后将受到所有内部号限制，是否确认添加",function(result){
		if(result){
			$("#add").button('loading');
			$.ajax({
		        url: BASE+"/welfare/num/insertNum",
		        type: "get",
		        data: data,
		        dataType: "json",
		        success: function (result) {
		        	$("#add").button('reset');
		        	if(result.success){
		        		bootbox.alert("添加成功");
		        		$('#welfareNumTable').bootstrapTable('refresh');
		        	}else{
		        		if(result.error==0){
		        			bootbox.alert("添加失败,超出内部号添加时间限制");
		        		}else if(result.error==1){
		        			bootbox.alert("添加失败,超出内部号添加数量限制");
		        		}else if(result.error==-1){
		        			bootbox.alert("添加失败,数据异常");
		        		}else if(result.error==2){
		        			bootbox.alert("超出角色充值上限");
		        		}else if(result.error==3){
		        			bootbox.alert("该角色已经是内部号");
		        		}
		        	}
		        },
			    error:function(XMLHttpRequest, textStatus, errorThrown){
			    	$("#add").button('reset');
			    	bootbox.alert("network error");
			    }
		    });
		}
	})

}

function search(){
	//var options = $('#welfareNumTable').bootstrapTable("getOptions");
	var url = BASE+"/welfare/num/numData?" +
			"pid="+$("#platSel2").select2('val') +
			"&serverId="+$("#serverSelect2").select2('val');
			/*"&limit="+options.pageSize+
			"&offset=0";*/
	if($("#roleName2").val()){
		url = url+"&roleName="+$("#roleName2").val();
	}
	if($("#userId2").val()){
		url = url + "&userId="+$("#userId2").val();
	}
			
	$('#welfareNumTable').bootstrapTable("refresh",{
		"url":url
	});
}
function updateStatus(id,isSenior){
	var row = $('#welfareNumTable').bootstrapTable('getRowByUniqueId', id);
	var data = {
			id:id,
			roleName:row['roleName'],
			roleId:row['roleId'],
			userId:row['userId'],
			usePeople:row['usePeople'],
			usePeoplePost:row['usePeoplePost'],
			status:row['status'] == 0?1:0,
			pid:row['pid'],
			serverId:row['serverId'],
			platName:row['platName'],
			serverName:row['serverName']
	}
	
	$.ajax({
        url: BASE+"/welfare/num/updateStatus",
        type: "get",
        data: data,
        dataType: "json",
        success: function (result) {
        	if(result){
        		bootbox.alert("更新成功");
        		row['status'] = data.status;
        		row['status1'] = data.status == 0 ? "正常" : "封停";
        		if(isSenior){
        			row['operate'] = data.status == 1 ?"<a title='解封' href=\"javascript:updateStatus('"+id+"',"+isSenior+")\">"+'解封</a>':
						"<a title='封禁' href=\"javascript:updateStatus('"+id+"',"+isSenior+")\">"+'封禁</a>';
        		}else{
        			row['operate'] = data.status == 1 ?"":
						"<a title='封禁' href=\"javascript:updateStatus('"+id+"',"+isSenior+")\">"+'封禁</a>';
        		}
        		
        		$('#welfareNumTable').bootstrapTable('updateByUniqueId', {  
    			    id: id,  
    			    row:row
        		});
        	}else{
        		bootbox.alert("更新失败");
        	}
        },
	    error:function(XMLHttpRequest, textStatus, errorThrown){
	    	bootbox.alert("network error");
	    }
    });
}

function searchNumLog(){
	//var options = $('#numLogTable').bootstrapTable("getOptions");
	var url = BASE+"/welfare/num/numLogData?"+
	"platName="+$("#platSel2").select2('val') +
	"&serverId="+$("#serverSelect2").select2('val')+
	"&editType="+$("#editTypeSel").select2('val')+
	"&startTime="+$("#startDatePicker").val()+
	"&endTime="+$("#endDatePicker").val();
	/*"&limit="+options.pageSize+
	"&offset=0";*/
	$('#numLogTable').bootstrapTable('refresh',{
		"url":url
	})
}

function deleteNum(){
	var selecteds = $('#welfareNumTable').bootstrapTable('getSelections');
	if(!selecteds.length>0){
		bootbox.alert("请选择要删除的福利号");
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
	bootbox.confirm("删除后福利号不受内部号限制、不可发放元宝,是否删除",function(result){
		if(result){
			$.ajax({
		        url: BASE+"/welfare/num/deleteNum",
		        type: "get",
		        data: data,
		        dataType: "json",
		        success: function (result) {
		        	if(result.success){
		        		search();
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

function addNumFormValid(){
    $("#numForm").validate({
        rules: {
        	roleId: {
                required: true
            },
            userId: {
                required: true
            },
            usePeople: {
                required: true
            },
            usePeoplePost: {
                required: true
            },
            roleName:{
            	required: true
            }
        },
        messages: {
        	roleId: {
        		required:"不能为空"
        	},userId: {
        		required:"不能为空"
        	},
        	usePeople: {
        		required:"不能为空"
        	},usePeoplePost: {
        		required:"不能为空"
        	},roleName:{
            	required: "不能为空"
            }
        }
    });
}