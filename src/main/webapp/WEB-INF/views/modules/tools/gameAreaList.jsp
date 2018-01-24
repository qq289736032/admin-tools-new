﻿<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1306'/></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/highcharts.jsp" %>
	<style type="text/css">
		span.input-group-btn {
			display: none;
		}
	</style>


<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
			$(document).ready(function() {
				$(".dblclick").dblclick(function() {
					$(this).parent().find("#openTime").css("display", "none");
					$(this).parent().find("#showOpen").css("display", "block");
				})
			})
			$(document).ready(function() {
				$(".dblclick1").dblclick(function() {
					$(this).parent().find("#hefuTime").css("display", "none");
					$(this).parent().find("#showHefu").css("display", "block");
				})
			})
			function hideTime(a) {
				$(a).parent().parent().parent().find("#openTime").css("display","block");
				$(a).parent().parent().parent().find("#showOpen").css("display", "none");
			}
			function hideTime1(a) {
				$(a).parent().parent().parent().find("#hefuTime").css("display","block");
				$(a).parent().parent().parent().find("#showHefu").css("display", "none");
			}
			
			function updateTime(a,woldId) {
				var openTime=$(a).parent().parent().parent().find("#openTime").text();
				var open_time=$(a).parent().parent().parent().find("#open_time").val();
				if(openTime!=open_time){
					if(open_time!=""){
						$.ajax({
							type : 'POST',
							url : "${ctx }/tools/gameArea/updateOpenTime",
							dataType : 'json',
							data:{"open_time":open_time,"woldId":woldId},
							before : function() {
								loading("正在提交请稍等...")
							},
							success : function(data) {
								if(data=="1"){
								$(a).parent().parent().parent().find("#openTime").css("display","block");
								$(a).parent().parent().parent().find("#showOpen").css("display", "none");
								$(a).parent().parent().parent().find("#openTime").text(open_time);
								alert("修改成功");
								}else{
								alert("修改失败");
								}
							}
						})
					}else{
						alert("开服时间不能为空");
					}
				 
				}else{
					alert("未进行修改");
				}
			}
			
			function updateTime1(a,woldId) {
				var hefuTime=$(a).parent().parent().parent().find("#hefuTime").text();
				var hefu_time=$(a).parent().parent().parent().find("#hefu_time").val();
				if( hefuTime!=hefu_time){
					if(hefu_time!="" ){
						$.ajax({
							type : 'POST',
							url : "${ctx }/tools/gameArea/updateHefuTime",
							dataType : 'json',
							data:{"hefu_time":hefu_time,"woldId":woldId},
							before : function() {
								loading("正在提交请稍等...")
							},
							success : function(data) {
								if(data=="1"){
								$(a).parent().parent().parent().find("#hefuTime").css("display","block");
								$(a).parent().parent().parent().find("#showHefu").css("display", "none");
								$(a).parent().parent().parent().find("#hefuTime").text(hefu_time); 
								alert("修改成功");
								}else{
								alert("修改失败");
								}
							}
						})
					}else{
						alert("合服时间不能为空");
					}
				}else{
					alert("未进行修改");
				}
			}
	
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/gameArea/gameAreas"><spring:message code='str1306'/></a></li>
	</ul>
	<form id="searchForm" action="${ctx}/tools/gameArea/gameAreas" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<label><spring:message code='str1307'/> <spring:message code='str4'/></label>
		<select id="pid" name="pids" onchange="setCurrentPlatform()">
			<option value=""><spring:message code='str627'/></option>
			<c:forEach var="item" items="${fns:getGamePlatformList()}">
				<option value="${item.pid}" <c:if test="${item.pid==paramMap.pids}">selected="selected"</c:if>>${item.name}</option>
			</c:forEach>
		</select>
		<label><spring:message code='str1284'/></label>
		<input id="serverId" name="serverId" value="${paramMap.serverId}"/>
		<label><spring:message code='str768'/>ID<spring:message code='str4'/></label>
		<input id="areaId" name="areaId" value="${paramMap.areaId}"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>" onclick="return page();"/>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th><spring:message code='str1285'/>ID</th> <th style="width: 80px"><spring:message code='str1286'/>ID</th> <th><spring:message code='str1287'/></th> <th><spring:message code='str1288'/></th> <th><spring:message code='str1289'/></th>
			<th><spring:message code='str1290'/>IP</th> <th><spring:message code='str1290'/><spring:message code='str1186'/></th> <th>web<spring:message code='str1186'/></th> <th><spring:message code='str1293'/><spring:message code='str1186'/></th> <th><spring:message code='str1293'/>IP</th>
			<th><spring:message code='str56'/>ID</th> <th><spring:message code='str1294'/></th> <th><spring:message code='str1308'/></th> <th><spring:message code='str1309'/></th> <th><spring:message code='str1078'/><spring:message code='str3'/></th><th><spring:message code='str128'/></th><th><spring:message code='str1310'/></th><th><spring:message code='str149'/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td>${item.world_id}</td>
				<td>${item.area_id}</td>
				<td>${item.world_name}</td>
				<td>${item.area_name}</td>
				<td>${item.area_type}</td>

				<td>${item.external_ip}</td>
				<td>${item.tcp_port}</td>
				<td>${item.web_port}</td>
				<td>${item.inner_port}</td>
				<td>${item.inner_ip}</td>
				<td>${fns:getGamePlatformNameById(item.pid,item.pid)}</td>
				<td>
				  <c:choose>
                    <c:when test="${item.status == 0}"><span class="label label-info"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:when test="${item.status == 1}"><span class="label label-warning"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:when>
                    <c:otherwise><span class="label label-important"><spring:message code="${fns:getDictKeys(item.status,'server_status',item.status)}"/></span></c:otherwise>
                   </c:choose>
				</td>
				<td>${item.follower_id}</td>
				<%-- <td class="dblclick" id="openTime"><span><fmt:formatDate value="${item.open_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
				<td class="dblclick" ><span id="openTime"><fmt:formatDate value="${item.open_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span><span id="showOpen" style="display:none"><input type="text" readonly="readonly" id="open_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${item.open_time}' pattern='yyyy-MM-dd HH:mm:ss'/>"/><br/><input type="button" id="tijiao" value="提交" onclick="updateTime(this,${item.world_id})"/> <input type="button"  value="取消" onclick="hideTime(this)"/></span></td>
				<%-- <td class="dblclick" id="hefuTime"><fmt:formatDate value="${item.combine_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>  --%>
				<td class="dblclick1" ><span id="hefuTime"><fmt:formatDate value="${item.combine_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span><span id="showHefu" style="display:none"><input type="text" readonly="readonly" id="hefu_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value='${item.combine_time}' pattern='yyyy-MM-dd HH:mm:ss'/>"/><br/><input type="button" id="tijiao" value="提交" onclick="updateTime1(this,${item.world_id})"/> <input type="button"  value="取消" onclick="hideTime1(this)"/></span></td>
				<td><fmt:formatDate value="${item.update_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.server_version}</td>
				<%-- <td><input type="button" value="同服" onclick="viewAttach('${item.res_line}','0','${fns:getResourceType(item.res_line )}')"></td>   --%>
				 <td><c:if test="${item.follower_id == 0}"><a type="button" href="#" class="btn btn-primary btn-small"   onclick="showContent('${ctx}/tools/gameArea/viewFollowerId?pids=${item.pid }&followerId=${item.world_id }&name=${item.area_name }')"><spring:message code="str1679"/></a></c:if> </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('table.highchart').highchartTable();
		$("#s2id_pids").hide();
		$("#pids").multiselect({
			includeSelectAllOption:true,
			enableFiltering:true
		});
	});
	
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
	                title: "<spring:message  code='followerId.config'/>",
	                width:'100%',
	                height:'97%',
	                content: data,
	                ok: function(){
	                    d.close();
	                },
	                cancel: function(){}
	            });
	            $("td.ui-dialog-body", top.document).css('height','auto');
	            d.showModal();
	        }
	    })
	}
	
	</script>

</html>
