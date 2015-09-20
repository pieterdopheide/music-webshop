package nl.hiephiepmuziek.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import nl.hiephiepmuziek.filters.CsrfHeaderFilter;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
				.and()
        	.authorizeRequests()
        		.antMatchers("/", "/index.html", "/bower_components/**", "/views/**","/app.js", "/app.css", "/navigation.js", "/css/**", "/img/**", "/rest/**", "/user").permitAll()
        		.anyRequest().authenticated()
        		.and()
//        	.formLogin()
//        		.loginPage("/views/login/login.html")
//        		.permitAll()
//        		.and()
        	.logout()
//        		.permitAll() // Login tutorial
        		.and()
        	.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
        		.csrf()
        		.csrfTokenRepository(csrfTokenRepository());
    }

	 @Override
	 protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//		 authManagerBuilder.inMemoryAuthentication()
//		 	.withUser("admin").password("admin").roles("ADMIN", "USER");
		 
		 authManagerBuilder.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(
					"select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery(
					"select username, role from user_roles where username=?").rolePrefix("ROLE_");
	 }
    
    // Login tutorial
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
