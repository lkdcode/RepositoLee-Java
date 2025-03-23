# 🎯 Generic

`Generic`을 사용하다보면 한 번쯤은 마주치게 되는 개념인 공변성을 간단히 정리한다.  
`Generic`은 기본적으로 무공변이다.  

## 사용된 클래스

🔗 [Animal](./src/main/java/task01/Animal.java) 은 상위 클래스,  
🔗 [Cat](./src/main/java/task01/Cat.java) 은 하위 클래스이다.  
🔗 [Haru](./src/main/java/task01/Haru.java) 는 하위 클래스이다.  

```java
class Animal {}    
class Cat extends Animal {}
class Haru extends Cat {}
```

위와 같은 계층 구조를 설정하고 공변성에 대해 살펴본다.  

## 무공변 Invariance (제네릭의 기본 동작)

- 🔗 [Invariance](./src/main/java/task01/Invariance.java)

"상위 - 하위 관계여도 다른 타입으로 간주된다."  
`List<Cat>`은 `List<Animal>`의 하위 타입이 아니다.  

```java
List<Cat> cats = new ArrayList<>();
List<Animal> animals = cats; // ❌컴파일 에러 발생
```

`Generic`은 기본적으로 무공변이기 때문에 타입이 정확히 일치하지 않으면 대입할 수 없다.  
이유는 `Animal` 의 여러 하위 타입이 존재할 때 `타입 안정성`이 깨지기 때문이다.  
`List<Animal> animals = cats;` 가 허용되면, `class Dog extends Animal {}` 도 해당 변수에 추가될 수 있음.  

만약 `new ArrayList<>(cats);` 로 하게되면, 복사 생성자 방식으로 업캐스팅을 통해 리스트를 생성하므로 문제를 해결할 수 있다.  

## 공변성 Covariance (? extends T)

- 🔗 [Covariance](./src/main/java/task01/Covariance.java)

"하위 타입을 상위 타입으로 다룰 수 있도록 허용"  
`List<Cat>`은 `List<? extends Animal>`의 하위 타입으로 허용된다.  

```java
List<Cat> cats = new ArrayList<>();
List<? extends Animal> animals = cats; // ✅컴파일 에러 없음

animals.add(new Cat()); // ❌ 쓰기만 허용
```

하지만, `쓰기`는 허용되지 않는다. 오직 `읽기`만 가능하다.  
컴파일러는 `List<? extends Animal> animals` 가 정확히 어떤 타입인지 알 수 없다.  
`Animal`의 하위 타입은 모두 허용되므로 읽기만 가능하고 쓰기를 제한하는 것이다.  

이또한 타입 안정성에 대한 이유인데, 쓰기가 허용된다면,  
`List<? extends Animal> animals = cats;` 현재 `List<Cat>` 와 다름이 없는데  
`animals.add(new Dog())`에서 타입 안정성이 문제된다.  

컴파일러가 컴파일 시점에 알 수 있는 타입과 런타임 과정에서 알 수 없는 것은 타입 안정성을 깰 수 있으므로 애초에 허용하지 않는다.  
`List<? extends T>` 는 컴파일 시점에 정확히 어떤 타입인지 알 수 없다. 하여 쓰기를 제한한다. (`읽기`는 무조건 `Animal` 타입.)  

```java
List<Cat> cats = new ArrayList<>();
List<? extends Animal> animals = cats;

Cat cat = cats.get(0); // 가능. (ArrayIndexOutOfBoundsException 무시)

animals.add(new Animal()); // 불가: Animal이 Cat의 하위 타입이 아님.
animals.add(new Cat()); // 불가: 공변성에서는 추가 불가.
```

## 반공변성 Contravariance (? super T)

- 🔗 [Contravariance](./src/main/java/task01/Contravariance.java)

"하위 타입을 상위 타입에 안전하게 추가할 수 있도록 허용"  
`List<Animal>`은 `List<? super Cat>`의 상위 타입으로 허용된다.  

`Cat`의 상위 타입들의 집합 리스트이므로 다양한 타입들이 존재할 수 있어 `읽기`는 최상위 타입인 `Object`로 읽고, `쓰기`는 가능하다.  

```java
List<? super Cat> list = List.of(new Cat(), new Haru());

Object animal1 = animals.get(0);  // 반공변성은 기본적으로 Object 로 리턴.
Cat animal2 = (Cat) animals.get(0);  
// Cat animal2 = (Cat) animals.get(0);  // 명시적 형변환이 없으면 에러

Animal animal3 = (Animal) animals.get(0);
// Animal animal3 = animals.get(0); // 명시적 형변환이 없으면 에러
```

참고로 오버로딩할 때 제네릭의 타입 소거와 관계 없다.  

```java
void something(List<Number> args){...}
void something(List<String> args){...}
```