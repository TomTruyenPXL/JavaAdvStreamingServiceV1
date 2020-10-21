package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account {
    private String email;
    private String password;
    private StreamingPlan streamingPlan;
    private List<Profile> profiles = new ArrayList<>();
    private PaymentInfo paymentInfo;

    public Account(String email, String password) {
        if(email == null || email.equals("")) {
            throw new IllegalArgumentException("Email kan niet leeg zijn");
        }

        if(password == null || password.equals("")) {
            throw new IllegalArgumentException("Password kan niet leeg zijn");
        }

        this.email = email;
        setPassword(password);
        profiles.add(new Profile("Profile 1"));

        streamingPlan = StreamingPlan.BASIC;
    }

    public void setStreamingPlan(StreamingPlan streamingPlan) {
        this.streamingPlan = streamingPlan;
    }

    public int getNumberOfProfiles() {
        return profiles.size();
    }

    public List<Profile> getProfiles() {
        profiles.sort(Comparator.comparing(Profile::getName));

        return profiles;
    }

    public void addProfile(Profile profile) {
        if(profiles.size() == streamingPlan.getNumberOfScreens()) {
            throw new TooManyProfilesException("Maximaal toegelaten accounts bereikt");
        }

        profiles.add(profile);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean verifyPassword(String password) {
        return PasswordUtil.isValid(password, this.password);
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setPassword(String password) {
        int passwordStrength = PasswordUtil.calculateStrength(password);
        if(passwordStrength < 5) {
            throw new IllegalArgumentException("Wachtwoord is te zwak");
        }

        this.password = PasswordUtil.encodePassword(password);
    }

    public Profile getFirstProfile() {
        return profiles.get(0);
    }
}
