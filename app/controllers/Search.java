package controllers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.mvc.*;


public class Search extends Controller {

	@With(CorsWrapper.class)
	public static Result index() {
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();	 
		try { 
			SearchParser search = mapper.readValue(json.traverse(), SearchParser.class);	 
			for(int k=0; k<search.getScientificName().length; k++){
				if(!search.getScientificName()[k].equals(null)){
					return ok(json);
				}
			}
				
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ok("blblbl");
	}

}

