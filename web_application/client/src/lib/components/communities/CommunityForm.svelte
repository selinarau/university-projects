<script>
    import { useCommunityState } from "$lib/states/communityState.svelte.js";
    import { useAuthState } from "$lib/states/authState.svelte.js";

    const authState = useAuthState();
    let communityState = useCommunityState();
    let errorMessage = $state("");

    const addCommunity = async (e) => {
        e.preventDefault();
        errorMessage = "";
        const community = Object.fromEntries(new FormData(e.target));
        try {
            await communityState.addCommunity(community);
            e.target.reset();
        } catch (error) {
            errorMessage = error.message;
        }
    };
</script>

{#if authState.user}
    <form onsubmit={addCommunity}>
        <label>
            Name
            <input
                id="name"
                name="name"
                type="text"
                placeholder="Community name"
                required
            />
        </label>
        <br/>
        <label>
            Description
            <textarea
                id="description"
                name="description"
                placeholder="Enter community description"
                required
            />
        </label>
        <br/>
        <input class="submit-button" type="submit" value="Add community"/>
    </form>
{:else}
    <p>You must be logged in to create a community.</p>
{/if}

{#if errorMessage}
    <p role="alert">{errorMessage}</p>
{/if}