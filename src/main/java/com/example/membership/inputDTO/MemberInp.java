package com.example.membership.inputDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInp {
    private String idStr;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int birthYear;
    private int birthMonth;
    private int dayOfBirth;
}
