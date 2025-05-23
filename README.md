# Сервис записей (Record Service)

## Обзор
Сервис записей - это микросервис, отвечающий за управление записями клиентов к мастерам в барбершопах. Он обрабатывает создание, обновление, получение и удаление записей на стрижки и другие услуги.

## Функциональность
- Управление записями клиентов (создание, обновление, получение, удаление)
- Планирование записей на определенное время
- Уведомления о записях
- Интеграция с сервисами барбершопов и пользователей

## Технологический стек
- Java 21
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Liquibase (для миграций базы данных)
- Планировщик задач (@EnableScheduling)

## API Endpoints
Сервис предоставляет RESTful API для:
- Управления записями
- Получения расписания мастеров
- Получения истории записей клиентов

## Конфигурация
Сервис использует следующую конфигурацию:
- База данных: PostgreSQL
- Обнаружение сервисов: Eureka
- Интеграция с другими сервисами через RestTemplate

## Зависимости
- Eureka Server: Регистрация и обнаружение сервисов
- PostgreSQL: Хранение данных о записях
- Gateway: Маршрутизация API
- Barbershop Service: Информация о барбершопах и услугах
- User Service: Информация о пользователях (клиентах и мастерах)

## Разработка
Для локального запуска сервиса:

1. Убедитесь, что PostgreSQL работает и создана база данных `record_db`
2. Примените миграции Liquibase
3. Установите необходимые переменные окружения (см. файл `.env`)
4. Запустите приложение: `./gradlew bootRun`

## Docker
Сервис можно развернуть с помощью Docker. Для этого необходимо скачать образ из Docker Hub и запустить командой:
```bash
docker run -p 8082:8082 --env-file .env krupnoveo/barbershop_with_microservices_record-service
```

## Переменные окружения
- `record.service.name`: Имя сервиса для регистрации в Eureka
- `record.service.port`: Порт, на котором работает сервис
- `postgres.host`: Хост PostgreSQL
- `postgres.port`: Порт PostgreSQL
- `DB_USER`: Имя пользователя базы данных
- `DB_PASSWORD`: Пароль базы данных
- `eureka.service.host`: Хост Eureka сервера
- `eureka.service.port`: Порт Eureka сервера
- `user.service.name`: Имя сервиса пользователей для обращения к нему
- `barbershop.service.name`: Имя сервиса барбершопов для обращения к нему 