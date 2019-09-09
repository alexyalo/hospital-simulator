# Build
El proyecto utiliza maven, por lo que es necesario tener el comando de consola **mvn** instalado.

## Comando para generar jar 
Estando parado en la raíz del proyecto, ejecutar
```mvn clean package```

# Run
La aplicación debe ser llamada con al menos un parámetro:
```java -cp hospital-1.0-SNAPSHOT-jar-with-dependencies.jar Application [status by comma] [drugs by comma]```

## Patient Status disponibles
* D (diabetes)
* F (fever)
* X (dead)
* T (tuberculosis)
* H (healthy)

## Drugs disponibles
* I (insulina)
* An (antibiotic)
* As (aspirin)
* P (paracetamol)

## Ejemplo
```java -cp hospital-1.0-SNAPSHOT-jar-with-dependencies.jar Application F,D P,I```

El output va a ser:
```F:0,H:1,D:1,T:0,X:0```