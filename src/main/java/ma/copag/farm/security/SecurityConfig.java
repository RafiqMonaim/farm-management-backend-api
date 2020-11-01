package ma.copag.farm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public BCryptPasswordEncoder getPasswordEncoder;//hachage de password

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        /*auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin123").roles("ADMIN","USER")
                .and()
                .withUser("user").password("{noop}user123").roles("USER");*/
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.formLogin();
        //http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//pour ne pas créer de sessions
        http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
        http.authorizeRequests().antMatchers("/villes/**","/fermes/**","/profile").authenticated();
        http.authorizeRequests().antMatchers("/registers/**","/secteurs/**","/parcelles/**").authenticated();
        http.authorizeRequests().antMatchers("/apportEaus/**","/testConformites/**").authenticated();
        //http.authorizeRequests().antMatchers("/registers/**").authenticated();

        //http.authorizeRequests().antMatchers(HttpMethod.GET,"/registers/**").hasAuthority("ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT,"/registers/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/fermes","/villes")
                .hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
