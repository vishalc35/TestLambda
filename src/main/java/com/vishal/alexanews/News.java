package com.vishal.alexanews;

import lombok.Getter;
import lombok.Setter;

public class News {

    // TODO: see if you need author in future
//    @Getter @Setter
//    private String author;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String description;

    News() {
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
