package com.stocksba.company.model;

import java.util.Date;

public class Company {

	private Integer id;
	private String name;
	private Integer turnover;
	private String ceo;
	private String code;
	private Integer sectorid;
	private String description;
	private String stockcode;
	private Date ipodate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTurnover() {
		return turnover;
	}

	public void setTurnover(Integer turnover) {
		this.turnover = turnover;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSectorid() {
		return sectorid;
	}

	public void setSectorid(Integer sectorid) {
		this.sectorid = sectorid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public Date getIpodate() {
		return ipodate;
	}

	public void setIpodate(Date ipodate) {
		this.ipodate = ipodate;
	}

}
