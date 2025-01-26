# 🎯 var 키워드

Java 10에서 도입된 `var` 키워드는 로컬 변수의 타입을 컴파일러가 자동으로 추론하는 기능이다.  
명시적으로 타입을 선언하지 않아도 컴파일러가 변수의 타입을 추론할 수 있는데 반복적인 타입 선언을 해결해 줄 수 있다.  

특히 자료구조나 제네릭 타입에 대한 타입 선언이 장황하게 느껴진다면 고려해볼만하다.  
하지만 오히려 코드의 타입을 추론하기 어렵다는 단점도 있으므로 트레이드오프 하여 결정하되 조직의 컨벤션을 최우선으로 해야한다.  

- `var` 키워드를 사용한 모습이다.

```java
var name = "Java"; // String
var age = 25; // int
var list = new ArrayList<String>(); // List<String>
```

타입 추론은 코틀린, 스칼라, 타입스크립트 등과 같은 프로그래밍 언어에서 이미 일반적으로 제공되는 기능이며,  
이는 개발자가 비즈니스 로직에 좀 더 집중할 수 있도록 도와준다. 예를 들어  

중첩된 자료구조를 리턴하는 🔗[QueryService.java](./src/main/java/task01/QueryService.java) 와 해당 자료구조의 총합을 구하는 🔗[CommandService.java](./src/main/java/task01/CommandService.java) 가 있고,  
이의 결과가 짝수인지 홀수인지 판단하는 🔗[SomeService.java](./src/main/java/task01/SomeService.java) 가 있다고 가정해보자.  

중첩된 자료구조는 `Map<String, Map<String, List<Integer>>>` 타입으로 예시를 위해 일급 콜렉션을 사용하지 않는다.  

짝수인지 홀수인지 판단하는 서비스는 두 개의 서비스를 가지고 있는 `Facade` 형태로 구현된다.  

```java
class SomeService {
    private final QueryService queryService = new QueryService();
    private final CommandService commandService = new CommandService();

    public Boolean someMethod() {
        final Map<String, Map<String, List<Integer>>> values = queryService.getValues();
        final Integer result = commandService.totalSum(values);
        return result % 2 == 0;
    }
}
```

`Service` 계층에서는 `Facade` 형태를 자주 볼 수 있는데 이는 관심사에 따라 타입을 명시하지 않아도 된다.  
Webflux 혹은 🔗[customDsl](../custom-dsl/README.md) 처럼 체이닝 메서드에서는 더욱더 그렇다.  

🔗[SomeService.java](./src/main/java/task01/SomeService.java) 의 `.someMethod()` 를 읽을 때의 관심사는 중첩된 자료구조 혹은 타입이 아닐 것이다.(`.totalSum()` 또한 마찬가지다)  
짝수인지 홀수인지를 판단하는 로직에 더 집중할텐데 이는 명시적으로 드러내는 타입이 방해가 될 수도 있다.  

읽어야할 관심사에 집중할 수 있도록 `var` 키워드를 적용해보자.  

- 🔗[SomeService.java](./src/main/java/task02/SomeService.java) `var` 키워드 적용

```java
public Boolean someMethod() {
    final var values = queryService.getValues();
    final var result = commandService.totalSum(values);
    return result % 2 == 0;
}
```

`var` 키워드를 적용하면 해당 메서드의 책임에 대해 집중해서 읽을 수 있다.  
이는 조직 문화에 따라 다르겠지만 함수형 프로그래밍이 대두되는 시점에서 변수를 할당하고 주고받는 메서드 바디는 점점 사라질 것이다.  

또 다른 장점은 `import` 구문이 사라진다는 것인데 이는 귀찮은 것 이상의 장점을 제공한다.  
리턴 타입을 수정할 때 클라이언트 코드는 수정하지 않아도 된다.  
🔗[QueryService.getValues()](./src/main/java/task02/QueryService.java)의 리턴 타입이 바뀌어도 지장이 없다.  
마찬가지로 🔗[CommandService.totalSum()](./src/main/java/task02/CommandService.java) 메서드 매개변수가 🔗[QueryService.getValues()](./src/main/java/task02/QueryService.java)의 리턴 타입과 일치한다면 🔗[SomeService.java](./src/main/java/task02/SomeService.java) 코드를 수정하지 않아도 된다.  

`import` 구문이 없어도 올바르게 `import` 된다.  
`var` 키워드는 컴파일 타임에 타입이 확정된다. 컴파일 타임에 초기화된 🔗[QueryService.getValues()](./src/main/java/task02/QueryService.java) 리턴 타입으로 `var` 의 타입을 결정한다.  
컴파일러는 `import` 구문은 없지만 `.getValues()` 의 리턴 타입을 확인 했으므로 `Map` 이 `java.util.map` 에 정의되어 있는지도 확인하여 결정한다.  
컴파일된 바이트 코드에서는 `java.util.map` 이라는 FQN(Fully Qualified Name)을 저장한다.  

이를 확인하기위해 컴파일해 `.class` 파일을 만들어 보자.  

- 전체 패키지를 컴파일하거나, 의존하고 있는 클래스들을 모두 컴파일한다.  

```shell
$ javac task02/*.java
or
$ javac CommandService.java QueryService.java SomeService.java
```

생성된 🔗[SomeService.class](./src/main/java/task02/CommandService.class) 파일을 보면 `import` 구문이 있지만 이는 디컴파일러가 `.class` 파일을 사람이 읽을 수 있는 Java 코드로 변환하기 때문이다.  
이 과정에서 바이트 코드에 기록된 FQN을 간략하게 표현하기 위해 `import` 구문이 추가된 것이지 실제 `import` 가 추가되지 않는다.  

모든 클래스 참조는 FQN 으로 이루어지므로 `var` 키워드를 사용하더라도 컴파일러는 문제없이 컴파일타임에 타입을 확정시킬 수 있다.
컴파일 한 결과를 바이트 코드로 확인해보자.  

```shell
$ javap -c SomeService.class
```

`someMethod()` 중간에 `4: invokevirtual #23 // Method task02/QueryService.getValues:()Ljava/util/Map;` 경로를 볼 수 있는데,  
이는 바이트 코드에서 모든 클래스 참조는 FQN으로 이루어지는 것이다.  


- 바이트 코드 일부 모습 

```shell
public java.lang.Boolean someMethod();
    Code:
       0: aload_0
       1: getfield      #10                 // Field queryService:Ltask02/QueryService;
       4: invokevirtual #23                 // Method task02/QueryService.getValues:()Ljava/util/Map;
```