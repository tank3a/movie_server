package dbclass.movie.service;

import dbclass.movie.domain.Member;
import dbclass.movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save() {
        Member member = Member.builder()
                .name("종원")
                .build();

        log.info("save arrived");

        memberRepository.save(member);
    }

    public List<Member> getList() {
        return memberRepository.findAll();
    }
}
