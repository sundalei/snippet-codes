package demo;

import java.time.ZonedDateTime;

public class FileInfo {

    private int id;

    private String name;

    private ZonedDateTime lastModified;

    private ZonedDateTime createdAt;

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

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {

        return "FileInfo{" +
                "name='" + name + '\'' +
                ", lastModified=" + lastModified +
                ", createdAt=" + createdAt +
                '}';
    }
}
