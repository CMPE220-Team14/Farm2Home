package com.android.cmpe220.farm2home.demo.consumer_activities;

/**
 * Created by pooja.prabhuswamy on 4/29/16.
 */
public class ConsumerConfig {

    //URL to our consumer_login.php file
    public static final String LOGIN_URL = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/user_login.php";

    //Keys for email and password as defined in our $_POST['key'] in consumer_login.php
    public static final String KEY_EMAIL = "username";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means consumer_login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "username";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
