package cn.misection.autoreport.report.entity;

import java.io.Serializable;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName SwufeUser
 * @Description TODO
 * @CreateTime 2021年09月08日 15:56:00
 */
public class SwufeUser implements Serializable {

    private volatile static SwufeUser instance = null;

    private String username;

    private String password;

    private SwufeUser() {
    }

    private SwufeUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static SwufeUser getInstance() {
        if (instance == null) {
            synchronized (SwufeUser.class) {
                if (instance == null) {
                    instance = new SwufeUser();
                }
            }
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
