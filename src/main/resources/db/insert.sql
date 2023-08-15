SET FOREIGN_KEY_CHECKS = 0;

truncate table address;
truncate table users;
truncate table notification;
truncate table media;
truncate table media_reactions;

INSERT INTO address(id, house_number, street, `state`, country) VALUES
(100, '4', 'Herbert Macaulay street', 'Lagos', 'Nigeria'),
(101, '4', 'Herbert Macaulay street', 'Lagos', 'Nigeria'),
(102, '4', 'Herbert Macaulay street', 'Lagos', 'Nigeria'),
(103, '4', 'Herbert Macaulay street', 'Lagos', 'Nigeria'),
(104, '4', 'Herbert Macaulay street', 'Lagos', 'Nigeria'),
(105, '4', 'Herbert Macaulay street', 'Lagos', 'Nigeria'),
(106, '4', 'Herbert Macaulay street', 'Lagos', 'Nigeria');

INSERT INTO users(id, email, password, is_active, address_id) VALUES
(500, 'test@email.com', 'password', 0, 100),
(501, 'fest@email.com', 'password', 0, 101),
(502, 'gest@email.com', 'password', 0, 102),
(503, 'hest@email.com', 'password', 0, 103),
(504, 'iest@email.com', 'password', 0, 104),
(505, 'kest@email.com', 'password', 0, 105),
(506, 'jest@email.com', 'password', 0, 106);
