package diaryexample.mydiary.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PasswordEncoderTest {
    private static String password = "스프링Spring";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void 패스워드_암호화() throws Exception {
        // given

        // when
        String encodePassword = passwordEncoder.encode(password);

        // then
        assertThat(encodePassword).startsWith("{");
        assertThat(encodePassword).contains("{bcrypt}");
        assertThat(encodePassword).isNotEqualTo(password);
    }

    @Test
    public void 패스워드_랜덤_암호화() throws Exception {
        // given

        // when
        String encodePassword1 = passwordEncoder.encode(password);
        String encodePassword2 = passwordEncoder.encode(password);

        // then
        assertThat(encodePassword1).isNotEqualTo(encodePassword2);
    }

    @Test
    public void 암호화된_비밀번호_매치() throws Exception {
        // when
        String encodePassword = passwordEncoder.encode(password);

        // then
        assertThat(passwordEncoder.matches(password, encodePassword)).isTrue();
    }
}
