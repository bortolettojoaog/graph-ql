package com.bortolettotech.graphql.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private String subKeycloak;
    private String name;
    private String telephone;
    private UserData userData;
}
