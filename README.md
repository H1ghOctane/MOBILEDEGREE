<h1 align="center">Мобильные автотесты для <a href="https://www.wikipedia.org">Wikipedia</a></h1>

<p align="center">
  <a href="https://www.wikipedia.org"><img src="images/logo/wiki.png" alt="Wikipedia"></a>
</p>

Мобильная часть дипломного проекта QA.GURU. Тесты проверяют поиск в Android-приложении [Wikipedia](https://www.wikipedia.org) и запускаются двумя способами: на эмуляторе Android Studio у себя на компьютере и на реальном устройстве в [BrowserStack](https://www.browserstack.com/app-automate). UI- и API-тесты лежат в репозитории [AVIASALESDEGREE](https://github.com/H1ghOctane/AVIASALESDEGREE).

## Содержание

- [Стек](#стек)
- [Что проверяют тесты](#что-проверяют-тесты)
- [Структура проекта](#структура-проекта)
- [Подготовка к локальному запуску](#подготовка-к-локальному-запуску)
- [Запуск](#запуск)
- [Сборка в Jenkins](#сборка-в-jenkins)
- [Отчёт Allure](#отчёт-allure)
- [Видео прохождения теста](#видео-прохождения-теста)

## Стек

<a id="tools"></a>
## :ballot_box_with_check:Технологии и инструменты:

| Java                                                                                                      | IntelliJ  <br>  Idea                                                                                               | GitHub                                                                                                     | JUnit 5                                                                                                           | Gradle                                                                                                     | Selenide                                                                                                         | Selenoid                                                                                                                  | Allure <br> Report                                                                                                         |  Jenkins                                                                                                        |   Jira                                                                                                              | Telegram                                                                                                            |Allure <br> TestOps                                                                                                          
|:----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------:|
| <a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  | <a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a> | <a href="https://github.com/allure-framework"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> |<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/software/jira/"><img src="images/logo/Jira.svg" width="50" height="50" alt="Java" title="Java"/></a> | <a href="https://web.telegram.org/"><img src="images\logo\Telegram.svg" width="50" height="50" alt="Telegram"/></a> |<a href="https://qameta.io/"><img src="images\logo\Allure_TO.svg" width="50" height="50" alt="Allure_TO"/></a> |

<a id="cases"></a>

| Назначение | Инструмент | Версия |
| --- | --- | --- |
| Язык | Java | 17 |
| Сборка | Gradle | 8.13 |
| Тестовый фреймворк | JUnit 5 | 5.13.4 |
| Работа с приложением | Selenide Appium | 7.18.1 |
| Драйвер | Appium Java Client | 9.5.0 |
| Конфигурация | Owner | 1.0.12 |
| Отчёты | Allure Report | 2.46.1 |
| Локальный запуск | Appium 2, эмулятор Android Studio | |
| Удалённый запуск | BrowserStack App Automate | |
| CI | Jenkins | |

## Что проверяют тесты

Все тесты в классе `WikipediaSearchTest`:

- поиск по запросам «Appium», «Selenium» и «Java» находит статьи (параметризованный тест);
- в результатах по запросу «Appium» есть статья с таким названием;
- статья «Appium» открывается из результатов, и в ней правильный заголовок;
- после очистки строки поиска список результатов пустеет.

## Структура проекта

```
src/test
├── java
│   ├── config    два конфига Owner: LocalConfig и BrowserstackConfig
│   ├── drivers   LocalDriver (эмулятор) и BrowserstackDriver
│   ├── helpers   скриншот и видео из BrowserStack в Allure
│   ├── screens   экраны приложения: главный, поиск, статья
│   └── tests     TestBase и тесты
└── resources
    └── config    local.properties и browserstack.properties
```

Тесты работают с приложением только через классы экранов (Screen Object). Способ запуска выбирается параметром `deviceHost`, и каждому способу соответствует свой конфиг.

## Подготовка к локальному запуску

1. В Android Studio открой Device Manager и создай эмулятор Pixel с Android 11 (API 30). Приложение собрано в 2017 году, на новых версиях Android оно может не установиться.
2. Установи Appium 2 и драйвер UiAutomator2:

   ```bash
   npm i -g appium
   appium driver install uiautomator2
   ```

3. Положи APK приложения в `src/test/resources/apps/WikipediaSample.apk`. Это демо-сборка Wikipedia из документации BrowserStack App Automate, та же, что загружена в BrowserStack. В репозиторий APK не коммитится.
4. Запусти эмулятор, затем в отдельном терминале команду `appium`. Команда `adb devices` должна показать эмулятор. Если его имя отличается от `emulator-5554`, поправь `local.deviceName` в `src/test/resources/config/local.properties`.

## Запуск

На эмуляторе:

```bash
./gradlew clean test -DdeviceHost=local
```

В BrowserStack:

```bash
./gradlew clean test -DdeviceHost=browserstack
```

Без параметра `deviceHost` тесты запускаются на эмуляторе.

Для BrowserStack нужны логин и ключ доступа. Положи их в файл `browserstack.properties` в домашней папке, вне репозитория:

```properties
bs.username=логин
bs.accessKey=ключ
```

В `src/test/resources/config/browserstack.properties` лежат только устройство, версия Android и ссылка на загруженное приложение, секретов там нет.

| Параметр | Что задаёт | По умолчанию |
| --- | --- | --- |
| `deviceHost` | где запускать: `local` или `browserstack` | `local` |
| `local.deviceName` | имя эмулятора из `adb devices` | `emulator-5554` |
| `local.platformVersion` | версия Android эмулятора | `11.0` |
| `local.appiumUrl` | адрес Appium | `http://127.0.0.1:4723` |
| `bs.deviceName` | устройство в BrowserStack | `Samsung Galaxy S22 Ultra` |
| `bs.osVersion` | версия Android в BrowserStack | `12.0` |

## Сборка в [Jenkins](https://jenkins.qa.guru/job/MOBILE_H1GHT0CTANE/)

Сборка запускает тесты в BrowserStack. Логин и ключ передаются параметрами `BROWSERSTACK_USERNAME` и `BROWSERSTACK_ACCESS_KEY` и в лог сборки не попадают.

<img src="images/screens/jenkins.png" alt="Сборка в Jenkins">

## [Отчёт Allure](https://jenkins.qa.guru/job/MOBILE_H1GHT0CTANE/lastCompletedBuild/allure/)

У каждого теста в отчёте есть шаги, скриншот последнего экрана и видео из BrowserStack.

<img src="images/logo/img.png" alt="Главная страница отчёта Allure">

<img src="images/logo/img2.png" alt="Тесты в отчёте Allure">

## Уведомления в Telegram  

После каждой сборки бот присылает в чат результат прогона и ссылку на отчёт:
<img src="images/logo/img2.png" alt="Тесты в отчёте Allure">

## Видео прохождения теста

Тест открытия статьи в BrowserStack.

<p align="center">
  <img src="images/video/8a1cee5b6e8e1626.mp4" width="300" alt="Прохождение теста в BrowserStack">
</p>
