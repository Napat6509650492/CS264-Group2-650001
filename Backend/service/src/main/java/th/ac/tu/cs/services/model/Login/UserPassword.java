package th.ac.tu.cs.services.model.Login;
import com.fasterxml.jackson.annotation.JsonProperty;
public class UserPassword {
    @JsonProperty("UserName")
    private String UserName ;
    @JsonProperty("PassWord")
    private String PassWord ;

    public UserPassword() {
    }

    public UserPassword(String username, String password) {
        this.UserName = username;
        this.PassWord = password;
    }

    public String getUsername() {
        return UserName;
    }

    public void setUsername(String username) {
        this.UserName = username;
    }

    public String getPassword() {
        return PassWord;
    }

    public void setPassword(String password) {
        this.PassWord = password;
    }
}
