package model;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class Course {

    private final long courseId;

    private final String name;

    private final long ownerId;

    private final String description;

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

    public Course(Builder builder) {
        courseId = builder.courseId;
        name = builder.name;
        ownerId = builder.ownerId;
        description = builder.description;
    }

    public static class Builder {
        private long courseId;

        private String name;

        private long ownerId;

        private String description;

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

        public Course build() {
            return new Course(this);
        }
    }
}
