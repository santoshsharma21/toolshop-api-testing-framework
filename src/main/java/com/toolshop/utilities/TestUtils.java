/**
 * 
 */
package com.toolshop.utilities;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.response.Response;

/**
 * 
 */
public class TestUtils {

	// return status code
	public static int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	// return int value
	public static int getIntValue(Response response, String key) {
		JSONObject obj = new JSONObject(response.asString());
		return obj.getInt(key);
	}

	// return string value
	public static String getStringValue(Response response, String key) {
		JSONObject obj = new JSONObject(response.asString());
		return obj.getString(key);
	}

	// return string from internal json array
	public static String getStringFromJsonArray(Response response, String arrayName) {
		JSONObject obj = new JSONObject(response.asString());
		return obj.getJSONArray(arrayName).getString(0);
	}
	
	// validates given field in non-empty in array of single or multiple jsonObject
	public static boolean isFieldEmpty(Response response, boolean singleObj, boolean allObj, String fieldName) {
		Set<Boolean> set = new HashSet<>();
		try {
			JSONArray json = new JSONArray(response.asString());
			if(singleObj) {
				return json.getJSONObject(0).isNull(fieldName);
			} else if(allObj) {
				for(int i=0; i<json.length(); i++) {
					if(!set.add(json.getJSONObject(i).isNull(fieldName))) {
						return false;
					}
				}
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	// return response header
	public static String getResponseHeaderValue(Response response, String valueName) {
		return response.getHeader(valueName);
	}

	// validates data types
	public static boolean validateIntDataType(Response response, String key) {
		JSONObject obj = new JSONObject(response.asString());
		return ((Object) obj.getInt(key)).getClass().getSimpleName().equals("Integer");
	}

	public static boolean validateStringDataType(Response response, String key) {
		JSONObject obj = new JSONObject(response.asString());
		return obj.getString(key).getClass().getSimpleName().equals("String");
	}

	
	// method loop through multiple json object inside response to check uniqueness
	// of id's
	public static boolean validateUniqueIds(Response response, String filedName) {
		Set<Integer> set = new HashSet<>();
		try {
			JSONArray jsonArray = new JSONArray(response.asString());
			for (int i = 0; i < jsonArray.length(); i++) {
				int id = jsonArray.getJSONObject(i).getInt(filedName);
				if (!set.add(id)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// validates response array is empty
	public static boolean isJsonArrayEmpty(Response response) {
		boolean flag = false;
		try {
			JSONArray jsonArray = new JSONArray(response.asString());
			if(jsonArray.isEmpty()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// validates array have multiple objects or not
	public static boolean isSingleObjectInArray(Response response) {
		try {
			JSONArray json = new JSONArray(response.asString());
			if(json.length() >= 2) {
				return false;
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		String r = """
//		        [
//		          {"id": 101, "name": "Brand A", "category": "Electronics"},
//		          {"id": 102, "name": "Brand B", "category": "Fashion"}
//		        ]
//		        """;
//		
//		System.out.println(isSingleObjectInArray(r));
//	}
}