import { error } from "@sveltejs/kit";
import { PUBLIC_API_URL } from "$env/static/public";

export const ssr = false;

export const load = async ({ params, fetch }) => {
  const communityId = Number(params.communityId);
  const postId = Number(params.postId);
  if (
    !Number.isSafeInteger(communityId) ||
    communityId < 1 ||
    !Number.isSafeInteger(postId) ||
    postId < 1
  ) {
    error(404, "Post not found");
  }

  const [communityResponse, postResponse] = await Promise.all([
    fetch(`${PUBLIC_API_URL}/api/communities/${communityId}`),
    fetch(
    `${PUBLIC_API_URL}/api/communities/${communityId}/posts/${postId}`,
    ),
  ]);
  if (communityResponse.status === 404) {
    error(404, "Community not found");
  }
  if (postResponse.status === 404) {
    error(404, "Post not found");
  }
  if (!communityResponse.ok) {
    error(communityResponse.status, "Could not load community");
  }
  if (!postResponse.ok) {
    error(postResponse.status, "Could not load post");
  }

  const [community, post] = await Promise.all([
    communityResponse.json(),
    postResponse.json(),
  ]);
  return { community, post };
};
