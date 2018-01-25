/**
 * @author zhixin wen <wenzhixin2010@gmail.com>
 * extensions: https://github.com/kayalshri/tableExport.jquery.plugin
 */

(function ($) {
    'use strict';
    var sprintf = $.fn.bootstrapTable.utils.sprintf;

    var TYPE_NAME = {
        json: 'JSON',
        xml: 'XML',
        png: 'PNG',
        csv: 'CSV',
        txt: 'TXT',
        sql: 'SQL',
        doc: 'MS-Word',
        excel: 'MS-Excel',
        powerpoint: 'MS-Powerpoint',
        pdf: 'PDF'
    };

    $.extend($.fn.bootstrapTable.defaults, {
        showExport: false,
        exportDataType: 'basic', // basic, all, selected
        // 'json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'powerpoint', 'pdf'
        exportTypes: ['json', 'xml', 'csv', 'txt', 'sql', 'excel'],
        exportOptions: {}
    });

    $.extend($.fn.bootstrapTable.defaults.icons, {
        export: 'glyphicon-export icon-share'
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initToolbar = BootstrapTable.prototype.initToolbar;

    BootstrapTable.prototype.initToolbar = function () {
        this.showToolbar = this.options.showExport;

        _initToolbar.apply(this, Array.prototype.slice.apply(arguments));

        if (this.options.showExport) {
            var that = this,
                $btnGroup = this.$toolbar.find('>.btn-group'),
                $export = $btnGroup.find('div.export');

            if (!$export.length) {
                $export = $([
                    '<div class="export btn-group">',
                        '<button class="btn btn-default' +
                            sprintf(' btn-%s', this.options.iconSize) +
                            ' dropdown-toggle" ' +
                            'data-toggle="dropdown" type="button">',
                            sprintf('<i class="%s %s"></i> ', this.options.iconsPrefix, this.options.icons.export),
                            '<span class="caret"></span>',
                        '</button>',
                        '<ul class="dropdown-menu" role="menu">',
                        '</ul>',
                    '</div>'].join('')).appendTo($btnGroup);

                var $menu = $export.find('.dropdown-menu'),
                    exportTypes = this.options.exportTypes;

                if (typeof this.options.exportTypes === 'string') {
                    var types = this.options.exportTypes.slice(1, -1).replace(/ /g, '').split(',');

                    exportTypes = [];
                    $.each(types, function (i, value) {
                        exportTypes.push(value.slice(1, -1));
                    });
                }
                $.each(exportTypes, function (i, type) {
                    if (TYPE_NAME.hasOwnProperty(type)) {
                        $menu.append(['<li data-type="' + type + '">',
                                '<a href="javascript:void(0)">',
                                    TYPE_NAME[type],
                                '</a>',
                            '</li>'].join(''));
                    }
                });

                $menu.find('li').click(function () {
                    var type = $(this).data('type'),
                        doExport = function () {
                            that.$el.tableExport($.extend({}, that.options.exportOptions, {
                                type: type,
                                escape: false
                            }));
                        };

                    if (that.options.exportDataType === 'all' && that.options.pagination) {
                        that.$el.one('load-success.bs.table page-change.bs.table', function () {
                            doExport();
                            that.togglePagination();
                        });
                        that.togglePagination();
                    } else if (that.options.exportDataType === 'selected') {
                        var data = that.getData(),
                            selectedData = that.getAllSelections();

                        that.load(selectedData);
                        doExport();
                        that.load(data);
                    } else {
                        doExport();
                    }
                });
                
                // 解决导出bug
	            var exportFlag = false ;
	            var exportFlag1 = false ;
	            var exportFlag2 = false ;
	            
	           
	            $("#dc").click(function (exportEvent) {
	            	if(typeof(exportDate)!="undefined"){
	            		if(new Date().getTime()-exportDate<500){
		            		return false;
		            	}
		            	exportDate = new Date().getTime();
	            	}
	            	
	            	var a = 1;
	            	$.each($("table").find("thead"),function(index,value){
	            		
	            		if($(value).attr("style")=="display: none;"){
	            			alert("请切换到表格样式再进行导出");
	            			a =2;
	            		}
	            	})
	            	if(a==2){
	            		return false;
	            	}
	            	
		            //更新导出状态
		            exportFlag = true ;
		            
		            var type = 'excel';
		            
	                var doExport = function () {
	                	
	                    that.$el.tableExport($.extend({}, that.options.exportOptions, {
	                        type: type,
	                        escape: false
	                    }));
	                    
	                };
		
		            if (that.options.exportDataType === 'all' && that.options.pagination) {
		                that.$el.one('load-success.bs.table page-change.bs.table', function () {
		                	
		                	if(!exportFlag){
		                		return true ;
		                	}
		                	
		                    doExport();
		                    
		                    that.togglePagination();
		                    
		                    exportFlag = false ;
		                    
		                });
		                
		                that.togglePagination();
		                
		            } else if (that.options.exportDataType === 'selected') {
		            	
		            	if(!exportFlag){
	                		return true ;
	                	}
		            	
		                var data = that.getData(),
		                    selectedData = that.getAllSelections();
		
		                that.load(selectedData);
		                doExport();
		                that.load(data);
		                
		                exportFlag = false ;
		                
		            } else {
		            	
		            	if(!exportFlag){
	                		return true ;
	                	}
	                	
		                doExport();
		                
		                exportFlag = false ;
		                
		            }
		            
		            //exportEvent.stopPropagation();
		            
		        });
	            
	            $("#dc1").click(function (exportEvent) {
	            	if(typeof(exportDate1)!="undefined"){
	            		if(new Date().getTime()-exportDate1<500){
		            		return false;
		            	}
		            	exportDate1 = new Date().getTime();
	            	}
	            	
	            	fileName = fileName1;
	            	var a = 1;
	            	$.each($("table").find("thead"),function(index,value){
	            		
	            		if($(value).attr("style")=="display: none;"){
	            			alert("请切换到表格样式再进行导出");
	            			a =2;
	            		}
	            	})
	            	if(a==2){
	            		return false;
	            	}
	            	
		            //更新导出状态
	            	exportFlag1 = true ;
		            
		            var type = 'excel';
		            
	                var doExport = function () {
	                	
	                    that.$el.tableExport($.extend({}, that.options.exportOptions, {
	                        type: type,
	                        escape: false
	                    }));
	                    
	                };
		
		            if (that.options.exportDataType === 'all' && that.options.pagination) {
		                that.$el.one('load-success.bs.table page-change.bs.table', function () {
		                	
		                	if(!exportFlag1){
		                		return true ;
		                	}
		                	
		                    doExport();
		                    
		                    that.togglePagination();
		                    
		                    exportFlag1 = false ;
		                    
		                });
		                
		                that.togglePagination();
		                
		            } else if (that.options.exportDataType === 'selected') {
		            	
		            	if(!exportFlag1){
	                		return true ;
	                	}
		            	
		                var data = that.getData(),
		                    selectedData = that.getAllSelections();
		
		                that.load(selectedData);
		                doExport();
		                that.load(data);
		                
		                exportFlag1 = false ;
		                
		            } else {
		            	
		            	if(!exportFlag1){
	                		return true ;
	                	}
	                	
		                doExport();
		                
		                exportFlag1 = false ;
		                
		            }
		            
		            //exportEvent.stopPropagation();
		            
		        });
	            
	            $("#dc2").click(function (exportEvent) {
	            	if(typeof(exportDate2)!="undefined"){
	            		if(new Date().getTime()-exportDate2<500){
		            		return false;
		            	}
		            	exportDate2 = new Date().getTime();
	            	}
	            	fileName = fileName2;
	            	var a = 1;
	            	$.each($("table").find("thead"),function(index,value){
	            		
	            		if($(value).attr("style")=="display: none;"){
	            			alert("请切换到表格样式再进行导出");
	            			a =2;
	            		}
	            	})
	            	if(a==2){
	            		return false;
	            	}
	            	
		            //更新导出状态
	            	exportFlag2 = true ;
		            
		            var type = 'excel';
		            
	                var doExport = function () {
	                	
	                    that.$el.tableExport($.extend({}, that.options.exportOptions, {
	                        type: type,
	                        escape: false
	                    }));
	                    
	                };
		
		            if (that.options.exportDataType === 'all' && that.options.pagination) {
		                that.$el.one('load-success.bs.table page-change.bs.table', function () {
		                	
		                	if(!exportFlag2){
		                		return true ;
		                	}
		                	
		                    doExport();
		                    
		                    that.togglePagination();
		                    
		                    exportFlag2 = false ;
		                    
		                });
		                
		                that.togglePagination();
		                
		            } else if (that.options.exportDataType === 'selected') {
		            	
		            	if(!exportFlag2){
	                		return true ;
	                	}
		            	
		                var data = that.getData(),
		                    selectedData = that.getAllSelections();
		
		                that.load(selectedData);
		                doExport();
		                that.load(data);
		                
		                exportFlag2 = false ;
		                
		            } else {
		            	
		            	if(!exportFlag2){
	                		return true ;
	                	}
	                	
		                doExport();
		                
		                exportFlag2 = false ;
		                
		            }
		            
		            //exportEvent.stopPropagation();
		            
		        });
                
            }
            
        }
        
        
    };
})(jQuery);
