# TODO app - backend
Backend implemented on Spring Boot that serves REST services to an Angular app ([TODO app](https://github.com/jpOlivo/todoapp-frontend)) in order to perform CRUD operations on a MongoDB instance created on [mLab](https://mlab.com/).

This implementation is based in [this tutorial](https://www.callicoder.com/spring-boot-mongodb-angular-js-rest-api-tutorial/).

# Running the app
The backend app can be executed from [TodoappApplication](https://github.com/jpOlivo/todoapp-backend/blob/master/src/main/java/com/example/todoapp/TodoappApplication.java) class.

# API REST
The documentation of API Rest is available in [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)

![img](https://i.imgur.com/3k91pkd.png)

# Database
There is a database created on mLab that can be access through MongoDB URI:

`mongodb://<dbuser>:<dbpassword>@ds125031.mlab.com:25031/todoapp`

or you can use your own database, review the [application.properties](https://github.com/jpOlivo/todoapp-backend/blob/master/src/main/resources/application.properties) file in order to configure it at your convenience.
