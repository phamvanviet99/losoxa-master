package com.phamvanviet.losoxa.config.security;

import com.phamvanviet.losoxa.constant.RoleConstant;
import com.phamvanviet.losoxa.security.CustomSuccessHandler;
import com.phamvanviet.losoxa.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return customSuccessHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/home", "/index", "/login","/register","/sendSimpleEmail").permitAll()

                .antMatchers("/admin/home").hasAnyAuthority(RoleConstant.HOME)
                .antMatchers("/admin/user").hasAnyAuthority(RoleConstant.VIEW_USER)
                .antMatchers("/admin/user/create").hasAnyAuthority(RoleConstant.CREATE_USER)
                .antMatchers("/admin/user/update/**").hasAnyAuthority(RoleConstant.UPDATE_USER)
                .antMatchers("/admin/user/delete/**").hasAnyAuthority(RoleConstant.DELETE_USER)
                .antMatchers("/admin/product").hasAnyAuthority(RoleConstant.VIEW_PRODUCT)
                .antMatchers("/admin/product/create").hasAnyAuthority(RoleConstant.CREATE_PRODUCT)
                .antMatchers("/admin/product/update/**").hasAnyAuthority(RoleConstant.UPDATE_PRODUCT)
                .antMatchers("/admin/product/delete/**").hasAnyAuthority(RoleConstant.DELETE_PRODUCT)
                .antMatchers("/admin/category").hasAnyAuthority(RoleConstant.VIEW_CATEGORY)
                .antMatchers("/admin/category/create").hasAnyAuthority(RoleConstant.CREATE_CATEGORY)
                .antMatchers("/admin/category/update/**").hasAnyAuthority(RoleConstant.UPDATE_CATEGORY)
                .antMatchers("/admin/category/delete/**").hasAnyAuthority(RoleConstant.DELETE_CATEGORY)
                .antMatchers("/admin/order").hasAnyAuthority(RoleConstant.VIEW_ORDER)
                .antMatchers("/admin/order/update/**").hasAnyAuthority(RoleConstant.UPDATE_ORDER)
                .antMatchers("/admin/role").hasAnyAuthority(RoleConstant.VIEW_ROLE)
                .antMatchers("/admin/role/create").hasAnyAuthority(RoleConstant.CREATE_ROLE)
                .antMatchers("/admin/role/update/**").hasAnyAuthority(RoleConstant.UPDATE_ROLE)
                .antMatchers("/admin/role/delete/**").hasAnyAuthority(RoleConstant.DELETE_ROLE)
                .antMatchers("/admin/permission").hasAnyAuthority(RoleConstant.VIEW_PERMISSION)
                .antMatchers("/admin/permission/create").hasAnyAuthority(RoleConstant.CREATE_PERMISSION)
                .antMatchers("/admin/permission/update/**").hasAnyAuthority(RoleConstant.UPDATE_PERMISSION)
                .antMatchers("/admin/permission/delete/**").hasAnyAuthority(RoleConstant.DELETE_PERMISSION)
                .antMatchers("/admin/blog").hasAnyAuthority(RoleConstant.VIEW_BLOG)
                .antMatchers("/admin/blog/create").hasAnyAuthority(RoleConstant.CREATE_BLOG)
                .antMatchers("/admin/blog/update/**").hasAnyAuthority(RoleConstant.UPDATE_BLOG)
                .antMatchers("/admin/blog/delete/**").hasAnyAuthority(RoleConstant.DELETE_BLOG)
                .antMatchers("/admin/report/list", "/admin/report/loyal-customer").hasAnyAuthority(RoleConstant.REPORT_LOYAL_CUSTOMER)
                .antMatchers("/admin/report/list", "/admin/report/revenue").hasAnyAuthority(RoleConstant.REPORT_REVENUE)
                .antMatchers("/cart", "/checkout","/checkout-message","/profile","/order-history","/order/**","/favourite","/pay-view").authenticated()
                .and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(customSuccessHandler)
                .failureUrl("/login?incorrectAccount").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true);
//                .and().rememberMe().tokenValiditySeconds(1 * 24 * 60 * 60).key("mySecret");
        // Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/images/**","/ckeditor/**","/ckfinder/**","/media/**");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memoryTokenRepository = new InMemoryTokenRepositoryImpl();
        return memoryTokenRepository;
    }
}
