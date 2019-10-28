package com.stackroute.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DTOUser {
    private String username;
    private String firstName;
    private String lastName;
    private String dob;
    private String role = "reader/author";
    private String email;
    private Long phoneNumber;
    private String gender;
    private String nationality;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;

}
