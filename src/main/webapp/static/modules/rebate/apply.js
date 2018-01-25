function dealselfData(res){
	$.each(res,function(index,value){
		//res.rows[index]['surplusNum'] = value['sumMoney'];
		$("#allGold").html(value['allowGold']);
	})
	return res;
}

function dealRatioData(res) {
	return res;
}

function dealGoodsData(res){
	$.each(res, function(index, value) {
		
		res[index]['goodsProp1'] = value['goodsProp']==0?"绑定":"不绑定";
		switch(value['exchangeLimit']){
			case 0:res[index]['exchangeLimit1'] ="无";break;
			case 1:res[index]['exchangeLimit1'] ="单角色每月上限";break;
			case 2:res[index]['exchangeLimit1'] ="单角色累积上限";break;
			default:res[index]['exchangeLimit1'] ="unknown";break;
		}
		
		var id = value['id'];
		res[index]['currentNum'] = "<a  href=\"javascript:beforeUpdateGoods('" + id
				+ "')\">修改</a>" + "&nbsp&nbsp<a  href=\"javascript:delGoods('"+ id + "')\">删除</a>";
		res[index]['currentNum'] = "<input id='"+id+"input'value='0' onkeyup=\"calculation('"+ id + "')\"></input>";
		res[index]['sumGold'] = "<span id='"+id+"span'>0</span>";
		
		
	});
	return res;
}

function calculation(id){
	//输入框为空 自动给0
	if($("#"+id+"input").val()==''){
		$("#"+id+"input").val(0);
	}
	//替换掉非数字字符
	$("#"+id+"input").val($("#"+id+"input").val().replace(/\D/g, ""));
	//$("#"+id+"input").val($("#"+id+"input").val().replace(^[1-9]\d*$, ""));
	//数字以0开头砍掉0
	if($("#"+id+"input").val().length==2&&$("#"+id+"input").val().indexOf('0')==0){
		var a = $("#"+id+"input").val().substring(1);
		$("#"+id+"input").val(a)
	}
	var row = $("#goodsSpecTable").bootstrapTable('getRowByUniqueId', id);
	var price = row['goodsPrice'];
	var num = $("#"+id+"input").val();
	$("#"+id+"span").html(num*price);
	
	var data = $("#goodsSpecTable").bootstrapTable('getData');
	var spendGold = 0;
	$.each(data,function(index,value){
		var a = $("#"+value['id']+"span").html();
		if(a){
			spendGold = parseInt(spendGold)+parseInt(a);
		}
	})
	var allGold = $("#allGold").html();
/*	if(spendGold>allGold){
		//var a = parseInt(allGold)-parseInt(spendGold);
		$("#"+id+"input").val(parseInt(allGold/price));
		$("#"+id+"span").html(parseInt(allGold/price)*price);
		var spendGold = 0;
		$.each(data,function(index,value){
			var a = $("#"+value['id']+"span").html();
			if(a){
				spendGold = parseInt(spendGold)+parseInt(a);
			}
		})
	}*/
	if(row['topNum']-row['usedNum']<$("#"+id+"input").val()){
		var n = parseInt(row['topNum']-row['usedNum'])<0?0:parseInt(row['topNum']-row['usedNum']);
		$("#"+id+"input").val(n);
		
		$("#"+id+"span").html(n*price);
		var spendGold = 0;
		$.each(data,function(index,value){
			var a = $("#"+value['id']+"span").html();
			if(a){
				spendGold = parseInt(spendGold)+parseInt(a);
			}
		})
	}
	
	if(spendGold>allGold){
		//这一次能用多少元宝  = 总元宝-（总花费-这一次的花费）
		var g = allGold-(spendGold-($("#"+id+"input").val()*price))
		$("#"+id+"input").val(parseInt(g/price));
		$("#"+id+"span").html((parseInt(g/price))*price);
		var spendGold = 0;
		$.each(data,function(index,value){
			var a = $("#"+value['id']+"span").html();
			if(a){
				spendGold = parseInt(spendGold)+parseInt(a);
			}
		})
	}
	
	$("#spendGold").html(spendGold);
}

function sendGoods(obj){
	$('#emailForm').validate();
	if(!$('#emailForm').valid()){
	    return;
	}
	if($("#spendGold").html()==0){
		bootbox.alert("请选择需要发送的物品");
		return;
	}
	console.log($("#spendGold").html());
	console.log($("#allGold").html());
	console.log($("#allGold").html()==0);
	console.log(parseInt($("#spendGold").html())>parseInt($("#allGold").html()));
	if($("#allGold").html()==0||parseInt($("#spendGold").html())>parseInt($("#allGold").html())){
		bootbox.alert("当前可用元宝不足");
		return;
	}
	var tableData = $("#goodsSpecTable").bootstrapTable('getData');
	var attachments = "";
	$.each(tableData,function(index,value){
		var num = $("#"+value['id']+"input").val();
		if(num!=0){
			var goodsProp = value['goodsProp']==0?1:0;
			attachments = attachments+value['goodsId']+","+num+","+goodsProp+","+value['goodsPrice']+","+value['goodsName']+";"
		}
	})
	var data = {
			timestamp : $.now(),
			roleId:param['id'],
			serverId:param['serverId'],
			serverName:param['serverName'],
			content:$("#emailText").val(),
			title:$("#emailTitle").val(),
			attachments:attachments,
			pid:param['pid'],
			platName:param['platName'],
			userId:param['userId']
	}
	$(obj).button('loading');
	$.ajax({
		url : BASE + "/rebate/self/submit",
		type : "get",
		data :data,
		dataType : "json",
		success : function(result) {
			$(obj).button('reset');
			if(result.success){
				bootbox.alert("提交成功");
				$("#selfTable").bootstrapTable('refresh');
				$("#spendGold").html("0");
				$('#goodsSpecTable').bootstrapTable('refresh');
			}else{
				bootbox.alert("提交失败,请重新提交");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$(obj).button('reset');
			bootbox.alert("network error");
		}
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
				$("#day1").html(result.day);
				var url = BASE +"/rebate/self/chargeRebateData?roleId="+param['id']+"&day="+result.day;
				initChargeDetailTable(url);
			} 
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			bootbox.alert("network error");
		}
	});
}

function dealDetailData(res){
	$.each(res,function(index,value){
		res[index]['statisticDate1'] = getDate("yyyy-MM-dd",new Date(res[index]['statisticDate']));
		res[index]['sumMoney'] = res[index]['sumMoney']/10;
	})
	return res;
}

function addEmailFormValidate(){
    $("#emailForm").validate({
        rules: {
        	emailTitle: {
                required: true,
                minlength: 0,
                maxlength: 14
            },
            emailText: {
                required: true,
                minlength: 0,
                maxlength: 100
            }
        },
        messages: {
        	emailTitle: {
        		required:"不能为空",
        		maxlength:"请输入一个长度最多是14的字符串"
        	},emailText: {
        		required:"不能为空",
        		maxlength:"请输入一个长度最多是100的字符串"
        	}
        }
    });
}
