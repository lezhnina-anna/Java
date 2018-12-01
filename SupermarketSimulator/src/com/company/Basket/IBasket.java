package com.company.Basket;

import com.company.Product.Product;

import java.util.Map;

public interface IBasket {
  void addProduct(Product product, double count);

  Map<Product, Double> getAllProducts();

  void clear();

  int getProductsCount();
}
