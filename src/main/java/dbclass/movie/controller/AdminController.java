package dbclass.movie.controller;

import dbclass.movie.dto.user.AdminInfoDTO;
import dbclass.movie.dto.user.LoginDTO;
import dbclass.movie.security.JwtToken;
import dbclass.movie.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin")
@Tag(name = "직원 데이터 처리 관련", description = "로그인, 회원가입...")
public class AdminController {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> adminSignup(@ModelAttribute AdminInfoDTO signupDTO) {
        log.info("admin signup request: " + signupDTO);
        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        adminService.signup(signupDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/signin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> adminSignIn(@ModelAttribute LoginDTO loginDTO) {
        log.debug("admin signIn request: " + loginDTO);
        JwtToken token = adminService.signIn(loginDTO);

        ResponseCookie responseCookie = ResponseCookie
                .from("refresh_token", token.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .maxAge(token.getDuration())
                .path("/")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", responseCookie.toString());

        return new ResponseEntity<>(token.getAccessToken(), headers, HttpStatus.OK);
    }
}
