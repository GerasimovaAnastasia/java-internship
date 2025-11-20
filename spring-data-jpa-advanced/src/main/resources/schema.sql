INSERT INTO categories (name) VALUES
    ('Электроника'),
    ('Книги'),
    ('Одежда')
ON CONFLICT DO NOTHING;