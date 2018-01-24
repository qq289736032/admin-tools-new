<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title><spring:message code='str589'/></title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/highcharts.jsp" %>
    <script src="${ctxStatic}/common/fixtable.js" type="text/javascript"></script>
    <script type="text/javascript">
        function viewAttach(line, flag, resLine) {
            var totalYb = $('#totalYb').val();
            var ybNum = $('#ybNum').val();
            var totalBind = $('#totalBind').val();
            var bindNum = $('#bindNum').val();
            var serverIds = $('.serverIds').val();
            var DateStart = $('#startDatePicker').val();
            var DateEnd = $('#endDatePicker').val();
            var content;
            $.ajax({
                url: '${ctx}/global/tradeController/resLineServersDistribution',
                data: {id: line, DateStart: DateStart, DateEnd: DateEnd, serverIds: serverIds},
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    content = "<div style='height:200px;line-height:200px;overflow-y:scroll;'><table id='dialogTable' class='table table-striped table-bordered table-condensed' >";
                    content = content + "<thead> <tr><th><spring:message code='str187'/></th><th><spring:message code='str14'/></th><th><spring:message code='str916'/></th><th><spring:message code='str189'/></th><th><spring:message code='str190'/>(%)</th><th><spring:message code='str191'/></th><th><spring:message code='str192'/>(%)</th></tr></thead><tbody>";
                    if (flag == 0) {
                        $.each(data, function (index, item) {
                            var testsRed = "";
                            var tests1Red = "";

                            if (totalYb == 0) {
                                texts = 0;
                            } else {
                                texts = (item.yb * 100 / totalYb).toFixed(2);
                            }

                            if (texts > 100) {
                                testsRed += "<span style='color: red;font-weight:bold' >" + texts + "</sapn>";
                            } else {
                                testsRed += texts;
                            }

                            if (ybNum == 0) {
                                texts1 = 0;
                            } else {
                                texts1 = (item.num * 100 / ybNum ).toFixed(2);
                            }

                            if (texts1 > 100) {
                                tests1Red += "<span style='color: red;font-weight:bold'>" + texts1 + "</sapn>";
                            }
                            else {
                                tests1Red += texts1;
                            }
                            content = content + "<tr><td>" + DateStart + "~" + DateEnd + "</td><td>" + item.area_id + "</td><td>" + resLine + "</td><td>" + item.yb + "</td><td ><c:if test='${totalYb >0 }'>" + testsRed + "%</c:if></td><td>" + item.num + "</td><td><c:if test='${ybNum >0 }'>" + tests1Red + "%</c:if></td></tr>";
                        });
                    } else {
                        $.each(data, function (index, item) {

                            if (totalBind == 0) {
                                texts2 = 0;
                            } else {
                                texts2 = (item.bind_yb * 100 / totalBind).toFixed(2);
                            }

                            if (bindNum == 0) {
                                texts3 = 0;
                            } else {
                                texts3 = (item.bind_num * 100 / bindNum  ).toFixed(2);
                            }

                            var tests2Red = "";
                            var tests3Red = "";
                            if (texts2 > 100) {
                                tests2Red += "<span style='color: red;font-weight:bold' >" + texts2 + "</sapn>";
                            } else {
                                tests2Red += texts2;
                            }
                            if (texts3 > 100) {
                                tests3Red += "<span style='color: red;font-weight:bold' >" + texts3 + "</sapn>";
                            } else {
                                tests3Red += texts3;
                            }
                            content = content + "<tr><td>" + DateStart + "~" + DateEnd + "</td><td>" + item.area_id + "</td><td>" + resLine + "</td><td>" + item.bind_yb + "</td><td><c:if test='${totalBind >0 }'>" + tests2Red + "%</c:if></td><td>" + item.bind_num + "</td><td><c:if test='${bindNum >0 }'>" + tests3Red + "%</c:if></td></tr>";
                        });
                    }
                    content = content + "</tbody></table></div>";
                    var d = top.dialog({
                        title: '<spring:message code='str591'/>',
                        lock: true,
                        zIndex: 999999999,
                        width: '100%',
                        height: '100%',
                        content: content
                    });
                    $("td.ui-dialog-body", top.document).css('height', 'auto');
                    d.showModal();

                }, error: function (request, status, e) {
                    alert(request + "====" + status + "=======" + e);
                }

            });

        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/global/tradeController/resLineDistribution"><spring:message code='str589'/></a>
    </li>
</ul>
<form id="searchForm" action="${ctx}/global/tradeController/resLineDistribution" method="post"
      class="breadcrumb form-search">

    <label><spring:message code='str185'/></label>
    <input type="text" name="createDateStart" class="input_search" size="10" readonly="readonly" maxlength="20"
           value="${paramMap.createDateStart}" id="startDatePicker"
           onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDatePicker\')}'})">
    -&nbsp;<input type="text" name="createDateEnd" class="input_search" size="10" readonly="readonly"
                  value="${paramMap.createDateEnd}" id="endDatePicker"
                  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatePicker\')}'})">
    <label><spring:message code='str55'/></label>
    <input id="serverIds" name="serverIds" class="serverIds" value="${paramMap.serverIdList}" readonly="readonly"/>
    <%--<input type="button" class="btn btn-primary" value="<spring:message code='str15'/>" onclick="openSeverDialog();">--%>
    <input type="button" class="btn btn-primary" value="<spring:message code='str15'/>"
           onclick="showContent('${ctx}/tools/gameArea/openGameServer')">
    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code="operation.search"/>"
                 onclick="return page();"/>
