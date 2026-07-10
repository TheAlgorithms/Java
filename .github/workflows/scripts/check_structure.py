#!/usr/bin/env python3
import pathlib
import sys
from typing import Iterator

# Utilisation de constantes en majuscules pour le Clean Code
VALID_PATHS = (
    pathlib.Path("src/main/java/com/thealgorithms/"),
    pathlib.Path("src/test/java/com/thealgorithms/"),
)


def _is_java_file_properly_located(java_file: pathlib.Path) -> bool:
    """Vérifie si un fichier Java est dans l'un des répertoires autorisés."""
    # any() est plus rapide et moderne pour valider une condition parmi plusieurs
    return any(valid_path in java_file.parents for valid_path in VALID_PATHS)


def _find_misplaced_java_files() -> Iterator[pathlib.Path]:
    """Recherche et retourne les fichiers Java mal placés sous forme de générateur."""
    # Remplacement de la liste par un générateur (Iterator) pour économiser la mémoire (approche Data)
    return (
        java_file
        for java_file in pathlib.Path(".").rglob("*.java")
        if not _is_java_file_properly_located(java_file)
    )


def main() -> None:
    """Fonction principale du script."""
    # Conversion du générateur en liste uniquement au moment de l'affichage
    misplaced_files = list(_find_misplaced_java_files())
    
    if misplaced_files:
        # Utilisation de f-strings modernes et écriture sur la sortie d'erreur standard (stderr)
        print(
            f"❌ Error: Found {len(misplaced_files)} Java file(s) in incorrect directories:",
            file=sys.stderr,
        )
        for file_path in misplaced_files:
            print(f"  - {file_path}", file=sys.stderr)
        sys.exit(1)
        
    print("✅ Success: All Java files are properly located.")


if __name__ == "__main__":
    main()
