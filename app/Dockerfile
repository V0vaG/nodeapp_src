# ========================
# 1. Build React frontend
# ========================
FROM node:18 AS frontend-build

WORKDIR /app/frontend
COPY frontend/ ./

RUN npm install && npm run build


# ========================
# 2. Build Spring Boot backend
# ========================
FROM maven:3.9-eclipse-temurin-17 AS backend-build

WORKDIR /app/backend
COPY backend/ ./

# Copy frontend build into backend resources
COPY --from=frontend-build /app/frontend/build/ src/main/resources/static/

RUN mvn clean package -DskipTests


# ========================
# 3. Run Spring Boot app on port 5000
# ========================
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=backend-build /app/backend/target/*.jar app.jar

EXPOSE 5000

ENV SERVER_PORT=5000

ENTRYPOINT ["java", "-jar", "app.jar"]
