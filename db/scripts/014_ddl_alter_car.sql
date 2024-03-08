ALTER TABLE car
    ADD COLUMN brand_id int references brand (id);