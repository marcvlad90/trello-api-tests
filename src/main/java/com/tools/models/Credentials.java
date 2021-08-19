package com.tools.models;

import com.tools.constants.EnvironmentConstants;

public class Credentials {
    private String key, token;

    public Credentials() {
        this.setKey(EnvironmentConstants.APP_KEY);
        this.setToken(EnvironmentConstants.TOKEN);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
