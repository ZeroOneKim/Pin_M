package byk.pinM.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //TODO csrf 방어 추가
                .mvcMatchers("/", "/signUp","/signUpEmailChk","/signUp_Process", "/signIn",
                        "/imgs/*", "/ajax/**","/login-link", "/asd").permitAll()
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    //.loginPage("/signIn")
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessUrl("/");

    }

    //static 폴더 하위는 모두 접근가능 ex) css/ js ....
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //TODO 관리자 권한 및 암호화(Bcrypt 외 등등 뭐쓸지 고민.) SHA-256..
}
