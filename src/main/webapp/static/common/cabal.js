var BASE;
var str1649;
var xtts;
var str1650;
var str173;
var str175;
var str171;
var str174;
var str172;
var  str1651;
var  str1569;
var str1652;
var str1189;


$(document).ready(function () {
    //绑定复选框(全选、取消全选)事件
    $("#checkAllOrNot").click(function () {
        var checked = this.checked;
        $(":checkbox[disabled!='disabled']").each(function () {
            $(this).attr("checked", checked);
        });
    });

    //绑定点击复选框，则更改tr背景颜色的事件
    //$(":checkbox[name='recordIds']").each(function (i) {
    //    $(this).click(function () {
    //        var currentTR = $(this).parents("tr");
    //        currentTR.css("background-color", this.checked ? "#F0F0F0" : "#FFF");
    //    });
    //});
});

// 引入js和css文件
function include(id, path, file){
	if (document.getElementById(id)==null){
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++){
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + path + name + "'";
            document.write("<" + tag + (i==0?" id="+id:"") + attr + link + "></" + tag + ">");
        }
	}
}

// 打开一个窗体
function windowOpen(url, name, width, height){
	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	window.open(url ,name , options);
}

// 显示加载框
function loading(mess){
	top.$.jBox.tip.mess = null;
	top.$.jBox.tip(mess,'loading',{opacity:0});
}

// 确认对话框
function confirmx(mess, href){
	top.$.jBox.confirm(mess,xtts,function(v,h,f){
		if(v=='ok'){
			loading(str1569);
			location = href;
		}
	},{buttonsFocus:1});
	top.$('.jbox-body .jbox-icon').css('top','55px');
	return false;
}


function ajaxConfirm(mess,url,params){
    var confirmDialog = top.dialog({
        title: str171,
        content: mess,
        okValue: str173,
        ok: function () {
            this.title(str174);
            _remoteCall(url,params,function(result){
                if(result.success){
                    tips(result.data)
                }
            })
        },
        cancelValue: str175,
        cancel: function () {}
    });
    $("td.ui-dialog-body", top.document).css('height','auto');
    confirmDialog.showModal();
}

function ajaxRequest(mess, url, params){
    top.$.jBox.confirm(mess,xtts,function(v,h,f){
        if(v=='ok'){
            _remoteCall(url,params,function(result){
                if(result.success){
                    tips(result.data)
                }
            })
        }
    },{buttonsFocus:1});
    top.$('.jbox-body .jbox-icon').css('top','55px');
    return false;
}

function tips(msg){
    top.$.jBox.tip(msg, 'success');
}

$(document).ready(function() {
	//所有下拉框使用select2
	$("select").select2();
	$('.fancybox').fancybox();
//    $("#inputForm").validate();
});

function _remoteCall(url, params, callback) {
    $.ajax({
        type : 'POST',
        url: url,
        dataType: 'json',
        data:params,
        before:function(){
          loading(str1189)
        },
        success:function(data){
            callback(data)
        }
    })
}


function page(n,s){
    $("#pageNo").val(n);
    $("#pageSize").val(s);
//    $("#searchForm").attr("action",action);
    $("#searchForm").submit();
    return false;
}


function submitCheckedRecordIds(url){
    var isChecked = false;
    $("input[name='recordIds']").each(function () {
        if (this.checked) {
            isChecked = true;
        }
    });
    if (isChecked == false) {
        tips(str1650);
        return;
    }
 /*   top.$.jBox.confirm(str173,xtts,function(v,h,f){
        if(v=="ok"){
            $("#tableForm").attr("action",url);
            $("#tableForm").submit();
        }
        return true;  
    },{buttonsFocus:1} }
    );*/
    var submit = function (v, h, f) {  
    	if (v == true)  {
    		 $("#tableForm").attr("action",url);
             $("#tableForm").submit();
    	}
    	
    	return true;  
    	};  
    	// 自定义按钮  
    	$.jBox.confirm(str173, xtts, submit, { buttons: { 'ok': true, 'cancel': false} });  
    	//}
    top.$('.jbox-body .jbox-icon').css('top','55px');

}

