package main;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Goods;
import entity.Cart;
import entity.Receipt;
import service.CalculationService;

public class SalesTaxApplication {

	public static void main(String[] args) {
		CalculationService.createinventory();
		System.out.println("Enter Number of Items");
		List<Goods> cart = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Integer n = sc.nextInt();
		System.out.println("Please enter " + String.valueOf(n) + " items one by one or paste all the items");
		sc.nextLine();
		CalculationService calService = new CalculationService();
		for (int i = 1; i <= n; i++) {
			String fromScanner = sc.nextLine();
			if (fromScanner.isEmpty()) {
				System.out.println("Please do not enter empty value");
				return;
			}
			Goods res = calService.addToCart(fromScanner);
			if (res == null) {
				return;
			}
			cart.add(res);
		}
		Receipt receipt = calService.calculateBill(cart);
		System.out.println("--------Bil Receipt----------");
		for (Cart item : receipt.getCartList()) {
			System.out.println(item.getQuantity() + " " + item.getProductname() + ": "
					+ item.getTaxedPrice().round(new MathContext(4)));
		}
		System.out.println("Sales tax: " + receipt.getSalesTax().setScale(2, RoundingMode.CEILING));
		System.out.println("Total: " + receipt.getTotalAmount().setScale(2, RoundingMode.CEILING));
	}

}
