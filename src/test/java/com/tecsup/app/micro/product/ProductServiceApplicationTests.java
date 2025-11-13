package com.tecsup.app.micro.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Verifica que el contexto de la aplicación se carga correctamente
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void main_WhenApplicationStarts_ShouldLoadContext() {
        // Verifica que la aplicación puede iniciarse
        ProductServiceApplication.main(new String[]{});
        assertThat(applicationContext).isNotNull();
    }
}
