import postgres from "postgres";

const sql = postgres();

const create = async (communityId, post, userId) => {
    const result = await sql `INSERT INTO posts
        (community_id, title, content, user_id)
        VALUES (${communityId}, ${post.title}, ${post.content}, ${userId})
        RETURNING *;`;
    return result[0];
};


const readAll = async (communityId) => {
    return await sql `SELECT posts.*, users.username
        FROM posts
        JOIN users ON users.id = posts.user_id
        WHERE posts.community_id = ${communityId} AND posts.parent_post_id IS NULL;`;
};

const readOne = async (communityId, postId) => {
    const result = await sql `SELECT posts.*, users.username
        FROM posts
        JOIN users ON users.id = posts.user_id
        WHERE posts.community_id = ${communityId}
          AND posts.id = ${postId}
          AND posts.parent_post_id IS NULL;`;
    return result[0];
};

const deleteOne = async (communityId, postId, userId) => {
    const result = await sql `DELETE FROM posts
        WHERE community_id = ${communityId} AND id = ${postId} AND (
        user_id = ${userId}
        OR EXISTS (
          SELECT 1 FROM communities
          WHERE id = ${communityId} AND user_id = ${userId}
            )
        )
        RETURNING *;`;
    return result[0];
};

export { create, readAll, readOne, deleteOne };