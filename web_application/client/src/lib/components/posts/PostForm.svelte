<script>
    let { communityId } = $props();
    import { useCommunityState } from "$lib/states/communityState.svelte.js";
    import { usePostState } from "$lib/states/postState.svelte.js";
    import { useAuthState } from "$lib/states/authState.svelte.js";

    let communityState = useCommunityState();
    let postState = usePostState();
    let authState = useAuthState();

    let community = $derived(communityState.communities.find((community) => community.id === communityId));

    const addPost = (e) => {
        e.preventDefault();
        const post = Object.fromEntries(new FormData(e.target));
        postState.addPost(communityId, post);
        e.target.reset();
    };
</script>

{#if community}
    {#if authState.user }
        <form onsubmit={addPost}>
            <label>
                Title
                <input
                    id="title"
                    name="title"
                    type="text"
                    placeholder="Post title"
                />
            </label>
            <br />
            <label>
                Content
                <textarea
                    id="content"
                    name="content"
                    placeholder="Post content"
                />
            </label>
            <br />
            <input class="submit-button" type="submit" value="Add post"/>
        </form>
    {:else}
        <p>You must be logged in to add a post.</p>
    {/if}
{:else}
    <p>Community not found.</p>
    <a class="back-to-communities" href="/communities">Back to communities</a>
{/if}