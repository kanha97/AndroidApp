package com.example.android.newsapi;

public class news {
    private String title;
    private String imageurl;
    private String description;

    public news(String title, String imageurl, String description) {
        this.title = title;
        this.imageurl = imageurl;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "news{" +
                "title='" + title + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
