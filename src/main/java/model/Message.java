package model;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class Message {

    private final long idFrom;
    private final long idTo;

    private final String body;

    public long getIdFrom() {
        return idFrom;
    }

    public long getIdTo() {
        return idTo;
    }

    public String getBody() {
        return body;
    }

    public Message(MessageBuilder builder) {
        idFrom = builder.idFrom;
        idTo = builder.idTo;
        body = builder.body;
    }

    public static class MessageBuilder {
        private long idFrom;
        private long idTo;

        private String body;

        public MessageBuilder setIdFrom(long idFrom) {
            this.idFrom = idFrom;
            return this;
        }

        public MessageBuilder setIdTo(long idTo) {
            this.idTo = idTo;
            return this;
        }

        public MessageBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

}
