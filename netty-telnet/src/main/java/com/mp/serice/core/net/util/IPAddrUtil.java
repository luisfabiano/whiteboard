package com.mp.serice.core.net.util;

import com.mp.serice.core.net.commons.exception.NetArgumentsException;

public class IPAddrUtil {

	private IPAddrUtil() {}

	public static Long getNumierCodeFormIpAddr(String ip) throws NetArgumentsException {
		if(null == ip || ip.trim().length()<1) {
			throw new NetArgumentsException("null param ip") ;
		}

		Long result = 0L;
		String[] ipElements = ip.split("\\.");
		// 字符串分割后的数组大小必须是4，多了少了都不是IP地址的格式
		if(ipElements.length<4) {
			throw new NetArgumentsException(String.format("Invalid param IP[%s]", ip)) ;
		}

		Integer[] elements = new Integer[4] ;
		for(int i=0;i<ipElements.length;i++) {
			try {
				elements[i] = Integer.parseInt(ipElements[i]) ;
			} catch (NumberFormatException e) {
				throw new NetArgumentsException(String.format("Invalid param IP[%s]", ip)) ;
			}
		}
		result = (Long.parseLong(ipElements[0]) << 24) + (Long.parseLong(ipElements[1]) << 16) + (Long.parseLong(ipElements[2]) << 8) + Long.parseLong(ipElements[3]);

		return result;
	}
}