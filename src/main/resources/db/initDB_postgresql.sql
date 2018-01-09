DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                    NOT NULL,
  email            VARCHAR                    NOT NULL,
  password         VARCHAR                    NOT NULL,
  registered       TIMESTAMP DEFAULT now()    NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE       NOT NULL,
  role             VARCHAR DEFAULT 'ROLE_USER'NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE restaurants
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name              VARCHAR                   NOT NULL,
  address           VARCHAR                   NOT NULL,
  cookery           VARCHAR                   NOT NULL
);

CREATE TABLE dishes
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name              VARCHAR                     NOT NULL,
  price             NUMERIC(5,2)                NOT NULL,
  restaurant_id     INTEGER                     NOT NULL,
  date              DATE DEFAULT current_date   NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  user_id           INTEGER                   NOT NULL,
  restaurant_id     INTEGER                   NOT NULL,
  date              DATE DEFAULT current_date NOT NULL,
  PRIMARY KEY (user_id, date),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_userid_date_idx ON votes (user_id, date);