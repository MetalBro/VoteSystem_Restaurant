-- DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM dishes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, ROLE) VALUES
  ('User', 'user@yandex.ru', 'password', 'ROLE_USER'),        --100000
  ('Admin', 'admin@gmail.com', 'admin', 'ROLE_ADMIN'),        --100001
  ('User_1', 'user@mail.ru', 'password_1', 'ROLE_USER'),      --100002
  ('User_2', 'user@bk.ru', 'password_2', 'ROLE_USER'),        --100003
  ('User_3', 'user@list.ru', 'password_3', 'ROLE_USER');      --100004

INSERT INTO restaurants (name, address, cookery) VALUES
  ('Nar-Sharab', 'Naberejnaya, 121', 'Caucasian'),            --100005
  ('Svoya kompanya', 'Lesoparkovaya, 15', 'Russian'),         --100006
  ('Pizzburg', 'Prospect Lenina, 29', 'Italian');             --100007

INSERT INTO dishes (name, price, restaurant_id, date) VALUES
  ('Харчо', 500, 100005, '2017-06-21'),
  ('Котлеты', 239.1, 100005, '2017-06-21'),
  ('Пюре', 109.2, 100005, '2017-06-21'),
  ('Щи', 500, 100006, '2016-05-31'),
  ('Драники', 110.9, 100006, '2016-05-31'),
  ('Оливье', 221.2, 100006, '2016-05-31'),
  ('Компот', 50, 100006, '2016-05-31'),
  ('Солянка', 500, 100007, '2017-10-11'),
  ('Овощной салат', 121.3, 100007, '2017-10-11'),
  ('Чай', 50, 100007, '2017-10-11'),
  ('Борщ', 500, 100007, '2017-10-12'),
  ('Печенка', 210.5, 100007, '2017-10-12'),
  ('Кисель', 50, 100007, '2017-10-12');


-- INSERT INTO user_roles (role, user_id) VALUES
--   ('ROLE_USER', 100000),
--   ('ROLE_ADMIN', 100001);

-- INSERT INTO meals (date_time, description, calories, user_id) VALUES
--   ('2015-05-30 10:00:00', 'Завтрак', 500, 100000),
--   ('2015-05-30 13:00:00', 'Обед', 1000, 100000),
--   ('2015-05-30 20:00:00', 'Ужин', 500, 100000),
--   ('2015-05-31 10:00:00', 'Завтрак', 500, 100000),
--   ('2015-05-31 13:00:00', 'Обед', 1000, 100000),
--   ('2015-05-31 20:00:00', 'Ужин', 510, 100000),
--   ('2015-06-01 14:00:00', 'Админ ланч', 510, 100001),
--   ('2015-06-01 21:00:00', 'Админ ужин', 1500, 100001);
