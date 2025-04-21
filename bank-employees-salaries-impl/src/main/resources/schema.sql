-- Создание таблицы для отделов
CREATE TABLE departments (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(500),
    phone VARCHAR(20),
    email VARCHAR(100),
    head_id BIGINT  -- Внешний ключ на сотрудника (руководителя отдела)
);

COMMENT ON TABLE departments IS 'Таблица отделов компании';
COMMENT ON COLUMN departments.id IS 'Уникальный идентификатор отдела';
COMMENT ON COLUMN departments.name IS 'Наименование отдела';
COMMENT ON COLUMN departments.address IS 'Физический адрес отдела';
COMMENT ON COLUMN departments.phone IS 'Контактный телефон отдела';
COMMENT ON COLUMN departments.email IS 'Электронная почта отдела';
COMMENT ON COLUMN departments.head_id IS 'ID руководителя отдела (ссылка на сотрудника)';

-- Создание таблицы для грейдов
CREATE TABLE grades (
    id BIGINT PRIMARY KEY,
    name_grade VARCHAR(100) NOT NULL
);

COMMENT ON TABLE grades IS 'Таблица грейдов сотрудников';
COMMENT ON COLUMN grades.id IS 'Уникальный идентификатор грейда';
COMMENT ON COLUMN grades.name_grade IS 'Наименование грейда';

-- Создание таблицы для должностей
CREATE TABLE positions (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id BIGINT NOT NULL REFERENCES departments(id),
    grade_id BIGINT REFERENCES grades(id)
);

COMMENT ON TABLE positions IS 'Таблица должностей сотрудников';
COMMENT ON COLUMN positions.id IS 'Уникальный идентификатор должности';
COMMENT ON COLUMN positions.name IS 'Наименование должности';
COMMENT ON COLUMN positions.department_id IS 'ID отдела, к которому относится должность';
COMMENT ON COLUMN positions.grade_id IS 'ID грейда для должности';

-- Создание таблицы для графиков работы
CREATE TABLE work_schedules (
    id BIGINT PRIMARY KEY,
    work_day INTEGER NOT NULL CHECK (work_day >= 0 AND work_day <= 7),
    week_day INTEGER NOT NULL CHECK (week_day >= 0 AND week_day <= 7),
    work_hour INTEGER NOT NULL CHECK (work_hour >= 0 AND work_hour <= 24)
);

COMMENT ON TABLE work_schedules IS 'Таблица графиков работы';
COMMENT ON COLUMN work_schedules.id IS 'Уникальный идентификатор графика';
COMMENT ON COLUMN work_schedules.work_day IS 'Количество рабочих дней в неделю';
COMMENT ON COLUMN work_schedules.week_day IS 'Количество выходных дней в неделю';
COMMENT ON COLUMN work_schedules.work_hour IS 'Количество рабочих часов в день';

-- Создание таблицы для сотрудников
CREATE TABLE employees (
    id BIGINT PRIMARY KEY,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    department_id BIGINT REFERENCES departments(id),
    position_id BIGINT REFERENCES positions(id),
    work_schedule_id BIGINT REFERENCES work_schedules(id),
    date_of_admission DATE,
    date_of_dismissal DATE,
    job_status VARCHAR(20) CHECK (job_status IN ('New', 'Working', 'Fired'))
);

COMMENT ON TABLE employees IS 'Таблица сотрудников компании';
COMMENT ON COLUMN employees.id IS 'Уникальный идентификатор сотрудника';
COMMENT ON COLUMN employees.last_name IS 'Фамилия сотрудника';
COMMENT ON COLUMN employees.first_name IS 'Имя сотрудника';
COMMENT ON COLUMN employees.middle_name IS 'Отчество сотрудника';
COMMENT ON COLUMN employees.phone IS 'Контактный телефон сотрудника';
COMMENT ON COLUMN employees.email IS 'Электронная почта сотрудника';
COMMENT ON COLUMN employees.department_id IS 'ID отдела сотрудника';
COMMENT ON COLUMN employees.position_id IS 'ID должности сотрудника';
COMMENT ON COLUMN employees.work_schedule_id IS 'ID графика работы сотрудника';
COMMENT ON COLUMN employees.date_of_admission IS 'Дата приема на работу';
COMMENT ON COLUMN employees.date_of_dismissal IS 'Дата увольнения';
COMMENT ON COLUMN employees.job_status IS 'Статус сотрудника (New, Working, Fired)';

