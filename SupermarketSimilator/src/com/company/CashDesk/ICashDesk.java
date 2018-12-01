package com.company.CashDesk;

import com.company.Customer.Customer;

import java.math.BigDecimal;

public interface ICashDesk {
  void setPaymentMethod(String value);

  String getPaymentMethod();

  boolean serveBuyer(Customer customer);

  Customer getFirstBuyerFromQueue();

  BigDecimal getGeneralPrice();

  void addToQueue(Customer customer);

  boolean isQueueEmpty();

  void getDailyReport();
}
