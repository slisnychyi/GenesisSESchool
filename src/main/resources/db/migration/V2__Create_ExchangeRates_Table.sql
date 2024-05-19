CREATE TABLE exchange_rates
(
    id           SERIAL PRIMARY KEY,
    date         DATE         NOT NULL,
    buying_rate  VARCHAR(255) NOT NULL,
    selling_rate VARCHAR(255) NOT NULL
);
