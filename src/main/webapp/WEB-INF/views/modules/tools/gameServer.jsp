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
    </style>
</head>
<body>

<div id="serverDialog" class="">
    <div class="breadcrumb form-search">

        <div class="pop_layer_cont">
            <input type="checkbox" class="btn_select_16" onclick="allSelectOrNot(this)"/><spring:message code='str1514'/>
            <%--<input type="checkbox" class="btn_select_16 allAreaOption"/><spring:message code='str1367'/>--%>
             <label><spring:message code='str1307'/> <spring:message code='str4'/></label>
             <select id="pids" name="pids" onchange="setCurrentPlatform()"  style="width:100px" >
				<option value=""><spring:message code='str627'/></option>
				<c:forEach var="item" items="${fns:getGamePlatformList()}">
					<option id="${item.pid}" value="${item.pid}" >${item.name}</option>
				</c:forEach>
		   </select>
		     <label><spring:message code='str1135'/> <spring:message code='str4'/></label>
		     <input name="serverId"  id="serverId"   style="width: 80px" value=""/>&nbsp;&nbsp;
		     <input id="serverIdOne" name="serverIdOne"   style="width: 80px"value="" />&nbsp;
		   &nbsp;<input id="btn" class="btn btn-primary" type="button" value="<spring:message code='str1368'/>" onclick="selectServerId()"/>
		   
        </div>

    </div>
      <c:forEach items="${fns:getGamePlatformListContainServer()}" var="platform" varStatus="status">
        <c:set var = "status_len" value="${status.count}"></c:set>
        <div id="content" class="modal-form modal-example select2-container-multi" style="background: #fff4e5">
            <div class="modal-header" >
                <input type="checkbox" onclick="" id="selectAll_${status.index}" class="game-server-sort-name" Value='${platform.name}'/><small class="label label-info">${platform.name}</small>
            </div>
            <div id="game-server-list-${status.index}" class="modal-body">
                <c:forEach items="${platform.gameServerList}" var="item" varStatus="serverIdStatus">
                    <%--<input type="checkbox" class="ids_${status.index}" name="ids_${status.index}" value="${item.worldId}" id="${item.worldId}"/>${item.worldName}<spring:message code='str498'/>${item.worldId}<spring:message code='str499'/>--%>
                         <BUTTON id="serverId_${item.worldId}"   nameIds="${platform.name}" nameIndex="idIndex_${item.id}" status="${item.status}" checkedStatus="0"  followerId="${item.followerId }" onclick="isServerChecked(this)" class="btn <c:if test="${item.status == 0}">btn-success</c:if><c:if test="${item.status == 2}">btn-danger</c:if> <c:if test="${item.followerId > 0}">btn-warning</c:if> ">${item.name}</BUTTON>
                         
              </c:forEach>
            </div>
        </div>
        <div style="padding-bottom: 3px"></div>
    </c:forEach>
</div>


