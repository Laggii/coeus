package model;

import java.sql.Timestamp;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class Message {

    private final long idFrom;
    private final long idTo;

    private final String body;

    private final Timestamp time;

    public long getIdFrom() {
        return idFrom;
    }

    public long getIdTo() {
        return idTo;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getTime() {
        return time;
    }

    public Message(Builder builder) {
        idFrom = builder.idFrom;
        idTo = builder.idTo;
        body = builder.body;
        time = builder.time;
    }

    public static class Builder {
        private long idFrom;
        private long idTo;

        private String body;

        private Timestamp time;

        public Builder setIdFrom(long idFrom) {
            this.idFrom = idFrom;
            return this;
        }

        public Builder setIdTo(long idTo) {
            this.idTo = idTo;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setTime(Timestamp time) {
            this.time = time;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

}
