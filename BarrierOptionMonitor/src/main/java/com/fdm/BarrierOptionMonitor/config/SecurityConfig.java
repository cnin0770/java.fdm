package com.fdm.BarrierOptionMonitor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.fdm.BarrierOptionMonitor.model.Staff;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${custom.security.enabled:true}")
    private boolean securityEnabled;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        Staff user = new Staff();
        user.setName("user");
        user.setPassword("user");
        Staff admin = new Staff();
        admin.setName("admin");
        admin.setPassword("admin");
        auth
                .inMemoryAuthentication()
                .withUser(user.getName()).password("{noop}" + user.getPassword()).roles("USER").and()
                .withUser(admin.getName()).password("{noop}" + admin.getPassword()).roles("ADMIN").and()
                .withUser("user2").password("{noop}user2").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) {
        if (!securityEnabled)
            web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }
}
