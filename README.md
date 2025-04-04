# school_newspaper
## General Information 

React.js + Java/Spring Boot application

This is a personal exercise application, which has for a goal to use Java/ Spring boot as a backend and React.js as frontend. The application is using spring security/JWT in the backend for authentication and authorization and axios to make the API calls in the frontend.

The application is still in progress with goals to improve the design of the application, make the design responsive, add the functionality to upload images, SSO with Google.

## Status

The app is currently being able to allow the user registrate, sign in (using JWT), view personal information and articles created by the user, view articles for today, edit/create/remove article nad edit personal information.

### Sign up page
![изображение](https://user-images.githubusercontent.com/105558464/171043575-9e8122d0-22ae-4819-9d51-b1673d2134df.png)

### Sign in page
![изображение](https://user-images.githubusercontent.com/105558464/171043716-3e1fc984-ed64-4ee6-93e4-4a7becacc6a7.png)

### Homepage
![изображение](https://user-images.githubusercontent.com/105558464/171043834-691dc78f-8b64-4a3a-9f21-43bb648e31c0.png)

### Profile
![изображение](https://user-images.githubusercontent.com/105558464/171043928-1ddd881c-9dd6-42ce-bcf3-9b58128fb0e0.png)


## Starting the app

For the app to work, you will have to run the backend (in my case it is intellij) and run `npm start` in your IDE (in my case it is VS code), before running the frontend use the `npm install` command to install the needed libraries.

## Dependencies and used libraries

The backend is using:
 - 'org.springframework.boot:spring-boot-starter-security'
 - 'org.projectlombok:lombok'
 - 'com.auth0:java-jwt:3.11.0'
 - 'org.springframework.boot:spring-boot-starter-web'
 - 'org.springframework.boot:spring-boot-devtools'

The frontend is using:
 - axios
 - browser-router
 - jwt-decode
 - react-icons
