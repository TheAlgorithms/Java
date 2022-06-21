// For format details, see https://aka.ms/devcontainer.json. For config options, see the README at:
// https://github.com/microsoft/vscode-dev-containers/tree/v0.238.0/containers/java
{
	"name": "Java",
	"build": {
		"dockerfile": "Dockerfile",
		"args": {
			// Update the VARIANT arg to pick a Java version: 11, 17
			// Append -bullseye or -buster to pin to an OS version.
			// Use the -bullseye variants on local arm64/Apple Silicon.
			"VARIANT": "17-bullseye",
			// Options
			"INSTALL_MAVEN": "true",
			"INSTALL_GRADLE": "true",
			"NODE_VERSION": "lts/*"
		}
	},

	// Configure tool-specific properties.
	"customizations": {
		// Configure properties specific to VS Code.
		"vscode": {
			// Set *default* container specific settings.json values on container create.
			"settings": { 
			},
			
			// Add the IDs of extensions you want installed when the container is created.
			"extensions": [
				"vscjava.vscode-java-pack",
				"GitHub.copilot",
			]
		}
	},

	// Use 'forwardPorts' to make a list of ports inside the container available locally.
	// "forwardPorts": [],

	// Use 'postCreateCommand' to run commands after the container is created.
	"postCreateCommand": "java -version",

	// Comment out to connect as root instead. More info: https://aka.ms/vscode-remote/containers/non-root.
	"remoteUser": "vscode",
	"features": {
		"git": "os-provided",
		"github-cli": "latest"
	}
}
