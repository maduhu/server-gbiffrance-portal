package models;

import java.util.ArrayList;

/**
 * The Dataset class is related to the MongoDB Dataset collection created after
 * the harvesting process (gbiffrance-harvest)
 * 
 * @author: Michael Akbaraly, Marie-Elise Lecoq
 */

public class Dataset {
	/***
	 * the dataset ID
	 */
	private Long id;
	/***
	 * the dataset name
	 */
	
	private String className;
	private String name;
	/***
	 * the access point to the dataset DwC-Archive or standardised XML
	 */
	private String url;
	/***
	 * the dataset type: ipt, digir, tapir, biocase
	 */
	private String type;
	/***
	 * the dataset harvest process status
	 */
	private String status;
	/***
	 * the temporary directory where data is stored during the harvest process
	 */
	private String tempDirectory;
	/***
	 * if an error occurs, shows the alphabetical range on which the harvest
	 * process stopped
	 */
	private String currentLower;
	/***
	 * flag that informs if the dataset is from outside the national network
	 */
	private boolean fromOutside;
	/***
	 * the dataset title
	 */
	private String title;
	/***
	 * the dataset description
	 */
	private String description;
	/***
	 * the language used in the dataset
	 */
	private String resourceLanguage;
	/***
	 * the dataset basis of record
	 */
	private String basisOfRecord;
	/***
	 * the dataset contact name
	 */
	private String resourceContactName;
	/***
	 * the dataset contact role
	 */
	private String resourceContactRole;
	/***
	 * the dataset contact address
	 */
	private String resourceContactAddress;
	/***
	 * the dataset contact email
	 */
	private String resourceContactEmail;
	/***
	 * the dataset contact telephone
	 */
	private String resourceContactTelephone;

	/***
	 * the dataset creator name
	 */
	private String resourceCreatorName;
	/***
	 * the dataset creator role
	 */
	private String resourceCreatorRole;
	/***
	 * the dataset creator address
	 */
	private String resourceCreatorAddress;
	/***
	 * the dataset creator email
	 */
	private String resourceCreatorEmail;
	/***
	 * the dataset creator telephone
	 */
	private String resourceCreatorTelephone;

	/***
	 * the dataset metadata creator name
	 */
	private String metadataProviderName;
	/***
	 * the dataset metadata creator role
	 */
	private String metadataProviderRole;
	/***
	 * the dataset metadata creator address
	 */
	private String metadataProviderAddress;
	/***
	 * the dataset metadata creator email
	 */
	private String metadataProviderEmail;
	/***
	 * the dataset metadata creator telephone
	 */
	private String metadataProviderTelephone;
	/***
	 * the dataset geographic coverage description
	 */
	private String geographicCoverageDescription;
	/***
	 * the dataset geographic coverage bounding coordinates
	 */
	private String geographicCoverageBoundingCoordinates;
	/***
	 * the dataset taxonomic coverage description
	 */
	private String taxonomicCoverageDescription;
	/***
	 * the dataset taxonomic coverage taxon list
	 */
	private String taxonomicCoverageTaxonList;
	/***
	 * the dataset temporal coverage date
	 */
	private String temporalCoverageDate;
	/***
	 * the dataset general keywords
	 */
	private String keywords;
	/***
	 * the dataset sampling description
	 */
	private String samplingDescription;
	/***
	 * quality control done on the dataset
	 */
	private String qualityControl;
	/***
	 * how the resource shoul be cited
	 */
	private String resourceCitation;
	/***
	 * external link to the dataset owner website
	 */
	private String homePageLink;
	/***
	 * DwC-Archive link
	 */
	private String dwcArchiveLink;
	/***
	 * concatenated list of tags that are used to retrieve the dataset on the
	 * search bar
	 */
	private String tagsText;
	/***
	 * table of tags that are used to retrieve the dataset on the search bar
	 */
	private ArrayList<String> tags;

	/***
	 * the data publisher linked to the dataset
	 * 
	 * @see DataPublisher
	 */
	private DataPublisher dataPublisher;
	
	public Dataset(){}

	public Dataset(Long id){
		this.id = id;
	}
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTempDirectory() {
		return tempDirectory;
	}

	public void setTempDirectory(String tempDirectory) {
		this.tempDirectory = tempDirectory;
	}

	public String getCurrentLower() {
		return currentLower;
	}

	public void setCurrentLower(String currentLower) {
		this.currentLower = currentLower;
	}

	public boolean isFromOutside() {
		return fromOutside;
	}

