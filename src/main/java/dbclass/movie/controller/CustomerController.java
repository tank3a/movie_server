package dbclass.movie.controller;

import dbclass.movie.dto.CustomerInfoToClientDTO;
import dbclass.movie.dto.CustomerLoginDTO;
import dbclass.movie.dto.CustomerInfoDTO;
import dbclass.movie.security.JwtToken;
import dbclass.movie.security.SecurityUtil;
import dbclass.movie.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/customer")
@Tag(name = "회원 정보 관련 로직", description = "로그인, 회원가입, 회원정보수정")
public class CustomerController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> customerSignup(@ModelAttribute CustomerInfoDTO signupDTO) {
        log.debug("signup request: " + signupDTO);
        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        customerService.signup(signupDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> customerSignIn(@ModelAttribute CustomerLoginDTO loginDTO) {
        log.debug("signin request: " + loginDTO);
        JwtToken token = customerService.signIn(loginDTO);

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

    @GetMapping("/getCustomerData")
    public CustomerInfoToClientDTO getCustomerData() {
        log.debug("get customer data");
        String loginId = SecurityUtil.getCurrentUsername();
        return customerService.getData(loginId);
    }

    @PostMapping("/modify")
    public ResponseEntity<String> customerModify(@ModelAttribute CustomerInfoDTO modifyDTO) {
        log.debug("modify customer data: " + modifyDTO);

        if(!(modifyDTO.getPassword() == null || modifyDTO.getPassword() == "")) {
            modifyDTO.setPassword(passwordEncoder.encode(modifyDTO.getPassword()));
        }

        customerService.updateCustomerData(modifyDTO, SecurityUtil.getCurrentUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestBody String password) {
        log.info(password);
        String loginId = SecurityUtil.getCurrentUsername();

        customerService.deleteCustomer(loginId, password, passwordEncoder);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
