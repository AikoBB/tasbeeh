package com.aigerimbb.android.tasbeeh.model;

/**
 * Created by Beishenbekova on 05.11.2016.
 */

public class Tasbeeh {
    private int id;
    private String name;
    private String name_tr;
    private String content;
    private int max_count;

    public Tasbeeh(int id, String name, String name_tr, String content, int max_count) {
        this.id = id;
        this.name = name;
        this.name_tr = name_tr;
        this.content = content;
        this.max_count = max_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_tr() {
        return name_tr;
    }

    public void setName_tr(String name_tr) {
        this.name_tr = name_tr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMax_count() {
        return max_count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }
}
