### Темы Java Core

+ [Общие определения](core/definition.md)
+ [ООП](core/oop.md)
+ [Типы данных](core/typeData.md)
+ [Типы переменных](core/typeVariable.md)
+ [Базовые операторы](core/baseOperators.md)
+ 
















### JVM, JRE и JDK

`Виртуальная машина Java (JVM)` выполняет скомпилированный байт-код.<br>
`Java Runtime Environment (JRE)` включает в себя JVM и стандартные библиотеки и запускает скомпилированные программы.<br>
Когда вы запускаете скомпилированную программу, JRE объединяет байт-код программы с необходимыми библиотеками и запускает JVM, которая выполняет полученный байт-код.<br>
`Java Development Kit (JDK)`, включающий JRE и средства разработки, используется разработчиками для написания программ.<br>
Он включает в себя JRE для запуска программ и инструментов для разработчиков: компилятор Java, отладчик, архиватор, генератор документации и т. д.<br>

![Image alt](https://raw.githubusercontent.com/dianapovarnitsina/java-theory/master/theory/img/jdk2.png)


***Связь между JVM, JRE и JDK***

![Image alt](https://raw.githubusercontent.com/dianapovarnitsina/java-theory/master/theory/img/jdk.png)



### Модификаторы
+ __static__ - говорит о том, что класс, объект или поле статичны.<br>
Статические переменные называются переменными класса и являются уникальными для всех экземпляров данного класса. 
Статические методы могут быть вызваны без создания объекта, в котором они описаны. 
Также может использоваться в виде отдельного блока внутри объекта.

+ __final__ - указыает невозможность переопределения.<br>
Final переменная является константой.
Final методы не могут быть переопределены при наследовании.
От final класса нельзя наследоваться.

+ __abstact__ - указывает на абстрактный метод или класс.

+ __default__ - начиная с Java 8 используется для объявления поведения по умолчанию в интерфейсах.

+ __native__ - указыает на нативную реализацию.

+ __strictfp__ - это модификатор, введенный в java 1.2, ограничивающий точность вычислений с float и double по стандарту IEEE. Для чего это нужно? Чтобы обеспечить переносимость. Дело в том, что JVM использует всю возможную точность процессора, а она на разных системах разная, поэтому и результат может получиться разный. Данный модификатор используется в программах требующих точность вычислений превышающих IEEE (обычно, что-нибудь связное с наукой).
+ __synchronized__ - это зарезервированное слово позволяет добиваться синхронизации в помеченных им методах или блоках кода.
+ __transient__ - поля, помеченные таким модификаторовм, пропускаюся при сериализации/десериализации.
+ __volatile__ - этот модификатор вынуждает потоки отключить оптимизацию доступа и использовать единственный экземпляр переменной. Если переменная примитивного типа – этого будет достаточно для обеспечения потокобезопасности. Если же переменная является ссылкой на объект – синхронизировано будет исключительно значение этой ссылки. Все же данные, содержащиеся в объекте, синхронизированы не будут!

__Модификаторы доступа__ - описывает доступность класса, объекта или поля:
+ __private__ (приватный): члены класса доступны только внутри класса. Для обозначения используется служебное слово `private`.
+ __(empty)__, package-private, package level (доступ на уровне пакета): видимость класса/членов класса только внутри пакета. Является модификатором доступа по умолчанию - специальное
  обозначение не требуется.
+ __protected__ (защищённый): члены класса доступны внутри пакета и в наследниках. Для обозначения используется служебное слово `protected`.
+ __public__ (публичный): класс/члены класса доступны всем. Для обозначения используется служебное слово `public`.

Последовательность модификаторов по возрастанию уровня закрытости: public, protected, default, private.

Во время наследования возможно изменения модификаторов доступа в сторону большей видимости (для поддержания соответствия _принципу подстановки Барбары Лисков_).


## Где и для чего используется модификатор `abstract`?
Класс помеченный модификатором `abstract` называется абстрактным классом. Такие классы могут выступать только предками для других классов. Создавать экземпляры самого абстрактного класса не разрешается. При этом наследниками абстрактного класса могут быть как другие абстрактные классы, так и классы, допускающие создание объектов.

Метод помеченный ключевым словом `abstract` - абстрактный метод, т.е. метод, который не имеет реализации. Если в классе присутствует хотя бы один абстрактный метод, то весь класс должен быть объявлен абстрактным.

Использование абстрактных классов и методов позволяет описать некий шаблон объекта, который должен быть реализован в других классах. В них же самих описывается лишь некое общее для всех потомков поведение.


### final константы и конструкторы

***Константы***

Константа похожа на переменную, тоже хранит значение, но его изменять невозможно.

Нужны: Чтобы хранить постоянные значения, которые точно не будет изменяться.

Для объявление константы используется ключевое слово final
Обычно модификатор доступа у константы – public
Доступ к константам осуществляется – напрямую, без get/set.
К константе и к переменной – можно обращаться как внутри класса (для личных нужд), так и получать с помощью ссылки на объект.

Можно final константу присваивать не сразу в месте объявления, а использовать для этого конструктор – тогда значение можно передавать извне.

```java
class Test{
    public final int number;
    
    public Test(int number){
        this.number == number
    }
}
```


