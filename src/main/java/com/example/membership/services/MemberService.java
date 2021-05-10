package com.example.membership.services;

import com.example.membership.inputDTO.MemberInp;
import com.example.membership.models.Member;
import com.example.membership.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(String idStr) {
        var id = getUuidFromString(idStr);
        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member not available"));
    }

    public void create(MemberInp memberInp) {
        var date_OfBirth = getDOB(memberInp);
        var member = new Member(memberInp, date_OfBirth);
        memberRepository.save(member);
    }

    public void update(MemberInp memberInp) {
        var member = memberRepository.findById(getUuidFromString(memberInp.getIdStr()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "member not present"));

        var dob = getDOB(memberInp);
        member.setDate_of_birth(dob);
        member.setFirstName(memberInp.getFirstName());
        member.setLastName(memberInp.getLastName());
        member.setEmail(memberInp.getEmail());
        member.setPhoneNumber(memberInp.getPhoneNumber());

        memberRepository.save(member);
    }

    public void delete(String idStr) {
        var id = getUuidFromString(idStr);
        var member = memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not available"));

        memberRepository.delete(member);
    }

    private LocalDate getDOB(MemberInp memberInp) {
        return LocalDate.of(memberInp.getBirthYear(), memberInp.getBirthMonth(),
                memberInp.getDayOfBirth());
    }


    private UUID getUuidFromString(String idStr) {
        try {
            return UUID.fromString(idStr);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Id");
        }
    }

}
