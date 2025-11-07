![img.png](task3.png)

### Задача: Обнаружение конфликтов версий зависимостей

Выполнена команда для анализа дерева зависимостей:
mvn dependency:tree

Найдены разные версии зависимостей в библиотеке jackson.

1. jackson-core:2.12.7

2. jackson-databind:2.14.2  - databind 2.14.2 ожидает core 2.14.2 несовместима с core 2.12.7 

3. jackson-annotations:2.15.2 -  annotations 2.15.2 несовместима с core 2.12.7

Есть риск ошибок NoSuchMethodError или ClassNotFoundException при выполнении.

### Итог: 

Успешно обнаружены конфликты версий между компонентами Jackson. 
Использование разных версий core, databind и annotations приводит к потенциальной несовместимости.

