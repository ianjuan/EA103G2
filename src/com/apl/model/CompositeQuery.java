package com.apl.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		if (columnName.equals("hos_rentfee") ) 
			aCondition = " ( " + columnName + " between " + value + " - " + " 2500 " + " and " + value + " + " + " 2500 " + " ) ";
		else if (columnName.equals("hos_add")) {
			aCondition = columnName + " like " + " '%" + value + "%' ";
		}else if (columnName.equals("hos_room")) {
			aCondition = columnName + " = " + " '" + value + "' ";
		}

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key);
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println(whereCondition);
			}
		}

		return whereCondition.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, String> map = new TreeMap<String, String>();
		map.put("hos_no", "hos_0000001");

		String finalSQL = "select * from house " + CompositeQuery.get_WhereCondition(map) + "order by hos_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
