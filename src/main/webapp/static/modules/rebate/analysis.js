function dealData(res){
	$.each(res,function(index,value){
		res[index]['sumNumRatio1'] = value['sumNumRatio']+"%";
		res[index]['timesRatio1'] = value['timesRatio']+"%";
		res[index]['countRoleIdRatio1'] = value['countRoleIdRatio']+"%";
	})
	return res;
}

function search(){
	var url = BASE + "/rebate/analysis/analysisData?goodsName="+$("#goodsName").val()
				
				+"&pid="+$("#platSel2").select2('val');
	if($("#goodsId").val()){
		url = url + "&goodsId="+$("#goodsId").val();
	}
	if($("#startDatePickerDay").val()){
		url = url+"&startTime="+$("#startDatePickerDay").val();
	}
	if($("#endDatePickerDay").val()){
		url = url+"&endTime="+$("#endDatePickerDay").val();
	}
	
	$("#analysisTable").bootstrapTable('refresh',{
		url:url
	});
}