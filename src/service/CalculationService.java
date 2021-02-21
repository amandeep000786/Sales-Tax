package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Goods;
import entity.Cart;
import entity.Receipt;

public class CalculationService {
	private static List<String> GOODS_TYPE_EXEMPTED;
	private static Map<String, String> inventory;
	private static Integer SALES_TAX_PCT = 10;
	private static Integer IMPORT_TAX_PCT = 5;

	public Receipt calculateBill(List<Goods> goods) {
		List<Cart> listItems = new ArrayList<>();
		BigDecimal salesTax = new BigDecimal(0);
		BigDecimal totalAmount = new BigDecimal(0);
		BigDecimal taxAmount;

		Receipt receipt = new Receipt();
		for (Goods good : goods) {
			Cart item = new Cart();
			taxAmount = geTaxAmount(good).setScale(2, RoundingMode.CEILING);
			salesTax = salesTax.add(taxAmount).setScale(2, RoundingMode.CEILING);

			item.setQuantity(good.getQuantity());
			item.setProductname(good.getGoodName());
			item.setTaxedPrice(good.getGoodShelfPrice().add(taxAmount));
			totalAmount = totalAmount.add(item.getTaxedPrice());
			listItems.add(item);
		}
		receipt.setSalesTax(salesTax);
		receipt.setTotalAmount(totalAmount);
		receipt.setCartList(listItems);
		return receipt;
	}

	public Goods addToCart(String entry) {
		try {
			String[] splittedPriceAndGood = entry.split(" at ");
			Integer quantity = Integer.valueOf(splittedPriceAndGood[0].substring(0, 1));
			String productName = splittedPriceAndGood[0].substring(1).trim();
			Goods good = new Goods();

			good.setGoodName(productName);
			good.setGoodShelfPrice(new BigDecimal(Float.valueOf(splittedPriceAndGood[1])));
			good.setGoodType(inventory.get(productName.trim()));
			good.setQuantity(quantity);

			if (productName.contains("imported")) {
				good.setIsimported(true);
			} else {
				good.setIsimported(false);
			}
			return good;
		} catch (NumberFormatException e) {
			System.out.println("Please enter correct format");
			return null;
			
		}

	}

	private BigDecimal geTaxAmount(Goods good) {
		BigDecimal tax;
		if (good.getIsimported() == true) {
			if (GOODS_TYPE_EXEMPTED.contains(good.getGoodType())) {
				tax= good.getGoodShelfPrice().multiply(new BigDecimal(IMPORT_TAX_PCT)).divide(new BigDecimal(100));
			
				return roundOff(tax);
			}

			tax= good.getGoodShelfPrice().multiply(new BigDecimal(SALES_TAX_PCT).add(new BigDecimal(IMPORT_TAX_PCT)))
					.divide(new BigDecimal(100));
			
			return roundOff(tax);
		}
		if (GOODS_TYPE_EXEMPTED.contains(good.getGoodType())) {
			return new BigDecimal(0);
		}

		tax= good.getGoodShelfPrice().multiply(new BigDecimal(SALES_TAX_PCT)).divide(new BigDecimal(100));
		return roundOff(tax);
	}
	
	private BigDecimal roundOff(BigDecimal amount) {
		return amount.multiply(new BigDecimal(20)).setScale(0, RoundingMode.UP).divide(new BigDecimal(20.0));
	}
	public static void createinventory() {
		inventory = new HashMap<>();
		inventory.put("book", "book");
		inventory.put("music CD", "entertainment");
		inventory.put("chocolate bar", "food");
		inventory.put("imported box of chocolates", "food");
		inventory.put("imported bottle of perfume", "FMCG");
		inventory.put("bottle of perfume", "FMCG");
		inventory.put("box of imported chocolates", "food");
		inventory.put("packet of headache pills", "medical");

		GOODS_TYPE_EXEMPTED = new ArrayList<>();
		GOODS_TYPE_EXEMPTED.add("book");
		GOODS_TYPE_EXEMPTED.add("food");
		GOODS_TYPE_EXEMPTED.add("medical");
	}

}
