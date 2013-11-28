package controllers;

import java.util.ArrayList;

import models.Dataset;
import models.Occurrence;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.cleverage.elasticsearch.IndexClient;

public class Occurrences extends Controller {
	
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
		return occurrence;
	}
	
	/**
	 * Function that return all the occurrence documents stored in our ElasticSearch 
	 * @return result JSON
	 */
	public static Result searchAll() {
		SearchResponse response = IndexClient.client
				.prepareSearch("gbiffrance-harvest").setTypes("Occurrence")
				.execute().actionGet();
		ArrayList<Occurrence> occurrenceList = new ArrayList<Occurrence>();
		for (SearchHit hit : response.getHits())
			occurrenceList.add(createJson(hit));
		return ok(Json.toJson(occurrenceList));
	}
	
	 public JsonNode SearchEngineRequest(SearchParser search){
		 SearchResponse response = IndexClient.client
					.prepareSearch("gbiffrance-harvest")
					.setTypes("Occurrence")
					.execute().actionGet();
			ArrayList<Occurrence> occurrenceList = new ArrayList<Occurrence>();
			for (SearchHit hit : response.getHits())
				occurrenceList.add(createJson(hit));
			return Json.toJson(occurrenceList);
	 }

}
