package controllers;

import java.util.ArrayList;

import models.DataPublisher;
import models.Dataset;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import com.github.cleverage.elasticsearch.IndexClient;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class Datasets extends Controller{
	@SuppressWarnings("unchecked")
	@With(CorsWrapper.class)
	
	public static Result searchAll() {

		SearchResponse response = IndexClient.client
				.prepareSearch("gbiffrance-harvest")
				.setTypes("Dataset")
				.execute()
				.actionGet();

		Long nbHits = response.getHits().getTotalHits();
		System.out.println(nbHits);
		ArrayList<Dataset> datasetList = new ArrayList<Dataset>();

		for (SearchHit hit : response.getHits()) {
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
			datasetList.add(dataset);
		}
		
		return ok(Json.toJson(datasetList));
	}
}
