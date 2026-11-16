import * as postRepository from "../repositories/postRepository.js";

const create = async (c) => {
    const user = c.get("user");
    if (!user) {
        return c.json({ error: "Not logged in" }, 401);
    }

    const communityId = Number(c.req.param("communityId"));
    if (!Number.isInteger(communityId)) {
        return c.json({error: "Invalid community id"}, 400);
    }
    const post = await c.req.json();

    if (!post.title || !post.content) {
    return c.json({ error: "Missing required fields" }, 400);
    }

    const newPost = await postRepository.create(communityId, post, user.id);
    return c.json(newPost, 201);
};

const readAll = async (c) => {
    const communityId = Number(c.req.param("communityId"));
    if (!Number.isInteger(communityId)) {
        return c.json({error: "Invalid community id"}, 400);
    }
    const posts = await postRepository.readAll(communityId);
    return c.json(posts);
};

const readOne = async (c) => {
    const communityId = Number(c.req.param("communityId"));
    if (!Number.isInteger(communityId)) {
        return c.json({error: "Invalid community id"}, 400);
    }
    const postId = Number(c.req.param("postId"));
    if(!Number.isInteger(postId)) {
        return c.json({error: "Invalid post id"}, 400);
    }

    const post = await postRepository.readOne(communityId, postId);
    if(!post) {
        return c.json({error: "Post not found"}, 404);
    }
    return c.json(post);
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
    const deletedPost = await postRepository.deleteOne(communityId, postId, user.id);
    if(!deletedPost){
        return c.json({error: "Post not found"}, 404);
    }
    return c.json(deletedPost);
};

export { create, readAll, readOne, deleteOne };