//ajax 提交验证参数
function submitCheckedRecordIdsAjax(url,redirectUrl){
    var isChecked = false;
    $("input[name='recordIds']").each(function () {
        if (this.checked) {
            isChecked = true;
        }
    });
    if (isChecked == false) {
        tips(str1650);
        return;
    }
    top.$.jBox.confirm(str173+"?",xtts,function(v,h,f){
        if(v=="ok"){
        	_remoteCall(url,$('#tableForm').serialize(),function(result){
        		   if(result.success){
        			   top.$.jBox.tip(result.data);
                        if(redirectUrl == undefined || redirectUrl == ''){
                            location.href = url.substring(0,url.lastIndexOf('/'));
                        }else{
                            location.href = redirectUrl;
                        }
                   }else{
                	   top.$.jBox.tip(result.data);
                   }
        	});
        }
    },{buttonsFocus:1});
    top.$('.jbox-body .jbox-icon').css('top','55px');

}




function submitCheckedRecordIdsWithPrompt_2(url){
    var isChecked = false;
    $("input[name='recordIds']").each(function () {
        if (this.checked) {
            isChecked = true;
        }
    });
    if (isChecked == false) {
        tips(str1650);
        return;
    }

    var content ="<form id='inputForm' class='form-horizontal'>"
    content = content + "<div class='control-group'><label class='control-label' style='width: 100px;'>"+str1671+":</label><div class='controls'><input type='text' id='reason' name='reason' value='' class='required' /></div></div></div>";
    content = content + "<div class='control-group'><label class='control-label' style='width: 100px;'>"+str1270+":</label><div class='controls'><input type='text' id='expireTime' name='expireTime' onfocus='WdatePicker({minDate:\"%y-%M-%d %H:%m:%s\",dateFmt:\"yyyy-MM-dd H:mm:ss\"});'/></div></div></div></form>"

    var d = top.dialog({
        title: str1651,
        fixed:true,
        //lock:true,
        content: content,
        ok: function () {
            var flag = false;
            var value = $(window.parent.document).find("#reason").val();
            var expireTime = $(window.parent.document).find("#expireTime").val();
            var confirmDialog = top.dialog({
                title: str171,
                content: str172,
                okValue: str173,
                ok: function () {
                    this.title(str174);
                    if(value != ''){
                        var form = $("#tableForm");
                        form.attr('action', url);
                        var msg = $("#msg").val(value);
                        var et = $("#expireTime").val(expireTime);
                        form.attr('msg', msg);
                        form.attr('expireTime',et);
                        form.submit();


                        d.close();
                        d.remove();
                    }else{
                        tips(str1651);
                    }
                },
                cancelValue: str175,
                cancel: function () {}
            });
            $("td.ui-dialog-body", top.document).css('height','auto');
            confirmDialog.showModal();
            return false;
        }
    });
    $("td.ui-dialog-body", top.document).css('height','auto');
    d.showModal();
}



function submitCheckedRecordIdsWithPrompt(url){
    var isChecked = false;
    $("input[name='recordIds']").each(function () {
        if (this.checked) {
            isChecked = true;
        }
    });
    if (isChecked == false) {
        tips(str1650);
        return;
    }
   top.$.jBox.confirm(str173,xtts,function(v,h,f){
        if(v=="ok"){
            var html = "<div style='padding:10px;'>"+str1671+"：<input type='text' id='reason' name='reason' class='required' /></div>";
            var submit = function (v, h, f) {
                if (f.reason == '') {
                    top.$.jBox.tip(str1651, 'error', { focusId: "reason" }); // 关闭设置 reason 为焦点
                    return false;
                }
                var form = $("#tableForm");
                form.attr('action', url);
                var msg = $("#msg").val(encodeURI(f.reason));
                form.attr('msg', msg);
                form.submit();

                return true;
            };
            top.$.jBox(html, { title: str1651,submit: submit,buttons: { 'ok': true}});
        }
    },{buttons: { 'ok': 'ok' ,'cancel':'cancel'}});

}
function doExport(action){
   /* top.$.jBox.confirm(str1652+"？",xtts,function(v,h,f){
        if(v=="ok"){
            $("#searchForm").attr("action",action);
            $("#searchForm").submit();
        }
    },{buttonsFocus:1});*/
	 var submit = function (v, h, f) {  
	    	if (v == true)  {
	    		 $("#searchForm").attr("action",action);
	             $("#searchForm").submit();
	    	}
	    	
	    	return true;  
	    	};  
	    	// 自定义按钮  
	$.jBox.confirm(str1652, xtts, submit, { buttons: {'ok': true, 'cancel': false} });  
    top.$('.jbox-body .jbox-icon').css('top','55px');
}

