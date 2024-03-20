CREATE TABLE created_recipes_join_table
(
    user_id   BIGINT,
    recipe_id BIGINT,
    PRIMARY KEY (user_id, recipe_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);

CREATE TABLE favorite_recipes_join_table
(
    user_id   BIGINT,
    recipe_id BIGINT,
    PRIMARY KEY (user_id, recipe_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);