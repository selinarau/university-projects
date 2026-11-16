import postgres from "postgres";

const sql = postgres();

const create = async (community, userId) => {
  const result = await sql`INSERT INTO communities
    (name, description, user_id)
    VALUES (${community.name}, ${community.description}, ${userId})
    RETURNING *;`;

  return result[0];
};

const readAll = async () => {
  return await sql`SELECT communities.*, users.username
    FROM communities
    JOIN users ON users.id = communities.user_id`;
};

const readOne = async (id) => {
  const result = await sql`SELECT communities.*, users.username
    FROM communities
    JOIN users ON users.id = communities.user_id
    WHERE communities.id = ${id}`;
  return result[0];
};

const update = async (id, community, userId) => {
  const result = await sql`UPDATE communities SET
      name = ${community.name},
      description = ${community.description}
    WHERE id = ${id} AND user_id = ${userId}
    RETURNING *;`;
  return result[0];
};

const deleteOne = async (id, userId) => {
  const result = await sql`DELETE FROM communities
    WHERE id = ${id} AND user_id = ${userId} RETURNING *`;
  return result[0];
};

export { create, deleteOne, readAll, readOne, update };