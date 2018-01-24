<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
	<title><spring:message code='str1274'/></title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		var key, lastValue = "", nodeList = [];
		var tree, setting = {view:{selectedMulti:false},check:{enable:"${checked}",nocheckInherit:true},
				data:{simpleData:{enable:true}},
				view:{
					fontCss:function(treeId, treeNode) {
						return (!!treeNode.highlight) ? {"font-weight":"bold"} : {"font-weight":"normal"};
					}
				},
				callback:{beforeClick:function(id, node){
					if("${checked}" == "true"){
						tree.checkNode(node, !node.checked, true, true);
						return false;
					}
				}, 
				onDblClick:function(){
					top.$.jBox.getBox().find("button[value='ok']").trigger("click");
					//alert($("input[type='text']", top.mainFrame.document).val());
					//$("input[type='text']", top.mainFrame.document).focus();
				}}};
		$(document).ready(function(){
			$.get("${ctx}${url}${fn:indexOf(url,'?')==-1?'?':'&'}&extId=${extId}&module=${module}&t="+new Date().getTime(), function(zNodes){
				// <spring:message code='str1275'/>
				tree = $.fn.zTree.init($("#tree"), setting, zNodes);
				
				// <spring:message code='str1276'/>
				var nodes = tree.getNodesByParam("level", 0);
				for(var i=0; i<nodes.length; i++) {
					tree.expandNode(nodes[i], true, false, false);
				}
				// <spring:message code='str1277'/>
				var ids = "${selectIds}".split(",");
				for(var i=0; i<ids.length; i++) {
					var node = tree.getNodeByParam("id", ids[i]);
					if("${checked}" == "true"){
						try{tree.checkNode(node, true, true);}catch(e){}
						tree.selectNode(node, false);
					}else{
						tree.selectNode(node, true);
					}
				}
			});
			key = $("#key");
			key.bind("focus", focusKey).bind("blur", blurKey).bind("change keydown cut input propertychange", searchNode);
		});
	  	function focusKey(e) {
			if (key.hasClass("empty")) {
				key.removeClass("empty");
			}
		}
		function blurKey(e) {
			if (key.get(0).value === "") {
				key.addClass("empty");
			}
			searchNode(e);
		}
		function searchNode(e) {
			// <spring:message code='str1278'/>
			var value = $.trim(key.get(0).value);
			
			// <spring:message code='str1279'/>
			var keyType = "name";
			if (key.hasClass("empty")) {
				value = "";
			}
			
			// <spring:message code='str1280'/>
			if (lastValue === value) {
				return;
			}
			
			// <spring:message code='str1281'/>
			lastValue = value;
			
			// <spring:message code='str1282'/>
			if (value === "") {
				return;
			}
			updateNodes(false);
			nodeList = tree.getNodesByParamFuzzy(keyType, value);
			updateNodes(true);
		}
		function updateNodes(highlight) {
			for(var i=0, l=nodeList.length; i<l; i++) {
				nodeList[i].highlight = highlight;				
				tree.updateNode(nodeList[i]);
				tree.expandNode(nodeList[i].getParentNode(), true, false, false);
			}
		}
		function search() {
			$("#search").slideToggle(200);
			$("#txt").toggle();
			$("#key").focus();
		}
	</script>
</head>
<body>
	<div style="position:absolute;right:8px;top:5px;cursor:pointer;" onclick="search();">
		<i class="icon-search"></i><label id="txt"><spring:message code='str1283'/></label>
	</div>
	<div id="search" class="control-group hide" style="padding:10px 0 0 15px;">
		<label for="key" class="control-label" style="float:left;padding:5px 5px 3px;"><spring:message code='str47'/></label>
		<input type="text" class="empty" id="key" name="key" maxlength="50" style="width:180px;">
	</div>
	<div id="tree" class="ztree" style="padding:15px 20px;"></div>
</body>
