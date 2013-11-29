
package controllers;

import java.util.ArrayList;

import models.Dataset;
import models.Occurrence;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MatchQueryBuilder.Type;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.search.SearchHit;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.cleverage.elasticsearch.IndexClient;

public class Occurrences extends Controller {
	
	private enum Rank {
		KINGDOM, PHYLUM, CLASS, ORDER, FAMILY, GENUS, SPECIES, SUBSPECIES
	}
	
	private static Occurrence createJson(SearchHit hit){
		Occurrence occurrence = new Occurrence();
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
		occurrence.setDatasetName((String) hit.getSource()
				.get("datasetName"));
		occurrence.setBasisOfRecord((String) hit.getSource()
				.get("basisOfRecord"));
		occurrence.setCatalogNumber((String) hit.getSource()
				.get("catalogNumber"));
		occurrence.setStartDayOfYear((String) hit.getSource()
				.get("startDayOfYear"));
		occurrence.setEndDayofYear((String) hit.getSource()
				.get("endDayOfYear"));
		occurrence.setYear((String) hit.getSource()
				.get("yearInterpreted"));
		occurrence.setCountry((String) hit.getSource()
				.get("country"));
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
				.get("datasetId"))));
		occurrence.setYear_interpreted((Integer) hit.getSource()
				.get("year_interpreted"));
		return occurrence;
	}
	private static Occurrence createJsonListOccurrence(SearchHit hit){
		Occurrence occurrence = new Occurrence();
		occurrence.setId(Long.parseLong((String) hit.getSource()
				.get("_id")));
		occurrence.setDatasetName((String) hit.getSource()
				.get("datasetName"));
		occurrence.setBasisOfRecord((String) hit.getSource()
				.get("basisOfRecord"));
		occurrence.setCatalogNumber((String) hit.getSource()
				.get("catalogNumber"));
		occurrence.setCountry((String) hit.getSource()
				.get("country"));
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
	/**
	 * Function that return all the occurrence documents stored in our ElasticSearch 
	 * @return result JSON
	 */
	public static Result searchAllOccurrences() {
		SearchResponse response = IndexClient.client
				.prepareSearch("gbiffrance-harvest").setTypes("Occurrence")
				.execute().actionGet();
		ArrayList<Occurrence> occurrenceList = new ArrayList<Occurrence>();
		for (SearchHit hit : response.getHits())
			occurrenceList.add(createJson(hit));
		return ok(Json.toJson(occurrenceList));
	}
	
	public static BoolQueryBuilder buildRequestQuery(SearchParser search){
		
		BoolQueryBuilder taxaQuery = new BoolQueryBuilder();
		
		if(!search.getScientificNames().isEmpty()){
			for(int i=0; i< search.getScientificNames().size(); ++i){
				Rank rank = Rank.valueOf(search.getScientificNames().get(i).getRank());
				switch (rank) {
					case KINGDOM:
						taxaQuery = QueryBuilders.boolQuery()
										.should(QueryBuilders.matchQuery("kingdom_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("kingdom", search.getScientificNames().get(i).getScientificName()));
						break;
					case PHYLUM:
						taxaQuery = QueryBuilders.boolQuery()
										.should(QueryBuilders.matchQuery("phylum_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("phylum", search.getScientificNames().get(i).getScientificName()));
						break;
					case CLASS:
						taxaQuery = QueryBuilders.boolQuery()
										.should(QueryBuilders.matchQuery("classs_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("classs", search.getScientificNames().get(i).getScientificName()));
						break;
					case ORDER:
						taxaQuery = QueryBuilders.boolQuery()
										.should(QueryBuilders.matchQuery("orderr_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("orderr", search.getScientificNames().get(i).getScientificName()));
						break;
					case FAMILY:
						taxaQuery = QueryBuilders.boolQuery()
										.should(QueryBuilders.matchQuery("family_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("family", search.getScientificNames().get(i).getScientificName()));
						break;
					case GENUS:
						taxaQuery = QueryBuilders.boolQuery()
										.should(QueryBuilders.matchQuery("genus_interpreted", search.getScientificNames().get(i).getScientificName()))
										.should(QueryBuilders.matchQuery("genus", search.getScientificNames().get(i).getScientificName()));
						break;
					case SPECIES:
						taxaQuery = QueryBuilders.boolQuery()
										.should((QueryBuilders.matchQuery("scientificName", search.getScientificNames().get(i).getScientificName())).type(Type.PHRASE_PREFIX).analyzer("simple"))
										.should(QueryBuilders.matchQuery("specificEpithet_interpreted", search.getScientificNames().get(i).getScientificName()).type(Type.PHRASE_PREFIX).analyzer("simple"));
						break;
					case SUBSPECIES:
						taxaQuery = QueryBuilders.boolQuery()
										.should((QueryBuilders.matchQuery("scientificName", search.getScientificNames().get(i).getScientificName())).type(Type.PHRASE_PREFIX).analyzer("simple"))
										.should(QueryBuilders.matchQuery("specificEpithet_interpreted", search.getScientificNames().get(i).getScientificName()).type(Type.PHRASE_PREFIX).analyzer("simple"));
						break;
					default:
						taxaQuery = QueryBuilders.boolQuery()
										.should((QueryBuilders.matchQuery("scientificName", search.getScientificNames().get(i).getScientificName())).type(Type.PHRASE_PREFIX).analyzer("simple"))
										.should(QueryBuilders.matchQuery("specificEpithet_interpreted", search.getScientificNames().get(i).getScientificName()).type(Type.PHRASE_PREFIX).analyzer("simple"));
						break;
				}
			}
		}
		return taxaQuery;
		
	}
	
	public static RangeFilterBuilder buildRequestFilter(SearchParser search){

		System.out.println(search.getDate());
		RangeFilterBuilder dateFilter  = FilterBuilders.rangeFilter("year_interpreted").from(search.getDate().getBeginDate()).to(search.getDate().getEndDate());
		return dateFilter;
		
	}
	
	/**
	 * Fonction qui lance la requete sur ElasticSearch
	 * @param search
	 * @return
	 */
	public JsonNode SearchOccurrences(SearchParser search) {
		
		BoolQueryBuilder searchQuery = buildRequestQuery(search);
		SearchResponse response = new SearchResponse();
		
		if (search.getDate() != null){
			RangeFilterBuilder searchFilter = buildRequestFilter(search);
			response = IndexClient.client
					.prepareSearch("gbiffrance-harvest").setTypes("Occurrence")
					.setQuery(searchQuery)
					.setFilter(searchFilter)
					.execute().actionGet();
		}else{
			 response = IndexClient.client
					.prepareSearch("gbiffrance-harvest").setTypes("Occurrence")
					.setQuery(searchQuery)
					.execute().actionGet();
		}
		
		System.out.println(response);
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
	public JsonNode SearchOccurrence(String occurrenceId) {
		String messageErreur;
		SearchResponse response = IndexClient.client
				.prepareSearch("gbiffrance-harvest", "Occurrence", occurrenceId)
				.execute().actionGet();
		
		if(response.getHits().getTotalHits() == 0)
			messageErreur= "Il n'y a pas de résultat";
		else if(response.getHits().getTotalHits()>1)
			messageErreur = "Il y a trop de résultat";
		else
			return Json.toJson(response.getHits().getAt(1));
		
		return Json.toJson("{ erreur :"+messageErreur+"}");
	}
	

}
