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

    public Course(CourseBuilder builder) {
        courseId = builder.courseId;
        name = builder.name;
        ownerId = builder.ownerId;
        description = builder.description;
    }

    public static class CourseBuilder {
        private long courseId;

        private String name;

        private long ownerId;

        private String description;

        public CourseBuilder setCourseId(long courseId) {
            this.courseId = courseId;
            return this;
        }

        public CourseBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CourseBuilder setOwnerId(long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public CourseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
