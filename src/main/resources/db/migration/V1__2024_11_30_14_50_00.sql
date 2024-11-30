CREATE TABLE IF NOT EXISTS challenges (
 id SERIAL PRIMARY KEY,
 type VARCHAR(50) NOT NULL,
 question TEXT NOT NULL,
 correct_answer TEXT NOT NULL,
 time_limit INTEGER NOT NULL,
 points INTEGER NOT NULL
);

INSERT INTO challenges (type, question, correct_answer, time_limit, points) VALUES
 ('ARRAY', 'Scrie o funcție care găsește elementul maxim dintr-un array', 'return Arrays.stream(arr).max().getAsInt();', 120, 100),
  ('SQL', 'Scrie un query SQL pentru a găsi top 3 cele mai vândute produse', 'SELECT product_id, COUNT(*) as sales FROM orders GROUP BY product_id ORDER BY sales DESC LIMIT 3', 120, 100),
 ('THEORY', 'Ce este polimorfismul în OOP?', 'Polimorfismul este capacitatea unui obiect de a lua multe forme', 60, 50);