-- Добавляем внешний ключ для руководителей отделов после создания таблицы employees
ALTER TABLE departments ADD CONSTRAINT fk_departments_head
    FOREIGN KEY (head_id) REFERENCES employees(id);

-- Создание таблицы для данных о посещаемости
CREATE TABLE attendance_data (
    id BIGINT PRIMARY KEY,
    date_att DATE NOT NULL,
    employee_id BIGINT NOT NULL REFERENCES employees(id),
    status VARCHAR(20) NOT NULL CHECK (status IN (
        'Working', 'Absent', 'Medical', 'Vacation',
        'Unpaid Leave', 'Maternity Leave', 'Study Leave'
    ))
);

COMMENT ON TABLE attendance_data IS 'Таблица данных о посещаемости сотрудников';
COMMENT ON COLUMN attendance_data.id IS 'Уникальный идентификатор записи';
COMMENT ON COLUMN attendance_data.date_att IS 'Дата посещения';
COMMENT ON COLUMN attendance_data.employee_id IS 'ID сотрудника';
COMMENT ON COLUMN attendance_data.status IS 'Статус присутствия';

-- Создание таблицы для KPI
CREATE TABLE kpis (
    id BIGINT PRIMARY KEY,
    personal_kpi NUMERIC(5,2) NOT NULL,
    team_kpi NUMERIC(5,2) NOT NULL,
    common_kpi NUMERIC(5,2) NOT NULL,
    employee_id BIGINT NOT NULL REFERENCES employees(id),
    month VARCHAR(2) NOT NULL,
    year VARCHAR(4) NOT NULL
);

COMMENT ON TABLE kpis IS 'Таблица показателей KPI сотрудников';
COMMENT ON COLUMN kpis.id IS 'Уникальный идентификатор записи KPI';
COMMENT ON COLUMN kpis.personal_kpi IS 'Персональный KPI сотрудника';
COMMENT ON COLUMN kpis.team_kpi IS 'Командный KPI';
COMMENT ON COLUMN kpis.common_kpi IS 'Общий KPI';
COMMENT ON COLUMN kpis.employee_id IS 'ID сотрудника';
COMMENT ON COLUMN kpis.month IS 'Месяц KPI (формат MM)';
COMMENT ON COLUMN kpis.year IS 'Год KPI (формат YYYY)';

-- Создание таблицы для данных о зарплатах
CREATE TABLE salaries_data (
    id BIGINT PRIMARY KEY,
    wage NUMERIC(10,2) NOT NULL,
    bonus NUMERIC(10,2) NOT NULL,
    position_id BIGINT NOT NULL REFERENCES positions(id)
);

COMMENT ON TABLE salaries_data IS 'Таблица базовых данных о зарплатах по должностям';
COMMENT ON COLUMN salaries_data.id IS 'Уникальный идентификатор записи';
COMMENT ON COLUMN salaries_data.wage IS 'Оклад';
COMMENT ON COLUMN salaries_data.bonus IS 'Бонус';
COMMENT ON COLUMN salaries_data.position_id IS 'ID должности';

-- Создание таблицы для начисленных зарплат
CREATE TABLE salaries (
    id BIGINT PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES employees(id),
    department_id BIGINT NOT NULL REFERENCES departments(id),
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    amount NUMERIC(12,2) NOT NULL
);

