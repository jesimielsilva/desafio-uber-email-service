package com.github.jesimielsilva.email_service.controllers;

import com.github.jesimielsilva.email_service.application.EmailSenderService;
import com.github.jesimielsilva.email_service.core.EmailRequest;
import com.github.jesimielsilva.email_service.core.exceptions.EmailServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class EmailSenderControllerTest {

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private EmailSenderController emailSenderController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveEnviarEmailComSucesso() {
        EmailRequest request = new EmailRequest("cash.money.app7@gmail.com", "Assunto", "corpo do email");

        doNothing().when(emailSenderService).sendEmail(anyString(), anyString(), anyString());

        ResponseEntity<String> response = emailSenderController.sendEmail(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("email sent successfully", response.getBody());
        verify(emailSenderService, times(1)).sendEmail("cash.money.app7@gmail.com", "Assunto", "corpo do email");

    }

    @Test
    void deveRetornarErroQuandoServicoFalhar() {
        //arrange
        EmailRequest request = new EmailRequest("cash.money.app7@gmail.com", "Assunto", "corpo do email");

        doThrow(new EmailServiceException("Erro no envio"))
                .when(emailSenderService).sendEmail(anyString(), anyString(), anyString());

        ResponseEntity<String> response = emailSenderController.sendEmail(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while sending email", response.getBody());

    }
}
