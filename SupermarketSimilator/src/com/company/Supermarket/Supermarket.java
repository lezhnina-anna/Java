package com.company.Supermarket;

import com.company.CashDesk.CashDesk;
import com.company.Customer.Customer;
import com.company.Logger.Logger;
import com.company.PaymentMethod.PaymentMethod;
import com.company.Product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Supermarket implements ISupermarket {
  private Map<Product, Double> list = new LinkedHashMap<>();
  private CashDesk cashDesk = new CashDesk();
  private int currentTime;
  private int customersCount = 0;
  private PaymentMethod paymentMethods = new PaymentMethod();
  private final int openTime; // in minutes since 00:00
  private final int workTime; // in minutes
  private final Random random = new Random();
  private Logger logger = new Logger();

  public Supermarket(int openTime, int workTime) {
    this.openTime = openTime;
    this.workTime = workTime;
    this.currentTime = openTime;
  }

  public int getCurrentTime(){
    return this.currentTime;
  }

  public void work() {
    this.list = generateProductList();
    while (currentTime < openTime + workTime || !cashDesk.isQueueEmpty()) {
      if (currentTime < openTime + workTime) {
        generateCustomersInCurrentTime();
      }
      serveCurrentCustomer();
      currentTime++;
    }
    cashDesk.getDailyReport();
  }

  public Product getProductByName(String name) throws Exception {
    for (Map.Entry<Product, Double> entry : list.entrySet()) {
      Product product = entry.getKey();
      if (product.getName().equals(name)) {
        return product;
      }
    }
    throw new Exception(name + " doesn't exist");
  }

  private void serveCurrentCustomer() {
    Customer customer = cashDesk.getFirstBuyerFromQueue();
    if (customer == null) {
      return;
    }
    if (customer.getBasket().getProductsCount() == 0) {
      logger.print(customer.getName() + " leaved supermarket", currentTime);
      return;
    }
    cashDesk.setPaymentMethod(paymentMethods.getMethodByIndex(random.nextInt(3)));
    if (this.cashDesk.serveBuyer(customer)) {
      int servedTime = currentTime + customer.getBasket().getProductsCount(); //spend minute for each product
      logger.print(customer.getName() + " was served. Payed " + cashDesk.getGeneralPrice().setScale(2, RoundingMode.HALF_UP)
              + " by " + cashDesk.getPaymentMethod(), servedTime); //in current time
    }
  }

  private void generateCustomersInCurrentTime() {
    final int MAX_CUSTOMERS_IN_SAME_TIME = 5;
    final int TIME_FOR_ONE_PRODUCT = 2;
    for (int i = 0; i < random.nextInt(MAX_CUSTOMERS_IN_SAME_TIME); i++) {
      final Customer customer = new Customer("Customer#" + this.customersCount++);
      logger.print(customer.getName() + " arrived to supermarket", currentTime);
      customer.takeRandomProducts(this);
      cashDesk.addToQueue(customer);
      final int spendTime = currentTime + customer.getBasket().getProductsCount() * TIME_FOR_ONE_PRODUCT;
      logger.print(customer.getName() + " in queue", spendTime);
    }
  }

  private Map<Product, Double> generateProductList() {
    int MAX_PRODUCTS_TYPE_COUNT = 100;
    int MAX_PRODUCTS_COUNT = 100;

    Map<Product, Double> products = new LinkedHashMap<>();
    int count = random.nextInt(MAX_PRODUCTS_TYPE_COUNT) + 1;
    for (int i = 0; i < count; i++) {
      Product product = this.generateProduct(i);
      if (product.getByWeight()) {
        products.put(product, random.nextDouble() * 1000);
      } else {
        int countProduct = random.nextInt(MAX_PRODUCTS_COUNT);
        products.put(product, (double) countProduct);
      }
    }
    return products;
  }

  private Product generateProduct(int number) {
    String name = "Product#" + number;
    boolean canChildBuy = random.nextBoolean();
    boolean byWeight = random.nextBoolean();
    BigDecimal price = new BigDecimal(random.nextDouble() * 100);
    return new Product(name, canChildBuy, byWeight, price);
  }

  public boolean removeProduct(Product item, double count) {
    Double productCount = 0.0;
    for (Map.Entry<Product, Double> entry : list.entrySet()) {
      Product product = entry.getKey();
      if (product.equals(item)) {
        productCount = entry.getValue();
      }
    }
    if (productCount - count < 0) {
      return false;
    }
    list.remove(item);
    list.put(item, productCount - count);
    return true;
  }

  public double getAll(Product item) {
    double result = 0.0;
    for (Map.Entry<Product, Double> entry : list.entrySet()) {
      Product product = entry.getKey();
      if (product.equals(item)) {
        result = entry.getValue();
      }
    }
    list.remove(item);
    return result;
  }

  public Map<Product, Double> getProducts() {
    return list;
  }

  public void setProducts(Map<Product, Double> list) {
    this.list = list;
  }
}
