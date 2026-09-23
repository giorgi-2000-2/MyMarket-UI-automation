package core.config;

/**
 * კატეგორიების ხის შემოვლის პარამეტრები.
 * stateFile ცარიელია -> resume გამორთულია (მეხსიერებაში ინახება).
 */
public interface IAppTree {
    int maxDepth();
    String stateFile();
}
