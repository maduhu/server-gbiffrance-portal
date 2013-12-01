package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DataPublisher;
import models.Dataset;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.cleverage.elasticsearch.IndexClient;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class Datasets extends Controller{
	
	@SuppressWarnings("unchecked")
	private static Dataset createJson(SearchHit hit){
		Dataset dataset = new Dataset();
		dataset.setId(Long.parseLong((String) hit.getSource()
				.get("_id")));
		dataset.setClassName((String) hit.getSource()
				.get("className"));
		dataset.setName((String) hit.getSource()
				.get("name"));
		dataset.setUrl((String)hit.getSource()
				.get("url"));
		dataset.setType((String)hit.getSource()
				.get("type"));
		dataset.setStatus((String)hit.getSource()
				.get("status"));
		dataset.setTempDirectory((String)hit.getSource()
				.get("tempDirectory"));
		dataset.setFromOutside((Boolean)hit.getSource()
				.get("fromOutside"));
		dataset.setTitle((String)hit.getSource()
				.get("title"));
		dataset.setDescription((String) hit.getSource()
				.get("description"));
		dataset.setTagsText((String) hit.getSource()
				.get("tagsText"));
		dataset.setTags((ArrayList<String>) hit.getSource()
				.get("tags"));
		dataset.setDataPublisher(new DataPublisher(Long.parseLong((String) hit.getSource()
				.get("dataPublisherId"))));
		return dataset;
	}
	
	@With(CorsWrapper.class)
	public static Result getAll() {

		SearchResponse response = IndexClient.client
				.prepareSearch("gbiffrance-harvest")
				.setTypes("Dataset")
				.execute()
				.actionGet();
		
		List<Dataset> datasetList = new ArrayList<Dataset>();

		for (SearchHit hit : response.getHits()) 
			datasetList.add(createJson(hit));
		
		return ok(Json.toJson(datasetList));
	}
	
	/**
	 * Fonction qui lance la requete sur ElasticSearch
	 * @param search
	 * @return
	 */
	@With(CorsWrapper.class)
	public static Result get(String datasetId) {
		System.out.println(datasetId);
		GetResponse response = IndexClient.client
				.prepareGet("gbiffrance-harvest", "Dataset", datasetId)
				.execute().actionGet();
		return ok(Json.toJson(response.getSource()));
	}

}
