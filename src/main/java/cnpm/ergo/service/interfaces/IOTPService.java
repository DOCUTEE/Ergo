package cnpm.ergo.service.interfaces;


import jakarta.mail.MessagingException;

public interface IOTPService {
    String generateOTP();  // Method to generate OTP
    void sendOTPEmail(String toEmail, String otp) throws MessagingException;  // Method to send OTP to email
}

