package com.realprojecy.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.realprojecy.controller.SessionConstants;
import com.realprojecy.controller.Bean.LoginBean;
import com.realprojecy.dto.C_jusorok;

import com.realprojecy.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    String loginForm(@ModelAttribute LoginBean loginBean){
        return "login/loginForm";
    }

    @PostMapping("/login")
    String login(@Validated @ModelAttribute LoginBean loginBean, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return loginForm(loginBean);
        }
        //입력을 아예안했을때 에러메시지
       C_jusorok loginMember = loginService.login(loginBean.getEmail(), loginBean.getPassword());
        // logger.info(loginMember.toString());

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디또는 비밀번호가 맞지 않습니다.");
            return loginForm(loginBean);
        }
        //로그인에 성공한다. 세션을 저장해서 로그아웃할대까지 계속 유지해서 로그인 중인지 알 수 있음
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);
        //여기 login에는 아이디랑 비밀번호가 들어있음
        return "redirect:" + redirectURL;


    }   

    @GetMapping("/logout")
    String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session !=null){
            session.invalidate();
        }

        return "redirect:/";
    }
    
}
