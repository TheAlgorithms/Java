Contributor starter template

This small template provides minimal files and guidance that can be used to initialize a new repository or provide a starter PR for an empty project.

Suggested files to add in a starter PR:
- README.md: Short project description, purpose, quick start, and how to contribute.
- CONTRIBUTING.md: Minimal contribution flow (fork -> branch -> PR), coding style notes, testing instructions.
- .github/ISSUE_TEMPLATE/good_first_issue.yml: Template for small tasks for new contributors (already added here).
- LICENSE: e.g., MIT or Apache-2.0.
- .github/workflows/build.yml: Basic CI to run tests on push/PR (optional).

Quick README example:

# ProjectName

Short one-line description.

## Getting started

1. Fork the repository
2. Build with: mvn -q -DskipTests package
3. Run tests: mvn -q test

## Contributing

See CONTRIBUTING.md and look for issues labeled `good first issue`.

---

Use this file as a checklist when opening an initial "starter" PR for a new repo. Keep the PR small and focused on onboarding (docs + ci), not large feature work.
