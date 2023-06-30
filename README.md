Данный проект нацелен на реализацию сервиса для учёта материального фонда предприятия 
и материально ответственных лиц, а также формирования отчётов. Приложение должно состоять 
из трех частей: функционал для работы с данными сотрудников, функционал для ведения списка
материальных ценностей и функционал для формирования Excel отчётов на основе введенных
ранее данных.

Для получения доступа к сервису необходимо запустить приложение и перейти по ссылке в браузере:

    http://localhost:8081/swagger-ui/index.html

TABLES IN POSTGRESQL:

    CREATE TABLE Employees (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR NOT NULL,
    SURNAME VARCHAR NOT NULL,
    PATRONYMIC VARCHAR,
    POST VARCHAR NOT NULL
    );
    
    CREATE TABLE Items (
    ID SERIAL PRIMARY KEY,
    EMPLOYEE_ID INTEGER,
    TYPE VARCHAR NOT NULL,
    COST INTEGER NOT NULL DEFAULT 0,
    CREATE_DATE DATE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES Employees(ID)
    );


