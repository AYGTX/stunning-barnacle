package com.gtx.backend.securitymodule;

public class SecurityConstants {
    public static final long EXPIRATION_TIME= 432000000; //  5 days in milliseconds
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String TOKEN_SECRET = "FOwSjZ9COtK4s";
}
