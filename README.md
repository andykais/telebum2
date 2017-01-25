## Web-Client & Web-Api
to install packages, use `shell.sh`. A weird issue is that webpack cannot detect a new
packages __after__ you require it in a file given it is not already installed. Therefore, when
adding new packages follow this order:
1. ./shell [service]
2. shell$ yarn add package
3. _Now_ import package to file 
