/**
 * 格式化时间
 * @param fmt 
 * @param date 日期
 * @returns
 */
function getDate(fmt,date) { 
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)){
   	    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o){
        if (new RegExp("(" + k + ")").test(fmt)){
           fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

/**
 * 判断数组是否有重复
 * @param arr
 * @returns {Boolean}
 */
function isArrRepeat(arr) {
	var hash = {};
	for ( var i in arr) {
		if (hash[arr[i]]) {
			return true;
		}
		//不存在该元素，则赋值为true
		hash[arr[i]] = true;
	}
	return false;
}
/**
 * 判断数组里面是不是有不同的元素
 * @param arr
 * @returns {Boolean}
 */
function isDif(arr){
	var a = [];
	for(var i = 0;i<arr.length;i++){
		var index = $.inArray(arr[i],a);
		if(index==-1){
			a.push(arr[i]);
		}
	}
	if(a.length>1){
		return true;
	}
	return false;
}