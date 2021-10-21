INSERT INTO pass(pass_number, expired) VALUES ('a1', current_timestamp - interval '1' MONTH );
INSERT INTO pass(pass_number, expired) VALUES ('a2', current_timestamp - interval '2' MONTH);
INSERT INTO pass(pass_number, expired) VALUES ('a3', current_timestamp - interval '2' MONTH);
INSERT INTO pass(pass_number, expired) VALUES ('a4', current_timestamp - interval '3' MONTH);
INSERT INTO pass(pass_number, expired) VALUES ('a5', current_timestamp - interval '5' MONTH);
INSERT INTO pass(pass_number, expired) VALUES ('a6', current_timestamp - interval '6' MONTH);
INSERT INTO pass(pass_number, expired) VALUES ('a7', current_timestamp + interval '6' MONTH);