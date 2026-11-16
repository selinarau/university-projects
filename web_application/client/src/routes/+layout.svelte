 <svelte:head><title>Svelte App</title></svelte:head>
<script>
  import "../app.css";
  import { useAuthState } from "$lib/states/authState.svelte.js";
  let { children } = $props();
  const authState = useAuthState();
</script>

<header>
    <nav aria-label="Main">
        <a href="/">Home</a>
        <a href="/communities">Communities</a>
    </nav>
    <div class="auth-links">
        {#if authState.user}
            
            <span>Logged in as {authState.user.username}!</span>
            <button class="logout-button" onclick={() => {
                if (confirm("Are you sure you want to logout?")) {
                authState.logout();
                }
            }}>Logout
            </button>
        {:else}
            <a class="auth-link login-link" href="/auth/login">Login</a>
            <a class="auth-link register-link" href="/auth/register">Register</a>
        {/if}
    </div>
    
</header>

<main>
    {@render children()}
</main>