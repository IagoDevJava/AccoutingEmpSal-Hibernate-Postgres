-- Таблица графиков работы
CREATE TABLE work_schedules (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL, -- Название графика (например, "5/2", "2/2")
    hours_per_week INTEGER NOT NULL, -- Количество рабочих часов в неделю
    description TEXT -- Описание графика работы
);

-- Таблица грейдов сотрудников
CREATE TABLE grades (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL, -- Название грейда (например, "Junior", "Middle", "Senior")
    min_salary DECIMAL(10, 2) NOT NULL, -- Минимальный оклад для грейда
    max_salary DECIMAL(10, 2) NOT NULL, -- Максимальный оклад для грейда
    description TEXT -- Описание грейда
);

-- Таблица KPI (ключевых показателей эффективности)
CREATE TABLE kpis (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL, -- Название KPI (например, "Высокий", "Средний", "Низкий")
    bonus_percent DECIMAL(5, 2) NOT NULL, -- Процент премии от оклада
    description TEXT -- Описание KPI
);

-- Таблица департаментов
CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL, -- Название департамента
    phone VARCHAR(20), -- Телефон департамента
    email VARCHAR(100), -- Email департамента
    head_id INTEGER, -- ID руководителя департамента (ссылка на сотрудника)
    description TEXT, -- Описание департамента
    FOREIGN KEY (head_id) REFERENCES employees(id) ON DELETE SET NULL
);

-- Таблица должностей
CREATE TABLE positions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL, -- Название должности
    department_id INTEGER NOT NULL, -- ID департамента, к которому относится должность
    grade_id INTEGER NOT NULL, -- ID грейда для должности
    schedule_id INTEGER NOT NULL, -- ID графика работы для должности
    salary DECIMAL(10, 2) NOT NULL, -- Оклад для должности
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE,
    FOREIGN KEY (grade_id) REFERENCES grades(id) ON DELETE RESTRICT,
    FOREIGN KEY (schedule_id) REFERENCES work_schedules(id) ON DELETE RESTRICT
);

-- Таблица сотрудников
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL, -- Имя сотрудника
    last_name VARCHAR(50) NOT NULL, -- Фамилия сотрудника
    middle_name VARCHAR(50), -- Отчество сотрудника
    phone VARCHAR(20), -- Телефон сотрудника
    email VARCHAR(100), -- Email сотрудника
    position_id INTEGER NOT NULL, -- ID должности сотрудника
    kpi_id INTEGER, -- ID KPI сотрудника (определяет процент премии)
    department_id INTEGER NOT NULL, -- ID департамента, в котором работает сотрудник
    hire_date DATE NOT NULL, -- Дата приема на работу
    salary DECIMAL(10, 2) NOT NULL, -- Оклад сотрудника
    bonus DECIMAL(10, 2) DEFAULT 0, -- Премия сотрудника
    FOREIGN KEY (position_id) REFERENCES positions(id) ON DELETE RESTRICT,
    FOREIGN KEY (kpi_id) REFERENCES kpis(id) ON DELETE SET NULL,
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE
);

-- Добавляем внешний ключ для департаментов после создания таблицы сотрудников
ALTER TABLE departments ADD CONSTRAINT fk_head
FOREIGN KEY (head_id) REFERENCES employees(id) ON DELETE SET NULL;

-- Заполняем таблицу графиков работы
INSERT INTO work_schedules (name, hours_per_week, description) VALUES
('5/2', 40, 'Пятидневная рабочая неделя с двумя выходными'),
('2/2', 48, 'Два рабочих дня, два выходных, по 12 часов в день');

-- Заполняем таблицу грейдов
INSERT INTO grades (name, min_salary, max_salary, description) VALUES
('Junior', 50000, 80000, 'Начинающий специалист'),
('Middle', 80000, 120000, 'Опытный специалист'),
('Senior', 120000, 200000, 'Ведущий специалист');

-- Заполняем таблицу KPI
INSERT INTO kpis (name, bonus_percent, description) VALUES
('Низкий', 0.05, 'Не выполнен план'),
('Средний', 0.15, 'План выполнен'),
('Высокий', 0.30, 'План перевыполнен');

-- Заполняем таблицу департаментов (пока без руководителей)
INSERT INTO departments (name, phone, email, description) VALUES
('IT', '+79991234567', 'it@company.com', 'Отдел информационных технологий'),
('Финансы', '+79997654321', 'finance@company.com', 'Финансовый отдел');

-- Заполняем таблицу должностей
INSERT INTO positions (name, department_id, grade_id, schedule_id, salary) VALUES
('Разработчик', 1, 2, 1, 100000), -- Middle разработчик в IT, график 5/2
('Тестировщик', 1, 1, 1, 70000), -- Junior тестировщик в IT, график 5/2
('Бухгалтер', 2, 2, 1, 90000), -- Middle бухгалтер в Финансах, график 5/2
('Аналитик', 2, 3, 1, 150000); -- Senior аналитик в Финансах, график 5/2

-- Заполняем таблицу сотрудников (первые два)
INSERT INTO employees (first_name, last_name, middle_name, phone, email, position_id, kpi_id, department_id, hire_date, salary, bonus) VALUES
('Иван', 'Иванов', 'Иванович', '+79991112233', 'i.ivanov@company.com', 1, 3, 1, '2020-01-15', 100000, 30000),
('Петр', 'Петров', 'Петрович', '+79992223344', 'p.petrov@company.com', 3, 2, 2, '2019-05-10', 90000, 13500);

-- Обновляем департаменты, назначая руководителей
UPDATE departments SET head_id = 1 WHERE id = 1; -- Иванов руководит IT
UPDATE departments SET head_id = 2 WHERE id = 2; -- Петров руководит Финансами

-- Добавляем еще двух сотрудников (всего 4)
INSERT INTO employees (first_name, last_name, middle_name, phone, email, position_id, kpi_id, department_id, hire_date, salary, bonus) VALUES
('Сергей', 'Сергеев', 'Сергеевич', '+79993334455', 's.sergeev@company.com', 2, 1, 1, '2021-03-20', 70000, 3500),
('Анна', 'Аннова', 'Анновна', '+79994445566', 'a.annova@company.com', 4, 3, 2, '2018-11-05', 150000, 45000);