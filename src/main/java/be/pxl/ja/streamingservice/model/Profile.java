package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Profile {
    private String name;
    private LocalDate dateOfBirth;
    private String avatar;
    private List<Content> recentlyWatched = new ArrayList<>();
    private List<Content> currentlyWatching = new ArrayList<>();
    private List<Content> myList = new ArrayList<>();



    public Profile(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public Profile(String name) {
        this.name = name;
        this.avatar = "profile1";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if(dateOfBirth.isAfter(LocalDate.now())) {
            throw new InvalidDateException(dateOfBirth, "dateOfBirth","Geboortedatum kan niet in de toekomst liggen");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
    }

    public void startWatching(Content content) {
        if(currentlyWatching.contains(content)) {
            currentlyWatching.remove(content);
        }

        currentlyWatching.add(0, content);
    }

    public void finishWatching(Content content) {
        if(currentlyWatching.contains(content)) {
            currentlyWatching.remove(content);
        }

        recentlyWatched.add(0, content);
    }

    public void addToMyList(Content content) {
        if(!myList.contains(content)) {
            myList.add(0, content);
        }
    }

    public void removeFromMyList(Content content) {
        if(myList.contains(content)) {
            myList.remove(content);
        }
    }

    public boolean allowedToWatch(Content content) {
        return content.getMaturityRating().getMinimumAge() <= getAge();
    }

    public boolean isInMyList(Content content) {
        return myList.contains(content);
    }

    public List<Content> getMyList() {
        return myList;
    }

    public Collection<Content> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public Collection<Content> getRecentlyWatched() {
        return recentlyWatched;
    }
}
