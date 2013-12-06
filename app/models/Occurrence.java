package models;

/**
 * The Occurrence class is related to the collection created after the harvesting process 
 * (gbiffrance-harvest)
 * @author Michael Akbaraly
 */

public class Occurrence {
	private Long id;
	private String className;

	  //Record-level
	private String typee;
	private String modified;
	private String language;
	private String rights;
	private String rightsHolder;
	private String accessRights;
	private String bibliographicCitation;
	private String referencess;
	private String institutionID;
	private String collectionID;

	private String datasetID;

	private String institutionCode;

	private String collectionCode;

	private String datasetName;
	private String ownerInstitutionCode;

	private String basisOfRecord;
	private String informationWithheld;
	private String dataGeneralizations;
	private String dynamicProperties;

	// Occurrence
	private String occurrenceID;
	private String catalogNumber;
	private String occurrenceRemarks;
	private String recordNumber;
	private String recordedBy;
	private String individualID;
	private String individualCount;
	private String sex;
	private String lifeStage;
	private String reproductiveCondition;
	private String behavior;
	private String establishmentMeans;
	private String occurrenceStatus;
	private String preparations;
	private String disposition;
	private String otherCatalogNumbers;
	private String previousIdentifications;
	private String associatedMedia;
	private String associatedReferences;
	private String associatedOccurrences;
	private String associatedSequences;
	private String associatedTaxa;
	private String datasetId;

	// Event
	private String eventID;
	private String samplingProtocol;
	private String samplingEffort;
	private String eventDate;
	private String eventTime;
	private String startDayOfYear;
	private String endDayofYear;
	private Integer year_interpreted;
	
	private String year;
	private String month;
	private String day;
	private String verbatimEventDate;
	private String habitat;
	private String fieldNumber;
	private String fieldNotes;
	private String eventRemarks;

	// Location
	private String locationID;
	private String higherGeographyID;
	private String higherGeography;

	private String continent;
	private String waterBody;
	private String islandGroup;
	private String island;

	private String country;
	private String countryCode;
	private String stateProvince;
	private String county;
	private String municipality;

	private String locality;
	private String verbatimLocality;
	private String verbatimElevation;
	private String minimumElevationInMeters;
	private String maximumElevationInMeters;
	private String verbatimDepth;
	private String minimumDepthInMeters;
	private String maximumDepthInMeters;
	private String minimumDistanceAboveSurfaceInMeters;
	private String maximumDistanceAboveSurfaceInMeters;
	private String locationAccordingTo;
	private String locationRemarks;
	private String verbatimCoordinates;
	private String verbatimLatitude;
	private String verbatimLongitude;
	private String verbatimCoordinateSystem;
	private String verbatimSRS;

	private String decimalLatitude;

	private String decimalLongitude;
	private String geodeticDatum;
	private String coordinateUncertaintyInMeters;
	private String coordinatePrecision;
	private String pointRadiusSpatialFit;
	private String footprintWKT;
	private String footprintSRS;
	private String footprintSpatialFit;
	private String georeferencedBy;
	private String georeferencedDate;
	private String georeferenceProtocol;
	private String georeferenceSources;
	private String georeferenceVerificationStatus;
	private String georeferenceRemarks;

	// GeologicalContext
	private String geologicalContextID;
	private String earliestEonOrLowestEonothem;
	private String latestEonOrHighestEonothem;
	private String earliestEraOrLowestErathem;
	private String latestEraOrHighestErathem;
	private String earliestPeriodOrLowestSystem;
	private String latestPeriodOrHighestSystem;
	private String earliestEpochOrLowestSeries;
	private String latestEpochOrHighestSeries;
	private String earliestAgeOrLowestStage;
	private String latestAgeOrHighestStage;
	private String lowestBiostratigraphicZone;
	private String highestBiostratigraphicZone;
	private String lithostratigraphicTerms;
	private String groupp;
	private String formation;
	private String member;
	private String bed;

	// Identification
	private String identificationID;
	private String identifiedBy;
	private String dateIdentified;
	private String identificationVerificationStatus;
	private String identificationRemarks;
	private String identificationQualifier;
	private String typeStatus;

