package com.ionos.codingtask.customer;

class Customer {

  private final String firstName;
  private final String lastName;

  Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  String getFirstName() {
    return firstName;
  }

  String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }
}
