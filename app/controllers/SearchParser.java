package controllers;


/**
 * The searchParser class is related to send by the client in order to search on the database
 * @author Marie-Elise Lecoq
 */

public class SearchParser {
	
	/**
	 * The GeoBound class is used to create latitude and longitude items.
	 */
	public class GeoBound{
		
		private String filter;
		private Float bound;
		
		public String getFilter() {
			return filter;
		}
		public void setFilter(String filter) {
			this.filter = filter;
		}
		public Float getBound() {
			return bound;
		}
		public void setBound(Float bound) {
			this.bound = bound;
		}
	}
	
	public class BoundingBox{
		public class Point {
			private Float latitude;
			private Float longitude;
			public Float getLatitude() {
				return latitude;
			}
			public void setLatitude(Float latitude) {
				this.latitude = latitude;
			}
			public Float getLongitude() {
				return longitude;
			}
			public void setLongitude(Float longitude) {
				this.longitude = longitude;
			}
		}
		
		private Point northEast;
		private Point southWest;
		public Point getNorthEast() {
			return northEast;
		}
		public void setNorthEast(Point northEast) {
			this.northEast = northEast;
		}
		public Point getSouthWest() {
			return southWest;
		}
		public void setSouthWest(Point southWest) {
			this.southWest = southWest;
		}
	}
	
	/**
	 * Array that store all the scientific name given by the user
	 */
	private String[] scientificName;
	
	/**
	 * Array that store all the vernacular name given by the user
	 */
	private String[] vernacularName;
	
	/**
	 * Array that store all the locality elements given by the user
	 */
	private String[] locality;

	/**
	 * Array that store all the latitude elements given by the user
	 */
	private GeoBound[] latitude;
	
	/**
	 * Array that store all the longitude elements given by the user
	 */
	private GeoBound[] longitude;
	
	/**
	 * if geolocalized is true, all the result are geolocalized.
	 */
	private boolean geolocalizedData;
	
	private BoundingBox[] boundingBox;
	
	private Integer[] dataPublisher;
	
	private Integer[] dataset;
	
	public String[] getScientificName() {
		return scientificName;
	}

	public void setScientificName(String[] scientificName) {
		this.scientificName = scientificName;
	}

	public String[] getVernacularName() {
		return vernacularName;
	}

	public void setVernacularName(String[] vernacularName) {
		this.vernacularName = vernacularName;
	}

	public String[] getLocality() {
		return locality;
	}

	public void setLocality(String[] locality) {
		this.locality = locality;
	}

	public GeoBound[] getLatitude() {
		return latitude;
	}

	public void setLatitude(GeoBound[] latitude) {
		this.latitude = latitude;
	}

	public GeoBound[] getLongitude() {
		return longitude;
	}

	public void setLongitude(GeoBound[] longitude) {
		this.longitude = longitude;
	}

	public boolean isGeolocalizedData() {
		return geolocalizedData;
	}

	public void setGeolocalizedData(boolean geolocalizedData) {
		this.geolocalizedData = geolocalizedData;
	}

	public BoundingBox[] getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(BoundingBox[] boundingBox) {
		this.boundingBox = boundingBox;
	}

	public Integer[] getDataPublisher() {
		return dataPublisher;
	}

	public void setDataPublisher(Integer[] dataPublisher) {
		this.dataPublisher = dataPublisher;
	}

	public Integer[] getDataset() {
		return dataset;
	}

	public void setDataset(Integer[] dataset) {
		this.dataset = dataset;
	}
	
	
	
}
