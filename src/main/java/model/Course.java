package model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Alexeev on 29.09.2016.
 */

/**
 * Course Bean with Builder pattern
 */
public class Course {

    private long courseId;

    private String name;

    private User owner;

    private String description;

    private Timestamp dateCreated;

    public long getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public boolean isOwner(User user) {
        return owner.equals(user);
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    public Course(Builder builder) {
        courseId = builder.courseId;
        name = builder.name;
        owner = builder.owner;
        description = builder.description;
        dateCreated = builder.dateCreated;
    }

    public static class Builder {
        private long courseId;

        private String name;

        private User owner;

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

        public Builder setOwner(User owner) {
            this.owner = owner;
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
            if (name == null || description == null) {
                throw new IllegalArgumentException("Course required parameters are empty");
            }
            return new Course(this);
        }
    }
}
