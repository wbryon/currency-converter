CREATE TABLE currencies (
                            id SERIAL PRIMARY KEY,
                            code VARCHAR(3) NOT NULL,
                            name VARCHAR(50) NOT NULL,
                            rate DECIMAL(10, 4) NOT NULL,
                            last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE conversion_history (
                                    id SERIAL PRIMARY KEY,
                                    source_currency_code VARCHAR(3) NOT NULL,
                                    target_currency_code VARCHAR(3) NOT NULL,
                                    conversion_rate DECIMAL(10, 4) NOT NULL,
                                    amount DECIMAL(10, 2) NOT NULL,
                                    converted_amount DECIMAL(10, 2) NOT NULL,
                                    conversion_timestamp TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Начальные данные для таблицы валют
INSERT INTO currencies (code, name, rate) VALUES
                                              ('USD', 'United States Dollar', 1.0000),
                                              ('EUR', 'Euro', 0.8500),
                                              ('GBP', 'British Pound', 0.7500),
                                              ('RUB', 'Russian Ruble', 75.0000);