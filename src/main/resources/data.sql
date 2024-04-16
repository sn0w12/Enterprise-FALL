INSERT INTO cars (brand, model, registration_number, price_per_day, is_booked) VALUES
('Toyota', 'Corolla', 'REG123', 55.0, false),
('Ford', 'Fiesta', 'REG124', 45.0, false),
('Tesla', 'Model 3', 'REG125', 85.0, true),
('Hyundai', 'i10', 'REG126', 40.0, false),
('BMW', '320i', 'REG127', 70.0, true);

--Booking testdata
INSERT INTO Booking (id, booking, booking_date, return_date, person_who_books, car_booked) VALUES
(1, 'Booking1', '2024-04-16', '2024-04-20', 'John Doe', 'Toyota Camry'),
(2, 'Booking2', '2024-04-18', '2024-04-25', 'Jane Smith', 'Honda Civic'),
(3, 'Booking3', '2024-04-20', '2024-04-24', 'Alice Johnson', 'Ford Mustang');

INSERT INTO customers (username, name, address, email, phone_number) VALUES
('jdoe', 'John Doe', '1234 Elm Street', 'johndoe@example.com', '555-1234'),
('asmith', 'Alice Smith', '2345 Oak Street', 'alicesmith@example.com', '555-2345'),
('rjones', 'Robert Jones', '3456 Pine Street', 'robertjones@example.com', '555-3456'),
('emiller', 'Emma Miller', '4567 Maple Street', 'emmamiller@example.com', '555-4567'),
('twilliams', 'Tom Williams', '5678 Spruce Street', 'tomwilliams@example.com', '555-5678');
