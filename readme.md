# Application description 

#### Sample init data is provided in `src/main/resources/data.sql` <br> 
### `INSERT INTO users (username, password, role) VALUES ('superuser', 'password', 'ADMIN');` <br>
### `INSERT INTO users (username, password, role) VALUES ('user', 'password', 'USER');`
<hr>

# How to run the application using Docker: 
1. Run **gradle build** task
2. From `prj-user-management` directory run the following: `docker build -t user-management:latest .`
3. Run the following: `docker run -p 8080:8080 user-management`
4. Go to `http://localhost:8080/swagger-ui/index.html#/` and navigate through swagger search bar to `/api`.