//多选服务器弹窗，去掉了合过服的服
function openSeverDialog(){
    top.$.jBox.open("iframe:"+BASE+"/tools/gameArea/openGameServerDialog", "选择游戏服务器(多选)",1000,$(top.document).height()-240,{
        buttons:{str173:"ok", "关闭":true}, bottomText:"多选服务器弹窗。",submit:function(v, h, f){
            if (v=="ok"){
                h.find("iframe")[0].contentWindow.sure();
                var selectedGameServerId = h.find("iframe")[0].contentWindow.selectedGameServerIds;
                $("#serverIds").val(selectedGameServerId);
                return true;
            }
        }, loaded:function(h){
            $(".jbox-content", top.document).css("overflow-y","hidden");
        }
    });
}


function selectSingleServer(){
    top.$.jBox.open("iframe:"+BASE+"/tools/gameArea/selectSingleGameServer", "选择游戏服务器(单选)",810,$(top.document).height()-240,{
        buttons:{str173:"ok", "关闭":true}, bottomText:"通过选择平台，查询出游戏服务器。",submit:function(v, h, f){
            var selectedGameServerId = h.find("iframe")[0].contentWindow.selectedGameServerId;
            var selectedGameServerName = h.find("iframe")[0].contentWindow.selectedGameServerName;
            var selectedGamePlatformName = h.find("iframe")[0].contentWindow.selectedGamePlatformName;
            if (v=="ok"){

                $("#serverIds").val(selectedGameServerId);
                $("#platformName").val(selectedGamePlatformName);
                return true;
            }
        }, loaded:function(h){
            $(".jbox-content", top.document).css("overflow-y","hidden");
        }
    });
}

function selectSingleGameServer(url){
    $.ajax({
        type : "POST",
        //url: "${ctx}/tools/gameArea/selectSingleGameServer",
        dataType: 'text',
        before:function(){
            loading(str1189)
        },
        success:function(data){

            var d = top.dialog({
                title: '单选服务器列表-(红色表示服务器已停)',
                lock:true,
                width:'100%',
                height:'100%',
                content: data,
                ok: function(){
                    top.myCallback();
                },
                cancel: function(){}
            });

            d.showModal();
        }
    })
}


function openGameUserDialog(){
    top.$.jBox.open("iframe:"+BASE+"/game/role/gameRoleDialog", "游戏角色列表",810,$(top.document).height()-240,{
        buttons:{str173:"ok", "关闭":true},bottomText:"查询条件至少输入一项才可以查询",submit:function(v, h, f){

            if (v=="ok"){

                h.find("iframe")[0].contentWindow.sure();
                var roleIds = h.find("iframe")[0].contentWindow.roleIds;
                var roleNames = h.find("iframe")[0].contentWindow.roleNames;

                $("#receiverNames").val(roleNames);
                $("#receiverUserIds").val(roleIds);

                return true;
            }
        }, loaded:function(h){
            $(".jbox-content", top.document).css("overflow-y","hidden");
        }
    });
}


function openGoodsDialog(targetId){
    top.$.jBox.open("iframe:"+BASE+"/tools/gameEmail/goodsDialog?targetId="+targetId, "物品列表",1200,$(top.document).height()-240,{
        buttons:{str173:"ok", "关闭":true},bottomText:"查询条件必须填写",submit:function(v, h, f){

            if (v=="ok"){

                var goodsId = h.find("iframe")[0].contentWindow.goodsId;
                var goodsName = h.find("iframe")[0].contentWindow.goodsName;

                $("#" + targetId).val(goodsName);
                $("#id" + targetId).val(goodsId);
                $("#count" + targetId).val(1);


                return true;
            }
        }, loaded:function(h){
            $(".jbox-content", top.document).css("overflow-y","hidden");
        }
    });
}


function info(data){
    top.$.jBox.info(data, '内容');
}


