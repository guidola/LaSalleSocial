package io.terno.lasallesocial.model;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Guillermo on 11/5/17.
 */



public class SocialUser extends RealmObject {

    @Required
    private String  name;
    @Required
    private String  last_name;
    @Required
    private String  gender;
    @Required
    private Date    birthdate;
    @Required
    private String  studies;

    private RealmList<SocialUser> friends;

    public SocialUser(String name, String last_name, String gender, Date birthdate, String studies, RealmList<SocialUser> friends) {
        this.name = name;
        this.last_name = last_name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.studies = studies;
        this.friends = friends;
    }

    public SocialUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public RealmList<SocialUser> getFriends() {
        return friends;
    }

    public void setFriends(RealmList<SocialUser> friends) {
        this.friends = friends;
    }

    public void addFriend(SocialUser friend) {
        this.friends.add(friend);
    }

    public void unfriend(SocialUser friend) {
        this.friends.remove(friend);
    }

    public boolean isFriendOf(SocialUser friend) {
        return this.friends.contains(friend);
    }

}
