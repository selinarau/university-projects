import { error } from "@sveltejs/kit";
import { PUBLIC_API_URL } from "$env/static/public";

export const ssr = false;

export const load = async ({ params, fetch }) => {
  const communityId = Number(params.communityId);
  if (!Number.isSafeInteger(communityId) || communityId < 1) {
    error(404, "Community not found");
  }

  const response = await fetch(
    `${PUBLIC_API_URL}/api/communities/${communityId}`,
  );
  if (response.status === 404) {
    error(404, "Community not found");
  }
  if (!response.ok) {
    error(response.status, "Could not load community");
  }

  return { community: await response.json() };
};
