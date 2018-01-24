package com.mokylin.cabal.modules.ws.web;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.utils.Encodes;
import com.mokylin.cabal.common.utils.Md5Utils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.utils.WebUtil;
import com.mokylin.cabal.common.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller("currencyConsume")
@RequestMapping("/ws/currencyConsume")
public class CurrencyConsumeController extends BaseController {
	
	
	private static final String API_KEY = "U2FxdGVkX157PGntBworK0C8chhb6248iUCxC5ADqQY";
	public static final int RET_CODE_SUCCESS = 1;  //查询成功
	public static final int RET_CODE_ERROR = 0;  //校验错误

	@ResponseBody
	@RequestMapping("")
	public String currencyAddExpend(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<Object, Object> parameters = WebUtil.getRequestParamMap(request);
		Error error = null;
        String server = MapUtils.getString(parameters, "server");
        if(StringUtils.isBlank(server)){
    	    String name=Encodes.urlEncode("服务器不能为空");
			error = new Error(RET_CODE_ERROR,name);
			return JSON.toJSONString(error);
        }
        String serverId="";
    	Map findAreaId = toolDaoTemplate.selectOne("gameArea.findAreaId", server);
    	if(findAreaId==null){
    		String name=Encodes.urlEncode("服务器不能为空");
			error = new Error(RET_CODE_ERROR,name);
			return JSON.toJSONString(error);
    	}
    	serverId=findAreaId.get("area_id").toString();
        /*String serverId = serverManager.getGameServer("37wan_1",server);*/
        String start_time = MapUtils.getString(parameters, "start_time");    
        String end_time = MapUtils.getString(parameters, "end_time");          
        String time = MapUtils.getString(parameters, "time");           
        String flag = MapUtils.getString(parameters, "flag");
		Map<Object, Object> parameter = Maps.newHashMap();
		Result result = null;
		
		if (!Md5Utils.md5To32(time + API_KEY).equals(flag)) {
			String name=Encodes.urlEncode("验证错误");
			error = new Error(RET_CODE_ERROR,name);
			return JSON.toJSONString(error);
        }
		if(!start_time.substring(0,8).equals(end_time.substring(0,8))){
			String name=Encodes.urlEncode("日期必须为同一天");
			error = new Error(RET_CODE_ERROR,name);
			return JSON.toJSONString(error);
		} 
		
		parameter.put("suffix", String.valueOf(start_time).substring(0,8));
		parameter.put("logDay", String.valueOf(start_time).substring(0,8));
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		start_time=start_time.substring(0, 4)+"-"+start_time.substring(4,6)+"-"+start_time.substring(6, 8)+" "+start_time.substring(8, 10)+":"+start_time.substring(10, 12)+":"+start_time.substring(12, 14);
		end_time=end_time.substring(0, 4)+"-"+end_time.substring(4,6)+"-"+end_time.substring(6, 8)+" "+end_time.substring(8, 10)+":"+end_time.substring(10, 12)+":"+end_time.substring(12, 14);
		Date startTime=format.parse(start_time);
		Date endTime=format.parse(end_time);
		parameter.put("server", serverId);
	    parameter.put("start_time", startTime.getTime());
	    parameter.put("end_time", endTime.getTime());
	    parameter.put("time", time);
		logger.info("CurrencyConsume 开始查询.........." + JSON.toJSONString(parameter));
		List<Map> costLog = logDaoTemplate.selectList("moneyFlowLog.currencyAddExpend", parameter);
		logger.info("CurrencyConsume 查询结果数量:" + costLog.size());
		if(costLog == null || costLog.size() < 1){
			String name=Encodes.urlEncode("查询数据为空");
			error = new Error(RET_CODE_ERROR,name);
			return JSON.toJSONString(error);
		}
		List currencyList =new ArrayList();
//	    String currency;
		StringBuilder builder = null;
	    for(int i=0;i<costLog.size();){

			Map tmp = costLog.get(i);
			builder = new StringBuilder();
			builder.append(tmp.get("user_name"));
			builder.append("\t");
			builder.append(tmp.get("role_id"));
			builder.append("\t");
			builder.append(tmp.get("role_name"));
			builder.append("\t");
			builder.append(tmp.get("server"));
			builder.append("\t");
			builder.append(tmp.get("currency_type"));
			builder.append("\t");
			builder.append("");
			builder.append("\t");
			builder.append(tmp.get("variation"));
			builder.append("\t");
			builder.append(tmp.get("remain"));
			builder.append("\t");
			builder.append(tmp.get("op_type"));
			builder.append("\t");
			builder.append("");
			builder.append("\t");
			builder.append("");
			builder.append("\t");
			builder.append("");
			builder.append("\t");
			builder.append(format.format(tmp.get("op_time")));
//	    	currency=costLog.get(i).get("user_name")+"\t"+costLog.get(i).get("role_id")+"\t"+costLog.get(i).get("role_name")+"\t"+costLog.get(i).get("server")+"\t"+costLog.get(i).get("currency_type")+"\t"+costLog.get(i).get("variation")+"\t"+costLog.get(i).get("remain")+"\t"+costLog.get(i).get("op_type")+"\t"+costLog.get(i).get("op_time");
	    	currencyList.add(builder.toString());
	    	costLog.remove(0);
	    }
	    result = new Result(RET_CODE_SUCCESS, currencyList);
		return JSON.toJSONString(result);
	}


  
	class Error{
		private int ret;
		private String msg;
		public int getRet() {
			return ret;
		}
		public void setRet(int ret) {
			this.ret = ret;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public Error(int ret, String msg) {
			super();
			this.ret = ret;
			this.msg = msg;
		}
		
	}
	
