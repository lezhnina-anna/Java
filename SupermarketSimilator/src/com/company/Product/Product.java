package com.company.Product;

import java.math.BigDecimal;

public class Product {
  private String name;
  private BigDecimal price;
  private boolean byWeight;
  private double weight;
  private boolean canChildBuy;

  public Product(String name, boolean canChildBuy, boolean byWeight, BigDecimal price) {
    this.name = name;
    this.canChildBuy = canChildBuy;
    this.byWeight = byWeight;
    this.price = price;
  }

  void setWeight(double weight) {
    this.weight = weight;
  }

  public double getWeight() {
    return this.weight;
  }

  public boolean getCanChildBuy() {
    return this.canChildBuy;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setByWeight(boolean byWeight) {
    this.byWeight = byWeight;
  }

  public boolean getByWeight() {
    return this.byWeight;
  }
}
