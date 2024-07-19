docker rm -vf $(docker ps -aq)
docker rmi -f $(docker images -aq)
./gradlew clean build && docker-compose up -d
