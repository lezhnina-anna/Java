package com.company.PaymentMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentMethod implements IPaymentMethod {
  private List<String> methods = new ArrayList<>(Arrays.asList("cash", "card", "discount"));

  public String getMethodByIndex(int index) {
    return this.methods.get(index);
  }
}
