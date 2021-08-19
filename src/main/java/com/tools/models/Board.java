package com.tools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tools.constants.EnvironmentConstants;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Board extends Credentials {
    public Board() {
        this.setKey(EnvironmentConstants.APP_KEY);
        this.setToken(EnvironmentConstants.TOKEN);
    }

    @Override
    public String toString() {
        return "Board [name=" + name + ", id=" + id + ", email=" + email + "]";
    }

    private String name, id, email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
