<script>
    let { communityId, postId } = $props();
    import { useCommunityState } from "$lib/states/communityState.svelte.js";
    import { usePostState } from "$lib/states/postState.svelte.js";
    import { useAuthState } from "$lib/states/authState.svelte.js";
    import { formatDate } from "$lib/utils/formatDate.js";
    
    let authState = useAuthState();
    let communityState = useCommunityState();
    let postState = usePostState();

    let community = $derived(communityState.communities.find((community) => community.id === communityId));
    let post = $derived(postState.posts[communityId]?.find((p) => p.id === postId));

    const removePost = (post) => {
        postState.removePost(communityId, post.id);
    };
</script>

{#if post}
    <div class="item-header">
        <h2 class="post-title">{post.title} </h2>
        {#if authState.user?.id && (authState.user.id === post.user_id || authState.user.id === community?.user_id)}
            <button class="delete-button" aria-label={`Delete post ${post.title}`} onclick={()=> { 
                if (confirm("Are you sure you want to delete this post and all its comments?")) {
                    removePost(post);
                }
            }}>Remove</button>
        {/if}
    </div>
    <p>{post.content} </p>
    <p class="content-author">Posted by {post.username? post.username : 'Undefined'} on <time datetime={post.created_at}>{formatDate(post.created_at)}</time></p>    
{:else}
    <p>Loading post...</p>
{/if}