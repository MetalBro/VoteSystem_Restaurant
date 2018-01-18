
# REST API documentation

### Security logic:
1. Guests can see restaurants and dishes for making decision where to lunch
2. Only authorized users can vote where to lunch
3. Admins use special methods to make menu and get information about users, who made decision where to lunch

## 1. Restaurant requests

### 1.1. User/guest's requests

**Request to get list of all restaurants**

Request example:

`curl -s http://localhost:8080/rest/restaurants`

Response:

`[{"id":100005,"name":"Mangal","address":"Prospect Lenina, 20","cookery":"Azerbaijan","new":false},{"id":100007,"name":"Pizzburg","address":"Prospect Lenina, 29","cookery":"Italian","new":false},{"id":100006,"name":"Svoya kompanya","address":"Lesoparkovaya, 15","cookery":"Russian","new":false}]`

**Request to get restaurant by id**

Request example:

`curl -s http://localhost:8080/rest/restaurants/100007`

Response:

`{"id":100007,"name":"Pizzburg","address":"Prospect Lenina, 29","cookery":"Italian","new":false}` 

**Request to get restaurant by name**

Request example:

`curl -s http://localhost:8080/rest/restaurants/by?name=Svoya%20kompanya`

Response:

`{"id":100006,"name":"Svoya kompanya","address":"Lesoparkovaya, 15","cookery":"Russian","new":false}` 

**Request to get all restaurants with dishes for this day**

Request example:

`curl -s http://localhost:8080/rest/restaurants/todayDishes`

Request returns a map with restaurants, that have menu for today.

### 1.2. Admin's requests

**Request to input a new restaurant**

Request example:  

`curl -s -X POST -d '{"name":"Titanik","address":"Kutuzovskaya, 10","cookery":"European"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/ --user admin@gmail.com:admin`

Response:  

`{"id":100033,"name":"Titanik","address":"Kutuzovskaya, 10","cookery":"European","new":false}`

**Request to update a restaurant**

Request example:

`curl -s -X PUT -d '{"id":100005,"name":"Mangal","address":"Prospect Lenina, 20","cookery":"Azerbaijan"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/ --user admin@gmail.com:admin`

**Request to delete a restaurant**

Request example:

`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100033 --user admin@gmail.com:admin`


## 2. Dish requests

### 2.1. User/guest's requests

**Request to get actual menus for chosen restaurant**

Request example:

`curl -s http://localhost:8080/rest/restaurants/100006/dishes/actuals`

Response:

    {  
           "2018-05-31": [  
             {
                 "id": 100014,
                 "name": "Кофе",
                 "price": 50,
                 "date": [
                     2018,
                     5,
                     31
                 ],
                 "new": false
             },
             {
                 "id": 100015,
                 "name": "Картошка фри",
                 "price": 110.9,
                 "date": [
                     2018,
                     5,
                     31
                 ],
                 "new": false
             },
             {
                 "id": 100016,
                 "name": "Салат столичный",
                 "price": 221.2,
                 "date": [
                     2018,
                     5,
                     31
                 ],
                 "new": false
             }
         ],
         "2018-09-30": [
             {
                 "id": 100017,
                 "name": "Сок апельсиновый",
                 "price": 50,
                 "date": [
                     2018,
                     9,
                     30
                 ],
                 "new": false
             },
             {
                 "id": 100018,
                 "name": "Гречка",
                 "price": 110.9,
                 "date": [
                     2018,
                     9,
                     30
                 ],
                 "new": false
             },
             {
                 "id": 100019,
                 "name": "Котлеты",
                 "price": 221.2,
                 "date": [
                     2018,
                     9,
                     30
                 ],
                 "new": false
             },
             {
                 "id": 100020,
                 "name": "Компот",
                 "price": 50,
                 "date": [
                     2018,
                     9,
                     30
                 ],
                 "new": false
             }
         ]
    }

**Request to get menus for chosen restaurant and date**

Request example:

