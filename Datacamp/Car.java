package Datacamp;

class Car {

  private String color;
  private String model;
  private int year;

  public Car(String color, String model, int year) {
    this.color = color;
    this.model = model;
    this.year = year;
  }

  // method
  static int getSpeed(int km) {
    return km * 1000 / 3600;
  }

  // getter
  public String getColor() {
    return color;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  // setter
  public void setYear(int year) {
    this.year = year;
  }
}