# spring-server-demo
spring-server-demo

## insert
POST http://13.59.118.36:9090/user/profile  
```
{
	"name": "qiang",
	"age": 18,
	"gender": 1,
	"income": "1RMB",
	"phone": "110",
	"occupation": "programer"
}
```

## get list
GET http://13.59.118.36:9090/user/profile

## get one
GET http://13.59.118.36:9090/user/profile/{id}

