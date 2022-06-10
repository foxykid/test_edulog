# Getting started

THIS IS A PROJECT TO STORE AND VIEW GPS TRACKS

# Requirements:

1.Java 8

2.H2 database v.2.1.2

# Project structure

```
src/                        
|- main/  
|  |- java.com.example.demo     project source code
|  |  |- controller				controller
|  |  |- domain					entities
|  |  |- dto					dto
|  |  |- enums					constant
|  |  |- exception				define and handle exception
|  |  |- helper					get message properties
|  |  |- mapper					map data between GPS and GPX
|  |  |- model					response model
|  |  |- repository				data access
|  |  |- service				service
|  |  |- util					utility class
|  |- resources     			setting files
|- test/ 
|  |- java.com.example.demo     test code
|  |  |- controller				test controller
|  |  |- repository				test data access
|  |  |- service				test service
```

# API list

Method	| Path	| Description	|
------------- | ------------------------- | ------------- |
POST	| /gps/uploadFile	| Upload gpx file	|
GET	| /gps/detail/{gpsId}	| Get gpx file for view details	|
GET	| /gps/list?page={pageSize}&size={size}	| View a list of latest tracks	|

# Diagram

<img width="683" height="734" alt="Upload file flow" src="https://github.com/foxykid/test_edulog/diagram/upload_diagram.png">

#### Get latest tracks
<img width="508" height="564" alt="Get latest tracks" src="https://github.com/foxykid/test_edulog/diagram/list_diagram.png">

#### View the details of track file
<img width="443" height="744" alt="Details of track" src="https://github.com/foxykid/test_edulog/diagram/detail_diagram.png">