	class Result{
		private int ret;
		private List<Map> msg;

        public Result() {
        }

        public Result(int ret, List<Map> costLog) {
            this.ret = ret;
            this.msg = costLog;
        }

        public int getRet() {
            return ret;
        }

        public void setRet(int ret) {
            this.ret = ret;
        }

        public List<Map> getMsg() {
            return msg;
        }

        public void setMsg(List<Map> msg) {
            this.msg = msg;
        }
	}
	
	class Msg{
		String user_name;
		String role_id;
		String role_name;
		String  server;
		int currency_type;
		int variation;
		int remain;
		int op_type;
		Date op_time;
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getRole_id() {
			return role_id;
		}
		public void setRole_id(String role_id) {
			this.role_id = role_id;
		}
		public String getRole_name() {
			return role_name;
		}
		public void setRole_name(String role_name) {
			this.role_name = role_name;
		}
		public String getServer() {
			return server;
		}
		public void setServer(String server) {
			this.server = server;
		}
		public int getCurrency_type() {
			return currency_type;
		}
		public void setCurrency_type(int currency_type) {
			this.currency_type = currency_type;
		}
		public int getVariation() {
			return variation;
		}
		public void setVariation(int variation) {
			this.variation = variation;
		}
		public int getRemain() {
			return remain;
		}
		public void setRemain(int remain) {
			this.remain = remain;
		}
		public int getOp_type() {
			return op_type;
		}
		public void setOp_type(int op_type) {
			this.op_type = op_type;
		}
		public Date getOp_time() {
			return op_time;
		}
		public void setOp_time(Date op_time) {
			this.op_time = op_time;
		}
		public Msg(String user_name, String role_id, String role_name, String server, int currency_type, int variation,
				int remain, int op_type, Date op_time) {
			super();
			this.user_name = user_name;
			this.role_id = role_id;
			this.role_name = role_name;
			this.server = server;
			this.currency_type = currency_type;
			this.variation = variation;
			this.remain = remain;
			this.op_type = op_type;
			this.op_time = op_time;
		}
		
		
	}
	
	
	
	
}
