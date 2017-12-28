-- DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS votes;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                    NOT NULL,
  email            VARCHAR                    NOT NULL,
  password         VARCHAR                    NOT NULL,
  registered       TIMESTAMP DEFAULT now()    NOT NULL,
  role             VARCHAR DEFAULT 'ROLE_USER'NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

-- CREATE TABLE user_roles
-- (
--   user_id INTEGER NOT NULL,
--   role    VARCHAR,
--   CONSTRAINT user_roles_idx UNIQUE (user_id, role),
--   FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
-- );

CREATE TABLE restaurants
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name              VARCHAR                   NOT NULL,
  address           VARCHAR                   NOT NULL,
  cookery           VARCHAR                   NOT NULL
);

CREATE TABLE menus
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id     INTEGER                   NOT NULL,
  date              DATE                      NOT NULL,
  main_course       VARCHAR                   NOT NULL,
  soup              VARCHAR,
  salad             VARCHAR,
  appetizer         VARCHAR,
  dessert           VARCHAR,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id           INTEGER                   NOT NULL,
  restaurant_id     INTEGER                   NOT NULL,
--   date_time         TIMESTAMP DEFAULT now()   NOT NULL,
  date              DATE DEFAULT now()        NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_userid_date_idx ON votes (user_id, date);