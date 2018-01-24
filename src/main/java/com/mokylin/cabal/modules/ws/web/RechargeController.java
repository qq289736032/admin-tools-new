package com.mokylin.cabal.modules.ws.web;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/25
 * admin-tools
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.welfare.service.ReturnResourceService;

@Controller("charge")
@RequestMapping("/ws/charge")
public class RechargeController extends BaseController {
	@Autowired
	private ReturnResourceService returnResourceService;

    /**
     * 充值返回资源
     * @return
     */
    @RequestMapping(value = "returnResource")
    @ResponseBody
    public Result returnResource(String orderId,String pid,String serverId,String uid,int gold) {
    	
    	return returnResourceService.chargeReturnSource(orderId, pid, serverId, uid, gold);
    }
}
