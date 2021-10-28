package com.dev.sisgestionMercados.Complement;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Sms {

	public static final String ACCOUNT_SID = "ACfb3606cbf5c104f8980c4a1d030f150a";
	  public static final String AUTH_TOKEN = "4207d9f31773798b608113b1dbd89026";

	  public void sendSms(String code, String telephone){
		  System.out.println("-->  LLEGA");
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        //+18456134556
	    
	    System.out.println("-->  LLEGA22");
	    Message message = Message.creator(new PhoneNumber("+591"+telephone), new PhoneNumber("+18456134556"),"   El código para la verificación de su número es: "+code).create();
	    System.out.println("-->  LLEGA A REALLIZAR UN MENSAJE");
	    System.out.println("-->  "+message.getSid());
	    System.out.println("--> -->  "+message.getMessagingServiceSid());
	  }
}
/*
 public class Example {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+14159352345"),
                new com.twilio.type.PhoneNumber("+14158141829"),
                "Where's Wallace?")
            .create();

        System.out.println(message.getSid());
    }
}
 */