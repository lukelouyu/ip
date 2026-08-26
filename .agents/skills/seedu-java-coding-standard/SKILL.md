---
name: seedu-java-coding-standard
description: Enforce the SE-EDU Java coding standard when editing or reviewing Java source in this repository.
---

# SE-EDU Java Coding Standard

Use the official SE-EDU Java coding standard (basic + intermediate) for every Java implementation, refactoring, and
code review in this repository. For topics the standard does not cover, use the Google Java Style Guide as directed by
the official standard.

Official standard: <https://se-education.org/guides/conventions/java/intermediate.html>

## Apply the standard

- Put every class in a package. Use lowercase package names rooted at the project name, `luke`, followed by logical
  subgroup names when the code is split into components.
- Name classes and enums with PascalCase nouns, methods with camelCase verbs, variables with camelCase English names,
  booleans to read as booleans, collections with plural nouns, and constants with SCREAMING_SNAKE_CASE.
- Use four spaces, never tabs. Keep lines below the 120-character hard limit and aim for fewer than 110 characters.
  Indent wrapped lines eight spaces beyond their parent line and wrap at readable, high-level boundaries.
- Use K&R braces. Always brace loop and conditional bodies, and put conditional bodies on separate lines.
- Indent `case` and `default` labels one level inside their `switch`, and indent their statements one further level.
  End each case with `break` or use an explicit `// Fallthrough` comment when fallthrough is intentional.
- Put spaces around operators and after Java keywords, commas, and `for`-loop semicolons. Separate logical units with
  one blank line.
- Attach array brackets to the type. Initialize variables at declaration when practical and declare them in the
  smallest useful scope. Do not expose non-constant class variables publicly unless the class is a behavior-free data
  class.
- List imports explicitly, remove unused imports, and keep import ordering consistent. Group static imports first,
  followed by ordinary imports grouped consistently by package family.
- Write English comments using American spelling. Add descriptive Javadoc header comments to every public class and
  public method, except getters/setters, exact overrides, and test code where the official standard allows omission.
  Start method summaries with a third-person verb such as `Returns`, `Adds`, or `Marks`; document parameters, return
  values, and thrown exceptions when doing so adds information.
- Give each class a logical, explainable member order rather than appending members chronologically. Keep overloads of
  the same method or constructor together.

## Preserve behavior

When applying this skill as a style-only task, do not change command semantics, output text, data flow, or add future
features. Compile with Java 25 and exercise the existing behavior after editing.
