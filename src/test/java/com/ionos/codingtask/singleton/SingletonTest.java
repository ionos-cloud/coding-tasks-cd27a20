package com.ionos.codingtask.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SingletonTest {

  @Test
  void instance_returnsNotNull() {
    Singleton singleton = Singleton.instance();

    assertThat(singleton).isNotNull();
  }

  @Test
  void callingInstanceTwice_returnsTheSameInstance() {
    Singleton s1 = Singleton.instance();
    Singleton s2 = Singleton.instance();

    assertThat(s1).isSameAs(s2);
  }
}
