# 동시성 문제 해결해보기
✏️ 동시성 문제가 발생할 수 있는 상황을 가정해 여러 방법으로 해결해보기

다룰 방법
- Synchronized
- 비관락
- 낙관락
- 분산락

## 환경설정
| 💡 필요환경 : Docker, MySQL 설치된 환경

MySQL 콘솔로 접속해 테스트용 DB를 생성합니다.
```sql
CREATE DATABASE example;
```

Redis 이미지를 설치 후 실행합니다.

init.sh 파일 실행 >>
```shell
chmod +x ./init.sh
./init.sh
```
스크립트가 실행되지 않거나 컨테이너가 실행중이어서 오류가 발생한다면,
아래 명령어를 실행해주세요
```
chmod +x ./clean.sh
./clean.sh

docker pull redis
docker run --name redis -d -p 6379:6379 redis
```

추가로 DB 접속 정보를 resource/application-db.yml 파일을 생성해서 작성해주세요.
```yaml
spring:
  datasource:
    url: jdbc:mysql://{host}:{port}/{database}
    username: {username}
    password: {password}
```

## 테스트환경
- MacBook Pro 14 / macOS Ventura 13
- Java 11
- Spring Boot 2.7.x
- MySQL 8.0.30