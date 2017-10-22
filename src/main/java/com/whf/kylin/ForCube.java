package com.whf.kylin;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.whf.kylin.po.Cube;
import com.whf.kylin.po.Segment;

public class ForCube {
	
	private static HttpPut put;
	
	static {
		System.out.println("开始构造put");
		put = new HttpPut();
		String auth ="ADMIN:KYLIN";
        String code = new String(new Base64().encode(auth.getBytes()));
        put.addHeader("Authorization", "Basic " + code);
        put.addHeader("Content-Type",
                "application/json;charset=UTF-8");
		
        put.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        
	}

	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpGet get = new HttpGet("http://192.168.25.129:7070/kylin/api/cubes/"+args[0]);
		
		String auth ="ADMIN:KYLIN";
        String code = new String(new Base64().encode(auth.getBytes()));
        get.addHeader("Authorization", "Basic " + code);
        get.addHeader("Content-Type",
                "application/json;charset=UTF-8");
		
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		
		CloseableHttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		
		String result = EntityUtils.toString(entity, "UTF-8");
		
		Gson gson = new Gson();
		Cube cube = gson.fromJson(result, Cube.class);
		
		List<Segment> list = cube.getSegments();
		
		for (Segment segment : list) {
			if(/*segment.getInput_records() == 0l*/true) {
				if(segment.getDate_range_start().length() >  8) {
					System.out.println(parse(segment.getDate_range_start()) +"  "+ parse(segment.getDate_range_end()));
					/*CloseableHttpResponse response2 = client.execute(constructPut("http://192.168.25.129:7070/kylin/api/cubes/"+cube.getName()+"/rebuild",
							parse(segment.getDate_range_start()), parse(segment.getDate_range_end()), "REFRESH", "UTF-8"));
					HttpEntity entity2 = response2.getEntity();
					String string = EntityUtils.toString(entity2);
					System.out.println(string);
					EntityUtils.consume(entity2);
					response2.close();*/
				}
			}
		}
		
		EntityUtils.consume(entity);
		response.close();
		
	}
	
	public static Long parse(String origin) throws ParseException {
		String regis = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(regis);
		Long time = Long.valueOf(origin);
		Date parse = new Date(time);
		System.out.println(sdf.format(parse));
		return time;
	} 
	
	public static HttpPut constructPut(String url , Long start , Long end  ,String type,String charset) throws Exception {
		
		URI uri = new URI(url);
		put.setURI(uri );
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", start);
		map.put("endTime", end);
		map.put("buildType","REFRESH");
		map.put("forceMergeEmptySegment",true);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		System.out.println(json);
		
		StringEntity entity = new StringEntity(json);
		put.setEntity(entity );
		
		return put;
	}
	
}
