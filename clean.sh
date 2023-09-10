docker stop $(docker ps -qa)
docker rm $(docker ps -qa)
docker rmi $(docker images -q)