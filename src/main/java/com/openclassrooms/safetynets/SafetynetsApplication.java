package com.openclassrooms.safetynets;

import com.google.gson.Gson;
import com.openclassrooms.safetynets.model.Root;
import com.openclassrooms.safetynets.services.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SafetynetsApplication {

	public static void main(String[] args) throws Exception {

//		FileReader fileReader = null;
//		String jsonFile = fileReader.readUrl("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
//
//		Gson gson = new Gson();
//		Root root = gson.fromJson(jsonFile,Root.class);

		SpringApplication.run(SafetynetsApplication.class, args);
	}
}