package dbclass.movie.service;

import dbclass.movie.domain.user.Admin;
import dbclass.movie.domain.user.Role;
import dbclass.movie.domain.user.UserAuthority;
import dbclass.movie.dto.user.AdminInfoDTO;
import dbclass.movie.dto.user.LoginDTO;
import dbclass.movie.exceptionHandler.DataExistsException;
import dbclass.movie.repository.AdminRepository;
import dbclass.movie.repository.AuthorityRepository;
import dbclass.movie.security.JwtToken;
import dbclass.movie.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AdminRepository adminRepository;
    private final JwtTokenProvider tokenProvider;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public void signup(AdminInfoDTO signupDTO) {
        if(adminRepository.existsByLoginId(signupDTO.getLoginId())) {
            throw new DataExistsException("중복된 아이디입니다.", "Admin");
        }

        Admin admin = Admin.builder()
                .name(signupDTO.getName())
                .loginId(signupDTO.getLoginId())
                .password(signupDTO.getPassword())
                .build();

        admin = adminRepository.save(admin);

        UserAuthority authority = UserAuthority.builder()
                .loginId(admin.getLoginId())
                .password(admin.getPassword())
                .authority(Role.ROLE_ADMIN.getType())
                .admin(admin)
                .build();

        authorityRepository.save(authority);

    }

    @Transactional(readOnly = true)
    public JwtToken signIn(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = loginDTO.toAuthentication();
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.createToken(loginDTO.getLoginId(), Role.ROLE_ADMIN);
    }
}
