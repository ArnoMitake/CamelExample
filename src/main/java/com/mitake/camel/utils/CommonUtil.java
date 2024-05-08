package com.mitake.camel.utils;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {
	private CommonUtil() {
	}

	public static CommonUtil commonUtil;

	public static synchronized CommonUtil getInstance() {
		if (commonUtil == null) {
			commonUtil = new CommonUtil();
		}
		return commonUtil;
	}

	/**
	 * 回傳正規手機門號(開頭8869轉為09)
	 * 
	 * @param destNo
	 * @return
	 */
	public String regularDestNo(String destNo) {
		if (StringUtils.isNotBlank(destNo) && StringUtils.left(destNo, 4).equals("8869")) {
			return "09" + StringUtils.substring(destNo, 4);
		} else {
			return destNo;
		}
	}

}