//导出xls文件
function exportXls(url) {
    if (url == null || url == "") {
        url = "xls";
    }
    var oldUrl = $("form:first").attr("action");
    $("form:first").attr("action", url);
    $("form:first").submit();
    $("form:first").attr("action", oldUrl);
}

//查看附件-解析游戏数据格式
function viewAttach(id){
    var content;
    var contentHtml = $("#hide_"+id).html();
    var obj = eval('(' + contentHtml + ')');
    content = "<table id='dialogTable' class='table table-striped table-bordered table-condensed'>";
    content = content + "<thead> <tr><th>物品名称</th><th>是否绑定</th><th>数量</th><th>有效期</th><th>附加属性</th></tr></thead><tbody>";
    for(var i = 0; i < obj.length; i ++){
        content = content + "<tr><td>"+obj[i].templateId+"</td><td>"+obj[i].bind+"</td><td>"+obj[i].count+"</td><td>"+obj[i].endTime+"</td><td>"+obj[i].attributesInfo+"</td></tr>";
    }
    content = content + "</tbody></table>";
    var d = top.dialog({
        title: '附件明细',
        lock:true,
        zIndex :999999999,
        width:'100%',
        height:'100%',
        content: content
    });
    $("td.ui-dialog-body", top.document).css('height','auto');
    d.showModal();
}


function viewAttachments(data){
    var obj = eval('(' + data + ')');
    var content = "<table id='dialogTable' class='table table-striped table-bordered table-condensed'>";
    content = content + "<thead> <tr><th>物品名称</th><th>是否绑定</th><th>数量</th><th>有效期</th></tr></thead><tbody>";
    for(var i = 0; i < obj.length; i ++){
        content = content + "<tr><td>"+obj[i].templateId+"</td><td>"+obj[i].bind+"</td><td>"+obj[i].count+"</td><td>"+obj[i].endTime+"</td></tr>";
    }
    content = content + "</tbody></table>";
    var d = top.dialog({
        title: '附件明细',
        lock:true,
        width:'100%',
        height:'100%',
        content: content
    });

    d.showModal();
}

function comfirmDialog(calblack){
    var confirmDialog = top.dialog({
        title: str171,
        content: str172,
        okValue: str173,
        ok: function () {
            this.title(str174);
            callback;
            return false;
        },
        cancelValue: str175,
        cancel: function () {}
    });
    confirmDialog.showModal();
}


function removeCountry(url, id){
	ajaxConfirm('确认删除？',url);
    $("#"+id).remove();
}

function addCountry(url){
    $.ajax({
        type : 'POST',
        url: url,
        dataType: 'json',
        //	data:params,
        before:function(){
            loading(str1189)
        },
        success:function(data){

            var content = "<input id=\"btnAddCountry\" class=\"btn btn-primary\" type=\"button\" value=\"添加国家\" onclick=\"top.addCountry('${ctx}/tools/crossArea/addCountry')\"/><br></br> <table id='dialogTable' class='table table-striped table-bordered table-condensed'>";
            content = content + "<thead> <tr><th>国家ID</th><th>国家名</th><th>国王名</th><th>创建时间</th><th>操作</th></tr></thead><tbody>";
            for(var i = 0; i < data.length; i ++){
                var url = "${ctx}/tools/crossArea/removeCountry?countryId=" + data[i].id;
                content = content + "<tr id="+i+"><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].kingName+"</td><td>"+data[i].addTime+"</td><td><a onclick=\"top.removeCountry('"+url+"');\">删除</a></td></tr>";
            }
            content = content + "</tbody></table>";
            var d = top.dialog({
                title: '国家列表',
                lock:true,
                width:'100%',
                height:'100%',
                content: content
            });

            d.showModal();
        }
    })
}

function submitGameUpdate(url){
    ajaxRequest("确定保存嘛?",url,{notice_log:$("#notice_log").val(),notice_content:$("#notice_content").val()})
}


function showContent(url) {
    $.ajax({
        type : "POST",
        url: url,
        dataType: 'text',
        //	data:params,
        before:function(){
            loading(str1189)
        },
        success:function(data){
            var d = top.dialog({
                title: str1649,
                width:'100%',
                height:'97%',
                content: data,
                ok: function(){
                    top.myCallback();
                },
                cancel: function(){}
            });

            d.showModal();
        }
    })
}
