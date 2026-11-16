import * as communityRepository from "../repositories/communityRepository.js";

const create = async (c) => {
  const user = c.get("user");
  if (!user) {
    return c.json({ error: "Not logged in" }, 401);
  }

  const community = await c.req.json();
  if (!community.name ||
    !community.description) {
    return c.json({ error: "Missing required fields" }, 400);
  }

  const newCommunity = await communityRepository.create(community, user.id);
  return c.json(newCommunity, 201);
};

const readAll = async (c) => {
  const communities = await communityRepository.readAll();
  return c.json(communities);
};

const readOne = async (c) => {
  const id = Number(c.req.param("communityId"));
  if (!Number.isInteger(id)) {
    return c.json({ error: "Invalid community id" }, 400);
  }

  const community = await communityRepository.readOne(id);
  if (!community) {
    return c.json({ error: "Community not found" }, 404);
  }

  return c.json(community);
};

const update = async (c) => {
  const user = c.get("user");
  if (!user) {
    return c.json({ error: "Not logged in" }, 401);
  }

  const id = Number(c.req.param("communityId"));
  if (!Number.isInteger(id)) {
    return c.json({ error: "Invalid community id" }, 400);
  }

  const community = await c.req.json();
  if (!community.name ||
    !community.description) {
    return c.json({ error: "Missing required fields" }, 400);
  }

  const updatedCommunity = await communityRepository.update(id, community, user.id);

  if (!updatedCommunity) {
    return c.json({ error: "Community not found" }, 404);
  }

  return c.json(updatedCommunity);
};

const deleteOne = async (c) => {
  const user = c.get("user");
  if (!user) {
    return c.json({ error: "Not logged in" }, 401);
  }

  const id = Number(c.req.param("communityId"));
  if (!Number.isInteger(id)) {
    return c.json({ error: "Invalid community id" }, 400);
  }

  const deletedCommunity = await communityRepository.deleteOne(id, user.id);

  if (!deletedCommunity) {
    return c.json({ error: "Community not found" }, 404);
  }

  return c.json(deletedCommunity);
};

export { create, deleteOne, readAll, readOne, update };