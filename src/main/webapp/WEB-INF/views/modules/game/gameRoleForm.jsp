<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='str86'/></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function bag(){
			location.href = "${ctx}/game/role/bag?roleId=${role.roleId}&serverId=${role.serverId}";
		}
        function currency() {
            var roleId = ${role.roleId};
            var serverId = ${role.serverId};
            var value = $('#t_cur_amount').val();
            var curType = $('#sel_money_type').val();
            if(value ==0 || curType <= 0) return;
            $.ajax({
                url: '${ctx}/game/role/currency',
                data: {id: roleId, value: value, curType: curType, serverId: serverId},
                type: 'post',
                dataType: 'json',
                success: function (data) {
                	if(data) {
                    	alert("====" + "<spring:message code='str1670'/>" + "=======");
                	} else {
	                    alert("<spring:message code='str1674'/>");
                	}
                }, error: function (request, status, e) {
                    alert("<spring:message code='str1674'/>");
                }

            });
        }
        function level() {
            var roleId = ${role.roleId};
            var serverId = ${role.serverId};
            var value = $('#t_level_amount').val();
            var levelType = $('#sel_level_type').val();
            if(value <=0 && levelType == 1) return;
            if(value <0 && levelType == 2) return;
            $.ajax({
                url: '${ctx}/game/role/level',
                data: {id: roleId, value: value, levelType: levelType, serverId: serverId},
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if(data) {
                    	alert("====" + "<spring:message code='str1670'/>" + "=======");
                	} else {
	                    alert("<spring:message code='str1674'/>");
                	}
                }, error: function (request, status, e) {
                    alert("<spring:message code='str1674'/>");
                }

            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/game/role/"><spring:message code='str71'/></a></li>
		<li class="active"><a><spring:message code='str72'/></a></li>
	</ul><br/>

	<form class="breadcrumb form-search">
		<input type="button" id="bagwarehourse" class="btn btn-primary" value="<spring:message code='str73'/>" onclick="bag()" />
	</form>

	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str87'/></div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th><spring:message code='str88'/></th><td>${role.roleId}</td>
				<th><spring:message code='str7'/></th><th>${role.roleName}</th>
				<th><spring:message code='str89'/></th><td><spring:message code='${fns:getDictKeys(role.job, "job_type",role.job)}'/></td>
				<th><spring:message code='str90'/></th><th>${role.gender}</th>
			</tr>
			<tr>
				<th><spring:message code='str85'/></th><td>${role.LEVEL}</td>
				<th><spring:message code='str91'/></th><td>${role.POWER}</td>
				<th><spring:message code='str92'/></th><td>${role.leiji_jine}</td>
				<th>VIP<spring:message code='str85'/></th><td>${role.viplevel}</td>
			</tr>
			<tr>
				<th><spring:message code='str93'/></th><td>${role.diamond}</td>
				<th><spring:message code='str94'/></th><td>${role.bind_diamond}</td>
				<th><spring:message code='str95'/></th><td>${role.coin}</td>

			</tr>
			<tr>
				<th><spring:message code='str96'/></th><td>${role.guild_name}</td>
				<th><spring:message code='str97'/>(1<spring:message code='str98'/> 0<spring:message code='str99'/>)</th><td>${role.guild_position}</td>
				<th><spring:message code='str100'/>IP</th><td>${role.last_login_ip}</td>

			</tr>
			<tr>
				<th><spring:message code='str101'/></th><td>${role.add_time}</td>
				<th><spring:message code='str102'/></th><td>${role.lastLoginTime}</td>
				<th><spring:message code='str103'/></th><td>${role.lastLogoutTime}</td>
				<th><spring:message code='str60'/></th><td>${role.lastChargeTime}</td>
				<th><spring:message code='str104'/>IP</th><td>${role.create_ip}</td>
			</tr>
		</table>
	</div>
<!-- 	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str105'/></div>
		<table id="friendsTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str106'/></th><th><spring:message code='str7'/></th><th><spring:message code='str89'/></th><th><spring:message code='str90'/></th><th><spring:message code='str85'/></th></tr>

		</table>
	</div> -->

   <div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str1671'/></div>
		<table id="horseTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<td>
					<select name="moneyType" id="sel_money_type">
						<option value=""><spring:message code='str627'/></option>
					 	<c:forEach items="${fns:getDictList('money_type')}" var="item">
							<option value="${item.value}"
								<c:if test="${item.value==paramMap.moneyType}">selected="selected"</c:if>
							><spring:message code="${item.internationalKey}"/></option>
					 	</c:forEach>
					</select>
				</td>
				<td>
					<input type="text" id="t_cur_amount" value="0" />
				</td>
				<td>
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="str1444"/>" onclick="currency()"/>
				</td>
			</tr>
		</table>
	</div>

   <div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str1673'/></div>
		<table id="horseTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<td>
					<select name="moneyType" id="sel_level_type">
						<option value=""><spring:message code='str627'/></option>
						<option value="1"><spring:message code='str85'/></option>
						<option value="2"><spring:message code='str1672'/></option>
					</select>
				</td>
				<td>
					<input type="text" id="t_level_amount" value="0" />
				</td>
				<td>
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="str1444"/>" onclick="level();"/>
				</td>
			</tr>
		</table>
	</div>

   <div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str107'/></div>
		<table id="horseTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str108'/></th><th><spring:message code='str1466'/>(<spring:message code='str110'/>1 2 3 4 5<spring:message code='str111'/>)</th><th><spring:message code='str85'/></th><th><spring:message code='str112'/></th></tr>
			<c:forEach items="${baoshis}" var="baoshi">
			  <tr>
				<td>${fns:getBaishiName(baoshi.part)}</td>
				<td>${baoshi.grid}</td>
				<td>${baoshi.LEVEL}</td>
				<td>${baoshi.exp}</td>
			</tr>
			</c:forEach>
		</table>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str113'/></div>
		<table id="itemsTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str108'/>(1:<spring:message code='str114'/> 2:<spring:message code='str115'/> )</th><th><spring:message code='str116'/></th><th><spring:message code='str117'/></th><th><spring:message code='str118'/></th><th><spring:message code='str119'/></th><th><spring:message code='str120'/></th><th><spring:message code='str121'/></th></tr>
			<c:forEach items="${items}" var="item">
				<tr>
					<td>${item.position}</td>
					<td>${fns:getGoodName(item.template_id)}</td>
					<td>${item.level}</td>
					<td>${item.count}</td>
					<td>${item.rare_level}</td>
					<td>${fns:parseObj2int(item.attributes,'strengthenLevel')}</td>
					<td>${fns:parseObj2int(item.attributes,'qualification')}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

     <div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str122'/></div>
		<table id="itemsTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str116'/></th><th><spring:message code='str117'/></th><th><spring:message code='str118'/></th><th><spring:message code='str119'/></th><th><spring:message code='str120'/></th><th><spring:message code='str121'/></th></tr>
			<c:forEach items="${xunbao}" var="item">
				<tr>
					<td>${fns:getGoodName(item.template_id)}</td>
					<td>${item.level}</td>
					<td>${item.count}</td>
					<td>${item.rare_level}</td>
					<td>${fns:parseObj2int(item.attributes,'strengthenLevel')}</td>
					<td>${fns:parseObj2int(item.attributes,'qualification')}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str124'/>)</div>
		<table id="horseTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str125'/></th><th><spring:message code='str126'/></th><th><spring:message code='str127'/></th><th><spring:message code='str128'/></th></tr>
			<c:forEach items="${horse}" var="horse">
			  <tr>
				<td>${horse.rank}</td>
				<td>${horse.rank}</td>
				<td>${horse.add_time}</td>
				<td>${horse.modify_time}</td>
			</tr>
			</c:forEach>
		</table>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str129'/>)</div>
		<table id="cloakTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str125'/></th><th><spring:message code='str126'/></th><th><spring:message code='str127'/></th><th><spring:message code='str128'/></th></tr>
			<c:forEach items="${cloak }" var="cloak">
			<tr>
			<td>${cloak.binqi_level}</td>
			<td>${cloak.binqi_level}</td>
			<td>${cloak.lingqu_time}</td>
			<td>${cloak.update_time}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
   
    <div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str130'/>)</div>
		<table id="cloakTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str131'/></th><th><spring:message code='str126'/></th><th><spring:message code='str132'/></th><th><spring:message code='str127'/></th></tr>
			<c:forEach items="${wuji}" var="wuji">
			<tr>
			<td>${fns:getMeirenName(wuji.meiren_id)}</td>
			<td>${wuji.meiren_level}</td>
			<td>${wuji.meiren_heti}</td>
			<td>${wuji.lingqu_time}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
<%-- 
	 <div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str133'/>)</div>
		<table id="nvwushenTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str125'/></th><th><spring:message code='str126'/></th><th><spring:message code='str127'/></th><th><spring:message code='str128'/></th></tr>
			<c:forEach items="${nvwushen}" var="nvwushen">
			<tr>
			 <td>${nvwushen.maxRank}</td>
			 <td>${nvwushen.rank}</td>
			 <td>${nvwushen.create_time}</td>
			 <td>${nvwushen.update_time}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str134'/>)</div>
		<table id="touxianTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str125'/></th><th><spring:message code='str126'/></th><th><spring:message code='str127'/></th><th><spring:message code='str128'/></th></tr>
			<c:forEach items="${touxian}" var="touxian">
			  <tr>
			    <td>${touxian.maxRank}</td>
			    <td>${touxian.rank}</td>
			    <td>${touxian.create_time}</td>
			    <td>${touxian.update_time}</td>
			  </tr>
			</c:forEach>
		</table>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str135'/>)</div>
		<table id="tainsuoTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str125'/></th><th><spring:message code='str126'/></th><th><spring:message code='str127'/></th><th><spring:message code='str128'/></th></tr>
			<c:forEach items="${tainsuo}" var="tainsuo">
			  <tr>
			   <td>${tainsuo.maxRank}</td>
			   <td>${tainsuo.rank}</td>
			   <td>${tainsuo.create_time}</td>
			   <td>${tainsuo.update_time}</td>
			  </tr>
			</c:forEach>
		</table>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str136'/>)</div>
		<table id="huashenTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str125'/></th><th><spring:message code='str126'/></th><th><spring:message code='str127'/></th><th><spring:message code='str128'/></th></tr>
			<c:forEach items="${huashen}" var="huashen">
			 <tr>
			   <td>${huashen.maxRank}</td>
			   <td>${huashen.rank}</td>
			   <td>${huashen.create_time}</td>
			   <td>${huashen.update_time}</td>
			 </tr>
			</c:forEach>
		</table>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading"><spring:message code='str123'/>(<spring:message code='str137'/>)</div>
		<table id="shengyiTable" class="table table-striped table-bordered table-condensed">
			<tr><th><spring:message code='str125'/></th><th><spring:message code='str126'/></th><th><spring:message code='str127'/></th><th><spring:message code='str128'/></th></tr>
			<c:forEach items="${shengyi}" var="shengyi">
			<tr>
			 <td>${shengyi.maxRank}</td>
			 <td>${shengyi.rank}</td>
			 <td>${shengyi.create_time}</td>
			 <td>${shengyi.update_time}</td>
			</tr>
			</c:forEach>
		</table>
	</div> --%>
</body>
</html>