	// Taxon
	private String taxonID;
	private String scientificNameID;
	private String acceptedNameUsageID;
	private String parentNameUsageID;
	private String originalNameUsageID;
	private String nameAccordingToID;
	private String namePublishedInID;
	private String taxonConceptID;

	private String scientificName;
	private String acceptedNameUsage;
	private String parentNameUsage;
	private String originalNameUsage;
	private String nameAccordingTo;
	private String namePublishedIn;
	private String namePublishedInYear;
	private String higherClassification;
	private String kingdom;
	private String phylum;
	private String classs; // class is a Java keyword
	private String orderr; // order is a SQL keyword
	private String family;
	private String genus;
	private String subgenus;
	private String specificEpithet;
	private String infraSpecificEpithet;
	private String kingdom_interpreted;
	private String phylum_interpreted;
	private String classs_interpreted;
	private String orderr_interpreted;
	private String family_interpreted;
	private String genus_interpreted;
	private String subgenus_interpreted;
	private String specificEpithet_interpreted;
	private String infraSpecificEpithet_interpreted;
	private String taxonRank;
	private String verbatimTaxonRank;
	private String scientificNameAuthorship;
	private String vernacularName;
	private String nomenclaturalCode;
	private String taxonomicStatus;
	private String nomenclaturalStatus;
	private String taxonRemarks;

	private String taxonStatus;
	private String ecatConceptId;
	private String ecatParentId;

	/**
	 * Hit score get by the occurrence during a search
	 */
	private float score;
	  
	  /**
	   * Related dataset
	   * @see Dataset
	   */
	  private Dataset dataset;

