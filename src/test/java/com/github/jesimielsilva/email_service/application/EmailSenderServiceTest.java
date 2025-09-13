package com.github.jesimielsilva.email_service.application;

import com.github.jesimielsilva.email_service.adapters.EmailSenderGateway;
import com.github.jesimielsilva.email_service.core.exceptions.EmailServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



public class EmailSenderServiceTest {

    @Mock
    private EmailSenderGateway emailSenderGateway;

    private  EmailSenderService emailSenderService;

    @BeforeEach
    void Setup() {
        MockitoAnnotations.openMocks(this);
        emailSenderService = new EmailSenderService(emailSenderGateway);
    }

    @Test
    void deveChamarGatewayComParamentrosCorretos() {
        emailSenderService.sendEmail("cash.money.app7@gmail.com", "Assunto", "corpo do email");

        verify(emailSenderGateway, times(1))
                .sendEmail("cash.money.app7@gmail.com", "Assunto", "corpo do email");
    }

    @Test
    void deveLancarExcecaoSeGatewayFalhar() {
        doThrow(new EmailServiceException("Error"))
                .when(emailSenderGateway).sendEmail(anyString(), anyString(), anyString());

        assertThrows(EmailServiceException.class, () ->
                emailSenderService.sendEmail("cash.money.app7@gmail.com", "Assunto", "corpo do email"));
    }
}
