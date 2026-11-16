 <svelte:head><title>Post</title></svelte:head>
<script>
    import { untrack } from "svelte";
    import { page } from "$app/state";
    import { initCommunity } from "$lib/states/communityState.svelte";
    import { initPost } from "$lib/states/postState.svelte";
    import { initComments } from "$lib/states/commentState.svelte";
    import Post from "$lib/components/posts/Post.svelte";
    import CommentForm from "$lib/components/comments/CommentForm.svelte";
    import CommentList from "$lib/components/comments/CommentList.svelte";

    let { data } = $props();
    let communityId = $derived(parseInt(page.params.communityId));
    let postId = $derived(parseInt(page.params.postId));

    $effect (() => {
        const currentCommunityId = communityId;
        const currentPostId = postId;
        const { community, post } = data;

        untrack(() => {
            initCommunity(currentCommunityId, community);
            initPost(currentCommunityId, currentPostId, post);
            initComments(currentCommunityId, currentPostId);
        });
    });
    </script>

<a class="back-link" href="/communities/{communityId}">Back to community</a>

<div class="post-container">
    <Post communityId={communityId} postId={postId}/>
    <CommentList communityId={communityId} postId={postId}/>
    <CommentForm communityId={communityId} postId={postId}/>
</div>