package com.example.model2.dto;

import java.io.Serializable;

//public domain software
public class PdsDto implements Serializable {
    private int seq;

    public int getSeq() {
        return seq;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFilename() {
        return filename;
    }

    public String getNewfilename() {
        return newfilename;
    }

    public int getReadcount() {
        return readcount;
    }

    public int getDowncount() {
        return downcount;
    }

    public String getRegdate() {
        return regdate;
    }

    private String id;
    private String title;
    private String content;
    private String filename;     // abc.txt
    private String newfilename;  // 21324124.txt
    private int readcount;
    private int downcount;
    private String regdate;


    @Override
    public String toString() {
        return "PdsDto{" +
                "seq=" + seq +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", filename='" + filename + '\'' +
                ", newfilename='" + newfilename + '\'' +
                ", readcount=" + readcount +
                ", downcount=" + downcount +
                ", regdate='" + regdate + '\'' +
                '}';
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setNewfilename(String newfilename) {
        this.newfilename = newfilename;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public void setDowncount(int downcount) {
        this.downcount = downcount;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public PdsDto(String id, String title, String content, String filename, String newfilename) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.filename = filename;
        this.newfilename = newfilename;
    }

    public PdsDto(int seq, String id, String title, String content, String filename, String newfilename, int readcount, int downcount, String regdate) {
        this.seq = seq;
        this.id = id;
        this.title = title;
        this.content = content;
        this.filename = filename;
        this.newfilename = newfilename;
        this.readcount = readcount;
        this.downcount = downcount;
        this.regdate = regdate;
    }
}
