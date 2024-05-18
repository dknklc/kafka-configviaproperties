FROM amazoncorretto:17-alphine
RUN mkdir /app
COPY app.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]



# Neden alphine-jdk demedik? Çünkü compile etmesine gerek yok çünkü jar dosyasını çalıştırcak sadece. Zaten yapmıştık onu.
