# Eksamensprojekt (Projectmanagement)
A projectmangement tool made for Alpha Solutions to help organize projects.


## Installation

1. Change application properties to match your SQL login details

Application.properties example:
```sh
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/projectmanagementdb
spring.datasource.username=root
spring.datasource.password=root
```

2. Run Docker by running runDocker.sh file
```bash
#!/usr/bin/env bash

cd $(git rev-parse --show-toplevel)
docker build  -f Dockerfile.java -t java-app .
docker build  -f Dockerfile.mysql -t mysql-server .
docker-compose up
```

## Screenshots
### Homepage
<img width="1066" alt="image" src="https://github.com/NichlasMau/EksamenProjekt/assets/113104513/f41ee08a-3170-46e3-8c28-f7d82881c0ca">

### Tasks
<img width="1062" alt="image" src="https://github.com/NichlasMau/EksamenProjekt/assets/113104513/44b2265e-90de-4c31-b2c5-b832ccfc8290">


## Authors

- [@Amir](https://www.github.com/Amirah2700)
- [@Dennis Loja Castillo](https://www.github.com/DennisLojaCastillo)
- [@Nichlas](https://www.github.com/NichlasMau)
- [@Samim S.](https://www.github.com/sami0880-kea)

