 INSERT INTO menu_categories (name, description) VALUES
                                                    ('Appetizers', 'Start your meal with these delicious options'),
                                                    ('Main Course', 'Our chef\'s special main dishes'),
('Desserts', 'Sweet endings to your perfect meal'),
('Beverages', 'Refreshing drinks and cocktails');

 INSERT INTO menu_items (category_id, name, description, price, available) VALUES
 (1, 'Bruschetta', 'Toasted bread with fresh tomatoes and basil', 8.99, true),
(1, 'Calamari', 'Crispy fried squid with marinara sauce', 12.99, true),
(1, 'Caesar Salad', 'Fresh romaine lettuce with classic caesar dressing', 10.99, true),

 (2, 'Beef Steak', 'Premium cut beef with grilled vegetables', 29.99, true),
(2, 'Grilled Salmon', 'Fresh salmon with lemon butter sauce', 24.99, true),
(2, 'Pasta Carbonara', 'Classic carbonara with pancetta', 18.99, true),

 (3, 'Tiramisu', 'Classic Italian dessert', 8.99, true),
(3, 'Chocolate Cake', 'Rich chocolate layer cake', 7.99, true),
(3, 'Cheesecake', 'New York style cheesecake', 8.99, true),

 (4, 'Red Wine', 'House red wine', 6.99, true),
(4, 'Sparkling Water', 'Premium sparkling mineral water', 3.99, true),
(4, 'Coffee', 'Fresh brewed coffee', 2.99, true);