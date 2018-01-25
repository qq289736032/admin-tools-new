function dealRankPlatData(res){
	$.each(res,function(index,value){
		res[index]['status1'] = value['status'] == 0 ? "正常" : "封停";
		res[index]['index'] = index+1;
	})
	return res;
}

function dealRankIndData(res){
	res.sort(function(a,b){
		return b.sumYb-a.sumYb;
	})
	$.each(res,function(index,value){
		res[index]['status1'] = value['status'] == 0 ? "正常" : "封停";
		res[index]['index'] = index+1;
		res[index]['tradingTimes'] = 0;
		res[index]['marketTimes'] = 0;
	})
	return res;
}

function searchRank(){
	var url = BASE+"/welfare/goldPool/rankData?" +
	"pid="+$("#platSel2").select2('val') +
	"&serverId="+$("#serverSelect2").select2('val')+
	"&type="+type+
	"&startTime="+$("#startDatePicker").val()+
	"&endTime="+$("#endDatePicker").val();
	$('#rankPlatTable').bootstrapTable("refresh",{
		"url":url
	});
}