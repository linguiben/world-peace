-- SQL script for login functionality tables
-- Database: wp (PostgreSQL)
-- Host: tx.jupiterSo.com:9527

-- User table for authentication
CREATE TABLE IF NOT EXISTS wp_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP,
    login_count INTEGER DEFAULT 0
);

-- Visitor statistics table
CREATE TABLE IF NOT EXISTS wp_visitor_stats (
    id INTEGER PRIMARY KEY DEFAULT 1,
    visit_count BIGINT DEFAULT 0,
    last_visit TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index for faster username lookup
CREATE INDEX IF NOT EXISTS idx_wp_user_username ON wp_user(username);

-- Insert initial visitor stats row
INSERT INTO wp_visitor_stats (id, visit_count, last_visit) 
VALUES (1, 0, CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

-- Example: Insert a test user (password should be hashed in production)
-- INSERT INTO wp_user (username, password) VALUES ('admin', 'admin123');
