package com.rms.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public interface PilotAuthenticator<T> extends Authenticator<T, Boolean> {
    @Override
    public Optional<Boolean> authenticate(T authenticator) throws AuthenticationException;
}
