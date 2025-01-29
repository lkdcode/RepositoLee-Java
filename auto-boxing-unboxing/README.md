# 🎯 오토박싱 / 오토언박싱

오토박싱과 오토언박싱은 `Java5` 에 도입된 기본타입(primitive type)과 래퍼 클래스(wrapper class) 간의 자동 형변환이다.  
개발자에게 편리함과 생산성을 높여주지만 성능 저하가 발생할 수 있으므로 무분별한 사용은 지양해야 한다.  
메서드 호출전과 후를 계산한 간단한 성능 비교부터 JMH를 활용한 벤치마크까지 성능을 확인해 본다.

# ✅ 시작에 앞서

⚠️ 벤치마크에 필요한 의존성인 `java.util.concurrent.TimeUnit`가 `JDK 21`에서 임포트가 안 되는 현상 때문에 해당 모듈만 `JDK 17`을 사용하였습니다.  
⚠️ JMH 벤치마크는 기본적으로 `src/jmh/java` 경로를 소스 루트로 사용합니다.(사용자 정의 가능)  
⚠️ JMH 벤치마크 클래스는 `default pacakage`를 지원하지 않으므로 반드시 패키지 선언이 필요합니다.(`src/jmh/java/lkdcode/app/benchmark`)

- JMH 공식문서와 Example 코드

