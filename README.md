# jpa 연습용 프로젝트

- jpa, querydsl 사용

## 메모

### 1. swagger

> swagger link : [swagger-ui.html](http://localhost:9000/swagger-ui/index.html)

### error report

> org.springframework.context.ApplicationContextException: Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException

1. 스프링 부트 2.6 이상 버전은 swagger2 와 호환되지 않음

2. Spring boot 2.6버전 이후에 spring.mvc.pathmatch.matching-strategy 값이 ant_apth_matcher에서 path_pattern_parser로 변경되면서 몇몇 라이브러리(swagger포함)에 오류가 발생

```yml
spring:
  mvc:
    pathmatch:
      matching-strategy: ant-path-matcher
```
