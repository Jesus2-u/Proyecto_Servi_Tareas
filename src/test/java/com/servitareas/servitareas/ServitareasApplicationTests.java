package com.servitareas.servitareas;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Temporalmente deshabilitado porque GitHub Actions
// no puede conectarse a la base de datos PostgreSQL local.
// Las pruebas unitarias seguirán ejecutándose normalmente.

@Disabled
@SpringBootTest
class ServitareasApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring cargue correctamente.
    }
}