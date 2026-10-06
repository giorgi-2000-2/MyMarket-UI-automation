package core.exeption;


public class CategoryNotFoundException extends TransientUiException {

    private final String categoryName;

    public CategoryNotFoundException(String categoryName, String message) {
        super(message);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}