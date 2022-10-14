package com.krealif.beritaku;

import java.util.Date;

public class News {
    private String id, title, category, body;
    private int ageRating;
    private Date published;

    public News(String id, String title, Date published, String category, String body, int ageRating) {
        this.id = id;
        this.title = title;
        this.published = published;
        this.category = category;
        this.body = body;
        this.ageRating = ageRating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }
}
