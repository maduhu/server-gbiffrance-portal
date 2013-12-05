package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.query.QueryFacetBuilder;
import org.elasticsearch.search.facet.query.QueryFacet;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.SearchHit;

import com.github.cleverage.elasticsearch.IndexClient;

import play.mvc.*;
import play.libs.Json;


public class Search extends Controller {

	static public class GeoBound {
		public GeoPoint northWest;
		public GeoPoint southEast;

		public GeoBound(double northWestLatitude, double northWestLongitude, double southEastLatitude, double southEastLongitude) {
			this.northWest = new GeoPoint(northWestLatitude, northWestLongitude);
			this.southEast = new GeoPoint(southEastLatitude, southEastLongitude);
		}

		public GeoBound(GeoPoint nw, GeoPoint se) {
			this.northWest = nw;
			this.southEast = se;
		}

		public GeoPoint center() {
			return new GeoPoint(
				(northWest.lat() + southEast.lat()) / 2,
				(northWest.lon() + southEast.lon()) / 2);
		}

		public String toString() {
			return "GeoBound(NorthWest("+northWest.lat()+", "+northWest.lon()+"), SouthEast("+southEast.lat()+", "+southEast.lon()+"))";
		}

		public GeoBound subBound(int i, int j, int dividerX, int dividerY) {
			double gridX = (southEast.lat() - northWest.lat()) / dividerX;
			double gridY = (northWest.lon() - southEast.lon()) / dividerY;

			GeoPoint nw = new GeoPoint(
				northWest.lat() + (dividerX - i - 1) * gridX,
				southEast.lon() + (dividerY - j) * gridY);
			GeoPoint se = new GeoPoint(
				northWest.lat() + (dividerX - i) * gridX,
				southEast.lon() + (dividerY - j - 1) * gridY);

			return new GeoBound(nw, se);
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			GeoBound geoBound = (GeoBound) o;

			if (!this.northWest.equals(geoBound.northWest)) return false;
			if (!this.southEast.equals(geoBound.southEast)) return false;

			return true;
		}
	}

	static public class Zone {
		public double lat;
		public double lng;
		public long count;

		public Zone(double lat, double lng, long count) {
			this.lat = lat;
			this.lng = lng;
			this.count = count;
		}
	}

