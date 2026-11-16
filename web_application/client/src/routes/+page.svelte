<svelte:head>
  <title>Communities Forum</title>
  <meta
    name="description"
    content="Explore communities, share ideas, and join conversations."
  />
</svelte:head>

<script>
  import { goto } from "$app/navigation";
  import { useAuthState } from "$lib/states/authState.svelte.js";

  const authState = useAuthState();

  $effect(() => {
    if (authState.user) {
      goto("/communities", { replaceState: true });
    }
  });
</script>

{#if !authState.user}
  <section class="welcome" aria-labelledby="welcome-title">
    <p class="eyebrow">A place for shared interests</p>
    <h1 id="welcome-title">Find your community. Join the conversation.</h1>
    <p class="intro">
      Discover communities, read posts, and share your ideas. Browsing is open
      to everyone; create an account to post and comment.
    </p>

    <div class="actions">
      <a class="auth-link browse-link" href="/communities">
        Browse communities
      </a>
      <a class="auth-link register-link" href="/auth/register">
        Create an account
      </a>
    </div>

    <p class="login-prompt">
      Already a member? <a class="auth-link login-link" href="/auth/login">Log in</a>
    </p>
  </section>

  <section class="features" aria-label="What you can do">
    <article>
      <h2>Explore</h2>
      <p>Find communities and read conversations that interest you.</p>
    </article>
    <article>
      <h2>Take part</h2>
      <p>Create an account to share posts and add comments.</p>
    </article>
    <article>
      <h2>Build together</h2>
      <p>Create a community and help make it a welcoming place to talk.</p>
    </article>
  </section>
{/if}