	public void setFromOutside(boolean fromOutside) {
		this.fromOutside = fromOutside;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceLanguage() {
		return resourceLanguage;
	}

	public void setResourceLanguage(String resourceLanguage) {
		this.resourceLanguage = resourceLanguage;
	}

	public String getBasisOfRecord() {
		return basisOfRecord;
	}

	public void setBasisOfRecord(String basisOfRecord) {
		this.basisOfRecord = basisOfRecord;
	}

	public String getResourceContactName() {
		return resourceContactName;
	}

	public void setResourceContactName(String resourceContactName) {
		this.resourceContactName = resourceContactName;
	}

	public String getResourceContactRole() {
		return resourceContactRole;
	}

	public void setResourceContactRole(String resourceContactRole) {
		this.resourceContactRole = resourceContactRole;
	}

	public String getResourceContactAddress() {
		return resourceContactAddress;
	}

	public void setResourceContactAddress(String resourceContactAddress) {
		this.resourceContactAddress = resourceContactAddress;
	}

	public String getResourceContactEmail() {
		return resourceContactEmail;
	}

	public void setResourceContactEmail(String resourceContactEmail) {
		this.resourceContactEmail = resourceContactEmail;
	}

	public String getResourceContactTelephone() {
		return resourceContactTelephone;
	}

	public void setResourceContactTelephone(String resourceContactTelephone) {
		this.resourceContactTelephone = resourceContactTelephone;
	}

	public String getResourceCreatorName() {
		return resourceCreatorName;
	}

	public void setResourceCreatorName(String resourceCreatorName) {
		this.resourceCreatorName = resourceCreatorName;
	}

	public String getResourceCreatorRole() {
		return resourceCreatorRole;
	}

	public void setResourceCreatorRole(String resourceCreatorRole) {
		this.resourceCreatorRole = resourceCreatorRole;
	}

	public String getResourceCreatorAddress() {
		return resourceCreatorAddress;
	}

	public void setResourceCreatorAddress(String resourceCreatorAddress) {
		this.resourceCreatorAddress = resourceCreatorAddress;
	}

	public String getResourceCreatorEmail() {
		return resourceCreatorEmail;
	}

	public void setResourceCreatorEmail(String resourceCreatorEmail) {
		this.resourceCreatorEmail = resourceCreatorEmail;
	}

	public String getResourceCreatorTelephone() {
		return resourceCreatorTelephone;
	}

	public void setResourceCreatorTelephone(String resourceCreatorTelephone) {
		this.resourceCreatorTelephone = resourceCreatorTelephone;
	}

	public String getMetadataProviderName() {
		return metadataProviderName;
	}

	public void setMetadataProviderName(String metadataProviderName) {
		this.metadataProviderName = metadataProviderName;
	}

	public String getMetadataProviderRole() {
		return metadataProviderRole;
	}

	public void setMetadataProviderRole(String metadataProviderRole) {
		this.metadataProviderRole = metadataProviderRole;
	}

	public String getMetadataProviderAddress() {
		return metadataProviderAddress;
	}

	public void setMetadataProviderAddress(String metadataProviderAddress) {
		this.metadataProviderAddress = metadataProviderAddress;
	}

	public String getMetadataProviderEmail() {
		return metadataProviderEmail;
	}

	public void setMetadataProviderEmail(String metadataProviderEmail) {
		this.metadataProviderEmail = metadataProviderEmail;
	}

	public String getMetadataProviderTelephone() {
		return metadataProviderTelephone;
	}

	public void setMetadataProviderTelephone(String metadataProviderTelephone) {
		this.metadataProviderTelephone = metadataProviderTelephone;
	}

	public String getGeographicCoverageDescription() {
		return geographicCoverageDescription;
	}

	public void setGeographicCoverageDescription(
			String geographicCoverageDescription) {
		this.geographicCoverageDescription = geographicCoverageDescription;
	}

	public String getGeographicCoverageBoundingCoordinates() {
		return geographicCoverageBoundingCoordinates;
	}

	public void setGeographicCoverageBoundingCoordinates(
			String geographicCoverageBoundingCoordinates) {
		this.geographicCoverageBoundingCoordinates = geographicCoverageBoundingCoordinates;
	}

	public String getTaxonomicCoverageDescription() {
		return taxonomicCoverageDescription;
	}

	public void setTaxonomicCoverageDescription(
			String taxonomicCoverageDescription) {
		this.taxonomicCoverageDescription = taxonomicCoverageDescription;
	}

	public String getTaxonomicCoverageTaxonList() {
		return taxonomicCoverageTaxonList;
	}

	public void setTaxonomicCoverageTaxonList(String taxonomicCoverageTaxonList) {
		this.taxonomicCoverageTaxonList = taxonomicCoverageTaxonList;
	}

	public String getTemporalCoverageDate() {
		return temporalCoverageDate;
	}

	public void setTemporalCoverageDate(String temporalCoverageDate) {
		this.temporalCoverageDate = temporalCoverageDate;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSamplingDescription() {
		return samplingDescription;
	}

	public void setSamplingDescription(String samplingDescription) {
		this.samplingDescription = samplingDescription;
	}

	public String getQualityControl() {
		return qualityControl;
	}

	public void setQualityControl(String qualityControl) {
		this.qualityControl = qualityControl;
	}

	public String getResourceCitation() {
		return resourceCitation;
	}

	public void setResourceCitation(String resourceCitation) {
		this.resourceCitation = resourceCitation;
	}

	public String getHomePageLink() {
		return homePageLink;
	}

	public void setHomePageLink(String homePageLink) {
		this.homePageLink = homePageLink;
	}

	public String getDwcArchiveLink() {
		return dwcArchiveLink;
	}

	public void setDwcArchiveLink(String dwcArchiveLink) {
		this.dwcArchiveLink = dwcArchiveLink;
	}

	public String getTagsText() {
		return tagsText;
	}

	public void setTagsText(String tagsText) {
		this.tagsText = tagsText;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public DataPublisher getDataPublisher() {
		return dataPublisher;
	}

	public void setDataPublisher(DataPublisher dataPublisher) {
		this.dataPublisher = dataPublisher;
	}

}
