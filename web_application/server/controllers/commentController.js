import * as commentRepository from "../repositories/commentRepository.js";

const create = async (c) => {
    const user = c.get("user");
    if (!user) {
        return c.json({ error: "Not logged in" }, 401);
    }

    const communityId = Number(c.req.param("communityId"));
    if (!Number.isInteger(communityId)) {
        return c.json({error: "Invalid community id"}, 400);
    }
    const postId = Number(c.req.param("postId"));
    if(!Number.isInteger(postId)) {
        return c.json({error: "Invalid post id"}, 400);
    }
    const comment = await c.req.json();

    if (!comment.content) {
    return c.json({ error: "Missing required fields" }, 400);
    }

    const newComment = await commentRepository.create(communityId, postId, comment, user.id);
    return c.json(newComment, 201);
};

const readAll = async (c) => {
    const communityId = Number(c.req.param("communityId"));
    if (!Number.isInteger(communityId)) {
        return c.json({error: "Invalid community id"}, 400);
    }

    const postId = Number(c.req.param("postId"));
    if(!Number.isInteger(postId)) {
        return c.json({error: "Invalid post id"}, 400);
    }

    const comments = await commentRepository.readAll(communityId, postId);
    return c.json(comments);
};

const deleteOne = async (c) => {
    const user = c.get("user");
    if (!user) {
        return c.json({ error: "Not logged in" }, 401);
    }

    const communityId = Number(c.req.param("communityId"));
    if (!Number.isInteger(communityId)) {
        return c.json({error: "Invalid community id"}, 400);
    }

    const postId = Number(c.req.param("postId"));
    if(!Number.isInteger(postId)) {
        return c.json({error: "Invalid post id"}, 400);
    }

    const commentId = Number(c.req.param("commentId"));
    if (!Number.isInteger(commentId)){
        return c.json({error: "Invalid comment id"}, 400);
    }

    const deletedComment = await commentRepository.deleteOne(communityId, postId, commentId, user.id);
    if (!deletedComment) {
        return c.json({error: "Comment not found"}, 404);
    }
    return c.json(deletedComment);
};

export { create, readAll, deleteOne };