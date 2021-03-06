<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code='str1139'/></title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<%@include file="/WEB-INF/views/include/dialog.jsp"%>

<%--<script type="text/javascript" src="${ctxStatic}/common/ajaxfileupload.js"></script>--%>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#title").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										var serverId = $("#serverIds").val();
										if (serverId == "") {
											tips("<spring:message code='str1105'/>!");
											return;
										}
										var isGlobal = $("#isGlobal").val();
										if (isGlobal == 1) {
											top.$.jBox.confirm("<spring:message code='str1322'/>","<spring:message code='str45'/>",function(v,h,f){
											        if(v=="ok"){
											        	form.submit();
											        }
											    },{buttonsFocus:1});
											    top.$('.jbox-body .jbox-icon').css('top','55px'); 
										}else{
											loading('<spring:message code='str1106'/>...');
											form.submit();
										}
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("<spring:message code='str1107'/>");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									},
									rules : {
										title : {
											required : true,
											maxlength : 14
										},
										content : {
											required : true,
											maxlength : 100
										},
										delayHours : {
											digits : true
										},
										jb : {
											digits : true,
											range : [ 0, 900000000 ]
										},
										yb : {
											digits : true,
											range : [ 0, 900000000 ]
										}
									}
								});
				$("#btnImport").click(function() {
					var serverId = $("#serverIds").val();
					if (serverId == "") {
						tips("<spring:message code='str1140'/>");
						return;
					} else {
						$.jBox($("#importBox").html(), {
							title : "<spring:message code='str1141'/>",
							buttons : {
								"<spring:message code='str876'/>" : true
							},
							//bottomText : "<spring:message code='str1142'/>5M<spring:message code='str1143'/>xls<spring:message code='str1144'/>xlsx<spring:message code='str1145'/>"
						});
					}
				});
				function openGoods(targetId) {
					openGoodsDialog(targetId);
				}
				/*  for(var i = 0; i <= 4; i ++){
				     $("#overage").append("<input onclick=\"openGoodsDialog("+i+")\" id=\""+i+"\" readonly=\"readonly\" class=\"input-small\"> <input type=\"hidden\" name=\"goodsList["+i+"].id\" id=\"id"+i+"\">\n" +
				     "                <select name=\"goodsList["+i+"].binding\" class=\"input-small\">\n" +
				     "                    <option value=\"1\" ><spring:message code='str1146'/></option>\n" +
				     "                    <option value=\"0\" ><spring:message code='str1147'/></option>\n" +
				     "                </select>\n" +
				     "                <spring:message code='str440'/>:<input name=\"goodsList["+i+"].count\" id=\"count"+i+"\" class=\"input-small\"> (<spring:message code='str1148'/>)\n" +
				     "                <spring:message code='str1149'/>:<input name=\"goodsList["+i+"].expireTime\" id=\"expireTime"+i+"\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})\" class=\"input-medium\">(<spring:message code='str1148'/>)\n" +
				     "                <spring:message code='str1150'/>:<input name=\"goodsList["+i+"].attributesInfo\" class=\"input-xlarge\" id=\"attributesInfo"+i+"\">(<spring:message code='str1151'/>)<br/>");
				 } */

			});

	function UploadSuccess(result, status) {
		var success = result.success;
		if (success) {
			$('#importBox').hide();
		} else {
			$('#importBox').hide();
		}
		tips(result.data)

	}

	function ajaxFileUpload() {
		var serverId = $("#server_ids").val();
		if (serverId == "") {
			tips("<spring:message code='str1140'/>");
			return;
		}

		$.ajaxFileUpload({
			url : '${ctx}/tools/gameEmail/import',
			secureuri : false,
			fileElementId : 'uploadFile',
			dataType : 'json',
			success : function(data, status) {
				$('#receiverNames').val(decodeURIComponent(data));
			},
			error : function(data, status, e) {
				alert(e + "<spring:message code='str1152'/>~<spring:message code='str1153'/>");
			}
		})

		return false;

	}

	function toggleTrReceiverNames() {
		if ($("#isGlobal").val() == "1") {
			$('#trReceiverNames').hide();
			$('#receiverNames').val("");
		} else {
			$('#trReceiverNames').show();
		}
		$("#server_ids").val("");
	}

	function openServer() {
		if ($("#isGlobal").val() == "1") {
			$("#server_ids").val("");
			showContent('${ctx}/tools/gameArea/openGameServer');
		} else {
			showContent('${ctx}/tools/gameArea/singleGameServer');
		}
	}

	function openGameUser() {
		var serverId = $("#server_ids").val();
		if (serverId == "") {
			alert("<spring:message code='str1140'/>");
			return;
		}

		openGameUserDialog(serverId);
	}

	function batchAddGameUser() {
		var serverId = $("#server_ids").val();
		if (serverId == "") {
			alert("<spring:message code='str1140'/>");
			return;
		}
	}

	function openGoods(targetId) {
		openGoodsDialog(targetId);
	}
	var i = 1;
	function addItems() {
		$("#overage")
				.append(
						"<div id=\"row"+i+"\"><input onclick=\"openGoodsDialog("
								+ i
								+ ")\" id=\""
								+ i
								+ "\" readonly=\"readonly\" class=\"input-small\"> <input type=\"hidden\" name=\"goodsList["+i+"].id\" id=\"id"+i+"\">\n"
								+ "                <select name=\"goodsList["+i+"].binding\" class=\"input-small\">\n"
								+ "                    <option value=\"1\" ><spring:message code='str1146'/></option>\n"
								+ "                    <option value=\"0\" ><spring:message code='str1147'/></option>\n"
								+ "                </select>\n"
								+ "                <spring:message code='str440'/>:<input name=\"goodsList["+i+"].count\" id=\"count"+i+"\" class=\"input-small\"> (<spring:message code='str1148'/>)\n"
								+ "                <spring:message code='str1149'/>:<input name=\"goodsList["
								+ i
								+ "].expireTime\" id=\"expireTime"
								+ i
								+ "\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})\" class=\"input-medium\">(<spring:message code='str1148'/>)\n"
								+ "                <spring:message code='str1150'/>:<input name=\"goodsList["+i+"].attributesInfo\" class=\"input-xlarge\" id=\"attributesInfo"+i+"\">(<spring:message code='str1151'/>)<input id=\"deleteGoods"+i+"\" class=\"btn btn-primary\" type=\"button\" value=\"<spring:message code='str22'/>\" onclick=\"deleteItems("
								+i+");\"/><br/>");

		i++;
	}
	function deleteItems(trIndex) {
		//if(trIndex==1){
		// alert('<spring:message code='str1314'/>');
		// return
		//}
		$("#row" + (trIndex)).remove();
		trIndex--;
	}
