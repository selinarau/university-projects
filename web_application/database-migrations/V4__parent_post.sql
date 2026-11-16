ALTER TABLE posts ADD COLUMN
    parent_post_id INTEGER REFERENCES posts(id) ON DELETE CASCADE;

CREATE INDEX ON posts (parent_post_id);