# Communities Forum Project

A small forum application where users can register, log in, create communities, write posts in them and comment on posts. Everyone can read everything, but only logged-in users can write, and only the post/comment creator or the community creator can delete content.

I have done the application alone with the course material and examples and using Copilot of VSC, ChatGPT and Claude to get suggestions, write code and fix bugs. All code was reviewed, adapted and tested by me.

## Features

- Register and log in (passwords are hashed, sessions use JWT tokens)
- Browse all communities, posts and comments without logging in
- Create communities, posts and comments when logged in
- Delete your own communities, posts and comments
  - Deleting a community also deletes its posts and their comments
  - Deleting a post also deletes its comments
  - The owner of a community can also delete other users' posts in it
- Confirmation dialogs before deleting or logging out
- Responsive layout, keyboard focus styles and dark mode support

## Tech stack

| Part | Technology |
| --- | --- |
| Client | SvelteKit (Svelte 5), Vite |
| Language | JavaScript (client and server) |
| Server | Deno, Hono |
| Database | PostgreSQL 17 |
| Migrations | Flyway |
| End-to-end tests | Playwright |
| Running everything | Docker Compose |
| Styling | Plain CSS (`client/src/app.css`) with CSS variables and dark mode |

## Project structure

```
.
├── client/                 SvelteKit app (port 5173)
│   └── src/
│       ├── lib/
│       │   ├── apis/       fetch functions for the server API
│       │   ├── components/ communities, posts and comments components
│       │   ├── states/     shared reactive state (auth, communities, posts, comments)
│       │   └── utils/      helpers such as authFetch and formatDate
│       └── routes/         pages
├── server/                 Deno + Hono API (port 8000)
│   ├── app.js              routes and authentication middleware
│   ├── controllers/        request handling and validation
│   └── repositories/       SQL queries
├── database-migrations/    Flyway SQL migrations
├── e2e-tests/              Playwright tests
├── compose.yaml            Docker Compose setup
└── project.env             environment variables for local development
```

## Requirements

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (or another Docker engine with the Compose plugin)

Nothing else needs to be installed. Node, Deno and PostgreSQL all run inside the containers.

## Getting started

1. Start from the project root, the folder that contains `compose.yaml`:

   ```bash
   docker compose up --build
   ```

2. Open the app at <http://localhost:5173>.
3. The API runs at <http://localhost:8000>.

Stop the application with `Ctrl+C`, or, if you started it with `-d`:

```bash
docker compose down
```

### Resetting the database

The database data is stored in a Docker volume. To delete all data and run the migrations from scratch:

```bash
docker compose down -v
docker compose up --build
```

You need to do this whenever you change an existing migration file, because Flyway checks that applied migrations have not been modified.

## Configuration

Settings are in `project.env`:

| Variable | Purpose |
| --- | --- |
| `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_DB` | database credentials |
| `FLYWAY_*` | connection settings for the migrations |
| `PG*` | connection settings used by the server |
| `JWT_SECRET` | secret used to sign login tokens |

The client reads `PUBLIC_API_URL` (for example `http://localhost:8000`) to know where the API is.

The values in `project.env` are for local development only. Use strong, private values in any deployed environment and do not publish real secrets.

## API overview

Reading is public. Creating and deleting require an `Authorization: Bearer <token>` header.

| Method | Path | Description | Login needed |
| --- | --- | --- | --- |
| POST | `/api/auth/register` | create an account | no |
| POST | `/api/auth/login` | log in, returns a token | no |
| GET | `/api/communities` | list communities | no |
| GET | `/api/communities/:communityId` | read one community | no |
| POST | `/api/communities` | create a community | yes |
| PUT | `/api/communities/:communityId` | update a community (owner only) | yes |
| DELETE | `/api/communities/:communityId` | delete a community (owner only) | yes |
| GET | `/api/communities/:communityId/posts` | list posts | no |
| GET | `/api/communities/:communityId/posts/:postId` | read one post | no |
| POST | `/api/communities/:communityId/posts` | create a post | yes |
| DELETE | `/api/communities/:communityId/posts/:postId` | delete a post (author or community owner) | yes |
| GET | `/api/communities/:communityId/posts/:postId/comments` | list comments | no |
| POST | `/api/communities/:communityId/posts/:postId/comments` | create a comment | yes |
| DELETE | `/api/communities/:communityId/posts/:postId/comments/:commentId` | delete a comment | yes |

## Permissions

The server decides who may delete something. Hiding a button in the client is only a convenience.

- The user id always comes from the verified token, never from the request body.
- Delete queries include the owner check in SQL, so a request for someone else's content deletes nothing and returns `404`.
- Cascading deletes are handled by the database (`ON DELETE CASCADE`), so no extra code is needed to remove child rows.

## Tests

End-to-end tests are in `e2e-tests/` and use Playwright. The application must be running before you run them. See `e2e-tests/Dockerfile` and `e2e-tests/playwright.config.js` for the setup.

## Troubleshooting

- **`docker: command not found`**: Docker is not installed, or Docker Desktop has not been started yet.
- **`relation "..." does not exist` in the server log**: the migrations did not run. Check the migration output with `docker compose ps -a` and `docker compose logs`, then reset the database as described above.
- **Pages do not update after clicking a link**: check the browser console for errors and restart the client with `docker compose restart client`.
- **Delete button is missing**: log out and log in again, so the stored user includes its id.


## Future Improvements

- **Edit communities and posts**: at the moment the content cannot be edited after created. The PUT routes are already existing.
- **Better pagination**: at the moment all the communities are listed on the page and the create community form is below the list. This is uncomfortable for users especially if there is a long list of communities and the same is for posts and comments. Would be better to have just few items of the list visible initially and a "Load more" button.
- **Search from the page**: user could filter communities and find them by the name.
- **Profile and settings page**: In the current version user cannot change the username or password when they are once set. 
- **Protection from XSS**: Keep the token in an`httpOnly` cookie instead of `localStorage`, which protects it from XSS.