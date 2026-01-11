CREATE TABLE outbox_events (
    id BIGSERIAL NOT NULL,
    book_id BIGINT NOT NULL,
    event_data TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    published BOOLEAN DEFAULT FALSE,
    CONSTRAINT outbox_events_pk PRIMARY KEY (id)
);