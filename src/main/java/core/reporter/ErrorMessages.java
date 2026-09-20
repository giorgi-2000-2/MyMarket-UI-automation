package core.reporter;

public enum ErrorMessages {
    TEST_SCOPE_ALREADY_ENTERED(
            " TestScope უკვე გახსნილია ამ თრედზე — წინა ტესტს exit() არ გამოუძახებია "),
    TEST_SCOPE_OUTSIDE(" TestScope-ის გარეთ მოთხოვნილია: %s"),
    REPORTER_NOT_INIT(" Reporter არ არის ინიციალიზებული "),
    EMPTY_NAME(" სახელი ცარიელია "),
    CONFIG_KEY_NOT_NUMBER(" კონფიგის გასაღები არ არის რიცხვი: %s"),
    JSON_READ_FAILED("JSON ფაილის წაკითხვა ვერ მოხერხდა: %s"),
    CONFIG_FILE_NOT_FOUND("%s ვერ მოიძებნა classpath-ზე"),
    CONFIG_FILE_READ_FAILED("%s-ის წაკითხვა ვერ მოხერხდა");

    private final String template;

    ErrorMessages(String template) {
        this.template = template;
    }

    public String get() {
        return template;
    }

    public String format(Object... args) {
        return String.format(template, args);
    }
}