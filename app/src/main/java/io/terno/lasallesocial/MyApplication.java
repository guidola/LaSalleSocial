package io.terno.lasallesocial;

import android.app.Application;
import android.content.SharedPreferences;

import java.security.interfaces.RSAKey;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.terno.lasallesocial.model.AppUser;
import io.terno.lasallesocial.model.Genders;
import io.terno.lasallesocial.model.SocialUser;

/**
 * Created by Guillermo on 11/5/17.
 */

public class MyApplication extends Application {

    private static final byte[] ENCRIPTION_KEY      = "thisisawonderfulencryptionkey".getBytes();
    private static final String REALM_NAME          = "lasallesocial.core";
    private static final String SP_NAME             = "io.terno.lasallesocial";
    private static final String SP_FIRSTRUN_FLAG    = "first_run";

    private Realm realm;

    private AppUser active_user;

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(this);

        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .encryptionKey(ENCRIPTION_KEY)
                .schemaVersion(42)
                .build();

        // Use the config
        this.realm = Realm.getInstance(config);

        this.initializeApplication();

    }

    private void initializeApplication() {

        SharedPreferences prefs = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        if (prefs.getBoolean(SP_FIRSTRUN_FLAG, true)) {
            // Do first run stuff here then set 'firstrun' as false
            Calendar c = Calendar.getInstance();
            c.set(1960, 2, 12);

            SocialUser social_user = new SocialUser(
                    "Patata",
                    "Brava",
                    Genders.GENDER_UNDET,
                    c.getTime(),
                    "I have no studies (:",
                    new RealmList<SocialUser>()
            );
            AppUser user = new AppUser(
                    "admin@salleurl.edu",
                    "qwerty",
                    social_user
            );

            realm.beginTransaction();
            realm.copyToRealm(user);
            realm.commitTransaction();

            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).apply();

        }

        active_user = null;

    }

    public boolean login(String uuid, String password) {

        RealmQuery<AppUser> q = realm.where(AppUser.class);

        active_user = q.equalTo("email", uuid).equalTo("password", password).findFirst();
        return active_user != null;
    }

    public void addSocialUser(SocialUser user) {

        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    public AppUser getActiveUser() {
        return active_user;
    }

    public RealmResults<SocialUser> getAllSocialUsers(){

        RealmQuery<SocialUser> q = realm.where(SocialUser.class);
        return q.findAll();

    }

    public void logout() {
        //set active user to null so logi
        active_user = null;
    }


    //TODO maybe we can handle keep logged in


}
