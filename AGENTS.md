# AGENTS.md

This file is a repo-specific guide for coding agents. It documents how to
build/test, how to run a single test, and the local code style patterns.

## Repo snapshot
- Multi-module Maven project (root `pom.xml` aggregates modules).
- Java versions vary by module (e.g., admin uses Java 21, api uses Java 8).
- Modules of interest: `world-peace-admin`, `world-peace-api`,
  `world-peace-service`, `world-peace-common` (+ submodules), `test01`,
  `world-peace-poc`, `world-peace-ai`.

## Cursor / Copilot rules
- No `.cursor/rules/`, `.cursorrules`, or `.github/copilot-instructions.md`
  found in this repo.

## Build / test commands

### General Maven usage (module-scoped)
- Build a module:
  - `mvn -f path/to/module/pom.xml compile`
  - `mvn -f path/to/module/pom.xml clean install`
- Run unit tests (Surefire):
  - `mvn -f path/to/module/pom.xml test`
- Run integration tests (Failsafe):
  - `mvn -f path/to/module/pom.xml verify`

### Known module scripts and docs
- `world-peace-admin` build script:
  - `mvn clean install -f ./pom.xml`
  - Source: `world-peace-admin/rebuild_wpadmin.sh`
- `test01` EvoSuite workflow:
  - `mvn compile`
  - `mvn clean install -Dmaven.test.skip=true`
  - `mvn dependency:copy-dependencies`
  - `javac evosuite-tests/com/jupiter/calc/*.java`
  - `java org.junit.runner.JUnitCore com.jupiter.calc.Say_ESTest`
  - Source: `test01/readme.md`

### Single-test commands (Maven Surefire/Failsafe)
These are the standard Maven patterns used by Surefire/Failsafe and align
with existing repo comments/examples.

Unit tests (Surefire, `-Dtest=`):
- Single class:
  - `mvn -f path/to/module/pom.xml -Dtest=TestCircle test`
- Single method:
  - `mvn -f path/to/module/pom.xml -Dtest=TestCircle#myTest test`
- Multiple methods:
  - `mvn -f path/to/module/pom.xml -Dtest=TestCircle#testOne+testTwo test`
- Pattern match:
  - `mvn -f path/to/module/pom.xml -Dtest=Test* test`

Integration tests (Failsafe, `-Dit.test=`):
- Single class:
  - `mvn -f path/to/module/pom.xml -Dit.test=ITCircle verify`
- Single method:
  - `mvn -f path/to/module/pom.xml -Dit.test=ITCircle#myTest verify`

Repo examples for single-test selection:
- `mvn test -Dtest=com.jupiter.Test001`
- `mvn test -Dtest=*Test#add,Test*`
- Source: `test01/src/test/java/com/jupiter/calc/CalculatorJUnit5Test.java`

### Test inclusion patterns
Modules use the default include patterns in their Maven configuration:
- Unit tests: `**/*Test.java`
- Integration tests: `**/*IT.java`
- Source: `world-peace-admin/pom.xml`, `world-peace-dependencies/pom.xml`,
  `world-peace-common/world-peace-common-util-redislock-starter/pom.xml`,
  `world-peace-common/world-peace-common-util-kafka-starter/pom.xml`.

### Docker build examples (admin)
- Local image build and push:
  - `docker build -t ${WPADMIN_VERSION} -f ./Dockerfile .`
  - `docker tag ${WPADMIN_VERSION} ${REPO_URL}/${WPADMIN_VERSION}`
  - `docker push ${REPO_URL}/${WPADMIN_VERSION}`
  - Source: `world-peace-admin/build_image.sh`
- Docker deployment guidance:
  - Source: `world-peace-admin/readme_wpadmin.MD`

## Lint / formatting
- No repo-wide lint or formatter config files found (.editorconfig,
  checkstyle, spotbugs, pmd, eslint, prettier, ruff, black).
- There are inline ESLint directive comments in
  `world-peace-admin/src/main/resources/static/shop/js/vue.js`.

## Code style conventions (observed)

### Java
- Indentation: 4 spaces.
- Braces: opening brace on the same line as declaration.
- Imports: grouped by package family rather than strict alphabetical order.
  Common pattern seen:
  1) `com.jupiter.*`
  2) `lombok.*`
  3) `org.springframework.*` (and other third-party)
  4) `javax.*`
  5) `java.*`
- Lombok is common (`@Slf4j`, `@Data`, `@Getter`, `@Setter`,
  `@RequiredArgsConstructor`).
- Spring style: `@Controller`/`@RestController` with `@RequestMapping` and
  `@GetMapping` routes; constructor injection is common.
- Logging: use `log.info` / `log.warn` / `log.error` from `@Slf4j`.

Representative files:
- `world-peace-admin/src/main/java/com/jupiter/admin/controllor/HelloController.java`
- `world-peace-admin/src/main/java/com/jupiter/admin/controllor/VueController.java`
- `world-peace-ai/src/main/java/com/jupiter/controller/ChatController.java`
- `world-peace-service/src/main/java/com/jupiter/mvc/service/OrderService.java`
- `world-peace-service/src/main/java/com/jupiter/mvc/exception/BizException.java`

### Error handling
- Business exceptions: `BizException` used for domain-level failures.
- Validation errors: `ValidationException` used in service-level checks.
- Example: `world-peace-service/src/main/java/com/jupiter/mvc/service/OrderService.java`.

### Python / Node utilities
- Python scripts exist under `docker/puppeteer/`.
  - Style: 4-space indentation, optional type hints and docstrings.
- Node.js scripts under `docker/puppeteer/` use CommonJS `require`,
  async/await, and try/catch error handling.

## Naming conventions (observed)
- Java: classes in PascalCase, methods and fields in lowerCamelCase.
- Constants: UPPER_SNAKE_CASE (when present).

## When adding or editing code
- Follow the existing module style rather than introducing new patterns.
- Prefer Lombok for boilerplate where used in neighboring files.
- Keep controller routing annotations consistent with local module patterns.
- For new tests, match file naming to Surefire/Failsafe includes:
  - Unit tests: `*Test.java`
  - Integration tests: `*IT.java`

## Notes for agents
- The repo contains multiple Java versions across modules; match the module
  version declared in its `pom.xml`.
- Some modules include tutorial or PoC code; avoid refactors unless requested.
- Keep changes minimal and scoped to the relevant module.
