package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.DataPublisher;
import models.Dataset;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.query.QueryFacet;
import org.elasticsearch.search.facet.terms.TermsFacet;

import com.github.cleverage.elasticsearch.IndexClient;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class Datasets extends Controller{
	

	
	@SuppressWarnings("unchecked")
	private static Dataset createJson(SearchHit hit){
		Dataset dataset = new Dataset();
		dataset.setId(Long.parseLong((hit.getSource()
				.get("id")).toString()));
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
		
		@SuppressWarnings("rawtypes")
		HashMap dataPublisher = (HashMap) hit.getSource()
				.get("dataPublisher");
		dataset.setDataPublisher(new DataPublisher(Long.parseLong(dataPublisher.get("$id").toString())));
		return dataset;
	}
	
	@With(CorsWrapper.class)
	public static Result getAll() {

		SearchResponse response = IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.dataset"))
				.setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.dataset"))
				.setSize(100)
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
		GetResponse response = IndexClient.client
				.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.dataset"), play.Configuration.root().getString("gbif.elasticsearch.type.dataset"), datasetId)
				.execute().actionGet();
		return ok(Json.toJson(response.getSource()));
	}
	
	@With(CorsWrapper.class)
	public static Result getStatistics(String datasetId) {
		Map<String, Integer> result = new HashMap();
		
		SearchRequestBuilder query =  IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence"))
				.setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
				.setQuery(QueryBuilders.matchQuery("dataset", datasetId))
				.setSearchType(SearchType.COUNT)
				.addFacet(FacetBuilders.termsFacet("kingdom")
					    .field("kingdom_interpreted")
					    .size(10));
		
		System.out.println(query);

		SearchResponse response = query.execute().actionGet();
		
		TermsFacet facets = (TermsFacet)response.getFacets().facetsAsMap().get("kingdom");
		
		for(TermsFacet.Entry e : facets) {
			result.put(e.getTerm().string(), e.getCount());
		}

		return ok(Json.toJson(result));
	}


}
