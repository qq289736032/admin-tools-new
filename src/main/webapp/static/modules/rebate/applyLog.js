function dealApplyLogData(res){
	$.each(res.rows,function(index,value){
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(res.rows[index]['createDate']));
		var flag = getDate("yyyy-MM-dd",new Date()) == getDate("yyyy-MM-dd",new Date(res.rows[index]['createDate']));
		var button = '<button type="button" class="btn btn-primary" onclick=\'getDetail("'+res.rows[index]['id']+'")\'><span class="glyphicon glyphicon-floppy-save"></span> 查看详情</button>';
		var status1 = "成功";
		if(flag&&res.rows[index]['status']==1){
			var button1 = '<button type="button" class="btn btn-primary" onclick=\'reSubmit("'+res.rows[index]['id']+'")\'><span class="glyphicon glyphicon-floppy-save"></span> 重新发送</button>';
			button = button+button1;
			status1 = "失败";
		}
		res.rows[index]['status1'] = status1;
		res.rows[index]['detail'] = button;
	})
	return res;
}

function getDetail(id){
	var row = $("#applyLogTable").bootstrapTable('getRowByUniqueId',id);
	$("#detailTitle").val(row['emailTitle']);
	$("#detailText").val(row['emailText']);
	var url = BASE + "/rebate/self/logDetailData?logId="+id;
	initDetailTable(url);
	$("#detailModal").modal('show');
	
}


function search(){
	var url = BASE + "/rebate/self/applyLogData?pid="+$("#platSel2").select2('val')+
	"&serverId="+$("#serverSelect2").select2('val')+
	"&roleName="+$("#roleName").val()+
	"&createName="+$("#createName").val();
	if($("#roleId").val()){
		url = url +"&roleId="+$("#roleId").val();
	}
	if($("#status").select2('val')){
		url = url+"&status="+$("#status").select2('val');
	}
	if($("#startDatePickerDay").val()){
		url = url+"&startTime="+$("#startDatePickerDay").val();
	}
	if($("#endDatePickerDay").val()){
		url = url+"&endTime="+$("#endDatePickerDay").val();
	}
	$("#applyLogTable").bootstrapTable('refresh',{
		url:url
	});
}

function reSubmit(id){
	var row = $("#applyLogTable").bootstrapTable('getRowByUniqueId', id);
	var attachments = row['attachments'];
	var logId = id;
	var content = row['content'];
	var title = row['title'];
	var roleId = row['roleId'];
	var serverId = row['serverId'];
	var data = {
			timestamp : $.now(),
			attachments:attachments,
			logId:logId,
			content:content,
			title:title,
			roleId:roleId,
			serverId:serverId
	}
	$.ajax({
		url : BASE + "/rebate/self/reSubmit",
		type : "get",
		data :data,
		dataType : "json",
		success : function(result) {
			if(result.success){
				bootbox.alert("发送成功");
				$("#applyLogTable").bootstrapTable('refresh');
			}else{
				bootbox.alert("发送失败");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			bootbox.alert("network error");
		}
	});
}