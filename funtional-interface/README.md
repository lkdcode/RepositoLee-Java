# 🎯 동작 파라미터화

# ✅ 시작에 앞서

환경은 아래와 같습니다.

- Java: 21
- Launcher JVM:  21.0.5 (Amazon.com Inc. 21.0.5+11-LTS)

```shell
$ java -version

> openjdk version "21.0.5" 2024-10-15 LTS
> OpenJDK Runtime Environment Corretto-21.0.5.11.1 (build 21.0.5+11-LTS)
> OpenJDK 64-Bit Server VM Corretto-21.0.5.11.1 (build 21.0.5+11-LTS, mixed mode, sharing)
```

모든 코드는 컴파일 및 런타임에 문제없이 동작합니다. 버전 정보, 환경 설정 등을 확인해주세요.

# 🎯 요구사항

주어진 🔗[BananaList.java](./src/main/java/funtional/BananaList.java) 목록을 원하는 조건에 맞춰 필터링하세요.

- 과일 바나나 🔗[Banana.java](./src/main/java/funtional/Banana.java)
- 바나나의 색상 🔗[Color.java](./src/main/java/funtional/Color.java)
- 바나나의 무게 🔗[Weight.java](./src/main/java/funtional/Weight.java)

Q. '빨간색 바나나만 찾고 싶어요.'  
Q. '초록색 바나나만 찾고 싶어요.'  
Q. '무거운 바나나만 찾고 싶어요.'  
Q. '가벼운 바나나만 찾고 싶어요.'  
Q. 'x 조건을 만족하거나 y 조건을 만족하는 바나나만 찾고 싶어요.'  
Q. 'x 조건을 만족하고 y 조건을 만족하고 z 조건을 만족하는 바나나만 찾고 싶어요.'  
Q. 'n 개의 조건을 만족하는 바나나만 찾고 싶어요.'  

# 🎯동작 파라미터화 코드 전달하기

객체지향 패러다임에서는 추상화가 중요하다. 한 문맥에서 대체가 가능한 여러 책임들의 집합을 역할로 표현하는 것도 하나의 추상화이며 추상화는 변하지 않는 그 무언가를 찾기 위함이다.  
이것은 객체뿐만아니라 행위 혹은 데이터 등 더 작은 개념에서도 추상화가 가능하며 자바에서 가장 먼저 생각나는건 추상 클래스 혹은 인터페이스일 것이다.

올림픽을 떠올리면 여러 운동 종목이 존재한다. 탁구, 달리기, 양궁, 수영 등은 구체적인 종목에 해당하고 이들을 '구기종목', '육상종목', '투기종목' 등 하나의 카테고리로 묶을 수 있다.  
구기종목에 포함되는 스포츠들은 축구, 야구, 농구, 배구 등이 있을 것이다. 이들은 모두 공에 대한 행위(차거나, 던지거나 등)가 있는데 '슛'이라는 개념으로 추상화 할 수 있다.

이처럼 행위에 대한 추상화를 Java8.0 에 도입된 동작 파라미터화로 표현할 수 있다. 이는 변경 사항 및 새로운 기능 추가에 대해 빠르게(혹은 쉽게) 구현할 수 있으며 장기적인 관점에서 유지보수가 비교적 쉬울
것이다.  
객체지향 패러다임에서는 시점에 따라 대체되는 여러 책임들을 역할로 추상화하여 다양한 요구 사항에 대해 대처하는데 하나의 작은 행위도 마찬가지다.

동작 파라미터화는 행위를 추상화하는 것이다.

# 🎯Solution

주어진 요구 사항은 바나나 목록을 필터링하는 것이다. 우선 빨간색 바나나를 필터링하는 코드는 아래와 같이 작성할 수 있다.  

- 🔗[바나나 찾기(for문)](./src/main/java/funtional/task01/BananaFilterService.java)

```java
public List<Banana> filterRedBananas(List<Banana> list) {
    List<Banana> result = new ArrayList<>();

    for (Banana banana : list) {
        if (banana.color().equals(Color.RED)) {
            result.add(banana);
        }
    }

    return result;
}
```

스트림으로 표현한다면 아래와 같이 

- 🔗[바나나 찾기(Stream)](./src/main/java/funtional/task02/BananaFilterService.java)

```java
public List<Banana> filterRedBananas(List<Banana> list) {
    return list.stream()
        .filter(banana -> banana.color().equals(Color.RED))
        .toList();
}
```

🔗[바나나 찾기(for문)](./src/main/java/funtional/task01/BananaFilterService.java), 🔗[바나나 찾기(Stream)](./src/main/java/funtional/task02/BananaFilterService.java) 두 클래스는 각각 하나의 조건만 만족하는 메서드들을 4개 들고 있다.    
새로운 조건이 계속 추가되거나 기존 조건이 변경된다면 유연하게 대처하기는 힘들 것이다.  

