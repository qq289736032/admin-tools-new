/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config ) {
	var str=parent.location.href ;  //取得整个地址栏
	var d=str.substr(str.indexOf("=")+1);
	if(d=="en_US"){
		config.language = 'en.js'; 
	}else  if(d=="ko_KR"){
		config.language = 'ko.js'; 
	}else{
		config.language = 'zh-cn.js'; 
	}
	config.uiColor = '#f7f5f4';
	config.width = '99.7%'; 
	if (config.height == ''){
		config.height = '400px';
	}
	config.removePlugins = 'elementspath,scayt';
	config.disableNativeSpellChecker = false;
	config.resize_dir = 'vertical';
	config.keystrokes =[[ CKEDITOR.CTRL + 13 /*Enter*/, 'maximize' ]];	
	config.extraPlugins = 'tableresize';
	config.enterMode = CKEDITOR.ENTER_P;
	config.shiftEnterMode = CKEDITOR.ENTER_BR;
	config.font_names=str1653+'/'+str1653+";"+str1654+'/'+str1654+";"+str1655+'/'+str1655+"_GB2312;"+str1656+'/'+str1656+"_GB2312;"+str1657+'/'+str1657+";"+str1658+'/'+str1658+";"+str1659+'/'+str1659+';'+ config.font_names;
	config.image_previewText='&nbsp;';
	config.toolbar_default = [
		['Source','-','Templates','Preview'],
	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print'],
	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],['ShowBlocks'],['Image','Capture','Flash'],['Maximize'],
	    '/',
	    ['Bold','Italic','Underline','Strike','-'],
	    ['Subscript','Superscript','-'],
	    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	    ['Link','Unlink','Anchor'],
	    ['Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	    '/',
	    ['Styles','Format','Font','FontSize'],
	    ['TextColor','BGColor']
	];
	config.toolbar = 'default';
	if(config.ckfinderPath){
		config.filebrowserBrowseUrl = config.ckfinderPath+'/ckfinder.html?type=files&start=files:'+config.ckfinderUploadPath;
		config.filebrowserImageBrowseUrl = config.ckfinderPath+'/ckfinder.html?type=images&start=images:'+config.ckfinderUploadPath;
		config.filebrowserFlashBrowseUrl = config.ckfinderPath+'/ckfinder.html?type=flash&start=flash:'+config.ckfinderUploadPath;
		config.filebrowserUploadUrl = config.ckfinderPath+'/core/connector/java/connector.java?command=QuickUpload&type=files&currentFolder='+config.ckfinderUploadPath;
		config.filebrowserImageUploadUrl = config.ckfinderPath+'/core/connector/java/connector.java?command=QuickUpload&type=images&currentFolder='+config.ckfinderUploadPath;
		config.filebrowserFlashUploadUrl = config.ckfinderPath+'/core/connector/java/connector.java?command=QuickUpload&type=flash&currentFolder='+config.ckfinderUploadPath;
		config.filebrowserWindowWidth = '1000';
		config.filebrowserWindowHeight = '700';
	}
};
CKEDITOR.stylesSet.add( 'default', [
	/* Block Styles */
	{ name : str1665, element : 'p', styles : { 'text-indent' : '20pt' } },
	/* Inline Styles */
	{ name : str1660	, element : 'span', styles : { 'background-color' : 'Yellow' } },
	{ name : str1661	, element : 'span', styles : { 'background-color' : 'Lime' } },
	/* Object Styles */
	{ name : str1662, element : 'img', attributes : { 'style' : 'padding: 5px; margin-right: 5px', 'border' : '2', 'align' : 'left' } },
	{ name : str1663, element : 'img', attributes : { 'style' : 'padding: 5px; margin-left: 5px', 'border' : '2', 'align' : 'right' } },
	{ name : str1664, element : 'table', styles: { 'border-style': 'hidden', 'background-color' : '#E6E6FA' } }
]);
