package model;

import java.sql.Timestamp;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class Message {

    private long messageId;
    private long idFrom;
    private long idTo;

    private String body;

    private Timestamp time;

    public long getMessageId() {
        return messageId;
    }

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

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public void setIdFrom(long idFrom) {
        this.idFrom = idFrom;
    }

    public void setIdTo(long idTo) {
        this.idTo = idTo;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Message(Builder builder) {
        messageId = builder.messageId;
        idFrom = builder.idFrom;
        idTo = builder.idTo;
        body = builder.body;
        time = builder.time;
    }

    public static class Builder {
        private long messageId;
        private long idFrom;
        private long idTo;

        private String body;

        private Timestamp time;

        public Builder setMessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

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
            if (body == null || time == null ) {
                throw new IllegalArgumentException("Message required parameters are empty");
            }
            return new Message(this);
        }
    }

}
