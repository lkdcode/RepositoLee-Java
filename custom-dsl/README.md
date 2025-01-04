# 🎯 Custom DSL

*"한 번 쓰인 코드는 여러 번 읽힌다."*

한 번 작성된 코드는 여러 개발자들이 각각 여러 번 읽는다.  
잘 작성된 코드는 여러 번 읽는 것에 대한 부담이 덜하며 비즈니스 관점에서 소프트웨어가 제대로 동작하는지 쉽게 확인할 수 있다.  
혹은 개발자가 아니더라도 이해할 수 있는 수준의 코드를 작성하고 싶다면 DSL이 도움이 될 수 있다.  
개발 언어의 특징을 모르더라도 마치 자연처럼 고수준의 코드를 제공할 수 있으며  
DDD의 유비쿼터스 언어로 표현된 코드는 도메인 지식만 있다면 충분히 이해할 수 있는 수준을 제공한다.

읽기 쉬운 코드는 상대적으로 변경에 강하며 잘 추상화된 구조를 갖춘다. DSL은 크게 외부적 DSL과 내부적 DSL로 나뉜다.  
여기서 말하는 외부적 DSL 은 jOOQ, BDDMockito 등 JVM에서 실행되는 언어들이 있으며, 자바 StreamAPI 등이 대표적인 내부적 DSL 에 포함된다.

내부적 DSL 은 다음 특징 등을 가지고 있다.

- 저수준의 코드를 고수준으로
- 람다 표현식 및 메서드 참조를 통해 프로그램의 신호 대비 잡음 줄이기
- 개발팀과 도메인 전문가가 공유하는, 이해할 수 있는 코드로 작성
- 도메인 전용 언어로 애플리케이션의 비즈니스 로직을 표현함으로써 비즈니스 관점에서 소프트웨어가 제대로 되었는지 확인할 수 있음
- 동시에 오해와 버그 방지

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

# 🎯 Custom DSL

주어진 횟수만큼 문자열을 콘솔에 출력하는 메서드를 자바를 몰랐던 시절로 돌아가서 읽어보자.  

```java
for (int i = 0; i < count; i++) {
    System.out.println(message);
}
```

자바를 모르던 시절도 돌아가 저 코드를 처음보고 이해할 수 있는 사람은 없을 것이다. 각 구문들이 어떤 의미를 뜻하는지 해석하기는 쉽지 않다.  
위와 똑같은 기능을 수행하는 아래의 코드를 읽어보자.

- 🔗[참고: Example1](./src/main/java/dsl/task01/Example1.java)

```java
Example1
    .say("Hello, DSL!")
    .times(3)
    .print();
```

자바를 모르는 사람이라도 주어진 단어들이 어떤 의미를 뜻하는지 충분히 추론이 가능하다.  
마치 자연어처럼 읽히는 수준의 코드를 '고수준 코드'라 하며, 그와 반대는 '저수준 코드'라 한다.  

# 🎯 Custom DSL

🔗[동작 파라미터화](../funtional-interface/README.md) 를 통해 행위에 대한 추상화를 알아봤는데 이를 이용하면 더욱 유용한 DSL을 만들 수 있다.

## 🍅 토마토 애플리케이션

🔗[토마토](./src/main/java/dsl/task02/Tomato.java)를 CRUD 할 수 있는 아주 작은 애플리케이션을 만들 때 DSL이 어떻게 유용한지 알아보자.  
🔗[토마토](./src/main/java/dsl/task02/Tomato.java)는 3가지의 데이터를 `Enum` 타입으로 가지고 있으며
🔗[Color](./src/main/java/dsl/task02/Tomato.java), 🔗[Size](./src/main/java/dsl/task02/Tomato.java),
🔗[Usage](./src/main/java/dsl/task02/Tomato.java)가 있다.