	  public Occurrence(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTypee() {
		return typee;
	}

	public void setTypee(String typee) {
		this.typee = typee;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getRightsHolder() {
		return rightsHolder;
	}

	public void setRightsHolder(String rightsHolder) {
		this.rightsHolder = rightsHolder;
	}

	public String getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

	public String getBibliographicCitation() {
		return bibliographicCitation;
	}

	public void setBibliographicCitation(String bibliographicCitation) {
		this.bibliographicCitation = bibliographicCitation;
	}

	public String getReferencess() {
		return referencess;
	}

	public void setReferencess(String referencess) {
		this.referencess = referencess;
	}

	public String getInstitutionID() {
		return institutionID;
	}

	public void setInstitutionID(String institutionID) {
		this.institutionID = institutionID;
	}

	public String getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(String collectionID) {
		this.collectionID = collectionID;
	}

	public String getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(String datasetID) {
		this.datasetID = datasetID;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public String getDatasetName() {
		return datasetName;
	}

	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}

	public String getOwnerInstitutionCode() {
		return ownerInstitutionCode;
	}

	public void setOwnerInstitutionCode(String ownerInstitutionCode) {
		this.ownerInstitutionCode = ownerInstitutionCode;
	}

	public String getBasisOfRecord() {
		return basisOfRecord;
	}

	public void setBasisOfRecord(String basisOfRecord) {
		this.basisOfRecord = basisOfRecord;
	}

	public String getInformationWithheld() {
		return informationWithheld;
	}

	public void setInformationWithheld(String informationWithheld) {
		this.informationWithheld = informationWithheld;
	}

	public String getDataGeneralizations() {
		return dataGeneralizations;
	}

	public void setDataGeneralizations(String dataGeneralizations) {
		this.dataGeneralizations = dataGeneralizations;
	}

	public String getDynamicProperties() {
		return dynamicProperties;
	}

	public void setDynamicProperties(String dynamicProperties) {
		this.dynamicProperties = dynamicProperties;
	}

	public String getOccurrenceID() {
		return occurrenceID;
	}

	public void setOccurrenceID(String occurrenceID) {
		this.occurrenceID = occurrenceID;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public String getOccurrenceRemarks() {
		return occurrenceRemarks;
	}

	public void setOccurrenceRemarks(String occurrenceRemarks) {
		this.occurrenceRemarks = occurrenceRemarks;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getRecordedBy() {
		return recordedBy;
	}

	public void setRecordedBy(String recordedBy) {
		this.recordedBy = recordedBy;
	}

	public String getIndividualID() {
		return individualID;
	}

	public void setIndividualID(String individualID) {
		this.individualID = individualID;
	}

	public String getIndividualCount() {
		return individualCount;
	}

	public void setIndividualCount(String individualCount) {
		this.individualCount = individualCount;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLifeStage() {
		return lifeStage;
	}

	public void setLifeStage(String lifeStage) {
		this.lifeStage = lifeStage;
	}

	public String getReproductiveCondition() {
		return reproductiveCondition;
	}

	public void setReproductiveCondition(String reproductiveCondition) {
		this.reproductiveCondition = reproductiveCondition;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getEstablishmentMeans() {
		return establishmentMeans;
	}

	public void setEstablishmentMeans(String establishmentMeans) {
		this.establishmentMeans = establishmentMeans;
	}

	public String getOccurrenceStatus() {
		return occurrenceStatus;
	}

	public void setOccurrenceStatus(String occurrenceStatus) {
		this.occurrenceStatus = occurrenceStatus;
	}

	public String getPreparations() {
		return preparations;
	}

	public void setPreparations(String preparations) {
		this.preparations = preparations;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getOtherCatalogNumbers() {
		return otherCatalogNumbers;
	}

	public void setOtherCatalogNumbers(String otherCatalogNumbers) {
		this.otherCatalogNumbers = otherCatalogNumbers;
	}

	public String getPreviousIdentifications() {
		return previousIdentifications;
	}

	public void setPreviousIdentifications(String previousIdentifications) {
		this.previousIdentifications = previousIdentifications;
	}

	public String getAssociatedMedia() {
		return associatedMedia;
	}

	public void setAssociatedMedia(String associatedMedia) {
		this.associatedMedia = associatedMedia;
	}

	public String getAssociatedReferences() {
		return associatedReferences;
	}

	public void setAssociatedReferences(String associatedReferences) {
		this.associatedReferences = associatedReferences;
	}

	public String getAssociatedOccurrences() {
		return associatedOccurrences;
	}

	public void setAssociatedOccurrences(String associatedOccurrences) {
		this.associatedOccurrences = associatedOccurrences;
	}

	public String getAssociatedSequences() {
		return associatedSequences;
	}

	public void setAssociatedSequences(String associatedSequences) {
		this.associatedSequences = associatedSequences;
	}

	public String getAssociatedTaxa() {
		return associatedTaxa;
	}

	public void setAssociatedTaxa(String associatedTaxa) {
		this.associatedTaxa = associatedTaxa;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getSamplingProtocol() {
		return samplingProtocol;
	}

	public void setSamplingProtocol(String samplingProtocol) {
		this.samplingProtocol = samplingProtocol;
	}

	public String getSamplingEffort() {
		return samplingEffort;
	}

	public void setSamplingEffort(String samplingEffort) {
		this.samplingEffort = samplingEffort;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getStartDayOfYear() {
		return startDayOfYear;
	}

	public void setStartDayOfYear(String startDayOfYear) {
		this.startDayOfYear = startDayOfYear;
	}

	public String getEndDayofYear() {
		return endDayofYear;
	}

	public void setEndDayofYear(String endDayofYear) {
		this.endDayofYear = endDayofYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getVerbatimEventDate() {
		return verbatimEventDate;
	}

	public void setVerbatimEventDate(String verbatimEventDate) {
		this.verbatimEventDate = verbatimEventDate;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public String getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(String fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public String getFieldNotes() {
		return fieldNotes;
	}

	public void setFieldNotes(String fieldNotes) {
		this.fieldNotes = fieldNotes;
	}

	public String getEventRemarks() {
		return eventRemarks;
	}

	public void setEventRemarks(String eventRemarks) {
		this.eventRemarks = eventRemarks;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public String getHigherGeographyID() {
		return higherGeographyID;
	}

	public void setHigherGeographyID(String higherGeographyID) {
		this.higherGeographyID = higherGeographyID;
	}

	public String getHigherGeography() {
		return higherGeography;
	}

	public void setHigherGeography(String higherGeography) {
		this.higherGeography = higherGeography;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getWaterBody() {
		return waterBody;
	}

	public void setWaterBody(String waterBody) {
		this.waterBody = waterBody;
	}

	public String getIslandGroup() {
		return islandGroup;
	}

	public void setIslandGroup(String islandGroup) {
		this.islandGroup = islandGroup;
	}

	public String getIsland() {
		return island;
	}

	public void setIsland(String island) {
		this.island = island;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getVerbatimLocality() {
		return verbatimLocality;
	}

	public void setVerbatimLocality(String verbatimLocality) {
		this.verbatimLocality = verbatimLocality;
	}

	public String getVerbatimElevation() {
		return verbatimElevation;
	}

	public void setVerbatimElevation(String verbatimElevation) {
		this.verbatimElevation = verbatimElevation;
	}

	public String getMinimumElevationInMeters() {
		return minimumElevationInMeters;
	}

	public void setMinimumElevationInMeters(String minimumElevationInMeters) {
		this.minimumElevationInMeters = minimumElevationInMeters;
	}

	public String getMaximumElevationInMeters() {
		return maximumElevationInMeters;
	}

	public void setMaximumElevationInMeters(String maximumElevationInMeters) {
		this.maximumElevationInMeters = maximumElevationInMeters;
	}

	public String getVerbatimDepth() {
		return verbatimDepth;
	}

	public void setVerbatimDepth(String verbatimDepth) {
		this.verbatimDepth = verbatimDepth;
	}

	public String getMinimumDepthInMeters() {
		return minimumDepthInMeters;
	}

	public void setMinimumDepthInMeters(String minimumDepthInMeters) {
		this.minimumDepthInMeters = minimumDepthInMeters;
	}

	public String getMaximumDepthInMeters() {
		return maximumDepthInMeters;
	}

	public void setMaximumDepthInMeters(String maximumDepthInMeters) {
		this.maximumDepthInMeters = maximumDepthInMeters;
	}

	public String getMinimumDistanceAboveSurfaceInMeters() {
		return minimumDistanceAboveSurfaceInMeters;
	}

	public void setMinimumDistanceAboveSurfaceInMeters(
			String minimumDistanceAboveSurfaceInMeters) {
		this.minimumDistanceAboveSurfaceInMeters = minimumDistanceAboveSurfaceInMeters;
	}

	public String getMaximumDistanceAboveSurfaceInMeters() {
		return maximumDistanceAboveSurfaceInMeters;
	}

	public void setMaximumDistanceAboveSurfaceInMeters(
			String maximumDistanceAboveSurfaceInMeters) {
		this.maximumDistanceAboveSurfaceInMeters = maximumDistanceAboveSurfaceInMeters;
	}

	public String getLocationAccordingTo() {
		return locationAccordingTo;
	}

	public void setLocationAccordingTo(String locationAccordingTo) {
		this.locationAccordingTo = locationAccordingTo;
	}

	public String getLocationRemarks() {
		return locationRemarks;
	}

	public void setLocationRemarks(String locationRemarks) {
		this.locationRemarks = locationRemarks;
	}

	public String getVerbatimCoordinates() {
		return verbatimCoordinates;
	}

	public void setVerbatimCoordinates(String verbatimCoordinates) {
		this.verbatimCoordinates = verbatimCoordinates;
	}

	public String getVerbatimLatitude() {
		return verbatimLatitude;
	}

	public void setVerbatimLatitude(String verbatimLatitude) {
		this.verbatimLatitude = verbatimLatitude;
	}

	public String getVerbatimLongitude() {
		return verbatimLongitude;
	}

	public void setVerbatimLongitude(String verbatimLongitude) {
		this.verbatimLongitude = verbatimLongitude;
	}

	public String getVerbatimCoordinateSystem() {
		return verbatimCoordinateSystem;
	}

	public void setVerbatimCoordinateSystem(String verbatimCoordinateSystem) {
		this.verbatimCoordinateSystem = verbatimCoordinateSystem;
	}

	public String getVerbatimSRS() {
		return verbatimSRS;
	}

	public void setVerbatimSRS(String verbatimSRS) {
		this.verbatimSRS = verbatimSRS;
	}

	public String getDecimalLatitude() {
		return decimalLatitude;
	}

	public void setDecimalLatitude(String decimalLatitude) {
		this.decimalLatitude = decimalLatitude;
	}

	public String getDecimalLongitude() {
		return decimalLongitude;
	}

	public void setDecimalLongitude(String decimalLongitude) {
		this.decimalLongitude = decimalLongitude;
	}

	public String getGeodeticDatum() {
		return geodeticDatum;
	}

	public void setGeodeticDatum(String geodeticDatum) {
		this.geodeticDatum = geodeticDatum;
	}

	public String getCoordinateUncertaintyInMeters() {
		return coordinateUncertaintyInMeters;
	}

	public void setCoordinateUncertaintyInMeters(
			String coordinateUncertaintyInMeters) {
		this.coordinateUncertaintyInMeters = coordinateUncertaintyInMeters;
	}

	public String getCoordinatePrecision() {
		return coordinatePrecision;
	}

	public void setCoordinatePrecision(String coordinatePrecision) {
		this.coordinatePrecision = coordinatePrecision;
	}

	public String getPointRadiusSpatialFit() {
		return pointRadiusSpatialFit;
	}

	public void setPointRadiusSpatialFit(String pointRadiusSpatialFit) {
		this.pointRadiusSpatialFit = pointRadiusSpatialFit;
	}

	public String getFootprintWKT() {
		return footprintWKT;
	}

	public void setFootprintWKT(String footprintWKT) {
		this.footprintWKT = footprintWKT;
	}

	public String getFootprintSRS() {
		return footprintSRS;
	}

	public void setFootprintSRS(String footprintSRS) {
		this.footprintSRS = footprintSRS;
	}

	public String getFootprintSpatialFit() {
		return footprintSpatialFit;
	}

	public void setFootprintSpatialFit(String footprintSpatialFit) {
		this.footprintSpatialFit = footprintSpatialFit;
	}

	public String getGeoreferencedBy() {
		return georeferencedBy;
	}

	public void setGeoreferencedBy(String georeferencedBy) {
		this.georeferencedBy = georeferencedBy;
	}

	public String getGeoreferencedDate() {
		return georeferencedDate;
	}

	public void setGeoreferencedDate(String georeferencedDate) {
		this.georeferencedDate = georeferencedDate;
	}

	public String getGeoreferenceProtocol() {
		return georeferenceProtocol;
	}

	public void setGeoreferenceProtocol(String georeferenceProtocol) {
		this.georeferenceProtocol = georeferenceProtocol;
	}

	public String getGeoreferenceSources() {
		return georeferenceSources;
	}

	public void setGeoreferenceSources(String georeferenceSources) {
		this.georeferenceSources = georeferenceSources;
	}

	public String getGeoreferenceVerificationStatus() {
		return georeferenceVerificationStatus;
	}

	public void setGeoreferenceVerificationStatus(
			String georeferenceVerificationStatus) {
		this.georeferenceVerificationStatus = georeferenceVerificationStatus;
	}

	public String getGeoreferenceRemarks() {
		return georeferenceRemarks;
	}

	public void setGeoreferenceRemarks(String georeferenceRemarks) {
		this.georeferenceRemarks = georeferenceRemarks;
	}

	public String getGeologicalContextID() {
		return geologicalContextID;
	}

	public void setGeologicalContextID(String geologicalContextID) {
		this.geologicalContextID = geologicalContextID;
	}

	public String getEarliestEonOrLowestEonothem() {
		return earliestEonOrLowestEonothem;
	}

	public void setEarliestEonOrLowestEonothem(String earliestEonOrLowestEonothem) {
		this.earliestEonOrLowestEonothem = earliestEonOrLowestEonothem;
	}

	public String getLatestEonOrHighestEonothem() {
		return latestEonOrHighestEonothem;
	}

	public void setLatestEonOrHighestEonothem(String latestEonOrHighestEonothem) {
		this.latestEonOrHighestEonothem = latestEonOrHighestEonothem;
	}

	public String getEarliestEraOrLowestErathem() {
		return earliestEraOrLowestErathem;
	}

	public void setEarliestEraOrLowestErathem(String earliestEraOrLowestErathem) {
		this.earliestEraOrLowestErathem = earliestEraOrLowestErathem;
	}

	public String getLatestEraOrHighestErathem() {
		return latestEraOrHighestErathem;
	}

	public void setLatestEraOrHighestErathem(String latestEraOrHighestErathem) {
		this.latestEraOrHighestErathem = latestEraOrHighestErathem;
	}

	public String getEarliestPeriodOrLowestSystem() {
		return earliestPeriodOrLowestSystem;
	}

	public void setEarliestPeriodOrLowestSystem(String earliestPeriodOrLowestSystem) {
		this.earliestPeriodOrLowestSystem = earliestPeriodOrLowestSystem;
	}

	public String getLatestPeriodOrHighestSystem() {
		return latestPeriodOrHighestSystem;
	}

	public void setLatestPeriodOrHighestSystem(String latestPeriodOrHighestSystem) {
		this.latestPeriodOrHighestSystem = latestPeriodOrHighestSystem;
	}

	public String getEarliestEpochOrLowestSeries() {
		return earliestEpochOrLowestSeries;
	}

	public void setEarliestEpochOrLowestSeries(String earliestEpochOrLowestSeries) {
		this.earliestEpochOrLowestSeries = earliestEpochOrLowestSeries;
	}

	public String getLatestEpochOrHighestSeries() {
		return latestEpochOrHighestSeries;
	}

	public void setLatestEpochOrHighestSeries(String latestEpochOrHighestSeries) {
		this.latestEpochOrHighestSeries = latestEpochOrHighestSeries;
	}

	public String getEarliestAgeOrLowestStage() {
		return earliestAgeOrLowestStage;
	}

	public void setEarliestAgeOrLowestStage(String earliestAgeOrLowestStage) {
		this.earliestAgeOrLowestStage = earliestAgeOrLowestStage;
	}

	public String getLatestAgeOrHighestStage() {
		return latestAgeOrHighestStage;
	}

	public void setLatestAgeOrHighestStage(String latestAgeOrHighestStage) {
		this.latestAgeOrHighestStage = latestAgeOrHighestStage;
	}

	public String getLowestBiostratigraphicZone() {
		return lowestBiostratigraphicZone;
	}

	public void setLowestBiostratigraphicZone(String lowestBiostratigraphicZone) {
		this.lowestBiostratigraphicZone = lowestBiostratigraphicZone;
	}

	public String getHighestBiostratigraphicZone() {
		return highestBiostratigraphicZone;
	}

	public void setHighestBiostratigraphicZone(String highestBiostratigraphicZone) {
		this.highestBiostratigraphicZone = highestBiostratigraphicZone;
	}

	public String getLithostratigraphicTerms() {
		return lithostratigraphicTerms;
	}

	public void setLithostratigraphicTerms(String lithostratigraphicTerms) {
		this.lithostratigraphicTerms = lithostratigraphicTerms;
	}

	public String getGroupp() {
		return groupp;
	}

	public void setGroupp(String groupp) {
		this.groupp = groupp;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getIdentificationID() {
		return identificationID;
	}

	public void setIdentificationID(String identificationID) {
		this.identificationID = identificationID;
	}

	public String getIdentifiedBy() {
		return identifiedBy;
	}

	public void setIdentifiedBy(String identifiedBy) {
		this.identifiedBy = identifiedBy;
	}

	public String getDateIdentified() {
		return dateIdentified;
	}

	public void setDateIdentified(String dateIdentified) {
		this.dateIdentified = dateIdentified;
	}

	public String getIdentificationVerificationStatus() {
		return identificationVerificationStatus;
	}

	public void setIdentificationVerificationStatus(
			String identificationVerificationStatus) {
		this.identificationVerificationStatus = identificationVerificationStatus;
	}

	public String getIdentificationRemarks() {
		return identificationRemarks;
	}

	public void setIdentificationRemarks(String identificationRemarks) {
		this.identificationRemarks = identificationRemarks;
	}

	public String getIdentificationQualifier() {
		return identificationQualifier;
	}

	public void setIdentificationQualifier(String identificationQualifier) {
		this.identificationQualifier = identificationQualifier;
	}

	public String getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(String typeStatus) {
		this.typeStatus = typeStatus;
	}

	public String getTaxonID() {
		return taxonID;
	}

	public void setTaxonID(String taxonID) {
		this.taxonID = taxonID;
	}

	public String getScientificNameID() {
		return scientificNameID;
	}

	public void setScientificNameID(String scientificNameID) {
		this.scientificNameID = scientificNameID;
	}

	public String getAcceptedNameUsageID() {
		return acceptedNameUsageID;
	}

	public void setAcceptedNameUsageID(String acceptedNameUsageID) {
		this.acceptedNameUsageID = acceptedNameUsageID;
	}

	public String getParentNameUsageID() {
		return parentNameUsageID;
	}

	public void setParentNameUsageID(String parentNameUsageID) {
		this.parentNameUsageID = parentNameUsageID;
	}

	public String getOriginalNameUsageID() {
		return originalNameUsageID;
	}

	public void setOriginalNameUsageID(String originalNameUsageID) {
		this.originalNameUsageID = originalNameUsageID;
	}

	public String getNameAccordingToID() {
		return nameAccordingToID;
	}

	public void setNameAccordingToID(String nameAccordingToID) {
		this.nameAccordingToID = nameAccordingToID;
	}

	public String getNamePublishedInID() {
		return namePublishedInID;
	}

	public void setNamePublishedInID(String namePublishedInID) {
		this.namePublishedInID = namePublishedInID;
	}

	public String getTaxonConceptID() {
		return taxonConceptID;
	}

	public void setTaxonConceptID(String taxonConceptID) {
		this.taxonConceptID = taxonConceptID;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getAcceptedNameUsage() {
		return acceptedNameUsage;
	}

	public void setAcceptedNameUsage(String acceptedNameUsage) {
		this.acceptedNameUsage = acceptedNameUsage;
	}

	public String getParentNameUsage() {
		return parentNameUsage;
	}

	public void setParentNameUsage(String parentNameUsage) {
		this.parentNameUsage = parentNameUsage;
	}

	public String getOriginalNameUsage() {
		return originalNameUsage;
	}

	public void setOriginalNameUsage(String originalNameUsage) {
		this.originalNameUsage = originalNameUsage;
	}

	public String getNameAccordingTo() {
		return nameAccordingTo;
	}

	public void setNameAccordingTo(String nameAccordingTo) {
		this.nameAccordingTo = nameAccordingTo;
	}

	public String getNamePublishedIn() {
		return namePublishedIn;
	}

	public void setNamePublishedIn(String namePublishedIn) {
		this.namePublishedIn = namePublishedIn;
	}

	public String getNamePublishedInYear() {
		return namePublishedInYear;
	}

	public void setNamePublishedInYear(String namePublishedInYear) {
		this.namePublishedInYear = namePublishedInYear;
	}

	public String getHigherClassification() {
		return higherClassification;
	}

	public void setHigherClassification(String higherClassification) {
		this.higherClassification = higherClassification;
	}

	public String getKingdom() {
		return kingdom;
	}

	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}

	public String getPhylum() {
		return phylum;
	}

	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public String getOrderr() {
		return orderr;
	}

	public void setOrderr(String orderr) {
		this.orderr = orderr;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getSubgenus() {
		return subgenus;
	}

	public void setSubgenus(String subgenus) {
		this.subgenus = subgenus;
	}

	public String getSpecificEpithet() {
		return specificEpithet;
	}

	public void setSpecificEpithet(String specificEpithet) {
		this.specificEpithet = specificEpithet;
	}

	public String getInfraSpecificEpithet() {
		return infraSpecificEpithet;
	}

	public void setInfraSpecificEpithet(String infraSpecificEpithet) {
		this.infraSpecificEpithet = infraSpecificEpithet;
	}

	public String getKingdom_interpreted() {
		return kingdom_interpreted;
	}

	public void setKingdom_interpreted(String kingdom_interpreted) {
		this.kingdom_interpreted = kingdom_interpreted;
	}

	public String getPhylum_interpreted() {
		return phylum_interpreted;
	}

	public void setPhylum_interpreted(String phylum_interpreted) {
		this.phylum_interpreted = phylum_interpreted;
	}

	public String getClasss_interpreted() {
		return classs_interpreted;
	}

	public void setClasss_interpreted(String classs_interpreted) {
		this.classs_interpreted = classs_interpreted;
	}
	
	

	public String getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}

	public String getOrderr_interpreted() { return orderr_interpreted; }
	public void setOrderr_interpreted(String orderr_interpreted) { this.orderr_interpreted = orderr_interpreted; }
	public String getFamily_interpreted() { return family_interpreted; }
	public void setFamily_interpreted(String family_interpreted) { this.family_interpreted = family_interpreted; }
	public String getGenus_interpreted() { return genus_interpreted; }
	public void setGenus_interpreted(String genus_interpreted) { this.genus_interpreted = genus_interpreted; }
	public String getSubgenus_interpreted() { return subgenus_interpreted; }
	public void setSubgenus_interpreted(String subgenus_interpreted) { this.subgenus_interpreted = subgenus_interpreted; }
	public String getSpecificEpithet_interpreted() { return specificEpithet_interpreted; }
	public void setSpecificEpithet_interpreted(String specificEpithet_interpreted) { this.specificEpithet_interpreted = specificEpithet_interpreted; }
	public String getInfraSpecificEpithet_interpreted() { return infraSpecificEpithet_interpreted; }
	public void setInfraSpecificEpithet_interpreted(String infraSpecificEpithet_interpreted) { this.infraSpecificEpithet_interpreted = infraSpecificEpithet_interpreted; }
	public String getTaxonRank() { return taxonRank; }
	public void setTaxonRank(String taxonRank) { this.taxonRank = taxonRank; }
	public String getVerbatimTaxonRank() { return verbatimTaxonRank; }
	public void setVerbatimTaxonRank(String verbatimTaxonRank) { this.verbatimTaxonRank = verbatimTaxonRank; }
	public String getScientificNameAuthorship() { return scientificNameAuthorship; }
	public void setScientificNameAuthorship(String scientificNameAuthorship) { this.scientificNameAuthorship = scientificNameAuthorship; }
	public String getVernacularName() { return vernacularName; }
	public void setVernacularName(String vernacularName) { this.vernacularName = vernacularName; }
	public String getNomenclaturalCode() { return nomenclaturalCode; }
	public void setNomenclaturalCode(String nomenclaturalCode) { this.nomenclaturalCode = nomenclaturalCode; }
	public String getTaxonomicStatus() { return taxonomicStatus; }
	public void setTaxonomicStatus(String taxonomicStatus) { this.taxonomicStatus = taxonomicStatus; }
	public String getNomenclaturalStatus() { return nomenclaturalStatus; }
	public void setNomenclaturalStatus(String nomenclaturalStatus) { this.nomenclaturalStatus = nomenclaturalStatus; }
	public String getTaxonRemarks() { return taxonRemarks; }
	public void setTaxonRemarks(String taxonRemarks) { this.taxonRemarks = taxonRemarks; }
	public String getTaxonStatus() { return taxonStatus; }
	public void setTaxonStatus(String taxonStatus) { this.taxonStatus = taxonStatus; }
	public String getEcatConceptId() { return ecatConceptId; }
	public void setEcatConceptId(String ecatConceptId) { this.ecatConceptId = ecatConceptId; }
	public String getEcatParentId() { return ecatParentId; }
	public void setEcatParentId(String ecatParentId) { this.ecatParentId = ecatParentId; }
	public float getScore() { return score; }
	public void setScore(float score) { this.score = score; }
	public Dataset getDataset() { return dataset; }
	public void setDataset(Dataset dataset) { this.dataset = dataset; }
	public Integer getYear_interpreted() { return year_interpreted; }
	public void setYear_interpreted(Integer year_interpreted) { this.year_interpreted = year_interpreted; }  
}
