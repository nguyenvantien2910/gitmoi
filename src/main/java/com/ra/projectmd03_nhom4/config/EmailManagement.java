package com.ra.projectmd03_nhom4.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EmailManagement {
    public static String ORDER_SUBJECT = "Order Success Confirmation";
    public static String REGISTER_SUBJECT = "Registration Success Confirmation";
    public static String CONFIRM_ORDER_SUBJECT = "Order Confirm Success Notification";
    public static String SENDER = "truongthang268@gmail.com";
    public static String HOST_PHONE = "0973902698";
    public static String HOST_ADDRESS = "18 Pham Hung, Toa nha Song Da";

    public static String orderConfirmation(String customerName, String receivedPhone, String receivedName, String serialNumber, Date createdDate, String shippingAddress) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(customerName).append(",\n\n")
                .append("Thank you for your order!\n\n")
                .append("We are pleased to inform you that your order #").append(serialNumber).append(" has been successfully placed. Here are the details of your order:\n\n")
                .append("Order Details:\n")
                .append("Received Name: ").append(receivedName).append("\n")
                .append("Received Phone: ").append(receivedPhone).append("\n")
                .append("-Shipping Address: ").append(shippingAddress).append("\n\n")
                .append("Created Date: ").append(createdDate).append("\n")
                .append("You can expect to receive your order by ").append(". We will send you another email once your order has been shipped.\n\n")
                .append("If you have any questions or need further assistance, please do not hesitate to contact us at ").append(SENDER).append("or call us at").append(HOST_PHONE + "\n\n")
                .append("Thank you for shopping with us!\n\n")
                .append("Best regards,\n")
                .append("[Cay Canh Shop]\n")
                .append("[Company's Contact Information] \n")
                .append("[0973902698] Nhom 4")
        ;

        return emailContent.toString();
    }

    public static String registerSuccess(String customerName, String username, String customerEmail) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(customerName).append(",\n\n")
                .append("Welcome to [SmartPhone Shop]!\n\n")
                .append("We are thrilled to have you with us. Your registration has been successfully completed. You can now enjoy all the benefits of being a member of our community.\n\n")
                .append("Your Account Details:\n")
                .append("- Username: ").append(username).append("\n")
                .append("- Email: ").append(customerEmail).append("\n\n")
                .append("To get started, please visit our website and log in using your credentials. If you need any assistance, feel free to reach out to us at [Customer Service Email].\n\n")
                .append("Thank you for joining us, and we look forward to serving you!\n\n")
                .append("Best regards,\n")
                .append("[Cay Canh Shop]\n")
                .append("[Company's Contact Information] \n")
                .append("[0973902698] Nhom 4");
        return emailContent.toString();
    }


    public static String orderShipmentConfirmation(String customerName, String orderNumber, String trackingNumber, String estimatedDeliveryDate, String trackingLink) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(customerName).append(",\n\n")
                .append("Great news! Your order #").append(orderNumber).append(" has been shipped and is on its way to you.\n\n")
                .append("Shipment Details:\n")
                .append("- Tracking Number: ").append(trackingNumber).append("\n")
                .append("- Estimated Delivery Date: ").append(estimatedDeliveryDate).append("\n\n")
                .append("You can track your order using the following link: ").append(trackingLink).append("\n\n")
                .append("If you have any questions or need further assistance, please do not hesitate to contact us at [Customer Service Email] or call us at [Customer Service Phone Number].\n\n")
                .append("Thank you for shopping with us!\n\n")
                .append("Best regards,\n")
                .append("[Cay Canh Shop]\n")
                .append("[Company's Contact Information] \n")
                .append("[0973902698] Nhom 4 ");

        return emailContent.toString();
    }

}
