INSERT INTO cars (brand, model, registration_number, price_per_day, is_booked) VALUES ('Toyota', 'Corolla', 'REG123', 55.0, false);
INSERT INTO cars (brand, model, registration_number, price_per_day, is_booked) VALUES ('Ford', 'Fiesta', 'REG124', 45.0, false);
INSERT INTO cars (brand, model, registration_number, price_per_day, is_booked) VALUES ('Tesla', 'Model 3', 'REG125', 85.0, true);
INSERT INTO cars (brand, model, registration_number, price_per_day, is_booked) VALUES ('Hyundai', 'i10', 'REG126', 40.0, false);
INSERT INTO cars (brand, model, registration_number, price_per_day, is_booked) VALUES ('BMW', '320i', 'REG127', 70.0, true);


INSERT INTO Booking (id, booking, booking_date, return_date, person_who_books, car_booked) VALUES
(1, 'Booking1', '2024-04-16', '2024-04-20', 'John Doe', 'Toyota Camry'),
(2, 'Booking2', '2024-04-18', '2024-04-25', 'Jane Smith', 'Honda Civic'),
(3, 'Booking3', '2024-04-20', '2024-04-24', 'Alice Johnson', 'Ford Mustang');
