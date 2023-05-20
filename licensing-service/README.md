# Licensing Service
Docker Commands
docker build -t licensing-service .     
docker run --name LicensingService1 --detach --publish 8080:8080 licensing-service