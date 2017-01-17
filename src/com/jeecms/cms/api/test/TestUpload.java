package com.jeecms.cms.api.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.upload.FileUpload;
import com.jeecms.common.util.Num62;
import com.jeecms.common.util.PayUtil;

public class TestUpload {

	public static void main(String[] args) {
		uploadVedio();
	}
	
	private static void uploadVedio(){
		String url="http://192.168.0.173:8080/jeecmsv8f/api/upload/o_upload.jspx?";
		FileUpload fileUpload = new FileUpload();
		String filePath="D:\\test\\1.mp4";
		Integer siteId=1;
		String type="vedio";
		Boolean mark=false;
		String nonce_str=RandomStringUtils.random(16,Num62.N62_CHARS);
		StringBuffer paramBuff=new StringBuffer();
		Map<String, String>param=new HashMap<String, String>();
		paramBuff.append("siteId="+siteId);
		paramBuff.append("&mark="+mark);
		paramBuff.append("&type="+type);
		paramBuff.append("&appId="+appId);
		paramBuff.append("&nonce_str="+nonce_str);
		String []params=paramBuff.toString().split("&");
		for(String p:params){
			String keyValue[]=p.split("=");
			param.put(keyValue[0], keyValue[1]);
		}
		String sign=PayUtil.createSign(param, appKey);
		paramBuff.append("&sign="+sign);
		String result ="";
		try {
			result= fileUpload.uploadFileWithHttpMime(siteId, type, mark,
					url, filePath, appId, nonce_str, sign);
			JSONObject json=new JSONObject(result);
			String status=(String) json.get("status");
			if(status.equals("true")){
				JSONObject bodyJson= (JSONObject) json.get("body");
				String uploadPath=(String) bodyJson.get("uploadPath");
				System.out.println("uploadPath->"+uploadPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static String appId="111111";
	private static String appKey="Sd6qkHm9o4LaVluYRX5pUFyNuiu2a8oi";

}
