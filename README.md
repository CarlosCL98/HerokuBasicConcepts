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

Ahora se ejecuta **git push heroku master** para desplegar la aplicación. Para esto, los archivos del proyecto deben estar en la raíz del repositorio para que funcione el despliegue.

![](imgs/2-DesplegandoEnHeroku.png)

Ahora podemos comprobar que hay una instancia de nuestra aplicación ejecutándose mediante el comando **heroku ps:scale web=1**

![](imgs/2-VerificandoHerokuRunning.png)

Para abrir la aplicación ejecutándose en heroku se usa **heroku open** y se redirecciona a la app.

![](imgs/2-AbriendoAppHeroku.png)

Ahora debemos completar los siguientes puntos:

- Complete the code to ensure that all the UserController endpoints are working properly.
	
	![](imgs/2-CarController.png)
	![](imgs/2-CarController2.png)

- Complete the code to ensure that all the CarController endpoints are working properly.

	![](imgs/2-UserController.png)

### View logs
Para ver los logs usamos **heroku logs --tail**

![](imgs/3-LogsHeroku.png)

### Use a database
Para usar Heroku Postgres en nuestra aplicación debemos usar el comando **heroku addons**

![](imgs/4-HerokuAddOns.png)

Ahora podemos ver la URL de la base de datos con el comando **heroku config** para poder realizar la configuración.

DATABASE_URL: postgres://zzyocyjnelxucb:905cae55ab2f241ba88f67132a1e847ce5564a0fd4b9d324b45ad584cd4e50a4@ec2-54-225-129-101.compute-1.amazonaws.com:5432/d5qs7ja74kk2jj

![](imgs/4-HerokuConfigDatabase.png)

Para ver información más general sobre la base de datos creada, se usa el comando **heroku pg**

![](imgs/4-HerokuPGInformacionGeneral.png)

### Verificar Funcionamiento de la conexión con la base

![](imgs/5-UsersPOST.png)

![](imgs/5-UsersPUT.png)

![](imgs/5-UsersGET.png)

![](imgs/5-UsersDELETE.png)

![](imgs/5-CarsPOST.png)

![](imgs/5-CarsPUT.png)

![](imgs/5-CarsGET.png)

![](imgs/5-CarsDELETE.png)
