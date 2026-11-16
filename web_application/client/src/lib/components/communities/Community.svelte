<script>
    let { communityId } = $props();
    import { useCommunityState } from "$lib/states/communityState.svelte.js";
    import { useAuthState } from "$lib/states/authState.svelte.js";
    import { formatDate } from "$lib/utils/formatDate.js";
    
    let authState = useAuthState();
    let communityState = useCommunityState();
    let community = $derived(communityState.communities.find((community) => community.id === communityId));
</script>

{#if community}
    <div class="item-header">
        <h2 class="community-name"> {community.name} </h2>
        {#if (authState.user?.id === community.user_id)}
            <button class="delete-button" aria-label={`Delete community ${community.name}`} onclick={() => { 
                if (confirm("Are you sure you want to delete this community? Its posts and comments will be deleted too.")) {
                communityState.removeCommunity(community.id)
                }
            }}>Delete</button>
        {/if}
    </div>
    <p class="community-description"> {community.description} </p>
    <p class="content-author">Created by {community.username? community.username : 'Undefined'} on <time datetime={community.created_at}>{formatDate(community.created_at)}</time></p>
{:else}
    <h2 class="community-name"> "Loading..." </h2>
{/if}