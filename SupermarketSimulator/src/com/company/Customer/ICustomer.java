package com.company.Customer;

import com.company.Basket.Basket;
import com.company.Supermarket.Supermarket;

import java.math.BigDecimal;

public interface ICustomer {

  BigDecimal getCash();

  void setCash(BigDecimal cash);

  BigDecimal getEMoney();

  void setEMoney(BigDecimal eMoney);

  int getDiscountPoints();

  void setDiscountPoints(int discountPoints);

  void takeRandomProducts(Supermarket supermarket);

  Basket getBasket();

  String getName();

  String getCategory();
}
