package com.dev.sisgestionMercados.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dev.sisgestionMercados.service.AuthUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
    private JwtFilterRequest jwtFilterRequest;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
	   return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authUserService).passwordEncoder(encoder);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("configure(HttpSecurity http) throws Exception");
    	http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
        		.antMatchers("/**/allProducts").permitAll()
        		.antMatchers("/**/productSearch").permitAll()
        		.antMatchers("/**/createFinalUser").permitAll()
        		.antMatchers("/**/createOrder/*").permitAll()
        		.antMatchers("/**/warehouseSearch").permitAll()
        		.antMatchers("/**/warehouseSearch/*").permitAll()
        		.antMatchers("/**/productSearch/*").permitAll()
        		.antMatchers("/**/allCategories/*").permitAll()
        		.antMatchers("/**/allSector").permitAll()
        		.antMatchers("/**/codeVerification").permitAll()  
        		.antMatchers("/**/loginUserFinal").permitAll() 
        		.antMatchers("/**/searchOfAllWarehouses").permitAll() 
        		.antMatchers("/**/allWarehouse").permitAll()
        		.antMatchers("/**/loginAccount/*").permitAll()
        		.antMatchers("/**/uniqueEmail/*").permitAll()
        		.antMatchers("/**/uniqueTelephoneAll/*").permitAll() 
        		.antMatchers("/**/uniqueEmailAll/*").permitAll()
        		.antMatchers("/**/recoverByPhone/*").permitAll()
        		.antMatchers("/**/changePassword").permitAll()
        		.antMatchers("/**/getGoogleMapsKey").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
}
