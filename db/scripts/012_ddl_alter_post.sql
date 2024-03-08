ALTER table auto_post
    ADD COLUMN photo_id int references auto_photo(id);