INSERT INTO users (username, password, enabled)
VALUES ('admin', 'admin', true);
INSERT INTO users (username, password, enabled)
VALUES ('test', 'test', true);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('test', 'ROLE_USER');
