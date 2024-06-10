# Use a imagem do OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Instalação do Maven
RUN apt-get update && apt-get install -y maven

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie o arquivo pom.xml para o diretório atual
COPY pom.xml .

# Baixe todas as dependências de compilação
RUN mvn dependency:go-offline

# Copie todos os arquivos do projeto para o diretório atual
COPY . .

# Compile o projeto e renomeie o arquivo JAR gerado para app.jar
RUN mvn clean package && \
    mv target/*.jar /app/app.jar

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Comando para executar a aplicação quando o container for iniciado
CMD java -jar app.jar