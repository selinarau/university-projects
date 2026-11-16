import { hash, verify } from "scrypt";
import { sign } from "@hono/hono/jwt";
import * as authRepository from "../repositories/authRepository.js";

const register = async (c) => {
  const user = await c.req.json();

  if (!user.username?.trim() || !user.email || !user.password) {
    return c.json({ error: "Missing required fields" }, 400);
  }

  try {
    user.username = user.username.trim();
    user.password_hash = await hash(user.password);
    const newUser = await authRepository.create(user);
    return c.json({ id: newUser.id, email: newUser.email, username: newUser.username });
  } catch (error) {
    if (error.code === "23505") {
      return c.json({ error: "Email already registered" }, 409);
    }
    console.error(error);
    return c.json({ error: "Registration failed" }, 500);
  }
};

const login = async (c) => {
  const user = await c.req.json();

  const foundUser = await authRepository.findByEmail(user.email);
  if (!foundUser) {
    return c.json({ error: "Invalid email or password" }, 401);
  }

  const isValid = await verify(user.password, foundUser.password_hash);
  if (!isValid) {
    return c.json({ error: "Invalid email or password" }, 401);
  }

  const payload = { email: foundUser.email, id: foundUser.id };
  const jwtSecret = Deno.env.get("JWT_SECRET");
  if (!jwtSecret) {
    return c.json({ error: "Authentication is not configured" }, 500);
  }

  const token = await sign(payload, jwtSecret);

  return c.json({
    message: "Login successful",
    user: { email: foundUser.email, id: foundUser.id, username: foundUser.username },
    token
  });
};

export { login, register };