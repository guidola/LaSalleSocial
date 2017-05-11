package io.terno.lasallesocial.model;

import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Guillermo on 11/5/17.
 */

public class AppUser extends RealmObject {

    private static final int MINIMUM_PWD_LENGTH = 6;

    // els requeriments son tan absurdament reduits que fa que el model no tingui gaire sentit

    @Required
    private String email;
    @Required
    private String password;
    @Required
    private SocialUser associated_profile;

    public AppUser(String email, String password, SocialUser user) {
        this.email = email;
        this.password = password;
        this.associated_profile = user;
    }

    public AppUser() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SocialUser getAssociated_profile() {
        return associated_profile;
    }

    public void setAssociated_profile(SocialUser associated_profile) {
        this.associated_profile = associated_profile;
    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= MINIMUM_PWD_LENGTH;
    }

    public boolean validateCredentialsFormat(String uuid, String pwd) {
        return !TextUtils.isEmpty(uuid) &&
                TextUtils.isEmpty(pwd) &&
                isEmailValid(uuid) &&
                isPasswordValid(pwd);
    }



}
