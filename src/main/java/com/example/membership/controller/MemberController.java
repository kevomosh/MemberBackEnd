package com.example.membership.controller;

import com.example.membership.inputDTO.MemberInp;
import com.example.membership.models.Member;
import com.example.membership.services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/member")
@CrossOrigin
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping()
    public ResponseEntity<List<Member>> getAllMembers(){
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{idStr}")
    public ResponseEntity<Member> getMemberById(@PathVariable String idStr){
        return ResponseEntity.ok(memberService.getMemberById(idStr));
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody MemberInp memberInp) {
      memberService.create(memberInp);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody MemberInp memberInp) {
        memberService.update(memberInp);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idStr}")
    public ResponseEntity<Void> delete(@PathVariable String idStr) {
        memberService.delete(idStr);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
