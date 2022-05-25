### Lombok

Позволяет сократить написание лишнего кода (boilerplate code) с помощью аннотаций. <br>
`boilerplate code` – это код, который не относится в бизнес процессам (конструкторы, гетеры сеттеры и пр.)<br>
Код становится более читабельным.

> https://projectlombok.org/

Javadoc Lombok:
> https://projectlombok.org/api/

Описание на официальном сайте
> https://projectlombok.org/features/all 

Другие ресурсы:
> https://www.baeldung.com/intro-to-project-lombok


@Slf4j - добавляет в класс логгер log
@SneakyThrows - делает проверяемые исключения непроверяемыми

@NoArgsConstructor //добавляет конструктор без аргументов
@AllArgsConstructor //добавляет конструктор со всеми параметрами
@RequiredArgsConstructor //добавляет конструктор для final полей
@Value - генерирует конструктор, только геттеры, методы equals, hashCode, toString. А также делает все поля private и final.
@Data - генерирует конструктор, геттеры, сеттеры, методы equals, hashCode, toString. А также делает все поля private.
@With - добавляет методы для каждого поля, которые делают клон объекта с одним измененным полем.

@Getter //добавляет геттеры для всех параметров класса
@Setter //добавляет сеттеры для всех параметров класса
@EqualsAndHashCode //добавляет реализации методов equals и hashCode
@ToString //добавляет реализацию метода toString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) //делает все поля private и final
@Builder - генерирует методы, которыми мы инициализируем объект по цепочке. Это удобно когда мы не хотим использовать конструктор со всеми параметрами (Если у нас класс неизменяемый, то в нем единственный конструктор со всеми параметрами).
@ToString.Exclude - Исключить поле в методе toString
@EqualsAndHashCode.Exclude - Исключить поле в методе equals и hashCode
@NonNull - обработка переменных, которые не должны получать null

@var - для обозначения «изменяемых» переменных
@val - для констант