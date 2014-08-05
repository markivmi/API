package com.rms.auth;

import io.dropwizard.auth.Authenticator;
import org.eclipse.jetty.server.Authentication;

/**
 * Created by Vinay on 8/4/2014.
 */
public interface RmsAuth<T> extends Authenticator<T, Authentication.User> {
}
