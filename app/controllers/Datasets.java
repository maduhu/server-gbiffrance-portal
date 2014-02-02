package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.Callable;

import models.DataPublisher;
import models.Dataset;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.BaseQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.query.QueryFacet;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.filter.FilterFacet;

import org.elasticsearch.index.query.FilterBuilders;
import static org.elasticsearch.index.query.FilterBuilders.existsFilter;

import com.github.cleverage.elasticsearch.IndexClient;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import play.cache.Cache;
import play.cache.Cached;

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
	@Cached(key = "/dataset/")
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

	@With(CorsWrapper.class)
	public static Result get(String datasetId) {
		final String dId = datasetId;

		try {

			Callable<Result> get = new Callable<Result>() {
				@Override
				public Result call() throws Exception {
					GetResponse response = IndexClient.client
							.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.dataset"), play.Configuration.root().getString("gbif.elasticsearch.type.dataset"), dId)
							.execute().actionGet();
					return ok(Json.toJson(response.getSource()));
				}
			};

			return Cache.getOrElse("/dataset/"+datasetId, get, 3600*24);
		} catch (Exception e) {
			return internalServerError();
		}
	}

	protected static Result getStatisticsQuery(BaseQueryBuilder q) {
		Map<String, Long> result = new HashMap();

		SearchRequestBuilder query =  IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence"))
				.setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
				.setQuery(q)
				.setSearchType(SearchType.COUNT)
				.addFacet(FacetBuilders.termsFacet("kingdom")
					.field("kingdom_interpreted")
					.size(10))
				.addFacet(FacetBuilders.filterFacet("georeferenced",
					FilterBuilders.boolFilter()
						.must(existsFilter("decimalLatitude_interpreted"))
						.must(existsFilter("decimalLongitude_interpreted"))
					));
		
		System.out.println(query);

		SearchResponse response = query.execute().actionGet();

		TermsFacet kingdomFacets = (TermsFacet) response.getFacets().facetsAsMap().get("kingdom");
		FilterFacet geoReferencedFacets = (FilterFacet) response.getFacets().facetsAsMap().get("georeferenced");

		for(TermsFacet.Entry e : kingdomFacets) {
			result.put(e.getTerm().string(), new Long(e.getCount()));
		}

		result.put("georeferenced", geoReferencedFacets.getCount());
		result.put("total", response.getHits().getTotalHits());

		return ok(Json.toJson(result));

	}


	@With(CorsWrapper.class)
	public static Result getStatistics(Long datasetId) {
		final Long dId = datasetId;

		try {

			Callable<Result> get = new Callable<Result>() {
				@Override
				public Result call() throws Exception {
					return getStatisticsQuery(QueryBuilders.matchQuery("dataset", dId.toString()));
				}
			};

			return Cache.getOrElse("/dataset/"+datasetId+"/statistics", get, 3600*24);
		} catch (Exception e) {
			return internalServerError();
		}

	}

	@With(CorsWrapper.class)
	@Cached(key = "/dataset/statistics")
	public static Result getAllStatistics() {
		return getStatisticsQuery(QueryBuilders.matchAllQuery());
	}


}
