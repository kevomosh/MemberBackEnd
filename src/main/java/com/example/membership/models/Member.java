package com.example.membership.models;

import com.example.membership.inputDTO.MemberInp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate date_of_birth;

    public Member(MemberInp memberInp, LocalDate dob) {
        this.firstName = memberInp.getFirstName();
        this.lastName = memberInp.getLastName();
        this.email = memberInp.getEmail();
        this.phoneNumber = memberInp.getPhoneNumber();
        this.date_of_birth = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (!getId().equals(member.getId())) return false;
        if (!getFirstName().equals(member.getFirstName())) return false;
        return getLastName().equals(member.getLastName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }
}
