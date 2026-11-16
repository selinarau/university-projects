import { expect, test } from "@playwright/test";

const API = process.env.API_URL ?? "http://localhost:8000";
const CLIENT = process.env.CLIENT_URL ?? "http://localhost:5173";

const unique = (prefix) =>
  `${prefix}-${Date.now()}-${Math.floor(Math.random() * 1e6)}`;

// Registers and logs in a new user through the API.
const createUser = async (request, name) => {
  const username = unique(name);
  const email = `${username}@test.com`;
  const password = "password123";

  const registered = await request.post(`${API}/api/auth/register`, {
    data: { username, email, password },
  });
  expect(registered.ok()).toBeTruthy();

  const login = await request.post(`${API}/api/auth/login`, {
    data: { email, password },
  });
  expect(login.ok()).toBeTruthy();

  const { token, user } = await login.json();
  return { email, id: user.id, headers: { Authorization: `Bearer ${token}` } };
};

const createCommunity = async (request, user) => {
  const response = await request.post(`${API}/api/communities`, {
    headers: user.headers,
    data: { name: unique("community"), description: "Test community" },
  });
  expect(response.ok()).toBeTruthy();
  return await response.json();
};

const createPost = async (request, user, community) => {
  const response = await request.post(
    `${API}/api/communities/${community.id}/posts`,
    {
      headers: user.headers,
      data: { title: unique("post"), content: "Test post content" },
    },
  );
  expect(response.status()).toBe(201);
  return await response.json();
};

const createComment = async (request, user, community, post) => {
  const response = await request.post(
    `${API}/api/communities/${community.id}/posts/${post.id}/comments`,
    { headers: user.headers, data: { content: "Test comment" } },
  );
  expect(response.status()).toBe(201);
  return await response.json();
};

const postUrl = (community, post) =>
  `${API}/api/communities/${community.id}/posts/${post.id}`;

test.describe("delete permissions", () => {
  test("a logged-out visitor sees no delete buttons", async ({
    page,
    request,
  }) => {
    const owner = await createUser(request, "owner");
    const community = await createCommunity(request, owner);

    await page.goto(`${CLIENT}/communities`);
    // Wait until the list has loaded before checking for buttons.
    await expect(page.getByText(community.name)).toBeVisible();
    await expect(page.getByRole("button", { name: /delete/i })).toHaveCount(0);
  });

  test("a logged-out request cannot delete anything", async ({ request }) => {
    const owner = await createUser(request, "owner");
    const community = await createCommunity(request, owner);

    const response = await request.delete(
      `${API}/api/communities/${community.id}`,
    );
    expect(response.status()).toBe(401);

    const stillThere = await request.get(
      `${API}/api/communities/${community.id}`,
    );
    expect(stillThere.status()).toBe(200);
  });

  test("a user cannot delete someone else's post", async ({ request }) => {
    const owner = await createUser(request, "owner");
    const author = await createUser(request, "author");
    const stranger = await createUser(request, "stranger");

    const community = await createCommunity(request, owner);
    const post = await createPost(request, author, community);

    const response = await request.delete(postUrl(community, post), {
      headers: stranger.headers,
    });
    expect(response.status()).toBe(404);

    const stillThere = await request.get(postUrl(community, post));
    expect(stillThere.status()).toBe(200);
  });

  test("the author can delete their own post", async ({ request }) => {
    const owner = await createUser(request, "owner");
    const author = await createUser(request, "author");

    const community = await createCommunity(request, owner);
    const post = await createPost(request, author, community);

    const response = await request.delete(postUrl(community, post), {
      headers: author.headers,
    });
    expect(response.status()).toBe(200);

    const gone = await request.get(postUrl(community, post));
    expect(gone.status()).toBe(404);
  });

  test("the community owner can delete another user's post", async ({
    request,
  }) => {
    const owner = await createUser(request, "owner");
    const author = await createUser(request, "author");

    const community = await createCommunity(request, owner);
    const post = await createPost(request, author, community);

    const response = await request.delete(postUrl(community, post), {
      headers: owner.headers,
    });
    expect(response.status()).toBe(200);

    const gone = await request.get(postUrl(community, post));
    expect(gone.status()).toBe(404);
  });

  test("a user cannot delete someone else's community", async ({ request }) => {
    const owner = await createUser(request, "owner");
    const stranger = await createUser(request, "stranger");
    const community = await createCommunity(request, owner);

    const response = await request.delete(
      `${API}/api/communities/${community.id}`,
      { headers: stranger.headers },
    );
    expect(response.status()).toBe(404);
  });
});

test.describe("cascade deletes", () => {
  test("deleting a post also deletes its comments", async ({ request }) => {
    const owner = await createUser(request, "owner");
    const community = await createCommunity(request, owner);
    const post = await createPost(request, owner, community);
    await createComment(request, owner, community, post);

    const response = await request.delete(postUrl(community, post), {
      headers: owner.headers,
    });
    expect(response.status()).toBe(200);

    const comments = await request.get(`${postUrl(community, post)}/comments`);
    if (comments.ok()) {
      expect(await comments.json()).toEqual([]);
    }
  });

  test("deleting a community removes its posts and comments", async ({
    request,
  }) => {
    const owner = await createUser(request, "owner");
    const author = await createUser(request, "author");

    const community = await createCommunity(request, owner);
    const post = await createPost(request, author, community);
    await createComment(request, owner, community, post);

    const response = await request.delete(
      `${API}/api/communities/${community.id}`,
      { headers: owner.headers },
    );
    expect(response.status()).toBe(200);

    const communityGone = await request.get(
      `${API}/api/communities/${community.id}`,
    );
    expect(communityGone.status()).toBe(404);

    const postGone = await request.get(postUrl(community, post));
    expect(postGone.status()).toBe(404);

    const comments = await request.get(`${postUrl(community, post)}/comments`);
    if (comments.ok()) {
      expect(await comments.json()).toEqual([]);
    }
  });
});