package entity;

import java.math.BigDecimal;

public class Goods {
	private Integer goodId;
	private String goodName;
	private String goodType;
	private BigDecimal goodShelfPrice;
	private Boolean isimported;
	private Integer quantity;

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public BigDecimal getGoodShelfPrice() {
		return goodShelfPrice;
	}

	public void setGoodShelfPrice(BigDecimal goodShelfPrice) {
		this.goodShelfPrice = goodShelfPrice;
	}

	public String getGoodType() {
		return goodType;
	}

	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}

	public Boolean getIsimported() {
		return isimported;
	}

	public void setIsimported(Boolean isimported) {
		this.isimported = isimported;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
