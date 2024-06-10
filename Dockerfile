# Use a imagem do OpenJDK 17 como base para a construção
FROM openjdk:17-jdk-slim AS builder

# Atualiza o índice do pacote e instala o Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho como /build
WORKDIR /build

# Copia apenas o arquivo de definição de dependências para aproveitar o cache
COPY pom.xml .

# Baixa as dependências de compilação
RUN mvn dependency:go-offline

# Copia todo o conteúdo do projeto (exceto arquivos listados no .dockerignore)
COPY . .

# Compila o projeto
RUN mvn package -DskipTests   # Ignora a execução dos testes durante a compilação

# Use a imagem do OpenJDK 17 como base para a aplicação
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho como /app
WORKDIR /app

# Copia o arquivo JAR gerado durante a compilação para o diretório /app
COPY --from=builder /build/target/*.jar /app/app.jar

# Copia o JAR do driver do PostgreSQL para o diretório /app/lib
COPY postgresql-driver.jar /app/lib/postgresql-driver.jar

# Adiciona o diretório /app/lib ao classpath da aplicação
ENV CLASSPATH=/app/lib/*

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Comando para executar a aplicação quando o container for iniciado
CMD java -jar app.jar
