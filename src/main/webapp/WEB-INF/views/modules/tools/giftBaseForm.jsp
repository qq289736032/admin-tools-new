<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1249'/></title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>

    <script type="text/javascript">

        $(document).ready(function() {
            $("#title").focus();
            $("#inputForm").validate({
                submitHandler: function(form){

                    loading('<spring:message code='str1106'/>...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("<spring:message code='str1107'/>");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });

        function openGoods(targetId){
            openGoodsDialog(targetId);
        }
		var trIndex = 1;
		function addItems(){
			$('#contentTable').append("<div id=\"row"+trIndex+"\"><input onclick=\"openGoodsDialog("
			+trIndex+")\" id=\""
			+trIndex+"\" readonly=\"readonly\"> <input type=\"hidden\" name=\"goodsList["
			+trIndex+"].id\" id=\"id"
			+trIndex+"\"><select name=\"goodsList["
			+trIndex+"].binding\" ><option value=\"1\" ><spring:message code='str1146'/></option><option value=\"0\" ><spring:message code='str1147'/></option></select> <spring:message code='str440'/>:<input name=\"goodsList["
			+trIndex+"].count\" id=\"count"
			+trIndex+"\"> (<spring:message code='str1148'/>) <spring:message code='str1149'/>:<input name=\"goodsList["
			+trIndex+"].expireTime\" id=\"expireTime"
			+trIndex+"\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})\">(<spring:message code='str1148'/>) <input id=\"deleteGoods\" class=\"btn btn-primary\" type=\"button\" value=\"<spring:message code='str22'/>\" onclick=\"deleteItems("
			+trIndex+");\"/> <br/>");
			trIndex ++;
		}
		
		function deleteItems(trIndex){
			//if(trIndex==1){
			// alert('<spring:message code='str1314'/>');
			// return
			//}
			$("#row"+(trIndex)).remove();
			trIndex --;
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
		<li><a href="${ctx}/tools/gift/giftBaseList"><spring:message code='str1394'/></a></li>
		<li class="active"><a href="${ctx}/tools/gift/form"><spring:message code='str1395'/></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gift" action="${ctx}/tools/gift/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1252'/>:</label>
            <div class="controls">
                <form:input path="name" htmlEscape="false"  class="input-xxlarge required"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><spring:message code='str718'/>:</label>
			<div class="controls">
                <form:textarea path="content" htmlEscape="false" rows="3" class="input-xxlarge required"/>
			</div>
		</div>

        <div class="control-group">

            <label class="control-label"><spring:message code='str1175'/>:</label>
            <div id="contentTable" class="controls">
				<div id="row0">
					<input onclick="openGoodsDialog(0)" id="0" readonly="readonly"> <input type="hidden" name="goodsList[0].id" id="id0">
					<select name="goodsList[0].binding" >
						<option value="1" ><spring:message code='str1146'/></option>
						<option value="0" ><spring:message code='str1147'/></option>
					</select>
					<spring:message code='str440'/>:<input name="goodsList[0].count" id="count0"> (<spring:message code='str1148'/>)
					<spring:message code='str1149'/>:<input name="goodsList[0].expireTime" id="expireTime0" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})">(<spring:message code='str1148'/>)

					<br/>
				</div>

                <%--<input onclick="openGoodsDialog(1)" id="1" readonly="readonly"> <input type="hidden" name="goodsList[1].id" id="id1">
                <select name="goodsList[1].binding" >
                    <option value="1" ><spring:message code='str1146'/></option>
                    <option value="0" ><spring:message code='str1147'/></option>
                </select>
                <spring:message code='str440'/>:<input name="goodsList[1].count" id="count1"> (<spring:message code='str1148'/>)
                <spring:message code='str1149'/>:<input name="goodsList[1].expireTime" id="expireTime1" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})">(<spring:message code='str1148'/>)<br/>

                <input onclick="openGoodsDialog(2)" id="2" readonly="readonly"> <input type="hidden" name="goodsList[2].id" id="id2">
                <select name="goodsList[2].binding" >
                    <option value="1" ><spring:message code='str1146'/></option>
                    <option value="0" ><spring:message code='str1147'/></option>
                </select>
                <spring:message code='str440'/>:<input name="goodsList[2].count" id="count2"> (<spring:message code='str1148'/>)
                <spring:message code='str1149'/>:<input name="goodsList[2].expireTime" id="expireTime2" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})">(<spring:message code='str1148'/>)<br/>

                <input onclick="openGoodsDialog(3)" id="3" readonly="readonly"> <input type="hidden" name="goodsList[3].id" id="id3">
                <select name="goodsList[3].binding" >
                    <option value="1" ><spring:message code='str1146'/></option>
                    <option value="0" ><spring:message code='str1147'/></option>
                </select>
                <spring:message code='str440'/>:<input name="goodsList[3].count" id="count3"> (<spring:message code='str1148'/>)
                <spring:message code='str1149'/>:<input name="goodsList[3].expireTime" id="expireTime3" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})">(<spring:message code='str1148'/>)<br/>

                <input onclick="openGoodsDialog(4)" id="4" readonly="readonly"> <input type="hidden" name="goodsList[4].id" id="id4">
                <select name="goodsList[4].binding" >
                    <option value="1" ><spring:message code='str1146'/></option>
                    <option value="0" ><spring:message code='str1147'/></option>
                </select>
                <spring:message code='str440'/>:<input name="goodsList[4].count" id="count4"> (<spring:message code='str1148'/>)
                <spring:message code='str1149'/>:<input name="goodsList[4].expireTime" id="expireTime4" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})">(<spring:message code='str1148'/>)<br/>--%>
            </div>
			<div class="controls">
				<input id="addGoods" class="btn btn-primary" type="button" value="<spring:message code='operation.add'/>" onclick="addItems();"/>
				<%--<input id="deleteGoods" class="btn btn-primary" type="button" value="<spring:message code='str22'/>" onclick="deleteItems();"/>--%>
			</div>
        </div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1263'/> <spring:message code='str1264'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>
