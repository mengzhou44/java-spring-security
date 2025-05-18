CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT orders_users_id_fk
                            FOREIGN KEY (user_id) REFERENCES users (id)
                                ON DELETE CASCADE
);