package models;

import java.util.ArrayList;

/**
 * The DataPublisher class is related to the MongoDB DataPublisher collection
 * created after the harvesting process (gbiffrance-harvest)
 * 
 * @author: Michael Akbaraly, Marie-Elise Lecoq
 */

public class DataPublisher {
	/**
	 * the data publisher ID
	 */
	private Long id;
	
	private String className;
	/**
	 * the data publisher name
	 */
	private String name;
	/**
	 * the data publisher description
	 */
	private String description;
	/**
	 * the data publisher address
	 */
	private String address;
	/**
	 * the data publisher administration contact information
	 */
	private String administrativeContact;
	/**
	 * the data publisher technical contact information
	 */
	private String technicalContact;
	/**
	 * the data publisher latitude
	 */
	private String latitude;
	/**
	 * the data publisher longitude
	 */
	private String longitude;
	/**
	 * the tags list to retrieve the data publisher in a search
	 */
	private ArrayList<String> tags;
	/**
	 * the data publisher media URL
	 */
	private String mediaURL;
	/**
	 * the data publisher image URL
	 */
	private String imageURL;
	
	public DataPublisher(Long id){
		this.id = id;
	}

	public DataPublisher() {
		// TODO Auto-generated constructor stub
	}

	// Getter and setter of the DataPublisher Model
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdministrativeContact() {
		return administrativeContact;
	}

	public void setAdministrativeContact(String administrativeContact) {
		this.administrativeContact = administrativeContact;
	}

	public String getTechnicalContact() {
		return technicalContact;
	}

	public void setTechnicalContact(String technicalContact) {
		this.technicalContact = technicalContact;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getMediaURL() {
		return mediaURL;
	}

	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
