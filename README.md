# Clearsoul2
Clearsoul2님을 위한 깃허브


## 스프링부트 프로젝트 만들기
###Spring Initializr로 프로젝트 생성
1) https://start.spring.io/
2) 옵션선택
- Project: Gradle Project 선택
- Language: Java 선택
- Spring Boot: 2.2.5버전(아무버전이나 선택해도 무방함)
- Project Metadata <br>
-- Group: me.clearsoul2<br>
-- Artifact:(입력하고 싶은 프로젝트 명)<br>
-- Options 클릭시 메타정보를 추가적으로 입력할 수 있어요<br>
--- Description: 프로젝트에 대한 상세정보<br>
--- Package name: Group + Artifact 합친 결과<br>
--- packaging: 빌드 후 결과물 (jar, war)<br>
jar, war의 차이는 https://j-a-l.tistory.com/79 <br>
--- java: java 버전<br>
- Dependencies: 의존성(사용하고 싶은 라이브러리를 입력하면됨) - Spring Web 추가

3) 하단에 Generate 클릭하면 프로젝트가 생성됨

## 스프링부트 Intellij로 import 하기
1) Spring Initializr에서 다운받은 파일을 압축풀고 원하는 경로로 이동시켜요
2) Intellij 프로그램을 실행시키고 나서 상단메뉴중 [File] -> [Open] 클릭후 다운받아서 압축해제한 폴더 경로를 입력해요
3) 프로젝트 Import되면 Intellij 화면이 뜨고, 오른쪽 하단에 gradle import하라고 메세지가 나와요 import를 클릭하세요
4) 하단에 import 메세지가 나오지 않았다면 아래 그림과 같이 오른쪽 Gradle탭 메뉴 클릭후 리플레쉬 아이콘을 클릭해주세요
5) import 단계에서 오류가 발생하면... 복잡해지는데, 1) Jdk 설정이 잘되었는지, Gradle 설정이 잘되어 있는지 확인해보세요
<br> 해당 내용은 직접 물어보는게 나을것 같네요

## Spring boot 웹서버 실행하기
1) src/main/java/me.clearsoul2.springboot 경로를 열어봐요(Intellij에서) 열면 ServletInitializer, SpringbootApplication 클래스 파일이 생성 되어있는 것을 확인할 수 있을거에요
2) me.clearsoul2.springboot에 마우스 우측 클릭후 package 생성을 클릭하고 package 명을 controller로 만들어요
3) controller package에 우측 클릭하고 java 파일 생성으로 하고 이름을 ClearSoul2Controller라고 만드세요
4) 아래 코드처럼 입력하세요
<pre><code>
package me.clearsoul2.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClearSoul2Controller {

    @GetMapping("/clearsoul2/test")
    public Map<String, Object> test(){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("결과값", "안녕 장수인");
        return returnMap;
    }

    @GetMapping("/clearsoul2/test2/{param}")
    public Map<String, Object> test2(@PathVariable("param") String param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("결과값", "안녕 " + param);
        return returnMap;
    }

}

</code></pre> 
5) SpringbootApplication을 오른쪽 클릭하고 Run 'SpringbootApplication' 클릭하여 웹서버를 실행시켜요 
6) 웹브라우저에 http://localhost:8080/clearsoul2/test 또는 http://localhost:8080/clearsoul2/test2/이름 넣으시면 결과값을 볼 수 있어요


## swagger연동하기
1) build.gradle 파일을 열어요.
2) 다음과 같이 두 줄을 복사붙여넣기 해요
<pre><code>
compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
</code></pre> 
3) me.clearsoul2.springboot 패키지 오른쪽 클릭 후 클래스(Java) 파일을 생성해요 이름은 SwaggerConfig
4) 내용은 다음과 같아요<br>
<pre><code>
package me.clearsoul2.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/clearsoul2/**"))
                .build();
    }
}


</code></pre> 
5) springboot를 실행해요
6) http://localhost:8080/swagger-ui.html 클릭하면 swagger 화면을 볼 수 있어요

## 하다가 막히면
제가 올린 github clone해서 다운받아서 비교해보세요
