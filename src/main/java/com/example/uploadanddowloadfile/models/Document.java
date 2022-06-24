package com.example.uploadanddowloadfile.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 512)
    private String name;

    private long size;

    @Column(name = "upload_time")
    private Date uploadTime;

    @Lob
    private byte[] content;

    public Document() {
    }

    public Document(String name, long size, Date uploadTime, byte[] content) {
        this.name = name;
        this.size = size;
        this.uploadTime = uploadTime;
        this.content = content;
    }

    public Document(Long id, String name, long size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
