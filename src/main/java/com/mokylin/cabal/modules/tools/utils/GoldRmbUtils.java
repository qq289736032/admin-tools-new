package com.mokylin.cabal.modules.tools.utils;

import java.math.BigDecimal;

public class GoldRmbUtils {
	public static BigDecimal GoldChangeToRmb(int gold){
		return new BigDecimal(gold).divide(BigDecimal.TEN);
	}
	public static int RmbChangeToGold(int rmb){
		return rmb*10;
	}
	public static void main(String[] args) {
		System.out.println(GoldChangeToRmb(15));
	}
}
