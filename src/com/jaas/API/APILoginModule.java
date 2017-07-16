package com.jaas.API;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * Created by viv on 7/16/2017.
 */

public class APILoginModule implements LoginModule {
    public static final String USER = "vivsoft@gmail.com";
    public static final String DIGITALID = "673487236873";

    private CallbackHandler callbackhandler = null;
    private boolean authenticationflag = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackhandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] ca = new Callback[1];
        ca[0] = new NameCallback("API Username:");
        try {
            callbackhandler.handle(ca);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String name = ((NameCallback) ca[0]).getName();

        //*********************************************************************************
        //API Implementation starts here
        //
        //1. Let's find the API username and convert that into the API DigitalID
        //2. If the username is not found, fail, and continue to normal site login.
        //3. If the username is found, convert to DigitalID and call API for authentication.
        //
        //*********************************************************************************
        if (USER.equals(name)) {
            //API username found. Converting to DigitalID.
            String digitalID = DIGITALID;

            //Pretend call to API REST API
            System.out.println("+-[API]--------------------------------------------------------+");
            System.out.println("| Calling API REST:  --> https://idnetwork.fisglobal.com/  <-- |");
            System.out.println("+-----------------------------------------------------------------+");
            long IDSCORE = 800;

            //Make final decision .. Pass = done, Fail = proceed to site login.
            if (IDSCORE > 700) {
                //API Score was high enough.
                System.out.println("API Authentication successful.");
                authenticationflag = true;
            } else {
                //API Score was too low.
                System.err.println("API Authentication failed, continuing to site login.");
                authenticationflag = false;
            }
        } else {
            //API username not found.
            System.err.println("API Authentication failed, continuing to site login.");
            authenticationflag = false;
        }
        return authenticationflag;
    }

    @Override
    public boolean commit() throws LoginException {
        return authenticationflag;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}