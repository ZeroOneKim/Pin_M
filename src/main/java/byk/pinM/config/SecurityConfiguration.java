package byk.pinM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .mvcMatchers("/", "/imgs/*", "/signUp","/signUpEmailChk","/signUp_Process", "/signUpEmailChkWithNum","/signIn-process")
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/signIn").permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout").permitAll()
                    .logoutSuccessUrl("/");

        http.sessionManagement().maximumSessions(1)
                .maxSessionsPreventsLogin(true);

    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT user_id, password, 1 FROM sy_user_mt WHERE user_id = ?")
                .authoritiesByUsernameQuery("SELECT A.user_id, B.role_nm FROM sy_user_mt A, sy_user_grp_mp B " +
                                            " WHERE user_id = ? AND A.role_id = B.role_id");
    }

    //static 폴더 하위는 모두 접근가능 ex) css/ js ....
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
