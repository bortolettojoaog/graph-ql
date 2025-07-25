package com.bortolettotech.graphql.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String id;
    private String street;
    private String number;
    private String postalCode;
    private String district;
    private String city;
    private String uf;
    private String complement;
    private String userId;
}
