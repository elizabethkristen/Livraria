# Use a imagem do OpenJDK 17 como base
FROM openjdk:17-jdk-slim AS builder

# Instalação do Maven
RUN apt-get update && apt-get install -y maven

# Defina o diretório de trabalho como /build
WORKDIR /build

# Copie o arquivo pom.xml para o diretório atual
COPY pom.xml .

# Baixe todas as dependências de compilação
RUN mvn dependency:go-offline

# Copie todos os arquivos do projeto para o diretório atual
COPY . .

# Compile o projeto
RUN mvn package

# Use a imagem do OpenJDK 17 como base para a aplicação
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie o arquivo JAR gerado durante a compilação para o diretório /app
COPY --from=builder /build/target/*.jar /app/app.jar

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Comando para executar a aplicação quando o container for iniciado
CMD java -jar app.jar
