ALTER TABLE payment
    ADD ip VARCHAR(255) DEFAULT '-1.-1.-1.-1';

ALTER TABLE payment
    ALTER COLUMN ip SET NOT NULL;