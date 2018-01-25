function loadResource() {
	$.ajax({
		url : BASE + "/welfare/goldPool/resourceData",
		type : "get",
		data : {
			pid : $("#platSel2").select2('val'),
			serverId : $("#serverSelect2").select2('val'),
			timestamp : $.now(),
			goldPoolCategory : goldPoolCategory
		},
		dataType : "json",
		success : function(result) {
			if (result) {
				$("#topGoldDay").html(result.topGoldDay + "元宝");
				$("#topCharge").html(result.topCharge + "元");
				$("#chargeSumOfDay").html(result.chargeSumOfDay+"元");// 本服今日充值
				$("#chargeSum").html(result.chargeSum+"元");// 本服总充值
				$("#newServiceGold").html(result.newServiceGold + "元宝");// 新服资源
				$("#goldSum").html(result.goldSum+ "元宝");// 本服总资源
				$("#drSum").html(result.drSum + "元宝");// 大R资源
				$("#issuedResource").html(result.issuedResource+ "元宝");// 本服已发资源
				$("#singleSum").html(result.singleSum + "元宝");// 单服充值后续资源
				$("#surplus").html("<font color='red'>"+result.surplus+" 元宝</font>");// 剩余资源
				
				$("#RResourceAmount").html(result.RResourceAmount+ "元");
				$("#topCharge").html(result.topCharge+ "元");
				$("#topGoldDay").html(result.topGoldDay+ "元宝");
				$("#topHoldGold").html(result.topHoldGold+ "元宝");
				$("#topInternalNumber").html(result.topInternalNumber);
				$("#addTimeLimit").html(result.addTimeLimit);

			}
		}
	});
}

function grantGold() {
	var selecteds = $('#welfareNumTable').bootstrapTable('getSelections');
	if(!selecteds.length>0){
		bootbox.alert("请选择福利号");
		return;
	}
	var ids = new Array();
	var serverIds = [];
	var flag = true;
	for (var i = 0; i < selecteds.length; i++) {
		ids[i] = selecteds[i].id;
		serverIds.push(selecteds[i].pid + "-" + selecteds[i].serverId);
		flag = selecteds[i].status==0;
		if(!flag){
			break;
		}
	}
	if(!flag){
		bootbox.alert("请选择未被封停的福利号");
		return;
	}
	if(isDif(serverIds)){
		//有多个不同服务器福利号一起发放的话增加了不少复杂度  暂时只支持批量操作同一个服务器的福利号
		bootbox.alert("暂不支持批量发放不同服务器的福利号元宝");
		return;
	}
	var pid = selecteds[0].pid;
	var serverId = selecteds[0].serverId;
	var data = {
		ids : ids.join(","),
		gold : $("#goldNum").val(),
		passageway : passageway,
		timestamp : $.now(),
		pid : pid,
		serverId : serverId
	};
	bootbox.confirm("确认发放吗？",function(result){
		if(result){
			$("#grant").button('loading');
			$.ajax({
				url : BASE + "/welfare/goldPool/grantGold",
				type : "get",
				data : data,
				dataType : "json",
				success : function(result) {
					$("#grant").button('reset');
					if (result.success) {
						if(result.data){
							var msg = "";
							$.each(result.data,function(key,value){
								var error = "";
								switch(value){
									case 0:error = "成功";break;
									case 1:error = "超出每日限额";break;
									case 2:error = "超出角色持有上限";break;
									case 3:error = "实际充值额度超过充值上限";break;
									default:error = "未知错误";break;
								}
								msg = msg+"角色:"+key+"   "+error+"<br/>";
							})
							bootbox.alert(msg);
							loadResource();
							$("#welfareNumTable").bootstrapTable("refresh");
						}else{
							$("#welfareNumTable").bootstrapTable("refresh");
							bootbox.alert("发放成功");
							loadResource();
							
						}

					}else{
						if(result.error==0){
							bootbox.alert("发放失败,奖金池没有启动")
						}else if(result.error==1){
							bootbox.alert("发放失败,剩余资源不足")
						}else if(result.error==2){
							
						}
					}
				}
			});
		}
	})

}
