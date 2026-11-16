import { PUBLIC_API_URL } from "$env/static/public";
import { authFetch } from "$lib/utils/fetchUtils.js";

const readPosts = async (communityId) => {
    const response = await fetch(`${PUBLIC_API_URL}/api/communities/${communityId}/posts`);
    return await response.json();
};

const readPost = async (communityId, postId) => {
    const response = await fetch(`${PUBLIC_API_URL}/api/communities/${communityId}/posts/${postId}`);
    return await response.json();
};

const createPost = async (communityId, post) => {
    const response = await authFetch(`${PUBLIC_API_URL}/api/communities/${communityId}/posts`, 
        {
            headers: {
                "Content-Type": "application/json",
            },
            method: "POST",
            body: JSON.stringify(post),
        },
    );
    if (!response.ok) {
        throw new Error("Could not create post");
    }
    return await response.json();
};

const deletePost = async (communityId, postId) => {
    const response = await authFetch(`${PUBLIC_API_URL}/api/communities/${communityId}/posts/${postId}`, 
        {
            method: "DELETE",
        }
    );
    if (!response.ok) {
        throw new Error("Could not delete post");
    }
    return await response.json();
};

export { createPost, deletePost, readPost, readPosts };