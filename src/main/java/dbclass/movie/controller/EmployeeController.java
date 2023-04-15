package dbclass.movie.controller;

import dbclass.movie.domain.Member;
import dbclass.movie.repository.MemberRepository;
import dbclass.movie.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class EmployeeController {

    private final MemberService memberService;

    @GetMapping("/member")
    public List<Member> getList() {
        return memberService.getList();
    }

    @PostMapping("/save")
    public void save() {
        memberService.save();
    }
}
