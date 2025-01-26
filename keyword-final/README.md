# 🎯 final 키워드

Java 에서 `final` 키워드는 변수, 메서드, 클래스에서 사용되며 각각 다른 의미를 가진다.  
메서드와 클래스는 실제 상속과 관련된 제한을 하며 부수적으로 명시적인 의도를 표현한다.  
변수에서의 의미는 조금 다른데 명시적으로 재할당을 금지하며 부수적으로 마커 효과를 준다.

#### 🎯 클래스와 메서드에서 `final`  

클래스와 메서드에 부여하면 하면 상속과 관련하여 제한한다. 클래스인 경우 상속을 제한하며, 메서드는 오버라이딩을 제한한다.  
JVM 은 메서드 및 클래스를 호출하는 과정에서 어디까지 확장이 되었는지 분석해야할 때가 있는데,  
`final` 키워드를 통해 JIT 컴파일러가 코드 최적화를 더 잘할 수 있도록 힌트를 제공한다.  
`final` 메서드는 오버라이드가 불가능하므로 JVM은 메서드를 호출하지 않고 바로 실행 코드에 삽입(인라이닝)할 수 있으며 `final` 클래스는 JVM이 클래스 계층 구조를 분석할 이유가 없다.  
클래스와 메서드에선 상속 재할당을 넘어선 성능 최적화의 중요한 힌트로도 사용된다.  

#### 🎯 변수에서 `final`  

변수에서 사용할 땐 재할당을 금지한다. `static`과 함께 사용하면 클래스 수준에서 공유되는 상수를 정의할 수 있으며 특히 `Immutable`과 혼동해선 안 된다.  

```java
class FinalExam {  
    public void someMethod() {  
        final String apple = "APPLE";  
        apple = "BANANA";  
    }  
}
```

원시값에서도 재할당을 금지하는 용도로 사용되므로 혼동해선 안 된다.  

```java
public static void primitive() {  
    final int number = 3;  
    /*  
	    이 경우에도 재할당이 금지다. 원시값은 메모리 값 자체를 가지고 있지만
	    재할당 할 수 없으므로 해당 메서드 바디에서만 재할당 금지이다.
	    이 경우 역시 여전히 불변과 혼동하면 안 된다.
	    number += 3;
	    number -= 3;
	    number = 13;
	    number /= 3;
	    number *= 3;
	*/
}
```

`final int target` 을 매개변수로 넘겨주더라도 복사본으로 넘기기 때문에 그 범위는 넘기기전 메서드 바디까지만 허용된다.  
또 `sum()` 메서드에서는 매개변수 `int service`를 `final` 로 정의하지 않았기 때문에 재할당이 가능한 것이다.  

```java
class FinalExam {

    public static int sum(int service) {
        service += 3;
        service += 10;
        service += 50;
        return service + 5;
    }

    public static void main(String[] args) {
        final int target = 100;
        int result = sum(target);

        System.out.println(result);
    }
}
```

# 🎯 재할당 금지와 불변

재할당을 금지하는 것과 불변은 다르다. 자료구조를 통해 쉽게 이해할 수 있는데 아래와 같은 리스트에 `final` 키워드를 부여해도 불변이 아니다.  

```java
class FinalList {
    private static final List<Integer> LIST = new ArrayList<>();

    public void add(final int number) {
        LIST.add(number);
    }
}
```

불변은 값의 상태를 절대로 변경할 수 없음을 의미하는데 위의 `.add()` 메서드는 `LIST`에 대한 상태를 변하게 하므로 불변성이 성립되지 않는다.  
불변성을 가지려면 내부 상태를 변경할 수 있는 메서드나 기능들을 제약해야하는데 `AbstractImmutableCollection<E>` 이 그러하다.

```java
public static void main(String[] args) {
    List<Integer> list = List.of(1, 2, 3, 4, 5);
    list.add(6);
}
```

`List.of()` 로 생성하는 리스트는 `ImmutableCollections.listFromTrustedArray` 를 리턴하게 되는데,  
내부적으로 상태를 변경할 수 있는 메서드에 대하여 익셉션을 발생시킨다.  

```java
//  ImmutableCollections.class
    static UnsupportedOperationException uoe() { return new UnsupportedOperationException(); }

    @jdk.internal.ValueBased
    abstract static class AbstractImmutableCollection<E> extends AbstractCollection<E> {
        // all mutating methods throw UnsupportedOperationException
        @Override public boolean add(E e) { throw uoe(); }
        @Override public boolean addAll(Collection<? extends E> c) { throw uoe(); }
        @Override public void    clear() { throw uoe(); }
        @Override public boolean remove(Object o) { throw uoe(); }
        @Override public boolean removeAll(Collection<?> c) { throw uoe(); }
        @Override public boolean removeIf(Predicate<? super E> filter) { throw uoe(); }
        @Override public boolean retainAll(Collection<?> c) { throw uoe(); }
    }
```

`Arrays.asList()` 는 조금 다른데 기존의 상태는 변경할 수 있지만 새로운 상태에 대한 변경은 불가하다.

```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    public abstract E get(int index);

    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    // java.util.Arrays.ArrayList
    @Override
    public E set(int index, E element) {
        E oldValue = service[index];
        service[index] = element;
        return oldValue;
    }
```

