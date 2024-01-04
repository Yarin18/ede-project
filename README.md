# EDE Project Yarin 

Table Of Content
1. Description of the project
2. Databases
3. Microservices
4. API Gateway (+ Rate Limiting & Security)
5. Endpoints


## Descriptipn
I have made this project by myself. Which only required a minimum of 3 Microservices. 
For the theme of this project I went with a fitness/health themed project. I have 3 microservices which I will explain more in depth below.
The three microservices I have are the "Nutrition Service", which allows you to track your meals. Then I have a "Workout Plan Service", which
allows you to track your workouts. To finish of, I've got a "User Service", which deals with users, for example passwords, emails, workout goals, etc.

## Databases
For the databases for each Microservice, I went with 2x MongoDB, and 1x MySQL. The reason for this is, most of our classes use relational databases for
exercises, and I wanted to try this out. Another reason for going twice with MongoDB is, I did my first service with it, and it went easy without any problems,
so I figured I would use this again for another service.

The services that run on MongoDB are the WorkoutPlanService, and the UserService. The NutritionService runs on MySQL.

## Microservices

In this section of the documentation I will be describing each microservice in dept. 

### Workout Plan Service (MongoDB)
This service goes over all the Workout planning. Users can create their own workouts, etc.
The model. For this service, I have 1 Model (Workout), and 2 DTO's (WorkoutRequest & WorkoutResponse)
The @Document annotation defines what the collection will be called where these workouts will be stored.
```java
@Document(collection = "workouts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout {

    private String id, name, userId;
    private Date date;
    private int minutes;
    private boolean isCardioWorkout;
}
```
Below is a list of all the endpoints available in this service, they will be explained more in dept with proof of working more down below in the "Endpoints" sections.
```
GET - /api/workout/test
GET - /api/workout/{id}
GET - /api/workout/all
GET - /api/workout/user?userId=<userId>
POST - /api/workout
PUT - /api/workout/{id}
DELETE - /api/workout/{id}
```

### Nutrition Service (MySQL)
The purpose of this service is to track your nutrition habbits, this can be done by creating Meals. 
In this service I have 1 model (Meal), and 2 DTOs (MealRequest & MealResponse).
These Meal objects are stored in a table named "nutrition", as you can see by the @Table annotation. 
```java
@Entity
@Table(name = "nutrition")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String name, userId;
    private int totalCalories;

}
```
Below is a list of all the endpoints available in this service, they will be explained more in dept with proof of working more down below in the "Endpoints" sections.
```
GET - /api/meal/all
GET - /api/meal/{id}
GET - /api/meal/user?userId=<userId>
POST - /api/meal
PUT - /api/meal/{id}
DELETE -/api/meal/{id}
```

### User Service (MongoDB)
The user service is a place where Users can be managed, users have workout goals, names, emails and passwords. 
This service again has 1 model (User) and 2 DTOs (UserRequest & UserResponse).
These documents are each stored in a collection named "users".
```java
@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id, name, password, email;
    // goal of hitting an x amount of workouts per week
    private int workoutGoal;
}
```
Below is a list of all the endpoints available in this service, they will be explained more in dept with proof of working more down below in the "Endpoints" sections.
```
GET - /api/user/all
GET - /api/user/{id}
POST - /api/user
PUT - /api/user/{id}
DELETE - /api/user/{id}
```

## API Gateway
In this section I will be explaining more about the API gateway specifics of my project. 
This includes rate limiting, security and the gateway re-routing itself.
The API gateway is meant to re-route all of the services endpoints to a more centralized endpoint.
For example, my individual services have the endpoints

```
GET - /api/user/all
GET - /api/meal/all
```
The gateway will allow you to re-route those endpoints and configure them to work like this
```
GET - /users/all
GET - /meals/all
```
This looks overall cleaner, and allows you to secure endpoints in a central place as well.

I added **every** endpoint in all of my services to the API Gateway.

### Security
For the security of my endpoints I used OAuth2. For this I had to configure a SecurityWebFilterChain. 
In that configuration I went ahead and forced authorized access on all matches to the /workout /meal and /user patterns.
I also had to make some adjustments to the application.yml for this.

### Rate Limiting
The rate limiting has been configured by the Redis rate limiting.
For this I had to create a new docker container running on the port 6379. Then I had to configure a "RedisStandaloneConfiguration", which I had to pass along into a "LettuceConnectionFactory"
and then to finish off the redis configuration, I've had to create a template and set the factory.

## Endpoints
In this section I will be describing each endpoint (as configured in the API gateway), with proof of the working of it.





