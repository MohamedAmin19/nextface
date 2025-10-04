package com.nextface.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    public void sendReservationEmail(String to, String name) throws IOException {
        Email fromEmail = new Email("reservations@nextfaceofficial.com");
        Email toEmail = new Email(to);
        String subject = "Welcome to Next Face";
        String body = "Hi " + name + ",\n\n"
                + "Thank you for registering with NextFace! "
                + "Your reservation has been successfully created.\n\n"
                + "Best regards,\nNextFace Team";

        Content content = new Content("text/plain", body);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        // SendGrid client uses the API key from the environment variable
        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);

        // Optional: log or handle response
        System.out.println("SendGrid status code: " + response.getStatusCode());
        System.out.println("SendGrid response body: " + response.getBody());
    }
}
