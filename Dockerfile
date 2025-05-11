# Dùng OpenJDK 17 (Render hỗ trợ tốt)
FROM openjdk:17

# Đặt biến ARG để chỉ định file jar sau khi build
ARG JAR_FILE=target/*.jar

# Copy file jar vào container
COPY ${JAR_FILE} app.jar

# Lệnh chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