<%--<script type="text/javascript" src="${ctxStatic}/modules/tools/gameSe rverDialog.js"/>--%>
<script type="text/javascript">
    //var topDocument = (top.frame_content == undefined) ? top.document : top.frame_content['mainFrame'].document;
    var selectedGameServerIds="";
    $(function() {
    
        $(".game-server-sort-name").click(function(){
            var id =$(this).parent().next("div").attr("id");
             if(this.checked){
                $("#"+id+" BUTTON").each(function(){
                    $(this).removeAttr("class");
                    $(this).attr("class", "btn btn-default");
                    $(this).attr("checkedStatus", 1);
                });
            }else{
                $("#"+id+" BUTTON").each(function(){
                    $(this).removeAttr("class");
                    $(this).attr("checkedStatus", 0);
                    var status = this.attributes.status.nodeValue;
                     if(status == 0){
                       var followerId=this.attributes.followerId.nodeValue;
                        if(followerId  > 0){
                            $(this).attr("class", "btn btn-warning");
                        }else{
                        	  $(this).attr("class", "btn btn-success");
                        } 
                    }else{
                        $(this).attr("class", "btn btn-danger");
                    } 
                   
                });
            } 
           //<spring:message code='str1369'/>checkbox<spring:message code='str1379'/>
           $('.btn_select_16').attr('checked',
           $('INPUT[class=game-server-sort-name]:checked').length == $('INPUT[class=game-server-sort-name]').length);
            
        });

        var ids = top.frames["mainFrame"].document.getElementById("serverIds").value;
        if(ids == null || ids == ""){
            return;
        }
        var idsArray = ids.split(",");
        for (var i = 0 ; i < idsArray.length ; i++ ){
            var id = jQuery.trim(idsArray[i]);
            $("#serverId_"+id).removeAttr("class");
            $("#serverId_"+id).attr("class", "btn btn-default");
            $("#serverId_"+id).attr("checkedStatus", 1);
            //<spring:message code='str1377'/>
            var checkLength=$("#serverId_"+id).parent().find("button[class='btn btn-default']").length;
            //<spring:message code='str1372'/>button<spring:message code='str1373'/>ID
            var btnId=$("#serverId_"+id).parent().attr("id");
            //<spring:message code='str1374'/>checkbox <spring:message code='str1375'/>
            if(checkLength==$("#"+btnId+" BUTTON").length){
    			$("#serverId_"+id).parent().parent().find(".game-server-sort-name").attr("checked",true);
    		}else{
    			$("#serverId_"+id).parent().parent().find(".game-server-sort-name").removeAttr("checked");
    		}
          	//<spring:message code='str1369'/>checkbox<spring:message code='str1376'/>
             $('.btn_select_16').attr('checked',
                    $('INPUT[class=game-server-sort-name]:checked').length == $('INPUT[class=game-server-sort-name]').length);
        }
    });



    function isServerChecked(a) {
        var check = a.attributes.checkedStatus.nodeValue;
        var status = a.attributes.status.nodeValue;
        var followerId=a.attributes.followerId.nodeValue;
        if(check == 0){
            $(a).attr("checkedStatus", 1);
            $(a).removeAttr("class");
            $(a).attr("class", "btn btn-default");
        }else {
            $(a).removeAttr("class");
            $(a).attr("checkedStatus", 0);
             if(status == 0){
                /* $(a).attr("class", "btn btn-success"); */
                var followerId=a.attributes.followerId.nodeValue;
                if(followerId  > 0){
                    $(a).attr("class", "btn btn-warning");
                }else{
                	  $(a).attr("class", "btn btn-success");
                }
            }else{
                $(a).attr("class", "btn btn-danger");
            } 
            
        }
      	//<spring:message code='str1372'/>button<spring:message code='str1373'/>ID
        var id=$(a).parent().attr("id"); 
      	//<spring:message code='str1377'/>
        var length=$(a).parent().find("button[class='btn btn-default']").length;
      	//<spring:message code='str1374'/>checkbox <spring:message code='str1378'/>
		if(length==$("#"+id+" BUTTON").length){
			$(a).parent().parent().find(".game-server-sort-name").attr("checked",true);
		}else{
			$(a).parent().parent().find(".game-server-sort-name").removeAttr("checked");
		}
        //<spring:message code='str1369'/>checkbox<spring:message code='str1379'/>
        $('.btn_select_16').attr('checked',
            $('INPUT[class=game-server-sort-name]:checked').length == $('INPUT[class=game-server-sort-name]').length);       
    }

    function allSelectOrNot(a){
        if(a.checked){
            $("#content .game-server-sort-name").each(function(){
                $(this).attr("checked",true);
            })
            $("#content BUTTON").each(function(){
                $(this).removeAttr("class");
                $(this).attr("class", "btn btn-default");
                $(this).attr("checkedStatus", 1);
            });
        }else{
            $("#content .game-server-sort-name").each(function(){
                $(this).attr("checked",false);
            })
            $("#content  BUTTON").each(function(){
                $(this).removeAttr("class");
                $(this).attr("checkedStatus", 0);
               var status = this.attributes.status.nodeValue;
                 if(status == 0){
                	 var followerId=this.attributes.followerId.nodeValue;
                     if(followerId  > 0){
                         $(this).attr("class", "btn btn-warning");
                     }else{
                     	  $(this).attr("class", "btn btn-success");
                     }
                }else{
                    $(this).attr("class", "btn btn-danger");
                } 
               /*  var followerId=this.attributes.followerId.nodeValue;
                if(followerId  > 0){
                    $(this).attr("class", "btn btn-warning");

                } */
                
            });
        }
    }


    function myCallback(){        
    	   $("#content BUTTON").each(function(){
    	     var check = this.attributes.checkedStatus.nodeValue;
    	     var ids = this.attributes.id.nodeValue;
    	     var  id=ids.substring(9,ids.length);
    	     if(check == 1 && selectedGameServerIds =="") {
    	         selectedGameServerIds = id;
    	     }else if(check == 1) {
    	           selectedGameServerIds+=( ","+ id );
    	     }
    	});   
    	
        top.frames["mainFrame"].document.getElementById("serverIds").value = selectedGameServerIds;
    }
    

    function  selectServerId(){
    	var pidServer=$("#pids").children('option:selected').val();
         if (pidServer == "") {
             tips("<spring:message code='str1633'/>!");
             return;
         }else{
        	  $("#content BUTTON").each(function(){
	              var check = this.attributes.checkedStatus.nodeValue;
		         	if(check==1){
		       	    	 $(this).removeAttr("class");
                    	//  $(this).attr("class", "btn btn-success");
                    	   var status = this.attributes.status.nodeValue;
			                 if(status == 0){
			                	 var followerId=this.attributes.followerId.nodeValue;
			                     if(followerId  > 0){
			                         $(this).attr("class", "btn btn-warning");
			                     }else{
			                     	  $(this).attr("class", "btn btn-success");
			                     }
			                }else{
			                    $(this).attr("class", "btn btn-danger");
			                }  
			               $(this).attr("checkedStatus", 0); 
		         	}
		         	
	         	});    
	          
	         var serverId=$("#serverId").val();
	     	 var serverIdOne=$("#serverIdOne").val();
		     		$("#content BUTTON").each(function(){
		     		 var d =this.attributes.nameIds.nodeValue;
		 	     	if(d==pidServer){	
			       	     var ids = this.attributes.nameIndex.nodeValue;
			       	     var  id=ids.substring(8,ids.length);
			       	     if(parseInt(id)>=parseInt(serverId)&&parseInt(id)<= parseInt(serverIdOne)){
			       	    	 $(this).removeAttr("class");
			                 $(this).attr("class", "btn btn-default");
			                 $(this).attr("checkedStatus", 1);
			                 tips("选择成功");
			       	     } 
		 	     	}
		         });   
	     	 
      }
    	
    	
    	
    }
 
 
    
</script>

</body>
</html>
