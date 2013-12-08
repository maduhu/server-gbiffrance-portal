
package controllers;

import static org.elasticsearch.index.query.FilterBuilders.existsFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Dataset;
import models.Occurrence;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MatchQueryBuilder.Type;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.histogram.HistogramFacet;
import org.elasticsearch.search.facet.histogram.HistogramFacetBuilder;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.cleverage.elasticsearch.IndexClient;

public class Occurrences extends Controller {
	
	private enum Rank {
		KINGDOM, PHYLUM, CLASS, ORDER, FAMILY, GENUS, SPECIES, SUBSPECIES
	}
	
	public static class StatisticParser{
		private String term;
		private int count;
		private String name;
		private String typeFilter;
		
		
		public String getTypeFilter() { return typeFilter; }
		public void setTypeFilter(String typeFilter) { this.typeFilter = typeFilter;}
		public String getTerm() { return term; }
		public void setTerm(String term) { this.term = term;}
		public int getCount() { return count; }
		public void setCount(int count) { this.count = count; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
	}
	
	@SuppressWarnings("rawtypes")
	private static Occurrence createJsonOccurrence(Map map){
		Occurrence occurrence = new Occurrence();
		String str = (String) map.get("dataset");
		String delims = "\"";
		String[] tokens = str.split(delims);
		String datasetId = tokens[7];
		occurrence.setDatasetId(datasetId);
		GetResponse response = IndexClient.client
				.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.dataset"), 
						play.Configuration.root().getString("gbif.elasticsearch.type.dataset"), datasetId)
						.execute().actionGet();
	
		HashMap dataPublisher = (HashMap) response.getSource()
				.get("dataPublisher");
		
		GetResponse responseDatapublisher = IndexClient.client
				.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.datapublisher"), 
						play.Configuration.root().getString("gbif.elasticsearch.type.datapublisher"), dataPublisher.get("$id").toString())
						.execute().actionGet();
		
		occurrence.setDatapublisherName(responseDatapublisher.getSource().get("name").toString());
		
		if(map.get("datasetName")==null){
			occurrence.setDatasetName((String)response.getSource().get("name"));
		}else{
			occurrence.setDatasetName((String)map.get("datasetName"));
		}
		
		if(map.get("basisOfRecord")==null){
			occurrence.setBasisOfRecord("occurrence");
		}else{		
			occurrence.setBasisOfRecord((String)map.get("basisOfRecord"));
		}
		
		occurrence.setCatalogNumber((String)map.get("catalogNumber"));
		