	@With(CorsWrapper.class)
	public static Result searchOccurrences() {
		Http.RequestHeader req = request();
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		try { 
			SearchParser search = mapper.readValue(json.traverse(), SearchParser.class);
			if (req.getQueryString("page") != null && req.getQueryString("size") != null) {
				Integer page;
				Integer size;

				try {
					page = Integer.parseInt(req.getQueryString("page"), 10);
					size = Integer.parseInt(req.getQueryString("size"), 10);
				} catch (Exception e) {
					return badRequest("invalid pager");
				}

				JsonNode jsonResult = Occurrences.searchOccurrences(search, page, size);
				return ok(jsonResult);

			} else {
				JsonNode jsonResult = Occurrences.searchOccurrences(search);
				return ok(jsonResult);
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return badRequest("Json incorrect");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return internalServerError("Oops");
	}

	public static class Marker{
		public double lat;
		public double lng;
		public long id;

		public Marker(double lat, double lng, long id) {
			this.lat = lat;
			this.lng = lng;
			this.id = id;
		}
	}

	@With(CorsWrapper.class)
	public static Result searchOccurrencesMarkers(double nwLat, double nwLng, double seLat, double seLng) {
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			SearchParser search = mapper.readValue(json.traverse(), SearchParser.class);
			BoolQueryBuilder baseQuery = Occurrences.buildRequestQuery(search);

			baseQuery
				.must(QueryBuilders.rangeQuery("decimalLatitude_interpreted")
					.gte(seLat)
					.lt(nwLat))
				.must(QueryBuilders.rangeQuery("decimalLongitude_interpreted")
					.gte(nwLng)
					.lt(seLng));

			SearchRequestBuilder query = IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence"))
				.setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
				.setQuery(baseQuery)
				.setSize(500);

			SearchResponse elasticResponse = query.execute().actionGet();
			ArrayList<Marker> response = new ArrayList<Marker>();

			for (SearchHit hit: elasticResponse.getHits().hits()) {
				Map<String, Object> h = hit.sourceAsMap();

				Double lat = (Double) h.get("decimalLatitude_interpreted");
				Double lon = (Double) h.get("decimalLongitude_interpreted");
				Long id = Long.parseLong(h.get("_id").toString(), 10);

				response.add(new Marker(lat, lon, id));
			}

			return ok(Json.toJson(response));

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return badRequest("Json incorrect");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return internalServerError("Oops");
	}


	@With(CorsWrapper.class)
	public static Result searchOccurrencesTile(double nwLat, double nwLng, double seLat, double seLng) {
		Integer divider = 4;
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			SearchParser search = mapper.readValue(json.traverse(), SearchParser.class);
			BoolQueryBuilder baseQuery = Occurrences.buildRequestQuery(search);

			baseQuery
				.must(QueryBuilders.rangeQuery("decimalLatitude_interpreted")
					.gte(seLat)
					.lt(nwLat))
				.must(QueryBuilders.rangeQuery("decimalLongitude_interpreted")
					.gte(nwLng)
					.lt(seLng));


			ArrayList<GeoBound> subTiles = new ArrayList<GeoBound>();
			ArrayList<QueryFacetBuilder> subQueries = new ArrayList<QueryFacetBuilder>();

			GeoBound global = new GeoBound(nwLat, nwLng, seLat, seLng);

			for (int i = 0; i < divider; i++) {
				for (int j = 0; j < divider; j++) {

					GeoBound bound = global.subBound(i, j, divider, divider);
					subTiles.add(bound);

					BoolQueryBuilder query = new BoolQueryBuilder();
					query
						.must(QueryBuilders.rangeQuery("decimalLatitude_interpreted")
							.gte(bound.southEast.lat())
							.lt(bound.northWest.lat()))
						.must(QueryBuilders.rangeQuery("decimalLongitude_interpreted")
							.gte(bound.northWest.lon())
							.lt(bound.southEast.lon()));

					subQueries.add(
						FacetBuilders.queryFacet("h" + i + "v" + j,
							query));
				}
			}

			SearchRequestBuilder query = IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence"))
				.setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
				.setQuery(baseQuery)
				.setSearchType(SearchType.COUNT);

			for(QueryFacetBuilder item : subQueries){
				query.addFacet(item);
			}

			SearchResponse elasticResponse = query.execute().actionGet();

			Map<String, Facet> facets = elasticResponse.getFacets().facetsAsMap();

			ArrayList<Zone> response = new ArrayList<Zone>();

			for (int i = 0; i < divider; i++) {
				for (int j = 0; j < divider; j++) {
					GeoBound bound = subTiles.remove(0);
					long count = ((QueryFacet) facets.get("h" + i + "v" + j)).getCount();

					response.add(new Zone(bound.center().lat(), bound.center().lon(), count));
				}
			}

			response().setHeader("Access-Control-Expose-Headers", "X-Map-Divider, X-Max-Hits");
			response().setHeader("X-Map-Divider", divider + "");
			response().setHeader("X-Max-Hits", elasticResponse.getHits().totalHits() + "");

			return ok(Json.toJson(response));

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return badRequest("Json incorrect");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return internalServerError("Oops");
	}

	@With(CorsWrapper.class)
	public static Result getSearchStatistic(){
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();	 
		try { 
			SearchParser search = mapper.readValue(json.traverse(), SearchParser.class);
			JsonNode jsonResult = Occurrences.statisticOccurrence(search);
			
			return ok(jsonResult);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return badRequest("Json incorrect");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return internalServerError("Oops");
	}
}

