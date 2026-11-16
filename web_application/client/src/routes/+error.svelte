<script>
  import { page } from "$app/state";
</script>

<svelte:head><title>{page.status}</title></svelte:head>

<div class="error-container">
    <h1>Error {page.status}</h1>
    <p>{page.error?.message ?? "Something went wrong."}</p>
    <a class="back-link" href="/communities">Back to communities</a>
</div>