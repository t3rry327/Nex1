package dev.t3rry327.hypix.api.nex1.objects;

public class Profile {
    String profileid;
    ProfilesEnum name;
    String owner;
    String [] coopmembers;
    
    public Profile(String profileid, ProfilesEnum name, String owner, String[] coopmembers) {
        this.profileid = profileid;
        this.name = name;
        this.owner = owner;
        this.coopmembers = coopmembers;
    }
    public String getProfileid() {
        return profileid;
    }
    public void setProfileid(String profileid) {
        this.profileid = profileid;
    }
    public ProfilesEnum getName() {
        return name;
    }
    public void setName(ProfilesEnum name) {
        this.name = name;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String[] getCoopmembers() {
        return coopmembers;
    }
    public void setCoopmembers(String[] coopmembers) {
        this.coopmembers = coopmembers;
    }
    
}
