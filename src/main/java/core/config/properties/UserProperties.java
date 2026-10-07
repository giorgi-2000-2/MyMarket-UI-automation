package core.config.properties;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.config.IUserConfig;
import core.config.TypedPropertiesHelper;


@Singleton
public class UserProperties implements IUserConfig {
    private final TypedPropertiesHelper helper;
@Inject
    public UserProperties(TypedPropertiesHelper helper) {
        this.helper = helper;
    }

    @Override
    public String loginMail()     { return helper.secret("MYMARKET_USER", "login.mail"); }

    @Override
    public String loginPassword() { return helper.secret("MYMARKET_PASSWORD", "login.password"); }

    @Override
    public String userId() {return helper.require("login.id");  }

    @Override
    public String expectedUserName() {return helper.require("name");  }




}