COMMENT ON TABLE salaries IS 'Таблица начисленных зарплат сотрудников';
COMMENT ON COLUMN salaries.id IS 'Уникальный идентификатор записи о зарплате';
COMMENT ON COLUMN salaries.employee_id IS 'ID сотрудника';
COMMENT ON COLUMN salaries.department_id IS 'ID отдела';
COMMENT ON COLUMN salaries.period_start IS 'Начало периода начисления';
COMMENT ON COLUMN salaries.period_end IS 'Конец периода начисления';
COMMENT ON COLUMN salaries.amount IS 'Сумма начисленной зарплаты';

-- Создание индексов для улучшения производительности запросов
CREATE INDEX idx_attendance_data_employee_id ON attendance_data(employee_id);
CREATE INDEX idx_attendance_data_date ON attendance_data(date_att);
CREATE INDEX idx_employees_department_id ON employees(department_id);
CREATE INDEX idx_employees_position_id ON employees(position_id);
CREATE INDEX idx_kpis_employee_id ON kpis(employee_id);
CREATE INDEX idx_salaries_employee_id ON salaries(employee_id);
CREATE INDEX idx_salaries_department_id ON salaries(department_id);
CREATE INDEX idx_salaries_period ON salaries(period_start, period_end);

-- Создаем таблицу для хранения праздничных дней
CREATE TABLE holidays (
    id SERIAL PRIMARY KEY,
    holiday_date DATE NOT NULL UNIQUE,
    description VARCHAR(255),
    is_recurring BOOLEAN DEFAULT FALSE, -- Флаг для ежегодных праздников
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE holidays IS 'Таблица праздничных дней';
COMMENT ON COLUMN holidays.holiday_date IS 'Дата праздника';
COMMENT ON COLUMN holidays.description IS 'Описание праздника';
COMMENT ON COLUMN holidays.is_recurring IS 'Флаг ежегодного праздника';

-- Создаем индекс для быстрого поиска по дате
CREATE INDEX idx_holidays_date ON holidays(holiday_date);

-- Создаем таблицу для хранения истории расчетов
CREATE TABLE salary_calculation_log (
    id BIGINT PRIMARY KEY,
    calculation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    calculated_by BIGINT NOT NULL REFERENCES employees(id),
    employee_id BIGINT NOT NULL REFERENCES employees(id),
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    base_salary NUMERIC(12,2) NOT NULL,
    base_bonus NUMERIC(12,2) NOT NULL,
    kpi_coefficient NUMERIC(5,2) NOT NULL,
    experience_coefficient NUMERIC(5,2) NOT NULL,
    overtime_bonus NUMERIC(12,2) NOT NULL,
    attendance_coefficient NUMERIC(5,2) NOT NULL,
    calculated_bonus NUMERIC(12,2) NOT NULL,
    gross_salary NUMERIC(12,2) NOT NULL,
    tax_amount NUMERIC(12,2) NOT NULL,
    net_salary NUMERIC(12,2) NOT NULL,
    calculation_details JSONB NOT NULL,
    is_approved BOOLEAN DEFAULT FALSE,
    approved_by BIGINT REFERENCES employees(id),
    approval_date TIMESTAMP
);

COMMENT ON TABLE salary_calculation_log IS 'Таблица для хранения истории расчетов заработной платы сотрудников';

COMMENT ON COLUMN salary_calculation_log.id IS 'Уникальный идентификатор записи в логе расчетов';
COMMENT ON COLUMN salary_calculation_log.calculation_date IS 'Дата и время выполнения расчета';
COMMENT ON COLUMN salary_calculation_log.calculated_by IS 'ID сотрудника, который выполнил расчет (ссылка на таблицу employees)';
COMMENT ON COLUMN salary_calculation_log.employee_id IS 'ID сотрудника, для которого выполнен расчет (ссылка на таблицу employees)';
COMMENT ON COLUMN salary_calculation_log.period_start IS 'Начальная дата расчетного периода';
COMMENT ON COLUMN salary_calculation_log.period_end IS 'Конечная дата расчетного периода';
COMMENT ON COLUMN salary_calculation_log.base_salary IS 'Базовый оклад сотрудника на момент расчета';
COMMENT ON COLUMN salary_calculation_log.base_bonus IS 'Базовый бонус сотрудника на момент расчета';
COMMENT ON COLUMN salary_calculation_log.kpi_coefficient IS 'Коэффициент KPI, примененный при расчете (влияет на бонус)';
COMMENT ON COLUMN salary_calculation_log.experience_coefficient IS 'Коэффициент стажа, примененный при расчете (1% за каждый год стажа)';
COMMENT ON COLUMN salary_calculation_log.overtime_bonus IS 'Сумма бонуса за переработки (оплата сверхурочных часов)';
COMMENT ON COLUMN salary_calculation_log.attendance_coefficient IS 'Коэффициент посещаемости (учитывает пропущенные дни)';
COMMENT ON COLUMN salary_calculation_log.calculated_bonus IS 'Итоговый рассчитанный бонус с учетом всех коэффициентов';
COMMENT ON COLUMN salary_calculation_log.gross_salary IS 'Зарплата до вычета налогов (оклад + бонусы)';
COMMENT ON COLUMN salary_calculation_log.tax_amount IS 'Сумма налога (13% от gross_salary)';
COMMENT ON COLUMN salary_calculation_log.net_salary IS 'Зарплата к выплате (на руки после вычета налогов)';
COMMENT ON COLUMN salary_calculation_log.is_approved IS 'Флаг утверждения расчета (true - утвержден, false - не утвержден)';
COMMENT ON COLUMN salary_calculation_log.approved_by IS 'ID сотрудника, который утвердил расчет (ссылка на таблицу employees)';
COMMENT ON COLUMN salary_calculation_log.approval_date IS 'Дата и время утверждения расчета';
COMMENT ON COLUMN salary_calculation_log.calculation_details IS
'Детальная информация о расчете в формате JSON. Содержит:
- working_days: количество рабочих дней в периоде
- holidays: количество праздничных дней
- worked_days: фактически отработанные дни
- worked_hours: фактически отработанные часы
- overtime_details: информация о переработках (часы, ставка)
- kpi_details: показатели KPI (личный, командный)
- attendance_rate: коэффициент посещаемости
- approval_comment: комментарий при утверждении (если есть)
- revocation: информация об отмене утверждения (если было)';

-- Индексы для ускорения поиска
CREATE INDEX idx_salary_calculation_log_employee ON salary_calculation_log(employee_id);
CREATE INDEX idx_salary_calculation_log_period ON salary_calculation_log(period_start, period_end);
CREATE INDEX idx_salary_calculation_log_calculated ON salary_calculation_log(calculated_by);

-- Добавляем основные праздники (пример для России)
INSERT INTO holidays (holiday_date, description, is_recurring) VALUES
('2023-01-01', 'Новый год', TRUE),
('2023-01-02', 'Праздничный день', TRUE),
('2023-01-07', 'Рождество Христово', TRUE),
('2023-02-23', 'День защитника Отечества', TRUE),
('2023-03-08', 'Международный женский день', TRUE),
('2023-05-01', 'Праздник Весны и Труда', TRUE),
('2023-05-09', 'День Победы', TRUE),
('2023-06-12', 'День России', TRUE),
('2023-11-04', 'День народного единства', TRUE);

-- Заполнение таблицы departments (отделы)
INSERT INTO departments (id, name, address, phone, email, head_id) VALUES
(1, 'IT отдел', 'ул. Программистов, 1', '+74951112233', 'it@company.com', NULL),
(2, 'Бухгалтерия', 'ул. Финансовая, 2', '+74952223344', 'accounting@company.com', NULL);

-- Заполнение таблицы grades (грейды)
INSERT INTO grades (id, name_grade) VALUES
(1, 'Junior'),
(2, 'Middle'),
(3, 'Senior');

-- Заполнение таблицы work_schedules (графики работы)
INSERT INTO work_schedules (id, work_day, week_day, work_hour) VALUES
(1, 5, 2, 8),  -- 5 рабочих дней, 2 выходных, 8 часов в день
(2, 4, 3, 6);  -- 4 рабочих дня, 3 выходных, 6 часов в день

-- Заполнение таблицы positions (должности)
INSERT INTO positions (id, name, department_id, grade_id) VALUES
(1, 'Разработчик', 1, 3),  -- Senior разработчик в IT отделе
(2, 'Бухгалтер', 2, 2);    -- Middle бухгалтер в бухгалтерии

-- Заполнение таблицы employees (сотрудники) - 4 записи
INSERT INTO employees (id, last_name, first_name, middle_name, phone, email, department_id, position_id, work_schedule_id, date_of_admission, date_of_dismissal, job_status) VALUES
(1, 'Иванов', 'Иван', 'Иванович', '+79161112233', 'ivanov@company.com', 1, 1, 1, '2020-01-15', NULL, 'Working'),
(2, 'Петрова', 'Мария', 'Сергеевна', '+79162223344', 'petrova@company.com', 2, 2, 1, '2021-03-10', NULL, 'Working'),
(3, 'Сидоров', 'Алексей', 'Петрович', '+79163334455', 'sidorov@company.com', 1, 1, 1, '2022-05-20', NULL, 'Working'),
(4, 'Кузнецова', 'Елена', 'Владимировна', '+79164445566', 'kuznetsova@company.com', 2, 2, 2, '2023-02-01', NULL, 'New');

-- Обновляем head_id в departments после создания сотрудников
UPDATE departments SET head_id = 1 WHERE id = 1;  -- Иванов - руководитель IT отдела
UPDATE departments SET head_id = 2 WHERE id = 2;  -- Петрова - руководитель бухгалтерии

-- Заполнение таблицы attendance_data (посещаемость)
INSERT INTO attendance_data (id, date_att, employee_id, status) VALUES
(1, '2023-10-01', 1, 'Working'),
(2, '2023-10-01', 2, 'Working'),
(3, '2023-10-02', 1, 'Vacation'),
(4, '2023-10-02', 2, 'Working');

-- Заполнение таблицы kpis (KPI)
INSERT INTO kpis (id, personal_kpi, team_kpi, common_kpi, employee_id, month, year) VALUES
(1, 85.5, 75.0, 80.2, 1, '10', '2023'),
(2, 90.0, 78.5, 84.2, 2, '10', '2023');

-- Заполнение таблицы salaries_data (данные о зарплатах)
INSERT INTO salaries_data (id, wage, bonus, position_id) VALUES
(1, 150000.00, 30000.00, 1),  -- Зарплата разработчика
(2, 120000.00, 20000.00, 2);  -- Зарплата бухгалтера

-- Заполнение таблицы salaries (начисленные зарплаты)
INSERT INTO salaries (id, employee_id, department_id, period_start, period_end, amount) VALUES
(1, 1, 1, '2023-10-01', '2023-10-31', 180000.00),
(2, 2, 2, '2023-10-01', '2023-10-31', 140000.00);

-- Заполнение таблицы salary_calculation_log тестовыми данными
INSERT INTO salary_calculation_log (
    id, calculation_date, calculated_by, employee_id, period_start, period_end,
    base_salary, base_bonus, kpi_coefficient, experience_coefficient,
    overtime_bonus, attendance_coefficient, calculated_bonus,
    gross_salary, tax_amount, net_salary, calculation_details, is_approved, approved_by, approval_date
) VALUES
-- Расчет для Иванова И.И. (IT отдел) за октябрь 2023
(
    1, '2023-10-25 14:30:00', 10, 1, '2023-10-01', '2023-10-31',
    150000.00, 30000.00, 1.15, 1.05,
    9375.00, 0.95, 40425.00,
    190425.00, 24755.25, 165669.75,
    '{
        "working_days": 22,
        "holidays": 1,
        "worked_days": 20,
        "worked_hours": 160,
        "overtime_details": {
            "hours": 5,
            "rate": 0.5,
            "hourly_rate": 937.50
        },
        "kpi_details": {
            "personal": 92.5,
            "team": 85.0
        },
        "attendance_rate": 0.95
    }',
    TRUE, 5, '2023-10-26 09:15:00'
),

