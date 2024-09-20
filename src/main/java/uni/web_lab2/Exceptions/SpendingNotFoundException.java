package uni.web_lab2.Exceptions;

public class SpendingNotFoundException extends RuntimeException{
    public SpendingNotFoundException(Long id) {
        super("Spending by id:" + id + " does not exist");
    }
}
