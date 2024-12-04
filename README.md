# Danal Backend Developer Pre-assignment(다날 백엔드 개발자 사전 과제)

---

## 개발과제
대용량 Data를 DB에(무결성 보장) 입력하는 어플리케이션을 구현합니다.
(상세 : 공공데이터의 CSV 파일을 데이터베이스 MySQL 에 저장하는 배치어플리케이션을 Spring Batch 를 사용하여 개발합니다.)

### 요구사항
1. 배치어플리케이션으로 CSV 파일을 읽어 데이터베이스(MySQL)에 CSV의 데이터를 열을 구분하여 저장합니다.
2. 배치 작업의 진행상황과 발생한 오류를 추적할 수 있도록 로깅합니다.
3. 배치의 기능을 검증하기 위해 단위테스트를 작성합니다.

---

## 개발 Stack
- JDK 17
- Spring Framework 6.2.0
- Spring Batch 5.2.0
- mysql 8.0.40
- gradle 8.11.1

## 실행 방법
### 1. Database 설정
- Database와 User를 생성하고 권한을 부여합니다.
```
mysql > CREATE DATABASE danal;
mysql > CREATE USER danal@'%' IDENTIFIED BY 'danal';
mysql > GRANT ALL PRIVILEGES ON danal.* TO danal@'%';
```

### 2. 서버 실행
- 서버를 실행하게 되면 flyway를 통해 저장해둔 DDL이 실행되어 테이블이 생성됩니다.
- Batch DDL : **resources/db/migration/V1__batch-initial.sql** 입니다.
- Application DDL : **resources/db/migration/V2__application-initial.sql** 입니다.
- <span style="color:yellow;">**다운받은 CSV 데이터에 날짜 형식의 데이터가 올바르지 않은 경우(예: 2024-02-00) 많아 날짜 형식의 컬럼은 모두 VARCHAR 형식으로 생성하였습니다.**</span>

### 3. Batch 실행
- http://localhost:8080/batch/start 에 접속해 [Batch Start!] 버튼을 클릭하면 Job이 실행됩니다.

### 4. Log 정책
- Job, Step, Chunk 단위로 실행하며 Console log를 남깁니다.

### 5. Exception 정책
- 예외 발생 시 Retry나 Skip은 설정하지 않았습니다.
- 예외가 발생할 경우 Chunk 단위로 롤백이 되어 다시 배치를 실행했을때 해당 Chunk부터 다시 시작됩니다.
