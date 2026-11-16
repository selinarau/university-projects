<script>
    let { communityId } = $props();
    import { useCommunityState } from "$lib/states/communityState.svelte.js";
    import { usePostState } from "$lib/states/postState.svelte.js";
    import { useAuthState } from "$lib/states/authState.svelte.js";
    import { formatDate } from "$lib/utils/formatDate.js";
   
    let communityState = useCommunityState();
    let postState = usePostState();
    let authState = useAuthState();

     let community = $derived(communityState.communities.find((community) => community.id === communityId));
    
    const removePost = (post) => {
        postState.removePost(communityId, post.id);
    };
</script>

<ul class="post-list">
    {#each postState.posts[communityId] as post}
        <li class="post-list-item">
            <div class="item-header">
                <h3><a class="post-link" href={`/communities/${communityId}/posts/${post.id}`}>{post.title}</a></h3>
                {#if authState.user.id && (authState.user.id === post.user_id || authState.user.id === community.user_id)}
                    <button class="delete-button" aria-label={`Delete post ${post.title}`} onclick={()=> { 
                        if (confirm("Are you sure you want to delete this post and all its comments?")) {
                            removePost(post);
                        }
                    }}>Remove</button>
                {/if}
            </div>
            <p class="post-content">{post.content}</p>
            <p class="content-author">Posted by {post.username? post.username : 'Undefined'} on <time datetime={post.created_at}>{formatDate(post.created_at)}</time></p>
        </li>
    {:else}
        <li>No posts yet. Be the first to post!</li>
    {/each}
</ul>