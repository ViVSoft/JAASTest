package com.jaas;

import com.jaas.UserPass.UserPassCallbackHandler;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "JaasConfig.config");
        LoginContext lc = null;
        try {
             lc = new LoginContext("JaasSecurity", new UserPassCallbackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(true){
            try {
                System.out.println("       _                    _____   _______        _   \n" +
                        "      | |  /\\        /\\    / ____| |__   __|      | |  \n" +
                        "      | | /  \\      /  \\  | (___      | | ___  ___| |_ \n" +
                        "  _   | |/ /\\ \\    / /\\ \\  \\___ \\     | |/ _ \\/ __| __|\n" +
                        " | |__| / ____ \\  / ____ \\ ____) |    | |  __/\\__ \\ |_ \n" +
                        "  \\____/_/    \\_\\/_/    \\_\\_____/     |_|\\___||___/\\__|\n" +
                        "                                                       \n" +
                        "                                                       ");
                lc.login();
                System.exit(0);
            } catch (LoginException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
