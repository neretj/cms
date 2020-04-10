package com.mojdan.app;

/*@Configuration
@EnableWebSecurity
@EnableAutoConfiguration*/
public class SecurityAutoConfiguration /*extends WebSecurityConfigurerAdapter*/ {
	
	/*@Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }*/
    
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("index.html", "/", "/home", "/api/*").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/api/auth/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}*/
}
