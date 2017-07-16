package com.jaas.API;


import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by viv on 7/16/2017.
 */

public class APICallbackHandler implements CallbackHandler {

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
            }
        }
    }
}
