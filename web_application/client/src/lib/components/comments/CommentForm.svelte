<script>
    let { communityId, postId } = $props();
    import { usePostState } from "$lib/states/postState.svelte.js";
    import { useCommentState } from "$lib/states/commentState.svelte";
    import { useAuthState } from "$lib/states/authState.svelte.js";
    
    let postState = usePostState();
    let commentState = useCommentState();
    let authState = useAuthState();

    let post = $derived(postState.posts[communityId]?.find((post) => post.id === postId));

    const addComment = (e) => {
        e.preventDefault();
        const comment = Object.fromEntries(new FormData(e.target));
        commentState.addComment(communityId, postId, comment);
        e.target.reset();
    };
</script>

{#if post}
    {#if authState.user}
        <form onsubmit={addComment}>
            <label>
                Content
                <textarea
                    id="content"
                    name="content"
                    placeholder="Comment content"
                />
            </label>
            <br/>
            <input class="submit-button" type="submit" value="Add comment"/>
        </form>
    {:else}
        <p>You must be logged in to add a comment.</p>
    {/if}
{:else}
    <p>Post not found.</p>
    <a class="back-to-communities" href={`/communities/${communityId}`}>Back to community</a>
{/if}  