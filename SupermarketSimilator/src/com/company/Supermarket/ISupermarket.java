package com.company.Supermarket;

import com.company.Product.Product;

import java.util.Map;

public interface ISupermarket {
  void work();

  int getCurrentTime();

  void setProducts(Map<Product, Double> list);

  Map<Product, Double> getProducts();

  Product getProductByName(String name) throws Exception;

  boolean removeProduct(Product item, double count);

  double getAll(Product item);
}
