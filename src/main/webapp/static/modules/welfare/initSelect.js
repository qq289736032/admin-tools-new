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
	$(id).select2();
}

function initAllPlat(id,isAll){
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

function initAllServerSelect(obj,pid){
	if(pid==""){
		var option = '<option value="">全部</option>';
		$(obj).append(option);
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
        		if(isAll){
        			var option = '<option value="">全部</option>';
        			$(obj).append(option);
        		}
        		$.each(result,function(index,value){
        			var option = '<option value="'+value.pid+'">'+value.name+'</option>';
        			$(obj).append(option);
        		})
        		$(obj).select2();
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
 * 平台不需要“全部”选项，服务器也不需要
 * @param obj
 */
function initPlatSelect2(platObj,serverObj){
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
        		initServerSelect2(serverObj,firstPid);
        	}
        }
    });
}

function initServerSelect2(obj,pid){
	$.ajax({
        url: BASE+"/tools/gameArea/getServerListByPid",
        type: "get",
        data: {timestamp:$.now(),pid:pid},
        dataType: "json",
        async:false,
        success: function (result) {
        	if(result){
        		$(obj).empty();
        		var firstServerId = "";
        		$.each(result,function(index,value){
        			
        			var option = '<option value="'+value.world_id+'">'+value.world_name+'</option>';
        			$(obj).append(option);
            		if(index==0){
            			firstServerId = value.world_id;
            		}
        		})
        		
        		$(obj).select2();
        	}
        }
    });
}