`curl -s http://localhost:8080/rest/restaurants/100006/dishes/by?date=2018-09-30`

Response:

    [
        {
            "id": 100017,
            "name": "Сок апельсиновый",
            "price": 50,
            "date": [
                2018,
                9,
                30
            ],
            "new": false
        },
        {
            "id": 100018,
            "name": "Гречка",
            "price": 110.9,
            "date": [
                2018,
                9,
                30
            ],
            "new": false
        },
        {
            "id": 100019,
            "name": "Котлеты",
            "price": 221.2,
            "date": [
                2018,
                9,
                30
            ],
            "new": false
        },
        {
            "id": 100020,
            "name": "Компот",
            "price": 50,
            "date": [
                2018,
                9,
                30
            ],
            "new": false
        }
    ]

**Request to get a dish by restaurant and id**

Request example:

`curl -s http://localhost:8080/rest/restaurants/100006/dishes/100014`

Response:

    {
        "id": 100014,
        "name": "Кофе",
        "price": 50,
        "date": [
            2018,
            5,
            31
        ],
        "new": false
    }

### 2.2. Admin's requests

**Request to delete a dish by restaurant and id**

Request example:

`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100005/dishes/100015 --user admin@gmail.com:admin`

**Request to delete all dishes by restaurant**

Request example:

`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100005/dishes --user admin@gmail.com:admin`

**Request to create a dish**

Request example:

`curl -s -X POST -d '{"name": "Fried fish","price": 521.50,"date": [2018, 9, 29]}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100007/dishes --user admin@gmail.com:admin`

Response:

`{"id":100033,"name":"Fried fish","price":521.50,"date":[2018,9,29],"new":false}`

**Request to update a dish**

Request example:

`curl -s -X PUT -d '{"id": 100021,"name":"Salmon roasted","price":750.50,"date":[2018, 9, 29]}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/100007/dishes --user admin@gmail.com:admin`

## 3. User requests

### 3.1. User's requests

**Request to get profile**

Request example: 

`curl -s http://localhost:8080/rest/profile --user user@yandex.ru:password`

Response:
    
    {
         "id": 100000,
         "name": "User",
         "email": "user@yandex.ru",
         "registered": 1516177924656,
         "enabled": true,
         "role": "ROLE_USER",
         "new": false
    }

**Request to update profile**

Request example:

`curl -s -X PUT -d '{"id":100000,"name":"newUser","email":"changed@yandex.ru","password":"newpass"}' -H 'Content-Type: application/json' http://localhost:8080/rest/profile --user user@yandex.ru:password`

**Request to delete profile**

Request example:

`curl -s -X DELETE http://localhost:8080/rest/profile --user user@yandex.ru:password`

### 3.1. Admin's requests

**Request to get all users**

Request example:

`curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin`

Response:

    [
        {
            "id": 100001,
            "name": "Admin",
            "email": "admin@gmail.com",
            "registered": 1516217507951,
            "enabled": true,
            "role": "ROLE_ADMIN",
            "new": false
        },
        {
            "id": 100000,
            "name": "User",
            "email": "user@yandex.ru",
            "registered": 1516217507951,
            "enabled": true,
            "role": "ROLE_USER",
            "new": false
        },
        {
            "id": 100002,
            "name": "User_1",
            "email": "user@mail.ru",
            "registered": 1516217507951,
            "enabled": true,
            "role": "ROLE_USER",
            "new": false
        },
        {
            "id": 100003,
            "name": "User_2",
            "email": "user@bk.ru",
            "registered": 1516217507951,
            "enabled": true,
            "role": "ROLE_USER",
            "new": false
        },
        {
            "id": 100004,
            "name": "User_3",
            "email": "user@list.ru",
            "registered": 1516217507951,
            "enabled": true,
            "role": "ROLE_USER",
            "new": false
        }
    ]

**Request to get user by id**

Request example:

`curl -s http://localhost:8080/rest/admin/users/100004 --user admin@gmail.com:admin`

