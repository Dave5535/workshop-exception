package Customexception;

public class Exceptions extends Exception {

    private String message;
    private Integer errorCode;

    public Exceptions(String message){
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
