CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE crypto_chain
(
    id            BIGINT       NOT NULL,
    name          VARCHAR(255) NOT NULL,
    shortcut_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_crypto_chain PRIMARY KEY (id)
);

CREATE TABLE crypto_currency
(
    id            BIGINT       NOT NULL,
    name          VARCHAR(255) NOT NULL,
    shortcut_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_crypto_currency PRIMARY KEY (id)
);

CREATE TABLE crypto_currency_chain_join
(
    id                 BIGINT       NOT NULL,
    decimals           BIGINT       NOT NULL,
    contract_address   VARCHAR(255) NOT NULL,
    crypto_currency_id BIGINT       NOT NULL,
    crypto_chain_id    BIGINT       NOT NULL,
    CONSTRAINT pk_crypto_currency_chain_join PRIMARY KEY (id)
);

CREATE TABLE crypto_wallet
(
    id          BIGINT       NOT NULL,
    public_key  VARCHAR(255) NOT NULL,
    private_key VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_crypto_wallet PRIMARY KEY (id)
);

CREATE TABLE payment
(
    id                 BIGINT       NOT NULL,
    payment_status_id  BIGINT       NOT NULL,
    service_id         BIGINT       NOT NULL,
    email              VARCHAR(255) NOT NULL,
    crypto_wallet_id   BIGINT,
    crypto_chain_id    BIGINT       NOT NULL,
    crypto_currency_id BIGINT       NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

CREATE TABLE payment_status
(
    id                BIGINT NOT NULL,
    time              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status_value      VARCHAR(255),
    expire_in_minutes BIGINT,
    message           VARCHAR(255),
    CONSTRAINT pk_payment_status PRIMARY KEY (id)
);

CREATE TABLE service
(
    id                      BIGINT       NOT NULL,
    name                    VARCHAR(255),
    description             VARCHAR(255),
    cost                    DOUBLE PRECISION,
    status_value            VARCHAR(255) NOT NULL,
    duration_number_of_days INTEGER      NOT NULL,
    CONSTRAINT pk_service PRIMARY KEY (id)
);

CREATE TABLE service_crypto_currencies
(
    crypto_currencies_id BIGINT NOT NULL,
    service_id           BIGINT NOT NULL
);

ALTER TABLE crypto_chain
    ADD CONSTRAINT uc_crypto_chain_name UNIQUE (name);

ALTER TABLE crypto_chain
    ADD CONSTRAINT uc_crypto_chain_shortcut_name UNIQUE (shortcut_name);

ALTER TABLE crypto_currency
    ADD CONSTRAINT uc_crypto_currency_name UNIQUE (name);

ALTER TABLE crypto_currency
    ADD CONSTRAINT uc_crypto_currency_shortcut_name UNIQUE (shortcut_name);

ALTER TABLE crypto_wallet
    ADD CONSTRAINT uc_crypto_wallet_address UNIQUE (address);

ALTER TABLE crypto_wallet
    ADD CONSTRAINT uc_crypto_wallet_private_key UNIQUE (private_key);

ALTER TABLE crypto_wallet
    ADD CONSTRAINT uc_crypto_wallet_public_key UNIQUE (public_key);

ALTER TABLE crypto_currency_chain_join
    ADD CONSTRAINT FK_CRYPTO_CURRENCY_CHAIN_JOIN_ON_CRYPTO_CHAIN FOREIGN KEY (crypto_chain_id) REFERENCES crypto_chain (id);

ALTER TABLE crypto_currency_chain_join
    ADD CONSTRAINT FK_CRYPTO_CURRENCY_CHAIN_JOIN_ON_CRYPTO_CURRENCY FOREIGN KEY (crypto_currency_id) REFERENCES crypto_currency (id);

ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_CRYPTO_CHAIN FOREIGN KEY (crypto_chain_id) REFERENCES crypto_chain (id);

ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_CRYPTO_CURRENCY FOREIGN KEY (crypto_currency_id) REFERENCES crypto_currency (id);

ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_CRYPTO_WALLET FOREIGN KEY (crypto_wallet_id) REFERENCES crypto_wallet (id);

ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_PAYMENT_STATUS FOREIGN KEY (payment_status_id) REFERENCES payment_status (id);

ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service (id);

ALTER TABLE service_crypto_currencies
    ADD CONSTRAINT fk_sercrycur_on_crypto_currency FOREIGN KEY (crypto_currencies_id) REFERENCES crypto_currency (id);

ALTER TABLE service_crypto_currencies
    ADD CONSTRAINT fk_sercrycur_on_service FOREIGN KEY (service_id) REFERENCES service (id);

INSERT INTO crypto_chain (id, name, shortcut_name)
VALUES (1, 'Binance smart chain', 'bsc');

INSERT INTO crypto_chain (id, name, shortcut_name)
VALUES (2, 'Tron chain', 'trc');

INSERT INTO crypto_chain (id, name, shortcut_name)
VALUES (3, 'Ethereum', 'eth');

INSERT INTO crypto_currency (id, name, shortcut_name)
VALUES (101, 'USD Teather', 'usdt');

INSERT INTO crypto_currency (id, name, shortcut_name)
VALUES (102, 'USD Circle', 'usdc');

INSERT INTO crypto_currency_chain_join (id, decimals, contract_address, crypto_chain_id, crypto_currency_id)
VALUES (1001, 18, '0x55d398326f99059fF775485246999027B3197955', 1, 101);

INSERT INTO crypto_currency_chain_join (id, decimals, contract_address, crypto_chain_id, crypto_currency_id)
VALUES (1002, 18, '0x8AC76a51cc950d9822D68b83fE1Ad97B32Cd580d', 1, 102);

INSERT INTO crypto_currency_chain_join (id, decimals, contract_address, crypto_chain_id, crypto_currency_id)
VALUES (1003, 6, 'TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t', 2, 101);

INSERT INTO crypto_currency_chain_join (id, decimals, contract_address, crypto_chain_id, crypto_currency_id)
VALUES (1004, 6, 'TEkxiTehnzSmSe2XqrBj4w32RUN966rdz8', 2, 102);

INSERT INTO crypto_currency_chain_join (id, decimals, contract_address, crypto_chain_id, crypto_currency_id)
VALUES (1005, 6, '0xdAC17F958D2ee523a2206206994597C13D831ec7', 3, 101);

INSERT INTO crypto_currency_chain_join (id, decimals, contract_address, crypto_chain_id, crypto_currency_id)
VALUES (1006, 6, '0xA0b86991c6218b36c1d19D4a2e9Eb0cE3606eB48', 3, 102);

INSERT INTO service (id, duration_number_of_days, name, description, cost, status_value)
VALUES (
           10001,
           30,
           '30 days access',
           '30 days access to service',
           0.01,
           'Period'
       );

INSERT INTO service_crypto_currencies (crypto_currencies_id, service_id)
VALUES (101, 10001);

INSERT INTO service_crypto_currencies (crypto_currencies_id, service_id)
VALUES (102, 10001);