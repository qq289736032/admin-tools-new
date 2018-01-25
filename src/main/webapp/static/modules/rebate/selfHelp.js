function dealSelfHelpData(res){
	res.sort(
			firstBy(function(v1,v2){return v2.pid - v1.pid})
			.thenBy(function(v1,v2){return v2.serverId - v1.serverId})
			.thenBy(function(v1,v2){return v2.allowGold - v1.allowGold})
			.thenBy(function(v1,v2){return v2.usedGold - v1.usedGold})
			);
	$.each(res,function(index,value){
		//res.rows[index]['surplusNum'] = value['sumMoney'];
		res[index]['edit'] = "<a href='"+BASE+"/rebate/self/apply?id="+value['roleId']+"&pid="+value['pid']+"&userId="+value['userId']+"&platName="+value['pid']+"&serverName="+value['serverName']+"&serverId="+value['serverId']+"' target='mainFrame'>申请返利</a>";
	})
	return res;
}

function search(){
	var url = BASE + "/rebate/self/selfHelpData?pid="+$("#platSel2").select2('val')+
	"&serverId="+$("#serverSelect2").select2('val')+
	"&roleName="+$("#roleName").val()+
	"&roleId="+$("#roleId").val()+
	"&userId="+$("#userId").val();

	$("#selfHelpTable").bootstrapTable('refresh',{
		url:url
	});
}

function loadKeepDayData() {
	$.ajax({
		url : BASE + "/rebate/config/keepDayData",
		type : "get",
		data : {
			timestamp : $.now()
		},
		dataType : "json",
		success : function(result) {
			if (result.day) {
				$("#day").html(result.day);
			} else {
				$("#day").html(0);
				
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			bootbox.alert("network error");
		}
	});
}