<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str470'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tools/crossArea/crossAreaManage"><spring:message code='str1179'/></a></li>
		<li class="active"><a href="${ctx}/tools/crossArea/crossAreaAdd?crossAreaType=0"><spring:message code='str1180'/></a></li>
	</ul>
	<form id="inputForm" action="${ctx}/tools/crossArea/doCrossAreaAdd" method="post" class="form-horizontal">
        <input hidden="hidden" style="display: none"  id="id" name="id" value="${paramMap.id}">
		<tags:message content="${message}"/>
        <div class="control-group">
            <label class="control-label"><spring:message code='str700'/>:</label>
            <div class="controls">
            <select id="crossAreaType" name="crossAreaType">
				<c:forEach items="${fns:getDictList('crossType')}" var="item">
					<option value="${item.value}" <c:if test="${item.value eq crossAreaType}">selected="selected"</c:if>><spring:message code="${item.internationalKey}"/></option>
				</c:forEach>
			</select>
			</div>
        </div>

        <div class="control-group">
            <label class="control-label"><spring:message code='str1240'/>ID:</label>
            <div class="controls">
                <select id="crossServerId" name="crossServerId">
                    <c:if test="${crossArea!=null}">
                        <option value="${crossArea.crossServer.worldId}" selected="selected">${crossArea.crossServer.worldName}</option>
                    </c:if>
                    <c:forEach items="${crossServerToPage}" var="item">
                        <option value="${item.worldId}" <c:if test="${item.worldId==wroldId}">selected="selected"</c:if>>${item.worldName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="control-group">
            <label class="control-label"><spring:message code='str1275'/>:</label>
            <div class="controls">
                <input id="crossAreaName" name="crossAreaName" value="${crossAreaName}"/>
            </div>
        </div>
        <div id="countryId_div" class="control-group">
            <label class="control-label"><spring:message code='str1181'/>ID:</label>
            <div class="controls">
                <select id="countryId" name="countryId">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><spring:message code='str14'/>:</label>
            <div class="controls">
                <select id="gameServerId" name="gameServerId" multiple="multiple">
                    <c:forEach items="${gameServerToPage}" var="item">
                        <option value="${item.worldId}" <c:if test="${fn:indexOf(gameServersId,item.worldId)>0}">selected="selected"</c:if>>${item.worldName}</option>
                    </c:forEach>
                    <c:forEach items="${crossArea.allServers}" var="item">
                        <option value="${item.worldId}" selected="selected">${item.worldName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='str1276'/>"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='str1117'/> <spring:message code='str1118'/>" onclick="history.go(-1)"/>
		</div>
	</form>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table.highchart').highchartTable();
			$("#s2id_gameServerId").hide();
			$("#gameServerId").multiselect({
				includeSelectAllOption:true,
				enableFiltering:true
			});
			resetLayout();
			$("#crossAreaType").bind("change",function(){
				crossAreaTypeChange(this);
			});
		});
		function crossAreaTypeChange(obj){
			resetLayout();
			if($(obj).val()==0){//<spring:message code='str1277'/>
				$("form:first").attr("action", "${ctx}/tools/crossArea/getBattleCrossInfo");
			}else{//<spring:message code='str1278'/>
				$("form:first").attr("action", "${ctx}/tools/crossArea/getCountryCrossInfo");
			}
			$("form:first").submit();
		}
		//<spring:message code='str1279'/>
		function resetLayout(){
			var crossType = $("#crossAreaType").val();
			if(crossType==0){
				$("#countryId_div").hide();
			}else{
				$("#countryId_div").show();
			}
		}
	</script>
</body>
</html>
