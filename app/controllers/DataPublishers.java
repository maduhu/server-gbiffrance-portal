package controllers;

import java.util.ArrayList;
import java.util.concurrent.Callable;

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
import play.cache.Cache;
import play.cache.Cached;

public class DataPublishers extends Controller {

	@With(CorsWrapper.class)
	@Cached(key = "/datapublishers/")
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
			dataPublisher.setEmail((String) hit.getSource()
					.get("email"));
			dataPublisher.setPhone((String) hit.getSource()
					.get("phone"));
			dataPublisher.setUuid((String) hit.getSource()
					.get("uuid"));
			dataPublisherList.add(dataPublisher);
		}
		
		return ok(Json.toJson(dataPublisherList));
	}
	
	@With(CorsWrapper.class)
	public static Result get(Long datapublisherId) {
		final Long dpId = datapublisherId;

		try {

			Callable<Result> get = new Callable<Result>() {
				@Override
				public Result call() throws Exception {
					GetResponse response = IndexClient.client
							.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.datapublisher"), play.Configuration.root().getString("gbif.elasticsearch.type.datapublisher"), dpId.toString())
							.execute().actionGet();
					return ok(Json.toJson(response.getSource()));
				}
			};

			return Cache.getOrElse("/datapublisher/"+datapublisherId, get, 3600*24);
		} catch (Exception e) {
			return internalServerError();
		}

	}

}