Response:

`{"id":100004,"name":"User_3","email":"user@list.ru","registered":1516217507951,"enabled":true,"role":"ROLE_USER","new":false}`

**Request to create user**

Request example:

`curl -s -X POST -d '{"name": "Created User","email": "createdUser@yandex.ru", "password":"createdUserPassword"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@gmail.com:admin`

Response:

`{"id":100033,"name":"Created User","email":"createdUser@yandex.ru","registered":1516218030539,"enabled":true,"role":"ROLE_USER","new":false}`

**Request to create/update user via params**

Request example:

`curl -s -X POST -d "id=&name=postedUser&email=posted@mail.ru&password=postedpass" http://localhost:8080/rest/admin/users/ajax --user admin@gmail.com:admin`

**Request to delete user**

Request example:

`curl -s -X DELETE http://localhost:8080/rest/admin/users/100004 --user admin@gmail.com:admin`

**Request to update user**

Request example:

`curl -s -X PUT -d '{"id":100003,"name":"newUser","email":"updad@yandex.ru","password":"newpass"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/100003 --user admin@gmail.com:admin`

**Request to get user by email**

Request example:

`curl -s http://localhost:8080/rest/admin/users/by?email=user@mail.ru --user admin@gmail.com:admin`

Response:

`{"id":100002,"name":"User_1","email":"user@mail.ru","registered":1516219862675,"enabled":true,"role":"ROLE_USER","new":false}`

**Request to enable user**

Request example:

`curl -s -X POST -d "enabled=false" http://localhost:8080/rest/admin/users/100004 --user admin@gmail.com:admin`

## 4. Vote requests

### 4.1. User's requests

**Request to vote for restaurant at current day**

Request example:

`curl -s -X POST http://localhost:8080/rest/votes/restaurants/100007 --user user@yandex.ru:password`

**Request to vote for restaurant at chosen day**

`curl -s -X POST http://localhost:8080/rest/votes/restaurants/100007/dishes/2020-10-11 --user user@yandex.ru:password`

**Request to delete(cancel) vote for chosen day**

`curl -s -X DELETE http://localhost:8080/rest/votes/2020-10-11 --user user@yandex.ru:password`

**Request to get vote for chosen day**

`curl -s http://localhost:8080/rest/votes/2017-06-21 --user user@yandex.ru:password`

Response:

    {
        "primaryKey": {
            "user": {
                "id": 100000,
                "name": "User",
                "email": "user@yandex.ru",
                "registered": 1516222442467,
                "enabled": true,
                "role": "ROLE_USER",
                "new": false
            },
            "date": [
                2017,
                6,
                21
            ]
        },
        "restaurant": {
            "id": 100005,
            "name": "Nar-Sharab",
            "address": "Naberejnaya, 121",
            "cookery": "Caucasian",
            "new": false
        }
    }
    
**Request to get all your votes**

Request example: 

`curl -s http://localhost:8080/rest/votes/user --user admin@gmail.com:admin`

Response:

    [
        {
            "primaryKey": {
                "user": {
                    "id": 100001,
                    "name": "Admin",
                    "email": "admin@gmail.com",
                    "registered": 1516222442467,
                    "enabled": true,
                    "role": "ROLE_ADMIN",
                    "new": false
                },
                "date": [
                    2017,
                    6,
                    21
                ]
            },
            "restaurant": {
                "id": 100005,
                "name": "Nar-Sharab",
                "address": "Naberejnaya, 121",
                "cookery": "Caucasian",
                "new": false
            }
        },
        {
            "primaryKey": {
                "user": {
                    "id": 100001,
                    "name": "Admin",
                    "email": "admin@gmail.com",
                    "registered": 1516222442467,
                    "enabled": true,
                    "role": "ROLE_ADMIN",
                    "new": false
                },
                "date": [
                    2018,
                    5,
                    31
                ]
            },
            "restaurant": {
                "id": 100006,
                "name": "Svoya kompanya",
                "address": "Lesoparkovaya, 15",
                "cookery": "Russian",
                "new": false
            }
        }
    ]

