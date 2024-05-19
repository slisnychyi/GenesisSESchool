CREATE TABLE subscribers
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    status     VARCHAR(20)  NOT NULL CHECK (status IN ('activated', 'deactivated')),
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