- 🔗[Color](./src/main/java/dsl/task02/Tomato.java)의 종류: RED, GREEN, BLUE
- 🔗[Size](./src/main/java/dsl/task02/Tomato.java)의 종류: SMALL, MEDIUM, LARGE
- 🔗[Usage](./src/main/java/dsl/task02/Tomato.java)의 종류: [DANCER](https://www.youtube.com/watch?v=O1qt1oiixDc)(?), JUICE,
  KETCHUP

패키지 구조는 널리 다루는 `Controller`-`Service`-`Repository` 패턴으로 구성되어 있으며 각각의 역할은 다음과 같지만,  
(여기서 집중해야할 곳은 클라이언트 코드인 `TomatoService` 이다.)  

- 🔗[TomatoController](./src/main/java/dsl/task02/TomatoController.java) 클래스 :
  🔗[TomatoService](./src/main/java/dsl/task02/TomatoService.java)와 상호작용하며 필터링된 토마토를 콘솔에 출력
- 🔗[TomatoService](./src/main/java/dsl/task02/TomatoService.java) 클래스 :
  🔗[TomatoRepository](./src/main/java/dsl/task02/TomatoRepository.java)와 상호작용하며 조건별로 토마토 목록을 필터링
- 🔗[TomatoRepository](./src/main/java/dsl/task02/TomatoRepository.java) 클래스 : 토마토 목록을 가지고 있는 데이터 베이스

🔗[TomatoRepository](./src/main/java/dsl/task02/TomatoRepository.java)에 임시 데이터가 셋업이 됐으니 모든 리스트를 조회하고 필터링한다고 가정해보자.

댄서도 될 수 있는 토마토는 색상, 크기, 용도 3가지 타입에 대해 모두 필터링할 수 있어야 한다.  
아마 누구나 쉽게 이 요구사항에 대해서 다음과 같이 구현할 수 있을 것이다.  

- 🔗[TomatoService](./src/main/java/dsl/task02/TomatoService.java)의 일부 메서드들

```java
class TomatoService {
    public List<Tomato> findAll(Tomato.Size size) {
        List<Tomato> list = tomatoRepository.findAll();
        List<Tomato> result = new ArrayList<>();

        for (Tomato tomato : list) {
            if (tomato.size() == size) {
                result.add(tomato);
            }
        }

        return result;
    }

    public List<Tomato> findAll(Tomato.Color color, Tomato.Size size) {
        List<Tomato> list = tomatoRepository.findAll();
        List<Tomato> result = new ArrayList<>();

        for (Tomato tomato : list) {
            if (tomato.size() == size && tomato.color() == color) {
                result.add(tomato);
            }
        }

        return result;
    }
}
```

메서드 오버로딩을 통해 필터링이 가능한 데이터들을 모두 받는다면 메서드가 우후죽순 생길 것이다.  
또 위의 코드는 '저수준 코드'이며 프로그램 언어대한 지식이 필요하다.  
만약 테이블에서 데이터를 조회하듯 'SELECT * FROM TOMATO WHERE COLOR = 'BLUE'' 등과 같은 표현식이 더 좋다고 생각했다면,  
외부 DSL을 떠올린 것이며 프로그램의 신호 대비 잡음을 효과적으로 줄일 수 있을 것이다.  
(또 프로그램에 대한 전문 지식이 없더라도 읽힐 수 있다.)

위의 형태를 읽기 쉽게 개선해보자.

- 🔗[TomatoService](./src/main/java/dsl/task02/TomatoService.java)의 일부 메서드들

```java
class TomatoService {
    public List<Tomato> findAll(Tomato.Size size, Tomato.Usage usage) {
        return tomatoRepository.findAll()
            .stream()
            .filter(tomato -> tomato.size() == size && tomato.usage() == usage)
            .toList();
    }

    public List<Tomato> findAll(Tomato.Color color, Tomato.Size size, Tomato.Usage usage) {
        return tomatoRepository.findAll()
            .stream()
            .filter(tomato -> tomato.color() == color)
            .filter(tomato -> tomato.size() == size)
            .filter(tomato -> tomato.usage() == usage)
            .toList();
    }
}
```

아까 보다는 확실히 나아졌다. 프로그램 전문가가 아니더라도 위의 코드는 어떻게 동작하는지 유추할 수 있다.    
'무언가를 *`findAll()` 모두 찾고* *`filter()` 필터링* 한다. 그리고 *`toList` 목록* 형식이다.'

아까보다는 더 나은 표현식으로 특정 메서드들의 정확한 동작을 몰라도 자연어처럼 읽히는 수준에 도달했지만 많은 메서드가 생기는 것까진 막을 수 없을 것이다.  
이후 동작 파라미터화를 통해 행동을 매개변수로 필터링할 조건들을 받는다면 더 개선할 수 있다.

```java
class TomatoService {
    @SafeVarargs
    public final List<Tomato> findAllFilterAll(Predicate<Tomato>... predicates) {
        return tomatoRepository.findAll()
            .stream()
            .filter(combinedAll(predicates))
            .toList();
    }

    @SafeVarargs
    public final List<Tomato> findAllFilterAny(Predicate<Tomato>... predicates) {
        return tomatoRepository.findAll()
            .stream()
            .filter(combinedAny(predicates))
            .toList();
    }

    @SafeVarargs
    private static Predicate<Tomato> combinedAll(Predicate<Tomato>... predicates) {
        return Stream.of(predicates)
            .reduce(Predicate::and)
            .orElse(t -> false);
    }

    @SafeVarargs
    private static Predicate<Tomato> combinedAny(Predicate<Tomato>... predicates) {
        return Stream.of(predicates)
            .reduce(Predicate::or)
            .orElse(t -> false);
    }
}
```

클라이언트는 다음과 같이 호출하여 사용할 수 있다.

```java
class Sample {
    public void 빨간색이거나_파란색_토마토_목록() {
        tomatoService.findAllFilterAny(
            tomato -> tomato.color() == Tomato.Color.RED,
            tomato -> tomato.color() == Tomato.Color.BLUE
        );
    }

    public void 빨간색이면서_작은_사이즈의_토마토_목록() {
        tomatoService.findAllFilterAny(
            tomato -> tomato.color() == Tomato.Color.RED,
            tomato -> tomato.size() == Tomato.Size.SMALL
        );
    }
}
```

여기까지는 더 나은 DSL을 만들기 위한 동작 파라미터화에 대한 이야기이다.  
DSL 은 동작 파라미터화와 빌더 패턴을 사용하면 더 친화적인 DSL 을 만들 수 있다.

# 🎯 Custom DSL

복잡한 도메인이 아닌 단순한 CRUD 오퍼레이션에서도 DSL은 큰 이점을 제공하는데 일반적으로 CRUD 오퍼레이션에는 검증 과정이 필수로 들어간다.  
다른 계층에서도 충분히 처리할 수 있겠지만 이번 예시에서는 계층형 아키텍처의 `Service` 에서만 수행한다는 가정하에 아래의 요구 사항을 살펴보자.

- 토마토는 데이터베이스에 저장할 수 있다.
- 단, 토마토는 익명 사용자는 토마토를 생성할 수 없다.
- 단, 사용자의 이름이 5글자를 초과하면 생성할 수 없다.
- 단, 사용자의 이름이 'Dancer' 인 경우 생성할 수 없다. (대,소문자는 구분하지 않는다.)
- 단, 토마토의 타입은 항상 'Dancer' 여야만 생성할 수 있다.

댄서가 될 수 있는 토마토는 위의 4가지 조건들을 모두 통과해야만 데이터베이스에 저장할 수 있다. 하나라도 어길경우 생성할 수 없다.  
위와 같은 요구 사항에 대해서 간단하게 구현할 수 있다.

사용자를 표현한 🔗[Users](./src/main/java/dsl/task03/Users.java) 클래스가 새로 추가되었다. (이름과 타입을 가지고 있음)  

- 🔗[task03.TomatoService](./src/main/java/dsl/task03/TomatoService.java) 클래스의 일부 모습

```java
class TomatoService {
    public void persist(Tomato tomato, Users users) {
        if (users.type() == Users.Type.ANONYMOUS) {
            throw new IllegalArgumentException("Invalid Authority!");
        }

        if (users.username().toUpperCase().contains("DANCER")) {
            throw new IllegalArgumentException("Invalid username! 'DANCER' is only for tomatoes!");
        }

        if (users.username().length() > 5) {
            throw new IllegalArgumentException("Invalid username length!");
        }

        if (tomato.usage() != Tomato.Usage.DANCER) {
            throw new IllegalArgumentException("Invalid tomato usage");
        }

        tomatoRepository.save(tomato, users);
    }
}
```

동작하는 애플리케이션은 항상 옳다. 하지만 우리는 오늘은 동작하고 내일은 수정할 수 있어야 한다.  

요구사항에 명시된 유효성 검사에 대해 실패한 경우 익셉션을 발생시키며 토마토는 결국 저장되지 않는다.  
저수준의 코드는 읽기도 부담스러우며 재활용하거나 확장하는 등 유지보수 측면에서 좋지 않다.  

- 요구사항의 변경으로 인해 새로운 유효성 검사가 추가되면 어떻게 할 것인가?  
- `BananaService` 라는 클래스가 존재하고 그곳에서도 유저에 대한 타입 검증을 한다면 어떻게 할 것인가?  

상상력을 넓혀 프로그래밍하는 것은 좋지 않지만 필연적으로 다가올 미래에 대한 대비책은 어느정도 마련해볼 수 있다.  

- 🔗[task03.TomatoCustomDsl](./src/main/java/dsl/task03/TomatoCustomDsl.java) 클래스의 일부 모습

```java
class TomatoCustomDsl {
    public TomatoCustomDsl validUsers(Predicate<Users> condition) {
        usersConditionList.add(condition);
        return this;
    }

    public void save(BiConsumer<Tomato, Users> target) {
        boolean valid = usersConditionList.stream().allMatch(condition -> condition.test(users))
            && tomatoConditionList.stream().allMatch(condition -> condition.test(tomato));

        if (!valid) {
            throw new IllegalArgumentException("INVALID!!!!!!!");
        }

        target.accept(tomato, users);
    }
}
```

🔗[task03.TomatoCustomDsl](./src/main/java/dsl/task03/TomatoCustomDsl.java) 클래스는 정적 생성자와 메서드 체인으로 구성되어 있으며 유창함을 의미하는 플루언트 스타일이다.  
`List<Predicate<Users>> usersConditionList` 로 유저에 대한 유효성 검사 항목 리스트를 가지고 있으며 클라이언트가 자유롭게 추가할 수 있다.  
토마토 유효성 검사도 마찬가지이며 모든 유효성 검사가 통과되면 토마토를 저장하게 된다.  
이또한 외부 넘기는(동작파라미터화) `.save(BiConsumer<Tomato, Users> target)` 메서드를 통해 수행된다.  

DSL은 비교적 설계가 어려우며 구현에 있어 비용이 든다. 인터페이스와 추상화가 많이 적용되어 있으니 이러한 구조가 낯설다면 라인 수는 적더라도 읽기 어렵다.  
하지만 이를 감수할 만한 장점들이 많다.  

간결함, 가동성, 유지보수, 높은 수준의 추상화 집중, 관심사 분리 등이 있고 클라이언트 입장에서 더 친화적이다.  
아래의 코드는 클라이언트가 사용하는 코드이다.  

- 🔗[task03.TomatoService](./src/main/java/dsl/task03/TomatoService.java) 클래스의 일부 모습

```java
class TomatoService {
    public void persisDsl(Tomato tomato, Users users) {
        TomatoCustomDsl.action(tomato, users)
            .validUsers(checkType())
            .validUsers(checkUsernameLength())
            .validUsers(checkUsername())
            .validTomato(checkTomatoUsage())
            .save(tomatoRepository::save);
    }
    
    private static Predicate<Tomato> checkTomatoUsage() {
        return tomato -> tomato.usage() == Tomato.Usage.DANCER;
    }
}
```

요구사항과 비교하며 코드를 읽는다면 그 요구사항에 명시된 그대로 읽히며 만약 누락되더라도 바로 찾을 수 있을 것이다. 프로그램 대비 잡음도 굉장히 줄었다.  

가령 `Service` 계층에선 메서드 바디에 특정 변수를 할당하고 그 변수를 매개변수로 넘기며 사용하고 최종적으로 다른 타입의 클래스를 리턴하며 종료되는 모습이 많은데,  
최종적으로 A 변수는 프로그램 잡음 중 하나에 포함될 수도 있다.  

- `result` 를 얻기 위해 `aValue, bValue` 를 읽는 것은 프로그램 잡음일 수도 있다. (매개변수로 바로 넘기자는 뜻이 아니다.)

```java
public class SomeClazz {
    public SomeResponse apply() {
        var aValue = AService.getSomeValue();
        var bValue = BService.getSomeValue(aValue);
        var result = CService.getSomeValue(aValue, bValue);
        return SomeResponse.from(result);
    }
}
```

다시 돌아가서 위의 DSL 을 사용하여 행위에 대한 추상화 목록을 만들고 외부에서 자유롭게 사용할 수 있으니 다양한 요구 사항을 수용할 수 있는 클래스를 만든셈이다.  
🔗[Users](./src/main/java/dsl/task03/Users.java) 타입이라면, 혹은 🔗[Tomato](./src/main/java/dsl/task03/Tomato.java) 타입이라면 다양한 유효성 검사를 자유롭게 추가할 수 있다.  

다만 조금은 암묵적 명시가 필요한데, `java.util`에 있는(아니더라도) 함수형 인터페이스만을 사용하므로 그 행위가 꼭 유효성 검사가 아니어도 DSL 입장에선 알 수 없다.  
이는 메서드의 분명한 목적 또는 명시적인 함수형 인터페이스를 새로 만들거나 조직, 팀 단위의 소통으로 해결할 수 있다.  
이미 만들어 놓은 기본 함수형 인터페이스만으로도 충분히 해결할 수 있기에 기본 함수형 인터페이스(`java.util`)를 권고하지만,  
재활용성이 높거나 명시적으로 사용하고 싶다면 새로운 함수형 인터페이스를 만들어도 무방하다.  

그러나 위의 코드도 약간의 문제가 있다.  
🔗[task03.TomatoService](./src/main/java/dsl/task03/TomatoService.java) 클래스는 내부 정적 메서드(`checkTomatoUsage()` 등)를 직접 가지고 있는 것으로 구현됐다.  
이는 위에서 본 두 문제에 대해서는 해결하지 못한다.  

- 요구사항의 변경으로 인해 새로운 유효성 검사가 추가되면 어떻게 할 것인가?  
- `BananaService` 라는 클래스가 존재하고 그곳에서도 유저에 대한 타입 검증을 한다면 어떻게 할 것인가?  

새로운 정적 메서드를 포함시키는 것으로 해결이 가능한가? 재활용성에서 어떻게 할 것인가?  

작은 라인의 코드는 읽기 부담이 덜하다.  
하나의 작은 책임들을 가지고 있는 클래스 및 메서드들로 분리하면 재활용성과 가독성, 최종적으로 더 자유로운 구조로 설계가 가능하다.  
🔗[task04.TomatoUserValidator](./src/main/java/dsl/task04/TomatoUserValidator.java) 인터페이스와 🔗[task04.TomatoValidator](./src/main/java/dsl/task04/TomatoValidator.java) 인터페이스가 추가되었다.  

유효성 검사에 대한 행위들을 모두 가지고 있으며 각각 다른 식으로 풀어냈는데 기존 DSL에서 사용하는데 전혀 문제가 없다.

- 구현체인 🔗[task04.TomatoUserValidatorImpl](./src/main/java/dsl/task04/TomatoUserValidatorImpl.java) 클래스의 일부 모습

```java
class TomatoUserValidatorImpl implements TomatoUserValidator {
    @Override
    public Consumer<Users> checkType() {
        return users -> {
            if (users.type() == Users.Type.ANONYMOUS) {
                throw new IllegalArgumentException("Invalid Authority!");
            }
        };
    }
}
```

- 구현체인 🔗[task04.TomatoValidatorImpl](./src/main/java/dsl/task04/TomatoValidatorImpl.java) 클래스의 일부 모습

```java
class TomatoValidatorImpl implements TomatoValidator {

    @Override
    public void checkTomatoUsage(Tomato tomato) {
        if (tomato.usage() != Tomato.Usage.DANCER) {
            throw new IllegalArgumentException("Invalid tomato usage");
        }
    }
}
```

두 구현체는 함수형 인터페이스를 구성하거나 혹은 매개변수로 받아서 처리하는데 클라이언트 코드에서 어떻게 사용되는지 살펴보자.


- 클라이언트 코드인 🔗[task04.TomatoService](./src/main/java/dsl/task04/TomatoService.java) 클래스의 일부 모습

```java
class TomatoService {
    public void persistDsl(Tomato tomato, Users users) {
        TomatoCustomDsl.action(tomato, users)
            .validUsers(tomatoUserValidator.checkType())
            .validUsers(tomatoUserValidator.checkUsername())
            .validUsers(tomatoUserValidator.checkUsernameLength())
            .validTomato(tomatoValidator::checkTomatoUsage)
            .save(tomatoRepository::save);
    }
}
```

람다 표현식은 익명 클래스보다 간결하지만 매개변수가 조금이라도 많아지면 읽기 부담스러운 것이 사실이다.  
이러한 문제는 메서드 참조를 통해 더 읽기 쉬운 코드로 바꿔준다.  

각각의 인터페이스로 분리하고 그 구현체들이 작은 몸집을 유지한다면 다른 도메인 혹은 다른 계층에서 사용하기 훨씬 쉬울 것이다.  
단위 테스트 또한 굉장히 유리하며 읽기와 변경에 부담스럽지 않다. 만약 요구 사항이 변경돼서 유효성 검사의 순서를 바꾸거나 제거한다면 어떻게 할 것인가?  

```java
class TomatoService {
    public void persistDsl(Tomato tomato, Users users) {
        TomatoCustomDsl.action(tomato, users)
            .validTomato(tomatoValidator::checkTomatoUsage) // 순서를 바꾸거나.
            .validUsers(tomatoUserValidator.checkUsernameLength())
            .validUsers(tomatoUserValidator.checkType())
//            .validUsers(tomatoUserValidator.checkUsername()) 제거하거나. 
            .save(tomatoRepository::save);
    }
}
```

위의 구조는 헥사고날 아키텍처의 포트와 어댑터를 떠올릴 수 있다. 동작 파라미터화랑 결합된 DSL은 여러 포트들을 수용할 수 있는 어댑터의 역할을 하며,  
그 구현체들은 추상화된 인터페이스로 마치 포트처럼 결합하게 된다.  

다른 `Banana` 어댑터가 있다면 `Tomato` 와 관련된 포트들을 사용할 수 있기에 이는 좋은 구조라 할 수 있다.  
또 포트에 해당하는 인터페이스의 메서드들이 DSL 메서드의 시그니처를 꼭 일치시킬 필요가 없는데, 이는 람다 표현식으로 풀어낼 수 있기 때문이다.  

```java
class AnotherTomatoService {
    public void persistDsl(Tomato tomato, Users users, SomeClazz someClazz) {
        TomatoCustomDsl.action(tomato, users)
            .validUsers(target -> {
              /* 
                 만약 someClazz 가 필요하다면 TomatoCustomDsl 을 수정할 필요가 없다.
                 람다 표현식의 바디를 열어서
                 someClazz 를 사용해 수행하면 된다.
              */
                someValidator.checkSome(target, someClazz);
            })
            .save(tomatoRepository::save);
    }
}
```

동작파라미터화와 메서드 참조를 통한 DSL은 도메인을 잘 표현할 수 있으며 변경과 확장에 모두 강하다.  
다만 구조에 대한 이해가 필요하며 초급 수준의 형태가 아니므로 초반 설계에 대해 장벽이 있을 수 있다.  
한 책에서는 빌더 패턴만으로 구현해야 하는 것에 단점으로 꼽았는데 이는 상위 수준의 빌더와 하위 수준의 빌더를 결합하는 많은 접착 코드가 필요하므로 여러 클래스들을 복합적으로 사용하는 것에 한정된다.  
