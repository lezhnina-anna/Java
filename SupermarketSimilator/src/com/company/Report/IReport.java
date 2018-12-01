package com.company.Report;

import com.company.Product.Product;

public interface IReport {
  void addItem(Product item, double count);

  void printReport();
}
