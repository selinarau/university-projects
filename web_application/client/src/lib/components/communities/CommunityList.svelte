<script>
    import { useCommunityState } from "$lib/states/communityState.svelte.js";
    import { useAuthState } from "$lib/states/authState.svelte.js";
    import { formatDate } from "$lib/utils/formatDate.js";

    let authState = useAuthState();
    let communityState = useCommunityState();
</script>


<ul class="community-list">
    {#each communityState.communities as c}
        <li class="community-list-item">
            <div class="item-header">
                <h2><a class="community-link" href={`/communities/${c.id}`}>{c.name}</a></h2>
                {#if (authState.user?.id === c.user_id)}
                    <button class="delete-button" aria-label={`Delete community ${c.name}`} onclick={() => { 
                        if (confirm("Are you sure you want to delete this community? Its posts and comments will be deleted too.")) {
                        communityState.removeCommunity(c.id)
                        }
                    }}>Delete</button>
                {/if}
            </div>
            <p>{c.description}</p>
            <p class="content-author">Created by {c.username? c.username : 'Undefined'} on <time datetime={c.created_at}>{formatDate(c.created_at)}</time></p>
        </li>
    {:else}
        <li class="no-content-item">No communities yet. Create the first one!</li>
    {/each}
</ul>