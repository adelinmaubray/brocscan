# brocscan — Agent Coding Guidelines

## Project Overview

**brocscan** is a Kotlin Multiplatform (KMP) application with a local database and QR Code reader for scanning tickets to enter a second-hand goods event. It
targets **Android**, **iOS**, and **Desktop (JVM)** using Compose Multiplatform for a shared UI.

- **Package**: `be.ami.maubray.brocscan`
- **Build system**: Gradle (Kotlin DSL) with Version Catalogs (`libs.*`)
- **UI framework**: Jetpack Compose / Compose Multiplatform (Material 3)
- **Database**: SQLDelight
- **Minimum SDK**: Android minSdk defined in version catalog, JVM 11

## Module Structure

```
brocscan/
├── androidApp/          # Android application module
├── desktopApp/          # Desktop (JVM) application module
├── shared/              # Shared KMP module (core logic + UI)
│   └── src/
│       ├── commonMain/  # Shared code across all platforms
│       ├── androidMain/ # Android-specific implementations
│       ├── iosMain/     # iOS-specific implementations
│       └── jvmMain/     # JVM/Desktop-specific implementations
├── gradle/              # Gradle wrapper + version catalog (libs.versions.toml)
├── build.gradle.kts     # Root build file (plugins only)
└── settings.gradle.kts  # Project settings and included modules
```

### Shared Module Source Sets

- **`commonMain`** — All shared code lives here:
	- `be.ami.maubray.brocscan/App.kt` — Main `@Composable` entry point
	- `be.ami.maubray.brocscan/Platform.kt` — Expect/actual platform interface
	- `be.ami.maubray.brocscan/DeviceVibrator.kt` — Expect/actual haptic feedback
	- `be.ami.maubray.brocscan/model/` — Data models (e.g., `Entry.kt`)
	- `be.ami.maubray.brocscan/composable/` — Reusable Compose components
	- `be.ami.maubray.brocscan/composable/layout/` — Screen layout composables
	- `be.ami.maubray.brocscan/style/` — Theme, colors, typography
	- `sqldelight/` — SQLDelight schema and queries (`.sq` files)
- **`androidMain`** — Android expect/actual implementations
- **`iosMain`** — iOS expect/actual implementations + `MainViewController.kt`
- **`jvmMain`** — JVM expect/actual implementations

## Key Dependencies

Managed via Gradle Version Catalog (`gradle/libs.versions.toml`):

- **Compose**: runtime, foundation, material3, materialIconsExtended, UI, resources
- **Lifecycle**: `androidx.lifecycle.viewmodelCompose`, `androidx.lifecycle.runtimeCompose`
- **Database**: SQLDelight runtime + platform-specific drivers (Android, Native)
- **Utilities**: `kotlinx-datetime`
- **Testing**: `kotlin-test`

## FileKit (Kotlin Multiplatform file APIs)

- Docs: https://filekit.mintlify.app (MCP server: https://filekit.mintlify.app/mcp, full text: https://filekit.mintlify.app/llms-full.txt).
- Modules: `filekit-core` (PlatformFile, read/write, file system), `filekit-dialogs` (suspend pickers/saver),
  `filekit-dialogs-compose` (`remember…Launcher` for Compose), `filekit-coil` (Coil 3 support). Group `io.github.vinceglb`.
- Most APIs are extension functions: import them from `io.github.vinceglb.filekit` (core),
  `io.github.vinceglb.filekit.dialogs`, `io.github.vinceglb.filekit.dialogs.compose`, or `io.github.vinceglb.filekit.coil`.
- Use `PlatformFile`, not `java.io.File` or `NSURL`, in shared code. Reading and writing (`readBytes()`, `readString()`,
  `write()`, `writeString()`) are `suspend` functions.
- Pickers return `null` when the user cancels, except `SingleWithState`/`MultipleWithState`, which emit
  `FileKitPickerState.Cancelled`. Prefer Compose launcher overloads that take `onError`.
- Platform setup: `FileKit.init(appId = ...)` on JVM desktop, Linux native, and Windows native before using app
  directories. On Android, `FileKit.init(activity)` before calling suspend dialog APIs (Compose launchers do it for you).