-- Расчет для Петровой М.С. (Бухгалтерия) за октябрь 2023
(
    2, '2023-10-25 14:35:00', 10, 2, '2023-10-01', '2023-10-31',
    120000.00, 20000.00, 1.08, 1.02,
    0.00, 1.00, 22032.00,
    142032.00, 18464.16, 123567.84,
    '{
        "working_days": 22,
        "holidays": 1,
        "worked_days": 22,
        "worked_hours": 176,
        "overtime_details": {
            "hours": 0,
            "rate": 0.5,
            "hourly_rate": 681.82
        },
        "kpi_details": {
            "personal": 89.0,
            "team": 82.0
        },
        "attendance_rate": 1.00
    }',
    TRUE, 5, '2023-10-26 09:20:00'
),

-- Расчет для Сидорова А.П. (IT отдел) за октябрь 2023 (не утвержден)
(
    3, '2023-10-25 14:40:00', 10, 3, '2023-10-01', '2023-10-31',
    150000.00, 30000.00, 1.10, 1.03,
    5625.00, 0.91, 32799.00,
    182799.00, 23763.87, 159035.13,
    '{
        "working_days": 22,
        "holidays": 1,
        "worked_days": 20,
        "worked_hours": 160,
        "overtime_details": {
            "hours": 3,
            "rate": 0.5,
            "hourly_rate": 937.50
        },
        "kpi_details": {
            "personal": 88.0,
            "team": 80.0
        },
        "attendance_rate": 0.91,
        "approval_comment": "Требуется проверка переработок"
    }',
    FALSE, NULL, NULL
),

