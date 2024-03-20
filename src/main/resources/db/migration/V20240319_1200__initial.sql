CREATE TYPE dish_type_enum AS ENUM ('VEGETARIAN', 'VEGAN', 'OTHER');


CREATE TYPE quantity_type_enum AS ENUM ('KILOGRAMS', 'GRAMS', 'LITERS', 'MILLILITERS', 'ABSOLUTE', 'SPOONS', 'TEASPOONS');

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
    dish_type    dish_type_enum,
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
    quantity_type        quantity_type_enum,
    FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
);

grant select, insert, update on table recipes to ${application-user};
grant select, insert, update on table ingredients to ${application-user};
grant select, insert, update on table recipe_ingredients to ${application-user};