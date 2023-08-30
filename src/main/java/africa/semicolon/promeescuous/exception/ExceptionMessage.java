package africa.semicolon.promeescuous.exception;

public enum ExceptionMessage {

    USER_NOT_FOUND_EXCEPTION("User no found"),

    USER_WITH_EMAIL_NOT_FOUND_EXCEPTION("user with email %s not found"),

    ACCOUNT_ACTIVATION_FAILED_EXCEPTION("Account activation was not successfully"),

    INVALID_CREDENTIALS_EXCEPTION("Invalid Credentials"),

    MESSAGE_SENT_SUCCESSFULLY("Message sent successfully."),

    SUCCESS("Success"),
    NOTIFICATION_SENT_SUCCESSFULLY("Notification sent successfully"),
    MEDIA_NOT_FOUND("Medial not found"),

    AUTHENTICATION_NOT_SUPPORTED("Authentication not supported on this system"),

    RESOURCE_NOT_FOUND("");





    ExceptionMessage(){


    }

    ExceptionMessage(String message){
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
