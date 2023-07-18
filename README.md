# webuni-spring-kh

## Course
### Get course:

```http request
GET http://localhost:8080/api/courses?full=true&id=2&name=La&teachers.name=Sí&students.id=4&semesterNumber=2,3
```

### Update course:
```http request
PUT http://localhost:8080/api/courses/46
```
```json
{
    "name":"Updated Course name",
    "studentIds": [63, 64, 65, 67]
}
```

### Get course history:
```http request
GET http://localhost:8080/api/courses/46/history
```

## Teacher
### Create teacher:
```http request
POST http://localhost:8080/api/teachers
```
```json
{
    "name":"Test One Teacher",
    "birthDate":"1999-02-21T00:00:00"
}
```
### Update teacher:
```http request
PUT http://localhost:8080/api/teachers
```
```json
{
    "id": 38,
    "name":"Test One Teacher U",
    "birthDate":"1997-02-21T00:00:00"
}
```
### Delete teacher:
```http request
DELETE http://localhost:8080/api/teachers/38
```
### Get teacher history:
```http request
GET http://localhost:8080/api/teachers/38/history
```

## Student
### Create student:
```http request
POST http://localhost:8080/api/students
```
```json
{
    "name":"Test One Student",
    "birthDate":"1999-02-21T00:00:00",
    "semester": 3,
    "studentCentralId":400
}
```
### Update student:
```http request
PUT http://localhost:8080/api/students/1
```
```json
{
    "id": 38,
    "name":"Test One Student U",
    "birthDate":"1997-02-21T00:00:00"
}
```
### Delete student:
```http request
DELETE http://localhost:8080/api/students/38
```
### Get student history:
```http request
GET http://localhost:8080/api/students/38/history
```