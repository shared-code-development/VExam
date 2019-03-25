CREATE TABLE v_exam.t_menu_role
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    menu_id int(11),
    role_id int(11)
);
CREATE INDEX idx_menu_id ON v_exam.t_menu_role (menu_id);
CREATE INDEX idx_role_id ON v_exam.t_menu_role (role_id);
INSERT INTO v_exam.t_menu_role (id, menu_id, role_id) VALUES (55, 3, 1);
INSERT INTO v_exam.t_menu_role (id, menu_id, role_id) VALUES (56, 30, 1);
INSERT INTO v_exam.t_menu_role (id, menu_id, role_id) VALUES (57, 10, 1);