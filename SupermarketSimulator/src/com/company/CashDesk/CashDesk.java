package com.company.CashDesk;

import com.company.Customer.Customer;
import com.company.Discount.Discount;
import com.company.Logger.Logger;
import com.company.Product.Product;
import com.company.Report.Report;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CashDesk implements ICashDesk {
  private Queue<Customer> queue = new LinkedList<>();
  private String paymentMethod;
  private Report report = new Report();
  private Discount discount = new Discount();
  private BigDecimal generalPrice;
  private Logger logger = new Logger();


  public void setPaymentMethod(String value) {
    this.paymentMethod = value;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public Customer getFirstBuyerFromQueue() {
    return queue.poll();
  }

  public void addToQueue(Customer customer) {
    queue.add(customer);
  }

  public boolean isQueueEmpty() {
    return queue.isEmpty();
  }

  public void getDailyReport() {
    this.report.printReport();
  }

  public boolean serveBuyer(Customer customer) {
    generalPrice = new BigDecimal(0);
    BigDecimal sum = new BigDecimal(0);
    for (Map.Entry<Product, Double> entry : customer.getBasket().getAllProducts().entrySet()) {
      Product item = entry.getKey();
      boolean success = false;
      if (!item.getCanChildBuy() && customer.getCategory().equals("child")) {
        logger.print(customer.getName() + " is a child. Can't buy " + item.getName(), -1);
      } else {
        double count = entry.getValue();
        sum = sum.add(item.getPrice().multiply(new BigDecimal(count)));
        //generalPrice = generalPrice.subtract(discount.getDiscount(customer, generalPrice));
        switch (this.paymentMethod) {
          case "card":
            success = this.payByCard(sum, customer);
            break;
          case "cash":
            success = this.payByCash(sum, customer);
            break;
          case "discount":
            success = this.payByDiscount(sum, customer);
            break;
        }
        if (success) {
          generalPrice = sum;
          report.addItem(item, entry.getValue());
        } else {
          logger.print(customer.getName() + " has't money for " + item.getName(), -1);
        }
      }
    }
    return !generalPrice.equals(new BigDecimal(0.0));
  }

  public BigDecimal getGeneralPrice() {
    return this.generalPrice;
  }

  private boolean payByCard(BigDecimal generalPrice, Customer customer) {
    if (generalPrice.compareTo(customer.getEMoney()) < 0) {
      customer.setEMoney(customer.getCash().subtract(generalPrice));
      return true;
    }
    return false;
  }

  private boolean payByCash(BigDecimal generalPrice, Customer customer) {
    if (generalPrice.compareTo(customer.getCash()) < 0) {
      customer.setCash(customer.getCash().subtract(generalPrice));
      return true;
    }
    return false;
  }

  private boolean payByDiscount(BigDecimal generalPrice, Customer customer) {
    if (generalPrice.compareTo(new BigDecimal(customer.getDiscountPoints())) < 0) {
      customer.setEMoney(customer.getCash().subtract(generalPrice));
      return true;
    }
    return false;
  }
}

