package com.company.Discount;

import com.company.Customer.Customer;

import java.math.BigDecimal;

public class Discount implements IDiscount {
  private BigDecimal discount = new BigDecimal(0);

  public BigDecimal getDiscount(Customer customer, BigDecimal generalPrice) {
    if (customer.getCategory().equals("retired")) {
      discount = generalPrice.multiply(new BigDecimal(0.2));
    }
    return discount;
  }
}
