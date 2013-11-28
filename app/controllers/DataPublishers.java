package controllers;

import java.util.ArrayList;

import models.DataPublisher;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import com.github.cleverage.elasticsearch.IndexClient;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class DataPublishers extends Controller {

	@SuppressWarnings("unchecked")
	@With(CorsWrapper.class)
	public static Result searchAll() {

		SearchResponse response = IndexClient.client
				.prepareSearch("gbiffrance-harvest")
				.setTypes("DataPublisher")
				.execute()
				.actionGet();

		Long nbHits = response.getHits().getTotalHits();
		System.out.println(nbHits);
		ArrayList<DataPublisher> dataPublisherList = new ArrayList<DataPublisher>();

		for (SearchHit hit : response.getHits()) {
			DataPublisher dataPublisher = new DataPublisher();
			dataPublisher.setId(Long.parseLong((String) hit.getSource()
					.get("_id")));
			dataPublisher.setClassName((String) hit.getSource()
					.get("className"));
			dataPublisher.setName((String) hit.getSource()
					.get("name"));
			dataPublisher.setDescription((String) hit.getSource()
					.get("description"));
			dataPublisher.setAdministrativeContact((String) hit.getSource()
					.get("administrativeContact"));
			dataPublisher.setTechnicalContact((String) hit.getSource()
					.get("technicalContact"));
			dataPublisher.setTags((ArrayList<String>) hit.getSource()
					.get("tags"));
			dataPublisherList.add(dataPublisher);
		}
		
		return ok(Json.toJson(dataPublisherList));
//		return ok(Response.toString());
	}

}
