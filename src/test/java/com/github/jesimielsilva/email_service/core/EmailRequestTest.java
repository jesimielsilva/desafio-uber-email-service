package com.github.jesimielsilva.email_service.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailRequestTest {

    @Test
    void deveCriarEmailResquestComValoresCorretos() {
        EmailRequest request = new EmailRequest("cash.money.app7@gmail.com", "Assunto", "corpo do email");

        assertEquals("cash.money.app7@gmail.com", request.to());
        assertEquals("Assunto", request.subject());
        assertEquals("corpo do email", request.body());
    }
}
