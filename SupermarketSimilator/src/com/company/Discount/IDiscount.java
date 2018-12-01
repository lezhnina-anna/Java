package com.company.Discount;

import com.company.Customer.Customer;

import java.math.BigDecimal;

public interface IDiscount {
  BigDecimal getDiscount(Customer customer, BigDecimal generalPrice);
}
