<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="replace" type="java.lang.String" required="true" description="需要替换的textarea编号"%>
<%@ attribute name="uploadPath" type="java.lang.String" required="false" description="文件上传路径，路径后自动添加年份。若不指定，则编辑器不可上传文件"%>
<%@ attribute name="height" type="java.lang.String" required="false" description="编辑器高度"%>
<script type="text/javascript">include('ckeditor_lib','${ctxStatic}/ckeditor/',['ckeditor.js']);</script>
<script type="text/javascript">
	var ${replace}Ckeditor = CKEDITOR.replace("${replace}");
	${replace}Ckeditor.config.height = "${height}";//<c:if test="${not empty uploadPath}">
	${replace}Ckeditor.config.ckfinderPath="${ctxStatic}/ckfinder";

	var date = new Date(), year = date.getFullYear(), month = (date.getMonth()+1)>9?date.getMonth()+1:"0"+(date.getMonth()+1);
	<%--${replace}Ckeditor.config.ckfinderUploadPath="${uploadPath}/"+year+"/"+month+"/";//--%>
	</c:if>

	//默认 link 为target="_blank"
	CKEDITOR.on( 'dialogDefinition', function( ev )
	{
		// Take the dialog name and its definition from the event data.
		var dialogName = ev.data.name;
		var dialogDefinition = ev.data.definition;

		// Check if the definition is from the dialog we're
		// interested on (the Link dialog).
		if ( dialogName == 'link' )
		{
			// FCKConfig.LinkDlgHideAdvanced = true
			//dialogDefinition.removeContents( 'advanced' );

			// FCKConfig.LinkDlgHideTarget = true
			//dialogDefinition.removeContents( 'target' );
			/*
			 Enable this part only if you don't remove the 'target' tab in the previous block.

			 // FCKConfig.DefaultLinkTarget = '_blank'
			 // Get a reference to the "Target" tab.
			 var targetTab = dialogDefinition.getContents( 'target' );
			 // Set the default value for the URL field.
			 var targetField = targetTab.get( 'linkTargetType' );
			 targetField[ 'default' ] = '_blank';
			 */
			var targetTab = dialogDefinition.getContents( 'target' );
			var targetField = targetTab.get( 'linkTargetType' );
			targetField[ 'default' ] = '_blank';
		}

		if ( dialogName == 'image' )
		{
			// FCKConfig.ImageDlgHideAdvanced = true
			dialogDefinition.removeContents( 'advanced' );
			// FCKConfig.ImageDlgHideLink = true
			dialogDefinition.removeContents( 'Link' );
		}

		if ( dialogName == 'flash' )
		{
			// FCKConfig.FlashDlgHideAdvanced = true
			dialogDefinition.removeContents( 'advanced' );
		}

	});
</script>