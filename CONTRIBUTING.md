# Contributing to Mokksy

Thank you for your interest in contributing! All contributions are welcome — bug reports, feature requests, documentation improvements, and code changes.

## Before You Start

- For non-trivial changes, **open an issue first** to discuss the approach with the maintainer.
- For large features, discuss the design before writing code — this avoids wasted effort.
- For small fixes (typos, documentation, obvious bugs), a PR without a prior issue is fine.

## Building Locally

Prerequisites: JDK 25, Gradle (wrapper included).

```shell
make build
```

This runs `./gradlew checkLegacyAbi build koverVerify koverXmlReport koverHtmlReport koverLog` — full build with API compatibility check, tests, and coverage verification.

Other useful targets:

| Command        | What it does                                         |
|----------------|------------------------------------------------------|
| `make test`    | Run all tests (re-runs even if up-to-date)           |
| `make lint`    | Check code style with Detekt                         |
| `make format`  | Auto-fix code style with Detekt                      |
| `make all`     | `format` + `lint` + `build`                          |
| `make knit`    | Compile and test README code examples via KNIT       |
| `make apidocs` | Generate API docs with Dokka                         |
| `make apidump` | Update the ABI dump after intentional API changes    |
| `make clean`   | Remove build outputs                                 |

## Code Style

- **Kotlin**: follow the [official Kotlin style guide](https://kotlinlang.org/docs/coding-conventions.html). No star imports.
- **Linting**: `make lint` (Detekt); `make format` to auto-correct. Configuration is in `detekt.yml`.
- **DSL builders** preferred over constructors; fall back to setters when a DSL hurts readability.
- Use `// region <name>` / `// endregion` to group related members in long files.
- Max line length: 100 characters.

## Testing

Prefer **integration tests** over unit tests — they give better coverage and are less fragile. Use unit tests for edge cases that are hard to cover via integration tests.

```shell
make test
```

### Kotlin tests

- Use [kotlin-test](https://kotlinlang.org/api/latest/kotlin.test/) and [Kotest assertions](https://kotest.io/docs/assertions/assertions.html).
- Backtick test names: `fun \`should return 200 OK\`()`.
- Infix assertions: `result shouldBe expected`, not `assertEquals(expected, result)`.
- For multiple assertions on the same subject use `assertSoftly(subject) { ... }`.
- Use `@ParameterizedTest` with `@ValueSource`/`@MethodSource` instead of duplicate `@Test` methods.

### Multiplatform tests

- Call `mokksy.awaitStarted()` at the start of each `commonTest` body.
- Include `seed` in every stub path (`"/resource-$seed"`) to prevent stub accumulation across tests.

### README examples

If you change README code examples, run `make knit` to verify they compile and pass as tests.

## API Compatibility

Mokksy uses binary API compatibility checks. If you **intentionally** change a public API:

```shell
make apidump
```

This updates `mokksy/api/`. Include the updated dump in your PR. Never remove or rename public API without deprecating it first.

## Documentation

- Add KDoc to interfaces and abstract classes; skip KDocs on overrides.
- Use `[ClassName]` references in KDoc, not plain text.
- Add brief code examples to KDoc where helpful.
- Generate and review API docs with `make apidocs` before submitting documentation changes.

## Submitting a PR

1. Fork the repository and create a branch from `main`.
2. Run `make all` (format + lint + build) — the CI runs the same checks.
3. Ensure `make knit` passes if you edited README examples.
4. If you changed public API, run `make apidump` and commit the updated dump.
5. Open a pull request against `main`. Describe what you changed and why.

PRs are reviewed by the maintainer (`@kpavlov`). Please be patient — this is a spare-time project.

## General Guidelines

- **Backward compatibility**: mark items `@Deprecated` rather than removing them.
- **Dependencies**: avoid adding new runtime dependencies; new test-scope dependencies are acceptable.
- **Thread safety**: ensure any new code is safe for concurrent use.
- **Java compatibility**: public APIs must remain usable from Java (JDK 17+).
- Apply [S.O.L.I.D.](https://en.wikipedia.org/wiki/SOLID) principles.
