# Dockerfile usado no deploy do Render (build multi-stage)
# Checkpoint 4 - Parte 2 (MVC e Deploy) - TDS FIAP

# ---- Etapa 1: build da aplicacao com Maven ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q clean package -DskipTests

# ---- Etapa 2: imagem final, so com o JRE e o jar gerado ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/mercado-express-mvc.jar app.jar

# No Render, a aplicacao roda no perfil "h2" (banco em memoria), ja que a
# rede da FIAP (Oracle ORACLE_FIAP) e restrita a acessos internos/VPN e nao
# e alcancavel por servicos publicos de deploy. Localmente ou em rede FIAP,
# use o perfil "oracle" (ver README).
ENV SPRING_PROFILES_ACTIVE=h2
ENV PORT=8083

EXPOSE 8083
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
