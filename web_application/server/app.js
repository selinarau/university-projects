import { Hono } from "@hono/hono";
import { cors } from "@hono/hono/cors";
import { verify } from "@hono/hono/jwt";
import * as communityController from "./controllers/communityController.js";
import * as postController from "./controllers/postController.js";
import * as commentController from "./controllers/commentController.js";
import * as authController from "./controllers/authController.js";

const app = new Hono();
app.use("/*", cors());

app.use("/*", async (c, next) => {
  const header = c.req.header("Authorization");
  if (header?.startsWith("Bearer ")) {
    try {
      const payload = await verify(header.slice(7), Deno.env.get("JWT_SECRET"), "HS256");
      c.set("user", payload);
    } catch (_error) {
      // invalid or expired token: continue as anonymous
    }
  }
  await next();
});

app.get("/api/communities", communityController.readAll);
app.get("/api/communities/:communityId", communityController.readOne);
app.post("/api/communities", communityController.create);
app.put("/api/communities/:communityId", communityController.update);
app.delete("/api/communities/:communityId", communityController.deleteOne);

app.get("/api/communities/:communityId/posts", postController.readAll);
app.get("/api/communities/:communityId/posts/:postId", postController.readOne);
app.post("/api/communities/:communityId/posts", postController.create);
app.delete("/api/communities/:communityId/posts/:postId", postController.deleteOne);

app.get("/api/communities/:communityId/posts/:postId/comments", commentController.readAll);
app.post("/api/communities/:communityId/posts/:postId/comments", commentController.create);
app.delete("/api/communities/:communityId/posts/:postId/comments/:commentId", commentController.deleteOne);

app.post("/api/auth/register", authController.register);
app.post("/api/auth/login", authController.login);

app.onError((err, c) => {
  console.error(err);
  return c.json({ error: "Something went wrong" }, 500);
});

app.notFound((c) => c.json({ error: "Not found" }, 404));

export default app;