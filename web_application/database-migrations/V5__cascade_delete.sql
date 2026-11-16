-- constraint names are guesses: check yours with \d posts
ALTER TABLE posts DROP CONSTRAINT posts_community_id_fkey;
ALTER TABLE posts ADD CONSTRAINT posts_community_id_fkey
    FOREIGN KEY (community_id) REFERENCES communities(id) ON DELETE CASCADE;

ALTER TABLE posts DROP CONSTRAINT posts_parent_post_id_fkey;
ALTER TABLE posts ADD CONSTRAINT posts_parent_post_id_fkey
    FOREIGN KEY (parent_post_id) REFERENCES posts(id) ON DELETE CASCADE;