# The Algorithms - Java

[![Build](https://github.com/TheAlgorithms/Java/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/TheAlgorithms/Java/actions/workflows/build.yml)
[![codecov](https://codecov.io/gh/TheAlgorithms/Java/graph/badge.svg?token=XAdPyqTIqR)](https://codecov.io/gh/TheAlgorithms/Java)
[![Discord chat](https://img.shields.io/discord/808045925556682782.svg?logo=discord&colorB=7289DA&style=flat-square)](https://discord.gg/c7MnfGFGa6)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/TheAlgorithms/Java)


You can run and edit the algorithms, or contribute to them using Gitpod.io (a free online development environment) with a single click.

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/TheAlgorithms/Java)

### All algorithms are implemented in Java (for educational purposes)
These implementations are intended for learning purposes. As such, they may be less efficient than the Java standard library.

## Contribution Guidelines
Please read our [Contribution Guidelines](CONTRIBUTING.md) before you contribute to this project.

## Algorithms
Our [directory](DIRECTORY.md) has the full list of applications.

### Local development & contributing

1. Clone & build
\`\`\`bash
git clone https://github.com/TheAlgorithms/Java.git
cd Java
mvn test   # or ./gradlew test if using Gradle
\`\`\`

2. Troubleshooting & tips
- Ensure Java 11+ is installed (see `pom.xml`).
- If tests fail: try: `mvn clean install`.
- For large builds: use `-DskipTests` when building and run tests separately.
- Contributions: add Javadoc, follow package structure, add tests for new algorithms.

3. How to get started
- Look for issues labelled `good first issue` (once template is active).
- Fork → branch → PR as per templates above.

