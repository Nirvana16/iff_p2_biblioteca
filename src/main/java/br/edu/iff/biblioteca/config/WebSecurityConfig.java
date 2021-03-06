package br.edu.iff.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http 
            .authorizeRequests()
            	.antMatchers("/resources/**","/css/**","/fonts/**"
            	,"/images/**","/js/**","/index","/","/contato").permitAll()
            	.antMatchers("/livros/novo").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
        		.exceptionHandling().accessDeniedPage("/403")
            .and()
            .logout()
            	.logoutSuccessUrl("/")
                .permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("1").roles("USER")
             .and()
                .withUser("admin").password("1").roles("ADMIN");
    }
}
