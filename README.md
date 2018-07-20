# TODO app - Backend
This app is implemented on Spring Boot and exposes REST services in order to consumed by an Angular app ([TODO app](https://github.com/jpOlivo/todoapp-frontend)).

This implementation was developed following a [tutorial](https://www.callicoder.com/spring-boot-mongodb-angular-js-rest-api-tutorial/) from [@callicoder](https://twitter.com/callicoder)


## API Rest
The services offered enable to perform CRUD operations on a MongoDB instance created on [mLab](https://mlab.com/databases/todoapp).

![img](https://i.imgur.com/3k91pkd.png)

There is a docs of this API available in [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)


## Database
An MongoDB instance was created in the cloud and it can be access through URI: `mongodb://<dbuser>:<dbpassword>@ds125031.mlab.com:25031/todoapp`

Also, you can use your own database. Please, review the [application.properties](https://github.com/jpOlivo/todoapp-backend/blob/master/src/main/resources/application.properties) file in order to change it.


## Running the app
From Eclipse IDE `com.example.todoapp.TodoappApplication -> Run As -> Java Application`

## Invoking services

1- Get __/api/todos__

```
c:\>curl http://localhost:8080/api/todos 

[{"id":"5b3bc75eb5f445377ce776a3","title":"Learn Angular","completed":false,"createdAt":"2018-07-03T18:58:38.060+0000"}]
```

2- Get __/api/todos/{id}__

```
c:\>curl http://localhost:8080/api/todos/5b3bc75eb5f445377ce776a3

{"id":"5b3bc75eb5f445377ce776a3","title":"Learn Angular","completed":false,"createdAt":"2018-07-03T18:58:38.060+0000"}
```

 