</script>
</head>
<body>
	<%--<div id="importBox">--%>
	<%--<div id="importForm"--%>
	<%--style="padding-left:20px;text-align:center;" class="form-search"><br/>--%>
	<%--<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/><spring:message code='str1154'/>--%>
	<%--<input id="uploadButton" type="submit" onclick="ajaxFileUpload();" class="btn btn-primary" value="<spring:message code='str1155'/>" />--%>
	<%--&lt;%&ndash;<a href="${ctx}/tools/gameEmail/template"><spring:message code='str1156'/></a>&ndash;%&gt;--%>
	<%--</div>--%>
	<%--</div>--%>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/gameEmail/"><spring:message code='str1157'/></a></li>
		<shiro:hasPermission name="game.email.edit">
			<li class="active"><a
					href="${ctx}/tools/gameEmail/form?id=${gameEmail.id}"><c:if test="${ empty gameEmail.id}"><spring:message code='str1158'/></c:if><c:if test=" ${not empty gameEmail.id}"><spring:message code=' str1297'/></c:if><spring:message code='str1159'/></a></li>
 			<li><a href="${ctx}/tools/gameEmail/applyEmail"><spring:message code='str1160'/>(<spring:message code='str1161'/>)</a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="game.email.batchadd">
			<li><a href="${ctx}/tools/gameEmail/batchAdd"><spring:message code='str1162'/></a></li>
		</shiro:hasPermission>
		 <shiro:hasPermission name="game.email.name">
            <li><a href="${ctx}/tools/gameEmail/batchNameAdd"><spring:message code='str1162'/>(<spring:message code='str653'/>)</a></li>
        </shiro:hasPermission>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="gameEmail"
		action="${ctx}/tools/gameEmail/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />

		<div class="control-group">
			<label class="control-label"><spring:message code='str1163'/>:</label>
			<div class="controls">
				<select id="isGlobal" name="isGlobal"
					onchange="toggleTrReceiverNames()">
					<option value="1"><spring:message code='str1164'/></option>
					<option value="0"><spring:message code='str1165'/></option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str14'/>:</label>
			<div class="controls">
				<input id="serverIds" name="serverIds" readonly />
				<%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openServer()"></button>--%>
				<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>"
					onclick="openServer()">
			</div>
		</div>

		<div class="control-group" style="display: none" id="trReceiverNames">
			<label class="control-label"><spring:message code='str1166'/>:</label>
			<div class="controls">
				<button type="button" onclick="openGameUser()"><spring:message code='str1166'/></button>
				<br />
				<textarea id="receiverNames" rows="3" name="receiverNames"
					class="input-xxlarge required"></textarea>
				<span style="color: #FF003A;"> * <spring:message code='str1167'/></span>

				<textarea id="receiverUserIds" style="display: none"
					name="receiverUserIds" class="required" readonly></textarea>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message code='str1168'/>:</label>
			<div class="controls">
				<form:input path="title" id="title" name="title" htmlEscape="false"
					class="input-xxlarge {required:true,maxlength:14}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str718'/>:</label>
			<div class="controls">
				<form:textarea path="content" id="content" htmlEscape="false"
					rows="3" class="input-xxlarge {required:true,maxlength:100}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1169'/>:</label>
			<div class="controls">
				<form:input path="jb" id="jb" name="jb" htmlEscape="false"
					cssClass="input-large"></form:input>
				<span class="help-inline">(<spring:message code='str1170'/>:9<spring:message code='str1171'/>)</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1172'/>:</label>
			<div class="controls">
				<form:input path="yb" id="yb" name="yb" htmlEscape="false"
					cssClass="input-large"></form:input>
				<span class="help-inline">(<spring:message code='str1170'/>:9<spring:message code='str1171'/>)</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str1173'/>:</label>
			<div class="controls">
				<form:input path="delayHours" id="delayHours" name="delayHours"
					htmlEscape="false" cssClass="input-large"></form:input>
				<span class="help-inline">(<spring:message code='str1174'/>)</span>
			</div>
		</div>

		<div class="control-group">

			<label class="control-label"><spring:message code='str1175'/>:</label>
			<div class="controls" id="overage">

				<!--  <input onclick="openGoodsDialog(0)" id="0" readonly="readonly" class="input-small"> <input type="hidden" name="goodsList[0].id" id="id0" class="input-small">
               <select name="goodsList[0].binding" class="input-small">
                   <option value="1" ><spring:message code='str1146'/></option>
                    <option value="0" ><spring:message code='str1147'/></option>
                </select>
                <spring:message code='str440'/>:<input name="goodsList[0].count" id="count0" class="input-small"> (<spring:message code='str1148'/>)
             <spring:message code='str1149'/>:<input name="goodsList[0].expireTime" id="expireTime0" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" class="input-small"">(<spring:message code='str1148'/>)
             <spring:message code='str1150'/>:<input name="goodsList[0].content" id="content0"   class="input-xlarge"><br/> -->

				<%--<input onclick="openGoodsDialog(1)" id="1" readonly="readonly" class="input-small"> <input type="hidden" name="goodsList[1].id" id="id1">--%>
				<%--<select name="goodsList[1].binding" class="input-small">--%>
				<%--<option value="1" ><spring:message code='str1146'/></option>--%>
				<%--<option value="0" ><spring:message code='str1147'/></option>--%>
				<%--</select>--%>
				<%--<spring:message code='str440'/>:<input name="goodsList[1].count" id="count1" class="input-small"> (<spring:message code='str1148'/>)--%>
				<%--<spring:message code='str1149'/>:<input name="goodsList[1].expireTime" id="expireTime1" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" class="input-medium">(<spring:message code='str1148'/>)<br/>--%>

				<%--<input onclick="openGoodsDialog(2)" id="2" readonly="readonly" class="input-small"> <input type="hidden" name="goodsList[2].id" id="id2">--%>
				<%--<select name="goodsList[2].binding" class="input-small">--%>
				<%--<option value="1" ><spring:message code='str1146'/></option>--%>
				<%--<option value="0" ><spring:message code='str1147'/></option>--%>
				<%--</select>--%>
				<%--<spring:message code='str440'/>:<input name="goodsList[2].count" id="count2" class="input-small"> (<spring:message code='str1148'/>)--%>
				<%--<spring:message code='str1149'/>:<input name="goodsList[2].expireTime" id="expireTime2" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" class="input-medium">(<spring:message code='str1148'/>)<br/>--%>

				<%--<input onclick="openGoodsDialog(3)" id="3" readonly="readonly" class="input-small"> <input type="hidden" name="goodsList[3].id" id="id3">--%>
				<%--<select name="goodsList[3].binding" class="input-small">--%>
				<%--<option value="1" ><spring:message code='str1146'/></option>--%>
				<%--<option value="0" ><spring:message code='str1147'/></option>--%>
				<%--</select>--%>
				<%--<spring:message code='str440'/>:<input name="goodsList[3].count" id="count3" class="input-small"> (<spring:message code='str1148'/>)--%>
				<%--<spring:message code='str1149'/>:<input name="goodsList[3].expireTime" id="expireTime3" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" class="input-medium">(<spring:message code='str1148'/>)<br/>--%>

				<%--<input onclick="openGoodsDialog(4)" id="4" readonly="readonly" class="input-small"> <input type="hidden" name="goodsList[4].id" id="id4">--%>
				<%--<select name="goodsList[4].binding" class="input-small">--%>
				<%--<option value="1" ><spring:message code='str1146'/></option>--%>
				<%--<option value="0" ><spring:message code='str1147'/></option>--%>
				<%--</select>--%>
				<%--<spring:message code='str440'/>:<input name="goodsList[4].count" id="count4" class="input-small"> (<spring:message code='str1148'/>)--%>
				<%--<spring:message code='str1149'/>:<input name="goodsList[4].expireTime" id="expireTime4" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" class="input-medium">(<spring:message code='str1148'/>)<br/>--%>
			</div>
		</div>
		<div class="controls">
			<input id="addGoods" class="btn btn-primary" type="button" value="<spring:message code='operation.add'/>"
				onclick="addItems();" />
			<%--<input id="deleteGoods" class="btn btn-primary" type="button" value="<spring:message code='str22'/>" onclick="deleteItems();"/>--%>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="<spring:message code='str1176'/> <spring:message code='str1177'/>" />&nbsp; <input id="btnCancel" class="btn" type="button"
				value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)" />
		</div>
	</form:form>

</body>
</html>
