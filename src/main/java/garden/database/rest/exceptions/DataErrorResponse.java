package garden.database.rest.exceptions;

public class DataErrorResponse {

    private int status;
    private String message;
    private long timeStmp;

    public DataErrorResponse() {}

    public DataErrorResponse(int status, String message, long timeStmp) {
        this.status = status;
        this.message = message;
        this.timeStmp = timeStmp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStmp() {
        return timeStmp;
    }

    public void setTimeStmp(long timeStmp) {
        this.timeStmp = timeStmp;
    }
}
