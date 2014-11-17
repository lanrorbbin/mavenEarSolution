package cn.gov.dd.tax.util;

import com.google.gson.Gson;

public class GsonHelper {
public static final Gson GSON = new Gson();

public static <T> T fromJson(String json, Class<T> type) {
	return GSON.fromJson(json, type);
}
}
