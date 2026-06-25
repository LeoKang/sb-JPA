package com.example.demo;

import com.example.demo.entity.Member;
import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class JpaApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var member1 = Member.builder()
                .name("윤서준")
                .email("SeojunYoon@hanbit.co.kr")
                .age(10).build();
        log.info("save 윤서준");
        memberRepository.save(member1);
        log.info("saved {}", member1);

        var member2 = Member.builder()
                .name("윤광철")
                .email("kwangcheolYoon@hanbit.co.kr")
                .age(43).build();
        log.info("save 윤광철");
        memberRepository.save(member2);
        log.info("saved {}", member2);

        log.info("read 윤서준");
        member1 = memberRepository.findById(member1.getId()).orElseThrow();

        log.info("update 윤서준");
        member1.setAge(11);
        memberRepository.save(member1);
        log.info("updated {}", member1);

        log.info("update 윤광철");
        memberRepository.save(member2);
        log.info("updated {}", member2);

//        List<Member> member = memberRepository.findAll();
//        var member = memberRepository.findAll();
//        log.info("Member : {}", member);

        var article = Article.builder()
                .title("방학 첫날이다.")
                .description("오늘은 열심히 방학 숙제를 했다.")
                .member(member1).build();
        articleRepository.save(article);

        var articles = articleRepository.findAll();
        for(Article article_list : articles) {
            log.info("{}", article_list);
        }

        log.info("애플리케이션 종료");
    }
}
