package cnpm.ergo.service.implement;

import cnpm.ergo.service.interfaces.IOTPService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class OTPService implements IOTPService {

    private static final String SMTP_HOST = "smtp.gmail.com";  // Gmail SMTP host
    private static final String SMTP_PORT = "587";            // Gmail SMTP port
    private static final String SMTP_USERNAME = "phucka004@gmail.com";  // Your Gmail email
    private static final String SMTP_PASSWORD = "xgskiyxtkcmnzdzx";     // Your Gmail app password

    // Method to generate a 6-digit OTP
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);  // Generate a 6-digit OTP
        System.out.println("Generated OTP: " + otp); // Log the generated OTP for debugging
        return String.valueOf(otp);
    }

    public void sendOTPEmail(String toEmail, String otp) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", SMTP_HOST);
            properties.put("mail.smtp.port", SMTP_PORT);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            System.out.println("Mail properties: " + properties); // Debugging log

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
                }
            });

            System.out.println("Session initialized: " + session); // Debugging log

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP for Password Reset");
            message.setText("Your OTP for password reset is: " + otp);

            Transport.send(message);
            System.out.println("OTP email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace(); // Log detailed error
            throw new RuntimeException("Failed to send OTP email.", e);
        }
    }

}
