package uni.web_lab2.Exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) {
        super("Category by id:" + id + " does not exist");
    }
}
