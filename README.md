# acsendo
prueba acsendo

jar autogenerado, no es necesario desplegar en servidor
al momento de generarse el jar se genera tabla 
revisar que la propiedad 
spring.jpa.hibernate.ddl-auto este en create (ver archivo de application.properties)

index.xhtml --> para usuarios generales, si ya ha cargado billetes y denominaciones se puede generar el calculo con el valor que se entregue
login.xhtml --> para acceso a carga de billetes, usuario admin contraseña 1234
admin.xhtml --> da opcion de carga de billetes y visualiza los que ya estan cargados

----------------------------------------------------------------------------------------------------

en ocasiones la redireccion del login no funciona bien, se deben hacer 2 reintentos
revisar que en la url de la base de datos y las credenciales de la misma correspondan con lo que esta en el archivo de propiedades

spring.datasource.url=jdbc:postgresql://localhost:5432/acsendotest
spring.datasource.username=postgres
spring.datasource.password=petunia12
