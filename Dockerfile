FROM openjdk:21
LABEL authors="sebastiankravetz"

WORKDIR /app
COPY Wallet_MATRIX /app/wallet
COPY target/duoc_springboot_pets_ecommerce_ms-1.0.1.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]