INSERT INTO USERS (username, email, password) VALUES
('Shimmer', 'test@gmail.com', '$2a$10$ZXDV5zl4UUfB9vvauKHsFeu0QdUalQNGNWiM.czYOnLwqfpXvv2eC'),
('Hitler', 'test@gmail.com2', '$2a$10$xzrPrLYtPfylp7TalaiRoe9eyhKDvWXmYlUZGX/CYTY0nBRk0A8Jm'),
('Anndreuis', 'Anndreuis@gmail.com', '$2a$10$JLw7afPltZa4aL2LnICzyO.1UuOPBPb9m5xSAQLf3U31oEMArR8/a');

INSERT INTO ACCOUNT_TYPE(account_type_name) VALUES
('YouTube'),
('Twitch'),
('Discord'),
('OP.GG'),
('LeagueOfLegendsAccount');

INSERT INTO LINKED_ACCOUNTS (account_identifier, user_id, account_type_id)
VALUES
( 'Andrew',
  (SELECT id FROM USERS WHERE username = 'Shimmer' LIMIT 1),
  (SELECT id FROM ACCOUNT_TYPE WHERE account_type_name = 'YouTube' LIMIT 1)
),
( 'Shimmer',
  (SELECT id FROM USERS WHERE username = 'Shimmer' LIMIT 1),
  (SELECT id FROM ACCOUNT_TYPE WHERE account_type_name = 'LeagueOfLegendsAccount' LIMIT 1)
),
( 'Hitler',
  (SELECT id FROM USERS WHERE username = 'Hitler' LIMIT 1),
  (SELECT id FROM ACCOUNT_TYPE WHERE account_type_name = 'LeagueOfLegendsAccount' LIMIT 1)
),
 ( 'Anndreuis',
   (SELECT id FROM USERS WHERE username = 'Anndreuis' LIMIT 1),
   (SELECT id FROM ACCOUNT_TYPE WHERE account_type_name = 'LeagueOfLegendsAccount' LIMIT 1)
 );

INSERT INTO TOURNAMENT_TYPE (name)
VALUES ('Single Elimination'),
       ('Double Elimination'),
       ('Round Robin'),
       ('Swiss');

INSERT INTO GAME (name)
VALUES ('League of Legends'),
       ('Valorant'),
       ('Dota 2'),
       ('Fortnite');


INSERT INTO TOURNAMENT (name, tournament_type_id, start_date, description, end_date, max_participants, game_id, host_id)
VALUES ('LoL World Championship', 1, '2024-11-01', 'The biggest LoL tournament', '2024-11-20', 16, 1, 1),
       ('Valorant Champions', 2, '2024-12-05', 'Valorant global tournament', '2024-12-15', 32, 2, 1),
       ('Dota 2 Invitational', 3, '2024-10-25', 'A Dota 2 tournament for top teams', '2024-11-05', 8, 3, 1);

INSERT INTO TOURNAMENT_PARTICIPANTS (tournament_id, user_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2);


INSERT INTO QUICK_PLAY (host_id, title, bid)
VALUES (1, 'Hitlers 1v1', 50.01);

INSERT INTO QUICK_PLAY (host_id, title, bid, challenger_id)
VALUES (2, 'Hitlerswefwe 1v1', 50.01, 3);