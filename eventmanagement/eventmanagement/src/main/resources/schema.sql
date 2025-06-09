-- Drop tables with CASCADE to handle dependent objects
DROP TABLE IF EXISTS attendees CASCADE;
DROP TABLE IF EXISTS events CASCADE;

-- Create events table
CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    event_date DATE NOT NULL,
    location VARCHAR(255),
    created_by VARCHAR(100),
    capacity INTEGER NOT NULL CHECK (capacity >= 1),
    remaining_capacity INTEGER NOT NULL CHECK (remaining_capacity >= 0 AND remaining_capacity <= capacity),
    tags VARCHAR(255)
);

-- Create attendees table
CREATE TABLE attendees (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    event_id BIGINT NOT NULL,
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);

-- Optional: Insert sample data for testing
INSERT INTO events (name, description, event_date, location, created_by, capacity, remaining_capacity, tags)
VALUES
    ('Tech Conference 2025', 'Annual tech conference', '2025-07-15', 'San Francisco', 'John Doe', 100, 100, 'tech,conference'),
    ('Music Festival', 'Outdoor music event', '2025-08-20', 'Austin', 'Jane Smith', 500, 500, 'music,festival');

INSERT INTO attendees (name, email, event_id)
VALUES
    ('Alice Johnson', 'alice@example.com', 1),
    ('Bob Williams', 'bob@example.com', 1);