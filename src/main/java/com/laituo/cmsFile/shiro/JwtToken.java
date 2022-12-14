package com.laituo.cmsFile.shiro;

import com.laituo.cmsFile.pojo.User;
import com.laituo.cmsFile.utils.JwtUtils;
import lombok.Data;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * @PackageName:com.ye.back.token
 * @ClassName:JwtToken
 * @Description:
 * @author:何进业
 * @date:2021/5/31 16:47
 */
@Data
@Component
public class JwtToken implements HostAuthenticationToken, RememberMeAuthenticationToken {
    private String token;

    private User user;

    private char[] password;
    private boolean rememberMe;
    private String host;


    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    public JwtToken() {
        this.rememberMe = false;
    }

    public JwtToken(String token, char[] password) {
        this(token, (char[])password, false, (String)null);
    }

    public JwtToken(String token, String password) {
        this(token, (char[])(password != null ? password.toCharArray() : null), false, (String)null);
    }

    public JwtToken(String token, User user) {
        this.host=null;
        this.rememberMe=false;
        this.token=token;
        this.password=null;
        this.user=user;
    }

    public JwtToken(String token, char[] password, String host) {
        this(token, password, false, host);
    }

    public JwtToken(String token, String password, String host) {
        this(token, password != null ? password.toCharArray() : null, false, host);
    }

    public JwtToken(String token, char[] password, boolean rememberMe) {
        this(token, (char[])password, rememberMe, (String)null);
    }

    public JwtToken(String token, String password, boolean rememberMe) {
        this(token, (char[])(password != null ? password.toCharArray() : null), rememberMe, (String)null);
    }

    public JwtToken(String token, char[] password, boolean rememberMe, String host) {
        this.rememberMe = false;
        this.token = token;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public JwtToken(String username, String password, boolean rememberMe, String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" - ");
        sb.append(JwtUtils.getIdByJwtToken(this.token).toString());
        sb.append(", rememberMe=").append(this.rememberMe);
        if (this.host != null) {
            sb.append(" (").append(this.host).append(")");
        }
        return sb.toString();
    }
}
