package org.mybatis.db.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class SqlUtils {

	private static Pattern PATTERN = Pattern.compile("\\s{2,}|\t|\r|\n");
	
	public static String format(String sql){
		  String destSql = "";
		  if (StringUtils.isNotEmpty(sql)) {
			  Matcher m = PATTERN.matcher(sql);
			  destSql = m.replaceAll(" ");
		  }
		  return destSql;
	}
	
	
}
