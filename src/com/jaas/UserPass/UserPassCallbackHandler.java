package com.jaas.UserPass;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserPassCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        int counter = 0;
        NameCallback nc = null;
        PasswordCallback pwc = null;

        while (counter < callbacks.length) {
            if (callbacks[counter] instanceof NameCallback) {
                nc = ((NameCallback) callbacks[counter++]);
                System.out.println(nc.getPrompt());
                nc.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            } else if (callbacks[counter] instanceof PasswordCallback) {
                pwc = (PasswordCallback) callbacks[counter++];
                System.out.println(pwc.getPrompt());
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                pwc.setPassword(br.readLine().toCharArray());
            }
        }
    }
}
