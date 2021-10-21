CREATE TABLE pass (
    id SERIAL,
    pass_number VARCHAR UNIQUE,
    expired TIMESTAMP
);