-- Расчет для Кузнецовой Е.В. (Бухгалтерия) за октябрь 2023
(
    4, '2023-10-25 14:45:00', 10, 4, '2023-10-01', '2023-10-31',
    120000.00, 20000.00, 1.00, 1.00,
    0.00, 0.86, 17200.00,
    137200.00, 17836.00, 119364.00,
    '{
        "working_days": 22,
        "holidays": 1,
        "worked_days": 19,
        "worked_hours": 152,
        "overtime_details": {
            "hours": 0,
            "rate": 0.5,
            "hourly_rate": 681.82
        },
        "kpi_details": {
            "personal": 85.0,
            "team": 78.0
        },
        "attendance_rate": 0.86
    }',
    TRUE, 5, '2023-10-26 09:25:00'
),

-- Расчет для Иванова И.И. (IT отдел) за ноябрь 2023
(
    5, '2023-11-25 14:30:00', 10, 1, '2023-11-01', '2023-11-30',
    150000.00, 30000.00, 1.18, 1.05,
    11250.00, 0.96, 42984.00,
    192984.00, 25087.92, 167896.08,
    '{
        "working_days": 21,
        "holidays": 2,
        "worked_days": 20,
        "worked_hours": 162,
        "overtime_details": {
            "hours": 6,
            "rate": 0.5,
            "hourly_rate": 937.50
        },
        "kpi_details": {
            "personal": 95.0,
            "team": 88.0
        },
        "attendance_rate": 0.96
    }',
    TRUE, 5, '2023-11-26 09:15:00'
),

-- Расчет для Петровой М.С. (Бухгалтерия) за ноябрь 2023 (отклонен)
(
    6, '2023-11-25 14:35:00', 10, 2, '2023-11-01', '2023-11-30',
    120000.00, 20000.00, 1.05, 1.02,
    0.00, 0.95, 21399.00,
    141399.00, 18381.87, 123017.13,
    '{
        "working_days": 21,
        "holidays": 2,
        "worked_days": 20,
        "worked_hours": 168,
        "overtime_details": {
            "hours": 0,
            "rate": 0.5,
            "hourly_rate": 681.82
        },
        "kpi_details": {
            "personal": 87.0,
            "team": 80.0
        },
        "attendance_rate": 0.95,
        "revocation": {
            "date": "2023-11-26T10:30:00",
            "by": 5,
            "reason": "Необходима корректировка по больничному"
        }
    }',
    FALSE, NULL, NULL
);