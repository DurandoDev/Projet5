package com.openclassrooms.safetynets.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.openclassrooms.safetynets.model.Root;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component
public class FileReader {

//	public Root jsonDataFromUrl() {
//
//		HttpURLConnection connection;
//		String url = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
//		Root jsonObject = null;
//		try {
//			connection = (HttpURLConnection) new URL(url).openConnection();
//
//			connection.setRequestMethod("GET");
//			int responseCode = connection.getResponseCode();
//
//			if (responseCode != 200) {
//				throw new RuntimeException("HttpResponseCode: " + responseCode);
//
//			} else {
//
//				ObjectMapper mapper = new ObjectMapper();
//				jsonObject = mapper.readValue(new URL(url), Root.class);
//
//				System.out.println(jsonObject);
//			}
//			connection.disconnect();
//		} catch (ProtocolException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return jsonObject;
//	}
//	private static void deserializedJsonObject(){
//		Gson gson = new Gson();
//		String jsonO = gson.toJson("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
//	}


	public static Root jsonDataFromUrl() {

		HttpURLConnection connection;
		String url = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
		Root jsonObject = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();

			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();

			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);

			} else {

				ObjectMapper mapper = new ObjectMapper();
				jsonObject = mapper.readValue(new URL(url), Root.class);

				System.out.println(jsonObject);
			}
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
