import { browser } from "$app/environment";
import * as postsApi from "$lib/apis/postsApi";

let postState  = $state({});

const initPosts = async (communityId) => {
    if(browser) {
        postState[communityId] = await postsApi.readPosts(communityId);
    }
};

const initPost = async (communityId, postId, loadedPost) => {
    if (!browser) {
        return;
    }
    const post = loadedPost ?? await postsApi.readPost(communityId, postId);
    if (!postState[communityId]) {
        postState[communityId] = [];
    }
    const index = postState[communityId].findIndex((p) => p.id === Number(postId));
    if (post && post.id) {
        if (index !== -1) {
            postState[communityId][index] = post;
        } else {
            postState[communityId].push(post);
        }
    }
};

const usePostState = () => {
    return {
        get posts() {
            return postState;
        },
        addPost: (communityId, post) => {
            postsApi.createPost(communityId, post).then((newPost) => {
                const posts = postState[communityId] || [];
                posts.push(newPost);
                postState[communityId] = posts;
            })
            .catch((error) => console.error(error));
        },
        removePost: (communityId, postId) => {
            postsApi.deletePost(communityId, postId).then(() => {
                postState[communityId] = postState[communityId].filter(
                    (p) => p.id !== Number(postId));
                })
                .catch((error) => console.error(error));
            },
    };
};

export { initPost, initPosts, usePostState };