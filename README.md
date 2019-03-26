## Heroku Basic Concepts
### Prepare the app
- **Execute the app in localhost. (Add screenshot)**
	Para esto, debemos iniciar la aplicación con **mvn spring-boot:run**
	
	![](imgs/1-spring-bootrun.png)
	
	y después de cargada, en el navegador se ingresa a la app con http://localhost:5000/
	
	![](imgs/1-LocalHost.png) 
		
- **What the application do?**
	Consiste en una aplicación simple en java que ofrece un tutorial para hacer el despliegue de aplicaciones en heroku. También posee una api rest que al desplegarla tiene servicios de usuarios y carros.
	
- **Describe the REST services exposed by the application.**
	Los dos servicios REST que ofrece son de carros y de usuarios. Básicamente se basa en un CRUD de usuarios teniendo los servicios de obtener, crear, actualizar y eliminar un usuario, y todo esto lo realiza por medio del path **/users**. Para los carros, se ofrecen las mismas funcionalidades (es decir un CRUD de carros) y por medio del path **/cars** se puede consumir el servicio.

- **For what purpose is the Procfile plaintext file?**
	El archivo Procfile indica cuáles son los comandos que son ejecutados cuando la aplicación se inicia. Se puede usar este documento para declarar una variedad de tipos de proceso como por ejemplo el servidor web de la aplicación, tareas a realizar antes de que una nueva versión sea desplegada, entre otros.
	
	![](imgs/1-Procfile.png)
		
	Tiene la siguiente estructura: **{process type}: {command}**
	
### Deploy the app
Para ejecutar el comando **heroku create** tuvimos que instalar Heroku desde la consola de comandos.

![](imgs/2-HerokuInstall.png)

Ahora se crea una app en heroku mediante el comando **heroku create**

![](imgs/2-HerokuAppCreate.png)

![](imgs/2-2HerokuAppCreate.png)

Ahora se ejecuta **git push heroku master** para desplegar la aplicación.

![](imgs/)



