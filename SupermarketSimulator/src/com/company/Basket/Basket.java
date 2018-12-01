package com.company.Basket;

import com.company.Product.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Basket implements IBasket {
  private Map<Product, Double> products = new LinkedHashMap<>();

  public void addProduct(Product product, double count) {
    if (this.products.containsKey(product)) {
      Double value = this.products.get(product);
      this.products.put(product, value + count);
      return;
    }
    this.products.put(product, count);
  }

  public Map<Product, Double> getAllProducts() {
    return this.products;
  }

  public void clear() {
    this.products.clear();
  }

  public int getProductsCount() {
    return products.size();
  }
}
