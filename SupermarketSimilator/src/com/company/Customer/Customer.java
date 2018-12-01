package com.company.Customer;

import com.company.Basket.Basket;
import com.company.Logger.Logger;
import com.company.Product.Product;
import com.company.Supermarket.Supermarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Customer implements ICustomer {
  private ArrayList<String> allCategory = new ArrayList<>(Arrays.asList("child", "adult", "retired"));
  private String name;
  private BigDecimal cash;
  private BigDecimal eMoney;
  private int discountPoints;
  private String category;
  private Basket basket = new Basket();
  private final Random random = new Random();
  private Logger logger = new Logger();

  public BigDecimal getCash() {
    return cash;
  }

  public void setCash(BigDecimal cash) {
    this.cash = cash;
  }

  public BigDecimal getEMoney() {
    return eMoney;
  }

  public void setEMoney(BigDecimal eMoney) {
    this.eMoney = eMoney;
  }

  public int getDiscountPoints() {
    return discountPoints;
  }

  public String getCategory() {
    return category;
  }

  public Basket getBasket() {
    return this.basket;
  }

  public String getName() {
    return name;
  }

  public void setDiscountPoints(int discountPoints) {
    this.discountPoints = discountPoints;
  }

  public Customer(String name, String category, int discountPoints, BigDecimal eMoney, BigDecimal cash) {
    this.name = name;
    this.category = category;
    this.cash = cash;
    this.discountPoints = discountPoints;
    this.eMoney = eMoney;
  }

  public Customer(String name) {
    BigDecimal money = new BigDecimal(random.nextDouble() * 10000);
    BigDecimal eMoney = new BigDecimal(random.nextDouble() * 100);
    int discountPoints = random.nextInt(1000);
    this.eMoney = eMoney;
    this.cash = money;
    this.name = name;
    this.category = RandomCategory();
    this.discountPoints = discountPoints;
  }

  public void takeRandomProducts(Supermarket supermarket) {
    int MAX_COUNT = 3;
    int count = random.nextInt(MAX_COUNT) + 1;
    double productCount;
    int time = supermarket.getCurrentTime();
    for (int i = 0; i < count; i++) {
      int productNum = random.nextInt(supermarket.getProducts().size()) + 1;
      String productName = "Product#" + productNum;
      try {
        Product product = supermarket.getProductByName(productName);
        if (product.getByWeight()) {
          productCount = random.nextDouble() * 1000;
        } else {
          productCount = random.nextInt(20) + 1;
        }
        if (productCount == 0) {
          continue;
        }
        if (!supermarket.removeProduct(product, productCount)) {
          productCount = supermarket.getAll(product);
          this.basket.addProduct(product, productCount);
        } else {
          this.basket.addProduct(product, productCount);
        }
        time += 2;
        logger.print(this.name + " take " + productCount + " items of " + productName, time);
      } catch (Exception err) {
        System.out.println(err.toString());
      }
    }
  }

  private String RandomCategory() {
    int index = random.nextInt(3);
    return allCategory.get(index);

  }
}
