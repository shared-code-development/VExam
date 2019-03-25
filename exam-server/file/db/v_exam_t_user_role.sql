CREATE TABLE v_exam.t_user_role
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int(11),
    role_id int(11),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES v_exam.t_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES v_exam.t_role (id)
);
CREATE INDEX idx_user_id ON v_exam.t_user_role (user_id);
CREATE INDEX idx_role_id ON v_exam.t_user_role (role_id);
INSERT INTO v_exam.t_user_role (id, user_id, role_id) VALUES (1, 1, 1);