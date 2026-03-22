# Security Policy

## Supported Versions

Security fixes are applied to the **latest stable release** and, where practical, to the current snapshot.

## Reporting a Vulnerability

**Please do not open a public GitHub issue for security vulnerabilities.**

Use [GitHub private vulnerability reporting](https://github.com/mokksy/mokksy/security/advisories/new) to disclose the issue confidentially.

Include:
- A description of the vulnerability and its potential impact
- Steps to reproduce or a minimal proof-of-concept
- Affected versions (if known)

## Response

- **Acknowledgement**: I aim to acknowledge reports within 5 business days.
- **Fixes**: handled on a best-effort basis; critical issues are prioritised.

I appreciate responsible disclosure and will credit reporters in the release notes unless they prefer to remain anonymous.

## Disclosure Policy

Security fixes are published as **patch releases**. Users who watch the repository for releases will be notified automatically via GitHub's release notification system.

Vulnerabilities in transitive dependencies should be reported to the respective upstream projects.

## Scope

This policy covers the Mokksy library code in this repository. It does not cover:
- Third-party dependencies (report to their maintainers)
- Issues in applications built *using* Mokksy
