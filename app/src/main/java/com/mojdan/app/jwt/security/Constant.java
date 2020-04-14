package com.mojdan.app.jwt.security;

public class Constant {

		// Spring Security
		public static final String LOGIN_URL = "/login";
		public static final String HOME_URL = "/home";
		public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
		public static final String TOKEN_BEARER_PREFIX = "Bearer ";

		// JWT
		public static final String ISSUER_INFO = "mojdan";
		public static final String SUPER_SECRET_KEY = "20m0j4?20";
		public static final long EXPIRATION = 18000; // gExpirationTimeInSeconds 5 Horas 
}
