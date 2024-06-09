package com.shahrinsiddeka.heamoslimdampatya.MainAdapter;

public class ChapterModel {
    private String title;
    private String fileName;

    public ChapterModel(String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public String getFileName() {
        return fileName;
    }
}