Arrays.asList() 와 List.of() 의 차이는 간략하게 아래와 같다.
자료구조를 알아보는 스텝이 아니므로 재할당과 불변에 의미만 파악하면 된다.

|           | Arrays.asList()                 | List.of()                                                  |
| --------- | ------------------------------- | ---------------------------------------------------------- |
| Java 버전   | Java 1.2 이상                     | Java 9 이상                                                  |
| 변경 가능 여부  | 크기 변경 불가, 요소 변경 가능              | 불변 (Immutable)                                             |
| `null` 허용 | NULLABLE                        | NOT NULL                                                   |
| 크기 변경     | ❌                               | ❌                                                          |
| 요소 수정     | ⭕️ (`set()` 가능)                 | ❌                                                          |
| 내부 구현     | 배열 기반 리스트                       | `ImmutableCollections`                                    |
| 배열과의 연결   | 리스트와 원본 배열이 연결됨                 | 독립적                                                        |
| 주요 예외     | `UnsupportedOperationException` | `UnsupportedOperationException`,<br>`NullPointerException` |

항상 조직의 컨벤션을 최우선으로 삼되 개선할 수 있는 방향을 찾는 것 또한 개발자의 몫이므로 그 근거를 경계해야 한다.  

메서드 바디내에서 `final` 키워드는 재할당을 금지할 뿐만아니라 특정 마킹처럼 사용할 수 있다.  
복잡한 계산 로직에서는 여러 변수들을 재할당하는 과정이 발생할 수 있는데 이 때 `final` 키워드는 집중해서 읽지 않아도 되는 변수로 지정되며 불변 객체와 함께 사용되면 더 안전하게 값 변경 가능성을 차단하게 된다.

람다 표현식에서 사용하는 외부 변수는 암묵적으로 `final` 로 간주되므로 컴파일러 에러도 피할 수 있다.  
`someMethod2()` 는 람다 바디에서 재할당할 수 없는 변수이므로 컴파일 에러가 발생한다.
```java
  
public static void someMethod1() {  
	final String message = "lkdcode";  
	Runnable runnable = () -> System.out.println(message);  
	runnable.run();  
}  

public static void someMethod2() {  
	final String message = "lkdcode";  
	Runnable runnable = () -> {  
//      message = "lkdcode exception";  
		System.out.println(message);  
	};  
	runnable.run();  
}
```

Java 표준 Stream Api 를 사용할 때도 마찬가지다. Stream Api 에서 외부 변수를 사용할 땐 `final` 혹은 `effectively final` 이어야 한다.  
이 제약은 함수형 프로그래밍 특성과 멀티 스레드 환경에서 안정성을 보장하기 위해 존재한다.  

람다로 런타임 중 특정 시점에 행위를 정의하는데, 람다 내부에서는 변수가 사실상 `final` 이어야 한다고 요구한다.  

Stream Api가 멀티스레드 환경에서 동작할 땐 여러 스레드가 해당 변수를 수정하려고 할 때 문제가 발생할 수 있다.  
값이 잘못 계산되거나 예기치 않은 결과를 초래할 수 있으므로 `final or effectively final` 이어야 한다.  

람다식은 클로저(Closure) 로 동작하는데 자신이 정의된 환경에 있는 변수들을 캡처하여 사용하는 기능을 말한다. 이때 캡처한 값이 변경되면 일관성을 잃는다.  
람다식 내부에서 외부 변수를 참조하면 그 변수는 클로저의 일부가 된다.  
하지만 람다는 실행 시점이 불확실한데 예를 들어 Stream 작업이 언제 실행될지 미리 알 수가 없다.  
따라서 클로저가 캡처한 변수의 값이 변하지 않아야 일관성을 유지할 수 있다.  

위와 같은 이유로 코드의 안정성과 예측성 때문에 `final or effectively final` 이어야 한다.   

```java
    public static void someStream(List<String> list) {  
        String prefix = "LKDCODE";  
        final var newList = list  
            .stream()  
            .map(e -> {  
                e = "Hello World";  
//                e = prefix + "byebye"; /*prefix 가 final 이면 사용 가능하다.*/  
//                prefix = "hihi"; /* 재할당은 금지한다. effectively final*/  
                return e.toUpperCase();  
            })  
            .toList();  
        System.out.println(newList);  
    }
```

위와 같은 Java 프로그래밍 언어에 대한 규약들을 지켜, 컴파일 시점과 런타임 시점에 오류없이 동작하기 위해 사용하는 것을 명시적 사용이라고 한다.  
부수적인 효과로는 중간에 나온 여러 값들을 계산하는 로직 중 재할당을 금지시켜 일종의 마커 효과를 누리기위한 표식으로 사용하는 것을 일컫는다.  

기본 문법을 지켜 동작하는 것에 대해 논할 거리는 별로 없지만 `final` 키워드의 부수적 효과는 꽤나 유용하게 사용할 수 있다.  
기술 서적의 저자는 인상 깊었던 조직의 컨벤션이나 추구하는 방향성을 볼 수 있는데 `O'Reilly` 책들에서 이 `final` 마커를 심심치 않게 볼 수 있다.  