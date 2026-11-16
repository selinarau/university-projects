import {browser} from "$app/environment";
import * as commentsApi from "$lib/apis/commentsApi";

let commentState = $state({});

const initComments = async (communityId, postId) => {
    if(browser) {
        if (!commentState[communityId]) {
            commentState[communityId] = {};
        }
        commentState[communityId][postId] = await commentsApi.readComments(communityId, postId);
    }
};

const useCommentState = () => {
    return {
        get comments() {
            return commentState;
        },
        addComment: (communityId, postId, comment) => {
            commentsApi.createComment(communityId, postId, comment).then((newComment) => {
                const comments = commentState[communityId][postId] || [];
                comments.push(newComment);
                commentState[communityId][postId] = comments;
            })
            .catch((error) => console.error(error));
        },
        removeComment: (communityId, postId, commentId) => {
            commentsApi.deleteComment(communityId, postId, commentId).then(()=> {
                if (commentState[communityId] && commentState[communityId][postId]) {
                    commentState[communityId][postId] = commentState[communityId][postId].filter(
                        (c) => c.id !== commentId
                    );
                }
            })
            .catch((error) => console.error(error));
        },
    };
};

export { initComments, useCommentState };