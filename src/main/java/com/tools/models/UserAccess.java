package com.tools.models;

import com.tools.constants.EnvironmentConstants;

public class UserAccess {
    private String key, token;

    public UserAccess() {
        this.setKey(EnvironmentConstants.API_KEY);
        this.setToken(EnvironmentConstants.API_TOKEN);
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
