package controllers;

import java.util.List;


/**
 * The searchParser class is related to send by the client in order to search on the database
 * @author Marie-Elise Lecoq
 */

public class SearchParser {
	
	/**
	 * The GeoBound class is used to create latitude and longitude items.
	 */
	public static class GeoBound{
		
		private String filter;
		private Float bound;
		
		public String getFilter() { return filter; }
		public void setFilter(String filter) { this.filter = filter; }
		public Float getBound() { return bound; }
		public void setBound(Float bound) { this.bound = bound; }
	}
	
	public static class BoundingBox{
		public static class Point {
			private Float latitude;
			private Float longitude;
			
			public Float getLatitude() { return latitude; }
			public void setLatitude(Float latitude) { this.latitude = latitude; }
			public Float getLongitude() { return longitude; }
			public void setLongitude(Float longitude) { this.longitude = longitude;}
		}
		
		private Point northEast;
		private Point southWest;
		
		public Point getNorthEast() { return northEast; }
		public void setNorthEast(Point northEast) { this.northEast = northEast; }
		public Point getSouthWest() { return southWest; }
		public void setSouthWest(Point southWest) { this.southWest = southWest; }
	}
	
	public static class ScientificNames {
		private String scientificName;
		private String rank;
		
		public String getScientificName() { return scientificName; }
		public void setScientificName(String scientificName) { this.scientificName = scientificName; }
		public String getRank() { return rank; }
		public void setRank(String rank) { this.rank = rank; }
	}
	
	private List<ScientificNames> scientificNames;
	private List<String> vernacularName;
	private List<String> locality;
	private List<GeoBound> latitude;
	private List<GeoBound> longitude;
	private boolean geolocalizedData;
	private List<BoundingBox> boundingBox;
	private List<Integer> dataPublisher;
	private List<Integer> dataset;
	
	
	public List<ScientificNames> getScientificNames() { return scientificNames; }
	public void setScientificNames(List<ScientificNames> scientificNames) { this.scientificNames = scientificNames; }
	public List<String> getVernacularName() { return vernacularName; }
	public void setVernacularName(List<String> vernacularName) { this.vernacularName = vernacularName; }
	public List<String> getLocality() { return locality; }
	public void setLocality(List<String> locality) { this.locality = locality; }
	public List<GeoBound> getLatitude() { return latitude; }
	public void setLatitude(List<GeoBound> latitude) { this.latitude = latitude; }
	public List<GeoBound> getLongitude() { return longitude; }
	public void setLongitude(List<GeoBound> longitude) { this.longitude = longitude; }
	public boolean isGeolocalizedData() { return geolocalizedData; }
	public void setGeolocalizedData(boolean geolocalizedData) { this.geolocalizedData = geolocalizedData; }
	public List<BoundingBox> getBoundingBox() { return boundingBox; }
	public void setBoundingBox(List<BoundingBox> boundingBox) { this.boundingBox = boundingBox; }
	public List<Integer> getDataPublisher() { return dataPublisher; }
	public void setDataPublisher(List<Integer> dataPublisher) { this.dataPublisher = dataPublisher; }
	public List<Integer> getDataset() { return dataset; }
	public void setDataset(List<Integer> dataset) { this.dataset = dataset; }
	
}
