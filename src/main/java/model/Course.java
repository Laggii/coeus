package model;

import java.sql.Timestamp;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class Course {

    private long courseId;

    private String name;

    private long ownerId;

    private String description;

    private final Timestamp dateCreated;

    public long getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course(Builder builder) {
        courseId = builder.courseId;
        name = builder.name;
        ownerId = builder.ownerId;
        description = builder.description;
        dateCreated = builder.dateCreated;
    }

    public static class Builder {
        private long courseId;

        private String name;

        private long ownerId;

        private String description;

        private Timestamp dateCreated;

        public Builder setCourseId(long courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setOwnerId(long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDateCreated(Timestamp dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Course build() {
            if(name == null || description == null) {
                throw new IllegalArgumentException("Course required parameters are empty");
            }
            return new Course(this);
        }
    }
}
