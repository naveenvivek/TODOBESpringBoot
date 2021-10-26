
# TODOBESpringBoot

TODO backend with restful endpoints supporting below tasks

Create TODO task,
Delete TODO Task, 
Update TODO Task, 
View TODOTask with Description and Date.





## API Reference

```http
  GET /todo/{userId}
```
#### Get User's All Todos List by passing User ID

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId ` | int      | **Required**. User Id |

#### Delete User's Todo Task by passing User ID and Todo Id

```http
  DELETE /todo/{userId}/deleteTodo/{todoId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId ` | int      | **Required**. User Id |
| `todoId ` | int      | **Required**. TODO Id |


#### Create New Todo Task for User passing User ID and Todo Request in body

```http
  POST /todo/{userId}/todos
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId ` | int      | **Required**. User Id |
| `Description` | string      | **Required**. Description |

#### Update Todo Task completion Status for User passing User ID and Todo Id

```http
  PUT /todo/updateTodo/{todoId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId ` | int      | **Required**. User Id |
| `todoId ` | int      | **Required**. TODO Id |
| `Description` | string      | **Required**. Description |


  
## Authors

- [@naveenvivek](https://github.com/naveenvivek)

  
## Deployment

To deploy this project :

First Approach :
i) Download the final war attached. https://github.com/naveenvivek/TODOBESpringBoot/blob/master/screenshot/todo-1.0.jar
ii) Have java 13 LTS version in classpath
iii) From command prompt execute "java -jar todo-1.0.jar"
iv) Application would start up
v) Once started application will run in port 8080 
vi) Below Url http://localhost:8080/swagger-ui.html

Second Approach to run fropm Intellij/Eclipse:
i) Checkout project from github https://github.com/naveenvivek/TODOBESpringBoot
ii) Do maven clean, test, package
iii) Run to build successful and target war would be generated.
iv) Run TodoApplication.java, as Run as java application.
v) Application would be started to run in port 8080
vi) Below Url http://localhost:8080/swagger-ui.html

  
## Screenshots

https://github.com/naveenvivek/TODOBESpringBoot/blob/master/screenshot/deploywar.jpg
https://github.com/naveenvivek/TODOBESpringBoot/blob/master/screenshot/todoswagger.jpg



  
