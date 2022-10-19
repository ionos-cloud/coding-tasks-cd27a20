package com.ionos.codingtask.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CustomerSorterTest {

  @Test
  void givenFiveCustomers_sort_customersAreSorted() {
    // given
    Customer c1 = new Customer("Scott", "Patton");
    Customer c2 = new Customer("Lucajava", "Tyler");
    Customer c3 = new Customer("Maggie", "Smith");
    Customer c4 = new Customer("Marvin", "Kelley");
    Customer c5 = new Customer("Alvin", "Smith");

    ArrayList<Customer> list = new ArrayList<>(List.of(c1, c2, c3, c4, c5));

    // when
    CustomerSorter.sort(list);

    // then
    assertThat(list).containsExactly(c4, c1, c5, c3, c2);
  }
}
