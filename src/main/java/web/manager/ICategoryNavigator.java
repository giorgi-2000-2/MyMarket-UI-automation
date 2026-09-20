package web.manager;


public interface ICategoryNavigator {
    void openDropdown();
    void clickOption(int index);
    void goBack();
    boolean isLeaf();
    boolean isBackButtonPresent();
    int optionsCount();
    int startIndex();
}