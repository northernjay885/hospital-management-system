INSERT INTO patient (id, firstname, lastname, inpatient) VALUES (1, 'Jack', 'Gabriel', true);

INSERT INTO patient (id, firstname, lastname, inpatient) VALUES (2, 'Martin', 'King', false);

INSERT INTO patient (id, firstname, lastname, inpatient) VALUES (3, 'Bob', 'Dylan', true);

INSERT INTO lab_report (id, patient_id, name, date) VALUES (1, 2, 'Typhus Fever', '2020-12-23 12:00:01');

INSERT INTO lab_report (id, patient_id, name, date) VALUES (2, 2, 'Toxicomania', '2019-11-29 13:00:01');

INSERT INTO lab_report (id, patient_id, name, date) VALUES (3, 2, 'Brucellosis', '2008-12-05 14:00:01');

INSERT INTO lab_report (id, patient_id, name, date) VALUES (4, 3, 'COVID-19', '2020-12-23 12:00:01');

INSERT INTO billing (id, name, patient_id, price, date) VALUES (1, 'Home service', 1, '1200.00', '2019-11-29 13:30:01');

INSERT INTO billing (id, name, patient_id, price, date) VALUES (2, 'Testing', 2, '5340.25', '2019-11-30 15:30:01');

INSERT INTO billing (id, name, patient_id, price, date) VALUES (3, 'Vaccine', 2, '500.00', '2021-01-01 14:30:01');