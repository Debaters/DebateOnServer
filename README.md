# DebateOnServer

## 빌드 

```
// 빌드 / 실행
./gradlew bootRun


// 빌드만 
./gradlew bootJar


// docker build 
docker build .
```
> 모든 자동/수동 배포에는 application.properties 가 필요하므로, 관리자에게 문의! 
## 자동 배포 과정 

github action을 통해, main 에 새로운 커밋이 있으면 업데이트됩니다. 

lightsail 에 설치한 watchtower image 에서 정기적으로 새 이미지를 보고 업데이트함.

## 수동 배포 

1. 로컬에서 스프링 프로젝트 빌드 `./gradlew build`
2. 도커 빌드 `docker build .`
3. github packages 에 푸시 (별첨)
4. lightsail 인스턴스 접속, docker image pull 을 이용해서 새로운 이미지 pull & run 

### docker package github에 업로드 하는법

https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-container-registry

