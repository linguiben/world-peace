Summary of Changes
1. UI Fixes
   | Issue | Fix |
   |-------|-----|
   | Footer not centered | Fixed CSS selector .subfoot_zbdw + added .subfoot_logo centering |
   | Banner/link position | Moved to new <section class="banner-section"> below service cards |
   | Stats badges position | Moved to navbar with new .navbar-stats class |
2. User Registration Feature
   | File | Changes |
   |------|---------|
   | index.html | Added tabbed modal with Sign In / Sign Up tabs |
   | LoginMapper.java | Added insertUser(), existsByUsername() |
   | LoginMapper.xml | Added SQL for insert and existence check |
   | LoginService.java | Added register(), existsByUsername() methods |
   | LoginController.java | Added POST /register endpoint with validation |
3. Docker Compose Support
   | File | Purpose |
   |------|---------|
   | docker-compose.yml | Orchestrates PostgreSQL + wpadmin containers |
   | .env.example | Template for environment variables |
   | application.yml | Updated to use ${DB_URL}, ${DB_USERNAME}, ${DB_PASSWORD} |
   Quick Start with Docker
   cd world-peace-admin
# 1. Create .env from template
cp .env.example .env
# Edit .env with your credentials
# 2. Build the application JAR
mvn clean package -DskipTests
# 3. Start all services
docker-compose up -d
# 4. View logs
docker-compose logs -f wpadmin
# 5. Stop services
docker-compose down
The services will be available at:
- Application: http://localhost:8090
- PostgreSQL: localhost:5432