**Request to count votes for this restaurant**

Request example:

`curl -s http://localhost:8080/rest/votes/restaurants/100005/count --user user@yandex.ru:password`

Response:

`3`

**Request to count all votes**

Request example:

`curl -s http://localhost:8080/rest/votes/countAll --user user@yandex.ru:password`

Response:

`8`

### 4.1. Admin's requests

**Request to get users decided to lunch this restaurant and date**

Request example:

`curl -s http://localhost:8080/rest/admin/votes/restaurants/100005/usersBy?date=2017-06-21 --user admin@gmail.com:admin`

Response:

    [
        {
            "id": 100000,
            "name": "User",
            "email": "user@yandex.ru",
            "registered": 1516222442467,
            "enabled": true,
            "role": "ROLE_USER",
            "new": false
        },
        {
            "id": 100001,
            "name": "Admin",
            "email": "admin@gmail.com",
            "registered": 1516222442467,
            "enabled": true,
            "role": "ROLE_ADMIN",
            "new": false
        },
        {
            "id": 100002,
            "name": "User_1",
            "email": "user@mail.ru",
            "registered": 1516222442468,
            "enabled": true,
            "role": "ROLE_USER",
            "new": false
        }
    ]
    
**Request to get votes for chosen restaurant between dates**

Request example:

`curl -s --user admin@gmail.com:admin "http://localhost:8080/rest/admin/votes/restaurants/100007/between?startDate=2017-10-10&endDate=2017-10-12"`

Response:

    [
        {
            "primaryKey": {
                "user": {
                    "id": 100000,
                    "name": "User",
                    "email": "user@yandex.ru",
                    "registered": 1516228451327,
                    "enabled": true,
                    "role": "ROLE_USER",
                    "new": false
                },
                "date": [
                    2017,
                    10,
                    11
                ]
            },
            "restaurant": {
                "id": 100007,
                "name": "Pizzburg",
                "address": "Prospect Lenina, 29",
                "cookery": "Italian",
                "new": false
            }
        },
        {
            "primaryKey": {
                "user": {
                    "id": 100002,
                    "name": "User_1",
                    "email": "user@mail.ru",
                    "registered": 1516228451327,
                    "enabled": true,
                    "role": "ROLE_USER",
                    "new": false
                },
                "date": [
                    2017,
                    10,
                    12
                ]
            },
            "restaurant": {
                "id": 100007,
                "name": "Pizzburg",
                "address": "Prospect Lenina, 29",
                "cookery": "Italian",
                "new": false
            }
        }
    ]

**Request to get votes for restaurant and date**

Request example:

`curl -s --user admin@gmail.com:admin "http://localhost:8080/rest/admin/votes/restaurants/100006/by?date=2018-05-31"`

Response:

    [
        {
            "primaryKey": {
                "user": {
                    "id": 100000,
                    "name": "User",
                    "email": "user@yandex.ru",
                    "registered": 1516228451327,
                    "enabled": true,
                    "role": "ROLE_USER",
                    "new": false
                },
                "date": [
                    2018,
                    5,
                    31
                ]
            },
            "restaurant": {
                "id": 100006,
                "name": "Svoya kompanya",
                "address": "Lesoparkovaya, 15",
                "cookery": "Russian",
                "new": false
            }
        },
        {
            "primaryKey": {
                "user": {
                    "id": 100001,
                    "name": "Admin",
                    "email": "admin@gmail.com",
                    "registered": 1516228451327,
                    "enabled": true,
                    "role": "ROLE_ADMIN",
                    "new": false
                },
                "date": [
                    2018,
                    5,
                    31
                ]
            },
            "restaurant": {
                "id": 100006,
                "name": "Svoya kompanya",
                "address": "Lesoparkovaya, 15",
                "cookery": "Russian",
                "new": false
            }
        }
    ]
