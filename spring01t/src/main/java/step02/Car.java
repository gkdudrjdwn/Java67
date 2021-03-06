package step02;

import java.util.Date;

public class Car {
  String  model;
  int     capacity;
  Date    outDate;
  
  @Override
  public String toString() {
    return "Car [model=" + model + ", capacity=" + capacity + ", outDate="
        + outDate + "]";
  }

  public Car() {
    System.out.println("Car() 생성");
  }
  
  public Car(String model, int capacity) {
    System.out.println("Car(String,int) 생성");
    this.model = model;
    this.capacity = capacity;
  }
  
  public Car(int capacity, String model) {
    System.out.println("Car(int,String) 생성");
    this.model = model;
    this.capacity = capacity;
  }
  
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    System.out.println("setModel() 호출됨.");
    this.model = model;
  }
  public int getCapacity() {
    return capacity;
  }
  public void setCapacity(int capacity) {
    System.out.println("setCapacity() 호출됨.");
    this.capacity = capacity;
  }
  public Date getOutDate() {
    return outDate;
  }
  public void setOutDate(Date outDate) {
    System.out.println("setOutDate() 호출됨.");
    this.outDate = outDate;
  }
  
  
}