</form>
<tags:message content="${message}"/>


<div class="row-fluid">
    <div class="span12 panel panel-primary">
        <div class="panel-heading"><spring:message code='str592'/></div>
        <div class="" style="width:100%;height:300px;line-height:300px;overflow:scroll;">
            <table id="resLine" class="table table-striped table-bordered table-condensed">
                <tr>
                    <th><spring:message code='str187'/></th>
                    <th><spring:message code='str916'/></th>
                    <th><spring:message code='str189'/></th>
                    <th><spring:message code='str190'/>(%)</th>
                    <th><spring:message code='str191'/></th>
                    <th><spring:message code='str192'/>(%)</th>
                    <th><spring:message code='str593'/></th>
                </tr>
                <c:forEach items="${reslist}" var="item">
                    <tr>
                        <td>${createDateStart } ~ ${createDateEnd }</td>
                        <td>${fns:getResourceType(item.res_line )}</td>
                        <td>${item.yb }</td>
                        <td <c:if test="${  totalYb >0 && (item.yb*100/totalYb > 100) }">style="color: red;font-weight:bold" </c:if>><c:if
                                test="${totalYb >0 }"><fmt:formatNumber value="${   totalYb >0 ? (item.yb*100/totalYb):0 }"
                                                                        pattern="#0.00"/>%</c:if></td>
                        <td>${item.num }</td>
                        <td <c:if test="${ybNum >0 && (item.num*100/ybNum > 100) }">style="color: red;font-weight:bold" </c:if>><c:if
                                test="${ybNum > 0 }"><fmt:formatNumber value="${ item.num >0 ? (item.num*100/ybNum):0 }" pattern="#0.00"/>%</c:if></td>
                        <td><input type="button" value="<spring:message code='str593'/>"
                                   onclick="viewAttach('${item.res_line}','0','${fns:getResourceType(item.res_line )}')">
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td style="font-weight: bold;"><spring:message code='str193'/></td>
                    <td></td>
                    <td>${totalYb }<input type="hidden" value="${totalYb }" id="totalYb"></td>
                    <td>100%</td>
                    <td>${ybNum }<input type="hidden" value="${ybNum }" id="ybNum"></td>
                    <td>100%</td>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>
</div>

<div class="row-fluid">
    <div class="span12 panel panel-primary">
        <div class="panel-heading"><spring:message code='str594'/></div>
        <div class="" style="width:100%;height:300px;line-height:300px;overflow:auto;overflow-x:hidden;">
            <table id="band-yb" class="table table-striped table-bordered table-condensed">
                <tr>
                    <th><spring:message code='str187'/></th>
                    <th><spring:message code='str916'/></th>
                    <th><spring:message code='str195'/></th>
                    <th><spring:message code='str190'/>(%)</th>
                    <th><spring:message code='str191'/></th>
                    <th><spring:message code='str192'/>(%)</th>
                    <th><spring:message code='str593'/></th>
                </tr>
                <c:forEach items="${reslist}" var="item">
                    <tr>
                    <tr>
                        <td>${createDateStart } ~ ${createDateEnd }</td>
                        <td>${fns:getResourceType(item.res_line )}</td>
                        <td>${item.bind_yb }</td>
                        <td
                                <c:if test="${ totalBind >0 && (item.bind_yb*100/totalBind > 100) }">style="color: red;font-weight:bold" </c:if>>
                            <c:if test="${totalBind > 0 }"><fmt:formatNumber value="${ item.bind_yb >0 ? (item.bind_yb*100/totalBind):0 }"
                                                                             pattern="#0.00"/> %</c:if></td>
                        <td>${item.bind_num }</td>
                        <td <c:if test="${ item.bind_num >0 && (item.bind_num*100/bindNum> 100) }">style="color: red;font-weight:bold" </c:if>>
                            <c:if test="${bindNum > 0 }"><fmt:formatNumber value="${ item.bind_num >0 ? (item.bind_num*100/bindNum):0 }"
                                                                           pattern="#0.00"/> %</c:if></td>
                        <td><input type="button" value="<spring:message code='str593'/>"
                                   onclick="viewAttach('${item.res_line}','1','${fns:getResourceType(item.res_line )}')">
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td style="font-weight: bold;"><spring:message code='str193'/></td>
                    <td></td>
                    <td>${totalBind }<input type="hidden" value="${totalBind }" id="totalBind"></td>
                    <td>100%</td>
                    <td>${bindNum }<input type="hidden" value="${bindNum }" id="bindNum"></td>
                    <td>100%</td>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>
