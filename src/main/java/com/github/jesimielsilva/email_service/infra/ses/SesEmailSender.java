package com.github.jesimielsilva.email_service.infra.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.github.jesimielsilva.email_service.adapters.EmailSenderGateway;
import com.github.jesimielsilva.email_service.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("cash.money.app7@gmail.com")
                .withDestination(new Destination().withBccAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );
        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonSimpleEmailServiceException exception) {
            throw new EmailServiceException("Failure while sending email", exception);
        }
    }
}
