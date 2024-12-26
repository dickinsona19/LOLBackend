CREATE TABLE IF NOT EXISTS USERS (
    id INT AUTO_INCREMENT  NOT NULL,
    username VARCHAR(25) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ACCOUNT_TYPE (
    id INT AUTO_INCREMENT NOT NULL,
    account_type_name VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT PK_AccountTypes PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS LINKED_ACCOUNTS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    account_identifier VARCHAR(255) NOT NULL,
    account_type_id INT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE,
    CONSTRAINT fk_account_type FOREIGN KEY (account_type_id) REFERENCES ACCOUNT_TYPE(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS TOURNAMENT_TYPE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS GAME (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS TOURNAMENT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    tournament_type_id INT NOT NULL,
    start_date DATE NOT NULL,
    description VARCHAR(500),
    end_date DATE,
    max_participants INT NOT NULL,
    game_id INT NOT NULL,
    host_id INT,
    FOREIGN KEY (host_id) REFERENCES USERS(id),
    FOREIGN KEY (tournament_type_id) REFERENCES TOURNAMENT_TYPE(id),
    FOREIGN KEY (game_id) REFERENCES GAME(id)
);


CREATE TABLE IF NOT EXISTS TOURNAMENT_PARTICIPANTS (
    tournament_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (tournament_id, user_id),
    FOREIGN KEY (tournament_id) REFERENCES TOURNAMENT(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE
);

CREATE TABLE PASSWORD_RESET_TOKENS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    user_id INT NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS QUICK_PLAY(
    id INT AUTO_INCREMENT  NOT NULL,
    host_id INT NOT NULL UNIQUE,
    title VARCHAR(50) NOT NULL UNIQUE,
    bid DOUBLE,
    challenger_id INT,
    FOREIGN KEY (challenger_id) REFERENCES USERS(id),
    FOREIGN KEY (host_id) REFERENCES USERS(id)
);