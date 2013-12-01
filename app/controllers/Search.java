package controllers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.mvc.*;


public class Search extends Controller {

	@With(CorsWrapper.class)
	public static Result searchOccurrences() {
		Occurrences occurrenceCtrl = new Occurrences();
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();	 
		try { 
			SearchParser search = mapper.readValue(json.traverse(), SearchParser.class);
			JsonNode jsonResult = occurrenceCtrl.SearchOccurrences(search);	
			return ok(jsonResult);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ok("bla");
	}
	
}

