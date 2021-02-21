package entity;
import java.math.BigDecimal;

public class Cart {
	private Integer productId;
	private String productname;
	private Integer quantity;
	private BigDecimal taxedPrice;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getTaxedPrice() {
		return taxedPrice;
	}

	public void setTaxedPrice(BigDecimal taxedlPrice) {
		this.taxedPrice = taxedlPrice;
	}

}
