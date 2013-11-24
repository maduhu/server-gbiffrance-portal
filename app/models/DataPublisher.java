package models;

import java.util.ArrayList;

/**
 * The DataPublisher class is related to the MongoDB DataPublisher collection 
 * created after the harvesting process (gbiffrance-harvest)
 * @author: Michael Akbaraly, Marie-Elise Lecoq
 */

public class DataPublisher {
	 /**
	   * the data publisher ID
	   */
	  public Long id;
	  /**
	   * the data publisher name
	   */
	  public String name;
	  /**
	   * the data publisher description
	   */
	  public String description;
	  /**
	   * the data publisher address
	   */
	  public String address;
	  /**
	   * the data publisher administration contact information
	   */
	  public String administrativeContact;
	  /**
	   * the data publisher technical contact information
	   */
	  public String technicalContact;
	  /**
	   * the data publisher latitude
	   */
	  public String latitude;
	  /**
	   * the data publisher longitude
	   */
	  public String longitude;
	  /**
	   * the tags list to retrieve the data publisher in a search
	   */
	  public ArrayList<String> tags;
	  /**
	   * the data publisher media URL
	   */
	  public String mediaURL;
	  /**
	   * the data publisher image URL
	   */
	  public String imageURL;
}
