package com.example.plandroid.plandroid;

/**
 * Created by Adeoluwa on 4/20/2015.
 */
public class User {
    public User() {

    }
    public User(String n) {
        setUsername(n);
    }
    public User(String n, String f, String l) {
        setUsername(n);
        setFirstname(f);
        setLastname(l);
    }

    //get functions
    public String getUsername(){ return username;}
    public String getPassword() { return password;}
    public String getFirstname(){ return firstname; }
    public String getLastname(){ return lastname; }
    public String getPhonenumber() { return phonenumber; }

    //set functions
    public void setUsername(String n) {
        username = n;
    }
    public void setPassword(String n) { password = n; }
    public void setFirstname(String n) {
        firstname = n;
    }
    public void setLastname(String n) {
        lastname = n;
    }
    public void setPhonenumber(String n) {phonenumber = n;}

    //private String u_id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phonenumber;
}