- Some APIs are platform-specific (for example, the file saver is not available on JS/Wasm; use `FileKit.download`).
  Check https://filekit.mintlify.app/platform-support before using an API in `commonMain`.

## Coding Conventions

### Kotlin Style

- **Indentation**: 4 spaces
- **Naming**: camelCase for functions/variables, PascalCase for classes/composables
- **Imports**: Grouped and sorted (standard library → third-party → project)
- **Composables**: Function-style composables with `@Composable` annotation, PascalCase names
- **Expect/Actual**: Use `expect` in `commonMain` and `actual` in platform-specific source sets for platform-specific behavior

### Compose Guidelines

- Use Material 3 components (`MaterialTheme`, `Scaffold`, etc.)
- Define app-specific color scheme and typography in `style/` package
- Use `Modifier` chaining for layout adjustments
- Keep composables focused and single-purpose

### SQLDelight

- Schema files live in `shared/src/commonMain/sqldelight/`
- Generated code is in `shared/build/generated/sqldelight/`
- Use typed queries defined in `.sq` files

## Building & Running

### Build commands

```bash
# Build all platforms
./gradlew assemble

# Build Android debug APK
./gradlew :androidApp:assembleDebug

# Build Desktop native distribution
./gradlew :desktopApp:packageTar

# Run Desktop app
./gradlew :desktopApp:run

# Run iOS (requires Xcode)
open iosApp/iosApp.xcodeproj
```

### Gradle wrapper

- Use `./gradlew` (Unix) or `gradlew.bat` (Windows)
- JVM toolchain resolved via `foojay-resolver-convention`

## Adding New Features

1. **Shared logic/UI**: Add files in `shared/src/commonMain/kotlin/be/ami/maubray/brocscan/`
2. **Platform-specific code**: Use expect/actual pattern or add to the platform-specific source set
3. **New composables**: Place in `composable/` or `composable/layout/` depending on scope
4. **New models**: Add to `model/` package
5. **Database changes**: Update `.sq` files in `sqldelight/`, then regenerate

## Agent Skills

This project has Kotlin agent skills installed (tracked in `skills-lock.json` from `Kotlin/kotlin-agent-skills`).
When working on tasks that match their domain, consult the corresponding skill before generating code or advice.

| Skill                                                  | When to Use                                                                                   |
|--------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `kotlin-backend-jpa-entity-mapping`                    | JPA entity design, identity/equality, relationships, N+1 queries, Kotlin-specific ORM traps   |
| `kotlin-tooling-agp9-migration`                        | Migrating to Android Gradle Plugin 9.0+ (plugin replacement, DSL migration, module splitting) |
| `kotlin-tooling-cocoapods-spm-migration`               | Migrating KMP projects from CocoaPods to Swift Package Manager                                |
| `kotlin-tooling-gradle-to-kotlin-toolchain-plugin`     | Porting a Gradle plugin to a Kotlin Toolchain local plugin                                    |
| `kotlin-tooling-gradle-to-kotlin-toolchain-project`    | Migrating an entire Gradle project to the Kotlin Toolchain                                    |
| `kotlin-tooling-immutable-collections-0-5-x-migration` | Migrating `kotlinx.collections.immutable` from 0.3.x/0.4.x to 0.5.x                           |
| `kotlin-tooling-java-to-kotlin`                        | Converting Java source files to idiomatic Kotlin                                              |
| `kotlin-tooling-kotlin-toolchain`                      | Building, running, or configuring projects with the Kotlin Toolchain                          |
| `kotlin-tooling-kotlin-toolchain-plugin-authoring`     | Authoring Kotlin Toolchain local plugins                                                      |
| `kotlin-tooling-native-build-performance`              | Diagnosing and fixing slow Kotlin/Native compilation and linking for iOS targets              |

## Important Notes

- The root `build.gradle.kts` only declares plugins; all logic is in module-level build files
- Version catalog aliases are accessed via `libs.*` (e.g., `libs.plugins.composeMultiplatform`)
- iOS app is configured in `iosApp/` (Xcode project)
- Desktop app main class: `be.ami.maubray.brocscan.MainKt`
- Application version: `3.0.0`
