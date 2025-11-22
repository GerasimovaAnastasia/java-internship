INSERT INTO categories (name) VALUES
    ('Электроника'),
    ('Книги'),
    ('Одежда')
ON CONFLICT DO NOTHING;
INSERT INTO products (name, category_id, price) VALUES
    ('iPhone 15', 1, 999.99),
    ('MacBook Pro', 1, 2499.99),
    ('Война и мир', 2, 29.99),
    ('Джинсы', 3, 49.99)
ON CONFLICT DO NOTHING;