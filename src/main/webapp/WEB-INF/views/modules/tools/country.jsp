<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

    <style type="text/css">
        /*.modal-header{ border-bottom: none;}*/
        /*.modal-body{border-bottom: 1px solid #EEEEEE}*/
        /*{*/
            /*margin: 1px;*/
            /*font: inherit;*/
            /*color: inherit;*/
        /*}*/
        BUTTON
        {
            overflow: visible;
        }
        BUTTON
        {
            text-transform: none;
        }
        BUTTON
        {
            cursor: pointer;
            -webkit-appearance: button;
        }
        *
        {
            background: 0px 0px;
            /*color: #000 !important;*/
            text-shadow: none;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        :after
        {
            background: 0px 0px;
            color: #000 !important;
            text-shadow: none;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        :before
        {
            background: 0px 0px;
            color: #000 !important;
            text-shadow: none;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        *
        {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        :after
        {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        :before
        {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
         HTML
        {
            font-size: 10px;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        }
        BODY
        {
            line-height: 1.4285;
            background-color: #fff;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            color: #333;
            font-size: 14px;
        }
        BUTTON
        {
            line-height: inherit;
            font-family: inherit;
            font-size: inherit;
        }
        .container
        {
            padding-left: 15px;
            padding-right: 15px;
            margin-left: auto;
            margin-right: auto;
        }
        .row
        {
            margin-left: -15px;
            margin-right: -15px;
        }
        .col-md-9
        {
            position: relative;
            min-height: 1px;
            padding-left: 15px;
            padding-right: 15px;
        }
        .btn
        {
            background-image: none;
            border-bottom: transparent 1px solid;
            text-align: center;
            border-left: transparent 1px solid;
            padding-bottom: 6px;
            line-height: 1.4285;
            padding-left: 12px;
            padding-right: 12px;
            display: inline-block;
            white-space: nowrap;
            margin-bottom: 0px;
            font-size: 14px;
            vertical-align: middle;
            border-top: transparent 1px solid;
            cursor: pointer;
            font-weight: 400;
            border-right: transparent 1px solid;
            padding-top: 6px;
            border-radius: 4px;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        .btn-default
        {
            border-bottom-color: #ccc;
            background-color: #fff;
            border-top-color: #ccc;
            color: #333;
            border-right-color: #ccc;
            border-left-color: #ccc;
        }
        .btn-primary
        {
            border-bottom-color: #2e6da4;
            background-color: #337ab7;
            border-top-color: #2e6da4;
            color: #fff;
            border-right-color: #2e6da4;
            border-left-color: #2e6da4;
        }
        .btn-success
        {
            border-bottom-color: #4cae4c;
            background-color: #5cb85c;
            border-top-color: #4cae4c;
            color: #fff;
            border-right-color: #4cae4c;
            border-left-color: #4cae4c;
        }
        .btn-info
        {
            border-bottom-color: #46b8da;
            background-color: #5bc0de;
            border-top-color: #46b8da;
            color: #fff;
            border-right-color: #46b8da;
            border-left-color: #46b8da;
        }
        .btn-warning
        {
            border-bottom-color: #eea236;
            background-color: #f0ad4e;
            border-top-color: #eea236;
            color: #fff;
            border-right-color: #eea236;
            border-left-color: #eea236;
        }
        .btn-danger
        {
            border-bottom-color: #d43f3a;
            background-color: #d9534f;
            border-top-color: #d43f3a;
            color: #fff;
            border-right-color: #d43f3a;
            border-left-color: #d43f3a;
        }
        .btn-link
        {
            color: #337ab7;
            font-weight: 400;
            border-radius: 0;
        }
        .btn-link
        {
            background-color: transparent;
            -webkit-box-shadow: none;
            box-shadow: none;
        }
        .btn-link
        {
            border-bottom-color: transparent;
            border-top-color: transparent;
            border-right-color: transparent;
            border-left-color: transparent;
        }
        .container:after
        {
            display: table;
            content: " ";
        }
        .container:before
        {
            display: table;
            content: " ";
        }
        .row:after
        {
            display: table;
            content: " ";
        }
        .row:before
        {
            display: table;
            content: " ";
        }
        .container:after
        {
            clear: both;
        }
        .row:after
        {
            clear: both;
        }
        .active{
            background-color: #4DF989;
        }

        button {
            padding: 10px 10px 10px 10px;
            margin-bottom: 10px;
            margin: 4px;
            width: 120px;
        }
        	td.ui-dialog-body {
            height: auto;
     }
    </style>
</head>
<body>
      <div id="serverDialog" class="">
              <table class="table table-striped table-bordered table-condensed" id="content">
				<tr><th></th><th><spring:message code='str1181'/>ID</th><th><spring:message code='str1237'/></th><th><spring:message code='str1238'/></th><th><spring:message code='str101'/></th></tr>
		       <c:forEach items="${fns:getCountry(crossId)}" var="country" varStatus="status">
		     		 	 <c:if test="${country.crossId == crossId}">  
				               <tr >
					                  <td><input type="checkbox" id="selectAll_${country.id }"  class="game-server-sort-name" name="countryId " value="${country.id }"/></td>
					                 <td>${country.id }</td>
					                 <td>${country.name }</td>
					                 <td>${country.kingName }</td>
					                 <td><fmt:formatDate value="${country.addTime }" pattern="yyyy-MM-dd HH:"/></td>
					             </tr>
					       </c:if>     
			</c:forEach>
		        </table>
     </div>
  <script type="text/javascript">
  var  countryIds="";
  $(function() {
      $(".game-server-sort-name").click(function(){
          var id = $(this).attr("id");
          if(this.checked){
              $("#"+id).each(function(){
                  $(this).attr("checked",true);
                  if( countryIds =="") {
                	   var  ids=id.substring(10,id.length);
                	  countryIds = ids;
                  }else {
                	  var  ids=id.substring(10,id.length);
                	  countryIds+=( ","+ ids );
                  }
              });
          }else{
              $("#"+id).each(function(){
                  $(this).attr("checked",false);
              });
          }
      });
    //  var ids = top.frames["mainFrame"].document.getElementById("countryIds").value;
  });
  function countryCallback(){
	  top.frames["mainFrame"].document.getElementById("countryIds").value = countryIds;
	  if(countryIds.length >0){
		  $.ajax({
		        type : "POST",
		        url: "${ctx}/tools/crossArea/addDetailCountry?countryId="+countryIds+"&crossId=${crossId}",
		        dataType: 'json',
		        before:function(){
		            loading("<spring:message code='str1189'/>...")
		        },
		        success:function(result){
		        var  data=result.data;
		        var   dataRow="";
		         for(var  i=0;i<data.length;i++){
						var url = "${ctx}/tools/crossArea/removeCountry?countryId=" + data[i].id;
						dataRow = dataRow + "<tr id='"+data[i].id+"'><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].kingName+"</td><td>"+data[i].addTime+"</td><td><a onclick=\"removeCountry('"+url+"','"+data[i].id+"');\"><spring:message code='str22'/></a></td></tr>";
		        }
			     var   a=$(window.frames["mainFrame"].document).find("#countryListTable").html();
			    $(window.frames["mainFrame"].document).find("#countryListTable").append(dataRow);
		        } 
		    })
		
	  }else{
		  tips("<spring:message code='str1239'/>");
	  }
	  
   }
  
  function allSelectOrNot(a){
      if(a.checked){
          $("#content .game-server-sort-name").each(function(){
              $(this).attr("checked",true);
          })
      }else{
          $("#content .game-server-sort-name").each(function(){
              $(this).attr("checked",false);
          })
      }
  }
 
  </script>
</body>
</html>
