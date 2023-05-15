INSERT INTO users (name, email, username, password)
VALUES ('John Doe', 'johndoe@example.com', 'johndoe', 'password123'),
       ('Jane Smith', 'janesmith@example.com', 'janesmith', 'password456'),
       ('Bob Johnson', 'bobjohnson@example.com', 'bobjohnson', 'password789');


INSERT INTO projects (name, description, status, budget, start_date, end_date)
VALUES ('Website Redesign', 'Redesigning company website for better user experience', 'doing', 5000.00,
        '2023-06-01 00:00:00', '2023-08-31 00:00:00'),
       ('Mobile App Development', 'Developing a mobile app for iOS and Android platforms', 'todo', 10000.00,
        '2023-07-01 00:00:00', '2023-10-31 00:00:00'),
       ('Social Media Campaign', 'Creating a social media campaign to increase brand awareness', 'done', 2500.00,
        '2023-05-01 00:00:00', '2023-06-30 00:00:00');


INSERT INTO subprojects (project_id, name, description, status, budget, start_date, end_date)
VALUES (1, 'Homepage Redesign', 'Redesigning the homepage to make it more user-friendly', 'doing', 2000.00,
        '2023-06-01 00:00:00', '2023-06-30 00:00:00'),
       (1, 'Product Page Redesign', 'Redesigning the product page to improve conversions', 'todo', 3000.00,
        '2023-07-01 00:00:00', '2023-07-31 00:00:00'),
       (2, 'iOS Development', 'Developing the iOS version of the mobile app', 'todo', 5000.00, '2023-07-01 00:00:00',
        '2023-08-31 00:00:00');


INSERT INTO tasks (subproject_id, name, description, status, start_date, end_date, budget, estimated_time)
VALUES (1, 'Design Wireframes', 'Create wireframes for the new homepage design', 'doing', '2023-06-01 00:00:00',
        '2023-06-07 00:00:00', 500.00, 20.0),
       (1, 'Design Mockups', 'Create mockups based on wireframes for homepage redesign', 'todo', '2023-06-08 00:00:00',
        '2023-06-14 00:00:00', 750.00, 30.0),
       (2, 'Develop Backend', 'Develop backend functionality for the mobile app', 'todo', '2023-07-01 00:00:00',
        '2023-07-31 00:00:00', 2000.00, 80.0);


INSERT INTO project_members (user_id, hourly_rate, project_id, role)
VALUES (1, 50.00, 1, "Administrator"),
       (2, 75.00, 1, "Designer"),
       (3, 100.00, 2, "Developer");


INSERT INTO customers (name, email, phone)
VALUES ('ABC Corporation', 'abccorp@example.com', '123-456-7890'),
       ('XYZ LLC', 'xyzllc@example.com', '555-555-5555'),
       ('Acme Inc', 'acmeinc@example.com', NULL);


INSERT INTO project_customers (customer_id, project_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);



