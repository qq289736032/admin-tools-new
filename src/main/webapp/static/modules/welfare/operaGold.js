function dealgrantGoldLogData(res){
	$.each(res.rows,function(index,value){
		res.rows[index]['createDate1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(value['createDate']));
	})
	return res;
}

function search(){
	
	var url = BASE+"/welfare/goldPool/grantGoldLogData?" +
			"pid="+$("#platSel2").select2('val') +
			"&serverId="+$("#serverSelect2").select2('val');
			
	if($("#roleName2").val()){
		url = url+"&roleName="+$("#roleName2").val();
	}
	if($("#userId2").val()){
		url = url + "&userId="+$("#userId2").val();
	}
	if($("#startDatePicker").val()){
		url = url + "&startTime="+$("#startDatePicker").val();
	}
	if($("#endDatePicker").val()){
		url = url + "&endTime="+$("#endDatePicker").val();
	}
			
	$('#grantGoldTable').bootstrapTable("refresh",{
		"url":url
	});
	
}