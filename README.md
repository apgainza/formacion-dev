Framework Backend Arquetipo Web
===================================

```
mvn archetype:generate -DarchetypeGroupId=com.mercadona.framework.cna.archetype -DarchetypeArtifactId=fwkcna-archetype-web -DarchetypeVersion=2.20.2
```
Podemos ejecutar y ver en funcionamiento el ejemplo incorporado en la generación del proyecto.
Prerequsitos:
- Docker
- Postman
- Cliente de postgres (para poder ejecutar SQLs de creación de tablas)
  Nos posicionamos en el directorio `raiz` del proyecto generado, y realizamos los siguientes pasos:
1. Levantamos una bbdd en local utilizando `docker`, en la carpeta `docker`
    ```
    docker-compose up
    ```
2. Creamos las tablas de base de datos del ejemplo utilizando el script situado en la carpeta `driven/repository-jpa/sql/migration/versions.1.0.0-create-schema-examples/V1.0.0__create-schema-examples.sql`

3. Hacemos la instalación en local del proyecto
    ```
    mvn clean install
    ```
4. Ejecutamos la aplicación desde la raíz del proyecto con:
    ```
    mvn clean spring-boot:run -pl boot -Dspring-boot.run.profiles=local
    ```
    o navegando a la carpeta `boot` del proyecto:
    ```
    mvn clean spring-boot:run -Dspring-boot.run.profiles=local
    ```
5. Probamos la aplicación desde `postman`, importamos las collections de la carpeta `driving/api-rest/postman`

# Microservicio generado a partir de arquetipo

Toda la documentación relevante al desarrollo de este tipo de proyectos se encuentra en la guía del desarrollador: [Guía del desarrollador](https://cna.gcp.mercadona.com/latest/archetype/web/)

Versión del arquetipo: ${archetypeVersion}
