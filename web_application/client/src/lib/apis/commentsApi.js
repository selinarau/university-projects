import { PUBLIC_API_URL } from "$env/static/public";
import { authFetch } from "$lib/utils/fetchUtils.js";

const readComments = async (communityId, postId) => {
    const response = await fetch(`${PUBLIC_API_URL}/api/communities/${communityId}/posts/${postId}/comments`);
    return await response.json(); 
};

const createComment = async (communityId, postId, comment) => {
    const response = await authFetch(`${PUBLIC_API_URL}/api/communities/${communityId}/posts/${postId}/comments`,
        {
            headers: {
                "Content-Type": "application/json",
            },
            method: "POST",
            body: JSON.stringify(comment),
        });
    if (!response.ok) {
        throw new Error("Could not create comment");
    }
    return await response.json();
};

const deleteComment = async (communityId, postId, commentId) =>{
    const response = await authFetch(`${PUBLIC_API_URL}/api/communities/${communityId}/posts/${postId}/comments/${commentId}`,
        {
            method: "DELETE",
        },
    );
    if (!response.ok) {
        throw new Error("Could not delete comment");
    }
    return await response.json();
};

export { createComment, deleteComment, readComments };