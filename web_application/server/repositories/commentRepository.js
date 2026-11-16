import postgres from "postgres";

const sql = new postgres();

const create = async (communityId, postId, comment, userId) => {
    const result = await sql `INSERT INTO posts
        (community_id, content, parent_post_id, user_id)
        VALUES (${communityId}, ${comment.content}, ${postId}, ${userId})
        RETURNING *;`;
    return result[0];
};

const readAll = async (communityId, postId) => {
    return await sql `SELECT posts.*, users.username
        FROM posts
        JOIN users ON users.id = posts.user_id
        WHERE posts.community_id = ${communityId}
          AND posts.parent_post_id = ${postId};`;
};

const deleteOne = async (communityId, postId, commentId, userId) => {
    const result = await sql `DELETE FROM posts
        WHERE community_id = ${communityId} AND parent_post_id = ${postId} AND id = ${commentId} AND (
        user_id = ${userId}
        OR EXISTS (
          SELECT 1 FROM communities
          WHERE id = ${communityId} AND user_id = ${userId}
            )
        )
        RETURNING *;`;
    return result[0];
};


export { create, readAll, deleteOne };