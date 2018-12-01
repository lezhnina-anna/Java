package com.company.Report;

import com.company.Product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class Report implements IReport {
  private Map<Product, Double> list = new LinkedHashMap<>();

  public void addItem(Product item, double count) {
    if (this.list.containsKey(item)) {
      Double value = this.list.get(item);
      this.list.put(item, value + count);
      return;
    }
    list.put(item, count);
  }

  public void printReport() {
    BigDecimal generalPrice = new BigDecimal(0);
    Product item;
    Double count;
    BigDecimal price;
    for (Map.Entry<Product, Double> entry : list.entrySet()) {
      item = entry.getKey();
      count = entry.getValue();
      price = item.getPrice().multiply(new BigDecimal(count));
      generalPrice =  generalPrice.add(price);
      System.out.println(item.getName() + " " + count + " " + price.setScale(2, RoundingMode.HALF_UP));
    }
    System.out.println("Всего продано товаров на сумму: " + generalPrice.setScale(2, RoundingMode.HALF_UP));
  }
}
