import pathlib
import sys


def _is_java_file_properly_located(java_file: pathlib.Path) -> bool:
    main_parents = java_file.parent.parents
    return (
        pathlib.Path("src/main/java/com/thealgorithms/") in main_parents
        or pathlib.Path("src/test/java/com/thealgorithms/") in main_parents
    )


def _find_misplaced_java_files() -> list[pathlib.Path]:
    return [
        java_file
        for java_file in pathlib.Path(".").rglob("*.java")
        if not _is_java_file_properly_located(java_file)
    ]


if __name__ == "__main__":
    misplaced_files = _find_misplaced_java_files()
    if misplaced_files:
        print("The following java files are not located in the correct directory:")
        for _ in misplaced_files:
            print(_)
        sys.exit(1)
