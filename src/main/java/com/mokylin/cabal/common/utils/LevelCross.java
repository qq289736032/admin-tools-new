package com.mokylin.cabal.common.utils;

import com.mokylin.cabal.modules.sys.entity.Dict;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelCross {

	public static List<Map<String, Object>> getLevelList(List<Map<String, Object>> maxLevelMap,
			List<Map<String, Object>> dictList, int maxlevel, String level, String title, String num) {
		List<Map<String, Object>> levelList = new ArrayList<>();
		int[] totalNum = new int[dictList.size()];
		for (int i = 1; i <= maxlevel; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(level, i);
			int[] numList = new int[dictList.size()];
			for (Map<String, Object> tiansuoMap : maxLevelMap) {
				if (MapUtils.getInteger(tiansuoMap, level) != i) {
					continue;
				}
				int jobIndex = 0;
				for (int f = 0; f < dictList.size(); f++) {
					String starlevel = String.valueOf(dictList.get(f).get(title));
					if (MapUtils.getString(tiansuoMap, title).equals(starlevel)) {
						numList[jobIndex] = MapUtils.getInteger(tiansuoMap, num);
						totalNum[jobIndex] += numList[jobIndex];
					}
					jobIndex++;
				}
			}
			map.put("numList", numList);
			levelList.add(map);
		}
		return levelList;
	}

	public static List<Map<String, Object>> getLevelDictList(List<Map<String, Object>> maxLevelMap, List<Dict> dictList,
			int maxlevel, String level, String title, String num) {
		List<Map<String, Object>> levelList = new ArrayList<>();
		int[] totalNum = new int[dictList.size()];
		for (int i = 1; i <= maxlevel; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(level, i);
			int[] numList = new int[dictList.size()];
			for (Map<String, Object> tiansuoMap : maxLevelMap) {
				if (MapUtils.getInteger(tiansuoMap, level) != i) {
					continue;
				}
				int jobIndex = 0;
				for (Dict dict : dictList) {
					String starlevel = dict.getValue();
					if (MapUtils.getString(tiansuoMap, title).equals(starlevel)) {
						numList[jobIndex] = MapUtils.getInteger(tiansuoMap, num);
						totalNum[jobIndex] += numList[jobIndex];
					}
					jobIndex++;
				}
			}
			map.put("numList", numList);
			levelList.add(map);
		}
		return levelList;
	}

	public static List<Map<String, Object>> selectPcuu(List<Map<String, Object>> pccu) {
		List<Map<String, Object>> pccuList = new ArrayList<>();
		for (Map<String, Object> m : pccu) {
			String key = m.get("logDay").toString();
			String col = m.get("col").toString();
			String[] strArr = col.split(",");
			int[] intArr = new int[288];
			int size = strArr.length;
			for (int a = 0; a < 288; a++) {
				if(a + 1 > size) break;
				if("null".equals(strArr[a])) break;
				intArr[a] = Integer.parseInt(strArr[a]);
			}
			
			if(pccuList != null && pccuList.size() == 0)
			{
				Map<String, Object> pMap = new HashMap<>();
				pMap.put("logDay", key);
				pMap.put("RealTime", intArr);
				pccuList.add(pMap);
				continue;
			}
			
			boolean flag = containskey(pccuList, key);
			if(!flag) {
				Map<String, Object> pMap = new HashMap<>();
				pMap.put("logDay", key);
				pMap.put("RealTime", intArr);
				pccuList.add(pMap);
				continue;
			}

			for (Map<String, Object> pcuMap : pccuList) {
				String logDay = MapUtils.getString(pcuMap, "logDay");
				if (logDay.equals(MapUtils.getString(m, "logDay"))) {
					int[] tmpRealTime = (int[]) pcuMap.get("RealTime");
					for (int i = 0; i < 288; i++) {
						tmpRealTime[i] = intArr[i] + tmpRealTime[i];
					}
					pcuMap.put("RealTime", tmpRealTime);
				} 
			}
		}
		return pccuList;
	}
	
	private static boolean containskey(List<Map<String, Object>> list, String value) {
		boolean flag = false;
		for(Map<String, Object> map : list){
			if(MapUtils.getString(map, "logDay").equals(value)){
				flag = true;
				break;
			}
		}
		return  flag;
	}

}
