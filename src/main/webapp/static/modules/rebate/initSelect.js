/**
 * 初始化下拉框指定选中值
 * @param id
 * @param selectData
 * @param firstPid
 */
function baseSpecifySelected(id,selectData,firstPid){
	$(id).select2('destroy');
	$(id).select2({
		width:"220px",
		allowClear:true,
		data:selectData
	});
	$(id).select2("val",firstPid);
}

/**
 * 初始化下拉框不指定选中值
 * @param id
 * @param selectData
 */
function baseDefaultSelected(id,selectData){
	$(id).select2('destroy');
	$(id).select2({
		width:"220px",
		allowClear:true,
		data:selectData
	});
}

function getAllPlat(){
	$.ajax({
        url: BASE+"/tools/gamePlatform/allPlat",
        type: "get",
        data: {timestamp:$.now()},
        dataType: "json",
        success: function (result) {
        	if(result){
        		var selectData = [{id:"",text:"全部"}];
        		$.each(result,function(index,value){
        			var select = {};
            		select.id = value.pid;
            		select.text = value.name;
            		selectData.push(select);
        		})
        		baseDefaultSelected("#platSel1",selectData);
        		baseDefaultSelected("#platSel2",selectData);
        		baseDefaultSelected("#platSel3",selectData);
        	}
        }
    });
}


function initPlat(id,isAll){
	$.ajax({
        url: BASE+"/tools/gamePlatform/allPlat",
        type: "get",
        data: {timestamp:$.now()},
        dataType: "json",
        success: function (result) {
        	if(result){
        		$(id).empty();
        		if(isAll){
        			var option = '<option value="">全部</option>';
        			$(id).append(option);
        		}
        		$.each(result,function(index,value){
        			var option = '<option value="'+value.pid+'">'+value.name+'</option>';
        			$(id).append(option);
        		})
        		$(id).select2();
        	}
        }
    });
}

/**
 * 需要“全部”选项
 * @param obj
 */
function initPlatSelect0(platObj,serverObj){
	$.ajax({
        url: BASE+"/tools/gamePlatform/allPlat",
        type: "get",
        data: {timestamp:$.now()},
        dataType: "json",
        async:false,
        success: function (result) {
        	if(result){
        		$(platObj).empty();
        		var option = '<option value="">全部</option>';
        		$(platObj).append(option);
        		
        		$.each(result,function(index,value){
        			var option = '<option value="'+value.pid+'">'+value.name+'</option>';
        			$(platObj).append(option);
        		})
        		$(platObj).select2();
        		initServerSelect0(serverObj,"");
        	}
        }
    });
}

function initServerSelect0(obj,pid){
	if(pid==""){
		var option = '<option value="">全部</option>';
		$(obj).append(option);
		$(obj).select2();
		return;
	}
	$.ajax({
        url: BASE+"/tools/gameArea/getServerListByPid",
        type: "get",
        data: {timestamp:$.now(),pid:pid},
        dataType: "json",
        async:false,
        success: function (result) {
        	if(result){
        		$(obj).empty();
        		var option = '<option value="">全部</option>';
    			$(obj).append(option);
        		
        		$.each(result,function(index,value){
        			var option = '<option value="'+value.world_id+'">'+value.world_name+'</option>';
        			$(obj).append(option);
        		})
        		$(obj).select2();
        	}
        }
    });
}

/**
 * 平台不需要“全部”选项，服务器需要
 * @param obj
 */
function initPlatSelect1(platObj,serverObj){
	$.ajax({
        url: BASE+"/tools/gamePlatform/allPlat",
        type: "get",
        data: {timestamp:$.now()},
        dataType: "json",
        async:false,
        success: function (result) {
        	if(result){
        		$(platObj).empty();
        		var firstPid = "";
        		$.each(result,function(index,value){
        			var option = '<option value="'+value.pid+'">'+value.name+'</option>';
        			$(platObj).append(option);
            		if(index==0){
            			firstPid = value.pid;
            		}
        		})
        		$(platObj).select2();
        		initServerSelect1(serverObj,firstPid);
        	}
        }
    });
}

function initServerSelect1(obj,pid){
	$.ajax({
        url: BASE+"/tools/gameArea/getServerListByPid",
        type: "get",
        data: {timestamp:$.now(),pid:pid},
        dataType: "json",
        async:false,
        success: function (result) {
        	if(result){
        		$(obj).empty();
        		var option = '<option value="">全部</option>';
    			$(obj).append(option);
        		
        		$.each(result,function(index,value){
        			
        			var option = '<option value="'+value.world_id+'">'+value.world_name+'</option>';
        			$(obj).append(option);
        		})
        		
        		$(obj).select2();
        	}
        }
    });
}

/**
 * 初始化物品属性下拉框
 */
function initGoodsPropSelect(id,isAll){
	var selectData = [{id:"0",text:"绑定"},{id:"1",text:"不绑定"}];
	if(isAll){
		selectData = [{id:"",text:"全部"},{id:"0",text:"绑定"},{id:"1",text:"不绑定"}];
	}
	
	baseDefaultSelected(id,selectData);
}

/**
 * 初始化兑换限制下拉框
 */
function initExchangeLimitSelect(id){
	var selectData = [{id:"0",text:"无"},{id:"1",text:"单角色每月上限"},{id:"2",text:"单角色累计上限"}];
	baseDefaultSelected(id,selectData);
}