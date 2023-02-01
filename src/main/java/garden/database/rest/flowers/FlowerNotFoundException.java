package garden.database.rest.flowers;

public class FlowerNotFoundException extends RuntimeException{

    public FlowerNotFoundException(){}

    public FlowerNotFoundException(String message){
        super(message);
    }

    public FlowerNotFoundException(Throwable cause){
        super(cause);
    }

    public FlowerNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public FlowerNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
