CREATE TABLE users
(
    user_id   SERIAL PRIMARY KEY,
    user_name VARCHAR(255)
);

CREATE TABLE ingredients
(
    ingredient_id SERIAL PRIMARY KEY,
    name          VARCHAR(255)
);

CREATE TABLE recipes
(
    recipe_id    SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    user_id      BIGINT,
    dish_type    VARCHAR(255),
    servings     INT,
    instructions TEXT,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE recipe_ingredients
(
    recipe_ingredient_id SERIAL PRIMARY KEY,
    recipe_id            BIGINT,
    name                 VARCHAR(255),
    ingredient_id        BIGINT,
    quantity_amount      DOUBLE PRECISION,
    quantity_unit        VARCHAR(50),
    quantity_type        VARCHAR(255),
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
);

grant select, insert, update on table recipes to ${application-user};
grant select, insert, update on table ingredients to ${application-user};
grant select, insert, update on table recipe_ingredients to ${application-user};