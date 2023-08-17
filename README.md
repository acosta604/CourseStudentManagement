
<h1 align="center">
  <br>
  COURSE STUDENT MANAGMENT API CRUD
  <br>
</h1>

<h4 align="center">A repository for developing a platform that facilitates course and student administration and tracking. -
<br>
<b>GROUP 1 - <a href="http://atl.academy" target="_blank">ATL ACADEMY</a></b>.</h4>



<p align="center">
   <a href="#endpoints">ENDPOINTS</a> •
  <a href="#runit">RUN IT</a>
</p>

![XsypBTZ](https://github.com/rretta/CourseStudentManagement/assets/87555292/1d1d68fb-a5ad-41ea-8a7d-a4c963c9e96e)


## ENDPOINTS


| Method   | Route                      | Description                                      |
| -------- | -------------------------- | ------------------------------------------------ |
| POST     | /api/student               | Register a new student                          |
| GET      | /api/student               | Get all students                                |
| GET      | /api/student/{id}          | Get details of a student by ID                  |
| PUT      | /api/student/{id}          | Update details of a student by ID               |
| DELETE   | /api/student/{id}          | Delete a student by ID                          |
| POST     | /api/professor             | Register a new professor                        |
| POST     | /api/enrollment            | Register a new enrollment                       |
| DELETE   | /api/enrollment/{id}       | Delete an enrollment by ID                      |
| GET      | /api/enrollment            | Get all enrollments                             |
| POST     | /api/course                | Register a new course                           |
| GET      | /api/course                | Get all courses                                 |
| GET      | /api/course/{id}           | Get details of a course by ID                   |
| PUT      | /api/course/update/{id}    | Update details of a course by ID                |
| DELETE   | /api/course/{id}           | Delete a course by ID                           |



## RUNIT

- create a database named `coursestudentmanagerbd`.


- set the  `application.properties` file correctly with the data of your DB


- run Springboot



