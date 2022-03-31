## Drones

A drone management service
---

# Start up
Run the below command to start up the project

`mvn spring-boot:run`

# The Service JSON REST APIs:
> Register a drone;
   
**Endpoint** POST http://localhost:8878/api/drone-manager/register

**Sample Request**
`{
"serial": "dfivehe635qwteghdiwjdfivehe635qwteghdiwjdfivehe635qwteghdiwjdfivehe635qwteghdiwjdfivehe635qwteghdiwj",
"model": "Lightweight",
"weightLimit": 450,
"batteryCapacity": 100,
"state": "IDLE"
}`

**Sample Response**
`{
"droneId": 1,
"serial": "dfivehe635jsjs",
"model": "Lightweight",
"currentWeight": 0.0,
"weightLimit": 450.0,
"batteryCapacity": 100.0,
"state": "IDLE"
}`
***

> Load a drone with medication items;

**Endpoint** POST http://localhost:8878/api/drone-manager/load

**Sample Request**
`{
"droneId": 1,
"medicationRequests": [
{
"name": "vitamin_c",
"weight": "50",
"code": "VIT594",
"image": "image.png"
},
{
"name": "advil",
"weight": "120",
"code": "ADV955",
"image": "image.png"
}
]
}`

**Sample  Response**
`{
"droneId": 1,
"currentWeight": 170.0,
"droneWeightLimit": 450.0,
"droneBatteryCapacity": 100.0,
"medicationRequests": [
{
"medicationId": 3,
"name": "vitamin_c",
"weight": 50.0,
"code": "VIT594",
"image": "image.png"
},
{
"medicationId": 4,
"name": "advil",
"weight": 120.0,
"code": "ADV955",
"image": "image.png"
}
]
}`
***
> Check loaded medication items for a given drone; 

**Endpoint** GET http://localhost:8878/api/drone-manager/contents/1

**Sample Response**
`[
{
"droneId": 1,
"currentWeight": 170.0,
"medications": [
{
"medicationId": 3,
"name": "vitamin_c",
"weight": 50.0,
"code": "VIT594",
"image": "image.png"
},
{
"medicationId": 4,
"name": "advil",
"weight": 120.0,
"code": "ADV955",
"image": "image.png"
}
]
}
]`
***
> Check available drones for loading;

**Endpoint** GET http://localhost:8878/api/drone-manager/available-drones

**Sample Response**
`[
{
"droneId": 1,
"serial": "dfivehe635jsjs",
"model": "Middleweight",
"currentWeight": 0.0,
"weightLimit": 500.0,
"batteryCapacity": 70.0,
"state": "IDLE"
},
{
"droneId": 3,
"serial": "deighthe635jsjs",
"model": "Middleweight",
"currentWeight": 170.0,
"weightLimit": 500.0,
"batteryCapacity": 80.0,
"state": "LOADING"
}
]`
***
> Check drone battery level;

**Endpoint** GET http://localhost:8878/api/drone-manager/battery/7

**Sample Response**
`{
"droneId": 7,
"serial": "deighthe635jsjs",
"model": "Middleweight",
"currentWeight": 500.0,
"weightLimit": 500.0,
"batteryCapacity": 80.0,
"state": "LOADING"
}`

:scroll: **END**
