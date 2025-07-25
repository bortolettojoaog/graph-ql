package com.bortolettotech.graphql.model;

import com.bortolettotech.graphql.type.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserData {
    private String id;
    private String email;
    private boolean emailVerified;
    private RoleEnum role;
    private String avatarFileId;
    private Address address;
    private String userId;
}
