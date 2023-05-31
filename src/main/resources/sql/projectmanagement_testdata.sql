USE projectmanagementdb;


INSERT INTO users (name, email, username, password)
VALUES ('John Doe', 'johndoe@example.com', 'johndoe', 'password123'),
       ('Jane Smith', 'janesmith@example.com', 'janesmith', 'password456'),
       ('Bob Johnson', 'bobjohnson@example.com', 'bobjohnson', 'password789');

INSERT INTO projects (name, description, status, budget, start_date, end_date)
VALUES ('Website Redesign', 'Redesigning company website for better user experience', 'doing', 50000.00,
        '2023-06-01 00:00:00', '2023-08-31 00:00:00'),
       ('Mobile App Development', 'Developing a mobile app for iOS and Android platforms', 'todo', 70000.00,
        '2023-07-01 00:00:00', '2023-10-31 00:00:00'),
       ('Social Media Campaign', 'Creating a social media campaign to increase brand awareness', 'done', 35000.00,
        '2023-05-01 00:00:00', '2023-06-30 00:00:00');

INSERT INTO subprojects (project_id, name, description, status, budget, start_date, end_date)
VALUES (1, 'Homepage Redesign', 'Redesigning the homepage to make it more user-friendly', 'doing', 6500.00,
        '2023-06-01 00:00:00', '2023-06-30 00:00:00'),
       (1, 'Product Page Redesign', 'Redesigning the product page to improve conversions', 'done', 8000.00,
        '2023-07-01 00:00:00', '2023-07-31 00:00:00'),
       (2, 'iOS Development', 'Developing the iOS version of the mobile app', 'todo', 15000.00, '2023-07-01 00:00:00',
        '2023-08-31 00:00:00');

INSERT INTO tasks (subproject_id, name, description, status, start_date, end_date, budget, estimated_time)
VALUES (1, 'Design Wireframes', 'Create wireframes for the new homepage design', 'doing', '2023-07-01 00:00:00',
        '2023-06-07 00:00:00', 1000.00, 20.0),
       (1, 'Design Mockups', 'Create mockups based on wireframes for homepage redesign', 'done', '2023-06-11 00:00:00',
        '2023-06-14 00:00:00', 1200.00, 30.0),
       (1, 'Hero Div', 'Create video slider for Hero Div', 'todo', '2023-06-12 00:00:00',
        '2023-06-14 00:00:00', 500.00, 2.0),
       (2, 'Develop Backend', 'Develop backend functionality for the mobile app', 'todo', '2023-07-01 00:00:00',
        '2023-07-31 00:00:00', 2000.00, 80.0),
       (2, 'Develop leaderboard', 'Develop leaderbaord for high scores', 'done', '2023-08-01 00:00:00',
        '2023-07-31 00:00:00', 2000.00, 80.0),
       (2, 'Develop Backend', 'Develop backend functionality for the mobile app', 'todo', '2023-07-01 00:00:00',
        '2023-07-31 00:00:00', 2000.00, 80.0);

INSERT INTO project_members (user_id, hourly_rate, project_id, role)
VALUES (1, 50.00, 1, "Administrator"),
       (2, 75.00, 1, "Designer"),
       (3, 100.00, 2, "Developer");

INSERT INTO customers (name, email, password)
VALUES ('ABC Corporation', 'abccorp@example.com', 'password123'),
       ('XYZ LLC', 'xyzllc@example.com', 'password123'),
       ('Acme Inc', 'acmeinc@example.com', 'password123');

INSERT INTO project_customers (customer_id, project_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO task_members (user_id, task_id)
VALUES (1, 1),
       (2, 2),
       (1, 3),
       (1, 4),
       (2, 5),
       (1, 6);