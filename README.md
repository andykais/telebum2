# Requirements
- [docker](https://www.docker.com/products/docker)
- [docker-compose](https://docs.docker.com/compose/install/)

make sure docker group is added (so docker can open ports without sudo)
```
sudo groupadd docker
sudo gpasswd -a ${USER} docker
sudo systemctl restart docker
```

# Setup
add [wait-for-it](https://github.com/vishnubob/wait-for-it) submodule
```bash
git submodule init
git submodule update --recursive
```

# Running
```bash
# init databases and install node modules (only necessary during setup)
./install.sh
# start the server in development mode
./start.sh dev

# enter into any running container in this project
./shell.sh [service_name]
```

## Gotchas
##### Web-Client & Web-Api
To install packages, use `shell.sh`. A weird issue is that webpack cannot detect a new
packages _after_ you require it in a file given it is not already installed. Therefore, when
adding new packages follow this order:
1. ./shell [service]
2. shell$ yarn add package
3. _now_ import package to file 
