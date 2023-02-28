import os

os.system("docker build . -t CacheDemo-service")
os.system("docker-compose -f docker-compose.yaml up")