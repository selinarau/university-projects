 <svelte:head><title>Community</title></svelte:head>
<script>
    import { page } from "$app/state";
    import { initCommunity } from "$lib/states/communityState.svelte";
    import { initPosts } from "$lib/states/postState.svelte";
    import Community from "$lib/components/communities/Community.svelte";
    import PostList from "$lib/components/posts/PostList.svelte";
    import PostForm from "$lib/components/posts/PostForm.svelte";

    let { data } = $props();
    let communityId = $derived(parseInt(page.params.communityId));

    $effect (()=>{
        initCommunity(communityId, data.community);
        initPosts(communityId);
    });
</script>

<a class="back-link" href="/communities">Back to communities</a>

<div class="community-container">
    <Community communityId={communityId}/>
    <PostList communityId={communityId}/>
    <PostForm communityId={communityId}/>
</div>
