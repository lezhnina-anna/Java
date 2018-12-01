package com.company.Logger;

public class Logger implements ILogger {
  public void print(String message, int time) {
    System.out.println("[time: " + time + "]: " + message);
  }
}
