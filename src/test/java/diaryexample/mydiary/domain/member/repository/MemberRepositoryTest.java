package diaryexample.mydiary.domain.member.repository;

import diaryexample.mydiary.domain.member.Member;
import diaryexample.mydiary.domain.member.Role;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @AfterEach
    private void after() {
        em.clear();
    }

    @Test
    public void 회원저장_성공() throws Exception {
        // given
        Member member = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname").role(Role.USER).age(22).build();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다."));

        assertThat(findMember).isSameAs(saveMember);
        assertThat(findMember).isSameAs(member);
    }

    @Test
    public void 오류_회원가입시_아이디가_없음() throws Exception {
        // given
        Member member = Member.builder().password("1234567890").name("member1").nickname("nickname").role(Role.USER).age(22).build();

        // when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_이름이_없음() throws Exception {
        // given
        Member member = Member.builder().username("username").password("1234567890").nickname("nickname").role(Role.USER).age(22).build();

        // when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_닉네임이_없음() throws Exception {
        // given
        Member member = Member.builder().username("username").password("1234567890").name("member1").role(Role.USER).age(22).build();

        // when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_나이가_없음() throws Exception {
        // given
        Member member = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname").role(Role.USER).build();

        // when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_중복된_아이디가_있음() throws Exception {
        // given
        Member member1 = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname1").role(Role.USER).age(22).build();
        Member member2 = Member.builder().username("username").password("123456789").name("member2").nickname("nickname2").role(Role.USER).age(22).build();

        memberRepository.save(member1);

        // when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));
    }

    @Test
    public void 성공_회원수정() throws Exception {
        // given
        Member member = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname").role(Role.USER).age(22).build();
        memberRepository.save(member);

        String updatePassword = "updatePassword";
        String updateName = "updateName";
        String updateNickname = "updateNickname";
        Integer updateAge = 33;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(() -> new Exception());
        findMember.updateName(updateName);
        findMember.updatePassword(passwordEncoder, updatePassword);
        findMember.updateNickname(updateNickname);
        findMember.updateAge(updateAge);
        em.flush();

        // then
        Member findUpdateMember = memberRepository.findById(findMember.getId()).orElseThrow(() -> new Exception());

        assertThat(findUpdateMember).isSameAs(findMember);
        assertThat(passwordEncoder.matches(updatePassword, findUpdateMember.getPassword()));
        assertThat(findUpdateMember.getName()).isEqualTo(updateName);
        assertThat(findUpdateMember.getNickname()).isEqualTo(updateNickname);
        assertThat(findUpdateMember.getAge()).isEqualTo(updateAge);
    }

    @Test
    public void 성공_회원삭제() throws Exception {
        // given
        Member member = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname").role(Role.USER).age(22).build();
        memberRepository.save(member);

        // when
        memberRepository.delete(member);

        // then
        assertThrows(Exception.class, () -> memberRepository.findById(member.getId()).orElseThrow(() -> new Exception()));
    }

    @Test
    public void existByUsername_정상작동() throws Exception {
        // given
        String username = "username";
        Member member = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname").role(Role.USER).age(22).build();
        memberRepository.save(member);

        // when, then
        assertThat(memberRepository.existsByUsername(username)).isTrue();
        assertThat(memberRepository.existsByUsername(username + "123")).isFalse();
    }

    @Test
    public void findByUsername_정상작동() throws Exception {
        // given
        String username = "username";
        Member member = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname").role(Role.USER).age(22).build();
        memberRepository.save(member);

        // when, then
        assertThat(memberRepository.findByUsername(username).get().getUsername()).isEqualTo(member.getUsername());
        assertThat(memberRepository.findByUsername(username).get().getName()).isEqualTo(member.getName());
        assertThat(memberRepository.findByUsername(username).get().getId()).isEqualTo(member.getId());
        assertThrows(Exception.class,
                () -> memberRepository.findByUsername(username + "123")
                        .orElseThrow(() -> new Exception()));
    }

    @Test
    public void 회원가입시_생성시간_등록() throws Exception {
        // given
        Member member = Member.builder().username("username").password("1234567890").name("member1").nickname("nickname").role(Role.USER).age(22).build();
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(() -> new Exception());

        // then
        assertThat(findMember.getCreatedDate()).isNotNull();
        assertThat(findMember.getLastModifiedDate()).isNotNull();
    }

}