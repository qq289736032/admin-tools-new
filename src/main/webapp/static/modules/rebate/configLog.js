function dealDayLogData(res){
	$.each(res.rows, function(index, value) {
		res.rows[index]['createTime1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(res.rows[index]['createDate']));
	});
	return res;
}

function searchDayLog(){
	var startTime = $("#startDatePickerDay").val();
	var endTime = $("#endDatePickerDay").val();
	var createName = $("#dayCreateName").val();
	var url = BASE+"/rebate/config/keepDayLogData?" +
	"createName="+createName;
	if(startTime){
		url = url+"&startTime="+startTime;
	}
	if(endTime){
		url = url+"&endTime="+endTime;
	}
	$('#dayTable').bootstrapTable("refresh",{
		"url":url
	});
}

function dealRatioLogData(res){
	$.each(res.rows, function(index, value) {
		res.rows[index]['createTime1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(res.rows[index]['createDate']));
	});
	return res;
}

function searchRatioLog(){
	var startTime = $("#startDatePickerRatio").val();
	var endTime = $("#endDatePickerRatio").val();
	var createName = $("#ratioCreateName").val();
	var url = BASE+"/rebate/config/rebateRatioLogData?" +
	"createName="+createName+
	"&editType="+$("#ratioEditSel").select2('val');
	if(startTime){
		url = url+"&startTime="+startTime;
	}
	if(endTime){
		url = url+"&endTime="+endTime;
	}
	$('#ratioLogTable').bootstrapTable("refresh",{
		"url":url
	});
}

function dealGoodsLogData(res){
	$.each(res.rows, function(index, value) {
		res.rows[index]['createTime1'] = getDate("yyyy-MM-dd hh:mm:ss",new Date(res.rows[index]['createDate']));
		res.rows[index]['goodsProp1'] = value['goodsProp']==0?"绑定":"不绑定";
		switch(value['exchangeLimit']){
		case 0:res.rows[index]['exchangeLimit1'] ="无";break;
		case 1:res.rows[index]['exchangeLimit1'] ="单角色每月上限";break;
		case 2:res.rows[index]['exchangeLimit1'] ="单角色累积上限";break;
		default:res.rows[index]['exchangeLimit1'] ="unknown";break;
		}
		res.rows[index]['platName'] = res.rows[index]['platName'] ==""?"全平台":res.rows[index]['platName'];
		
	});
	return res;
}

function searchGoodsLog(){
	var startTime = $("#startDatePickerGoods").val();
	var endTime = $("#endDatePickerGoods").val();
	var createName = $("#goodsCreateName").val();
	var url = BASE+"/rebate/config/rebateGoodsLogData?" +
	"createName="+createName+
	"&editType="+$("#goodsEditSel").select2('val')+
	"&goodsProp="+$("#goodsProp").select2('val')+
	"&goodsName="+$("#goodsName").val()+
	"&goodsId="+$("#goodsId").val();
	if(startTime){
		url = url+"&startTime="+startTime;
	}
	if(endTime){
		url = url+"&endTime="+endTime;
	}
	
	$('#goodsLogTable').bootstrapTable("refresh",{
		"url":url
	});
}