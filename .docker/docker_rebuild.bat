cd ..

CALL mvn clean install -DskipTests

docker rmi -f docker-rest-coingoal

cd .\.docker\

docker compose up
