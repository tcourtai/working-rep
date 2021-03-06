package org.tcourtai.friends2go.hello;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight extends FlightInfo {

	private String departure;
	private String arrival;
	private String price;
	
	@Enumerated(EnumType.STRING)
	private Company company;
	private float priceNum;
	
	public Flight() {
		super();
		this.departure = "UNKNOWN";
		this.arrival = "UNKNOWN";
		this.price = "";
	};
	
	public Flight(FlightInfo fi) {
		super(fi);
		this.departure = "UNKNOWN";
		this.arrival = "UNKNOWN";
		this.price = "";
	};
	
	
	public Flight(String searchID, String fromCode, String toCode, String date, FlightType fligthType, String departure, String arrival, String price, Company company) {
		super(searchID, fromCode, toCode, date, fligthType);
		this.departure = departure;
		this.arrival = arrival;
		this.price = price;
		this.priceNum = parsePrice(price);
		this.company = company;
	}
	
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
		this.priceNum = parsePrice(price);
	}

	public float getPriceNum() {
		return priceNum;
	}

	public void setPriceNum(float priceNum) {
		this.priceNum = priceNum;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "############ Fligth details ###############"
		+ " \nFrom(Code) : " + this.getFromCode()
		+ " \nTo(Code) : " + this.getToCode()
		+ " \nDate : " + this.getDate()
		+ " \nDeparture : " + this.getDeparture()
		+ " \nArrival : " + this.getArrival()
		+ " \nPrice : " + this.getPrice()
		+ " \nCompany : " + this.getCompany();
	}

	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(addItemToCSV(this.getFrom()));
		sb.append(addItemToCSV(this.getFromCode()));
		sb.append(addItemToCSV(this.getTo()));
		sb.append(addItemToCSV(this.getToCode()));
		sb.append(addItemToCSV(this.getDate()));
		sb.append(addItemToCSV(this.getDeparture()));
		sb.append(addItemToCSV(this.getArrival()));
		sb.append(addItemToCSV(this.getPrice()));
		sb.append(addItemToCSV(this.getCompany().toString()));
		sb.append(addItemToCSV(this.getFlightType().toString()));
		
		return sb.toString(); 
	}
	
	public String addItemToCSV(String item) {
		return item + FileUtil.csvSeparator;
	}

	public float parsePrice(String p) {
		float fp = Float.MAX_VALUE;
		p = p.replaceAll("\\$", "");
		p = p.replaceAll("[,]", "");
		try {
			fp = Float.parseFloat(p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fp;
	}
}
