package web.config;


/** სექციის გვერდის მოსალოდნელი URL (მხოლოდ web-ს სჭირდება). */
public interface ISectionUrls {
    String urlOf(Section section);
}
