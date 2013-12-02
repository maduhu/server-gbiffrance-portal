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
		public static class Bound{
			public static class Point{
				private String lat;
				private String lng;
				public String getLat() { return lat; }
				public void setLat(String lat) { this.lat = lat; }
				public String getLng() { return lng; }
				public void setLng(String lng) { this.lng = lng; }
			}
			
			private Point _northEast;
			private Point _southWest;
			public Point get_northEast() { return _northEast; }
			public void set_northEast(Point _northEast) { this._northEast = _northEast; }
			public Point get_southWest() { return _southWest; }
			public void set_southWest(Point _southWest) { this._southWest = _southWest; }
		}
		
		private Bound bounds;
		private String name;
		public Bound getBounds() { return bounds; }
		public void setBounds(Bound bounds) { this.bounds = bounds; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
	}
	
	public static class ScientificNames {
		private String scientificName;
		private String rank;
		
		public String getScientificName() { return scientificName; }
		public void setScientificName(String scientificName) { this.scientificName = scientificName; }
		public String getRank() { return rank; }
		public void setRank(String rank) { this.rank = rank; }
	}
	
	public static class Locality{
		private String name;
		private String[] bounds;
		
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		public String[] getBounds() { return bounds; }
		public void setBounds(String[] bounds) { this.bounds = bounds; }
	}
	
	public static class Date{
		private String beginDate;
		private String endDate;
		
		public String getBeginDate() { return beginDate; }
		public void setBeginDate(String beginDate) { this.beginDate = beginDate; }
		public String getEndDate() { return endDate; }
		public void setEndDate(String endDate) { this.endDate = endDate; }
	}
	
	
	private List<ScientificNames> scientificNames;
	private List<String> vernacularName;
	private List<Locality> localities;
	private List<GeoBound> latitude;
	private List<GeoBound> longitude;
	private boolean geolocalizedData;
	private List<BoundingBox> boundingBox;
	private List<Long> datasetId;
	private Date date;
	
	
	public List<ScientificNames> getScientificNames() { return scientificNames; }
	public void setScientificNames(List<ScientificNames> scientificNames) { this.scientificNames = scientificNames; }
	public List<String> getVernacularName() { return vernacularName; }
	public void setVernacularName(List<String> vernacularName) { this.vernacularName = vernacularName; }
	public List<Locality> getLocalities() { return localities; }
	public void setLocality(List<Locality> localities) { this.localities = localities; }
	public List<GeoBound> getLatitude() { return latitude; }
	public void setLatitude(List<GeoBound> latitude) { this.latitude = latitude; }
	public List<GeoBound> getLongitude() { return longitude; }
	public void setLongitude(List<GeoBound> longitude) { this.longitude = longitude; }
	public boolean isGeolocalizedData() { return geolocalizedData; }
	public void setGeolocalizedData(boolean geolocalizedData) { this.geolocalizedData = geolocalizedData; }
	public List<BoundingBox> getBoundingBox() { return boundingBox; }
	public void setBoundingBox(List<BoundingBox> boundingBox) { this.boundingBox = boundingBox; }
	public List<Long> getDataset() { return datasetId; }
	public void setDataset(List<Long> dataset) { this.datasetId = dataset; }
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	
}
