<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str1448'/></title>
	<meta name="decorator" content="default"/>

	<link rel="stylesheet" href="${ctxStatic}/CodeMirror/lib/codemirror.css" />
	<link rel="stylesheet" href="${ctxStatic}/CodeMirror/3024-night.css" />
	<script src="${ctxStatic}/CodeMirror/lib/codemirror.js"></script>
	<script src="${ctxStatic}/CodeMirror/sql/sql.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/CodeMirror/show-hint.css" />
	<script src="${ctxStatic}/CodeMirror/show-hint.js"></script>
	<script src="${ctxStatic}/CodeMirror/sql-hint.js"></script>

	<style>
		.CodeMirror {border: 1px solid black; font-size:14px; height: 300px}
		.noborder{border:none;}

		.alert, .alert h4 {
			color: #080808;
		}

		dd {
			margin-left: 70px;
		}

		.line_sep {
			border: 1px solid black
		}
		.label {
			line-height: 25px;
		}

		#contentTable > tbody > tr {
			cursor: pointer;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tools/query"><spring:message code='str1448'/></a></li>
	</ul>
	<input id="pageNo" name="pageNo" type="hidden" value="${paramMap.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${paramMap.pageSize}"/>

	<tags:message content="${message}"/>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6">
				<textarea id="code" name="code" style="height: 200px">SELECT pid,area_id,role_id,role_name,flow_type,value,log_day,add_time FROM goods_flow_log_20150515 limit 10
				</textarea>
			</div>
			<div class="span6 alert" style="height: 300px">
				<%--<button type="button" class="close" data-dismiss="alert">×</button>--%>
					<h4>SQL<spring:message code='str1449'/></h4>
					<dl>
						<dt style="float: left"><spring:message code='str1450'/></dt><dd><spring:message code='str1385'/></dd>
						<dt style="float: left;padding-top: 10px">SQL<spring:message code='str1401'/></dt><dd style="padding-top: 10px">MySql<spring:message code='str1451'/></dd>
						<dt style="float: left;padding-top: 10px"><spring:message code='str1452'/></dt><dd style="padding-top: 10px"><spring:message code='str1453'/></dd>
						<dt style="float: left;padding-top: 10px"><spring:message code='str1454'/></dt><dd style="padding-top: 10px" ><spring:message code='str1455'/>100<spring:message code='str1456'/></dd>
						<dt style="float: left;padding-top: 10px"><spring:message code='str1457'/></dt><dd style="padding-top: 10px"><spring:message code='str1458'/>SQL<spring:message code='str1459'/><a href="http://www.w3school.com.cn/sql/index.asp" target="_blank" class="link">w3school</a></dd>
						<dt style="float: left;padding-top: 10px">SQL<spring:message code='str1460'/></dt><dd style="padding-top: 10px" ><spring:message code='str1461'/>SQL<spring:message code='str1462'/>30<spring:message code='str1463'/></dd>
					</dl>

			</div>
		</div>

		<div class="row-fluid">
			<div class="span6">
				<a href="${ctx }/tools/query/query" id="sql_query" class="btn btn-primary" type="button" style="float: right"><spring:message code='str83'/></a>
				<%--<button class="btn btn-success" type="button" style="float: right"><spring:message code='str5'/></button>--%>
			</div>
		</div>

		<div class="row-fluid" style="padding-top: 10px">
			<div class="span12">
				<p class="success">
					<h4><span class="label label-info"><spring:message code='str1464'/></span></h4>
				</p>
				<div style="overflow: scroll" id="queryResult">
				<!-- <table id="queryResult" class="table table-striped table-hover" style="overflow: scroll">
				</table> -->
				</div>
			</div>
		</div>

		<div class="line_sep"></div>

		<div class="row-fluid" style="padding-top: 10px">
			<div class="span12">
				<p class="success">
					<h4><span class="label label-info">SQL<spring:message code='str1465'/></span></h4>
				</p>
				<table id="contentTable" class="table table-striped table-hover">
					<thead>
					<tr>
						<th>
							<spring:message code='str1466'/>
						</th>
						<th>
							SQL
						</th>
						<th>
							<spring:message code='str1467'/>
						</th>
						<th>
							<spring:message code='str1468'/>
						</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="status">
							<tr class='<c:if test="${status.index % 2 == 1}">success</c:if>' >
								<td>${status.index + 1}</td>
								<td>${item.sql}</td>
								<td>${item.add_time}</td>
								<td>${item.create_name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>



	<script type="text/javascript">

		var editor;

		window.onload = function() {
			var mime = 'text/x-mariadb';
			// get mime type
			if (window.location.href.indexOf('mime=') > -1) {
				mime = window.location.href.substr(window.location.href.indexOf('mime=') + 5);
			}
			editor = CodeMirror.fromTextArea(document.getElementById('code'), {
				mode: mime,
				indentWithTabs: true,
				smartIndent: true,
				lineNumbers: true,
				matchBrackets : true,
				autofocus: true,
				extraKeys: {"Ctrl-Space": "autocomplete"},
				hintOptions: {tables: {
					users: {name: null, score: null, birthDate: null},
					countries: {name: null, population: null, size: null}
				}}
			});
		};

		$(document).ready(function() {
			$("#sql_query").click(function(){
				var sql = editor.getValue();
				if ($.trim(sql) == "") { tips("<spring:message code='str1469'/>SQL<spring:message code='str1470'/>~"); return false; }
//				var mode = $('input[name=mode]:checked').val();
//				$.cookie('queryMode', mode);
              var pageNo=$("#pageNo").val();
              var pageSize=$("#pageSize").val();
				$.post($(this).attr("href"), {"sql" : sql,"pageNo":pageNo,"pageSize":pageSize}).success(function(data) {
					$("#queryResult").html(data);
				}).error(function() {
					tips("<spring:message code='str1471'/>!");
				});
				return false;
			});



			$("#contentTable > tbody > tr").click(function(){
				var sql = $(this).children("td").get(1).innerHTML;
				editor.setValue(sql);
			});

			$("tr:even").addClass("success");//<spring:message code='str1472'/>
			$("tr:odd").addClass("error");//<spring:message code='str1473'/>
		})

		
		function   page(n,s){
			var  code= editor.getValue();
			$.ajax({
		        type : 'POST',
		        url: "${ctx }/tools/query/query?sql="+code+"&pageNo="+n+"&pageSize="+s,
		        dataType: 'json',
		       // data:params,
		        before:function(){
		          loading("正在提交请稍等...")
		        },
		        success:function(data){
		        	$("#queryResult").html(data);
		        }
		    })
			
			
		}
		
		
		
	</script>
</body>

</html>
