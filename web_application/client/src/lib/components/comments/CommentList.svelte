<script>
    let { communityId, postId } = $props();
    import { useCommunityState } from "$lib/states/communityState.svelte.js";
    import { useCommentState } from "$lib/states/commentState.svelte";
    import { useAuthState } from "$lib/states/authState.svelte.js";
    import { formatDate } from "$lib/utils/formatDate.js";

    let communityState = useCommunityState();
    let commentState = useCommentState();
    let authState = useAuthState();

    let community = $derived(communityState.communities.find((community) => community.id === communityId));

    const removeComment = (comment) => {
        commentState.removeComment(communityId, postId, comment.id);
    };
</script>

<ul class="comment-list">
    {#each (commentState.comments[communityId]?.[postId] ?? []) as comment}
        <li class="comment-list-item">
            <div class="item-header">
                {#if authState.user?.id && (authState.user.id === comment.user_id || authState.user.id === community?.user_id)}
                    <button class="delete-button" aria-label={`Delete comment by ${comment.username}`} onclick={() => { 
                        if (confirm("Are you sure you want to delete this comment?")) {
                            removeComment(comment);
                        }
                    }}>Remove</button>
                {/if}
            </div>
            <p>{comment.content}</p>
            <p class="content-author">Comment by {comment.username? comment.username : 'Undefined'} on <time datetime={comment.created_at}>{formatDate(comment.created_at)}</time></p>
        </li>
    {:else}
        <li>Be the first to comment!</li>
    {/each}
</ul>