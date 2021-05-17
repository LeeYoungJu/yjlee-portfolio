package com.yjlee.portfolio.web;

import com.yjlee.portfolio.config.auth.dto.SessionUser;
import com.yjlee.portfolio.domain.users.User;
import com.yjlee.portfolio.service.users.UserService;
import com.yjlee.portfolio.web.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping("/login")
    public LoginUserDto checkSession() {
        //User u = User.builder().name("yjlee").email("aaa@bbb.com").picture("picture").build();
        //SessionUser temp = new SessionUser(u);
        //return temp;

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        LoginUserDto loginUserDto = null;
        if(sessionUser != null) {
            loginUserDto = LoginUserDto.builder()
                    .name(sessionUser.getName())
                    .email(sessionUser.getEmail())
                    .imageUrl(sessionUser.getPicture())
                    .build();
        }
        return loginUserDto;
    }

    @PostMapping("/login")
    public LoginUserDto SetSession(@RequestBody LoginUserDto loginUserDto) {
        userService.saveOrUpdate(loginUserDto);
        SessionUser sessionUser = new SessionUser(loginUserDto.toEntity());
        httpSession.setAttribute("user", sessionUser);
        return loginUserDto;
    }

}