🔗 [GITHUB Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh)  
🔗 [GITHUB JMH Example](https://github.com/twoVersatile/jmhsample)  
🔗 [의존성 Gradle](build.gradle)

# 🎯 오토박싱 / 오토언박싱

래퍼 클래스는 기본 타입을 변환하기 위해 새로운 객체를 생성하거나 캐시된 객체를 반환한다. 이 과정에서 당연히 기본 타입보다 더 많은 메모리와 CPU 리소스를 소비한다.  
예를 들어 `int` ➡️ `Integer`로 변환할 때 오토박싱 과정에서는 내부적으로 `Integer.valueOf(42)`를 호출하여 객체로 변환한다.  
캐싱 범위(-128 ~ 127)를 벗어난 값은 매번 새로운 객체를 생성하므로 추가적인 메모리 할당과 GC 부담이 발생한다.

디버그 모드로 `int` ↔️ `Integer` 오토박싱/오토언박싱을 확인해볼 수 있는데 `java.lang.Integer` 에서 `valueOf()` 메서드에 브레이크 포인트를 추가하면 된다.

- Integer.valueOf() 메서드

```java
    @IntrinsicCandidate
    public static Integer valueOf(int i){
        if(i>=IntegerCache.low&&i<=IntegerCache.high)
            return IntegerCache.cache[i+(-IntegerCache.low)];
        return new Integer(i);
    }
```

조건문에 `IntegerCache.low`와 `IntegerCache.high`가 캐시 범위를 의미하는데 `IntegerCache`는 중첩 클래스로 자리잡고 있다.

```java
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer[] cache;
    static Integer[] archivedCache;

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                h = Math.max(parseInt(integerCacheHighPropValue), 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(h, Integer.MAX_VALUE - (-low) - 1);
            } catch (NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;
        /*...*/
    }
}
```

`cache = archivedCache; // range [-128, 127] must be interned (JLS7 5.1.7)` 이 부분에서 알 수 있듯이  
캐시 범위인 `-128~127`까지의 값을 미리 `Integer[]`에 셋업하고 있다.  
🔗 [task01.Application](./src/main/java/task01/Application.java)에서 확인해 볼 수 있으며 `java.lang.Integer.valueOf()` 메서드에 브레이크
포인트를 지정해야 한다.

# 🎯 오토박싱 / 오토언박싱

1부터 n까지 덧셈을 하는 메서드 2개를 하나는 기본 타입으로, 하나는 래퍼 타입으로 구현하고 성능 차이를 확인해 보자.

- 🔗 [AutoBoxing.foreach()](./src/main/java/task02/AutoBoxing.java) 래퍼 타입으로 구현되어 있다.

```java
public final class AutoBoxing {
    public static Long foreach(final Integer n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .reduce(0L, Long::sum);
    }
}
```

- 🔗 [Primitive.foreach()](./src/main/java/task02/Primitive.java) 기본 타입으로 구현되어 있다.

```java
public final class Primitive {
    public static long foreach(final int n) {
        return IntStream.rangeClosed(0, n)
            .asLongStream()
            .sum();
    }
}
```

- 🔗 [PrimitiveParallel.foreach()](./src/main/java/task02/PrimitiveParallel.java) 병렬 스트림으로 구현되어 있다.

```java
public class PrimitiveParallel {
    public static long foreach(final int n) {
        return IntStream.rangeClosed(0, n)
            .parallel()
            .asLongStream()
            .sum();
    }
}
```

🔗 [task02.Application](./src/main/java/task02/Application.java) 메인 메서드를 실행하면 각 메서드들이 결과를 리턴하는데 걸리는 시간을 체크하여 콘솔에 출력한다.  
래퍼,기본,병렬처리까지 소요시간에 대해서 간략하게 볼 수 있다. `IntStream.rangeClosed()` 메서드는 종료 값을 포함하는 기본타입에 특화된 메서드이다.  
하드웨어 스펙에 따라 조금씩 다르겠지만 보통 기본 타입보다 래퍼 타입이 더 오래걸리며 1,000,000까지의 총합부터는 병렬처리가 우수하다.

병렬 스트림은 내부적으로 `ForJoinPool`을 사용하여 데이터를 여러 CPU 코어에 분산하여 처리하는데 이때 추가적인 비용으로 인해 성능이 오히려 느려질 수 있다.  
위의 코드는 이러한 상황을 잘 보여주는 예시이다.

# 🎯 JMH 벤치마크

JMH 라이브러리를 활용해 더 정확하게 확인해 보자.  
위의 의존성을 추가한 뒤 벤치마크 클래스를 생성해준다.

🔗 [ForeachBenchmark](./src/jmh/java/lkdcode/app/benchmark/ForeachBenchmark.java) 클래스의 경로에 유의하며 jmhJar 를 실행해 준다.

- 인텔리제이 매뉴 중 `View -> Tool Windows -> Gradle`을 키고 `auto-boxing-unboxing -> Tasks -> jmh -> jmhJar 를 실행해도 된다.

```shell
$ ./gradlew :auto-boxing-unboxing:jmhJar
# auto-boxing-unboxing/build/libs 경로로 이동하여 jar 를 실행해 준다.
$ java -jar auto-boxing-unboxing-v1.0-jmh.jar
```

#### 📊 JMH 벤치마크 결과

| 벤치마크 메서드                                    | (max)         | Mode   | Cnt | Score     | Error       | Units   |
|---------------------------------------------|---------------|--------|-----|-----------|-------------|---------|
| `ForeachBenchmark.autoBoxingExecute`        | 1,000,000,000 | `avgt` | 10  | 9,204.975 | ± 3,991.408 | `ms/op` |
| `ForeachBenchmark.autoBoxingExecute`        | 10,000,000    | `avgt` | 10  | 58.497    | ± 3.914     | `ms/op` |
| `ForeachBenchmark.autoBoxingExecute`        | 100,000       | `avgt` | 10  | 0.555     | ± 0.009     | `ms/op` |
| `ForeachBenchmark.primitiveExecute`         | 1,000,000,000 | `avgt` | 10  | 646.988   | ± 6.540     | `ms/op` |
| `ForeachBenchmark.primitiveExecute`         | 10,000,000    | `avgt` | 10  | 6.598     | ± 0.190     | `ms/op` |
| `ForeachBenchmark.primitiveExecute`         | 100,000       | `avgt` | 10  | 0.075     | ± 0.002     | `ms/op` |
| `ForeachBenchmark.primitiveParallelExecute` | 1,000,000,000 | `avgt` | 10  | 119.871   | ± 0.682     | `ms/op` |
| `ForeachBenchmark.primitiveParallelExecute` | 10,000,000    | `avgt` | 10  | 1.259     | ± 0.015     | `ms/op` |
| `ForeachBenchmark.primitiveParallelExecute` | 100,000       | `avgt` | 10  | 0.079     | ± 0.001     | `ms/op` |

`@Param` 으로 입력한 크기에 따라 각 메서드가 벤치마크 됐는데 각 컬럼의 의미는 다음과 같다.

- `Mode`: 벤치마크 실행 모드(`avgt`,`thrpt` 등)
- `Cnt`: 벤치마크 실행 반복 횟수 (기본값 10)
- `Score`: 측정된 성능 값 (`ms/op`, `ops/sec` 등)
- `Error`: 신뢰 구간 (결과 편차)
- `Units`: 성능 단위 (`ms/op`, `ops/sec`)

상세 과정과 결과는 🔗 [BenchmarkResult](result1.txt)에서 확인할 수 있다. 빌드는 `JDK 17`로 진행되지만 `java -jar` 명령어는 `JAVA_HOME` 변수가 우선적용되므로 `JDK 21`로 된 모습이다.