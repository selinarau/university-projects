 <svelte:head><title>Communities</title></svelte:head>
<script>
    import { initCommunities } from "$lib/states/communityState.svelte";
    import CommunityList from "$lib/components/communities/CommunityList.svelte";
    import CommunityForm from "$lib/components/communities/CommunityForm.svelte";

    $effect (() => {
        initCommunities();
    });
</script>

<h1 class="communities-title">Communities</h1>
<CommunityList />

<h2 class="community-form-title">Add a community</h2>
<CommunityForm />