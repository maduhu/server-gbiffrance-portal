package controllers;

import java.util.ArrayList;

import models.DataPublisher;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

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
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.datapublisher"))
				.setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.datapublisher"))
				.addSort("name", SortOrder.ASC)
				.setSize(100)
				.execute()
				.actionGet();

		ArrayList<DataPublisher> dataPublisherList = new ArrayList<DataPublisher>();

		for (SearchHit hit : response.getHits()) {
			DataPublisher dataPublisher = new DataPublisher();
			dataPublisher.setId(Long.parseLong(hit.getId()));
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
			dataPublisher.setType((String) hit.getSource()
					.get("type"));
			dataPublisher.setTown((String) hit.getSource()
					.get("town"));
			dataPublisherList.add(dataPublisher);
		}
		
		return ok(Json.toJson(dataPublisherList));
	}
	
	/**
	 * Fonction qui lance la requete sur ElasticSearch
	 * @param search
	 * @return
	 */
	@With(CorsWrapper.class)
	public static Result get(String datapublisherId) {
		System.out.println(datapublisherId);
		GetResponse response = IndexClient.client
				.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.datapublisher"), play.Configuration.root().getString("gbif.elasticsearch.type.datapublisher"), datapublisherId)
				.execute().actionGet();
		return ok(Json.toJson(response.getSource()));
	}

}