- 새로운 메서드를 추가할 것인가?
- 기존의 메서드를 수정할 것인가?

모두 기존의 클래스가 너무 비대해지거나 삭제된 기존 메서드를 추후에 다시 추가하거나 하는 등의 문제가 발생할 수 있다.  
동작 파라미터화란 아직 어떻게 실행될지 결정되지 않은 코드 블록을 의미한다. 행위를 추상화한 것으로 행위를 수행하되 그 행위가 어떤 행위인지 나중으로 미뤄 실행하는 것이다.  

위의 4가지 필터링은 바나나의 조건이 구체적이다.  

- 빨간색 바나나
- 초록색 바나나
- 무거운 바나나
- 가벼운 바나나

여기서 중요한 행위는 '어떤 조건'으로 바나나 목록을 필터링할 것이냐다.  
어떤 조건을 외부에서 매개변수로 받으면 어떻게 될까? n개의 조건을 수행할 수 있게 되는 좀 더 추상적인 메서드로 탄생할 것이다.  

- 🔗[바나나 찾기(동작 파라미터화)](./src/main/java/funtional/task03/BananaFilterService.java)

```java
public List<Banana> filteredBananas(List<Banana> list, Predicate<Banana> condition) {
    return list.stream()
        .filter(condition)
        .toList();
}
```

`Predicate<T> condition` 은 조건을 추상화한 매개변수이다. 먼저 아래에서 사용법을 보자.  

- 🔗[동작 파라미터화 사용](./src/main/java/funtional/task03/Application.java)

```java
List<Banana> redBananas = bananaFilterService.filteredBananas(list, banana -> banana.color().equals(Color.RED));
List<Banana> greenBananas = bananaFilterService.filteredBananas(list, banana -> banana.color().equals(Color.GREEN));
List<Banana> heavyBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.HEAVY));
List<Banana> lightBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.LIGHT));
```

이처럼 외부에서 '어떤 행위' 여기서는 바나나 필터 조건을 매개변수로 넘겨줌으로써 다양한 동작에 대처할 수 있게 된다.  
2개 이상의 족언을 `and` 로 만족하거나 `or` 로 만족하는 나머지 요구사항에 대해서도 매개변수로 넘겨주면 기존의 코드를 수정하지 않아도 대처가 가능하다.  

```java
List<Banana> lightAndRedBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.LIGHT) && banana.color().equals(Color.RED));
List<Banana> heavyAndRedBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.HEAVY) && banana.color().equals(Color.RED));
List<Banana> lightAndGreenBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.LIGHT) && banana.color().equals(Color.GREEN));
List<Banana> heavyAndGreenBananas = bananaFilterService.filteredBananas(list, banana -> banana.weight().equals(Weight.HEAVY) && banana.color().equals(Color.GREEN));
```

# 🎯 함수형 인터페이스

`Predicate<T> condition` 은 `package java.util.function` 에 기본적으로 포함되어있는 함수형 인터페이스이다.  

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

매개변수로 받는 제네릭한 타입이 무엇이든간에 `.test()` 메서드를 수행하고나면 boolean을 리턴해야 한다.  
이를 매개변수로 받는 `.stream().fileter()` 메서드는 행위에 대한 추상화가 존재한다고 볼 수 있다.  

한 개의 파라미터로 다양한 동작을 수행하게 도와주는데 이것은 동작 파라미터화의 강점이다. 익명 클래스로 구현하는 것보다는 람다 표현식이나 메서드 참조로 표현하는 것이 더 간결하다.  
하나의 추상 메서드를 가진 인터페이스는 모두 함수형 인터페이스에 해당되며 `static`, `default` 메서드는 포함되지 않는다.  

특별한 이유(도메인 계층에서 명세가 필요하거나, 명시적이어야 하거나 등)가 아니라면 기본적으로 제공되는 함수형 인터페이스를 사용하는 것이 좋으며,  
원시 타입에 특화된 별도의 함수형 인터페이스도 존재므로 필요에 따라 `package java.util.function` 목록을 확인하는 것도 방법이다.  

- 🔗[람다, 메서드 참조](./src/main/java/funtional/task03/Application.java)

```java
private static boolean greenCondition(Banana banana) {
    return banana.color().equals(Color.GREEN);
}

private static Predicate<Banana> redCondition() {
    return banana -> banana.color().equals(Color.RED);
}

private static Predicate<Banana> heavyAndGreenCondition() {
    return banana -> banana.weight().equals(Weight.HEAVY) && banana.color().equals(Color.GREEN);
}
```

람다 표현식 및 메서드 참조는 명시적인 메서드명을 참조함으로써 가독성을 높일 수 있다.  
🔗[람다, 메서드 참조](./src/main/java/funtional/task03/Application.java) 참고하면 각각 어떤 상황에 쓰이는지 알 수 있다.