		if(map.get("country")==null){
			occurrence.setCountry("non renseigné");
		}else{		
			occurrence.setCountry((String)map.get("country"));
		}
		occurrence.setId(Long.parseLong(map.get("_id").toString()));
		occurrence.setClassName((String)map.get("className"));
		occurrence.setTypee((String)map.get("type"));
		occurrence.setInstitutionCode((String)map.get("institutionCode"));
		occurrence.setCollectionCode((String)map.get("collectionCode"));
		occurrence.setStartDayOfYear((String)map.get("startDayOfYear"));
		occurrence.setEndDayofYear((String)map.get("endDayOfYear"));
		occurrence.setYear((String)map.get("yearInterpreted"));
		occurrence.setCountryCode((String)map.get("countryCode"));
		occurrence.setStateProvince((String)map.get("stateProvince"));
		occurrence.setMunicipality((String)map.get("municipality"));
		occurrence.setDecimalLatitude((String)map.get("decimalLatitude"));
		occurrence.setDecimalLongitude((String)map.get("decimalLongitude"));
		occurrence.setCoordinatePrecision((String)map.get("coordinatePrecision"));
		occurrence.setIdentifiedBy((String)map.get("identifiedBy"));
		occurrence.setNameAccordingToID((String)map.get("nameAccordingToID"));
		occurrence.setScientificName((String)map.get("scientificName"));
		occurrence.setNameAccordingTo((String)map.get("nameAccordingTo"));
		occurrence.setGenus((String)map.get("genus"));
		occurrence.setKingdom_interpreted((String)map.get("kingdom_interpreted"));
		occurrence.setPhylum_interpreted((String)map.get("phylum_interpreted"));
		occurrence.setClasss_interpreted((String)map.get("classs_interpreted"));
		occurrence.setOrderr_interpreted((String)map.get("orderr_interpreted"));
		occurrence.setFamily_interpreted((String)map.get("family_interpreted"));
		occurrence.setGenus_interpreted((String)map.get("genus_interpreted"));
		occurrence.setFamily_interpreted((String)map.get("family_interpreted"));
		occurrence.setSpecificEpithet_interpreted((String)map.get("specificEpithet_interpreted"));
		occurrence.setEcatConceptId((String)map.get("ecatConceptId"));
		occurrence.setEcatParentId((String)map.get("ecatConceptId"));
		occurrence.setTaxonStatus((String)map.get("taxonStatus"));
		occurrence.setIndividualCount((String)map.get("individualCount"));
		occurrence.setPhylum((String)map.get("phylum"));
		occurrence.setClasss((String)map.get("classs"));
		occurrence.setFamily((String)map.get("family"));
		occurrence.setFieldNumber((String)map.get("fieldNumber"));
		occurrence.setOccurrenceRemarks((String)map.get("occurrenceRemarks"));
		occurrence.setRecordedBy((String)map.get("recordedBy"));
		occurrence.setSex((String)map.get("sex"));
		if((Integer)map.get("year_interpreted")!=null)
			occurrence.setYear_interpreted((Integer)map.get("year_interpreted"));
		return occurrence;
	}
	private static Occurrence createJson(SearchHit hit){
		Occurrence occurrence = new Occurrence();
		
		if(hit.getSource().get("datasetName")==null){
			String str = (String) hit.getSource().get("dataset");
			String delims = "\"";
			String[] tokens = str.split(delims);
			String datasetId = tokens[7];
			GetResponse response = IndexClient.client
					.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.dataset"), 
							play.Configuration.root().getString("gbif.elasticsearch.type.dataset"), datasetId)
							.execute().actionGet();
			occurrence.setDatasetName((String)response.getSource().get("name"));
		}else{
			occurrence.setDatasetName((String) hit.getSource()
					.get("datasetName"));
		}
		
		if(hit.getSource().get("basisOfRecord")==null){
			occurrence.setBasisOfRecord("occurrence");
		}else{		
			occurrence.setBasisOfRecord((String) hit.getSource()
					.get("basisOfRecord"));
		}
		
		occurrence.setCatalogNumber((String) hit.getSource()
				.get("catalogNumber"));
		
		if(hit.getSource().get("country")==null){
			occurrence.setCountry("non renseigné");
		}else{		
			occurrence.setCountry((String) hit.getSource()
					.get("country"));
		}
		occurrence.setId(Long.parseLong((String) hit.getSource()
				.get("_id")));
		occurrence.setClassName((String) hit.getSource()
				.get("className"));
		occurrence.setTypee((String) hit.getSource()
				.get("type"));
		occurrence.setInstitutionCode((String) hit.getSource()
				.get("institutionCode"));
		occurrence.setCollectionCode((String) hit.getSource()
				.get("collectionCode"));
		occurrence.setStartDayOfYear((String) hit.getSource()
				.get("startDayOfYear"));
		occurrence.setEndDayofYear((String) hit.getSource()
				.get("endDayOfYear"));
		occurrence.setYear((String) hit.getSource()
				.get("yearInterpreted"));
		occurrence.setCountryCode((String) hit.getSource()
				.get("countryCode"));
		occurrence.setStateProvince((String) hit.getSource()
				.get("stateProvince"));
		occurrence.setMunicipality((String) hit.getSource()
				.get("municipality"));
		occurrence.setDecimalLatitude((String) hit.getSource()
				.get("decimalLatitude"));
		occurrence.setDecimalLongitude((String) hit.getSource()
				.get("decimalLongitude"));
		occurrence.setCoordinatePrecision((String) hit.getSource()
				.get("coordinatePrecision"));
		occurrence.setIdentifiedBy((String) hit.getSource()
				.get("identifiedBy"));
		occurrence.setNameAccordingToID((String) hit.getSource()
				.get("nameAccordingToID"));
		occurrence.setScientificName((String) hit.getSource()
				.get("scientificName"));
		occurrence.setNameAccordingTo((String) hit.getSource()
				.get("nameAccordingTo"));
		occurrence.setGenus((String) hit.getSource()
				.get("genus"));
		occurrence.setKingdom_interpreted((String) hit.getSource()
				.get("kingdom_interpreted"));
		occurrence.setPhylum_interpreted((String) hit.getSource()
				.get("phylum_interpreted"));
		occurrence.setClasss_interpreted((String) hit.getSource()
				.get("classs_interpreted"));
		occurrence.setOrderr_interpreted((String) hit.getSource()
				.get("orderr_interpreted"));
		occurrence.setFamily_interpreted((String) hit.getSource()
				.get("family_interpreted"));
		occurrence.setGenus_interpreted((String) hit.getSource()
				.get("genus_interpreted"));
		occurrence.setFamily_interpreted((String) hit.getSource()
				.get("family_interpreted"));
		occurrence.setSpecificEpithet_interpreted((String) hit.getSource()
				.get("specificEpithet_interpreted"));
		occurrence.setEcatConceptId((String) hit.getSource()
				.get("ecatConceptId"));
		occurrence.setEcatParentId((String) hit.getSource()
				.get("ecatConceptId"));
		occurrence.setTaxonStatus((String) hit.getSource()
				.get("taxonStatus"));
		occurrence.setIndividualCount((String) hit.getSource()
				.get("individualCount"));
		occurrence.setPhylum((String) hit.getSource()
				.get("phylum"));
		occurrence.setClasss((String) hit.getSource()
				.get("classs"));
		occurrence.setFamily((String) hit.getSource()
				.get("family"));
		occurrence.setFieldNumber((String) hit.getSource()
				.get("fieldNumber"));
		occurrence.setOccurrenceRemarks((String) hit.getSource()
				.get("occurrenceRemarks"));
		occurrence.setRecordedBy((String) hit.getSource()
				.get("recordedBy"));
		occurrence.setSex((String) hit.getSource()
				.get("sex"));
		occurrence.setDataset(new Dataset(Long.parseLong((String) hit.getSource()
				.get("dataset.id"))));
		occurrence.setYear_interpreted((Integer) hit.getSource()
				.get("year_interpreted"));
		return occurrence;
	}
	private static Occurrence createJsonListOccurrence(SearchHit hit){
		Occurrence occurrence = new Occurrence();
		occurrence.setId(Long.parseLong(hit.getSource()
				.get("_id").toString()));
		
		if(hit.getSource().get("datasetName")==null){
			String str = (String) hit.getSource().get("dataset");
			String delims = "\"";
			String[] tokens = str.split(delims);
			String datasetId = tokens[7];
			GetResponse response = IndexClient.client
					.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.dataset"), 
							play.Configuration.root().getString("gbif.elasticsearch.type.dataset"), datasetId)
							.execute().actionGet();
			occurrence.setDatasetName((String)response.getSource().get("name"));
		}else{
			occurrence.setDatasetName((String) hit.getSource()
					.get("datasetName"));
		}
		if(hit.getSource().get("basisOfRecord")==null){
			occurrence.setBasisOfRecord("occurrence");
		}else{		
			occurrence.setBasisOfRecord((String) hit.getSource()
					.get("basisOfRecord"));
		}
		occurrence.setCatalogNumber((String) hit.getSource()
				.get("catalogNumber"));
		if(hit.getSource().get("country")==null){
			occurrence.setCountry("non renseigné");
		}else{		
			occurrence.setCountry((String) hit.getSource()
					.get("country"));
		}
		
		occurrence.setDecimalLatitude((String) hit.getSource()
				.get("decimalLatitude"));
		occurrence.setDecimalLongitude((String) hit.getSource()
				.get("decimalLongitude"));
		occurrence.setCoordinatePrecision((String) hit.getSource()
				.get("coordinatePrecision"));
		occurrence.setScientificName((String) hit.getSource()
				.get("scientificName"));
		occurrence.setEcatConceptId((String) hit.getSource()
				.get("ecatConceptId"));
		occurrence.setYear_interpreted((Integer) hit.getSource()
				.get("year_interpreted"));
		return occurrence;
	}
	
	public static StatisticParser createJsonStatistic(String term, int count, String typeFilter, String name){
		
		StatisticParser statisticParser = new StatisticParser();
		statisticParser.setTerm(term);
		statisticParser.setCount(count);
		statisticParser.setTypeFilter(typeFilter);
		statisticParser.setName(name);
		return statisticParser;
	}
	
	/**
	 * Function that return all the occurrence documents stored in our ElasticSearch 
	 * @return result JSON
	 */
	public static Result searchAllOccurrences() {
		SearchResponse response = IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence"))
				.setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
				.execute().actionGet();
		ArrayList<Occurrence> occurrenceList = new ArrayList<Occurrence>();
		for (SearchHit hit : response.getHits())
			occurrenceList.add(createJson(hit));
		return ok(Json.toJson(occurrenceList));
	}
	

	
	public static BoolQueryBuilder buildRequestQuery(SearchParser search){
		
		BoolQueryBuilder taxaQuery = QueryBuilders.boolQuery();
		BoolQueryBuilder datasetQuery = QueryBuilders.boolQuery();
		BoolQueryBuilder finalQuery = new BoolQueryBuilder();
		BoolQueryBuilder geoQuery = QueryBuilders.boolQuery();
		finalQuery = QueryBuilders.boolQuery();
		
		if(search.getScientificNames()!= null){
			for(int i=0; i< search.getScientificNames().size(); ++i){
				Rank rank = Rank.valueOf(search.getScientificNames().get(i).getRank());
				switch (rank) {
					case KINGDOM:
						taxaQuery = taxaQuery
										.should(QueryBuilders.matchQuery("kingdom_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("kingdom", search.getScientificNames().get(i).getScientificName()));
						break;
					case PHYLUM:
						taxaQuery =taxaQuery
										.should(QueryBuilders.matchQuery("phylum_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("phylum", search.getScientificNames().get(i).getScientificName()));
						break;
					case CLASS:
						taxaQuery = taxaQuery
										.should(QueryBuilders.matchQuery("classs_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("classs", search.getScientificNames().get(i).getScientificName()));
						break;
					case ORDER:
						taxaQuery = taxaQuery
										.should(QueryBuilders.matchQuery("orderr_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("orderr", search.getScientificNames().get(i).getScientificName()));
						break;
					case FAMILY:
						taxaQuery = taxaQuery
										.should(QueryBuilders.matchQuery("family_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("family", search.getScientificNames().get(i).getScientificName()));
						break;
					case GENUS:
						taxaQuery = taxaQuery
										.should(QueryBuilders.matchQuery("genus_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("genus", search.getScientificNames().get(i).getScientificName()));
						break;
					case SPECIES:
						taxaQuery = taxaQuery
										.should((QueryBuilders.matchQuery("scientificName", search.getScientificNames().get(i).getScientificName())).type(Type.PHRASE_PREFIX).analyzer("simple"))
										.should(QueryBuilders.matchQuery("specificEpithet_interpreted", search.getScientificNames().get(i).getScientificName()).type(Type.PHRASE_PREFIX).analyzer("simple"));
						break;
					case SUBSPECIES:
						taxaQuery = taxaQuery
										.should((QueryBuilders.matchQuery("scientificName", search.getScientificNames().get(i).getScientificName())).type(Type.PHRASE_PREFIX).analyzer("simple"))
										.should(QueryBuilders.matchQuery("specificEpithet_interpreted", search.getScientificNames().get(i).getScientificName()).type(Type.PHRASE_PREFIX).analyzer("simple"));
						break;
					default:
						taxaQuery = taxaQuery
										.should((QueryBuilders.matchQuery("scientificName", search.getScientificNames().get(i).getScientificName())).type(Type.PHRASE_PREFIX).analyzer("simple"))
										.should(QueryBuilders.matchQuery("specificEpithet_interpreted", search.getScientificNames().get(i).getScientificName()).type(Type.PHRASE_PREFIX).analyzer("simple"));
						break;
				}
			}
			finalQuery = finalQuery.must(taxaQuery);
		}
		
		if(search.getDataset() != null){
			for(int i=0; i< search.getDataset().size(); ++i){
				datasetQuery = datasetQuery
//									.should(QueryBuilders.matchQuery("dataset.$id", search.getDataset().get(i)));
									.should(QueryBuilders.matchQuery("dataset", search.getDataset().get(i)));
			}
			finalQuery = finalQuery.must(datasetQuery)
;		}
		
		if(search.getLatitude()!=null || search.getLongitude() != null || search.getLocalities() != null || search.getBoundingBox() !=null){
			
			if(search.getBoundingBox() != null){
				BoolQueryBuilder boundingBoxQuery = new BoolQueryBuilder();
				for(int i=0; i< search.getBoundingBox().size(); ++i){
					geoQuery = geoQuery.should(boundingBoxQuery
								.must(QueryBuilders.rangeQuery("decimalLatitude")
										.gte(search.getBoundingBox().get(i).getBounds().get_southWest().getLat())
										.lt(search.getBoundingBox().get(i).getBounds().get_northEast().getLat()))
								.must(QueryBuilders.rangeQuery("decimalLongitude")
										.gte(search.getBoundingBox().get(i).getBounds().get_southWest().getLng())
										.lt(search.getBoundingBox().get(i).getBounds().get_northEast().getLng())));
				}
			}
			
			if(search.getLatitude() != null){
				BoolQueryBuilder latitudeQuery = new BoolQueryBuilder();
				for(int i=0; i< search.getLatitude().size(); ++i){
					if(search.getLatitude().get(i).getFilter().equals("inf")){
						geoQuery = geoQuery.should(latitudeQuery
									.should(QueryBuilders.rangeQuery("decimalLatitude")
											.lte(search.getLatitude().get(i).getBound())));
					}else{
						geoQuery = geoQuery.should(latitudeQuery
								.should(QueryBuilders.rangeQuery("decimalLatitude")
										.gte(search.getLatitude().get(i).getBound())));
					}		
				}
			}
			
			if(search.getLongitude() != null){
				BoolQueryBuilder longitudeQuery = new BoolQueryBuilder();
				for(int i=0; i< search.getLongitude().size(); ++i){
					if(search.getLongitude().get(i).getFilter().equals("inf")){
						geoQuery = geoQuery.should(longitudeQuery
									.should(QueryBuilders.rangeQuery("decimalLongitude")
											.lte(search.getLongitude().get(i).getBound())));
					}else{
						geoQuery = geoQuery.should(longitudeQuery
								.should(QueryBuilders.rangeQuery("decimalLongitude")
										.gte(search.getLongitude().get(i).getBound())));
					}		
				}
			}
			
			if(search.getLocalities()!=null){
				BoolQueryBuilder localityQuery = new BoolQueryBuilder();
				for(int i=0; i< search.getLocalities().size(); ++i){
					geoQuery = geoQuery.should(localityQuery
								.must(QueryBuilders.rangeQuery("decimalLatitude")
										.gte(search.getLocalities().get(i).getBounds()[0])
										.lt(search.getLocalities().get(i).getBounds()[1]))
								.must(QueryBuilders.rangeQuery("decimalLongitude")
										.gte(search.getLocalities().get(i).getBounds()[2])
										.lt(search.getLocalities().get(i).getBounds()[3])));
				}
			}
			finalQuery = finalQuery.must(geoQuery);
		}
		

		return finalQuery;		
	}
	
	public static BoolFilterBuilder buildRequestFilter(SearchParser search){
		BoolFilterBuilder finalFilter = new BoolFilterBuilder();
		finalFilter = FilterBuilders.boolFilter();
		finalFilter.must(FilterBuilders.matchAllFilter());
		
		if(search.getDate() != null){
			finalFilter = finalFilter
							.must(FilterBuilders.rangeFilter("year_interpreted").from(search.getDate().getBeginDate()).to(search.getDate().getEndDate()));
		}
		System.out.println(search.isGeolocalizedData());
		if(search.isGeolocalizedData()==true){
			finalFilter = finalFilter
							.must(existsFilter("decimalLatitude_interpreted"))
							.must(existsFilter("decimalLongitude_interpreted"));
		}
		
		return finalFilter;
		
	}
	
	/**
	 * Fonction qui lance la requete sur ElasticSearch
	 * @param search
	 * @return
	 */
	public static JsonNode searchOccurrences(SearchParser search) {
		return searchOccurrences(search, 0, 10);
	}

	public static JsonNode searchOccurrences(SearchParser search, Integer page, Integer size) {
		
		BoolQueryBuilder searchQuery = buildRequestQuery(search);
		SearchResponse response = new SearchResponse();
		BoolFilterBuilder searchFilter = buildRequestFilter(search);
		
		response = IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence")).setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
				.setQuery(searchQuery)
				.setFilter(searchFilter)
				.setSize(size)
				.setFrom(size * page)
				.execute().actionGet();
		
		System.out.println(response);
		
		response().setHeader("Access-Control-Expose-Headers", "X-Max-Hits");
		response().setHeader("X-Max-Hits", response.getHits().totalHits() + "");
		
		ArrayList<Occurrence> occurrenceList = new ArrayList<Occurrence>();
		for (SearchHit hit : response.getHits())
			occurrenceList.add(createJsonListOccurrence(hit));
		
		return Json.toJson(occurrenceList);
	}
	
	/**
	 * Fonction qui lance la requete sur ElasticSearch
	 * @param search
	 * @return
	 */
	@With(CorsWrapper.class)
	public static Result get(String occurrenceId) {
		System.out.println(occurrenceId);
		GetResponse response = IndexClient.client
				.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence"), play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"), occurrenceId)
				.execute().actionGet();
		
		return ok(Json.toJson(createJsonOccurrence(response.getSource())));

	}
	
	public static TermsFacetBuilder statTaxon (SearchParser search, BoolFilterBuilder searchFilter){
	
		TermsFacetBuilder canonicalNameFacet = new TermsFacetBuilder("ecatConceptId").size(20);
		canonicalNameFacet.field("ecatConceptId");
		
		if (searchFilter != null)
			canonicalNameFacet.facetFilter(searchFilter);
		return canonicalNameFacet;
	}
	
	public static TermsFacetBuilder statDataset (SearchParser search, BoolFilterBuilder searchFilter){
		
		TermsFacetBuilder datasetFacet = new TermsFacetBuilder("datasetId").size(20);
		datasetFacet.field("datasetId");
		
		if (searchFilter != null)
			datasetFacet.facetFilter(searchFilter);
		return datasetFacet;
	}
	
	public static HistogramFacetBuilder statDate (SearchParser search, BoolFilterBuilder searchFilter){
		
		HistogramFacetBuilder dateFacet = FacetBuilders.histogramFacet("dateFacet")
										.field("year_interpreted")
										.interval(1);
		if (searchFilter != null)
			dateFacet.facetFilter(searchFilter);
		return dateFacet;
	}
	
	
	public static JsonNode statisticOccurrence(SearchParser search){
		SearchResponse response = new SearchResponse();
	
		BoolQueryBuilder searchQuery = buildRequestQuery(search);
		BoolFilterBuilder searchFilter = buildRequestFilter(search);
		TermsFacetBuilder taxaFacet = statTaxon(search, searchFilter);
		TermsFacetBuilder datasetFacet = statDataset(search, searchFilter);
		HistogramFacetBuilder dateFacet = statDate(search, searchFilter);
		
		response = IndexClient.client
				.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence")).setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
				.setQuery(searchQuery)
				.setFilter(searchFilter)
				.addFacet(taxaFacet)
				.addFacet(datasetFacet)
				.addFacet(dateFacet)
				.execute().actionGet();
		System.out.println(response);
		ArrayList<StatisticParser> statList = new ArrayList<StatisticParser>();
		TermsFacet resultFacetTaxa = (TermsFacet) response.getFacets().facetsAsMap().get("ecatConceptId");
		TermsFacet resultFacetDataset = (TermsFacet) response.getFacets().facetsAsMap().get("datasetId");
		HistogramFacet resultFacetDate = (HistogramFacet) response.getFacets().facetsAsMap().get("dateFacet");

		for (TermsFacet.Entry entry : resultFacetTaxa){
			SearchResponse getScientificName = IndexClient.client
												.prepareSearch(play.Configuration.root().getString("gbif.elasticsearch.index.occurrence")).setTypes(play.Configuration.root().getString("gbif.elasticsearch.type.occurrence"))
												.setQuery(QueryBuilders.matchQuery("ecatConceptId", entry.getTerm().string()))
												.setSize(1)
												.execute().actionGet();								
			statList.add(createJsonStatistic(entry.getTerm().string(), entry.getCount(), "taxa", getScientificName.getHits().getAt(0).getSource().get("scientificName").toString()));
		}
		
		for (TermsFacet.Entry entry : resultFacetDataset){
			GetResponse getDataset = IndexClient.client
					.prepareGet(play.Configuration.root().getString("gbif.elasticsearch.index.dataset"), play.Configuration.root().getString("gbif.elasticsearch.type.dataset"), entry.getTerm().string())
					.execute().actionGet();							
			statList.add(createJsonStatistic(entry.getTerm().string(), entry.getCount(), "dataset", getDataset.getSource().get("title").toString()));
		}
		
		for(HistogramFacet.Entry entry : resultFacetDate){
			statList.add(createJsonStatistic(Long.toString(entry.getKey()), (int) entry.getCount(), "year", "null"));
		}
		
		return Json.toJson(statList);
	}
	


}
